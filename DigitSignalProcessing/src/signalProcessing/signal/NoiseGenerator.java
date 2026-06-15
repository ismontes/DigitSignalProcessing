package signalProcessing.signal;
import java.util.Random;

/*
 * [NoiseGenerator]		- Simulates real-world sensor noise. We take a clean 
 * 						signal and 'corrupts' it using Guassian Noise
 */
public class NoiseGenerator 
{
	private static Random rand = new Random();
	
	/*
	 * Adds a Gaussian noise to the signal
	 * 
	 * @param [signal]	- Signal array from SignalGenerator
	 * @param [stdDev]	- Strength of noise
	 * 
	 * @return 			- Noisy signal double array
	 */
	public static double[] addNoise(double[] signal, double stdDev)
	{
		/*Invalid Inputs*/
		if(stdDev < 0)
		{
			throw new IllegalArgumentException("Standard Deviation must "
					+ "be > 0");
		}
		if(signal == null)
		{
			throw new IllegalArgumentException("Signal can't be null");
		}
		
		/**********************************************************************/
		
		double[] noisy = new double[signal.length];
		
		for(int i = 0; i < noisy.length; i++)
		{
			//Generate Gaussian noise (mean = 0, variance = 1)
			double noise	= rand.nextGaussian() * stdDev;
			
			noisy[i] 		= signal[i] + noise;
		}
		
		return noisy;
	}

}
