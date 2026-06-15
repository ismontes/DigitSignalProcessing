package signalProcessing.signal;

/*
 * [SignalGenerator]	- Generate synthetic sin() signals as our base to 
 * 						analyze
 */
public class SignalGenerator 
{
	/*
	 * Based off parameters, we generate our specified sin() wave
	 * 
	 * @param [samples]		- Signal length
	 * @param [sampleRate]	- How often measurements are taken
	 * @param [frequency]	- How fast wave oscillates
	 * @param [amplitude]	- Signal Strength
	 * 
	 * @return 				- double[] of sin() wave
	 */
	public static double[] generateSinWave( int samples, double sampleRate,
											double frequency, double amplitude)
	{
		/*
		 * Invalid Responses
		 */
		if(sampleRate <= 0)
		{
			throw new IllegalArgumentException("Sample Rate must be positive");
		}
		if(samples <= 0) 
		{
			throw new IllegalArgumentException("Must have at least one sample");
		}
		
		/**********************************************************************/
		
		double[] signal = new double[samples];
		
		for (int i = 0; i < signal.length; i++) 
		{
			double t = i / sampleRate;
			
			signal[i] = amplitude * Math.sin(2 * Math.PI * frequency * t);
		}
		return signal;
	}
}
