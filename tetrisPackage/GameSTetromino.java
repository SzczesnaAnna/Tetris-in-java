package tetrisPackage;

public class GameSTetromino extends GameTetromino
{
	public GameSTetromino()
	{
		super();
		color = 5;
		shape = new int[2][3];
		fillPiece();
		shape[0][0] = 0;
		shape[1][2] = 0;
		type = TetrominoType.S;
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
						column += 1;
						shape = new int[3][2];
						fillPiece();
						shape[0][1] = 0;
						shape[2][0] = 0;
						orientation = Orientation.RIGHT;
						break;
					}
					case DOWN:
					{
						row -= 1;
						shape = new int[3][2];
						fillPiece();
						shape[0][1] = 0;
						shape[2][0] = 0;
						orientation = Orientation.LEFT;
						break;
					}
					case RIGHT:
					{
						row += 1;
						column -= 1;
						shape = new int[2][3];
						fillPiece();
						shape[0][0] = 0;
						shape[1][2] = 0;
						orientation = Orientation.DOWN;
						break;
					}
					case LEFT:
					{
						shape = new int[2][3];
						fillPiece();
						shape[0][0] = 0;
						shape[1][2] = 0;
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
						shape = new int[3][2];
						fillPiece();
						shape[0][1] = 0;
						shape[2][0] = 0;
						orientation = Orientation.LEFT;
						break;
					}
					case DOWN:
					{
						row -= 1;
						column += 1;
						shape = new int[3][2];
						fillPiece();
						shape[0][1] = 0;
						shape[2][0] = 0;
						orientation = Orientation.RIGHT;
						break;
					}
					case RIGHT:
					{
						column -= 1;
						shape = new int[2][3];
						fillPiece();
						shape[0][0] = 0;
						shape[1][2] = 0;
						orientation = Orientation.UP;
						break;
					}
					case LEFT:
					{
						row += 1;
						shape = new int[2][3];
						fillPiece();
						shape[0][0] = 0;
						shape[1][2] = 0;
						orientation = Orientation.DOWN;
						break;
					}
				}
				break;
			}
		}
	}
}