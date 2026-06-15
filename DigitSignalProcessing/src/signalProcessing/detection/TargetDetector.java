package signalProcessing.detection;

import signalProcessing.math.Complex;

public class TargetDetector 
{
	/*
	 * [TargetDetector]	- Interprets the FFT and tried to find if there was
	 *   				  a target signal in the first place
	 */
	
	
	/*
	 * Finds the strongest frequency in the given signal
	 * 
	 * @param [spectrum]	- Signal spectrum given by fft
	 * @param [sampleRate]	- Samples per second
	 * 
	 * @return				- Strongest frequency
	 */
	public static double detectDominantFrequency(Complex[] spectrum, 
												double sampleRate)
	{
		/**************************[Invalid Arguments]*************************/
		
		if(spectrum == null)
		{
			throw new IllegalArgumentException("Spectrum can't be null");
		}
		
		/*************************[Find Strongest Bin]*************************/
		
		double 	maxMag = 0;
		int 	maxIndex = 0;
		
		for(int i = 0; i < spectrum.length / 2; i++)
		{
			double magnitude = spectrum[i].magnitude();
			
			if(magnitude > maxMag)
			{
				maxMag = magnitude;
				maxIndex = i;
			}
		}
		
		return (maxIndex * sampleRate / spectrum.length);
		
	}
	
	/*
	 * Determines if a target signal is found
	 * 
	 * @param [spectrum]	- Signal spectrum given by fft
	 * @param [sampleRate]	- Samples per second
	 * 
	 * @return [boolean]	- Found or not found
	 */
	public static boolean detectTarget(Complex[] spectrum, double threshold)
	{
		/**************************[Invalid Arguments]*************************/
		
		if(spectrum == null)
		{
			throw new IllegalArgumentException("Spectrum can't be null");
		}
		
		/************************[Find Valid Frequency]************************/
		double maxMag = 0;
		
		for(int i = 0; i < spectrum.length / 2; i++)
		{
			double magnitude = spectrum[i].magnitude();
			
			if(magnitude > maxMag)
			{
				maxMag = magnitude;
			}
		}
		return (maxMag > threshold);
	}
}
