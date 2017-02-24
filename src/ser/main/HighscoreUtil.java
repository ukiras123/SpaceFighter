package ser.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;

public class HighscoreUtil {

	URL url = this.getClass().getResource("/highScore.txt");
	File file;
	String score;

	public int getScore() {
		try {
			try {
				file = new File(url.toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			score = bufferedReader.readLine();
			System.out.println(score);
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file");
		} catch (IOException ex) {
			System.out.println("Error reading file ");
		}
		return Integer.parseInt(score);
	}

	public void setScore(int score) {
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
		try {
			if (file.exists() == false) {
				System.out.println("We had to make a new file.");
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(file);
			out.append(String.valueOf(score));
			out.close();
		} catch (IOException e) {
			System.out.println("COULD NOT LOG!!");
		}
	}

}
