/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.PaymentHistoryDao;
import nc.dhhs.nccss.acts.ecoa.beans.CheckBean;
import nc.dhhs.nccss.acts.ecoa.beans.JournalTranBean;
import nc.dhhs.nccss.acts.ecoa.beans.NameFormat;
import nc.dhhs.nccss.acts.ecoa.beans.PaymentBean;
import nc.dhhs.nccss.acts.ecoa.web.service.PaymentHistoryService;

/**
 * @author Mallika Velagapudi
 *
 */
public class PaymentHistoryServiceImpl implements PaymentHistoryService
{
	protected final Logger		logger	= Logger.getLogger(PaymentHistoryServiceImpl.class);

	@Autowired
	private PaymentHistoryDao	paymentHistoryDao;

	@Override
	@Transactional(readOnly = true)
	public ArrayList<CheckBean> getCreditForCP(String mpi) throws Exception
	{

		logger.debug("\n********** IN PaymentHistoryServiceImpl:geCreditforCP **********\n");

		ArrayList<CheckBean> checkList = new ArrayList<CheckBean>();

		//statement.setMaxRows(12);

		SqlRowSet rs = paymentHistoryDao.getCheckComp(mpi);

		while (rs.next())
		{
			// rk 12/03/03 - CT# 416627. Exclude checks that were  written to Agencies.

			String type = rs.getString(11);

			if (!type.equalsIgnoreCase("AGCY"))
			{

				CheckBean checkBean = new CheckBean();
				checkBean.setCheckAmount(rs.getBigDecimal(1).toString());
				checkBean.setCheckMpi(rs.getString(2));
				checkBean.setCheckDeposit(false);

				String checkNbr = rs.getString(3).trim();
				String checkStatus = rs.getString(9);
				String checkDate = rs.getDate(4).toString();
				String cdPayInd = rs.getString(12);

				int icheckNbr = 0;

				if (!checkNbr.equals(""))
				{
					// Check Mailed Out
					checkBean.setCheckDeposit(false);
				}
				else if (checkDate.equals("0001-01-01"))
				{
					if (cdPayInd.equalsIgnoreCase("DC"))
					{
						checkBean.setDebitCardDeposit(true);
					}
					// Check to see if the Client has any eft
					// entries
					// If found, the check will be sent out via eft,
					// else it will be mailed

					SqlRowSet r = paymentHistoryDao.getEFTDetails(mpi);

					while (r.next())
					{
						checkBean.setCheckDeposit(true);
						checkBean.setDebitCardDeposit(false);
					}

				}
				else if (!cdPayInd.equalsIgnoreCase("DC"))
				{
					checkBean.setCheckDeposit(true);
				}
				else
				{
					// Check was sent via EFT
					checkBean.setCheckDeposit(true);
				}
				checkBean.setCheckNumber(checkNbr);
				checkBean.setCheckDate(rs.getDate(4));
				checkBean.setCheckCreditDate(rs.getDate(10));
				checkList.add(checkBean);
			}
		}

		return checkList;

	}

	@Override
	@Transactional(readOnly = true)
	public ArrayList<PaymentBean> getPaymentsByNCP(String mpi) throws Exception

	{
		logger.debug("\n********** IN PaymentHistoryServiceImpl:getPaymentsByNCP **********\n");

		ArrayList<JournalTranBean> JournalTranList = new ArrayList<JournalTranBean>();

		SqlRowSet rs = paymentHistoryDao.getJtrnQuery(mpi);

		while (rs.next())
		{
			JournalTranBean jtrnBean = new JournalTranBean();
			jtrnBean.setAmountApplied(rs.getBigDecimal(5));
			jtrnBean.setNbMonth(rs.getShort(1));
			jtrnBean.setNbBatch(rs.getInt(2));
			jtrnBean.setNbItem(rs.getShort(3));
			jtrnBean.setDateTran(rs.getDate(4));
			jtrnBean.setAmountRemaining(rs.getBigDecimal(6));
			JournalTranList.add(jtrnBean);
		}

		ArrayList<PaymentBean> paymentList = new ArrayList<PaymentBean>();

		for (int i = 0; i < JournalTranList.size(); i++)
		{
			JournalTranBean jtrnBean = (JournalTranBean) JournalTranList.get(i);
			PaymentBean paymentBean = new PaymentBean();
			paymentBean.setPayorMpi(mpi);
			paymentBean.setTranDate(jtrnBean.getDateTranStrPg());

			if (jtrnBean.getAmountRemaining().doubleValue() > 0.0D)
			{
				if (jtrnBean.getAmountApplied().compareTo(jtrnBean.getAmountRemaining()) == 0)
				{
					paymentBean.setApplied(false);
					paymentBean.setAmtRemaining("");
				}
				else if (jtrnBean.getAmountApplied().compareTo(jtrnBean.getAmountRemaining()) == 1)
				{
					paymentBean.setApplied(false);
					paymentBean.setAmtRemaining(jtrnBean.getAmountRemaining().toString());
				}
			}
			else
			{
				paymentBean.setApplied(true);
			}

			paymentBean.setAmtApplied(jtrnBean.getAmountApplied().toString());

			double amtAlloc = 0.0D;

			rs = paymentHistoryDao.getAllocQuery(mpi, jtrnBean.getNbMonth(), jtrnBean.getNbBatch(), jtrnBean.getNbItem());

			int j = 0;
			while (rs.next())
			{
				paymentBean.setPayeeCase(j, rs.getString(1));
				paymentBean.setAmtDisbursed(j, rs.getBigDecimal(2).toString());
				amtAlloc += rs.getBigDecimal(2).doubleValue();
				j += 1;
			}

			// rk 12/2/03. CT # 409287. Manual Adjustments must also be
			// reported.

			if (jtrnBean.getAmountApplied().doubleValue() > amtAlloc && j < 20)
			{

				rs = paymentHistoryDao.getManualAdj(mpi, jtrnBean.getNbMonth(), jtrnBean.getNbBatch(), jtrnBean.getNbItem());

				while (rs.next())
				{
					if (j >= 20)
					{
						break;
					}
					paymentBean.setPayeeCase(j, rs.getString(1));
					paymentBean.setAmtDisbursed(j, rs.getBigDecimal(2).toString());
					j++;
				}

			}

			paymentList.add(paymentBean);

		}

		for (int i = 0; i < paymentList.size(); i++)
		{
			PaymentBean p = (PaymentBean) paymentList.get(i);
			String[] nbCases = p.getPayeeCase();
			for (int j = 0; j < nbCases.length; j++)
			{
				if (!nbCases[j].equals(""))
				{

					String strMPI = "0000000000";

					rs = paymentHistoryDao.getPcasQuery(nbCases[j]);

					while (rs.next())
					{
						strMPI = rs.getString(1);
					}

					int iMPI = Integer.parseInt(strMPI);

					if (iMPI < 2000)
					{

						rs = paymentHistoryDao.getSystemAccount(strMPI, "SYST");

						while (rs.next())
						{
							p.setPayeeName(j, rs.getString(1));
						}

					}
					else
					{

						rs = paymentHistoryDao.getParticipant(strMPI);

						while (rs.next())
						{
							String lname = rs.getString(1);
							String fname = rs.getString(2);
							String mname = rs.getString(3);

							NameFormat nf = new NameFormat(lname, fname, mname);
							String name = nf.toMixedCase();

							p.setPayeeName(j, name);
						}

					}

				}

			}
		}

		return paymentList;
	}

}