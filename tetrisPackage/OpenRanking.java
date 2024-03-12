package tetrisPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class OpenRanking {
	public static List<GamePlayerID> rank = new ArrayList<GamePlayerID>();
	String nameFile;
	public OpenRanking(String nameF) {
		// TODO Auto-generated constructor stub
		nameFile=nameF;
	}


	void readFile() {
        String filePath = nameFile;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
            	String[] words = line.split(" ");
          	    int score = Integer.parseInt(words[0]);
           	    String player = words[1];
           	    rank.add(new GamePlayerID(score,player));
            }
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Wystąpił błąd podczas wczytywania pliku: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
