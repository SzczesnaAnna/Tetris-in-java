package tetrisPackage;

import java.awt.Font;

public record UsedFonts()
{
	static int fontMenuSize = 35;
    static Font fontMenu = new Font("Monospaced", Font.BOLD, fontMenuSize);
	static int fontMenuSize2 = 20;
	static Font fontMenu2 = new Font("Monospaced", Font.BOLD, fontMenuSize2);
	
	static int fontGameSize = 28;
	static Font fontGame = new Font("Monospaced", Font.BOLD, fontGameSize);
	static int fontGameSize2 = 20;
	static Font fontGame2 = new Font("Monospaced", Font.BOLD, fontGameSize2);
	static int fontGameSize3 = 7;
	static Font fontGame3 = new Font("Monospaced", Font.BOLD ,fontGameSize2);
}