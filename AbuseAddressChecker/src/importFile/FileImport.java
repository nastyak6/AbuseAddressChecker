package importFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import logger.LoggerToFile;

public class FileImport implements IImport {
	
	private HashSet<String> addresses = new HashSet<>(); 

	
	public void clear() {
		addresses.clear();
	}
	
	public void add(String address) {
		if (!addresses.contains(address))
			addresses.add(address);
	}
	
	public HashSet<String> getAddresses() {
		return addresses;
	}

	@Override
	public void upload(String filePath) {
		try {
		      File myObj = new File(filePath);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String address = myReader.nextLine();
				if (!addresses.contains(address))
					addresses.add(address);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
				LoggerToFile.getLogger().log("[upload] - Could not load address file. Error msg: " + e.getMessage());
		    }
	}

}
