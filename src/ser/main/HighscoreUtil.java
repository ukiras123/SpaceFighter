package ser.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HighscoreUtil {

	// Path to create highScoreLog file
	String path = System.getProperty("user.home") + "/Documents";

	// Setting player score in the file
	public void setScore(int score) {
		try {
			PrintWriter writer = new PrintWriter(path + "/highScoreLog.txt", "UTF-8");
			writer.println(score);
			writer.close();
		} catch (IOException e) {
		}
	}

	// Getting the score from the file
	public int getScore() throws IOException {
		String line = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path + "/highScoreLog.txt"));
		} catch (IOException e) {
			PrintWriter writer = new PrintWriter(path + "/highScoreLog.txt", "UTF-8");
			writer.println(0);
			writer.close();
			br = new BufferedReader(new FileReader(path + "/highScoreLog.txt"));
		}
		line = br.readLine();
		br.close();
		return Integer.valueOf(line);
	}

}
