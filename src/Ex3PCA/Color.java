package Ex3PCA;

/**
 * A color object with R, G, B components
 */
public class Color 
{
	/** The red component of the color*/
	private double _red;
	/** The green component  of the color*/
	private double _green;
	/** The blue component of the color*/
	private double _blue;
	
	/**
	 * Creates a new color object
	 * @param red The red component
	 * @param green The green component
	 * @param blue The blue component
	 */
	public Color(double red, double green, double blue)
	{
		_red = trimColorValue(red);
		_green = trimColorValue(green);
		_blue = trimColorValue(blue);
	}
	
	/**
	 * Creates a new color as a copy another
	 * @param other The color to copy
	 */
	public Color(Color other)
	{
		_red = other._red;
		_blue = other._blue;
		_green = other._green;		
	}
	
	/**
	 * 
	 * @return The red component of the color
	 */
	public double getRed()
	{
		return _red;
	}
	
	/**
	 * 
	 * @return The green component of the color
	 */
	public double getGreen()
	{
		return _green;
	}
	
	/**
	 * 
	 * @return The blue component of the color
	 */
	public double getBlue()
	{
		return _blue;
	}
	
	/**
	 * Adds another color to this color component by component and returns it as a new object
	 * @param other The other color
	 * @return The result of the addition operation
	 */
	public Color add(Color other)
	{
		return new Color(_red + other._red, _green + other._green, _blue + other._blue);
	}
	
	/**
	 * Multiplies another color with this color component by component and returns it as a new object
	 * @param other The other color
	 * @return The result of the multiplication operation
	 */
	public Color multiply(Color other)
	{
		return new Color(_red * other._red, _green * other._green, _blue * other._blue);		
	}
	
	/**
	 * Multiply every copmonent of this color with a coefficient and returns the result as a new object
	 * @param coefficient The coefficient to multiply with
	 * @return The reuslt of the multiplication
	 */
	public Color multiply(double coefficient)
	{
		return new Color(_red * coefficient, _green * coefficient, _blue * coefficient);		
	}
	
	/**
	 * Trims the value of the color, so it will be in the range of 0 to 1
	 * @param value The value to trim
	 * @return The trimmed result, minimum is 0, maximum is 1
	 */
	private double trimColorValue(double value)
	{
		return Math.max(0d, Math.min(1.0, value));
	}
	
	/**
	 * Gets the string represntation of the object
	 */
	public String toString()
	{
		return String.format("(%.2f,%.2f,%.2f)", _red,_blue,_green); 
	} 
}
