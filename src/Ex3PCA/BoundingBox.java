package Ex3PCA;

import java.util.ArrayList;
import Jama.SingularValueDecomposition;

public class BoundingBox 
{
	private PointCloud _pointCloud;
	private ArrayList<Point> _boundaries;
	
	public BoundingBox(PointCloud pointCloud)
	{
		_pointCloud = pointCloud;
		_boundaries = new ArrayList<Point>();
	}
	
	public void calculate()
	{
		// TODO: fill here...
	}
	
	public ArrayList<Point> getBoundaries()
	{
		return _boundaries;
	}
}
