/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.beans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 * @author mvelagapudi
 *
 */
public class PreNcIdUser implements Serializable {

	private static final long serialVersionUID = -1792754984477964889L;

	private String id_part = "";

	private String id_user = "";

	private String id_pswd = "";

	private String in_dis_login = "";

	private String lastName = "";

	private String firstName = "";

	private String middleName = "";

	private String ad_part_ln_1 = "";

	private String ad_part_ln2 = "";

	private String city = "";

	private String state = "";

	private String ad_part_zip_5 = "";

	private String ad_part_zip_4 = "";

	private String nb_tel = "";

	private String emailId = "";

	private String pswd_qstn = "";

	private String pswd_ans = "";

	private Date lastUpdDate;

	private Time lastUpdTime;

	private String id_wrkr_lst_upd;

	private String trml_lst_upd;

	private Date dt_lst_login;

	private String userAdmin = "";

	int nb_grace_login;

	private Date dateDeactivated;

	private String ncId = "";

	private long id_user_auth;

	SimpleDateFormat DATE_FORMAT_SCREEN = new SimpleDateFormat("MM/dd/yyyy");

	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * @return the id_part
	 */
	public String getId_part() {
		return id_part;
	}

	/**
	 * @param id_part
	 *            the id_part to set
	 */
	public void setId_part(String id_part) {
		this.id_part = id_part;
	}

	/**
	 * @return the id_user
	 */
	public String getId_user() {
		return id_user;
	}

	/**
	 * @param id_user
	 *            the id_user to set
	 */
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	/**
	 * @return the id_pswd
	 */
	public String getId_pswd() {
		return id_pswd;
	}

	/**
	 * @param id_pswd
	 *            the id_pswd to set
	 */
	public void setId_pswd(String id_pswd) {
		this.id_pswd = id_pswd;
	}

	/**
	 * @return the in_dis_login
	 */
	public String getIn_dis_login() {
		return in_dis_login;
	}

