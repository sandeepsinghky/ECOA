package nc.dhhs.nccss.acts.ecoa.web.service;

import nc.dhhs.nccss.acts.ecoa.beans.PreNcIdUser;

/**
 * @author Mallika Velagapudi
 *
 */

public interface UserRegisterService {

	/**
	 * @param mpi
	 * @param ssn
	 * @param dob
	 * @return
	 * @throws Exception
	 */
	public PreNcIdUser isRegisteredOldUser(String mpi, String ssn, String dob) throws Exception;

	/**
	 * @param mpi
	 * @param ssn
	 * @param dob
	 * @param errorMsg
	 * @return
	 * @throws Exception
	 */
	public String getParticipantStatus(String mpi, String ssn, String dob, StringBuffer idPart) throws Exception;

	/**
	 * @param attempts
	 * @param ncId
	 * @return
	 * @throws Exception
	 */
	public int updateNbAttempt(int attempts, String ncId) throws Exception;


    /**
	 * @param mpi
	 * @return
	 * @throws Exception
	 */
	public String getMPIUsedUser(String mpi) throws Exception; 
}
