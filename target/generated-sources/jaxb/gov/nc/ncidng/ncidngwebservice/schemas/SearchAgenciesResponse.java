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
 *         &lt;element ref="{http://ncidng.nc.gov/NCIDNGWebService/schemas}searchAgenciesResult"/>
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
    "searchAgenciesResult"
})
@XmlRootElement(name = "searchAgenciesResponse")
public class SearchAgenciesResponse {

    @XmlElement(required = true)
    protected SearchAgenciesResult searchAgenciesResult;

    /**
     * Gets the value of the searchAgenciesResult property.
     * 
     * @return
     *     possible object is
     *     {@link SearchAgenciesResult }
     *     
     */
    public SearchAgenciesResult getSearchAgenciesResult() {
        return searchAgenciesResult;
    }

    /**
     * Sets the value of the searchAgenciesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchAgenciesResult }
     *     
     */
    public void setSearchAgenciesResult(SearchAgenciesResult value) {
        this.searchAgenciesResult = value;
    }

}
