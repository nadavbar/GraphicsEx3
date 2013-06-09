package Ex3PCA;

/**
 * Represents a pixel in the image
 */
public class Pixel 
{
	/** The pixel x coordinate */
	private int _x;
	/** The pixel y coordinate */
	private int _y;
	/** The distance of the pixel from the screen*/
	private double _distance; 
	
	/**
	 * Creates a new pixel object
	 * @param x The x coordinate of the pixel
	 * @param y The y coordinate of the pixel
	 * @param distance The distance of the pixel from the camera
	 */
	public Pixel(int x, int y, double distance)
	{
		_x = x;
		_y = y;
		_distance = distance;
	}
	
	/**
	 * Gets the x coordinate of the pixel
	 * @return The x coordinate of the pixel
	 */
	public int getX()
	{
		return _x;
	}
	
	/**
	 * The y coordinate of the pixel
	 * @return The y coordinate of the pixel
	 */
	public int getY()
	{
		return _y;
	}
	
	/**
	 * Gets the distance of the pixel from the camera
	 * @return The distance of the pixel from the camera
	 */
	public double getDistance()
	{
		return _distance;
	}

}
