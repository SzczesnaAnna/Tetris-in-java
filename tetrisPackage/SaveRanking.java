package tetrisPackage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

public class SaveRanking extends OpenRanking {

	public SaveRanking(String nameF) {
		super(nameF);
		// TODO Auto-generated constructor stub
		Collections.sort(rank, new ScoreComparator());
	     try {
	           File file = new File(nameF);
	            if (file.exists()) {
	                file.delete();
	            }
	            
	            if (rank.size() > 20) {
	                rank.subList(20, rank.size()).clear();
	            }
	            
	            FileOutputStream writer = new FileOutputStream(file);
	            for (int i = 0; i < rank.size(); i++) {
	                GamePlayerID player = rank.get(i);
	                String line = (player.getScore()+" "+player.getName()+"\n");
	                writer.write(line.getBytes());}
	            writer.close();
	            rank.clear();
	        } catch (IOException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Wystąpił błąd podczas zapisywania pliku: " + e.getMessage());
	        }
	    }
	public static class ScoreComparator implements Comparator<GamePlayerID> {
        @Override
        public int compare(GamePlayerID p1, GamePlayerID p2) {
            return p2.getScore() - p1.getScore(); // Descending order
        }
    }
}
