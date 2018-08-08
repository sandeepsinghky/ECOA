/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.web.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.WebServiceException;
import org.springframework.ws.client.core.WebServiceTemplate;

import gov.nc.ncidng.ncidngwebservice.schemas.AuthenticateToNCIDv2Request;
import gov.nc.ncidng.ncidngwebservice.schemas.AuthenticateToNCIDv2Response;
import gov.nc.ncidng.ncidngwebservice.schemas.SearchUserByLoginIDRequest;
import gov.nc.ncidng.ncidngwebservice.schemas.SearchUserByLoginIDResponse;
import nc.dhhs.nccss.acts.ecoa.web.config.WebsiteConfiguration;
import nc.dhhs.nccss.acts.ecoa.web.service.AuthenticationWebService;

/**
 * @author Vijay Peddapalli, Phani Konuru
 *
 */
public class AuthenticationWebServiceImpl implements AuthenticationWebService
{

	@Autowired
	private WebServiceTemplate	webServiceTemplate;

	protected final Logger		logger	= Logger.getLogger(getClass());

	/**
	 * 
	 */
	public AuthenticationWebServiceImpl()
	{
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.AuthenticationWebService#
	 * getNCIDAuthenticationReponse(java.lang.String, java.lang.String)
	 */
	public AuthenticateToNCIDv2Response getNCIDAuthenticationReponse(String loginName, String password)
			throws WebServiceException
	{

		logger.debug("getNCIDAuthenticationReponse(" + loginName + ", password:*****  )");

		AuthenticateToNCIDv2Request authRequest = new AuthenticateToNCIDv2Request();

		authRequest.setAppID(WebsiteConfiguration.getAppIDReqNCID());

		authRequest.setAppPassword(WebsiteConfiguration.getAppPwdReqNCID());

		authRequest.setUserID(loginName);

		authRequest.setUserPassword(password);

		AuthenticateToNCIDv2Response authResponse = (AuthenticateToNCIDv2Response) webServiceTemplate.marshalSendAndReceive(authRequest);

		/*
		 * String returnCode =
		 * authResponse.getAuthenticateToNCIDv2Result().getMessageArray().
		 * getMessage().get(0).getCode(); if (returnCode.equals("0")) {
		 * logger.info("after AuthenticateToNCIDv2Response"); }
		 */
		return (authResponse == null ? null : authResponse);
	}

	/*
	 * (non-Javadoc)
	 * @see nc.dhhs.nccss.acts.ecoa.web.service.AuthenticationWebService#
	 * searchUserByLoginID(java.lang.String)
	 */
	public SearchUserByLoginIDResponse searchUserByLoginID(String loginName) throws WebServiceException
	{
		logger.debug("searchUserByLoginID(" + loginName + ")");

		SearchUserByLoginIDRequest sLogInIDRequest = new SearchUserByLoginIDRequest();

		sLogInIDRequest.setUserID(loginName);
		sLogInIDRequest.setAppID(WebsiteConfiguration.getAppIDReqNCID());
		sLogInIDRequest.setAppPassword(WebsiteConfiguration.getAppPwdReqNCID());
		sLogInIDRequest.setSearchOp(WebsiteConfiguration.getSearchOp());

		SearchUserByLoginIDResponse slogInIDResponse = (SearchUserByLoginIDResponse) webServiceTemplate.marshalSendAndReceive(sLogInIDRequest);

		return slogInIDResponse;
	}

}
