/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.transaction.annotation.Transactional;

import nc.dhhs.nccss.acts.dao.SearchClerkOfCourtDao;
import nc.dhhs.nccss.acts.ecoa.beans.NameFormat;
import nc.dhhs.nccss.acts.ecoa.beans.ThirdParty;
import nc.dhhs.nccss.acts.ecoa.web.service.SearchClerkOfCourtService;

/**
 * @author Mallika Velagapudi
 *
 */
public class SearchClerkOfCourtServiceImpl implements SearchClerkOfCourtService
{
	protected final Logger			logger	= Logger.getLogger(SearchClerkOfCourtServiceImpl.class);

	@Autowired
	private SearchClerkOfCourtDao	clerkOfCourtDao;

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.SearchClerkOfCourtService#
	 * getClerkOfCourtList(java.lang.String, java.lang.String, java.lang.String,
	 * int, int)
	 */
	@Override
	@Transactional(readOnly = true)
	public ArrayList<ThirdParty> getClerkOfCourtList(String strCity, String strCounty, String fipsType, int searchType,
			int iPage) throws Exception

	{

		logger.debug("\n********** IN SearchClerkOfCourtServiceImpl:getClerkOfCourtList **********\n");

		ArrayList<ThirdParty> cocList = new ArrayList<ThirdParty>();

		SqlRowSet rs = null;

		switch (searchType)
		{
		case 0: // Search by State
			rs = clerkOfCourtDao.getClerkOfCourt(searchType, null, null, fipsType);
			break;
		case 1: // Search by State & City
			rs = clerkOfCourtDao.getClerkOfCourt(searchType, strCity, null, fipsType);
			break;
		case 2: // Search by State & County
			rs = clerkOfCourtDao.getClerkOfCourt(searchType, null, strCounty, fipsType);
			break;
		case 3: // Search by State, City & County
			rs = clerkOfCourtDao.getClerkOfCourt(searchType, strCity, strCounty, fipsType);
			break;
		default:
			break;
		}

		while (rs.next())
		{
			ThirdParty tp = new ThirdParty();
			tp.setThirdPartyId(rs.getString(2).trim());
			tp.setThirdPartyNm(rs.getString(3).trim());
			tp.setAddressLn1(rs.getString(4).trim());
			tp.setAddressLn2(rs.getString(5).trim());
			tp.setThirdPartyCity(rs.getString(6).trim());
			tp.setThirdPartyState(rs.getString(7).trim());
			tp.setThirdPartyZip5(rs.getString(8).trim());
			NameFormat nf = new NameFormat(rs.getString(14).trim(), rs.getString(15).trim(), rs.getString(16).trim());
			tp.setContactName(nf.toMixedCase());

			tp.setPhoneAreaCode(rs.getString(11).trim());
			tp.setPhoneExc(rs.getString(12).trim());
			tp.setPhoneLn(rs.getString(13).trim());
			cocList.add(tp);

		}

		return cocList;

	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.SearchClerkOfCourtService#
	 * getClerkOfCourtDetail(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public ThirdParty getClerkOfCourtDetail(String id3pty, String thirdPtyType) throws Exception
	{
		logger.debug("\n********** IN SearchClerkOfCourtServiceImpl:getClerkOfCourtDetail **********\n");

		ThirdParty tp = null;

		SqlRowSet rs = clerkOfCourtDao.getClerkOfCourtDetail(id3pty, thirdPtyType);

		if (rs.next())
		{
			tp = new ThirdParty();
			tp.setThirdPartyId(rs.getString(2).trim());
			tp.setThirdPartyNm(rs.getString(3).trim());
			tp.setAddressLn1(rs.getString(4).trim());
			tp.setAddressLn2(rs.getString(5).trim());
			tp.setThirdPartyCity(rs.getString(6).trim());
			tp.setThirdPartyState(rs.getString(7).trim());
			tp.setThirdPartyZip5(rs.getString(8).trim());
			tp.setContactName(rs.getString(15).trim() + " " + rs.getString(16).trim() + " " + rs.getString(14).trim());
			tp.setPhoneAreaCode(rs.getString(11).trim());
			tp.setPhoneExc(rs.getString(12).trim());
			tp.setPhoneLn(rs.getString(13).trim());

		}

		return tp;

	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.SearchClerkOfCourtService#
	 * getClerkOfCourtPageList(java.util.ArrayList, int)
	 */
	@Override

	public ArrayList<ThirdParty> getClerkOfCourtPageList(ArrayList<ThirdParty> cocList, int iPage) throws Exception
	{
		logger.debug("\n********** IN SearchClerkOfCourtServiceImpl:getClerkOfCourtPageList **********\n");

		ArrayList<ThirdParty> pageCocList = new ArrayList<ThirdParty>();

		int count = 0;

		int recordCount = 0;

		Iterator<ThirdParty> cocIterator = cocList.iterator();

		while (cocIterator.hasNext() && count < 11)
		{

			ThirdParty tp = (ThirdParty) cocIterator.next();

			if ((recordCount / 10) == iPage)

			{

				pageCocList.add(tp);

				count += 1;
			}
			recordCount += 1;
		}

		return pageCocList;

	}

}