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
 *         &lt;element ref="{http://ncidng.nc.gov/NCIDNGWebService/schemas}viewUserByGUIDResult"/>
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
    "viewUserByGUIDResult"
})
@XmlRootElement(name = "viewUserByGUIDResponse")
public class ViewUserByGUIDResponse {

    @XmlElement(required = true)
    protected ViewUserByGUIDResult viewUserByGUIDResult;

    /**
     * Gets the value of the viewUserByGUIDResult property.
     * 
     * @return
     *     possible object is
     *     {@link ViewUserByGUIDResult }
     *     
     */
    public ViewUserByGUIDResult getViewUserByGUIDResult() {
        return viewUserByGUIDResult;
    }

    /**
     * Sets the value of the viewUserByGUIDResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ViewUserByGUIDResult }
     *     
     */
    public void setViewUserByGUIDResult(ViewUserByGUIDResult value) {
        this.viewUserByGUIDResult = value;
    }

}
