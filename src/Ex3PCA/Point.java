package Ex3PCA;

/**
 * Represents a point cloud point
 */
public class Point 
{
	/** The location of the point */
	private Vector3D _point;
	/** The normal to the point */
	private Vector3D _normal;
	
	/**
	 * Creates a new point
	 * @param point The point location
	 * @param normal The normal to the point
	 */
	public Point(Vector3D point, Vector3D normal)
	{
		_point = point;
		_normal = normal;
	}
	
	/**
	 * @return Gets the locaiton of the point
	 */
	public Vector3D getPoint()
	{
		return _point;
	}
	
	/**
	 * @return Gets the normal to the point
	 */
	public Vector3D getNormal()
	{
		return _normal;
	}
}
