package tetrisPackage;

/**
*	Class {@code GameData} is a class that stores the game state data,
*	that consists of: board state, score, level and game pieces.
*	@author Mako
*	@version 0.9.5
*/

public class GameData
{
	/**
	 *	2D matrix storing the state of the Tetrion (board).
	 *	@since 0.1.0
	 */
	int[][] data;
	/**
	 *	Total points earned in current play session.
	 *	@since 0.7.8
	 */
	int score;
	/**
	 *	Indicator for the speed, and by extension difficulty, of the game.
	 *	@since 0.8.9
	 */
	int level;
	/**
	 *	A counter used to calculate the level.
	 *	@since 0.1.0
	 */
	int totalLinesCleared;
	/**
	 *	Marker signaling to upper layers of code when a level up is meant to occur.
	 *	@since 0.4.5
	 */
	boolean levelUp;
	/**
	 *	Marker signaling to upper layers of code if the current piece has not been swapped yet.
	 *	@since 0.4.5
	 */
	boolean canSwap;
	/**
	 *	Tetromino (game piece) currently controlled by the player.
	 *	@since 0.4.6
	 */
	GameTetromino currentTetromino;
	/**
	 *	Tetromino that will appear after the current one stops moving.
	 *	@since 0.6.8
	 */
	GameTetromino nextTetromino;
	/**
	 *	Tetromino that can be swapped with the current one.
	 *	@since 0.9.5
	 */
	GameTetromino storedTetromino;
	
