package Report;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import logger.LoggerToFile;

public class ExcelReport implements IReport {

	@Override
	public void SaveReport(ArrayList<ReportItem> report) {
	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH.mm.ss");  
	   LocalDateTime now = LocalDateTime.now();  
		String filename = "Report Result - " + dtf.format(now) + ".xls";  

		HSSFWorkbook workbook = new HSSFWorkbook();  
		HSSFSheet sheet = workbook.createSheet("Report Result");   
		int numOfRows = 0;
		HSSFRow rowhead = sheet.createRow(numOfRows++);  
		rowhead.createCell(0).setCellValue("Address");  
		rowhead.createCell(1).setCellValue("# Of Abuses");  
		rowhead.createCell(2).setCellValue("Adress Link");  
		
		for (ReportItem item : report) {
			rowhead = sheet.createRow(numOfRows++);  
			rowhead.createCell(0).setCellValue(item.getAddress());  
			rowhead.createCell(1).setCellValue(item.getNumOfAbuses());  
			rowhead.createCell(2).setCellValue(item.getLink());  
		}
		
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);  
			
			fileOut.close();  
			workbook.close(); 
		} catch (Exception e) {
			LoggerToFile.getLogger().log("[SaveReport] - Could not save the report to excel. Error msg: " + e.getMessage());
		}  
 

	}

}
