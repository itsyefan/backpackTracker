package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void summaryScene(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("summaryScene.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
