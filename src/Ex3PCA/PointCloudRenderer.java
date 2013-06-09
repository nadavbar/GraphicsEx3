package Ex3PCA;

import java.util.ArrayList;

/**
 * The main class which generates point cloud image
 */
public class PointCloudRenderer 
{	
	/** The scene height in pixels */
	private int _height;
	/** The scene width in pixels */
	private int _width;
	/** The camera object */
	private Camera _camera;
	/** The list of materials used in the scene */
	private double _pixelPointRatio;
	
	private double _screenHeight;
	private ViewPlane _cameraViewPlane;
	private ArrayList<PointCloud> _clouds;
	/** The scene height in pixels, in double - used for faster calculation */
	private double _heightDouble;
	/** The scene width in pixels - used for faster calculation */
	private double _widthDouble;
	
	/**
	 * Creates a new scene generator object
	 * @param camera The camera
	 * @param clouds The list of clouds to generate the image for
	 * @param width The scene width, in pixels
	 * @param height The scene height, in pixels
	 */
	public PointCloudRenderer(Camera camera, ArrayList<PointCloud> clouds, int width, int height)
	{
		_height = height;;
		_width = width;
		_heightDouble = (double) height;
		_widthDouble = (double) width;
		_camera =  camera;
		_clouds = clouds;
		_cameraViewPlane = new ViewPlane(_camera);
		_pixelPointRatio = _camera.getScreenWidth() / (double)_width;
		_screenHeight = (double)_height * _pixelPointRatio;
	}
	
	/**
	 * The main loop. Renders the image and returns a multidimensional array color objects containing the image
	 * @return The image data
	 */
	public Color[][] renderPointCloud()
	{
		Color[][] imageData = new Color[_height][_width];
		
		for (PointCloud cloud : _clouds)
		{
			PointCloud boundingBoxCloud = cloud.calculateBoundingBox();
			// draw the bounding box
			drawCloud(boundingBoxCloud, imageData);
			
			// print the bounding box
			for (Point point : boundingBoxCloud.getPoints())
			{
				System.out.println(point.getPoint().toString());
			}
			
			// draw the point cloud
			drawCloud(cloud, imageData);
			
		}
		
		return imageData;
	}
	
	/**
	 * Draws the given point cloud on the image data
	 * @param cloud The cloud to draw
	 * @param imageData The image data to draw ons
	 */
	private void drawCloud(PointCloud cloud, Color[][] imageData)
	{
		for (Point p : cloud.getPoints())
		{
			Pixel pixel = project3DPointTo2D(p.getPoint());
			if (pixel != null)
			{	
				imageData[pixel.getY()][pixel.getX()] = cloud.getColor();
				
				int radius = (int) Math.ceil((cloud.getSize() / pixel.getDistance())/2.0);
				radius = Math.max(1, radius);

				// draws a square around the pixel, according to it's size
				for (int i= (pixel.getX() - radius); i < (pixel.getX() + radius); i++)
				{
					for (int j= (pixel.getY() - radius); j < (pixel.getY() + radius); j++)
					{
						if (i>= 0 && i < _width && j>=0 && j < _height)
						{
							imageData[j][i] = new Color(cloud.getColor());
						}
					}
				}
			}
		}
	}
	
	/**
	 * Projects a point from the 3D space to the 2D space
	 * @param point The point to project
	 * @return A 2d pixel, represents the projected point
	 */
	private Pixel project3DPointTo2D(Vector3D point)
	{
		// The code here follows the instructions given in the exercise spec:
		
		Vector3D pointCameraRay = point.sub(_camera.getPosition());
		
		double ratio = pointCameraRay.dotProduct(_camera.getDirection()) / _camera.getScreenDistance();
		Vector3D projection = pointCameraRay.multByScalar(1.0 / ratio);
		Vector3D betweenVector = projection.sub(_cameraViewPlane.getCenter());
		double xAxisDistance = betweenVector.dotProduct(_cameraViewPlane.getVx());
		double widthRatio = xAxisDistance / _camera.getScreenWidth();
		
		if (widthRatio < -1 || widthRatio > 1)
		{
			return null;
		}
		double transformedRatio = (widthRatio + 1.0)/2.0;
		int xCoordinate = (int) (transformedRatio * _widthDouble);
		
		double yAxisDistance = betweenVector.dotProduct(_cameraViewPlane.getVy());
		double heightRatio = -yAxisDistance / _screenHeight;
		
		if (heightRatio < -1 || heightRatio > 1)
		{
			return null;
		}
		
		transformedRatio = (heightRatio + 1.0) / 2.0;
		int yCoordinate = (int) (_heightDouble * transformedRatio);
		
		if (xCoordinate < 0 || yCoordinate < 0 || xCoordinate >= _width || yCoordinate >= _height )
		{
			return null;
		}
		
		double distance = pointCameraRay.size();
		return new Pixel(xCoordinate, yCoordinate, distance);
	}
}

