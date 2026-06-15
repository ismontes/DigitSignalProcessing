package signalProcessing.filter;

import signalProcessing.math.Complex;

public class Test_LowPassFilter 
{

	public static void main(String[] args) 
	{
		testHighCutOff();
		System.out.println("");
		
		testZeroCutOff();
		System.out.println("");
		

	}
	
	public static void testHighCutOff()
	{
		Complex[] spectrum = { new Complex(1, 0), new Complex(2, 0), 
				               new Complex(3, 0), new Complex(4, 0)};
		Complex[] result = LowPassFilter.apply(spectrum, 1000, 1000);
		
		for(int i = 0; i < result.length; i++)
		{
			if(spectrum[i].getReal() != result[i].getReal() ||
					spectrum[i].getImag() != result[i].getImag())
			{
				System.out.println("Fail: The values shouldn't have changed");
				break;
			}
		}
		
		System.out.println("Pass: All values are the same");
	}

    public static void testZeroCutOff()
    {
		Complex[] spectrum = { new Complex(10, 0), new Complex(2, 0), 
	               			   new Complex(3, 0), new Complex(4, 0)};
		
		Complex[] result = LowPassFilter.apply(spectrum, 1000, 0);

		if(result[0].getReal() == 10 && result[0].getImag() == 0)
		{
			System.out.println("Pass: Initial frequency was not changed");
		}
		for(int i = 1; i < result.length; i++)
		{
			if(result[i].getReal() != 0 || result[i].getImag() != 0)
			{
				System.out.println("Fail: The values shouldn't have changed");
				break;
			}
		}

		System.out.println("Pass: All values are the same");
    }

}
