/**
 * 
 */
package nc.dhhs.nccss.acts.ecoa.beans;

/**
 * @author mvelagapudi
 *
 */
public class FrequencyConvertor
{

	static final public FrequencyRank[]	frequencyRank	= { new FrequencyRank(1, "WKLY", 1.0D, "Weekly"), new FrequencyRank(2, "BIWK", 1.0D, "Bi Weekly"), new FrequencyRank(3, "SEMO", 1.0D, "Semi Monthly"), new FrequencyRank(4, "MNTH", 1.0D, "Monthly"), new FrequencyRank(5, "BIMO", 1.0D, "Bi Monthly"), new FrequencyRank(6, "QTLY", 1.0D, "Quarterly"), new FrequencyRank(7, "SEAN", 1.0D, "Semi Anually"), new FrequencyRank(8, "ANNL", 1.0D, "Yearly") };

	static final public FrequencyData[]	frequencyData	= { new FrequencyData("WKLY", "ANNL", 52.0D), new FrequencyData("WKLY", "SEAN", 26.0D), new FrequencyData("WKLY", "QTLY", 13.0D), new FrequencyData("WKLY", "MNTH", 4.33D), new FrequencyData("WKLY", "BIWK", 2.0D), new FrequencyData("WKLY", "WKLY", 1.0D), new FrequencyData("BIWK", "ANNL", 26.0D), new FrequencyData("BIWK", "SEAN", 13.0D), new FrequencyData("BIWK", "QTLY", 6.5D), new FrequencyData("BIWK", "MNTH", 2.16D), new FrequencyData("BIWK", "BIWK", 1.0D), new FrequencyData("SEMO", "ANNL", 24.0D), new FrequencyData("SEMO", "SEAN", 12.0D), new FrequencyData("SEMO", "QTLY", 6.0D), new FrequencyData("SEMO", "MNTH", 2.0D), new FrequencyData("SEMO", "SEMO", 1.0D), new FrequencyData("MNTH", "ANNL", 12.0D), new FrequencyData("MNTH", "SEAN", 6.0D), new FrequencyData("MNTH", "QTLY", 3.0D), new FrequencyData("MNTH", "MNTH", 1.0D), new FrequencyData("QTLY", "ANNL", 4.0D), new FrequencyData("QTLY", "SEAN", 2.0D), new FrequencyData("QTLY", "QTLY", 1.0D), new FrequencyData("SEAN", "ANNL", 2.0D), new FrequencyData("ANNL", "ANNL", 1.0D) };

	/**
	 * FrequencyConverter constructor comment.
	 */
	public FrequencyConvertor()
	{
		super();
	}

	/**
	 * Method to Convert Amounts from one frequency to another Creation date:
	 * (3/28/00 4:02:01 PM)
	 * 
	 * @return java.lang.Double
	 * @param cFrequency
	 *            java.lang.String
	 * @param dFrequency
	 *            java.lang.String
	 * @param amt
	 *            java.lang.Double
	 */
	public Double getConvertedAmount(String currentFrequency, String desiredFrequency, Double amt)
	{
		int currentRank = 0;
		int desiredRank = 0;
		double convertedValue = 0;

		// If the frequency passed is WKLY format
		for (int i = 0; i < frequencyRank.length; i++)
		{
			if (frequencyRank[i].freqCode.equals(currentFrequency))
			{
				currentRank = frequencyRank[i].freqRank;
				break;
			}
		}

		// If the frequency passed is Weekly Format
		if (currentRank == 0)
		{
			for (int i = 0; i < frequencyRank.length; i++)
			{
				if (frequencyRank[i].frequencyName.equals(currentFrequency))
				{
					currentRank = frequencyRank[i].freqRank;
					currentFrequency = frequencyRank[i].freqCode;
					break;
				}
			}
		}

		for (int i = 0; i < frequencyRank.length; i++)
		{
			if (frequencyRank[i].freqCode.equals(desiredFrequency))
			{
				desiredRank = frequencyRank[i].freqRank;
				break;
			}
		}

		if (desiredRank == 0)
		{
			for (int i = 0; i < frequencyRank.length; i++)
			{
				if (frequencyRank[i].frequencyName.equals(desiredFrequency))
				{
					desiredRank = frequencyRank[i].freqRank;
					desiredFrequency = frequencyRank[i].freqCode;
					break;
				}
			}
		}

		if (desiredRank < currentRank)
		{
			for (int i = 0; i < frequencyData.length; i++)
			{
				if ((frequencyData[i].prevFrequency.equals(desiredFrequency)) && (frequencyData[i].currFrequency.equals(currentFrequency)))
				{
					convertedValue = amt.doubleValue() / frequencyData[i].convFactor.doubleValue();
					return new Double(convertedValue);
				}
			}
		}

		if (desiredRank > currentRank)
		{
			for (int i = 0; i < frequencyData.length; i++)
			{
				if ((frequencyData[i].currFrequency.equals(desiredFrequency)) && (frequencyData[i].prevFrequency.equals(currentFrequency)))
				{
					convertedValue = amt.doubleValue() * frequencyData[i].convFactor.doubleValue();
					return new Double(convertedValue);
				}
			}
		}

		return amt;
	}
}
