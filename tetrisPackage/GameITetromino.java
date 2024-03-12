package tetrisPackage;

public class GameITetromino extends GameTetromino
{
	public GameITetromino()
	{
		super();
		color = 6;
		shape = new int[1][4];
		fillPiece();
		type = TetrominoType.I;
	}

	@Override
	void rotate(Rotation r)
	{
		switch(r)
		{
			case CLOCKWISE:
			{
				switch(orientation)
				{
					case UP:
					{
						row -= 2;
						column += 1;
						shape = new int[4][1];
						fillPiece();
						orientation = Orientation.RIGHT;
						break;
					}
					case DOWN:
					{
						row -= 1;
						column += 2;
						shape = new int[4][1];
						fillPiece();
						orientation = Orientation.LEFT;
						break;
					}
					case RIGHT:
					{
						row += 1;
						column -= 1;
						shape = new int[1][4];
						fillPiece();
						orientation = Orientation.DOWN;
						break;
					}
					case LEFT:
					{
						row += 2;
						column -= 2;
						shape = new int[1][4];
						fillPiece();
						orientation = Orientation.UP;
						break;
					}
				}
				break;
			}
			case COUNTERCLOCKWISE:
			{
				switch(orientation)
				{
					case UP:
					{
						row -= 2;
						column += 2;
						shape = new int[4][1];
						fillPiece();
						orientation = Orientation.LEFT;
						break;
					}
					case DOWN:
					{
						row -= 1;
						column += 1;
						shape = new int[4][1];
						fillPiece();
						orientation = Orientation.RIGHT;
						break;
					}
					case RIGHT:
					{
						row += 2;
						column -= 1;
						shape = new int[1][4];
						fillPiece();
						orientation = Orientation.UP;
						break;
					}
					case LEFT:
					{
						row += 1;
						column -= 2;
						shape = new int[1][4];
						fillPiece();
						orientation = Orientation.DOWN;
						break;
					}
				}
				break;
			}
		}
	}
}