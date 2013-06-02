package Ex3PCA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * The main class which generates the scene image
 */
public class PointCloudRenderer 
{
	/** An epsilon value used for the computatoin of soft shadows*/
	private static final double EPSILON = 0.00001;
	/** The scene height in pixels */
	private int _height;
	/** The scene width in pixels */
	private int _width;
	/** The camera object */
	private Camera _camera;
	/** The scene settings */
	private Settings _settings;
	/** The list of materials used in the scene */
	private double _pixelPointRatio;
	
	private double _screenHeight;
	private ViewPlane _cameraViewPlane;
	private ArrayList<PointCloud> _clouds;
	/** The scene height in pixels */
	private double _heightDouble;
	/** The scene width in pixels */
	private double _widthDouble;
	
	/**
	 * Creates a new scene generator object
	 * @param camera The camera
	 * @param settings The scene settings
	 * @param materials The list of materials for the scene
	 * @param surfaces The list of surfaces for the scene
	 * @param lights The list of lights for the scene
	 * @param width The scene width, in pixels
	 * @param height The scene height, in pixels
	 */
	public PointCloudRenderer(Camera camera, Settings settings, ArrayList<PointCloud> clouds, int width, int height)
	{
		_height = height;;
		_width = width;
		_heightDouble = (double) height;
		_widthDouble = (double) width;
		_camera =  camera;
		_settings = settings;
		_clouds = clouds;
		_cameraViewPlane = new ViewPlane(_camera);
		_pixelPointRatio = _camera.getScreenWidth() / (double)_width;
		_screenHeight = (double)_height * _pixelPointRatio;
	}
	
	/**
	 * The main loop. Renders the scene and returns a multidimensional array color objects containing the image
	 * @return The image data
	 */
	public Color[][] renderPointCloud()
	{
		Color[][] imageData = new Color[_height][_width];
		
		for (PointCloud cloud : _clouds)
		{
			for (Point p : cloud.getPoints())
			{
				Pixel pixel = project3DPointTo2D(p.getPoint());
				if (pixel != null)
				{
					// TODO: use the size parameter
					imageData[pixel.getY()][pixel.getX()] = new Color(cloud.getColor());
				}
			}
		}
		
		return imageData;
	}
	
	private Pixel project3DPointTo2D(Vector3D point)
	{
		Vector3D pointCameraRay = point.sub(_camera.getPosition());
		double ratio = pointCameraRay.dotProduct(_camera.getDirection()) / _camera.getScreenDistance();
		Vector3D projection = pointCameraRay.multByScalar(1.0 / ratio).add(_camera.getPosition());
		// TODO: maybe calculate in the other direction
		Vector3D betweenVector = projection.sub(_cameraViewPlane.getCenter());
		double xAxisDistance = betweenVector.dotProduct(_cameraViewPlane.getVx());
		double widthRatio = xAxisDistance / _camera.getScreenWidth();
		
		if (widthRatio < -1 || widthRatio > 1)
		{
			return null;
		}
		
		int xCoordinate = (int) ((widthRatio + 1.0)* _widthDouble - _widthDouble/2.0);
		
		double yAxisDistance = betweenVector.dotProduct(_cameraViewPlane.getVy());
		double heightRatio = yAxisDistance / _screenHeight;
		
		if (heightRatio < -1 || heightRatio > 1)
		{
			return null;
		}
		
		int yCoordinate = (int) (((heightRatio + 1.0) * _heightDouble) - _heightDouble/2.0);
		
		if (xCoordinate < 0 || yCoordinate < 0 || xCoordinate >= _width || yCoordinate >= _height )
		{
			return null;
		}
		
		double distance = pointCameraRay.size();
		return new Pixel(xCoordinate, yCoordinate, distance);
	}
}

