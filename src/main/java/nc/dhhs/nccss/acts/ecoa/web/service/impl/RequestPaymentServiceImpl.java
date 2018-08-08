/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.RequestPaymentDao;
import nc.dhhs.nccss.acts.ecoa.beans.NameFormat;
import nc.dhhs.nccss.acts.ecoa.beans.UserInformation;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.service.RequestPaymentService;
import nc.dhhs.nccss.acts.ecoa.web.util.AppConstants;

/**
 * @author Mallika Velagapudi
 *
 */
public class RequestPaymentServiceImpl implements RequestPaymentService {
	protected final Logger logger = Logger.getLogger(RequestPaymentServiceImpl.class);

	@Autowired
	private RequestPaymentDao requestDao;

	@Override
	@Transactional(readOnly = true)
	public StringBuffer buildCPPayments(UserInformation userInfoBean) throws Exception

	{
		logger.debug("\n********** IN RequestPaymentServiceImpl:buildCPPayments **********\n");

		String mpi = userInfoBean.getMpi();

		Calendar c = Calendar.getInstance();

		java.util.Date now = c.getTime();

		c.add(Calendar.MONTH, -13);

		java.util.Date start = c.getTime();

		java.sql.Date endDate = new java.sql.Date(now.getTime());
		java.sql.Date startDate = new java.sql.Date(start.getTime());
		StringBuffer buf = new StringBuffer();

		buf.append("NOTE: PLEASE DO NOT RESPOND DIRECTLY TO THIS E-MAIL MESSAGE. THIS ADDRESS IS NOT MONITORED.\n\n");

		buf.append("This message has been sent in response to your request for a thirteen month "
				+ "payment history. If you did not request this service or believe this "
				+ "message has been sent to you in error, please contact " + WebsiteConfiguration.getPrimaryEmail()
				+ ".\n\n" + "Below is your payment history. \n\n"
				+ "NORTH CAROLINA DEPARTMENT OF HEALTH AND HUMAN SERVICES       " + formatDate(endDate.toString())
				+ "\n\n" + "Custodial Parent MPI # " + userInfoBean.getMpi() + "\n\n");

		SqlRowSet r = requestDao.getCallCenterAddress("3700000CCT", "AGCY");

		while (r.next()) {
			String addrLine1 = r.getString(4);
			String addrLine2 = r.getString(5);
			String city = r.getString(6);
			String state = r.getString(7);
			String zip5 = r.getString(8);
			String zip4 = r.getString(9);
			String phAcd = r.getString(11);
			String phExc = r.getString(12);
			String phLn = r.getString(13);

			buf.append(addrLine1 + "\n");
			buf.append(addrLine2 + "\n");
			buf.append(city + " " + state + " " + zip5 + "-" + zip4 + "\n");
			buf.append("(" + phAcd + ")" + phExc + "-" + phLn + "\n\n\n");
		}

		SqlRowSet s = requestDao.getPartInfo(userInfoBean.getMpi());

		while (s.next()) {
			String fname = s.getString(3).trim();
			String mname = s.getString(4).trim();
			String lname = s.getString(2).trim();

			NameFormat nf = new NameFormat(lname, fname, mname);
			String name = nf.toMixedCase();

			buf.append("Dear " + name + ":\n\n");
		}

		buf.append("This notice provides a listing of child support paid to you. " + "It includes payments made from "
				+ formatDate(startDate.toString()) + " through " + formatDate(endDate.toString()) + ".\n\n");

		buf.append("\nCASE SUMMARY:\n\n");

		// Build the header for Case Summary
		StringBuffer strBuffer = new StringBuffer(100);

		for (int i = 0; i < 100; i++) {
			strBuffer.insert(i, " ");
		}

		strBuffer.insert(1, "Case Number");
		strBuffer.insert(21, "Child(ren)");
		strBuffer.insert(56, "Non-Custodial Parent");

		// Right Justify the String
		String strTrim = strBuffer.toString();
		strTrim = ("a" + strTrim).trim().substring(1);
		buf.append(strTrim + "\n");

		buf.append(
				"____________________________________________________________________________________________________\n");

		String strChildName = "";

		r = requestDao.getCases(mpi, "CLI");

		// For each case, get the Children
		while (r.next()) {

			String ivdCase = r.getString(1);

			// Get the Custodial Parent on the Case

			SqlRowSet rs3 = requestDao.getParent(ivdCase, "AP");

			String strMPI = "";
			String strCustodial = "0";

			while (rs3.next()) {
				strMPI = rs3.getString(1);
			}

			int iMPI = Integer.parseInt(strMPI);

			if (iMPI < 2000) {

				rs3 = requestDao.getSystemAccount(strMPI, "SYST");

				while (rs3.next()) {
					strCustodial = rs3.getString(1);
				}

			} else {

				rs3 = requestDao.getParticipant(strMPI);

				while (rs3.next()) {
					String lname = rs3.getString(1);
					String fname = rs3.getString(2);
					String mname = rs3.getString(3);

					NameFormat nf = new NameFormat(lname, fname, mname);
					String name = nf.toMixedCase();
					strCustodial = name;
				}

			}

			SqlRowSet rs2 = requestDao.getChildren(ivdCase);

			while (rs2.next()) {
				String fname = rs2.getString(2).trim();
				String mname = rs2.getString(3).trim();
				String lname = rs2.getString(1).trim();

				NameFormat nf = new NameFormat(lname, fname, mname);
				strChildName = nf.toMixedCase();

				strBuffer = new StringBuffer(100);

				for (int i = 0; i < 100; i++) {
					strBuffer.insert(i, " ");
				}

				strBuffer.insert(1, ivdCase);
				strBuffer.insert(21, strChildName);
				strBuffer.insert(56, strCustodial);

				// Right Justify the String
				strTrim = strBuffer.toString();
				strTrim = ("a" + strTrim).trim().substring(1);
				buf.append(strTrim + "\n");

			}
		}

		buf.append("\n\nPAYMENT SUMMARY:\n\n");

		strBuffer = new StringBuffer(100);

		for (int i = 0; i < 100; i++) {
			strBuffer.insert(i, " ");
		}

		strBuffer.insert(1, "Date");
		strBuffer.insert(20, "Check Number");
		strBuffer.insert(34, "Amount Paid");
		strBuffer.insert(55, "Case Credited");

		// Right Justify the String
		strTrim = strBuffer.toString();
		strTrim = ("a" + strTrim).trim().substring(1);
		buf.append(strTrim + "\n");

		buf.append(
				"____________________________________________________________________________________________________\n");

		double chkTotal = 0.0D;

		r = requestDao.getCPPayments(mpi, startDate, endDate);

		while (r.next()) {
			strBuffer = new StringBuffer(100);
			for (int i = 0; i < 100; i++) {
				strBuffer.insert(i, " ");
			}

			String chkNbr = r.getString(1).trim();
			String chkDate = r.getDate(2).toString();

			if (!chkDate.equals(AppConstants.DEFAULT_DATE)) {

				if (chkNbr.equals("")) {
					chkNbr = "EFT PAYMENT";
				}

				chkDate = formatDate(chkDate);

				chkTotal = chkTotal + r.getBigDecimal(4).doubleValue();

				NumberFormat nf = NumberFormat.getCurrencyInstance();

				String chkAmount = nf.format(r.getBigDecimal(4).doubleValue());

				strBuffer.insert(1, chkDate);
				strBuffer.insert(20, chkNbr);
				strBuffer.insert(34, formatNumber(12, chkAmount));
				strBuffer.insert(55, r.getString(3));

				// Right Justify the String
				strTrim = strBuffer.toString();
				strTrim = ("a" + strTrim).trim().substring(1);

				buf.append(strTrim + "\n");
			}
		}

		buf.append("\n\n\n");

		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String disbTotal = nf.format(chkTotal);

		buf.append("Total payments distributed: " + disbTotal + "\n\n\n");
		buf.append("If you have any questions, please contact this agency at the telephone number listed above.\n\n\n");

		return buf;

	}

