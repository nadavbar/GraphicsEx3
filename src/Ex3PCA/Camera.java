package Ex3PCA;

/**
 * The camera object
 */
public class Camera 
{
	/** The position of the camera */
	private Vector3D _position;
	/** The look at position */
	private Vector3D _lookAt;
	/** The camera up vector */
	private Vector3D _upVector;
	/** The distance from the camera to it's view plane*/
	private double _screenDistance;
	/** The with of the screen*/
	private double _screenWidth;
	/** The camera view plane normal*/
	private Vector3D _direction;
	
	/**
	 * Creates a new camera object
	 * @param position The camera position
	 * @param lookAt The look at point
	 * @param upVector The up vector
	 * @param screenDistance The distance between the camera and it's view plane
	 * @param screenWidth The screen width
	 */
	public Camera(Vector3D position, Vector3D lookAt, Vector3D upVector,
				  double screenDistance, double screenWidth)
	{
		_position = position;
		_lookAt = lookAt;
		_upVector = upVector;
		_screenDistance = screenDistance;
		_screenWidth = screenWidth;
		_direction = _lookAt.sub(_position).normalize();
	}
	
	/**
	 * 
	 * @return The camera position
	 */
	public Vector3D getPosition()
	{
		return _position;
	}
	
	/**
	 * 
	 * @return The look at point
	 */
	public Vector3D getLookAt()
	{
		return _lookAt;
	}
	
	/**
	 * 
	 * @return The camera up vector
	 */
	public Vector3D getUpVector()
	{
		return _upVector;
	}
	
	/**
	 * 
	 * @return The distance between the camera and it's view plane
	 */
	public double getScreenDistance()
	{
		return _screenDistance;
	}
	
	/**
	 * 
	 * @return The screen width
	 */
	public double getScreenWidth()
	{
		return _screenWidth;
	}
	
	/**
	 * 
	 * @return The camera view plane normal
	 */
	public Vector3D getDirection()
	{
		return _direction;
	}
}
