package application;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class SummaryController implements Initializable{
	@FXML
	private ListView<String> itemList;
	@FXML
	private Label quanLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private Label totalLabel;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	String currentCase;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for (String k : item.getCaseItems().keySet()) {
			itemList.getItems().add(k);
		}
		
		itemList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				
				currentCase = itemList.getSelectionModel().getSelectedItem();
				quanLabel.setText("Quantity: " + Integer.toString(item.getCaseItems().get(currentCase).getQuantity()));
				priceLabel.setText("Price: " + item.getCaseItems().get(currentCase).getPrice());
				totalLabel.setText("Total: NZ$ " + Double.toString((Double.parseDouble(item.getCaseItems().get(currentCase).getPrice().split(" ")[1]) * Double.valueOf(item.getCaseItems().get(currentCase).getQuantity()))));
			}
			
		});
		
		
	}
	
	public void homeScene(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
