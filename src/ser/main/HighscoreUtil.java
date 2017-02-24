package ser.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HighscoreUtil {
	String path = System.getProperty("user.home");

	public void setScore(int score) {
		try {
			PrintWriter writer = new PrintWriter(path + "/output1.txt", "UTF-8");
			writer.println(score);
			writer.close();
		} catch (IOException e) {
		}
	}

	public int getScore() throws IOException {
		String line = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path + "/output1.txt"));
		} catch (IOException e) {
			PrintWriter writer = new PrintWriter(path + "/output1.txt", "UTF-8");
			writer.println(0);
			writer.close();
			br = new BufferedReader(new FileReader(path + "/output1.txt"));
		}
		try {
			line = br.readLine();
		} finally {
			br.close();
		}
		return Integer.valueOf(line);
	}

}
