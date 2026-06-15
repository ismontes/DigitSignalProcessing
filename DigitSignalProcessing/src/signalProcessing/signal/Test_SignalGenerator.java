package signalProcessing.signal;

public class Test_SignalGenerator 
{
	public static void main(String[] args) 
	{
		testLength();
		testZero();
		testAmplitude();
		testZeroAmplitude();
		testZeroFrequency();
		testZeroSampleRate();
	}
	
	public static void testLength()
	{
		double[] signal = SignalGenerator.generateSinWave(100, 50, 50, 50);
		
		if(signal.length != 100) 	{System.out.println("Fail: Wrong Length");}
		else 						{System.out.println("Pass: Right Length");}
	}

	public static void testZero()
	{
		double[] signal = SignalGenerator.generateSinWave(100, 50, 50, 50);
		
		if(Math.abs(signal[0]) < 0.000001) 
		{System.out.println("Pass: sin(0) = 0");}
		
		else 
		{System.out.println("Fail: sin(0) != 0");}
	}
	
	public static void testAmplitude()
	{
		double[] signal = SignalGenerator.generateSinWave(1000, 1000, 50, 2.0);
		
		for (double d : signal) 
		{
			if(d > 2.0 || d < -2.0)
			{
				System.out.println("Fail: Value exceeds amplitude");
				break;
			}
		}
		
		System.out.println("Pass: Values didn't exceed amplitude");
	}
	
	public static void testZeroAmplitude()
	{
		double[] signal = SignalGenerator.generateSinWave(50, 50, 50, 0);
		
		for(double d : signal)
		{
			if(d != 0)
			{
				System.out.println("Fail: Value is not 0");
				break;
			}
		}
		
		System.out.println("Pass: All values are 0");
	}
	
	public static void testZeroFrequency() 
	{
		double[] signal = SignalGenerator.generateSinWave(50, 50, 0, 10);
		
		for(double d : signal)
		{
			if(d != 0)
			{
				System.out.println("Fail: Value is not 0");
				break;
			}
		}
		
		System.out.println("Pass: Frequency of 0 gives all-zero vals");
	}
	
	public static void testZeroSampleRate()
	{
		try
		{
			SignalGenerator.generateSinWave(100, 0, 50, 5);
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Exception caught: Not possible to have 0 sample"
					+ " rate");
		}
	}
}
