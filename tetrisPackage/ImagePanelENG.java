package tetrisPackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanelENG extends JPanel
{
	private BufferedImage image;

	public ImagePanelENG()
	{
		super();
		ImageIcon image = new ImageIcon("tetristloENG.PNG", "Tło");
		JLabel tlo = new JLabel(image);
		tlo.setSize(700, 650);
		add(tlo);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, 0, 0, this);
	}
}