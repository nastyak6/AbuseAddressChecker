package Report;

import java.util.ArrayList;

public class Report {
	protected ArrayList<ReportItem> report = new ArrayList<ReportItem>();
	private IReport reportActions;
	
	public Report(IReport reportActions) {
		this.reportActions = reportActions;
	}
	
	public ArrayList<ReportItem> getReportAsArrayList() {
		return report;
	}
	
	public void addReportItem(ReportItem item) {
		report.add(item);
	}


	public void SaveReport(ArrayList<ReportItem> report) {
		reportActions.SaveReport(report);
	}

}
