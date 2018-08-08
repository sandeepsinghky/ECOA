package nc.dhhs.nccss.acts.ecoa.web.controller.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartAddress;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartBenefit;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartChldAffil;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartContact;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartInsurance;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartOther;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartPaternty;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartProfile;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartProfileExt;
import nc.dhhs.nccss.acts.ecoa.beans.CaseParticipant;

/**
 * @author Phani Konuru
 * 
 */

public class ChldparamData extends ParamData
{

	/**
	 * 
	 */
	private static final long				serialVersionUID	= -6627870399532813166L;

	private CaseParticipant					participant			= null;

	private CasePartProfile					partProfile			= null;

	private List<CasePartAddress>			addressList			= null;

	private List<CasePartContact>			contactList			= null;

	private Map<String, CasePartBenefit>	benifitList			= null;

	private Map<String, String>				chldNcpList			= null;

	private List<CasePartChldAffil>			chldNcpToAddList	= null;

	private Map<String, CasePartInsurance>	insuranceList		= null;

	private Map<String, CasePartOther>		partOtherList		= null;

	private CasePartPaternty				partPaternity		= null;

	private Map<String, CasePartProfileExt>	partProfileExtList	= null;

	/**
	 * @return the participant
	 */
	public CaseParticipant getParticipant()
	{
		return participant;
	}

	/**
	 * @param participant
	 *            the participant to set
	 */
	public void setParticipant(CaseParticipant participant)
	{
		this.participant = participant;
	}

	/**
	 * @return the partProfile
	 */
	public CasePartProfile getPartProfile()
	{
		return partProfile;
	}

	/**
	 * @param partProfile
	 *            the partProfile to set
	 */
	public void setPartProfile(CasePartProfile partProfile)
	{
		this.partProfile = partProfile;
	}

	/**
	 * @return the addressList
	 */
	public List<CasePartAddress> getAddressList()
	{
		return addressList;
	}

	/**
	 * @param addressList
	 *            the addressList to set
	 */
	public void setAddressList(List<CasePartAddress> addressList)
	{
		this.addressList = addressList;
	}

	public void addAddr(CasePartAddress addr)
	{
		if (getAddressList() == null)
		{
			addressList = new ArrayList();
			addressList.add(addr);
		}
		else
		{
			addressList.add(addr);
		}
	}

	/**
	 * @return the contactList
	 */
	public List<CasePartContact> getContactList()
	{
		return contactList;
	}

	/**
	 * @param contactList
	 *            the contactList to set
	 */
	public void setContactList(List<CasePartContact> contactList)
	{
		this.contactList = contactList;
	}

	/**
	 * @param contact
	 */
	public void addContact(CasePartContact contact)
	{
		if (getContactList() == null)
		{
			contactList = new ArrayList();
			contactList.add(contact);
		}
		else
		{
			contactList.add(contact);
		}
	}

	/**
	 * @return the benifitList
	 */
	public Map<String, CasePartBenefit> getBenifitList()
	{
		return benifitList;
	}

	/**
	 * @param benifitList
	 *            the benifitList to set
	 */
	public void setBenifitList(Map<String, CasePartBenefit> benifitList)
	{
		this.benifitList = benifitList;
	}

	/**
	 * @return the chldNcpList
	 */
	public Map<String, String> getChldNcpList()
	{
		return chldNcpList;
	}

	/**
	 * @param chldNcpList
	 *            the chldNcpList to set
	 */
	public void setChldNcpList(Map<String, String> chldNcpList)
	{
		this.chldNcpList = chldNcpList;
	}

	/**
	 * @return the chldNcpToAddList
	 */
	public List<CasePartChldAffil> getChldNcpToAddList()
	{
		return chldNcpToAddList;
	}

	/**
	 * @param chldNcpToAddList
	 *            the chldNcpToAddList to set
	 */
	public void setChldNcpToAddList(List<CasePartChldAffil> chldNcpToAddList)
	{
		this.chldNcpToAddList = chldNcpToAddList;
	}

	/**
	 * @return the insuranceList
	 */
	public Map<String, CasePartInsurance> getInsuranceList()
	{
		return insuranceList;
	}

	/**
	 * @param insuranceList
	 *            the insuranceList to set
	 */
	public void setInsuranceList(Map<String, CasePartInsurance> insuranceList)
	{
		this.insuranceList = insuranceList;
	}

	/**
	 * @return the partOtherList
	 */
	public Map<String, CasePartOther> getPartOtherList()
	{
		return partOtherList;
	}

	/**
	 * @param partOtherList
	 *            the partOtherList to set
	 */
	public void setPartOtherList(Map<String, CasePartOther> partOtherList)
	{
		this.partOtherList = partOtherList;
	}

	/**
	 * @return the partPaternity
	 */
	public CasePartPaternty getPartPaternity()
	{
		return partPaternity;
	}

	/**
	 * @param partPaternity
	 *            the partPaternity to set
	 */
	public void setPartPaternity(CasePartPaternty partPaternity)
	{
		this.partPaternity = partPaternity;
	}

	/**
	 * @return the partProfileExtList
	 */
	public Map<String, CasePartProfileExt> getPartProfileExtList()
	{
		return partProfileExtList;
	}

	/**
	 * @param partProfileExtList
	 *            the partProfileExtList to set
	 */
	public void setPartProfileExtList(Map<String, CasePartProfileExt> partProfileExtList)
	{
		this.partProfileExtList = partProfileExtList;
	}

}