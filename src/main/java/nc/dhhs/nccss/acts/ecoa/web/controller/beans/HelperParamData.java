package nc.dhhs.nccss.acts.ecoa.web.controller.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nc.dhhs.nccss.acts.ecoa.beans.CasePartAddress;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartContact;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartEmp;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartOther;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartPaternty;
import nc.dhhs.nccss.acts.ecoa.beans.CasePartProfileExt;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Phani Konuru
 * 
 */

public class HelperParamData implements Serializable
{
	public void populateAddress(ParamData bean, Map paramList, String addrType, String addrCode, String partPage)
	{
		boolean data_entered = false;
		CasePartAddress addrBean = new CasePartAddress();
		//get mailing address
		if (paramList.get(addrCode + "_street") != null && !((String) paramList.get(addrCode + "_street")).equals(""))
		{
			addrBean.setAddrLn1((String) paramList.get(addrCode + "_street"));
			data_entered = true;
		}
		if (paramList.get(addrCode + "_city") != null && !((String) paramList.get(addrCode + "_city")).equals(""))
		{
			addrBean.setAddrCty((String) paramList.get(addrCode + "_city"));
			data_entered = true;
		}
		if (paramList.get(addrCode + "_zip") != null && !((String) paramList.get(addrCode + "_zip")).equals(""))
		{
			addrBean.setAddrZip5((String) paramList.get(addrCode + "_zip"));
			data_entered = true;
		}
		if (paramList.get(addrCode + "_state") != null && paramList.get(addrCode + "_state") != "")
		{
			addrBean.setAddrSt((String) paramList.get(addrCode + "_state"));
			data_entered = true;
		}

		if (data_entered)
		{
			addrBean.setAddrType(addrType);

			if (partPage.equals(AppConstants.CP_PARTICIPANT_TYPE))
			{
				((CPparamData) bean).addAddr(addrBean);
			}
			else if (partPage.equals(AppConstants.NCP_PARTICIPANT_TYPE))
			{
				((NCPparamData) bean).addAddr(addrBean);
			}
			else if (partPage.equals(AppConstants.CHLD_PARTICIPANT_TYPE))
			{
				((ChldparamData) bean).addAddr(addrBean);
			}
		}

	}

	public void populateContact(ParamData bean, Map paramList, String contactTypeDesc, String contactType,
			String partPage)
	{
		boolean data_entered = false;
		CasePartContact contactBean = new CasePartContact();
		if (!contactType.equals(AppConstants.CONTACT_TYPE_EMAIL))
		{
			if (paramList.get("phone_" + contactTypeDesc + "_val") != null && paramList.get("phone_" + contactTypeDesc + "_val").equals(AppConstants.CONDITION_TRUE))
			{
				contactBean.setContactInfo((String) paramList.get(contactTypeDesc + "_ph1") + (String) paramList.get(contactTypeDesc + "_ph2") + (String) paramList.get(contactTypeDesc + "_ph3"));
				data_entered = true;
			}
		}
		else
		{
			if (paramList.get("email") != null && !paramList.get("email").equals(""))
			{
				contactBean.setContactInfo((String) paramList.get("email"));
				data_entered = true;
			}
		}

		if (data_entered)
		{
			contactBean.setContactType(contactType);

			if (partPage.equals(AppConstants.CP_PARTICIPANT_TYPE))
			{
				((CPparamData) bean).addContact(contactBean);
			}
			else if (partPage.equals(AppConstants.NCP_PARTICIPANT_TYPE))
			{
				((NCPparamData) bean).addContact(contactBean);
			}
			else if (partPage.equals(AppConstants.CHLD_PARTICIPANT_TYPE))
			{
				((ChldparamData) bean).addContact(contactBean);
			}

		}

	}

	public void populateIncomeSrc(ParamData bean, Map paramList, String partPage)
	{
		boolean data_entered = false;

		double totalAmt = 0;

		String incomeTypeList = ((String) paramList.get("incomeTypeLst")).trim();

		Map<String, String> selectedIncSrc = new HashMap();

		if (!incomeTypeList.trim().equals(""))
		{
			String[] typeList = incomeTypeList.split(",");
			for (int i = 0; i < typeList.length; i++)
			{
				String incType = typeList[i];
				String amt = (String) paramList.get("income_val_" + incType);
				if (amt != null && !amt.equals(""))
				{
					totalAmt = totalAmt + Double.parseDouble(amt);
				}
				selectedIncSrc.put(incType, amt);
			}
		}

		if (selectedIncSrc.size() > 0)
		{
			if (partPage.equals(AppConstants.CP_PARTICIPANT_TYPE))
			{
				((CPparamData) bean).setIncSrcList(selectedIncSrc);
				((CPparamData) bean).getParticipant().setIncome(totalAmt);
			}
			else if (partPage.equals(AppConstants.NCP_PARTICIPANT_TYPE))
			{
				((NCPparamData) bean).setIncSrcList(selectedIncSrc);
				((NCPparamData) bean).getParticipant().setIncome(totalAmt);
			}
		}

	}

