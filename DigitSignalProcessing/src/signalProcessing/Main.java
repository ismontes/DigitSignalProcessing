package signalProcessing;

import signalProcessing.detection.TargetDetector;
import signalProcessing.filter.LowPassFilter;
import signalProcessing.math.Complex;
import signalProcessing.math.FFT;
import signalProcessing.signal.NoiseGenerator;
import signalProcessing.signal.SignalGenerator;

/*
 * [Main] - An example connecting all of the classes together. Creating a 
 * 			signal, we add noise and run it through the FFT to see if we can
 * 			recover 50hz.
 */
public class Main 
{
	public static void main(String[] args) 
	{
		//Signal Parameters
		int 	samples 	= 1024;
		double 	sampleRate 	= 1000.0;
		double 	targetFreq 	= 50.0;
		double 	amplitude 	= 3.0;
		double 	noiseLevel 	= 0.25;
		double 	cuttOffFreq = 100.0;
		double 	detectThres = 100.0;
		
		//[1] - Generate a clean signal
		double[] signal = SignalGenerator.generateSinWave
					(samples, sampleRate, targetFreq, amplitude);
		
		//[2] - Add noise
		double[] noisy = NoiseGenerator.addNoise(signal, noiseLevel);
		
		//[3] - Convert to Complex
		Complex[] input = new Complex[samples];
		
		for(int i = 0; i < samples; i++)
		{
			input[i] = new Complex(noisy[i], 0);
		}
		
		//[4] - Compute FFT
		Complex[] spectrum = FFT.fft(input);
		
		//[5] Filter the spectrum
		Complex[] filteredSpectrum = LowPassFilter.apply
					(spectrum, sampleRate, cuttOffFreq);
		
		//[6] - Detect Dominant Frequency
		double detectedFreq = TargetDetector.detectDominantFrequency
					(filteredSpectrum, sampleRate);
		
		//[7] - Determine if a target exists
		boolean targetDetected = TargetDetector.detectTarget
					(filteredSpectrum, detectThres);
		
		//[8] - Display Results
		System.out.println("Expected Frequency: " + targetFreq + " Hz");
		System.out.println("Detected Frequency: " + detectedFreq + " Hz");
		
		if(targetDetected)
		{
			System.out.println("Target detected! :D");
		}
		else
		{
			System.out.println("No target was detected :(");
		}
		
	}
}
