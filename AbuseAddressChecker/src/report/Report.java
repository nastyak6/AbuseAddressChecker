package report;

import java.util.ArrayList;
import java.util.HashSet;
import bitcoinabuse.BitcoinAbuseConnection;

public class Report {
	protected ArrayList<ReportItem> report = new ArrayList<ReportItem>();
	private IReport reportActions;
	private BitcoinAbuseConnection BAC;
	
	public Report(IReport reportActions) {
		BAC = new BitcoinAbuseConnection();
		this.reportActions = reportActions;
	}
	
	public ArrayList<ReportItem> getReportAsArrayList() {
		return report;
	}
	public void clear() {
		report.clear();
	}
	
	public void addReportItem(ReportItem item) {
		report.add(item);
	}

	public void SaveReport() {
		reportActions.SaveReport(report);
	}
	
	public void Scan(HashSet<String> addresses) {
		for (String address : addresses) {
			String numOfAbuses = BAC.getNumberOfAbuses(address);
			String link = BAC.getLink(address);
			ReportItem item = new ReportItem(address, numOfAbuses, link);
			report.add(item);
		}
	}

}
