package Ex3PCA;

/**
 * Represents a view plane. Calculates the coordinate system according to the plane
 */
public class ViewPlane 
{
	/** The x-axis vector for the place*/
	private Vector3D _vx;
	/** The z-axis vector for the plane*/
	private Vector3D _vz;
	/** The y axis vector of the plane*/
	private Vector3D _vy;
	
	private Camera _camera;
	private Vector3D _center;
	
	/**
	 * Creates a new ViewPlane object
	 * @param normal The normal of the plane
	 * @param upVector The up vector
	 */
	public ViewPlane(Camera camera)
	{
		_camera = camera;
		
		_vz = camera.getDirection();
		_vx = camera.getUpVector().crossProduct(_vz);
		_vy = _vx.crossProduct(_vz);
		
		_center = _camera.getPosition().add(_vz.multByScalar(_camera.getScreenDistance()));
	}
	
	/**
	 * 
	 * @return The z-axis component
	 */
	public Vector3D getVz()
	{
		return _vz;
	}
	
	/**
	 * 
	 * @return The x-axis component
	 */
	public Vector3D getVx()
	{
		return _vx;
	}
	
	/**
	 * 
	 * @return The y-axis component
	 */
	public Vector3D getVy()
	{
		return _vy;
	}
	
	public Camera getCamera()
	{
		return _camera;
	}
	
	public Vector3D getCenter()
	{
		return _center;
	}
}
