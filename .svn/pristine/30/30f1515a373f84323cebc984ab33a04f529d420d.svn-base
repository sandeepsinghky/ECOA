package nc.dhhs.nccss.acts.ecoa.web.typeconverters;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

/**
 * @author Vijay Peddapalli
 *
 */
public class DateTypeConverter extends PropertyEditorSupport
{

	Logger		log		= Logger.getLogger(DateTypeConverter.class);

	String[]	formats	= { "MM-dd-yyyy", "MM/dd/yyyy" };

	/**
	 * 
	 */
	public DateTypeConverter()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param source
	 */
	public DateTypeConverter(Object source)
	{
		super(source);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 */
	public String getAsText()
	{
		return (getValue() == null ? "" : new SimpleDateFormat(formats[0]).format((Date) getValue()));
	}

	/*
	 * (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	public void setAsText(String textValue) throws IllegalArgumentException
	{
		if (StringUtils.isEmpty(textValue))
		{
			setValue(null);
			return;
		}
		Date retDate = null;
		try
		{
			retDate = DateUtils.parseDate(textValue, formats);
		}
		catch (ParseException e)
		{
			setValue(null);
			throw new IllegalArgumentException();
		}
		setValue(retDate);
	}

}
