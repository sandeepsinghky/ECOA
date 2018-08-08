package nc.dhhs.nccss.acts.ecoa.beans;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * @author Phani Konuru
 *
 */
public class TaskNotes implements java.io.Serializable {


	private static final long serialVersionUID = 5765649295590968866L;

	private String idReference = "";
	
	private String cdType = "";
	
	private String cdResol = "";
	
	private BigDecimal idNotes;
	
	private BigDecimal noteNext = new BigDecimal(0);
	
	private String nmNoteRef = "";
	
	private Date				lastUpdatDt;

	private Time				lastUpdateTm;
	
	private String idWrkrLst = "";
	
	private String idLstUpd = "";
	
	private String notesTxt = "";

	/**
	 * @return the idReference
	 */
	public String getIdReference()
	{
		return idReference;
	}

	/**
	 * @param idReference the idReference to set
	 */
	public void setIdReference(String idReference)
	{
		this.idReference = idReference;
	}

	/**
	 * @return the cdType
	 */
	public String getCdType()
	{
		return cdType;
	}

	/**
	 * @param cdType the cdType to set
	 */
	public void setCdType(String cdType)
	{
		this.cdType = cdType;
	}

	/**
	 * @return the cdResol
	 */
	public String getCdResol()
	{
		return cdResol;
	}

	/**
	 * @param cdResol the cdResol to set
	 */
	public void setCdResol(String cdResol)
	{
		this.cdResol = cdResol;
	}

	/**
	 * @return the idNotes
	 */
	public BigDecimal getIdNotes()
	{
		return idNotes;
	}

	/**
	 * @param idNotes the idNotes to set
	 */
	public void setIdNotes(BigDecimal idNotes)
	{
		this.idNotes = idNotes;
	}

	/**
	 * @return the noteNext
	 */
	public BigDecimal getNoteNext()
	{
		return noteNext;
	}

	/**
	 * @param noteNext the noteNext to set
	 */
	public void setNoteNext(BigDecimal noteNext)
	{
		this.noteNext = noteNext;
	}

	/**
	 * @return the nmNoteRef
	 */
	public String getNmNoteRef()
	{
		return nmNoteRef;
	}

	/**
	 * @param nmNoteRef the nmNoteRef to set
	 */
	public void setNmNoteRef(String nmNoteRef)
	{
		this.nmNoteRef = nmNoteRef;
	}

	/**
	 * @return the lastUpdatDt
	 */
	public Date getLastUpdatDt()
	{
		return lastUpdatDt;
	}

	/**
	 * @param lastUpdatDt the lastUpdatDt to set
	 */
	public void setLastUpdatDt(Date lastUpdatDt)
	{
		this.lastUpdatDt = lastUpdatDt;
	}

	/**
	 * @return the lastUpdateTm
	 */
	public Time getLastUpdateTm()
	{
		return lastUpdateTm;
	}

	/**
	 * @param lastUpdateTm the lastUpdateTm to set
	 */
	public void setLastUpdateTm(Time lastUpdateTm)
	{
		this.lastUpdateTm = lastUpdateTm;
	}

	/**
	 * @return the idWrkrLst
	 */
	public String getIdWrkrLst()
	{
		return idWrkrLst;
	}

	/**
	 * @param idWrkrLst the idWrkrLst to set
	 */
	public void setIdWrkrLst(String idWrkrLst)
	{
		this.idWrkrLst = idWrkrLst;
	}

	/**
	 * @return the idLstUpd
	 */
	public String getIdLstUpd()
	{
		return idLstUpd;
	}

	/**
	 * @param idLstUpd the idLstUpd to set
	 */
	public void setIdLstUpd(String idLstUpd)
	{
		this.idLstUpd = idLstUpd;
	}

	/**
	 * @return the notesTxt
	 */
	public String getNotesTxt()
	{
		return notesTxt;
	}

	/**
	 * @param notesTxt the notesTxt to set
	 */
	public void setNotesTxt(String notesTxt)
	{
		this.notesTxt = notesTxt;
	}
	
}
