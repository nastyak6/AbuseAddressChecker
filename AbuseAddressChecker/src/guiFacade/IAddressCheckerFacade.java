package guiFacade;

import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import report.ReportItemTV;

public interface IAddressCheckerFacade {

	public void ImportAdressesFile(ListView<String>Address_ListvVew,TextField Address_textField);
	
	public void DeleteRow(ListView<String>Address_ListvVew);
	
	public void ClearTable(ListView<String>Address_ListvVew);
	
	public void AddToList(ListView<String>Address_ListvVew,TextField Address_textField);
	
	public void Scan(TableView<ReportItemTV> report_tableView);

	public void SaveReport();
	
	public void LoadLogsFromFile(TextArea log_textArea);
}
