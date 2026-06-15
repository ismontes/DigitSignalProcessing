package signalProcessing.math;

import signalProcessing.signal.SignalGenerator;

public class Test_FFT 
{

	public static void main(String[] args) 
	{
		//testBaseCase();
		System.out.println("");
		
		//testAllZeros();
		System.out.println("");
		
		//test50Hz();
		System.out.println("");
		
		testCombinedFrequency();

	}

	public static void testBaseCase()
	{
		Complex[] input = {new Complex(5, 0)};
		
		Complex[] result = FFT.fft(input);
		
		if(result.length == 1 && result[0].getReal() == 5
				&& result[0].getImag() == 0)
		{
			System.out.println("Pass: Recursion stopped correctly");
		}
	}
	
	public static void testAllZeros()
	{
		Complex[] input = {new Complex(0, 0), new Complex(0, 0), 
							new Complex(0, 0), new Complex(0, 0)};
		
		for (Complex val : input) 
		{
			if(val.getImag() != 0 || val.getReal() != 0)
			{
				System.out.println("Fail: Values changed somehow");
				break;
			}
		}
		
		System.out.println("Pass: All values are zero and unchanged");
	}

	public static void test50Hz()
	{
		//Variables
		int samples = 1024;
		double sampleRate = 1000.0;
		double target = 50.0;
		
		//Generate sin() wave
		double[] signal = SignalGenerator.generateSinWave
				(samples, sampleRate, target, 1.0);
		
		//Convert to Complex
		Complex[] input = new Complex[samples];
		
		for (int i = 0; i < samples; i++) 
		{
			input[i] = new Complex(signal[i], 0);
		}
		
		//Run FFT
		Complex[] spectrum = FFT.fft(input);
		
		//Find strongest frequency bin
		double maxMag = 0.0;
		int maxIndex = 0;
		
		//Only examine first half of FFT
		for(int i = 0; i < spectrum.length; i++)
		{
			double magnitude = spectrum[i].magnitude();
			
			if(magnitude > maxMag)
			{
				maxMag = magnitude;
				maxIndex = i;
			}
		}
		
		//Convert FFT bin to frequency
		double detectedFrequency = maxIndex * sampleRate / samples;
		
		System.out.println("Detected frequency: " + detectedFrequency + " Hz");
		
		//Verify that it's close to 50hz
		System.out.println(Math.abs(detectedFrequency - target) < 1.0);
	}

	public static void testCombinedFrequency()
	{
		//Variables
		int samples = 1024;
		double sampleRate = 1000.0;
		
		/***************[Combine sin() wave of 50Hz and 120Hz]*****************/
		
		double[] sin50 = SignalGenerator.generateSinWave
				(samples, sampleRate, 50, 1.0);
		
		double[] sin120 = SignalGenerator.generateSinWave
				(samples, sampleRate, 120, 0.5);
		
		double[] combined = new double[samples];
		
		for(int i = 0; i < combined.length; i++)
		{
			combined[i] = sin50[i] + sin120[i];
		}
		
		/**********************[Convert to Complex]****************************/
		
		Complex[] input = new Complex[samples];
		
		for (int i = 0; i < input.length; i++) 
		{
			input[i] = new Complex(combined[i], 0);
			
		}
		
		/***************************[Run FFT]**********************************/
		
		Complex[] spectrum = FFT.fft(input);
		
		/*********************[Find the strongest bins]************************/
		int strongestBin = -1;
		int lesserBin = -1;
		
		double strongestMag = 0;
		double lesserMag = 0;
		
		for(int i = 0; i < spectrum.length / 2; i++)
		{
			double magnitude = spectrum[i].magnitude();
			
			//We found the strongest so far
			if(magnitude > strongestMag)
			{
				//Move current largest to the second largest
				lesserMag = strongestMag;
				lesserBin = strongestBin;
				
				//Set current value as the largest
				strongestMag = magnitude;
				strongestBin = i;
			}
			//Found a candidate for the second strongest
			else if(magnitude > lesserMag)
			{
				//Set current as the second largest
				lesserMag = magnitude;
				lesserBin = i;
			}
		}
		
		/************************[Print values]********************************/
		
		double strongestFreq = strongestBin * sampleRate / samples;
		double lesserFreq = lesserBin * sampleRate / samples;
		
		System.out.println("Strongest Frequency: " + strongestFreq + " Hz");
		System.out.println("Second strongest Frequency: " + lesserFreq 
							+ " Hz");
		
		System.out.println("Found 50: " + ((Math.abs(strongestFreq - 50) < 2)
							|| (Math.abs(lesserFreq) - 50) < 2));
		System.out.println("Found 120: " + ((Math.abs(strongestFreq - 120) < 2)
				|| (Math.abs(lesserFreq) - 120) < 2));
	}
}
