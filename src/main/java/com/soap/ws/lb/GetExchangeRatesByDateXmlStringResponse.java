
package com.soap.ws.lb;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="getExchangeRatesByDate_XmlStringResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getExchangeRatesByDateXmlStringResult"
})
@XmlRootElement(name = "getExchangeRatesByDate_XmlStringResponse")
public class GetExchangeRatesByDateXmlStringResponse {

    @XmlElement(name = "getExchangeRatesByDate_XmlStringResult")
    protected String getExchangeRatesByDateXmlStringResult;

    /**
     * Gets the value of the getExchangeRatesByDateXmlStringResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetExchangeRatesByDateXmlStringResult() {
        return getExchangeRatesByDateXmlStringResult;
    }

    /**
     * Sets the value of the getExchangeRatesByDateXmlStringResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetExchangeRatesByDateXmlStringResult(String value) {
        this.getExchangeRatesByDateXmlStringResult = value;
    }

}
