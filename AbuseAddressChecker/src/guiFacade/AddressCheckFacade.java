package guiFacade;

import java.io.File;
import java.util.ArrayList;

import importFile.FileImport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logger.LoggerToFile;
import report.ExcelReport;
import report.Report;
import report.ReportItem;
import report.ReportItemTV;

public class AddressCheckFacade implements IAddressCheckerFacade {

	FileImport importFile;
	Report r;
	LoggerToFile logger;

	public AddressCheckFacade() {
		super();
		logger = LoggerToFile.getLogger();
		ExcelReport xr = new ExcelReport();
		r = new Report(xr);
		this.importFile = new FileImport();
	}

	@Override
	public void ImportAdressesFile(ListView<String> Address_ListvVew, TextField Address_textField) {
		Address_ListvVew.getItems().clear();
		FileChooser fileChooser = new FileChooser();
		String currentDirectory = System.getProperty("user.dir");
		fileChooser.setInitialDirectory(new File(currentDirectory));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File selectedFile = fileChooser.showOpenDialog((Stage) Address_textField.getScene().getWindow());

		if (selectedFile != null) {
			importFile.upload(selectedFile.toString());

			for (String address : importFile.getAddresses()) {
				Address_ListvVew.getItems().add(address);
			}
			logger.log("Successfully uploaded address file");
		} else {
			logger.log("Please select file");

		}
	}

	@Override
	public void DeleteRow(ListView<String> Address_ListvVew) {
		if (Address_ListvVew.getSelectionModel().getSelectedItem() != null) {
			Address_ListvVew.getItems().remove(Address_ListvVew.getSelectionModel().getSelectedItem());
			logger.log("Row was successfully Removed");
		} else {
			logger.log("Please select a row first");
		}
	}

	@Override
	public void ClearTable(ListView<String> Address_ListvVew) {
		Address_ListvVew.getItems().clear();
		importFile.clear();
		logger.log("Table was successfully cleared");

	}

	@Override
	public void AddToList(ListView<String> Address_ListvVew, TextField Address_textField) {
		if (Address_textField.getText() == null || Address_textField.getText().isEmpty()) {
			logger.log("Please Insert address before clicking 'Add To List'");
			return;
		}

		importFile.add(Address_textField.getText());
		Address_ListvVew.getItems().clear();

		for (String address : importFile.getAddresses()) {
			Address_ListvVew.getItems().add(address);
		}
		Address_textField.clear();
		logger.log("Table was successfully added to the list");

	}

	@Override
	public void Scan(TableView<ReportItemTV> report_tableView) {
		r.clear();
		report_tableView.getItems().clear();

		r.Scan(importFile.getAddresses());
		ObservableList<ReportItemTV> ov = FXCollections.observableArrayList();
		ArrayList<ReportItemTV> reportArrayListForTableView = new ArrayList<>();
		for (ReportItem item : r.getReportAsArrayList()) {
			reportArrayListForTableView.add(new ReportItemTV(item.getAddress(), item.getNumOfAbuses(), item.getLink()));
		}

		for (ReportItemTV item : reportArrayListForTableView) {
			ov.add(item);
		}
		report_tableView.setItems(ov);
		logger.log("Scan completed successfully");

	}

	@Override
	public void SaveReport() {
		r.SaveReport();
	}

	@Override
	public void LoadLogsFromFile(TextArea log_textArea) {
		log_textArea.clear();
		logger.loadLogsFromFile();

		for (String log : logger.getLogs()) {
			log_textArea.appendText("\n" + log);
		}
	}

}
