/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.CaseObligationDao;
import nc.dhhs.nccss.acts.ecoa.beans.FrequencyConvertor;
import nc.dhhs.nccss.acts.ecoa.beans.Obligation;
import nc.dhhs.nccss.acts.ecoa.web.service.CaseObligationService;

/**
 * @author Mallika Velagapudi
 *
 */
public class CaseObligationServiceImpl implements CaseObligationService
{
	protected final Logger		logger	= Logger.getLogger(CaseObligationServiceImpl.class);

	@Autowired
	private CaseObligationDao	obligationDao;

	@Override
	@Transactional(readOnly = true)
	public Obligation getCaseObligation(String ivdCase, String caseRelshp) throws Exception

	{
		logger.debug("\n********** IN CaseObligationServiceImpl:getCaseObligation **********\n");

		Obligation ob = new Obligation();

		ob.setCsupTotal(obligationDao.getCsupQuery(ivdCase, caseRelshp));

		ob.setArrsTotal(obligationDao.getArrearsQuery(ivdCase, caseRelshp));

		SqlRowSet rs = obligationDao.getCsupFrequency(ivdCase);

		getLowestFrequency(rs, ob, "CSUP");

		rs = obligationDao.getArrearsFrequency(ivdCase);

		getLowestFrequency(rs, ob, "ARRS");

		return ob;

	}

	/**
	 * Method to Convert the Amounts to one low frequency
	 */
	@SuppressWarnings("static-access")
	private void getLowestFrequency(SqlRowSet rs, Obligation ob, String subType) throws Exception
	{

		FrequencyConvertor fq = new FrequencyConvertor();

		double arrsTotal = 0.0D;
		boolean firstTime = true;

		int prevRank = 0;
		int currentRank = 0;

		String lowestFrequency = "";
		double frequencyAmount = 0.0D;

		String loFreq = "WKLY";
		String prevFreq = "";

		while (rs.next())
		{

			for (int i = 0; i < fq.frequencyRank.length; i++)
			{
				if (fq.frequencyRank[i].freqCode.equals(rs.getString(2)))
				{
					currentRank = fq.frequencyRank[i].freqRank;
					break;
				}
			}

			if (firstTime)
			{
				firstTime = false;
				prevFreq = rs.getString(2);
				Double amt = new Double(rs.getBigDecimal(1).doubleValue());
				arrsTotal = amt.doubleValue();
				prevRank = currentRank;
				loFreq = prevFreq;
			}
			else
			{
				if (rs.getString(2).equals(prevFreq))
				{
					Double amt = new Double(rs.getBigDecimal(1).doubleValue());
					arrsTotal += amt.doubleValue();
				}
				else if (prevRank < currentRank)
				{
					Double amt = new Double(rs.getBigDecimal(1).doubleValue());
					Double convertedAmt = fq.getConvertedAmount(rs.getString(2), prevFreq, amt);
					arrsTotal += convertedAmt.doubleValue();
				}
				else
				{
					Double convertedAmt = fq.getConvertedAmount(prevFreq, rs.getString(2), new Double(arrsTotal));
					Double amt = new Double(rs.getBigDecimal(1).doubleValue());
					arrsTotal = convertedAmt.doubleValue() + amt.doubleValue();
					loFreq = rs.getString(2);

				}
				prevFreq = rs.getString(2);
			}
		}

		frequencyAmount = arrsTotal;
		lowestFrequency = loFreq;

		if (subType.equals("CSUP"))
		{
			ob.setCsupAmt(frequencyAmount);

			ob.setCsupFreq(lowestFrequency);
		}
		else if (subType.equals("ARRS"))
		{
			ob.setArrsAmt(frequencyAmount);

			ob.setArrsFreq(lowestFrequency);
		}

	}

}
