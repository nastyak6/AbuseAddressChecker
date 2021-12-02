import Report.ExcelReport;
import Report.Report;
import Report.ReportItem;
import bitcoinabuse.BitcoinAbuseConnection;
import logger.LoggerToFile;

public class Main {

	public static void main(String[] args) {
		LoggerToFile logger = LoggerToFile.getLogger();
		BitcoinAbuseConnection bac  = new BitcoinAbuseConnection();
		String result1 = bac.CheckAddress("bc1qpzf5a7ucnfkwhgkxlyaywsyj6ennaspkcaxcpq");
		String result2 = bac.getNumberOfAbuses("bc1qpzf5a7ucnfkwhgkxlyaywsyj6ennaspkcaxcpq");
		String result3 = bac.getLink("bc1qpzf5a7ucnfkwhgkxlyaywsyj6ennaspkcaxcpq");

		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);

		
		ExcelReport xr = new ExcelReport();
		Report r = new Report(xr);
		r.addReportItem(new ReportItem("someAddress", "3", "someLink"));
		r.addReportItem(new ReportItem("someAddress2", "32", "someLink2"));
		r.SaveReport();
		
		logger.log("THIS IS A TEST LOG1");
		logger.log("THIS IS A TEST LOG2");

	}

}
