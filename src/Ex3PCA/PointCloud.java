package Ex3PCA;

import java.util.ArrayList;

public class PointCloud 
{
	private ArrayList<Point> _points;
	private Color _color;
	private double _size;
	
	public PointCloud(ArrayList<Point> points, Color color, double size)
	{
		_points = points;
		_color = color;
		_size = size;
	}
	
	public ArrayList<Point> getPoints()
	{
		return _points;
	}
	
	public Color getColor()
	{
		return _color;
	}
	
	public double getSize()
	{
		return _size;
	}
}
