package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.ecoa.web.service.FeedbackService;
import nc.dhhs.nccss.acts.dao.FeedBackDao;
import nc.dhhs.nccss.acts.ecoa.beans.TaskFormBean;
import nc.dhhs.nccss.acts.ecoa.beans.TaskNotes;
import nc.dhhs.nccss.acts.ecoa.beans.TaskBean;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author pkonuru
 *
 */

public class FeedbackServiceImpl implements FeedbackService
{
	protected final Logger			logger	= Logger.getLogger(FeedbackServiceImpl.class);
	
	@Autowired
	private FeedBackDao		feedBackDao;


	public int getCSSCtrlNbr() throws SQLException
	{
		return feedBackDao.getCSSCtrlNbr();
	}
	
	@Transactional
	public int createFeedback(TaskFormBean tskFrmBean)  throws SQLException
	{
		int result = 1;
		int referenceId = getCSSCtrlNbr();
		if(referenceId !=0)
		{
			//insert task notes
			TaskNotes notes = new TaskNotes();
			java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
			
			Calendar now = Calendar.getInstance();
			int month = now.get(Calendar.MONTH) + 1;
			int year = now.get(Calendar.YEAR);

			BigDecimal idNotes = new BigDecimal(ts.getTime());
			notes.setIdNotes(idNotes);
			notes.setLastUpdatDt(new java.sql.Date(ts.getTime()));
			notes.setLastUpdateTm(new java.sql.Time(ts.getTime()));
			notes.setIdWrkrLst("ACTS9999");
			notes.setIdLstUpd("ACTS9999");
			notes.setNotesTxt(tskFrmBean.getNtResolution());
			result = feedBackDao.insertCSENotes(notes);
			if (result != 1 )
				throw new RuntimeException("createFeedback: insert into csesrv notes failed, Rollback this transaction!");
			
			//insert init task
			TaskBean taskInit = new TaskBean();
			taskInit.setIdReference(referenceId);
			taskInit.setTsCreate(ts);
			taskInit.setCdType("INIT");
			taskInit.setIdWrkrAssign("");
			taskInit.setTsAssign(ts);
			taskInit.setTsEnd(ts);
			taskInit.setIdNotes(idNotes);
			taskInit.setIdWrkrCreate("ACTS9999");
			taskInit.setTsUpdate(ts);
			taskInit.setIdWrkrUpdate("ACTS9999");
			result = feedBackDao.insertCSETrk(taskInit);
			if (result != 1 )
				throw new RuntimeException("createFeedback: insert into csesrv frmtrk, init record failed, Rollback this transaction!");
			
			/**
			 * Obtain the latest time
			 */
			ts = new java.sql.Timestamp(System.currentTimeMillis());
			System.out.println("Task Creation Timestamp: " + ts);

			java.sql.Timestamp tsAssigned = getAssignedTimeStamp(ts);
			System.out.println("Task Assignment Timestamp: " + tsAssigned);
			
			//insert into task form
			tskFrmBean.setIdReference(referenceId);
			tskFrmBean.setTsCreate(ts);
			tskFrmBean.setTsUpdate(ts);
			tskFrmBean.setCreateMo(month);
			tskFrmBean.setCreateYr(year);
			tskFrmBean.setTsAssigned(tsAssigned);
			result = feedBackDao.insertCSEForm(tskFrmBean);
			if (result != 1 )
				throw new RuntimeException("createFeedback: insert into csesrv form failed, Rollback this transaction!");
			
			//insert open task
			TaskBean taskOpen = new TaskBean();
			taskOpen.setIdReference(referenceId);
			taskOpen.setTsCreate(ts);
			taskOpen.setCdType("OPEN");
			taskOpen.setIdWrkrAssign("");
			taskOpen.setTsAssign(ts);
			taskOpen.setTsEnd(new java.sql.Timestamp(0));
			taskOpen.setIdNotes(idNotes);
			taskOpen.setIdWrkrCreate("ACTS9999");
			taskOpen.setTsUpdate(ts);
			taskOpen.setIdWrkrUpdate("ACTS9999");
			result = feedBackDao.insertCSETrk(taskOpen);
			if (result != 1 )
				throw new RuntimeException("createFeedback: insert into csesrv frmtrk, open record failed, Rollback this transaction!");			
		}
		
		//SP to assign worker for the task
		result = feedBackDao.updateTaskAssignedWorker(referenceId, tskFrmBean.getNmCustomerLast(), tskFrmBean.getIdEmail());
		if (result != 1 )
			throw new RuntimeException("createFeedback: insert into csesrv frmtrk, open record failed, Rollback this transaction!");			
		
		return referenceId != 0 ? referenceId : 0;
	}
	
