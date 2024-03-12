package tetrisPackage;

import java.awt.Color;
import javax.swing.JPanel;

/**
*	Record {@code TileColor} is a class that stores the colors corresponding to values in {@code GameData}.
*	@author Mako
*	@version 0.9.5
*	@see tetrisPackage.GameData#data
*/

public record TileColor()
{
	/**
	 *	Method matching the color of a given tile to the corresponding data.
	 *	@param p tile to be colored.
	 *	@param c data value dictating color.
	 */
	public static void tileColor(JPanel p, int c)
	{
		switch(c)
		{
			case 1:
			{
				p.setBackground(Color.magenta);
				break;
			}
			case 2:
			{
				p.setBackground(Color.red);
				break;
			}
			case 3:
			{
				p.setBackground(Color.orange);
				break;
			}
			case 4:
			{
				p.setBackground(Color.yellow);
				break;
			}
			case 5:
			{
				p.setBackground(Color.green);
				break;
			}
			case 6:
			{
				p.setBackground(Color.cyan);
				break;
			}
			case 7:
			{
				p.setBackground(Color.blue);
				break;
			}
			default:
			{
				p.setBackground(Color.lightGray);
			}
		}
	}

	public static void tileHalfColor(JPanel p, int c)
	{
		switch(c)
		{
			case 1:
			{
				p.setBackground(new Color(255, 170, 255));
				break;
			}
			case 2:
			{
				p.setBackground(new Color(255, 170, 170));
				break;
			}
			case 3:
			{
				p.setBackground(new Color(255, 200, 170));
				break;
			}
			case 4:
			{
				p.setBackground(new Color(255, 255, 170));
				break;
			}
			case 5:
			{
				p.setBackground(new Color(170, 255, 170));
				break;
			}
			case 6:
			{
				p.setBackground(new Color(170, 255, 255));
				break;
			}
			case 7:
			{
				p.setBackground(new Color(170, 170, 255));
				break;
			}
			default:
			{
				p.setBackground(new Color(192, 192, 192));
			}
		}
	}
}