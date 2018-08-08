/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.WorkFlowDAO;
import nc.dhhs.nccss.acts.ecoa.web.service.WorkFlowService;

/**
 * @author Vijay Peddapalli
 *
 */
public class WorkFlowServiceImpl implements WorkFlowService
{
	protected final Logger	logger	= Logger.getLogger(WorkFlowService.class);

	@Autowired
	private WorkFlowDAO		workFlowDao;

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.WorkFlowService#updateStepNumber(
	 * java.lang.String, java.lang.String, long, java.lang.String)
	 */
	@Transactional
	public String updateStepNumber(String flowId, String flowType, long stepNum, String userId) throws SQLException
	{
		logger.debug("\n********** IN WorkFlowServiceImpl: updateStepNumber(flowId: " + flowId + ", flowType: " + flowType + ", stepNum: " + stepNum + ", userId: " + userId + " )");
		return workFlowDao.updateFlowProgressStep(flowId, flowType, stepNum, userId);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * nc.dhhs.nccss.acts.ecoa.web.service.WorkFlowService#createNewStepNum(
	 * java.lang.String, java.lang.String, long, java.lang.String)
	 */
	@Transactional
	public String createNewStepNum(String flowId, String flowType, long stepNum, String userId) throws SQLException
	{
		logger.debug("\n********** IN WorkFlowServiceImpl: createNewStepNum(flowId: " + flowId + ", flowType: " + flowType + ", stepNum: " + stepNum + ", userId: " + userId + " )");

		String returnCode = workFlowDao.insertFlowProgressStep(flowId, flowType, stepNum, userId);

		logger.info("\n********** createNewStepNum( returncode#: " + returnCode + ")");

		return returnCode;
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.WorkFlowService#getStepNumber(
	 * java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public long getStepNumber(String flowId, String flowType) throws SQLException
	{
		logger.debug("\n********** IN WorkFlowServiceImpl: getStepNumber(flowId: " + flowId + ", flowType: " + flowType + ")");

		long stepNum = workFlowDao.retriveFlowProgressStep(flowId, flowType);

		logger.info("\n********** getStepNumber( STEP#: " + stepNum + ")");
		return stepNum;

	}

}
