package Ex3PCA;

public class Point 
{
	private Vector3D _point;
	private Vector3D _normal;
	
	public Point(Vector3D point, Vector3D normal)
	{
		_point = point;
		_normal = normal;
	}
	
	public Vector3D getPoint()
	{
		return _point;
	}
	
	public Vector3D getNormal()
	{
		return _normal;
	}
}