	@Override

	@Transactional(readOnly = true)
	public StringBuffer buildNCPPayments(UserInformation userInfoBean) throws Exception {

		logger.debug("\n********** IN RequestPaymentServiceImpl:buildNCPPayments **********\n");

		double total = 0.0D;

		// Performance Tuning code 10/05/2001

		String mpi = userInfoBean.getMpi();

		Calendar c = Calendar.getInstance();

		java.util.Date now = c.getTime();

		c.add(Calendar.MONTH, -13);

		java.util.Date start = c.getTime();

		java.sql.Date endDate = new java.sql.Date(now.getTime());

		java.sql.Date startDate = new java.sql.Date(start.getTime());

		StringBuffer buf = new StringBuffer();

		buf.append("NOTE: PLEASE DO NOT RESPOND DIRECTLY TO THIS E-MAIL MESSAGE. THIS ADDRESS IS NOT MONITORED.\n\n");

		buf.append("This message has been sent in response to your request for a thirteen month "
				+ "payment history. If you did not request this service or believe this "
				+ "message has been sent to you in error, please contact " + WebsiteConfiguration.getPrimaryEmail()
				+ ".\n\n" + "Below is your payment history. \n\n"
				+ "NORTH CAROLINA DEPARTMENT OF HEALTH AND HUMAN SERVICES       " + formatDate(endDate.toString())
				+ "\n\n" + "Non-Custodial Parent MPI # " + mpi + "\n\n");

		SqlRowSet rs = requestDao.getCallCenterAddress("3700000CCT", "AGCY");

		while (rs.next()) {
			String addrLine1 = rs.getString(4);
			String addrLine2 = rs.getString(5);
			String city = rs.getString(6);
			String state = rs.getString(7);
			String zip5 = rs.getString(8);
			String zip4 = rs.getString(9);
			String phAcd = rs.getString(11);
			String phExc = rs.getString(12);
			String phLn = rs.getString(13);

			buf.append(addrLine1 + "\n");
			buf.append(addrLine2 + "\n");
			buf.append(city + " " + state + " " + zip5 + "-" + zip4 + "\n");
			buf.append("(" + phAcd + ")" + phExc + "-" + phLn + "\n\n\n");
		}

		rs = requestDao.getPartInfo(mpi);

		while (rs.next()) {
			String fname = rs.getString(3).trim();
			String mname = rs.getString(4).trim();
			String lname = rs.getString(2).trim();

			NameFormat nf = new NameFormat(lname, fname, mname);
			String name = nf.toMixedCase();

			buf.append("Dear " + name + ":\n\n");
		}

		buf.append("This notice is to provide you with a listing of your "
				+ "child support payments and how these payments were credited. " + "It includes payments made from "
				+ formatDate(startDate.toString()) + " to " + formatDate(endDate.toString()) + ".\n\n");

		buf.append("\nCASE SUMMARY:\n\n");

		// Build the header for Case Summary
		StringBuffer strBuffer = new StringBuffer(100);

		for (int i = 0; i < 100; i++) {
			strBuffer.insert(i, " ");
		}

		strBuffer.insert(1, "Case Number");
		strBuffer.insert(21, "Child(ren)");
		strBuffer.insert(56, "Custodial Parent");

		// Right Justify the String
		String strTrim = strBuffer.toString();
		strTrim = ("a" + strTrim).trim().substring(1);
		buf.append(strTrim + "\n");

		buf.append(
				"____________________________________________________________________________________________________\n");

		String strChildName = "";

		SqlRowSet r = requestDao.getCases(mpi, "AP");

		// For each case, get the Children

		while (r.next()) {

			String ivdCase = r.getString(1);

			SqlRowSet rs3 = requestDao.getParent(ivdCase, "CLI");

			String strMPI = "";
			String strCustodial = "0";

			while (rs3.next()) {
				strMPI = rs3.getString(1);
			}

			int iMPI = Integer.parseInt(strMPI);

			if (iMPI < 2000) {

				rs3 = requestDao.getSystemAccount(strMPI, "SYST");

				while (rs3.next()) {
					strCustodial = rs3.getString(1);
				}

			} else {

				rs3 = requestDao.getParticipant(strMPI);

				while (rs3.next()) {
					String lname = rs3.getString(1);
					String fname = rs3.getString(2);
					String mname = rs3.getString(3);

					NameFormat nf = new NameFormat(lname, fname, mname);
					String name = nf.toMixedCase();
					strCustodial = name;
				}

			}

			// Get all the children on the case

			SqlRowSet rs2 = requestDao.getChildren(ivdCase);

			while (rs2.next()) {
				String fname = rs2.getString(2).trim();
				String mname = rs2.getString(3).trim();
				String lname = rs2.getString(1).trim();

				NameFormat nf = new NameFormat(lname, fname, mname);
				strChildName = nf.toMixedCase();

				strBuffer = new StringBuffer(100);

				for (int i = 0; i < 100; i++) {
					strBuffer.insert(i, " ");
				}

				strBuffer.insert(1, ivdCase);
				strBuffer.insert(21, strChildName);
				strBuffer.insert(56, strCustodial);

				// Right Justify the String
				strTrim = strBuffer.toString();
				strTrim = ("a" + strTrim).trim().substring(1);
				buf.append(strTrim + "\n");

			}

		}

		buf.append("\n\nPAYMENT SUMMARY:\n\n");

		// Build the header for Case Summary
		strBuffer = new StringBuffer(100);

		for (int i = 0; i < 100; i++) {
			strBuffer.insert(i, " ");
		}

		strBuffer.insert(1, "Date");
		strBuffer.insert(16, "Amount");
		strBuffer.insert(33, "Case");
		strBuffer.insert(48, "Amount");
		strBuffer.insert(64, "Payment");

		// Right Justify the String
		strTrim = strBuffer.toString();
		strTrim = ("a" + strTrim).trim().substring(1);
		buf.append(strTrim + "\n");

		// Build the header for Case Summary
		strBuffer = new StringBuffer(100);

		for (int i = 0; i < 100; i++) {
			strBuffer.insert(i, " ");
		}

		strBuffer.insert(17, "Paid");
		strBuffer.insert(31, "Credited");
		strBuffer.insert(47, "Credited");
		strBuffer.insert(65, "Type");

		// Right Justify the String
		strTrim = strBuffer.toString();
		strTrim = ("a" + strTrim).trim().substring(1);
		buf.append(strTrim + "\n");

		buf.append(
				"____________________________________________________________________________________________________\n");

		r = requestDao.getNcpPayments(mpi, startDate, endDate);

		while (r.next()) {
			if ((r.getString(9).trim().equals("RCP")) || ((r.getString(9).trim().equals("ADJ")) && (r.getInt(5) == 1)
					&& (r.getString(10).trim().equals("RCP"))) || (r.getInt(5) != 1)) {
				total = total + r.getBigDecimal(2).doubleValue();

				strBuffer = new StringBuffer(100);
				for (int i = 0; i < 100; i++) {
					strBuffer.insert(i, " ");
				}

				NumberFormat nf = NumberFormat.getCurrencyInstance();
				String pymtAmt = nf.format(r.getBigDecimal(2).doubleValue());

				strBuffer.insert(0, formatDate(r.getDate(1).toString()));
				strBuffer.insert(11, formatNumber(12, pymtAmt));
				strBuffer.insert(30, r.getString(4));
				strBuffer.insert(44, formatNumber(12, pymtAmt));

				String pymtSrc = r.getString(3).trim();
				String pymtSrcDesc = "";

				if (pymtSrc.equals("ADWC") || pymtSrc.equals("APWC")) {
					pymtSrcDesc = "Worker's Compensation";
				} else if (pymtSrc.equals("ALOD") || pymtSrc.equals("ALOT")) {
					pymtSrcDesc = "Military Allotment";
				} else if (pymtSrc.equals("APE") || pymtSrc.equals("APED") || pymtSrc.equals("SEFT")) {
					pymtSrcDesc = "Employer Payment";
				} else if (pymtSrc.equals("APP")) {
					pymtSrcDesc = "Check/Money Order";
				} else if (pymtSrc.equals("BOND")) {
					pymtSrcDesc = "Bond";
				} else if (pymtSrc.equals("ESC")) {
					pymtSrcDesc = "Unemployment";
				} else if (pymtSrc.equals("ETAX")) {
					pymtSrcDesc = "Tax Payment";
				} else if (pymtSrc.equals("FIDM")) {
					pymtSrcDesc = "Fin. Institution Payment";
				} else if (pymtSrc.equals("FTAX")) {
					pymtSrcDesc = "Federal Tax";
				} else if (pymtSrc.equals("IRSF")) {
					pymtSrcDesc = "IRS Collection";
				} else if (pymtSrc.equals("LIEN")) {
					pymtSrcDesc = "Lien Release";
				} else if (pymtSrc.equals("LUCK")) {
					pymtSrcDesc = "Lottery";
				} else if (pymtSrc.equals("OSP")) {
					pymtSrcDesc = "Other State Agency";
				} else if (pymtSrc.equals("OTST")) {
					pymtSrcDesc = "Other State";
				} else if (pymtSrc.equals("PURA")) {
					pymtSrcDesc = "Admin. Enforced Paym't.";
				} else if (pymtSrc.equals("PURG")) {
					pymtSrcDesc = "Purge Payment";
				} else if (pymtSrc.equals("STAX")) {
					pymtSrcDesc = "State Tax";
				} else if (pymtSrc.equals("VLTY")) {
					pymtSrcDesc = "Voluntary Payment";
				} else {
					pymtSrcDesc = "Unknown Payment Source";
				}

				strBuffer.insert(59, pymtSrcDesc);

				// Right Justify the String
				strTrim = strBuffer.toString();
				strTrim = ("a" + strTrim).trim().substring(1);

				String payment = strTrim + "\n";
				buf.append(payment);
			}
		}

		return buf;

	}

