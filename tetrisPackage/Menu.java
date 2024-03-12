package tetrisPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


@SuppressWarnings("serial")
public class Menu extends JFrame
{
	JRadioButton RB;	
    JRadioButton RB2= new JRadioButton("ENG");
    
	public static int language = 1;
	
	public static final int x= 700;
	public static final int y= 650;
	
    public JSlider volumeSlider;
    public float volume;
    GameFrame gameFrame;
	
    
	public Menu() throws HeadlessException
{			super("T E T R I S");

	 		
	 		this.setSize(x,y);
	 		this.setResizable(false);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			
			
			JPanel imagePanelENG= new ImagePanelENG();
			JPanel imagePanelPL= new ImagePanelPL();
	        
	        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
	        volumeSlider.setMajorTickSpacing(20);
	        volumeSlider.setMinorTickSpacing(5);
	        volumeSlider.setPaintTicks(true);
	        volumeSlider.setPaintLabels(true);
	        volumeSlider.setValue(50);
	        volume = volumeSlider.getValue() / 100.0f;
	        Music.setVolume(volume);
	     //volumeSlider.setOpaque(false);
	        
	        volumeSlider.addChangeListener(new ChangeListener() {
	            public void stateChanged(ChangeEvent e) {
	                volume = volumeSlider.getValue() / 100.0f;
	                Music.setVolume(volume);
	            }
	        });
	        Music player = new Music();
            player.play("tetristheme2.wav");
			
	 		
	 		JButton time = new JButton("TIME RUSH");
	 		time.setPreferredSize(new Dimension(180, 60));
	 		time.setFont(UsedFonts.fontMenu);
	 		time.addActionListener(new ActionListener()
	 		{
				@Override
			    public void actionPerformed(ActionEvent arg0)
				{
					gameFrame = new GameFrame(true);
					setVisible(false);
				    OpenRanking timeRanking = new OpenRanking("timeRanking");
				  timeRanking.readFile();
					ChangeListener menuListener = new ChangeListener()
					{
						@Override
						public void stateChanged(ChangeEvent e)
						{
							if(gameFrame.spanel.toMenu.getModel().isPressed())
							{
								gameFrame.dispose();
								setVisible(true);
							}
						}
					};
					gameFrame.spanel.toMenu.addChangeListener(menuListener);
				}
			});
	 		
	 		JButton notime = new JButton("CLASSIC");
	 		notime.setPreferredSize(new Dimension(180, 60));
	 		notime.setFont(UsedFonts.fontMenu);
	 		notime.addActionListener(new ActionListener()
	 		{
				@Override
			    public void actionPerformed(ActionEvent arg0)
				{
					gameFrame = new GameFrame(false);
					setVisible(false);
					OpenRanking noTimeRanking = new OpenRanking("NOtimeRanking");
					noTimeRanking.readFile();
					ChangeListener menuListener = new ChangeListener()
					{
						@Override
						public void stateChanged(ChangeEvent e)
						{
							if(gameFrame.spanel.toMenu.getModel().isPressed())
							{
								gameFrame.dispose();
								setVisible(true);
							}
						}
					};
					gameFrame.spanel.toMenu.addChangeListener(menuListener);
				}
			});
	 		
	 		JButton sound = new JButton("SOUND");
	 		sound.setPreferredSize(new Dimension(100, 60));
	 		sound.setFont(UsedFonts.fontMenu);
	 		
	 		JButton lang = new JButton("LANGUAGE");
	 		lang.setPreferredSize(new Dimension(180, 60));
	 		lang.setFont(UsedFonts.fontMenu);

	 		JButton tutor = new JButton("TUTORIAL");
	 		tutor.setFont(UsedFonts.fontMenu);
	 		tutor.setPreferredSize(new Dimension(250,60));
	 		
	 		JPanel test = new JPanel(new GridLayout(1,2));
	 		
	 		RB= new JRadioButton("PL");
	 		RB.setSize(new Dimension(90, 30));
	 		RB.setFont(UsedFonts.fontMenu2);
			RB.setActionCommand("1");
		
			
			RB2= new JRadioButton("ENG");
			RB2.setPreferredSize(new Dimension(90, 30));
			RB2.setFont(UsedFonts.fontMenu2);
			RB2.setActionCommand("2");
			RB2.setSelected(true);
		//	RB2.getModel().setPressed(true);
			
			
			ButtonGroup group = new ButtonGroup();
			group.add(RB);
			group.add(RB2);
			
			test.add(RB);
			test.add(RB2);
	 		JPanel przyciski = new JPanel(new GridBagLayout());
	 		przyciski.setSize(x, y);
	 		przyciski.setBackground(new Color(1,1,1,0));
	 		GridBagConstraints gbc = new GridBagConstraints();
	 		gbc.fill = GridBagConstraints.HORIZONTAL;
	 		
	 		gbc.insets = new Insets(0, 40, 20, 40);
	 		gbc.gridx=2;
	 		gbc.gridy=3;
	 		przyciski.add(test, gbc);
	 		
	 		gbc.insets = new Insets(0, 40, 20, 40);
	 		gbc.gridx=1;
	 		gbc.gridy=3;
	 		przyciski.add(volumeSlider, gbc);
	 		
	 		gbc.insets = new Insets(320, 40, 20, 40);
	 		gbc.gridx=1;
	 		gbc.gridy=1;
	 		przyciski.add(notime, gbc);
	 		
	 		gbc.gridx=2;
	 		gbc.gridy=1;
	 		przyciski.add(time,gbc);
	 		
	 		gbc.insets = new Insets(10, 40, 20, 40);
	 		gbc.gridx=1;
	 		gbc.gridy=2;
	 		przyciski.add(sound, gbc);
	 		
	 		gbc.gridx=2;
	 		gbc.gridy=2;
	 		przyciski.add(lang, gbc);
	 	
	 		
	 		gbc.insets = new Insets(5, 40, 55, 40);
	 		gbc.gridwidth=2;
	 		gbc.gridx=1;
	 		gbc.gridy=4;
	 		przyciski.add(tutor, gbc);
	 		add(przyciski);
	 		add(imagePanelENG);
	 		
	 		this.revalidate();
	        this.repaint();
	        JTextArea ranktextArea = new JTextArea();
	        ranktextArea.setEditable(false); // Wyłączenie możliwości edycji tekstu
	        JScrollPane scrollPane = new JScrollPane(ranktextArea);
	 		tutor.addActionListener(new ActionListener()
	 		{
				@Override
			    public void actionPerformed(ActionEvent arg0)
				{
					String linia = "";
			        FileReader fr = null;
			        BufferedReader bfr;
					JOptionPane optionPane = new JOptionPane();
			        optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
					if (RB.isSelected()) {
						try {
							fr = new FileReader("TutorPL");
							bfr = new BufferedReader(fr);
								
							ranktextArea.setText("");
				    		while ((linia = bfr.readLine()) != null) {
				    			ranktextArea.setText(ranktextArea.getText()+linia +"\n"  );
						}}catch (IOException e) {
							e.printStackTrace();
						}
						
						 JOptionPane.showMessageDialog(null, scrollPane, "Instrukcja", JOptionPane.PLAIN_MESSAGE, null);
					}else {
						try {
							fr = new FileReader("TutorENG");
							bfr = new BufferedReader(fr);
								
							ranktextArea.setText("");
				    		while ((linia = bfr.readLine()) != null) {
				    			ranktextArea.setText(ranktextArea.getText()+linia +"\n"  );
						}}catch (IOException e) {
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, scrollPane, "Instruction", JOptionPane.PLAIN_MESSAGE, null);
					}}
						
		   }); 
	 		

			RB.addActionListener(new ActionListener()
	 		{
				@Override
			    public void actionPerformed(ActionEvent arg0)
				{
					language = 2;
					
					time.setText("Z CZASEM");
					notime.setText("BEZ CZASU");
					lang.setText("JĘZYK");
					sound.setText("DŹWIĘK");
					tutor.setText("SAMOUCZEK");
					imagePanelENG.setVisible(false);
					add(imagePanelPL);
					imagePanelPL.setVisible(true);
					repaint();
				}
			});
			
			
	 		
	 		RB2.addActionListener(new ActionListener()
	 		{
				@Override
			    public void actionPerformed(ActionEvent arg0)
				{
					language = 1;
					
					time.setText("TIME RUSH");
					notime.setText("CLASSIC");
					lang.setText("LANGUAGE");
					sound.setText("SOUND");
					tutor.setText("TUTORIAL");
					imagePanelENG.setVisible(true);
					imagePanelPL.setVisible(false);
				}
			});     
		
	}
	
	public static void main(String[] args)
	{
		Menu frame = new Menu();
		frame.setVisible(true);
	}
}