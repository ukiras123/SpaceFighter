package ser.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

//This process is not supported while exporting JAR
public class FILE2 {

	public int getScore() {
		// StringBuilder stringBuilder = new StringBuilder();
		String line = null;

		InputStream inputStream = getClass().getResourceAsStream("/highScore.txt");

		if (inputStream == null) {
			System.out.println("inputStream == null");
		} else {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			try {
				line = bufferedReader.readLine();
				bufferedReader.close();
			} catch (IOException ex) {
			}
		}

		return Integer.valueOf(line);
	}

	public void setScore(int score) {
		URL url = getClass().getResource("/highScore.txt");
		File file = new File(url.getPath());

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
