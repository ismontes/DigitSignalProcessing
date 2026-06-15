package signalProcessing.math;

/*
 * [FFT]	- Convert a complex signal from the time domain into the frequency
 * 			domain and identify the key frequencies
 */
public class FFT 
{
	public static Complex[] fft(Complex[] x)
	{
		int N = x.length;
		
		/****************[Base case N = 1, Stop and return]********************/
		
		if(N == 1)
		{
			return new Complex[] {x[0]};
		}
		
		/****************[Split into even and odd Samples]*********************/
		
		Complex[] even 	= new Complex[N / 2];
		Complex[] odd	= new Complex[N / 2];
		
		for (int i = 0; i < N / 2; i++) 
		{
			even[i] = x[2 * i];
			odd[i] 	= x[2 * i + 1];
		}
		
		/*********************[Solve Recursively]******************************/
		
		Complex[] evenFFT = fft(even);
		Complex[] oddFFT = fft(odd);
		
		Complex[] combinedSpectrum = new Complex[N];
		
		/****************************[FFT]*************************************/
		
		for (int i = 0; i < N / 2; i++) 
		{
			double angle = -2 * Math.PI * i / N;
			
			Complex phaseFactor = new Complex(Math.cos(angle), Math.sin(angle));
			
			combinedSpectrum[i] = 
					evenFFT[i].add(phaseFactor.multiply(oddFFT[i]));
			
			combinedSpectrum[i + N / 2] = 
					evenFFT[i].subtract(phaseFactor.multiply(oddFFT[i]));
 		}
		
		return combinedSpectrum;
	}
}
