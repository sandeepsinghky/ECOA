package nc.dhhs.nccss.acts.ecoa.web.controller.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.dhhs.nccss.acts.ecoa.beans.CaseCourtOrder;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartAddress;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartContact;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartEmp;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartNCPExt;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartOther;
import nc.dhhs.nccss.acts.ecoa.beans.CaseParticipant;
import nc.dhhs.nccss.acts.ecoa.beans.Vehicle;

/**
 * @author Phani Konuru
 * 
 */

public class NCPparamData extends ParamData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4198711793410995509L;

	private CaseParticipant participant = null;

	private List<CasePartAddress> addressList = null;

	private List<CasePartContact> contactList = null;

	private Map<String, String> incSrcList = null;

	private CasePartEmp empInfo = null;

	private CasePartNCPExt ncpExtDetails = null;

	private Map<String, CasePartOther> relNamesMap = null;

	private List<Vehicle> vehicleList = null;

	private Map<String, CaseCourtOrder> selectedOrderMap = null;

	private Map<String, String[]> childrenMap = null;

	/**
	 * @return the selectedOrderMap
	 */
	public Map<String, CaseCourtOrder> getSelectedOrderMap() {
		return selectedOrderMap;
	}

	/**
	 * @param selectedOrderMap
	 *            the selectedOrderMap to set
	 */
	public void setSelectedOrderMap(Map<String, CaseCourtOrder> selectedOrderMap) {
		this.selectedOrderMap = selectedOrderMap;
	}

	/**
	 * @return the childrenMap
	 */
	public Map<String, String[]> getChildrenMap() {
		return childrenMap;
	}

	/**
	 * @param childrenMap
	 *            the childrenMap to set
	 */
	public void setChildrenMap(Map<String, String[]> childrenMap) {
		this.childrenMap = childrenMap;
	}

	/**
	 * @return the participant
	 */
	public CaseParticipant getParticipant() {
		return participant;
	}

	/**
	 * @return the ncpExtDetails
	 */
	public CasePartNCPExt getNcpExtDetails() {
		return ncpExtDetails;
	}

	/**
	 * @param ncpExtDetails
	 *            the ncpExtDetails to set
	 */
	public void setNcpExtDetails(CasePartNCPExt ncpExtDetails) {
		this.ncpExtDetails = ncpExtDetails;
	}

	/**
	 * @return the vehicleList
	 */
	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	/**
	 * @return the relNamesMap
	 */
	public Map<String, CasePartOther> getRelNamesMap() {
		return relNamesMap;
	}

	/**
	 * @param relNamesMap
	 *            the relNamesMap to set
	 */
	public void setRelNamesMap(Map<String, CasePartOther> relNamesMap) {
		this.relNamesMap = relNamesMap;
	}

	/**
	 * @param vehicleList
	 *            the vehicleList to set
	 */
	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	/**
	 * @param participant
	 *            the participant to set
	 */
	public void setParticipant(CaseParticipant participant) {
		this.participant = participant;
	}

	/**
	 * @return the addressList
	 */
	public List<CasePartAddress> getAddressList() {
		return addressList;
	}

	/**
	 * @param addressList
	 *            the addressList to set
	 */
	public void setAddressList(List<CasePartAddress> addressList) {
		this.addressList = addressList;
	}

	public void addAddr(CasePartAddress addr) {
		if (getAddressList() == null) {
			addressList = new ArrayList();
			addressList.add(addr);
		} else {
			addressList.add(addr);
		}
	}

	/**
	 * @return the contactList
	 */
	public List<CasePartContact> getContactList() {
		return contactList;
	}

	/**
	 * @param contactList
	 *            the contactList to set
	 */
	public void setContactList(List<CasePartContact> contactList) {
		this.contactList = contactList;
	}

	public void addContact(CasePartContact contact) {
		if (getContactList() == null) {
			contactList = new ArrayList();
			contactList.add(contact);
		} else {
			contactList.add(contact);
		}
	}

	/**
	 * @return the incSrcList
	 */
	public Map<String, String> getIncSrcList() {
		return incSrcList;
	}

	/**
	 * @param incSrcList
	 *            the incSrcList to set
	 */
	public void setIncSrcList(Map<String, String> incSrcList) {
		this.incSrcList = incSrcList;
	}

	/**
	 * @return the empInfo
	 */
	public CasePartEmp getEmpInfo() {
		return empInfo;
	}

	/**
	 * @param empInfo
	 *            the empInfo to set
	 */
	public void setEmpInfo(CasePartEmp empInfo) {
		this.empInfo = empInfo;
	}

}