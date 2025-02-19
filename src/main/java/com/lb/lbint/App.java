package com.lb.lbint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class App {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DBManager db = new DBManager();
		SoapServiceCaller soap = new SoapServiceCaller();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		float conValue = 0;
		loop: while(true){
			printInstructions();
			switch(reader.readLine()) {
				case "1":
					System.out.println("Pasirinkti klabą. LT EN");
					soap.printCurrencyList(reader.readLine().toUpperCase());
					break;
				case "2":
					System.out.println("Įveskite norimos valiutos kodą");
					soap.printCurrencyRate(reader.readLine());
					break;
				case "3":
					System.out.println("Įveskite valiutos kodą");
					String curr = reader.readLine();
					System.out.println("Įveskite valiutos į kuria konvertuojama kodą");
					String currCon = reader.readLine();
					System.out.println("Įveskite valiutos konvertavimo sumą");
					try {
						conValue = Float.parseFloat(reader.readLine());
					} catch (NumberFormatException e) {						
						System.out.println("Blogai įvesta konvertacijos suma");
						break;
					}
					CurrencyCalc.printCurrencyConversion(soap, db, curr, currCon, conValue);
					break;
				case "4":
					db.getRecent();
					break;
				case "5":
					break loop;
				case "6":
					db.update((float) 12.7);
				default:
					System.out.println("Tokia komanda neegzistuoja");
					break;
			}
		}
	}
	
	private static void printInstructions() {
        System.out.println("1 - Atspausdinti galimų sąrašą valiutų");
        System.out.println("2 - Gauti valiutos keitimo kursą.");
        System.out.println("3 - Valiutų konvertacija.");
        System.out.println("4 - Valiutų konvertacijos istorija.");
        System.out.println("5 - Baigti programos vykdymą.");
	}

}
