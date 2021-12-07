package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoggerToFile implements ILogger {

	public static LoggerToFile logger = null;
	public static String fileName = "log.txt";
	
	private ArrayList<String> logs = new ArrayList<>();
	
	public ArrayList<String> getLogs() {
		return logs;
	}

	// private constructor
	private LoggerToFile() {
	}
	
	// Singleton design pattern
	public static LoggerToFile getLogger() {
		if (logger == null) {
			logger = new LoggerToFile();

			File file = new File(fileName);
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Could not create log.txt file");
			}
		}
		return logger;
	}

	@Override
	public void log(String msg) {
	      FileWriter myWriter;
		try {
			long millis=System.currentTimeMillis();  
			java.util.Date date=new java.util.Date(millis);  
			myWriter = new FileWriter(fileName, true);
			BufferedWriter out = new BufferedWriter(myWriter);
			out.write(date + " " + msg + "\n");
			logs.add(date + " " + msg + "\n");
			out.close();
			myWriter.close();

		} catch (IOException e) {
			System.out.println("[log] - Could not log. Error msg: " + e.getMessage());
		}

	}

	public void loadLogsFromFile() {
		logs.clear();
		try {
		      File myObj = new File(fileName);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String log = myReader.nextLine();
		        logs.add(log);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		    }
	}
}