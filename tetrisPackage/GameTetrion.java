package tetrisPackage;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
*	Class {@code GameTetrion} is a class that displays the game board,
*	that consists of color changing squares on a grid called Tetrion.
*	@author Mako
*	@version 0.9.5
*/

@SuppressWarnings("serial")
public class GameTetrion extends JPanel
{
	/**
	 *2D matrix storing tiles making up the Tetrion.
	 *	@since 0.1.1
	 */
	JPanel[][] tiles;
	
	/**
	 *	Default constructor. Initializes the board with empty tiles.
	 *	@since 0.1.1
	 */
	public GameTetrion()
	{
		setLayout(new GridLayout(20,10,1,1));
		setBackground(Color.gray);
		tiles = new JPanel[20][10];
		for(int i = 0; i < tiles.length; i++)
		{
			for(int j = 0; j < tiles[i].length; j++)
			{
				tiles[i][j] = new JPanel();
				add(tiles[i][j]);
			}
		}
	}
	
	/**
	 *	Method painting tiles based on data provided.
	 *	@param gdata instance of class for storing data about game state.
	 *	@since 0.2.1
	 *	@see tetrisPackage.GameData#data
	 *	@see tetrisPackage.TileColor#tileColor(JPanel, int)
	 */
	public void tilesColor(GameData gdata)
	{
		for(int i = 0; i < tiles.length; i++)
		{
			for(int j = 0; j < tiles[i].length; j++)
			{
				TileColor.tileColor(tiles[i][j], gdata.data[i+4][j+2]);
			}
		}
		for(int i = 0; i < gdata.currentTetromino.shape.length; i++)
		{
			for(int j = 0; j < gdata.currentTetromino.shape[i].length; j++)
			{
				if(gdata.currentTetromino.row>3) 
				{
					if(gdata.currentTetromino.shape[i][j] != 0)
					{
						TileColor.tileHalfColor(tiles[gdata.ghost()-4+i][gdata.currentTetromino.column+j-2], gdata.data[gdata.currentTetromino.row+i][gdata.currentTetromino.column+j]);
					}
				}
			}
		}
		if(gdata.currentTetromino.row > 3)
		{
			for(int i = 0; i < gdata.currentTetromino.shape.length; i++)
			{
				for(int j = 0; j < gdata.currentTetromino.shape[i].length; j++)
				{
					if(gdata.currentTetromino.shape[i][j] != 0)
					{
						TileColor.tileColor(tiles[gdata.currentTetromino.row-4+i][gdata.currentTetromino.column+j-2], gdata.data[gdata.currentTetromino.row+i][gdata.currentTetromino.column+j]);
					}
				}
			}
		}
	}
}