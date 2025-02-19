
package com.soap.ws.lb;

import java.math.BigDecimal;
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
 *         <element name="getCurrentExchangeRateResult" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
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
    "getCurrentExchangeRateResult"
})
@XmlRootElement(name = "getCurrentExchangeRateResponse")
public class GetCurrentExchangeRateResponse {

    @XmlElement(required = true)
    protected BigDecimal getCurrentExchangeRateResult;

    /**
     * Gets the value of the getCurrentExchangeRateResult property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getGetCurrentExchangeRateResult() {
        return getCurrentExchangeRateResult;
    }

    /**
     * Sets the value of the getCurrentExchangeRateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setGetCurrentExchangeRateResult(BigDecimal value) {
        this.getCurrentExchangeRateResult = value;
    }

}
