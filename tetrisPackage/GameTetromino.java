package tetrisPackage;

abstract class GameTetromino
{
	int row;
	int column;
	int color;
	int[][] shape;
	Orientation orientation;
	TetrominoType type;

	public GameTetromino()
	{
		row = 0;
		column = 0;
		orientation = Orientation.UP;
	}
	
	public boolean checkSpace(int r, int c, int[][] d)
	{
		if(d[r][c] == 0)
			return true;
		else
			return false;
	}

	public void move(Orientation o, int r, int c, int[][] d)
	{
		switch(o)
		{
			case DOWN:
			{
				d[r+1][c] = d[r][c];
				d[r][c] = 0;
				break;
			}
			case UP:
			{
				d[r-1][c] = d[r][c];
				d[r][c] = 0;
				break;
			}
			case RIGHT:
			{
				d[r][c+1] = d[r][c];
				d[r][c] = 0;
				break;
			}
			case LEFT:
			{
				d[r][c-1] = d[r][c];
				d[r][c] = 0;
				break;
			}
		}
	}
	
	public boolean checkPiece(Orientation o, int[][] d)
	{
		switch(o)
		{
			case DOWN:
			{
				for(int j = 0; j < shape[shape.length-1].length; j++)
				{
					for(int i = shape.length-1; i > -1; i--)
					{
						if(shape[i][j] != 0)
						{
							if(row+shape.length > d.length-3 || !checkSpace(row+i+1, column+j, d))
								return false;
							else
								break;
						}
					}
				}
				return true;
			}
			case UP:
			{
				for(int j = 0; j < shape[shape.length-1].length; j++)
				{
					for(int i = 0; i < shape.length; i++)
					{
						if(shape[i][j] != 0)
						{
							if(row < 3 || !checkSpace(row+i-1, column+j, d))
								return false;
							else
								break;
						}
					}
				}
				return true;
			}
			case RIGHT:
			{
				for(int i = 0; i < shape.length; i++)
				{
					for(int j = shape[i].length-1; j > -1; j--)
					{
						if(shape[i][j] != 0)
						{
							if(column+shape[i].length > d[i].length-3 || !checkSpace(row+i, column+j+1, d))
								return false;
							else
								break;
						}
					}
				}
				return true;
			}
			case LEFT:
			{
				for(int i = 0; i < shape.length; i++)
				{
					for(int j = 0; j < shape[i].length; j++)
					{
						if(shape[i][j] != 0)
						{
							if(column < 3 || !checkSpace(row+i, column+j-1, d))
								return false;
							else
								break;
						}
					}
				}
				return true;
			}
			default:
				return false;
		}
	}

	public void movePiece(Orientation o, int[][] d)
	{
		switch(o)
		{
			case DOWN:
			{
				for(int j = 0; j < shape[shape.length-1].length; j++)
				{
					for(int i = shape.length-1; i > -1; i--)
					{
						if(shape[i][j] != 0)
						{
							move(o, row+i, column+j, d);
						}
					}
				}
				row++;
				break;
			}
			case UP:
			{
				for(int j = 0; j < shape[shape.length-1].length; j++)
				{
					for(int i = 0; i < shape.length; i++)
					{
						if(shape[i][j] != 0)
						{
							move(o, row+i, column+j, d);
						}
					}
				}
				row--;
				break;
			}
			case RIGHT:
			{
				for(int i = 0; i < shape.length; i++)
				{
					for(int j = shape[i].length-1; j > -1; j--)
					{
						if(shape[i][j] != 0)
						{
							move(o, row+i, column+j, d);
						}
					}
				}
				column++;
				break;
			}
			case LEFT:
			{
				for(int i = 0; i < shape.length; i++)
				{
					for(int j = 0; j < shape[i].length; j++)
					{
						if(shape[i][j] != 0)
						{
							move(o, row+i, column+j, d);
						}
					}
				}
				column--;
				break;
			}
		}
	}
	
	public void fillPiece()
	{
		for(int i = 0; i < shape.length; i++)
		{
			for(int j = 0; j < shape[i].length; j++)
			{
				shape[i][j] = color;
			}
		}
	}
	
	abstract void rotate(Rotation r);
}