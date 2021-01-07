package UtilityLibraries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.log4j.Logger;

public class TextFileManager {

	final static Logger logger = Logger.getLogger(TextFileManager.class);
	private String fileName;

	public TextFileManager(String filePath) {
		try {
			File file = new File(filePath);
			String fullFilePath = file.getAbsolutePath();
			String parentFolders = file.getParent();
			File file2 = new File(parentFolders);
			if (!file2.exists()) {
				file2.mkdirs();
			}
			fileName = fullFilePath;
		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

	public String readFile() {
		String data = null;
		String line = null;
		BufferedReader bfr = null;
		FileReader fileReader = null;
		StringBuffer sb = null;
		String newLine = System.lineSeparator();
		try {
			fileReader = new FileReader(fileName);
			bfr = new BufferedReader(fileReader);
			sb = new StringBuffer();
			while ((line = bfr.readLine()) != null) {
				sb.append(line + newLine);
			}
			data = sb.toString();
		} catch (Exception e) {
			logger.error("Error: ", e);
		} finally {
			if (bfr != null) {
				try {
					bfr.close();
					fileReader.close();
					sb = null;
				} catch (Exception e) {
					logger.error("Error: ", e);
				}
			}
		}
		logger.info("Reading external file: " + fileName);
		return data;
	}

	public void writeFile(String inputData) {
		FileWriter fileWriter = null;
		BufferedWriter bfw = null;
		try {
			fileWriter = new FileWriter(fileName);
			bfw = new BufferedWriter(fileWriter);
			bfw.write(inputData);
		} catch (Exception e) {
			logger.error("Error: ", e);
		} finally {
			try {
				bfw.close();
				fileWriter.close();
			} catch (Exception e) {
				logger.error("Error: ", e);
			}
		}
		logger.info("Creating external file: " + fileName);
	}

	/*
	 * public static void main(String[] args) { TextFileManager fileWriter = new
	 * TextFileManager("c:/img/Frank/myData1/codeData1.txt");
	 * fileWriter.writeFile("I love Java Programming!"); //String data =
	 * fileWriter.readFile(); //logger.info("data is: " + data);
	 * 
	 * }
	 */
	
}
