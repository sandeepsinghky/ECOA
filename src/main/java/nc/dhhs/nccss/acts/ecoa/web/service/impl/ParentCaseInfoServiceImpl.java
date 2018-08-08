/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.ParentCaseInfoDao;
import nc.dhhs.nccss.acts.ecoa.beans.NameFormat;
import nc.dhhs.nccss.acts.ecoa.beans.ParentCaseInfo;
import nc.dhhs.nccss.acts.ecoa.beans.UserInformation;
import nc.dhhs.nccss.acts.ecoa.web.service.ParentCaseInfoService;

/**
 * @author Mallika Velagapudi
 *
 */
public class ParentCaseInfoServiceImpl implements ParentCaseInfoService
{
	protected final Logger						logger				= Logger.getLogger(CaseParticipantServiceImpl.class);

	@Autowired
	private ParentCaseInfoDao					parentCaseInfoDao;
	private static final Map<String, String>	caseTypeDescMap		= new HashMap<String, String>()
																	{
																		{
																			put("AFDC", "Temporary Assistance to Needy Families");
																			put("ARRF", "IV-E Foster Care arrears only");
																			put("ARRN", "Non-Public Assistance arrears only ");
																			put("ARRP", "Public Assistance arrears only");
																			put("ARRS", "SFHF arrears only");
																			put("INQO", "Inquiry only (Case Initiation) ");
																			put("LOCO", "Locate Only");
																			put("IVE", "Foster Care");
																			put("MAO", "Medical Assistance Only");
																			put("NIVD", "Non-IV-D");
																			put("NPA", "Non-Public Assistance");
																			put("PRIV", "Private child support case");
																			put("SFHF", "State Foster HomeFund");
																			put("TCC", "Transitional Child Care");
																		}
																	};

