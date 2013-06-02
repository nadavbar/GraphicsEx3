package Ex3PCA;

/**
 * Holds the global settings of the scene
 */
public class Settings 
{
	/** The background color*/
	private Color _background;
	/** The number of shadow rays*/
	private int _shadowRays;
	/** The recursion level limitation*/
	private int _recursionLevel;
	
	/**
	 * Creates a new settings object
	 * @param backColor
	 * @param shadowRays
	 * @param recursionLevel
	 */
	public Settings(Color backColor, int shadowRays, int recursionLevel)
	{
		_background = backColor;
		_shadowRays = shadowRays;
		_recursionLevel = recursionLevel;
	}
	
	/**
	 * 
	 * @return The background color
	 */
	public Color getBackColor()
	{
		return _background;
	}
	
	/**
	 * 
	 * @return The number of shadow rays
	 */
	public int getShadowRays()
	{
		return _shadowRays;
	}
	
	/**
	 * 
	 * @return The recursion level
	 */
	public int getRecursionLevel()
	{
		return _recursionLevel;
	}
}