	public void populateCurrEmpInfo(ParamData bean, Map paramList, String partPage)
	{
		CasePartEmp currEmpInfo = new CasePartEmp();
		boolean data_entered = false;
		if (paramList.get("curr_emp") != null && paramList.get("curr_emp") != "")
		{
			currEmpInfo.getThirdPartyInfo().setThirdPartyNm((String) paramList.get("curr_emp"));
			data_entered = true;
		}
		if (paramList.get("emp_street") != null && paramList.get("emp_street") != "")
		{
			currEmpInfo.getThirdPartyInfo().setAddressLn1((String) paramList.get("emp_street"));
			data_entered = true;
		}
		if (paramList.get("emp_city") != null && paramList.get("emp_city") != "")
		{
			currEmpInfo.getThirdPartyInfo().setThirdPartyCity((String) paramList.get("emp_city"));
			data_entered = true;
		}
		if (paramList.get("emp_state") != null && paramList.get("emp_state") != "")
		{
			currEmpInfo.getThirdPartyInfo().setThirdPartyState((String) paramList.get("emp_state"));
			data_entered = true;
		}
		if (paramList.get("emp_zip") != null && paramList.get("emp_zip") != "")
		{
			currEmpInfo.getThirdPartyInfo().setThirdPartyZip5((String) paramList.get("emp_zip"));
			data_entered = true;
		}

		if (paramList.get("emp_ph1") != null && paramList.get("emp_ph1") != "")
		{
			currEmpInfo.getThirdPartyInfo().setPhoneAreaCode((String) paramList.get("emp_ph1"));
			data_entered = true;
		}

		if (paramList.get("emp_ph2") != null && paramList.get("emp_ph2") != "")
		{
			currEmpInfo.getThirdPartyInfo().setPhoneExc((String) paramList.get("emp_ph2"));
			data_entered = true;
		}

		if (paramList.get("emp_ph3") != null && paramList.get("emp_ph3") != "")
		{
			currEmpInfo.getThirdPartyInfo().setPhoneLn((String) paramList.get("emp_ph3"));
			data_entered = true;
		}

		if (data_entered)
		{
			if (partPage.equals(AppConstants.CP_PARTICIPANT_TYPE))
			{
				currEmpInfo.getThirdPartyInfo().setThirdPartyType("2");
				((CPparamData) bean).setEmpInfo(currEmpInfo);
			}
			else if (partPage.equals(AppConstants.NCP_PARTICIPANT_TYPE))
			{
				currEmpInfo.getThirdPartyInfo().setThirdPartyType("4");
				((NCPparamData) bean).setEmpInfo(currEmpInfo);
			}
		}

	}

	public void populatePrevEmpInfo(ParamData bean, Map paramList, String partPage)
	{
		CasePartEmp prevEmpInfo = new CasePartEmp();
		boolean data_entered = false;
		if (paramList.get("last_emp") != null && paramList.get("last_emp") != "")
		{
			prevEmpInfo.getThirdPartyInfo().setThirdPartyNm(((String) paramList.get("last_emp")).trim());
			data_entered = true;
		}
		if (paramList.get("dt_emp_end") != null && paramList.get("dt_emp_end") != "")
		{
			prevEmpInfo.setEmplmEndDt(((String) paramList.get("dt_emp_end")).trim());
			data_entered = true;
		}
		if (paramList.get("end_reason") != null && paramList.get("end_reason") != "")
		{
			prevEmpInfo.setEmplmEndReasn(((String) paramList.get("end_reason")).trim());
			data_entered = true;
		}
		if (paramList.get("occupation") != null && paramList.get("occupation") != "")
		{
			prevEmpInfo.setOcupation(((String) paramList.get("occupation")).trim());
			data_entered = true;
		}

		if (data_entered)
		{
			if (partPage.equals(AppConstants.CP_PARTICIPANT_TYPE))
			{
				prevEmpInfo.getThirdPartyInfo().setThirdPartyType("3");
				((CPparamData) bean).setEmpInfo(prevEmpInfo);
			}
			else if (partPage.equals(AppConstants.NCP_PARTICIPANT_TYPE))
			{
				prevEmpInfo.getThirdPartyInfo().setThirdPartyType("5");
				((NCPparamData) bean).setEmpInfo(prevEmpInfo);
			}
		}
	}

