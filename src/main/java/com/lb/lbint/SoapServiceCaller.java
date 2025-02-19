package com.lb.lbint;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SoapServiceCaller {

	private String endpointUrl;
	private Map<String, String> headers;

	public SoapServiceCaller() {
		endpointUrl = "https://www.lb.lt/webservices/fxrates/fxrates.asmx";
		headers = new HashMap<>();
	}

	public String invokeSoapService(String endpointUrl, String body) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) (new URL(endpointUrl)).openConnection();

		// Request body
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte[] bodyBytes = body.getBytes();
		bout.write(bodyBytes);

		// Creating and adding header to connection
		headers.put("Content-Length", String.valueOf(body.getBytes().length));
		headers.put("Content-Type", "text/xml; charset=utf-8");
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		addHeaders(connection);

		// Writing to HTTP connection out stream
		OutputStream out = connection.getOutputStream();
		out.write(bout.toByteArray());
		out.close();

		// Sending and getting response
		return getSendRequestAndGetResponse(connection);
	}

	private void addHeaders(HttpURLConnection connection) {
		headers.forEach(connection::setRequestProperty);
	}

	private String getSendRequestAndGetResponse(HttpURLConnection connection) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null)
			builder.append(line);
		return builder.toString();
	}

	private NodeList callCurrencyList() throws ParserConfigurationException, SAXException, IOException {
		String response = null;
		headers = new HashMap<>();
		String body = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:fxr=\"http://www.lb.lt/WebServices/FxRates\"><soapenv:Header/><soapenv:Body><fxr:getCurrencyList/></soapenv:Body></soapenv:Envelope>";
		try {
			response = this.invokeSoapService(endpointUrl, body);
		} catch (IOException e) { // Auto-generated catch block
			e.printStackTrace();
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;

		builder = factory.newDocumentBuilder();
		StringBuilder xmlStringBuilder = new StringBuilder();
		xmlStringBuilder.append(response);
		ByteArrayInputStream input;

		input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
		Document doc = builder.parse(input);
		doc.getDocumentElement().normalize();
		return doc.getElementsByTagName("CcyNtry");
	}

	private NodeList callCurrencyRate(String pCurrency) throws ParserConfigurationException, SAXException, IOException {
		String response = null;
		headers = new HashMap<>();
		String body = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:fxr=\"http://www.lb.lt/WebServices/FxRates\"><soapenv:Header/><soapenv:Body><fxr:getFxRatesForCurrency><fxr:tp>EU</fxr:tp><fxr:ccy>"
				+ pCurrency.toUpperCase() + "</fxr:ccy></fxr:getFxRatesForCurrency></soapenv:Body></soapenv:Envelope>";

		try {
			response = this.invokeSoapService(endpointUrl, body);
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;

		builder = factory.newDocumentBuilder();
		StringBuilder xmlStringBuilder = new StringBuilder();
		xmlStringBuilder.append(response);
		ByteArrayInputStream input;

		input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));
		Document doc = builder.parse(input);
		doc.getDocumentElement().normalize();
		return doc.getElementsByTagName("CcyAmt");
	}

	public boolean currencyExists(String pCurrency) {
		NodeList nList = null;
		try {
			nList = callCurrencyList();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			return false;
		}
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				if (eElement.getElementsByTagName("Ccy").item(0).getTextContent().equals(pCurrency.toUpperCase()))
					return true;
			}
		}
		System.out.println(pCurrency + " - tokios valiutos nėra.");
		return false;
	}

	public void printCurrencyList(String pLang) {
		if (!(pLang.equals("LT") || pLang.equals("EN"))) {
			System.out.println(pLang + " - valiutos pavadinimo tokia kalba nėra. Yra LT arba EN");
		}

		NodeList nList = null;
		try {
			nList = callCurrencyList();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			return;
		}
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				NodeList langNodes = eElement.getElementsByTagName("CcyNm");
				for (int j = 0; j < langNodes.getLength(); j++) {
					Element langElement = (Element) langNodes.item(j);
					String lang = langElement.getAttribute("lang");
					String currency = eElement.getElementsByTagName("Ccy").item(0).getTextContent();
					String currencyName = langElement.getTextContent();
					if (lang.equals(pLang)) {
						System.out.println(currency + " - " + currencyName);
						break;
					}
				}
			}
		}
	}

	public void printCurrencyRate(String pCurrency) {
		if (pCurrency.toUpperCase().equals("EUR")) {
			System.out.println("EUR - 1");
			return;
		}
		if (!currencyExists(pCurrency))
			return;

		NodeList nList = null;
		try {
			nList = callCurrencyRate(pCurrency);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		Node nNode = nList.item(1);
		Element eElement = (Element) nNode;

		String currency = eElement.getElementsByTagName("Amt").item(0).getTextContent();
		System.out.println("EUR - 1");
		System.out.println(pCurrency + " - " + currency);
	}

	public float getCurrencyRate(String pCurrency) {
		if (pCurrency.equals("EUR"))
			return 1;
		if (!currencyExists(pCurrency))
			return (Float) null;

		NodeList nList = null;
		try {
			nList = callCurrencyRate(pCurrency);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		Node nNode = nList.item(1);
		Element eElement = (Element) nNode;
		if (eElement == null)
			return 0;
		return Float.parseFloat(eElement.getElementsByTagName("Amt").item(0).getTextContent());
	}
}
