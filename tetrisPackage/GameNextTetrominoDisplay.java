package tetrisPackage;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GameNextTetrominoDisplay extends JPanel
{
	JPanel[][] display;
	public int chooseLang = Menu.language;
	public GameNextTetrominoDisplay()
	{
		setLayout(new GridLayout(2,1));
		JLabel nextPieceTitle = new JLabel("Next Piece");
		
		if(chooseLang==1){
			nextPieceTitle.setText("Next Piece"); repaint();}
		else if(chooseLang==2){nextPieceTitle.setText("Następny klocek"); repaint();}
		
		nextPieceTitle.setHorizontalAlignment(SwingConstants.CENTER);
		nextPieceTitle.setVerticalAlignment(SwingConstants.CENTER);
		nextPieceTitle.setFont(UsedFonts.fontGame2);
		JPanel nextPieceDisplay = new JPanel(new GridLayout(2,5,1,1));
		nextPieceDisplay.setBackground(getBackground());
		display = new JPanel[2][5];
		for(int i = 0; i < display.length; i++)
		{
			for(int j = 0; j < display[i].length; j++)
			{
				display[i][j] = new JPanel();
				nextPieceDisplay.add(display[i][j]);
			}
		}
		setSize(200, 100);
		add(nextPieceTitle);
		add(nextPieceDisplay);
	}
	
	public void displayColor(GameTetromino p)
	{
		for(int i = 0; i < display.length; i++)
		{
			for(int j = 0; j < display[i].length; j++)
			{
				display[i][j].setBackground(getBackground());
			}
		}
		for(int i = 0; i < p.shape.length; i++)
		{
			for(int j = 0; j < p.shape[i].length; j++)
			{
				if(p.shape[i][j] != 0)
					if(p.shape.length == 1)
						TileColor.tileColor(display[i][j], p.shape[i][j]);
					else
						TileColor.tileColor(display[i][j+1], p.shape[i][j]);
			}
		}
	}
}