	public void populatePartOther(ParamData bean, Map paramList, String OtherDesc, String OtherCode, String partPage)
	{
		CasePartOther partOther = new CasePartOther();
		boolean data_entered = false;

		if (paramList.get(OtherDesc + "fname") != null && !((String) paramList.get(OtherDesc + "fname")).equals(""))
		{
			partOther.setFirstNm((String) paramList.get(OtherDesc + "fname"));
			data_entered = true;
		}
		if (paramList.get(OtherDesc + "mname") != null && !((String) paramList.get(OtherDesc + "mname")).equals(""))
		{
			partOther.setMiddleNm((String) paramList.get(OtherDesc + "mname"));
			data_entered = true;
		}
		if (paramList.get(OtherDesc + "lname") != null && !((String) paramList.get(OtherDesc + "lname")).equals(""))
		{
			partOther.setLastNm((String) paramList.get(OtherDesc + "lname"));
			data_entered = true;
		}
		if (data_entered)
		{
			if (partPage.equals(AppConstants.CHLD_PARTICIPANT_TYPE))
			{
				partOther.setRelationship(OtherCode);
				if (((ChldparamData) bean).getPartOtherList() != null)
				{
					((ChldparamData) bean).getPartOtherList().put(OtherCode, partOther);
				}
				else
				{
					Map<String, CasePartOther> newPartOther = new HashMap();
					newPartOther.put(OtherCode, partOther);
					((ChldparamData) bean).setPartOtherList(newPartOther);
				}
			}
		}
	}

	public void populatePaterntyInfo(ParamData bean, Map paramList)
	{
		CasePartPaternty paterntyInfo = new CasePartPaternty();
		boolean data_entered = false;
		
		if(paramList.get("paternityTst") != null){
			((ChldparamData) bean).getPartProfile().setPatnTst((String)paramList.get("paternityTst"));
		}

		if (paramList.get("paternityTst") != null && ((String) paramList.get("paternityTst")).equalsIgnoreCase(AppConstants.SELOPTION_YES))
		{
			if (paramList.get("paterntyTstDt") != null && paramList.get("paterntyTstDt") != "")
			{
				paterntyInfo.setPatnTestDt((String) paramList.get("paterntyTstDt"));
				data_entered = true;
			}
			if (paramList.get("paterntyRes") != null && paramList.get("paterntyRes") != "")
			{
				paterntyInfo.setPatnResult((String) paramList.get("paterntyRes"));
				data_entered = true;
			}
		}
		
		if(paramList.get("paterntyEst") != null){
			((ChldparamData) bean).getPartProfile().setPatnEst((String)paramList.get("paterntyEst"));
		}

		if (paramList.get("paterntyEst") != null && ((String) paramList.get("paterntyEst")).equalsIgnoreCase(AppConstants.SELOPTION_YES))
		{
			if (paramList.get("paterntyCdEst") != null && paramList.get("paterntyCdEst") != "")
			{
				paterntyInfo.setPatnEst((String) paramList.get("paterntyCdEst"));
				data_entered = true;
			}
			if (paramList.get("paterntyEstDt") != null && paramList.get("paterntyEstDt") != "")
			{
				paterntyInfo.setPatnEstDt((String) paramList.get("paterntyEstDt"));
				data_entered = true;
			}
			if (paramList.get("paterntyCnty") != null && paramList.get("paterntyCnty") != "")
			{
				paterntyInfo.setPatnEstCnty((String) paramList.get("paterntyCnty"));
				data_entered = true;
			}
			if (paramList.get("paterntySt") != null && paramList.get("paterntySt") != "")
			{
				paterntyInfo.setPatnEstSt((String) paramList.get("paterntySt"));
				data_entered = true;
			}
		}

		if (data_entered)
		{
			((ChldparamData) bean).setPartPaternity(paterntyInfo);
		}

	}

	public void populatePartProfileExt(ParamData bean, Map paramList, String partProfileExtDesc,
			String partProfileExtCode)
	{
		CasePartProfileExt partProfileExt = new CasePartProfileExt();
		boolean data_entered = false;

		if (paramList.get(partProfileExtDesc + "Cty") != null && !((String) paramList.get(partProfileExtDesc + "Cty")).equals(""))
		{
			partProfileExt.setProfCty((String) paramList.get(partProfileExtDesc + "Cty"));
			data_entered = true;
		}
		if (paramList.get(partProfileExtDesc + "Cnty") != null && !((String) paramList.get(partProfileExtDesc + "Cnty")).equals(""))
		{
			partProfileExt.setProfCnty((String) paramList.get(partProfileExtDesc + "Cnty"));
			data_entered = true;
		}
		if (paramList.get(partProfileExtDesc + "St") != null && !((String) paramList.get(partProfileExtDesc + "St")).equals(""))
		{
			partProfileExt.setProfSt((String) paramList.get(partProfileExtDesc + "St"));
			data_entered = true;
		}
		if (paramList.get(partProfileExtDesc + "Ctry") != null && !((String) paramList.get(partProfileExtDesc + "Ctry")).equals(""))
		{
			partProfileExt.setProfCntry((String) paramList.get(partProfileExtDesc + "Ctry"));
			data_entered = true;
		}

		if (data_entered)
		{

			partProfileExt.setProfileExtType(partProfileExtCode);
			if (((ChldparamData) bean).getPartProfileExtList() != null)
			{
				((ChldparamData) bean).getPartProfileExtList().put(partProfileExtCode, partProfileExt);
			}
			else
			{
				Map<String, CasePartProfileExt> newPartProfileExt = new HashMap();
				newPartProfileExt.put(partProfileExtCode, partProfileExt);
				((ChldparamData) bean).setPartProfileExtList(newPartProfileExt);
			}
		}
	}

}