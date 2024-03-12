package tetrisPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class GameSidePanel extends JPanel
{
	JLabel scoreText;
	JLabel levelText;
	JLabel timeLabel;
	
	JLabel timeText;
	int timeSeconds;
	int timeMinutes;
	JButton stopButton;
	ChangeListener pauseListener;
	ActionListener displayTimerTask;
	Timer displayTimer;
	JButton toMenu;
	boolean paused;
	public static boolean withTime;
	
	GameNextTetrominoDisplay nextTetrominoDisplay;
	GameStoredTetrominoDisplay storedTetrominoDisplay;
	
	public int chooseLang = Menu.language;
	
	public GameSidePanel(boolean withTime)
	{
		paused = false;
		this.withTime = withTime;
		setLayout(new GridLayout(6,1,40,10));
		setBackground(Color.blue);
		
		timeSeconds = 0;
		timeMinutes = 0;
		displayTimerTask = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setTime();
			}
		};
		displayTimer = new Timer(1000, displayTimerTask);
		displayTimer.setInitialDelay(250);
		displayTimer.start();
		
		nextTetrominoDisplay = new GameNextTetrominoDisplay();
 		add(nextTetrominoDisplay);
		
		storedTetrominoDisplay = new GameStoredTetrominoDisplay();
 		add(storedTetrominoDisplay);
		
 		JPanel scorePanel = new JPanel(new BorderLayout());
		scorePanel.setSize(300, 100);
		scorePanel.setLayout(new GridLayout(2,2));
		scoreText = new JLabel("0");
		scoreText.setHorizontalAlignment(SwingConstants.CENTER);
		scoreText.setVerticalAlignment(SwingConstants.CENTER);
		scoreText.setFont(UsedFonts.fontGame);
		
		JLabel scoreLabel= new JLabel("Score");
		if(chooseLang==1){
			scoreLabel.setText("Score"); repaint();}
		else if(chooseLang==2){
			scoreLabel.setText("Wynik"); repaint();}
		
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setVerticalAlignment(SwingConstants.CENTER);
		scoreLabel.setFont(UsedFonts.fontGame);
		levelText = new JLabel("1");
		levelText.setHorizontalAlignment(SwingConstants.CENTER);
		levelText.setVerticalAlignment(SwingConstants.CENTER);
		levelText.setFont(UsedFonts.fontGame);
		JLabel levelLabel = new JLabel("Level");
		
		if(chooseLang==1){
			levelLabel.setText("Level"); repaint();}
		else if(chooseLang==2){levelLabel.setText("Poziom"); repaint();}
		
		levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		levelLabel.setVerticalAlignment(SwingConstants.CENTER);
		levelLabel.setFont(UsedFonts.fontGame);
		scorePanel.add(levelLabel);
		scorePanel.add(scoreLabel);
		scorePanel.add(levelText);
		scorePanel.add(scoreText);
 		add(scorePanel);
		
 		JPanel timePanel = new JPanel();
		timePanel.setLayout(new GridLayout(2,1));
		timeText = new JLabel("0:00");
		timeText.setHorizontalAlignment(SwingConstants.CENTER);
		timeText.setVerticalAlignment(SwingConstants.CENTER);
		timeText.setFont(UsedFonts.fontGame);
		if(withTime)
			timeLabel = new JLabel("Time Left");
		else
			timeLabel = new JLabel("Time");
		if(chooseLang==2){
			timeLabel.setText("Czas"); repaint();}
		
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setVerticalAlignment(SwingConstants.CENTER);
		timeLabel.setFont(UsedFonts.fontGame);
		timePanel.add(timeLabel);
		timePanel.add(timeText);
		add(timePanel);
		
		JPanel stopmenu = new JPanel(new GridLayout(1,2));
		stopButton = new JButton("Pause");
		if(chooseLang==1){
			stopButton.setText("Pause"); repaint();}
		else if(chooseLang==2){stopButton.setText("Pauza"); repaint();}
		stopButton.setFont(UsedFonts.fontGame);
		pauseListener = new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				if(stopButton.getModel().isPressed() && !paused)
				{
					paused = true;
					stopButton.setText("Play");
					if(chooseLang==1){
					stopButton.setText("Play"); repaint();}
					else{stopButton.setText("Wznów"); repaint();}
					
					stopButton.getModel().setEnabled(false);
					displayTimer.stop();
				}
				else if(stopButton.getModel().isPressed() && paused)
				{
					paused = false;
					stopButton.setText("Pause");
					stopButton.getModel().setEnabled(false);
					displayTimer.restart();
				}
				stopButton.getModel().setEnabled(true);
			}
		};
		stopButton.addChangeListener(pauseListener);
		
		toMenu = new JButton("MENU");
		toMenu.setFont(UsedFonts.fontGame);
				
 		stopmenu.add(stopButton);
		stopmenu.add(toMenu);
		add(stopmenu);
		
		JPanel ranking = new JPanel(new BorderLayout());
 		JLabel rank = new JLabel("Rank");
 		if(chooseLang==1){
			rank.setText("Rank"); repaint();}
		else if(chooseLang==2){rank.setText("Ranking"); repaint();}
 		
 		rank.setHorizontalAlignment(SwingConstants.CENTER);
		rank.setVerticalAlignment(SwingConstants.CENTER);
 		rank.setFont(UsedFonts.fontGame2);
 		
 		JTextArea ranktextArea = new JTextArea(20, getWidth());
        ranktextArea.setEditable(false); // Wyłączenie możliwości edycji tekstu
        JScrollPane scrollPane = new JScrollPane(ranktextArea);
        
        scrollPane.setLocation(0, 0);
        
        String linia = "";
        FileReader fr = null;
        BufferedReader bfr;
        String namerankFile;
        if(withTime)
			namerankFile="timeRanking";
		else
			namerankFile="NOtimeRanking";
		try {
			fr = new FileReader(namerankFile);
			bfr = new BufferedReader(fr);
				
			ranktextArea.setText("");
			int nrline=1;
    		while ((linia = bfr.readLine()) != null) {
    			ranktextArea.setText(ranktextArea.getText()+nrline+". "+linia +"\n"  );
    			nrline++;
		}}catch (IOException e) {
			e.printStackTrace();
		}
		
		ranking.add(rank, BorderLayout.PAGE_START);
        ranking.add(scrollPane, BorderLayout.CENTER);
 
 		add(ranking);
	}
	
	public void setTime()
	{
		timeSeconds++;
		if(timeSeconds==60)
		{
			timeSeconds = 0;
			timeMinutes++;
		}
		if(withTime)
			timeText.setText(29-timeMinutes+":"+String.format("%02d",60-timeSeconds));
		else
			timeText.setText(timeMinutes+":"+String.format("%02d",timeSeconds));
	}
}