package Ex3PCA;

import java.util.ArrayList;

import Jama.Matrix;
import Jama.SingularValueDecomposition;

public class BoundingBox 
{
	private PointCloud _pointCloud;
	private ArrayList<Point> _boundaries;
	private static final Color COLOR = new Color(0, 0, 1.0); 
	private static final double SIZE = 7;
	
	public BoundingBox(PointCloud pointCloud)
	{
		_pointCloud = pointCloud;
		_boundaries = new ArrayList<Point>();
	}
	
	public PointCloud calculate()
	{
		Vector3D center = _pointCloud.getCenter();
		
		Matrix matrix = new Matrix(3, 1000);
		
		int stepSize = Math.max(1,_pointCloud.getPoints().size() / PointCloud.SAMPLE_SIZE);
		ArrayList<Vector3D> vectorsSet = new ArrayList<Vector3D>();
		for (int i=0; i < Math.min(PointCloud.SAMPLE_SIZE, _pointCloud.getPoints().size()); i++)
		{
			Vector3D v = _pointCloud.getPoints().get(i*stepSize).getPoint();
			Vector3D zeroCenteredV = v.sub(center);
			vectorsSet.add(zeroCenteredV);
			matrix.set(0,i,zeroCenteredV.getX());
			matrix.set(1,i,zeroCenteredV.getY());
			matrix.set(2,i,zeroCenteredV.getZ());
		}
		
		SingularValueDecomposition svd = new SingularValueDecomposition(matrix);
		Matrix u = svd.getU();
		
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
		
		PointCloud boundingBoxCloud = new PointCloud(points, COLOR, SIZE);
		
		return boundingBoxCloud;
	}
	
	
	
	public ArrayList<Point> getBoundaries()
	{
		return _boundaries;
	}
}