	/**
	 * @param in_dis_login
	 *            the in_dis_login to set
	 */
	public void setIn_dis_login(String in_dis_login) {
		this.in_dis_login = in_dis_login;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the ad_part_ln_1
	 */
	public String getAd_part_ln_1() {
		return ad_part_ln_1;
	}

	/**
	 * @param ad_part_ln_1
	 *            the ad_part_ln_1 to set
	 */
	public void setAd_part_ln_1(String ad_part_ln_1) {
		this.ad_part_ln_1 = ad_part_ln_1;
	}

	/**
	 * @return the ad_part_ln2
	 */
	public String getAd_part_ln2() {
		return ad_part_ln2;
	}

	/**
	 * @param ad_part_ln2
	 *            the ad_part_ln2 to set
	 */
	public void setAd_part_ln2(String ad_part_ln2) {
		this.ad_part_ln2 = ad_part_ln2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the street
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the ad_part_zip_5
	 */
	public String getAd_part_zip_5() {
		return ad_part_zip_5;
	}

	/**
	 * @param ad_part_zip_5
	 *            the ad_part_zip_5 to set
	 */
	public void setAd_part_zip_5(String ad_part_zip_5) {
		this.ad_part_zip_5 = ad_part_zip_5;
	}

	/**
	 * @return the ad_part_zip_4
	 */
	public String getAd_part_zip_4() {
		return ad_part_zip_4;
	}

	/**
	 * @param ad_part_zip_4
	 *            the ad_part_zip_4 to set
	 */
	public void setAd_part_zip_4(String ad_part_zip_4) {
		this.ad_part_zip_4 = ad_part_zip_4;
	}

	/**
	 * @return the nb_tel
	 */
	public String getNb_tel() {
		return nb_tel;
	}

	/**
	 * @param nb_tel
	 *            the nb_tel to set
	 */
	public void setNb_tel(String nb_tel) {
		this.nb_tel = nb_tel;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the pswd_qstn
	 */
	public String getPswd_qstn() {
		return pswd_qstn;
	}

	/**
	 * @param pswd_qstn
	 *            the pswd_qstn to set
	 */
	public void setPswd_qstn(String pswd_qstn) {
		this.pswd_qstn = pswd_qstn;
	}

	/**
	 * @return the lastUpdDate
	 */
	public Date getLastUpdDate() {
		return lastUpdDate;
	}

	/**
	 * @param lastUpdDate
	 *            the lastUpdDate to set
	 */
	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	/**
	 * @return the lastUpdTime
	 */
	public Time getLastUpdTime() {
		return lastUpdTime;
	}

	/**
	 * @param lastUpdTime
	 *            the lastUpdTime to set
	 */
	public void setLastUpdTime(Time lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}

	/**
	 * @return the id_wrkr_lst_upd
	 */
	public String getId_wrkr_lst_upd() {
		return id_wrkr_lst_upd;
	}

	/**
	 * @param id_wrkr_lst_upd
	 *            the id_wrkr_lst_upd to set
	 */
	public void setId_wrkr_lst_upd(String id_wrkr_lst_upd) {
		this.id_wrkr_lst_upd = id_wrkr_lst_upd;
	}

	/**
	 * @return the trml_lst_upd
	 */
	public String getTrml_lst_upd() {
		return trml_lst_upd;
	}

	/**
	 * @param trml_lst_upd
	 *            the trml_lst_upd to set
	 */
	public void setTrml_lst_upd(String trml_lst_upd) {
		this.trml_lst_upd = trml_lst_upd;
	}

	/**
	 * @return the dt_lst_login
	 */
	public Date getDt_lst_login() {
		return dt_lst_login;
	}

	public String getDt_lst_loginStrPg() {
		if (dt_lst_login == null)
			return null;
		else
			return DATE_FORMAT_SCREEN.format(dt_lst_login).trim();
	}

	public String getDt_lst_loginStr() {
		if (dt_lst_login == null)
			return null;
		else
			return DATE_FORMAT.format(dt_lst_login).trim();
	}

	/**
	 * @param dt_lst_login
	 *            the dt_lst_login to set
	 */
	public void setDt_lst_login(Date dt_lst_login) {
		this.dt_lst_login = dt_lst_login;
	}

	/**
	 * @return the userAdmin
	 */
	public String getUserAdmin() {
		return userAdmin;
	}

	/**
	 * @param userAdmin
	 *            the userAdmin to set
	 */
	public void setUserAdmin(String userAdmin) {
		this.userAdmin = userAdmin;
	}

	/**
	 * @return the nb_grace_login
	 */
	public int getNb_grace_login() {
		return nb_grace_login;
	}

	/**
	 * @param nb_grace_login
	 *            the nb_grace_login to set
	 */
	public void setNb_grace_login(int nb_grace_login) {
		this.nb_grace_login = nb_grace_login;
	}

	/**
	 * @return the dateDeactivated
	 */
	public Date getDateDeactivated() {
		return dateDeactivated;
	}

	/**
	 * @param dateDeactivated
	 *            the dateDeactivated to set
	 */
	public void setDateDeactivated(Date dateDeactivated) {
		this.dateDeactivated = dateDeactivated;
	}

	/**
	 * @return the ncId
	 */
	public String getNcId() {
		return ncId;
	}

	/**
	 * @param ncId
	 *            the ncId to set
	 */
	public void setNcId(String ncId) {
		this.ncId = ncId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the id_user_auth
	 */
	public long getId_user_auth() {
		return id_user_auth;
	}

	/**
	 * @param id_user_auth
	 *            the id_user_auth to set
	 */
	public void setId_user_auth(long id_user_auth) {
		this.id_user_auth = id_user_auth;
	}

	/**
	 * @return the pswd_ans
	 */
	public String getPswd_ans() {
		return pswd_ans;
	}

	/**
	 * @param pswd_ans
	 *            the pswd_ans to set
	 */
	public void setPswd_ans(String pswd_ans) {
		this.pswd_ans = pswd_ans;
	}

}
