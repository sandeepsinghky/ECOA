package nc.dhhs.nccss.acts.ecoa.web.controller.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartAddress;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartContact;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartEmp;
import nc.dhhs.nccss.acts.ecoa.beans.CaseParticipant;

/**
 * @author Phani Konuru
 * 
 */

public class CPparamData extends ParamData
{

	/**
	 * 
	 */
	private static final long		serialVersionUID	= -2356699988788401552L;

	private CaseParticipant			participant			= null;

	private List<CasePartAddress>	addressList			= null;

	private List<CasePartContact>	contactList			= null;

	private Map<String, String>		incSrcList			= null;

	private CasePartEmp				empInfo				= null;

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
	 * @return the incSrcList
	 */
	public Map<String, String> getIncSrcList()
	{
		return incSrcList;
	}

	/**
	 * @param incSrcList
	 *            the incSrcList to set
	 */
	public void setIncSrcList(Map<String, String> incSrcList)
	{
		this.incSrcList = incSrcList;
	}

	/**
	 * @return the empInfo
	 */
	public CasePartEmp getEmpInfo()
	{
		return empInfo;
	}

	/**
	 * @param empInfo
	 *            the empInfo to set
	 */
	public void setEmpInfo(CasePartEmp empInfo)
	{
		this.empInfo = empInfo;
	}

}