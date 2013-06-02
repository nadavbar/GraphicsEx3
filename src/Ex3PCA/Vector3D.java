package Ex3PCA;

/**
 * Representation of 3D vector
 */
public class Vector3D 
{
	/** The x coordinate of the vector*/
	private double _x;
	/** The y coordinate of the vector */
	private double _y;
	/** The z coordinate of the vector */
	private double _z;
	
	/** 
	 * Creates a new vector object
	 * @param x The x coordinate of the object
	 * @param y The y coordinate of the object
	 * @param z The z coordinate of the object
	 */
	public Vector3D(double x, double y, double z)
	{
		_x = x;
		_y = y;
		_z = z;
	}
	
	/**
	 * Adds the given vector to this vector and returns the result as a new object
	 * @param second The vector to add to this vector
	 * @return The result of the addition
	 */
	public Vector3D add(Vector3D second)
	{
		return new Vector3D(_x + second._x, _y + second._y, _z + second._z);
	}
	
	/**
	 * Subtracts the given vector form this vector and returns the result
	 * @param second The vector to subtract
	 * @return The result of the subtraction operation
	 */
	public Vector3D sub(Vector3D second)
	{
		return new Vector3D(_x - second._x, _y - second._y, _z - second._z);
	}
	
	/**
	 * Calculates the dot product of this vector and the given vector
	 * @param other The other vector
	 * @return The result of the dot product operation between the 2 vectors
	 */
	public double dotProduct(Vector3D other)
	{
		return _x * other._x + _y * other._y + other._z * _z;
	}
	
	/**
	 * Performs a cross product between this vector and the given vector
	 * @param other The second vector
	 * @return The result of the cross product operation
	 */
	public Vector3D crossProduct(Vector3D other)
	{
		return new Vector3D(_y*other._z - _z*other._y,
							_z*other._x - _x*other._z,
							_x * other._y - _y*other._x);
	}
	
	/**
	 * Normalizes the vector so it's size will be one and return a new vector with the result
	 * @return The nomalized vector
	 */
	public Vector3D normalize()
	{
		double size = size();
		
		return new Vector3D(_x / size, _y / size, _z/size);
	}
	
	/**
	 * Multiplies this vector with the given scalar value and returns the result
	 * @param scalar The scalar to multiply this vector with
	 * @return This vector, multiplied by the scalar
	 */
	public Vector3D multByScalar(double scalar)
	{
		return new Vector3D(_x * scalar, _y * scalar, _z * scalar);
	}
	
	/**
	 * @return the size of this vector
	 */
	public double size()
	{
		double sizeSquared = dotProduct(this);
		return Math.sqrt(sizeSquared);
	}
	
	/**
	 * @return The x position of this vector
	 */
	public double getX()
	{
		return _x;
	}
	
	/**
	 * @return The y position of this vector
	 */
	public double getY()
	{
		return _y;
	}
	
	/**
	 * @return The z position of this vector
	 */
	public double getZ()
	{
		return _z;
	}
	
	/**
	 * Returns a string representation of this vector
	 */
	public String toString()
	{
		return String.format("(%.2f,%.2f,%.2f)", _x,_y,_z); 
	}
}
