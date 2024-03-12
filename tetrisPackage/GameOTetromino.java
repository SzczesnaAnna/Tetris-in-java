package tetrisPackage;

public class GameOTetromino extends GameTetromino
{
	public GameOTetromino()
	{
		super();
		color = 4;
		shape = new int[2][2];
		fillPiece();
		type = TetrominoType.O;
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
						orientation = Orientation.RIGHT;
						break;
					}
					case DOWN:
					{
						orientation = Orientation.LEFT;
						break;
					}
					case RIGHT:
					{
						orientation = Orientation.DOWN;
						break;
					}
					case LEFT:
					{
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
						orientation = Orientation.LEFT;
						break;
					}
					case DOWN:
					{
						orientation = Orientation.RIGHT;
						break;
					}
					case RIGHT:
					{
						orientation = Orientation.UP;
						break;
					}
					case LEFT:
					{
						orientation = Orientation.DOWN;
						break;
					}
				}
				break;
			}
		}
	}
}