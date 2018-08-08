package nc.dhhs.nccss.acts.ecoa.beans;

/**
 * @author mvelagapudi
 *
 *         A Class that is used to hold the rank, frequency code, factor and
 *         description of the frequency code. factor
 * 
 *         An object of this class must be created before the FrequencyConvertor
 *         can be used.
 * 
 */
public class FrequencyRank
{
	public int		freqRank;
	public String	freqCode;
	public Double	freqFactor;
	public String	frequencyName;

	//constructor
	public FrequencyRank(int rank, String code, double factor, String name)
	{
		freqRank = rank;
		freqCode = code;
		freqFactor = new Double(factor);
		frequencyName = name;

	}
}
