package Ex3PCA;

import java.util.ArrayList;

import Jama.Matrix;
import Jama.SingularValueDecomposition;

/**
 * Represents a point cloud with a bounding box
 */
public class PointCloud 
{
	/** The point cloud collection of points*/
	private ArrayList<Point> _points;
	/** The point cloud color*/
	private Color _color;
	/** The point cloud size*/
	private double _size;
	/** The center of the point cloud */
	private Vector3D _center;
	
	/** The color of the bounding box */
	private static final Color BOUNDING_BOX_COLOR = new Color(0, 0, 1.0);
	/** The size of the boundning box*/
	private static final double BOUNDING_BOX_SIZE = 7;
	
	/** The sample size from which the bounding box will be calculated*/
	public static final int SAMPLE_SIZE = 1000;
	
	/**
	 * Creates a new point cloud object
	 * @param points The collection of points
	 * @param color The point cloud color
	 * @param size The point cloud size
	 */
	public PointCloud(ArrayList<Point> points, Color color, double size)
	{
		_points = points;
		_color = color;
		_size = size;
		
		_center = calculateCenter();
	}
	
	/**
	 * Calculates the center of the point cloud
	 * @return
	 */
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
	
	/**
	 * Calculates the bounding box of the point cloud
	 * @return A point cloud which represents the bounding box
	 */
	public PointCloud calculateBoundingBox()
	{
		Vector3D center = this.getCenter();
		
		 // create the matrix to calculate the SVD for
		Matrix matrix = new Matrix(3, 1000);
		
		int stepSize = Math.max(1,this.getPoints().size() / PointCloud.SAMPLE_SIZE);
		ArrayList<Vector3D> vectorsSet = new ArrayList<Vector3D>();
		for (int i=0; i < Math.min(PointCloud.SAMPLE_SIZE, this.getPoints().size()); i++)
		{
			Vector3D v = this.getPoints().get(i*stepSize).getPoint();
			Vector3D zeroCenteredV = v.sub(center);
			vectorsSet.add(zeroCenteredV);
			matrix.set(0,i,zeroCenteredV.getX());
			matrix.set(1,i,zeroCenteredV.getY());
			matrix.set(2,i,zeroCenteredV.getZ());
		}
		
		// get the svd
		SingularValueDecomposition svd = new SingularValueDecomposition(matrix);
		Matrix u = svd.getU();
		
		// calculates the projections on the PCA axis
		Vector3D [] axisVectors = new Vector3D[6];
		axisVectors[0] = new Vector3D(u.get(0, 0), u.get(1, 0), u.get(2, 0)).normalize();
		axisVectors[1] = new Vector3D(u.get(0, 1), u.get(1, 1), u.get(2, 1)).normalize();
		axisVectors[2] = new Vector3D(u.get(0, 2), u.get(1, 2), u.get(2, 2)).normalize();
		axisVectors[3] = axisVectors[0].multByScalar(-1);
		axisVectors[4] = axisVectors[1].multByScalar(-1);
		axisVectors[5] = axisVectors[2].multByScalar(-1);
		
		double [] maximums = new double[6];
		
		for (Vector3D vec : vectorsSet)
		{
			for (int i=0; i<axisVectors.length; i++)
			{
				double size = vec.dotProduct(axisVectors[i]);
				
				if (size > maximums[i])
				{
					maximums[i] = size;
				}
			}
		}
		
		ArrayList<Point> points = new ArrayList<Point>();
		
		for (int i=0; i<2; i++)
		{
			for (int j=0; j<2; j++)
			{
				for (int k=0; k<2; k++)
				{
					Vector3D first = axisVectors[i*3].multByScalar(maximums[i*3]);
					Vector3D second = axisVectors[j*3+1].multByScalar(maximums[j*3+1]);
					Vector3D third = axisVectors[k*3+2].multByScalar(maximums[k*3+2]);
					Vector3D v = center.add(first).add(second).add(third);
					points.add(new Point(v,	null));
				}
			}
		}
		
		PointCloud boundingBoxCloud = new PointCloud(points, BOUNDING_BOX_COLOR, BOUNDING_BOX_SIZE);
		
		return boundingBoxCloud;
	}
	
	/**
	 * @return The bounding box points
	 */
	public ArrayList<Point> getPoints()
	{
		return _points;
	}
	
	/**
	 * @return The color of the bounding box
	 */
	public Color getColor()
	{
		return _color;
	}
	
	/**
	 * @return The size of each point of the bounding box
	 */
	public double getSize()
	{
		return _size;
	}
	
	public Vector3D getCenter()
	{
		return _center; 
	}
}