	/**
	 *	Default constructor. Initializes the matrix with empty tiles.
	 *	@since 0.1.0
	 */
	public GameData()
	{
		data = new int[26][14];
		for(int i = 0; i < data.length; i++)
		{
			for(int j = 0; j < data[i].length; j++)
			{
				data[i][j] = 0;
			}
		}
		score = 0;
		level = 1;
		totalLinesCleared = 0;
		levelUp = false;
		canSwap = true;
	}
	
	
	/**
	 *	Method adding a given tetromino to the Tetrion.
	 *	@param p tetromino to be added.
	 *	@since 0.3.4
	 */
	public void addPiece(GameTetromino p)
	{
		for(int i = 0; i < p.shape.length; i++)
		{
			for(int j = 0; j < p.shape[i].length; j++)
			{
				if(p.shape[i][j] != 0)
					data[p.row+i][p.column+j] = p.shape[i][j];
			}
		}
	}
	
	
	/**
	 *	Method creating next tetromino and placing it in its starting position.
	 *	@since 0.3.4
	 */
	public void spawnPiece()
	{
		currentTetromino = nextTetromino;
		if(currentTetromino.shape.length == 1)
		{
			currentTetromino.row = 3;
			currentTetromino.column = 5;
		}
		else
		{
			currentTetromino.row = 2;
			currentTetromino.column = 6;
		}
		addPiece(currentTetromino);
		nextTetromino = choosePiece(GameRand.MyRand());
	}
	
	
	/**
	 *	Method removing a given tetromino from the Tetrion.
	 *	@param p tetromino to be removed.
	 *	@since 0.4.1
	 */
	public void removePiece(GameTetromino p)
	{
		for(int i = 0; i < p.shape.length; i++)
		{
			for(int j = 0; j < p.shape[i].length; j++)
			{
				if(p.shape[i][j] != 0)
					data[p.row+i][p.column+j] = 0;
			}
		}
	}
	
	
	/**
	 *	Method checking if a given row in Tetrion is completely filled.
	 *	@param r row to be checked.
	 *	@return true if every tile in the row is occupied, false if else.
	 *	@since 0.3.7
	 */
	public boolean checkFullRow(int r)
	{
		for(int i = 2; i < data[r].length-2; i++)
		{
			if(data[r][i] != 0)
				continue;
			else
				return false;
		}
		return true;
	}
	
	
	/**
	 *	Method checking if current tetromino is overlapping with other non-empty tiles.
	 *	@param row from which overlap is checked.
	 *	@param column from which overlap is checked.
	 *	@return true if the tetromino is overlapping, false if else.
	 */
	public boolean checkOverlap(int row, int column)
	{
		for(int i = 0; i < currentTetromino.shape.length; i++)
		{
			for(int j = 0; j < currentTetromino.shape[i].length; j++)
			{
				if(currentTetromino.shape[i][j] != 0)
				{
					if(data[row+i][column+j] != 0)
						return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 *	Method moving a given row one tile down.
	 *	@param r row to be moved.
	 *	@since 0.3.7
	 */
	public void dropRow(int r)
	{
		for(int i = 2; i < data[r].length-2; i++)
		{
			data[r+1][i] = data[r][i];
			data[r][i] = 0;
		}
	}
	
	
	/**
	 *	Method clearing full rows, rewarding points, calculating level ups and signaling level ups.
	 *	@since 0.7.8
	 */
	public void deleteRows()
	{
		int linesCleared = 0;
		for(int i = data.length-3; i > 3; i--)
		{
			if(checkFullRow(i))
			{
				linesCleared++;
				for(int j = i-1; j > 2; j--)
					dropRow(j);
				i++;
			}
		}
		switch(linesCleared)
		{
			case 1:
			{
				score += 100*(level);
				break;
			}
			case 2:
			{
				score += 300*(level);
				break;
			}case 3:
			{
				score += 500*(level);
				break;
			}case 4:
			{
				score += 800*(level);
				break;
			}
		}
		totalLinesCleared += linesCleared;
		if(totalLinesCleared > 10*(level)-1)
		{
			level++;
			levelUp = true;
		}
		linesCleared = 0;
	}
	
	
	/**
	 *	Method selecting tetromino constructors based on their type.
	 *	@param type chosen type of new tetromino.
	 *	@return constructor for chosen tetromino.
	 *	@since 0.6.0
	 */
	public GameTetromino choosePiece(TetrominoType type)
	{
		switch(type)
		{
			case I:
				return new GameITetromino();
			case L:
				return new GameLTetromino();
			case J:
				return new GameJTetromino();
			case T:
				return new GameTTetromino();
			case S:
				return new GameSTetromino();
			case Z:
				return new GameZTetromino();
			case O:
				return new GameOTetromino();
			default:
				return null;
		}
	}
	
	
	/**
	 *	Method reversing the last rotation of current tetromino.
	 *	@param r direction of last rotation.
	 *	@since 0.8.4
	 *	@see tetrisPackage.GameData#rotatePiece(Rotation)
	 */
	public void counterRotate(Rotation r)
	{
		switch(r)
		{
			case CLOCKWISE:
			{
				currentTetromino.rotate(Rotation.COUNTERCLOCKWISE);
				break;
			}
			case COUNTERCLOCKWISE:
			{
				currentTetromino.rotate(Rotation.CLOCKWISE);
				break;
			}
		}
	}
	
	
	/**
	 *	Method rotating current tetromino in a given direction.
	 *	@param r direction of rotation.
	 *	@since 0.8.2
	 */
	public void rotatePiece(Rotation r)
	{
		if(currentTetromino.type == TetrominoType.O)
		{
			currentTetromino.rotate(r);
			return;
		}
		int tmpRow = currentTetromino.row;
		int tmpColumn = currentTetromino.column;
		
		removePiece(currentTetromino);
		currentTetromino.rotate(r);
		
		if(currentTetromino.row < 2)
			currentTetromino.row = 2;
		else if(currentTetromino.row+currentTetromino.shape.length > data.length-2)
			currentTetromino.row = data.length-2 - currentTetromino.shape.length;
		
		if(currentTetromino.column < 2)
			currentTetromino.column = 2;
		else if(currentTetromino.column+currentTetromino.shape[currentTetromino.shape.length-1].length > data[data.length-1].length-2)
			currentTetromino.column = data[data.length-1].length-2 - currentTetromino.shape[currentTetromino.shape.length-1].length;
		
		if(checkOverlap(currentTetromino.row, 0))
		{
			tryAround:
			{
				if(currentTetromino.column != 2)
				{
					if(checkOverlap(currentTetromino.row, currentTetromino.column-1))
					{
						currentTetromino.column--;
						break tryAround;
					}
				}
				if(currentTetromino.column+currentTetromino.shape[currentTetromino.shape.length-1].length != data[data.length-1].length-2)
				{
					if(checkOverlap(currentTetromino.row, currentTetromino.column+1))
					{
						currentTetromino.column++;
						break tryAround;
					}
				}
				if(currentTetromino.row+currentTetromino.shape.length != data.length-2)
				{
					if(checkOverlap(currentTetromino.row+1, currentTetromino.column))
					{
						currentTetromino.row++;
						break tryAround;
					}
				}
				if(currentTetromino.row != 2)
				{
					if(checkOverlap(currentTetromino.row-1, currentTetromino.column))
					{
						currentTetromino.row--;
						break tryAround;
					}
				}
				counterRotate(r);
				currentTetromino.row =  tmpRow;
				currentTetromino.column = tmpColumn;
			}
		}
		addPiece(currentTetromino);
	}
	
	
	/**
	 *	Method moving current tetromino in a given direction.
	 *	@param o direction to move the tetromino.
	 *	@since 0.6.9
	 */
	public void movePiece(Orientation o)
	{
		if(currentTetromino.checkPiece(o, data))
			currentTetromino.movePiece(o, data);
	}
	
	
	
	/**
	 *	Method defining the game end condition.
	 *	@return true if the player lost the game, false if else.
	 *	@since 0.9.1
	 */
	public boolean checkGameOver()
	{
		if(currentTetromino.row < 4 && !currentTetromino.checkPiece(Orientation.DOWN, data))
			return true;
		else
			return false;
	}
	
	
	/**
	 *	Method checking the row of the ghost tetromino.
	 *	@return the row of the ghost tetromino.
	 *	@since 0.9.5
	 */
	public int ghost()
	{
		int tmpRow = currentTetromino.row;
		while(currentTetromino.checkPiece(Orientation.DOWN, data))
		{
			movePiece(Orientation.DOWN);
		}
		int row = currentTetromino.row;
		while(currentTetromino.row != tmpRow)
		{
			movePiece(Orientation.UP);
		}
		return row;
	}
	
	
	/**
	 *	Method swapping current tetromino with the stored one.
	 *	@since 0.9.5
	 */
	public void swap()
	{
		if(canSwap)
		{
			if(storedTetromino == null)
			{
				storedTetromino = choosePiece(currentTetromino.type);
				removePiece(currentTetromino);
				spawnPiece();
			}
			else
			{
				TetrominoType tmp = currentTetromino.type;
				removePiece(currentTetromino);
				currentTetromino = storedTetromino;
				if(currentTetromino.shape.length == 1)
				{
					currentTetromino.row = 3;
					currentTetromino.column = 5;
				}
				else
				{
					currentTetromino.row = 2;
					currentTetromino.column = 6;
				}
				addPiece(currentTetromino);
				storedTetromino = choosePiece(tmp);
			}
			canSwap = false;
		}
	}
}