	private static final Map<String, String>	caseProcStatusMap	= new HashMap<String, String>()
																	{
																		{
																			put("COLL", "Collection");
																			put("DELQ", "Delinquent");
																			put("EST", "Establishment ");
																			put("INIT", "Initiation");
																			put("ARRS", "SFHF arrears only");
																			put("LOCT", "Locate");
																			put("NIVD", "Non IV-D");
																			put("PAT", "Paternity");

																		}
																	};

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.ParentCaseInfoService#getCaseList(nc.
	 * dhhs.nccss.acts.ecoa.beans.UserInformation)
	 */
	@Override
	@Transactional(readOnly = true)
	public ArrayList<ParentCaseInfo> getCaseList(UserInformation userInfoBean) throws Exception
	{

		logger.debug("\n********** IN ParentCaseinfoServiceImpl:getCaseList **********\n");

		ArrayList<ParentCaseInfo> caseList = new ArrayList<ParentCaseInfo>();

		boolean dualRole = userInfoBean.isDualRole();
		String relshp = userInfoBean.getCaseRelshp();
		String mpi = userInfoBean.getMpi();

		SqlRowSet caseSet = parentCaseInfoDao.getCaseList(userInfoBean.getMpi());

		String inDangerous = parentCaseInfoDao.getPartProfile(userInfoBean.getMpi().trim());

		while (caseSet.next())
		{
			boolean addCase = false;
			String caseStatus = caseSet.getString(5).trim();
			String caseType = caseSet.getString(4).trim();
			String closureReason = caseSet.getString(6);
			String partType = caseSet.getString(2).trim();

			if (caseStatus.equals("CLSD"))
			{
				if (caseType.equals("NIVD"))
				{
					if (closureReason.equals("CSCR"))
					{
						addCase = true;
					}
					else if (closureReason.equals("CDEL"))
					{
						addCase = true;
					}
				}
				else
				{
					if (!closureReason.equals("CDUP"))
					{
						addCase = true;
					}
				}
			}
			else
			{
				addCase = true;
			}

			if (addCase)
			{
				if (relshp.equals(partType))
				{
					addCase = true;
				}
				else
				{
					addCase = false;
				}
			}

			if (addCase)
			{
				ParentCaseInfo cb = new ParentCaseInfo();
				cb.setIvdCase(caseSet.getString(1));
				cb.setPartType(caseSet.getString(2));
				cb.setPartStatus(caseSet.getString(3));
				cb.setCaseType(caseType);
				cb.setCaseStatus(caseStatus);
				cb.setClosureReason(closureReason);

				String protectiveOrder = caseSet.getString(7);
				cb.setProtectiveOrder(protectiveOrder.equals("Y") ? true : false);

				String voilenceExists = caseSet.getString(8);
				if ((voilenceExists.equals("UGCA")) || (voilenceExists.equals("UGCP")))
				{
					cb.setVoilenceExists(true);
				}
				else
				{
					if (inDangerous.equals("Y"))
					{
						cb.setVoilenceExists(true);
					}

				}

				caseList.add(cb);
			}
		}
		// Now obtain all the cases from the CaseListBean object and set the properties for other participant.
		for (ParentCaseInfo cb : caseList)
		{

			SqlRowSet rs = parentCaseInfoDao.getSecondParty(mpi, cb.getIvdCase());

			if (rs.next())
			{

				String strOtherMPI = rs.getString(1).trim();
				String name = "";

				int otherMPI = Integer.parseInt(strOtherMPI);

				if (otherMPI < 2000)
				{
					SqlRowSet r = parentCaseInfoDao.getAgencyName(strOtherMPI);
					while (r.next())
					{
						name = r.getString(3);
					}

				}
				else
				{
					SqlRowSet r = parentCaseInfoDao.getParticipantName(strOtherMPI);
					while (r.next())
					{
						String fname = r.getString(3).trim();
						String mname = r.getString(4).trim();
						String lname = r.getString(2).trim();

						NameFormat nf = new NameFormat(lname, fname, mname);
						name = nf.toMixedCase();

					}

					// Check to see if the other participant is possibly
					// dangerous

					String in_Dangerous = parentCaseInfoDao.getPartProfile(strOtherMPI);

					if (in_Dangerous.equals("Y"))
					{
						cb.setVoilenceExists(true);
					}

				}
				cb.setOtherName(name);
				cb.setOtherPartType(rs.getString(2));
			}

			SqlRowSet r = parentCaseInfoDao.getUnderOrder(cb.getIvdCase());

			if (r.next())
			{
				cb.setUnderOrder(true);
			}

		}

		for (ParentCaseInfo cb : caseList)

		{
			ParentCaseInfo case1 = cb;
		}

		return caseList;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.ParentCaseInfoService#getCaseDetail(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public ParentCaseInfo getCaseDetail(String mpi, String ivdCase) throws Exception

	{
		logger.debug("\n********** IN ParentCaseinfoServiceImpl:getCaseDetail **********\n");

		ParentCaseInfo caseBean = new ParentCaseInfo();

		SqlRowSet rs = parentCaseInfoDao.getCaseDetail(mpi, ivdCase);

		while (rs.next())
		{
			caseBean.setIvdCase(rs.getString(1).trim());
			caseBean.setPartType(rs.getString(2).trim());
			caseBean.setPartStatus(rs.getString(3).trim());
			caseBean.setCaseType(rs.getString(4).trim());
			caseBean.setCaseTypeDesc(getCaseTypeDesc(rs.getString(4).trim()));
		}

		rs = parentCaseInfoDao.getSecondPartyQuery(mpi, ivdCase);
		while (rs.next())
		{
			String fname = rs.getString(4).trim();
			String mname = rs.getString(5).trim();
			String lname = rs.getString(3).trim();

			NameFormat nf = new NameFormat(lname, fname, mname);
			String name = nf.toMixedCase();

			caseBean.setOtherName(name);
			caseBean.setOtherPartType(rs.getString(2).trim());
		}

		int childCount = parentCaseInfoDao.getChildren(ivdCase);

		caseBean.setChildCount(childCount);

		rs = parentCaseInfoDao.getWorker(ivdCase);

		while (rs.next())
		{
			String prcsStat = rs.getString(1).trim();
			caseBean.setProcessStatus(prcsStat);
			caseBean.setPrcsStatDesc(getProcessStatDesc(prcsStat));
			caseBean.setWrkrName(rs.getString(3).trim() + " " + rs.getString(5).trim() + " " + rs.getString(4).trim());
			caseBean.setWrkrAddr1(rs.getString(6).trim());
			caseBean.setWrkrAddr2(rs.getString(7).trim());
			caseBean.setWrkrCity(rs.getString(8).trim());
			caseBean.setWrkrState(rs.getString(9).trim());
			caseBean.setWrkrZip(rs.getString(10).trim());
			caseBean.setWrkrPhone(rs.getString(12).trim() + " " + rs.getString(13).trim() + " " + rs.getString(14).trim());
			caseBean.setWrkrEmail(rs.getString(15).trim());
			caseBean.setWrkrPhoneExt(rs.getString(17).trim());
		}

		rs = parentCaseInfoDao.getUnderOrder(ivdCase);

		if (rs.next())
		{
			caseBean.setUnderOrder(true);
		}

		return caseBean;
	}

	private String getProcessStatDesc(String type)
	{
		if (caseProcStatusMap.containsKey(type)) return caseProcStatusMap.get(type);
		return "Cannot Find Process Status Desc";
	}

	private String getCaseTypeDesc(String type)
	{
		if (caseTypeDescMap.containsKey(type)) return caseTypeDescMap.get(type);

		return "Cannot Find Case Type Desc";
	}

}
