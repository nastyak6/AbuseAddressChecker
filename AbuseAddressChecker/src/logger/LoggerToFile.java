package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoggerToFile implements ILogger {

	public static LoggerToFile logger = null;
	public static String fileName = "log.txt";
	
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
			out.close();
			myWriter.close();

		} catch (IOException e) {
			System.out.println("[log] - Could not log. Error msg: " + e.getMessage());
		}

	}

}