/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.UserSignatureDAO;
import nc.dhhs.nccss.acts.ecoa.beans.UserSignature;
import nc.dhhs.nccss.acts.ecoa.web.service.UserSignatureService;

/**
 * @author Vijay Peddapalli
 *
 */
public class UserSignatureServiceImpl implements UserSignatureService
{

	protected final Logger		logger	= Logger.getLogger(UserSignatureServiceImpl.class);

	@Autowired
	private UserSignatureDAO	userSignatureDAO;

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.UserSignatureService#
	 * getCaseApplicationSignature(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<UserSignature> getCaseApplicationSignature(String caseAppId, String signType) throws Exception
	{
		// TODO Auto-generated method stub
		logger.debug("\n********** IN UserSignatureServiceImpl: getCaseApplicationSignature( caseAppId: " + caseAppId + "signType: " + signType + ")");

		return userSignatureDAO.retrieveSignature(caseAppId, signType);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.UserSignatureService#
	 * getCaseApplicationAllSignatures(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public List<UserSignature> getCaseApplicationAllSignatures(String caseAppId) throws Exception
	{
		logger.debug("\n********** IN UserSignatureServiceImpl: getCaseApplicationAllSignatures( caseAppId: " + caseAppId + ")");

		return userSignatureDAO.retrieveAllSignatures(caseAppId);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.UserSignatureService#
	 * insertOrUpdateSignature(nc.dhhs.nccss.acts.ecoa.beans.UserSignature,
	 * java.lang.String, java.lang.String)
	 */
	@Transactional
	public String insertOrUpdateSignature(UserSignature signBean, String userId, String dbOpType) throws SQLException
	{
		logger.debug("\n********** IN CaseApplicationServiceImpl: insertOrUpdateSignature( signBean , userId: " + userId + " dbOpType: " + dbOpType + ")");

		String returnCode = userSignatureDAO.insertOrUpdateSignature(signBean, userId, dbOpType).trim();

		logger.info("\n********** IN CaseApplicationServiceImpl: insertOrUpdateSignature( returncode: " + returnCode + ")**********\n");

		return returnCode;
	}

}