	/*
	 * this request helps the user of this class request a payment coupon to be
	 * mailed to them. Note: The records inserted by this servlet into a db2
	 * table are processed by the Acts Batch on a nightly basis.
	 */

	@Override
	@Transactional(readOnly = true)
	public boolean getMailAddress(String mpi) throws Exception {

		logger.debug("\n********** IN RequestPaymentServiceImpl:getMailAddress **********\n");

		boolean mailExist = false;

		SqlRowSet rs = requestDao.getMailAddress(mpi);

		if (rs.next()) {

			mailExist = true; // mail address is present to send coupon
								// through mail.
		}

		return mailExist;

	}

	@Override
	@Transactional
	public void createWebTran(String mpi, String caseRelshp, String ivdCase) throws Exception

	{
		logger.info("\n********** IN RequestPaymentServiceImpl:createWebTran **********\n");

		requestDao.createWebTran(mpi, caseRelshp, ivdCase);

	}

	String formatDate(String inDate)

	{
		try {
			java.util.Date outDate = null;

			SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

			outDate = ds.parse(inDate);

			if (outDate != null) {

				inDate = df.format(outDate).trim();

			}

		}

		catch (Exception e)

		{
			logger.error(e.getMessage());

		}

		return inDate;
	}

	public String formatNumber(int fieldlen, String number) {

		StringBuffer sb = new StringBuffer();

		int numSpaces = fieldlen - number.length();

		for (int i = 0; i < numSpaces; i++) {
			sb.append(" ");
		}

		sb.append(number);

		return sb.toString();
	}

}