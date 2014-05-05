package ar.com.finit.pacman.score;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author leo
 */
public class Score {

	private String[][] score;

	public void saveScore() {
		BufferedWriter writer = null;
		try {
			String filename = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "pacman_score.txt";
			File file = new File(filename);
			writer = new BufferedWriter(new FileWriter(file));
			String scoreString = parseScore(score);
			writer.write(scoreString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	private String parseScore(String[][] score) {
		String scoreString = "";
		for (int i = 0; i < 10; i++) {
			if (score[i][0] == null) break;
			scoreString += score[i][0] + ":" + score[i][1] + ":";
		}
		return scoreString;
	}

	public String[][] getScore() {
		if (score == null) {
			if (!existsFile()) {
				score = new String[10][2];
				saveScore();
			} else {
				String scoreString = "";
				BufferedReader br = null;
				try {
					String sCurrentLine;
					br = new BufferedReader(new FileReader(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "pacman_score.txt"));
					while ((sCurrentLine = br.readLine()) != null) {
						scoreString += sCurrentLine;
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				} finally {
					try {
						if (br != null)
							br.close();
					} catch (IOException ex) {
						throw new RuntimeException(ex);
					}
				}
				score = parseScore(scoreString);
			}
		}
		bubbleSort();
		return score;
	}

	private boolean existsFile() {
		File f = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "pacman_score.txt");
		return f.exists();
	}

	private String[][] parseScore(String scoreString) {
		String[][] score = new String[10][2];
		String[] scoreParts = scoreString.split(":");
		int j = 0;
		for (int i = 0; i < scoreParts.length - 1; i += 2) {
			score[j][0] = scoreParts[i];
			score[j][1] = scoreParts[i + 1];
			j++;
			if (j == 10)
				break;
		}
		return score;
	}

	public void setScore(String[][] score) {
		this.score = score;
	}

	public void bubbleSort() {
		int j;
		boolean flag = true;
		String temp0;
		String temp;

		while (flag) {
			flag = false; 
			for (j = 0; j < 10 - 1; j++) {
				if (score[j + 1][1] == null) {
					break;
				}
				if (Integer.parseInt(score[j][1]) < Integer.parseInt(score[j + 1][1])) {
					temp0 = score[j][0]; 
					temp = score[j][1]; 
					score[j][0] = score[j + 1][0];
					score[j][1] = score[j + 1][1];
					score[j + 1][0] = temp0;
					score[j + 1][1] = temp;
					flag = true;
				}
			}
		}
	}

	public void saveScore(String text, int points) {
		String scoreString = parseScore(score);
		scoreString += text + ":" + points + ":";
		score = parseScore(scoreString);
		this.saveScore();
		
	}

}
