//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.06.14 at 01:38:34 PM EDT 
//


package gov.nc.ncidng.ncidngwebservice.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://ncidng.nc.gov/NCIDNGWebService/schemas}authenticateToNCIDv2Result"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "authenticateToNCIDv2Result"
})
@XmlRootElement(name = "authenticateToNCIDv2Response")
public class AuthenticateToNCIDv2Response {

    @XmlElement(required = true)
    protected AuthenticateToNCIDv2Result authenticateToNCIDv2Result;

    /**
     * Gets the value of the authenticateToNCIDv2Result property.
     * 
     * @return
     *     possible object is
     *     {@link AuthenticateToNCIDv2Result }
     *     
     */
    public AuthenticateToNCIDv2Result getAuthenticateToNCIDv2Result() {
        return authenticateToNCIDv2Result;
    }

    /**
     * Sets the value of the authenticateToNCIDv2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticateToNCIDv2Result }
     *     
     */
    public void setAuthenticateToNCIDv2Result(AuthenticateToNCIDv2Result value) {
        this.authenticateToNCIDv2Result = value;
    }

}
