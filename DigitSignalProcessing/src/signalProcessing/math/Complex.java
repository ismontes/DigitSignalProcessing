package signalProcessing.math;
/*
 * [Complex]	- Represent complex numbers and perform complex functions to 
 * 				aid the FFT computations
 */
public class Complex 
{
	//Instance Variables
	private double real;
	private double imag;
	
	/*
	 * Constructor
	 * 
	 * @param [real]	- Real component
	 * @param [imag]	- Imaginary component
	 */
	public Complex(double real, double imag)
	{
		this.real = real;
		this.imag = imag;
	}
	
	/*****************************Getters**************************************/
	
	public double getReal()
	{
		return real;
	}
	
	public double getImag()
	{
		return imag;
	}
	
	/*****************************Functions************************************/
	/*
	 * [Add] two complex numbers
	 */
	public Complex add(Complex other)
	{
		return new Complex((real + other.real), (imag + other.imag));
	}
	
	/*
	 * [Subtract] two complex numbers
	 */
	public Complex subtract(Complex other)
	{
		return new Complex((real - other.real), (imag - other.imag));
	}
	
	/*
	 * [Multiply] two complex numbers
	 */
	public Complex multiply(Complex other)
	{
		double newReal = (real * other.real) - (imag * other.imag);
		double newImag = (real * other.imag) + (imag * other.real);
		
		return new Complex(newReal, newImag);
	}
	
	/*
	 * [Magnitude] of complex vector
	 */
	public double magnitude()
	{
		return Math.sqrt((real * real) + (imag * imag));
	}
}
