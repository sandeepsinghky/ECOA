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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;group ref="{http://ncidng.nc.gov/NCIDNGWebService/schemas}userinfogroup"/>
 *         &lt;element ref="{http://ncidng.nc.gov/NCIDNGWebService/schemas}RoleArray" minOccurs="0"/>
 *         &lt;element ref="{http://ncidng.nc.gov/NCIDNGWebService/schemas}ResourceArray" minOccurs="0"/>
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
    "userDN",
    "userID",
    "personalTitle",
    "firstName",
    "middleInitial",
    "lastName",
    "fullName",
    "suffix",
    "userType",
    "employeeType",
    "guid",
    "accountExpirationDate",
    "loginDisabled",
    "lockedByIntruder",
    "passwordExpirationDate",
    "secondFactorRole",
    "accountStatus",
    "migrationStatus",
    "businessPhone",
    "businessPhoneExt",
    "eMail",
    "street",
    "addressLine2",
    "city",
    "state",
    "zipCode",
    "crConfig",
    "memberOfOrganizationArray",
    "memberOfDivisionArray",
    "memberOfSectionArray",
    "roleArray",
    "resourceArray"
})
@XmlRootElement(name = "Entry")
public class Entry {

    @XmlElement(name = "User_DN", required = true)
    protected String userDN;
    @XmlElement(name = "User_ID", required = true)
    protected String userID;
    @XmlElement(name = "Personal_Title")
    protected String personalTitle;
    @XmlElement(name = "First_Name", required = true)
    protected String firstName;
    @XmlElement(name = "Middle_Initial")
    protected String middleInitial;
    @XmlElement(name = "Last_Name", required = true)
    protected String lastName;
    @XmlElement(name = "Full_Name", required = true)
    protected String fullName;
    @XmlElement(name = "Suffix")
    protected String suffix;
    @XmlElement(name = "User_Type", required = true)
    protected String userType;
    @XmlElement(name = "Employee_Type")
    protected String employeeType;
    @XmlElement(name = "GUID", required = true)
    protected String guid;
    @XmlElement(name = "Account_Expiration_Date")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar accountExpirationDate;
    @XmlElement(name = "Login_Disabled")
    protected Boolean loginDisabled;
    @XmlElement(name = "Locked_by_Intruder")
    protected Boolean lockedByIntruder;
    @XmlElement(name = "Password_Expiration_Date")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar passwordExpirationDate;
    @XmlElement(name = "Second_Factor_Role")
    protected String secondFactorRole;
    @XmlElement(name = "Account_Status", required = true)
    protected String accountStatus;
    @XmlElement(name = "Migration_Status")
    protected Boolean migrationStatus;
    @XmlElement(name = "Business_Phone")
    protected String businessPhone;
    @XmlElement(name = "Business_Phone_Ext")
    protected String businessPhoneExt;
    @XmlElement(required = true)
    protected String eMail;
    @XmlElement(name = "Street")
    protected String street;
    @XmlElement(name = "Address_Line2")
    protected String addressLine2;
    @XmlElement(name = "City")
    protected String city;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "Zip_Code")
    protected String zipCode;
    @XmlElement(name = "CR_Config")
    protected Boolean crConfig;
    @XmlElement(name = "Member_of_OrganizationArray")
    protected MemberOfOrganizationArray memberOfOrganizationArray;
    @XmlElement(name = "Member_of_DivisionArray")
    protected MemberOfDivisionArray memberOfDivisionArray;
    @XmlElement(name = "Member_of_SectionArray")
    protected MemberOfSectionArray memberOfSectionArray;
    @XmlElement(name = "RoleArray")
    protected RoleArray roleArray;
    @XmlElement(name = "ResourceArray")
    protected ResourceArray resourceArray;

    /**
     * Gets the value of the userDN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserDN() {
        return userDN;
    }

    /**
     * Sets the value of the userDN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserDN(String value) {
        this.userDN = value;
    }

    /**
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
    }

    /**
     * Gets the value of the personalTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonalTitle() {
        return personalTitle;
    }

    /**
     * Sets the value of the personalTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonalTitle(String value) {
        this.personalTitle = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the middleInitial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleInitial() {
        return middleInitial;
    }

    /**
     * Sets the value of the middleInitial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleInitial(String value) {
        this.middleInitial = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the fullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Gets the value of the suffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Sets the value of the suffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuffix(String value) {
        this.suffix = value;
    }

    /**
     * Gets the value of the userType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the value of the userType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserType(String value) {
        this.userType = value;
    }

    /**
     * Gets the value of the employeeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeType() {
        return employeeType;
    }

    /**
     * Sets the value of the employeeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeType(String value) {
        this.employeeType = value;
    }

    /**
     * Gets the value of the guid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGUID() {
        return guid;
    }

    /**
     * Sets the value of the guid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGUID(String value) {
        this.guid = value;
    }

    /**
     * Gets the value of the accountExpirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAccountExpirationDate() {
        return accountExpirationDate;
    }

    /**
     * Sets the value of the accountExpirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAccountExpirationDate(XMLGregorianCalendar value) {
        this.accountExpirationDate = value;
    }

    /**
     * Gets the value of the loginDisabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLoginDisabled() {
        return loginDisabled;
    }

    /**
     * Sets the value of the loginDisabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLoginDisabled(Boolean value) {
        this.loginDisabled = value;
    }

    /**
     * Gets the value of the lockedByIntruder property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLockedByIntruder() {
        return lockedByIntruder;
    }

    /**
     * Sets the value of the lockedByIntruder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLockedByIntruder(Boolean value) {
        this.lockedByIntruder = value;
    }

    /**
     * Gets the value of the passwordExpirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPasswordExpirationDate() {
        return passwordExpirationDate;
    }

    /**
     * Sets the value of the passwordExpirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPasswordExpirationDate(XMLGregorianCalendar value) {
        this.passwordExpirationDate = value;
    }

    /**
     * Gets the value of the secondFactorRole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondFactorRole() {
        return secondFactorRole;
    }

    /**
     * Sets the value of the secondFactorRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondFactorRole(String value) {
        this.secondFactorRole = value;
    }

    /**
     * Gets the value of the accountStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountStatus() {
        return accountStatus;
    }

    /**
     * Sets the value of the accountStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountStatus(String value) {
        this.accountStatus = value;
    }

    /**
     * Gets the value of the migrationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMigrationStatus() {
        return migrationStatus;
    }

    /**
     * Sets the value of the migrationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMigrationStatus(Boolean value) {
        this.migrationStatus = value;
    }

    /**
     * Gets the value of the businessPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessPhone() {
        return businessPhone;
    }

    /**
     * Sets the value of the businessPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessPhone(String value) {
        this.businessPhone = value;
    }

    /**
     * Gets the value of the businessPhoneExt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessPhoneExt() {
        return businessPhoneExt;
    }

    /**
     * Sets the value of the businessPhoneExt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessPhoneExt(String value) {
        this.businessPhoneExt = value;
    }

    /**
     * Gets the value of the eMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * Sets the value of the eMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMail(String value) {
        this.eMail = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the addressLine2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets the value of the addressLine2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine2(String value) {
        this.addressLine2 = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the zipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the value of the zipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipCode(String value) {
        this.zipCode = value;
    }

    /**
     * Gets the value of the crConfig property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCRConfig() {
        return crConfig;
    }

    /**
     * Sets the value of the crConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCRConfig(Boolean value) {
        this.crConfig = value;
    }

    /**
     * Gets the value of the memberOfOrganizationArray property.
     * 
     * @return
     *     possible object is
     *     {@link MemberOfOrganizationArray }
     *     
     */
    public MemberOfOrganizationArray getMemberOfOrganizationArray() {
        return memberOfOrganizationArray;
    }

    /**
     * Sets the value of the memberOfOrganizationArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link MemberOfOrganizationArray }
     *     
     */
    public void setMemberOfOrganizationArray(MemberOfOrganizationArray value) {
        this.memberOfOrganizationArray = value;
    }

    /**
     * Gets the value of the memberOfDivisionArray property.
     * 
     * @return
     *     possible object is
     *     {@link MemberOfDivisionArray }
     *     
     */
    public MemberOfDivisionArray getMemberOfDivisionArray() {
        return memberOfDivisionArray;
    }

    /**
     * Sets the value of the memberOfDivisionArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link MemberOfDivisionArray }
     *     
     */
    public void setMemberOfDivisionArray(MemberOfDivisionArray value) {
        this.memberOfDivisionArray = value;
    }

    /**
     * Gets the value of the memberOfSectionArray property.
     * 
     * @return
     *     possible object is
     *     {@link MemberOfSectionArray }
     *     
     */
    public MemberOfSectionArray getMemberOfSectionArray() {
        return memberOfSectionArray;
    }

    /**
     * Sets the value of the memberOfSectionArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link MemberOfSectionArray }
     *     
     */
    public void setMemberOfSectionArray(MemberOfSectionArray value) {
        this.memberOfSectionArray = value;
    }

    /**
     * Gets the value of the roleArray property.
     * 
     * @return
     *     possible object is
     *     {@link RoleArray }
     *     
     */
    public RoleArray getRoleArray() {
        return roleArray;
    }

    /**
     * Sets the value of the roleArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoleArray }
     *     
     */
    public void setRoleArray(RoleArray value) {
        this.roleArray = value;
    }

    /**
     * Gets the value of the resourceArray property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceArray }
     *     
     */
    public ResourceArray getResourceArray() {
        return resourceArray;
    }

    /**
     * Sets the value of the resourceArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceArray }
     *     
     */
    public void setResourceArray(ResourceArray value) {
        this.resourceArray = value;
    }

}
