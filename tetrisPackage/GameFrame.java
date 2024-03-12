package tetrisPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameFrame extends JFrame
{
	GameTetrion mpanel;
	GameSidePanel spanel;
	GameData gdata;
	int timeFrame; // game updates itself every frame, this value is how many milliseconds are in 1 frame
	double updateCounter;
	ActionListener timerTask;
	Timer gameTimer;
	JPanel contentPanel;
	InputMap inputMap;
	ActionMap actionMap;
	public int chooseLang = Menu.language;
	
	public GameFrame(boolean withTime) throws HeadlessException
	{
		this.setSize(700,650);
		this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.black);
		setLocationRelativeTo(null);
		
		
		
		mpanel = new GameTetrion();
		mpanel.setPreferredSize(new Dimension(400, 640));
		add(mpanel, BorderLayout.LINE_START);
		
		spanel = new GameSidePanel(withTime);
		spanel.setPreferredSize(new Dimension(250, 640));
		add(spanel, BorderLayout.LINE_END);
		
		gdata = new GameData();
		setVisible(true);
		gdata.nextTetromino = gdata.choosePiece(GameRand.MyRand());
		gdata.spawnPiece();
		spanel.nextTetrominoDisplay.displayColor(gdata.nextTetromino);
		
		timeFrame = 17; // should be 1000/60 or 16.(6) , but it has to be an integer so it's rounded up
		updateCounter = 0;
		timerTask = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(!spanel.paused)
				{
					updateCounter += calculateGameSpeed();
					while(updateCounter > 1)
					{
						updateCounter--;
						updateGame();
					}
					mpanel.tilesColor(gdata);
				}
			}
		};
		gameTimer = new Timer(timeFrame, timerTask);
		gameTimer.start();
		
		contentPanel = (JPanel) this.getContentPane();
		inputMap = contentPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		actionMap = contentPanel.getActionMap();
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "Left");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "Right");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "Down");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0), "Drop");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0), "Rotate Left");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0), "Rotate Right");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "Swap");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "Pause");
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Esc");
		actionMap.put("Left", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(!spanel.paused)
				{
					gdata.movePiece(Orientation.LEFT);
					mpanel.tilesColor(gdata);
					GameRand.randomBonus--;
				}
			}
		});
		actionMap.put("Right", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(!spanel.paused)
				{
					gdata.movePiece(Orientation.RIGHT);
					mpanel.tilesColor(gdata);
					GameRand.randomBonus++;
				}
			}
		});
		actionMap.put("Down", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(!spanel.paused)
				{
					gdata.movePiece(Orientation.DOWN);
					gdata.score++;
					spanel.scoreText.setText(""+gdata.score);
					mpanel.tilesColor(gdata);
				}
			}
		});
		actionMap.put("Drop", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(!spanel.paused)
				{
					while(gdata.currentTetromino.checkPiece(Orientation.DOWN, gdata.data))
					{
						gdata.movePiece(Orientation.DOWN);
						gdata.score += 2;
					}
					spanel.scoreText.setText(""+gdata.score);
					mpanel.tilesColor(gdata);
					updateGame();
				}
			}
		});
		actionMap.put("Rotate Left", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(!spanel.paused && gdata.currentTetromino.row > 1)
				{
					gdata.rotatePiece(Rotation.COUNTERCLOCKWISE);
					mpanel.tilesColor(gdata);
					GameRand.randomBonus -= 2;
				}
			}
		});
		actionMap.put("Rotate Right", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(!spanel.paused && gdata.currentTetromino.row > 1)
				{
					gdata.rotatePiece(Rotation.CLOCKWISE);
					mpanel.tilesColor(gdata);
					GameRand.randomBonus += 2;
				}
			}
		});
		actionMap.put("Swap", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if(!spanel.paused)
				{
					gdata.swap();
					spanel.storedTetrominoDisplay.displayColor(gdata.storedTetromino);
					mpanel.tilesColor(gdata);
				}
			}
		});
		actionMap.put("Pause", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				spanel.stopButton.getModel().setPressed(true);
			}
		});
		actionMap.put("Esc", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				spanel.toMenu.getModel().setPressed(true);
			}
		});
	}
	
	
	
	public void updateGame()
	{
		if(gdata.checkGameOver() || (spanel.withTime && spanel.timeMinutes == 30))
		{
			JPanel gameOverWindow = new JPanel(new GridLayout(6, 1));
			gameOverWindow.setSize(400, 600);
			JLabel endGame = new JLabel("It seems that you have gone Hollow.");
			JLabel endGame2 = new JLabel("Make sure to not lose your Humanity next time");
			JLabel endGame3 = new JLabel("Your final score:  "+gdata.score);
			JLabel endGame4 = new JLabel("Enter your name:");
			if(chooseLang==1){
				endGame = new JLabel("It seems that you have gone Hollow.");
				endGame2 = new JLabel("Make sure to not lose your Humanity next time");
				endGame3 = new JLabel("Your final score:  "+gdata.score);
				endGame4 = new JLabel("Enter your name:"); repaint();}
			else if(chooseLang==2){
				endGame = new JLabel("Koniec gry!");
				endGame2 = new JLabel("Próbuj dalej :)");
				endGame3 = new JLabel("Twój wynik:  "+gdata.score);
				endGame4 = new JLabel("Wpisz imię do rankingu:"); repaint();}
			JTextArea ID = new JTextArea("");
			
			gameOverWindow.add(endGame);
			gameOverWindow.add(endGame2);
			gameOverWindow.add(endGame3);
			gameOverWindow.add(endGame4);
			
			gameOverWindow.add(ID);
			Object[] exit= {"BECOME DARK SPIRIT"};
			spanel.stopButton.getModel().setPressed(true);
			gameTimer.stop();
			spanel.displayTimer.stop();
			
			int option =JOptionPane.showOptionDialog(null,gameOverWindow,"YOU DIED", JOptionPane.OK_OPTION,JOptionPane.INFORMATION_MESSAGE, null, exit, null);
			
			if (option == JOptionPane.OK_OPTION) {
	                    String savenameFile;
	                    String name=ID.getText();
	                    String modifiedname = name.replace(' ', '_');
	                    if(name.isEmpty()){
						OpenRanking.rank.add(new GamePlayerID(gdata.score, "Player"));}
	                    else {OpenRanking.rank.add(new GamePlayerID(gdata.score, modifiedname));}
						if(GameSidePanel.withTime)
							savenameFile="timeRanking";
						else
							savenameFile="NOtimeRanking";
						
						SaveRanking saveRankToFile = new SaveRanking(savenameFile);
						
						spanel.toMenu.getModel().setPressed(true);
	
	           
	                }

			spanel.toMenu.getModel().setPressed(true);
	   
		}
		else if((gdata.currentTetromino.checkPiece(Orientation.DOWN, gdata.data)))
			gdata.movePiece(Orientation.DOWN);
		else
		{
			gdata.deleteRows();
			spanel.scoreText.setText(""+gdata.score);
			gdata.spawnPiece();
			spanel.nextTetrominoDisplay.displayColor(gdata.nextTetromino);
			if(gdata.levelUp)
			{
				gdata.levelUp = false;
				spanel.levelText.setText(""+gdata.level);
			}
			gdata.canSwap = true;
		}
	}
	
	public double calculateGameSpeed()
	{
		switch(gdata.level)
		{
			case 1:
				return 0.01667;
			case 2:
				return 0.021017;
			case 3:
				return 0.026977;
			case 4:
				return 0.035256;
			case 5:
				return 0.04693;
			case 6:
				return 0.06361;
			case 7:
				return 0.0879;
			case 8:
				return 0.1236;
			case 9:
				return 0.1775;
			case 10:
				return 0.2598;
			case 11:
				return 0.388;
			case 12:
				return 0.59;
			case 13:
				return 0.92;
			case 14:
				return 1.46;
			case 15:
				return 2.36;
			case 16:
				return 3.91;
			case 17:
				return 6.61;
			case 18:
				return 11.44;
			default:
				return 20.23;
		}
	}
}