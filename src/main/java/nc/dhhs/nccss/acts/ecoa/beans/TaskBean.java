package nc.dhhs.nccss.acts.ecoa.beans;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

/**
 * @author Phani Konuru
 *
 */
public class TaskBean implements java.io.Serializable {


	private static final long serialVersionUID = 2475359179048017331L;

	private int idReference = 0;
	
	private String cdType = "";
	
	private String cdResol = "";
	
	private BigDecimal idNotes;
	
	private Timestamp tsCreate;
	
	private Timestamp tsUpdate;
	
	private Timestamp tsAssign;
	
	private Timestamp tsEnd;
	
	private String idWrkrAssign = "";
	
	private String idWrkrCreate = "";
	
	private String idWrkrUpdate = "";

	/**
	 * @return the idReference
	 */
	public int getIdReference()
	{
		return idReference;
	}

	/**
	 * @param idReference the idReference to set
	 */
	public void setIdReference(int idReference)
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
	 * @return the tsCreate
	 */
	public Timestamp getTsCreate()
	{
		return tsCreate;
	}

	/**
	 * @param tsCreate the tsCreate to set
	 */
	public void setTsCreate(Timestamp tsCreate)
	{
		this.tsCreate = tsCreate;
	}

	/**
	 * @return the tsUpdate
	 */
	public Timestamp getTsUpdate()
	{
		return tsUpdate;
	}

	/**
	 * @param tsUpdate the tsUpdate to set
	 */
	public void setTsUpdate(Timestamp tsUpdate)
	{
		this.tsUpdate = tsUpdate;
	}

	/**
	 * @return the tsAssign
	 */
	public Timestamp getTsAssign()
	{
		return tsAssign;
	}

	/**
	 * @param tsAssign the tsAssign to set
	 */
	public void setTsAssign(Timestamp tsAssign)
	{
		this.tsAssign = tsAssign;
	}

	/**
	 * @return the tsEnd
	 */
	public Timestamp getTsEnd()
	{
		return tsEnd;
	}

	/**
	 * @param tsEnd the tsEnd to set
	 */
	public void setTsEnd(Timestamp tsEnd)
	{
		this.tsEnd = tsEnd;
	}

	/**
	 * @return the idWrkrAssign
	 */
	public String getIdWrkrAssign()
	{
		return idWrkrAssign;
	}

	/**
	 * @param idWrkrAssign the idWrkrASsign to set
	 */
	public void setIdWrkrAssign(String idWrkrAssign)
	{
		this.idWrkrAssign = idWrkrAssign;
	}

	/**
	 * @return the idWrkrCreate
	 */
	public String getIdWrkrCreate()
	{
		return idWrkrCreate;
	}

	/**
	 * @param idWrkrCreate the idWrkrCreate to set
	 */
	public void setIdWrkrCreate(String idWrkrCreate)
	{
		this.idWrkrCreate = idWrkrCreate;
	}

	/**
	 * @return the idWrkrUpdate
	 */
	public String getIdWrkrUpdate()
	{
		return idWrkrUpdate;
	}

	/**
	 * @param idWrkrUpdate the idWrkrUpdate to set
	 */
	public void setIdWrkrUpdate(String idWrkrUpdate)
	{
		this.idWrkrUpdate = idWrkrUpdate;
	}
	
}
