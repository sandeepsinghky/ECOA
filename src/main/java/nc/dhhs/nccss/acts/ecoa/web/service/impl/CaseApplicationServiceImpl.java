package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.CaseApplicationDao;
import nc.dhhs.nccss.acts.dao.UserSignatureDAO;
import nc.dhhs.nccss.acts.ecoa.beans.CaseApplication;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseApplicationService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/*
 * @author Phani Konuru
 */

public class CaseApplicationServiceImpl implements CaseApplicationService
{

	protected final Logger		logger	= Logger.getLogger(CaseApplicationServiceImpl.class);

	@Autowired
	private CaseApplicationDao	caseApplDao;

	@Autowired
	private UserSignatureDAO	userSignatureDAO;

	@Transactional
	public String CreateCaseApplication(CaseApplication applBean) throws SQLException
	{

		logger.debug("\n********** IN CaseApplicationServiceImpl: CreateCaseApplication(CaseApplication) **********\n");

		applBean.setApplStatus(AppConstants.APP_STATUS_ACTIVE);
		applBean.setApplCreateDt(new Date());
		return caseApplDao.createCaseApplication(applBean);
	}

	@Transactional(readOnly = true)
	public List<CaseApplication> getCaseApplicationByFieldName(String field, String value) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationServiceImpl: getCaseApplicationByFieldName(field" + field + "," + value + ") **********\n");

		return caseApplDao.getCaseApplicationByField(field, value);
	}

	@Transactional(readOnly = true)
	public List<CaseApplication> getCaseApplicationByAppId(String applId) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationServiceImpl: getCaseApplicationByAppId(applId: " + applId + ")");

		return caseApplDao.getCaseApplicationByAppId(applId);
	}

	@Transactional
	public String deleteCaseApplication(String applId, String ncId) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationServiceImpl: deleteCaseApplication(applId:" + applId + ",ncId: " + ncId + ")");
		String rValue = "";

		CaseApplication applBean = new CaseApplication();

		if ((applId != null && applId != "") && (ncId != null && ncId != ""))
		{
			applBean.setApplicationId(applId);
			applBean.setNcId(ncId);

			rValue = caseApplDao.deleteCaseApplication(applBean);
		}
		return rValue;
	}

	@Transactional
	public String updateCaseApplicationRNR(CaseApplication applBean) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationServiceImpl: updateCaseApplicationRNR(CaseApplication)");

		CaseApplication appl = getCaseApplicationByAppId(applBean.getApplicationId()).get(0);

		if (applBean.getFirstName() != null && !applBean.getFirstName().equals(""))
		{
			appl.setFirstName(applBean.getFirstName());
		}
		if (applBean.getLastName() != null && !applBean.getLastName().equals(""))
		{
			appl.setLastName(applBean.getLastName());
		}
		if (applBean.getMiddleName() != null && !applBean.getMiddleName().equals(""))
		{
			appl.setMiddleName(applBean.getMiddleName());
		}
		return caseApplDao.updateCaseApplication(appl);

	}

	@Transactional
	public String updateCaseApplicationNotes(CaseApplication applBean) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationServiceImpl: updateCaseApplicationNotes(CaseApplication)");

		CaseApplication appl = getCaseApplicationByAppId(applBean.getApplicationId()).get(0);

		/*
		 * if (applBean.getApplNotes() != null &&
		 * applBean.getApplNotes().equals("")) { if (appl.getApplNotes() != null
		 * && appl.getApplNotes().equals("")) { return; } }
		 */

		if (applBean.getApplNotes() != null && applBean.getApplNotes() != "")
		{
			logger.info("Updating info for AppId: " + applBean.getApplicationId());

			appl.setApplNotes(applBean.getApplNotes());
		}

		return caseApplDao.updateCaseApplication(appl);
	}

	@Transactional
	public String updateCaseApplicationSubmit(CaseApplication applBean) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationServiceImpl: updateCaseApplicationSubmit(CaseApplication)");

		CaseApplication appl = getCaseApplicationByAppId(applBean.getApplicationId()).get(0);
		appl.setApplSubmitDt(new Date());
		appl.setApplStatus(AppConstants.APP_STATUS_SUBMIT);

		return caseApplDao.updateCaseApplication(appl);
	}

}
