package Ex3PCA;

import java.util.ArrayList;

public class PointCloud 
{
	private ArrayList<Point> _points;
	private Color _color;
	private double _size;
	private Vector3D _center;
	
	public static final int SAMPLE_SIZE = 1000;
	
	public PointCloud(ArrayList<Point> points, Color color, double size)
	{
		_points = points;
		_color = color;
		_size = size;
		
		_center = calculateCenter();
	}
	
	private Vector3D calculateCenter()
	{
		// calculate center
		double xSum = 0d;
		double ySum = 0d;
		double zSum = 0d;
		
		int step  = Math.max(1, _points.size() / SAMPLE_SIZE);
		
		// TODO: maybe calculate the center for everything?
		for (int i = 0; i < Math.min(_points.size(),SAMPLE_SIZE) ; i++)
		{
			Point point = _points.get(i * step);
			xSum += point.getPoint().getX();
			ySum += point.getPoint().getY();
			zSum += point.getPoint().getZ();
		}
		
		return new Vector3D(xSum / (double) SAMPLE_SIZE, ySum / (double)SAMPLE_SIZE, zSum / (double)SAMPLE_SIZE);
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
	
	public Vector3D getCenter()
	{
		return _center; 
	}
}
