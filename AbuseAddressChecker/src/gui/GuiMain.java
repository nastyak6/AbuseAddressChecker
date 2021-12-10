package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GuiMain extends Application{

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("BitcoinAbuseGUI.fxml"));
			loader.load();
			Parent p = loader.getRoot();
			primaryStage.setTitle("Abuse Address Checker");
			primaryStage.getIcons().add(new Image("/gui/resources/istockphoto-1299615596-612x612-removebg-preview.png"));
			primaryStage.setScene(new Scene(p));
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
