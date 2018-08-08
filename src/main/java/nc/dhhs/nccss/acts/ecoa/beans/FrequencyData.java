
package nc.dhhs.nccss.acts.ecoa.beans;

/**
 * A Class that is used to hold an amount, frequency and the conversion factor
 * An object of this class must be created before the FrequencyConvertor can be
 * used.
 * 
 * @author: mvelagapudi
 */
class FrequencyData
{
	public String	prevFrequency;
	public String	currFrequency;
	public Double	convFactor;

	/**
	 * FrequencyData constructor comment.
	 */
	public FrequencyData(String f1, String f2, double f)
	{
		prevFrequency = f1;
		currFrequency = f2;
		convFactor = new Double(f);
	}
}
