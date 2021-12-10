package gui;

import java.net.URL;
import java.util.ResourceBundle;
import guiFacade.AddressCheckerFacade;
import guiFacade.IAddressCheckerFacade;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import report.ReportItemTV;

public class BitcoinAbuseController implements Initializable{

    @FXML
    private AnchorPane anchorP;
    
    @FXML
    private TextField Address_textField;

    @FXML
    private Button Add_button;

    @FXML
    private ListView<String> Address_ListvVew;

    @FXML
    private Button clear_button;

    @FXML
    private Button scan_button;

    @FXML
    private TextArea log_textArea;

    @FXML
    private TableView<ReportItemTV> report_tableView;
    
    @FXML
    private TableColumn<ReportItemTV, String> addressCol;

    @FXML
    private TableColumn<ReportItemTV, String> abusesCol;

    @FXML
    private TableColumn<ReportItemTV, String> linkCol;

    @FXML
    private ImageView upload_button;

    @FXML
    private ImageView save_button;

    @FXML
    private Button clear_button1;

    @FXML
    private Button clear_button2;
    
    IAddressCheckerFacade addressCheckFacade;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		init();
		addressCheckFacade = new AddressCheckerFacade();
		Address_ListvVew.setCellFactory(TextFieldListCell.forListView());
		
		Address_ListvVew.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
			@Override
			public void handle(ListView.EditEvent<String> t) {
				Address_ListvVew.getItems().set(t.getIndex(), t.getNewValue());
			}
						
		});
		
		addressCheckFacade.LoadLogsFromFile(log_textArea);

	}
	
	private void init() {
		addressCol.setCellValueFactory(new PropertyValueFactory<ReportItemTV, String>("address"));
		abusesCol.setCellValueFactory(new PropertyValueFactory<ReportItemTV, String>("numOfAbuses"));
		linkCol.setCellValueFactory(new PropertyValueFactory<ReportItemTV, String>("link"));
	}
	
	@FXML
	private void ImportAdressesFile() {
		addressCheckFacade.ImportAdressesFile(Address_ListvVew, Address_textField);
		addressCheckFacade.LoadLogsFromFile(log_textArea);
	}

	@FXML
	private void DeleteRow() {
		addressCheckFacade.DeleteRow(Address_ListvVew);
		addressCheckFacade.LoadLogsFromFile(log_textArea);

	}

	@FXML
	private void ClearTable() {
		addressCheckFacade.ClearTable(Address_ListvVew);
		addressCheckFacade.LoadLogsFromFile(log_textArea);

	}

	@FXML
	private void AddToList() {
		addressCheckFacade.AddToList(Address_ListvVew, Address_textField);
		addressCheckFacade.LoadLogsFromFile(log_textArea);

	}
	
	@FXML
	private void Scan() {
		addressCheckFacade.Scan(report_tableView);
		addressCheckFacade.LoadLogsFromFile(log_textArea);

	}
	
	@FXML 
	private void SaveReport() {
		addressCheckFacade.SaveReport();
        Alert a = new Alert(AlertType.NONE);
        a.setAlertType(AlertType.INFORMATION);
        a.setTitle("CONFIRMATION");
        a.setContentText("The Report Was Saved");
        a.show();
		addressCheckFacade.LoadLogsFromFile(log_textArea);

	}

}


