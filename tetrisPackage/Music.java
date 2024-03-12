package tetrisPackage;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

public class Music extends JPanel{
	private static final long serialVersionUID = 1L;
	private static Clip clip;

    public Music() {
       
    }

    public void play(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        float initialVolume = (float) 0.5;
        setVolume(initialVolume);
    }

    public static void setVolume(float volume) {
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float minGain = gainControl.getMinimum();
            float maxGain = gainControl.getMaximum();
            float range = maxGain - minGain;
            float gain = (range * volume) + minGain;
            gainControl.setValue(gain);
        }
    }

}
