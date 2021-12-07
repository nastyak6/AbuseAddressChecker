package report;

import javafx.beans.property.SimpleStringProperty;

public class ReportItemTV {
	private SimpleStringProperty address;
	private SimpleStringProperty numOfAbuses;
	private SimpleStringProperty link;
	
	public ReportItemTV(String address, String numOfAbuses, String link) {
		this.address = new SimpleStringProperty(address);
		this.numOfAbuses = new SimpleStringProperty(numOfAbuses);
		this.link = new SimpleStringProperty(link);
	}
	
	public String getAddress() {
		return address.get();
	}
	public void setAddress(SimpleStringProperty address) {
		this.address = address;
	}
	public String getNumOfAbuses() {
		return numOfAbuses.get();
	}
	public void setNumOfAbuses(SimpleStringProperty numOfAbuses) {
		this.numOfAbuses = numOfAbuses;
	}
	public String getLink() {
		return link.get();
	}
	public void setLink(SimpleStringProperty link) {
		this.link = link;
	}
	
}

