
package nc.dhhs.nccss.acts.ecoa.beans;

/**
 * A Simple Utility Class that inspects a name for suffix characters and convert
 * them to Mixed Case/Upper Case based on Type
 * 
 * 
 * @author: mvelagapudi
 */
public class NameFormat
{

	String					last_name;
	String					first_name;
	String					middle_name;

	static final String[]	suffix	= { " JR.", " JR", " SR.", " SR", " DDS.", " DDS", " ESQ.", " ESQ", " MD.", " MD", " PA.", " PA", " PHD.", " PHD", " I.", " I", " IST", " II.", " II", " 2ND", " III.", " III", " 3RD", " IV.", " IV", " 4TH", " V.", " V", " 5TH", " CPA.", " CPA" };

	/**
	 * NameFormat constructor comment.
	 */
	public NameFormat()
	{
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (10/22/2001 2:29:40
	 * PM)
	 * 
	 * @param last_name
	 *            java.lang.String
	 * @param first_name
	 *            java.lang.String
	 * @param middle_name
	 *            java.lang.String
	 */
	public NameFormat(String last_name, String first_name, String middle_name)
	{

		this.last_name = last_name.trim();
		this.first_name = first_name.trim();
		this.middle_name = middle_name.trim();
	}

	/**
	 * Insert the method's description here. Creation date: (10/22/2001 3:29:29
	 * PM)
	 * 
	 * @return java.lang.String
	 * @param name
	 *            java.lang.String
	 */
	public String inspectSuffixes(String name)
	{

		boolean found = false;

		name = name.trim();

		if (name != null && name.length() > 1)
		{
			for (int i = 0; i < suffix.length; i++)
			{
				int indx = name.indexOf(suffix[i]);
				if ((indx != -1) && (indx > 0))
				{
					String namesuffix = name.substring(indx);

					if (namesuffix.equals(" JR."))
					{
						namesuffix = " Jr.";
					}
					else if (namesuffix.equals(" JR"))
					{
						namesuffix = " Jr";
					}
					else if (namesuffix.equals(" SR."))
					{
						namesuffix = " Sr.";
					}
					else if (namesuffix.equals(" SR"))
					{
						namesuffix = " Sr";
					}
					else if (namesuffix.equals(" PHD."))
					{
						namesuffix = " PhD.";
					}
					else if (namesuffix.equals(" PHD"))
					{
						namesuffix = " PhD";
					}
					else if (namesuffix.equals(" 1ST"))
					{
						namesuffix = " 1st";
					}
					else if (namesuffix.equals(" 2ND"))
					{
						namesuffix = " 2nd";
					}
					else if (namesuffix.equals(" 3RD"))
					{
						namesuffix = " 3rd";
					}
					else if (namesuffix.equals(" 4TH"))
					{
						namesuffix = " 4th";
					}
					else if (namesuffix.equals(" 5TH"))
					{
						namesuffix = " 5th";
					}
					name = name.substring(0, indx);
					name = name.substring(0, 1) + name.substring(1).toLowerCase() + namesuffix;
					found = true;
					break;
				}
			}

			if (!found && name.length() > 1)
			{
				name = name.substring(0, 1) + name.substring(1).toLowerCase();
			}

			return name;
		}
		else if (name != null)
		{
			return name;
		}
		else
		{
			return "";
		}
	}

	/**
	 *
	 * @return java.lang.String
	 */
	public String toMixedCase()
	{

		if (middle_name.length() > 1)
		{
			middle_name = inspectSuffixes(middle_name);
		}

		if (first_name.length() > 1)
		{
			first_name = inspectSuffixes(first_name);
		}

		if (last_name.length() > 1)
		{
			last_name = inspectSuffixes(last_name);
		}

		String name = first_name + " " + middle_name + " " + last_name;

		return name;

	}
}
