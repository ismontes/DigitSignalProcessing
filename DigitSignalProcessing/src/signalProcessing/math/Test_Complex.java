package signalProcessing.math;

public class Test_Complex 
{
	public static Complex num1 = new Complex(2.0, 4.0);
	public static Complex num2 = new Complex(-1.0, -2.0);
	public static Complex num3 = new Complex(0.0, 3.0);
	public static Complex num4 = new Complex(-8.0, 0.0);
	
	public static void main(String[] args) 
	{
		testAddition();
		System.out.println("");
		
		testSubtract();
		System.out.println("");
		
		testMultiply();
		System.out.println("");
		
		testMagnitude();
		System.out.println("");
	}
	
	public static void testAddition()
	{
		Complex sum1 = num1.add(num2);
		Complex sum2 = num3.add(num4);
		
		if(sum1.getReal() == 1.0 && sum1.getImag() == 2.0)
		{
			System.out.println("Sum1 successful");
		}
		if(sum2.getReal() == -8.0 && sum2.getImag() == 3.0)
		{
			System.out.println("Sum2 successful");
		}
	}
	
	public static void testSubtract()
	{
		Complex sub1 = num1.subtract(num2);
		Complex sub2 = num3.subtract(num4);
		
		if(sub1.getReal() == 3.0 && sub1.getImag() == 6.0)
		{
			System.out.println("Sub1 successful");
		}
		if(sub2.getReal() == 8.0 && sub2.getImag() == 3.0)
		{
			System.out.println("Sub2 successful");
		}
	}
	
	public static void testMultiply()
	{
		Complex prod1 = num1.multiply(num2);
		Complex prod2 = num3.multiply(num4);
		
		if(prod1.getReal() == 6.0 && prod1.getImag() == -8.0)
		{
			System.out.println("Prod1 successful");
		}
		if(prod2.getReal() == 0.0 && prod2.getImag() == -24.0)
		{
			System.out.println("Prod2 successful");
		}
	}
	
	public static void testMagnitude()
	{
		double mag1 = num1.magnitude();
		double mag2 = num2.magnitude();
		double mag3 = num3.magnitude();
		double mag4 = num4.magnitude();
		
		if(mag1 == Math.sqrt(20) && mag2 == Math.sqrt(5) &&
				mag3 == 3.0 && mag4 == 8.0)
		{
			System.out.println("All magnitudes successful");
		}
				
	}
}