	private java.sql.Timestamp getAssignedTimeStamp(java.sql.Timestamp ts) {

		java.util.Date dt = new java.util.Date(ts.getTime());
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.getTime();

		int DAY_OF_WEEK = rightNow.get(Calendar.DAY_OF_WEEK);
		int HOUR_OF_DAY = rightNow.get(Calendar.HOUR_OF_DAY);
		int MINUTE_OF_HOUR = rightNow.get(Calendar.MINUTE);

		switch (DAY_OF_WEEK) {
			case Calendar.SATURDAY :
				rightNow.add(Calendar.DAY_OF_MONTH, 2);
				rightNow.set(Calendar.HOUR_OF_DAY, 7);
				rightNow.set(Calendar.MINUTE, 0);
				rightNow.set(Calendar.SECOND, 0);
				java.util.Date now = rightNow.getTime();
				ts = new java.sql.Timestamp(now.getTime());
				return ts;
			case Calendar.SUNDAY :
				rightNow.add(Calendar.DAY_OF_MONTH, 1);
				rightNow.set(Calendar.HOUR_OF_DAY, 7);
				rightNow.set(Calendar.MINUTE, 0);
				rightNow.set(Calendar.SECOND, 0);
				now = rightNow.getTime();
				ts = new java.sql.Timestamp(now.getTime());
				return ts;
			default :
				break;
		}

		//		if (HOUR_OF_DAY >= 17 && HOUR_OF_DAY <= 24) {
		if ((HOUR_OF_DAY > 16 || (HOUR_OF_DAY == 16 && MINUTE_OF_HOUR > 30)) && HOUR_OF_DAY <= 24) {
			switch (DAY_OF_WEEK) {
				case Calendar.MONDAY :
				case Calendar.TUESDAY :
				case Calendar.WEDNESDAY :
				case Calendar.THURSDAY :
					rightNow.add(Calendar.DAY_OF_MONTH, 1);
					rightNow.set(Calendar.HOUR_OF_DAY, 7);
					rightNow.set(Calendar.MINUTE, 0);
					rightNow.set(Calendar.SECOND, 0);
					java.util.Date now = rightNow.getTime();
					ts = new java.sql.Timestamp(now.getTime());
					return ts;
				case Calendar.FRIDAY :
					rightNow.add(Calendar.DAY_OF_MONTH, 3);
					rightNow.set(Calendar.HOUR_OF_DAY, 7);
					rightNow.set(Calendar.MINUTE, 0);
					rightNow.set(Calendar.SECOND, 0);
					now = rightNow.getTime();
					ts = new java.sql.Timestamp(now.getTime());
					return ts;
				default :
					break;
			}
		}

		if (HOUR_OF_DAY >= 1 && HOUR_OF_DAY < 7) {
			switch (DAY_OF_WEEK) {
				case Calendar.MONDAY :
				case Calendar.TUESDAY :
				case Calendar.WEDNESDAY :
				case Calendar.THURSDAY :
				case Calendar.FRIDAY :
					rightNow.set(Calendar.HOUR_OF_DAY, 7);
					rightNow.set(Calendar.MINUTE, 0);
					rightNow.set(Calendar.SECOND, 0);
					java.util.Date now = rightNow.getTime();
					ts = new java.sql.Timestamp(now.getTime());
					return ts;
				default :
					break;
			}
		}

		return ts;
	}

}
