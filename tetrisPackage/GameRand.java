package tetrisPackage;

public class GameRand
{
	static int timesCalled = 0;
	static int randomBonus = 0;
	
	static TetrominoType MyRand()
	{
		timesCalled++;
		boolean repeat = true;
		while(repeat)
		{
			repeat = false;
			switch (shift())
			{
				case 0:
					return TetrominoType.I;
				case 1:
					return TetrominoType.L;
				case 2:
					return TetrominoType.J;
				case 3:
					return TetrominoType.T;
				case 4:
					return TetrominoType.S;
				case 5:
					return TetrominoType.Z;
				case 6:
					return TetrominoType.O;
				default:
				{
					repeat = true;
				}
			}
		}
		return null;
	}
	
	static int shift()
	{
		int v = timesCalled;
		if(randomBonus > 0)
			v += randomBonus;
		v ^= (v << 7);
		v ^= (v >> 11);
		v ^= (v << 5);
		return v%7;
	}
}