
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
 *         <element name="Currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="DateLow" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="DateHigh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "currency",
    "dateLow",
    "dateHigh"
})
@XmlRootElement(name = "getExchangeAvgRate")
public class GetExchangeAvgRate {

    @XmlElement(name = "Currency")
    protected String currency;
    @XmlElement(name = "DateLow")
    protected String dateLow;
    @XmlElement(name = "DateHigh")
    protected String dateHigh;

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the dateLow property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateLow() {
        return dateLow;
    }

    /**
     * Sets the value of the dateLow property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateLow(String value) {
        this.dateLow = value;
    }

    /**
     * Gets the value of the dateHigh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateHigh() {
        return dateHigh;
    }

    /**
     * Sets the value of the dateHigh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateHigh(String value) {
        this.dateHigh = value;
    }

}
