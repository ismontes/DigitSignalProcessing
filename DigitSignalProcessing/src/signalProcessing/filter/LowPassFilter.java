package signalProcessing.filter;

import signalProcessing.math.Complex;

/*
 * [LowPassFilter]	- Keep the desired range of frequencies while removing
 * 					  the cutoff range
 */
public class LowPassFilter 
{
	/*
	 * Runs through the FFT output and separates the frequencies that we want
	 * 
	 *@param[spectrum]		- FFT output
	 *@param[sampleRate]	- Samples per second
	 *@param[cuttOff]		- Highest frequency to keep
	 *
	 *@return				- Filtered Complex[]
	 */
	public static Complex[] apply(Complex[] spectrum, double sampleRate,
									double cutOff)
	{
		/*********************[Invalid inputs]*********************************/
		
		if(spectrum == null)
		{
			throw new IllegalArgumentException("Spectrum can't be null");
		}
		if(cutOff < 0)
		{
			throw new IllegalArgumentException("Cutoff frequency can't be neg");
		}
		
		/*********************[Filter frequencies]*****************************/
		
		Complex[] filteredSpectrum = new Complex[spectrum.length];
		
		for(int i = 0; i < spectrum.length; i++)
		{
			double frequency = i * sampleRate / spectrum.length;
			
			if(frequency <= cutOff) 
			{
				filteredSpectrum[i] = spectrum[i];
			}
			else
			{
				filteredSpectrum[i] = new Complex(0, 0);
			}
		}
		return filteredSpectrum;
	}
}
