package signalProcessing.signal;

public class Test_NoiseGenerator 
{
	public static void main(String[] args) 
	{
		compareOutputs();
		System.out.println("");
		
		testZeroNoise();
		System.out.println("");
		
		testNegativeDev();
		System.out.println("");
	}
	
	public static void compareOutputs()
	{
		double[] signal = SignalGenerator.generateSinWave(20, 1000, 50, 1.0);
		double[] noisy = NoiseGenerator.addNoise(signal, 0.5);
		
		for (int i = 0; i < 5; i++) 
		{
			System.out.printf("Original: %.4f	Noisy: %.4f%n",
					signal[i], noisy[i]);
		}
	}
	
	public static void testZeroNoise()
	{
		double[] signal = SignalGenerator.generateSinWave(500, 500, 50, 5.0);
		double[] noisy = NoiseGenerator.addNoise(signal, 0);
		
		boolean same = true;
		
		for(int i = 0; i < signal.length; i++)
		{
			if(signal[i] != noisy[i])
			{
				same = false;
				break;
			}
		}
		
		if(same) {System.out.println("Pass: Zero noise effects nothing");}
		else {System.out.println("Fail: Zoise is effectin signal");}
	}

	public static void testNegativeDev()
	{
		double[] signal = SignalGenerator.generateSinWave(100, 50, 50, 2.0);
		try
		{
			@SuppressWarnings("unused")
			double[] noisy = NoiseGenerator.addNoise(signal, -5.0);
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Standard deviation must be > 0");
		}
	}
}
