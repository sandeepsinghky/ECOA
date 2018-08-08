/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.AlertDAO;
import nc.dhhs.nccss.acts.ecoa.beans.Alert;
import nc.dhhs.nccss.acts.ecoa.web.service.AlertService;

/**
 * @author Vijay Peddapalli
 *
 */
public class AlertServiceImpl implements AlertService
{
	@Autowired
	private AlertDAO		alertDao;

	protected final Logger	logger	= Logger.getLogger(AlertServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.AlertService#getCSSNewsAlert()
	 */
	@Transactional(readOnly = true)
	public List<Alert> retrieveCSSAnnouncements() throws SQLException
	{
		logger.debug("\n********** IN AlertServiceImpl: retrieveCSSAnnouncements() **********\n");

		return alertDao.retrieveMessages();

	}

}
