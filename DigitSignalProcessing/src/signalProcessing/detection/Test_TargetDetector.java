package signalProcessing.detection;


/*
 * Import the Noise and Signal Generator classes so that we can test properly
 */
import signalProcessing.signal.NoiseGenerator;
import signalProcessing.signal.SignalGenerator;
import signalProcessing.math.Complex;
import signalProcessing.math.FFT;

public class Test_TargetDetector 
{

	public static void main(String[] args) 
	{
		testStrongTarget();
		System.out.println("");
		
		testTargetwithNoise();
		System.out.println("");
		
		testNoTargetFound();

	}
	
	/*
	 * Strong frequency can be found with NO NOISE
	 */
	public static void testStrongTarget()
	{
		/************************[Variables]***********************************/
		int 	samples 	= 1024;
		double 	sampleRate 	= 1000.0;
		double 	targetFreq 	= 50.0;
		
		/*************[Create Signal and Convert to Complex]*******************/
		double[] signal = SignalGenerator.generateSinWave
									(samples, sampleRate, targetFreq, 1.0);
		
		Complex[] input = new Complex[samples];
		
		for (int i = 0; i < samples; i++) 
		{
			input[i] = new Complex(signal[i], 0);
		}
		
		/****************************[Run FFT]*********************************/
		Complex[] spectrum = FFT.fft(input);
		
		
		/*************[Detect Dominant Frequency and Check]********************/
		double detectedFreq = TargetDetector.detectDominantFrequency
									(spectrum, sampleRate);
		
		
		System.out.println("Freq detected with no noise: " 
								+ (Math.abs(detectedFreq - targetFreq) < 2.0));
	}

	/*
	 * Strong frequency can be found WITH NOISE
	 */
	public static void testTargetwithNoise()
	{
		/************************[Variables]***********************************/
		int 	samples 	= 1024;
		double 	sampleRate 	= 1000.0;
		double 	targetFreq 	= 50.0;
		
		/********************[Create Signal & Add Noise]***********************/
		double[] signal = SignalGenerator.generateSinWave
									(samples, sampleRate, targetFreq, 1.0);
		
		double[] noisy = NoiseGenerator.addNoise(signal, 0.2);
		
		/*******************[Convert to Complex & Run FFT]*********************/
		Complex[] input = new Complex[samples];
		
		for (int i = 0; i < samples; i++) 
		{
			input[i] = new Complex(noisy[i], 0);
		}	
		
		Complex[] spectrum = FFT.fft(input);
		
		/*************[Detect Dominant Frequency and Check]********************/
		
		boolean found = TargetDetector.detectTarget(spectrum, 100.0);
		
		System.out.println("Freq detected with noise: " 
								+ found);
	}

	/*
	 * Check if random noise triggers a target detection
	 */
	public static void testNoTargetFound()
	{
		int samples = 1024;
		
		/********************[Create Signal & Add Noise]***********************/
		double[] signal = new double[samples];
		
		double[] noisy = NoiseGenerator.addNoise(signal, 0.2);
		
		/*******************[Convert to Complex & Run FFT]*********************/
		Complex[] input = new Complex[samples];
		
		for (int i = 0; i < samples; i++) 
		{
			input[i] = new Complex(noisy[i], 0);
		}	
		
		Complex[] spectrum = FFT.fft(input);
		
		/*************[Detect Dominant Frequency and Check]********************/
		
		boolean found = !TargetDetector.detectTarget(spectrum, 100.0);
		
		System.out.println("Low-level noise should not trigger a found freq: " 
								+ found);		
	}
}
