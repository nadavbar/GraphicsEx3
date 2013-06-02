package Ex3PCA;

public class Pixel 
{
	private int _x;
	private int _y;
	private double _distance; 
	
	public Pixel(int x, int y, double distance)
	{
		_x = x;
		_y = y;
		_distance = distance;
	}
	
	public int getX()
	{
		return _x;
	}
	
	public int getY()
	{
		return _y;
	}
	
	public double getDistance()
	{
		return _distance;
	}

}
