package application;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;
	private static List<String> idList = new ArrayList<String>();
	
	
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		HttpClient getInv = HttpClient.newHttpClient();
		HttpRequest getInvRequest = HttpRequest.newBuilder().uri(URI.create("https://steamcommunity.com/inventory/76561198045531422/730/2")).build();
		getInv.sendAsync(getInvRequest, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenApply(HomeController::parseItem).join();
		
		for (item cases : item.getCaseItems().values()) {
			HttpClient getPrice = HttpClient.newHttpClient();
			HttpRequest getPriceRequest = HttpRequest.newBuilder().uri(URI.create("https://steamcommunity.com/market/priceoverview/?market_hash_name=" + cases.getName().replace(" ", "%20") + "&appid=730&currency=22")).build();
			
			cases.setPrice(getPrice.sendAsync(getPriceRequest, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenApply(HomeController::parsePrice).join());
		}
		
	}
	
	public static String parsePrice(String responseBody) {
		//Method retrieves the minimum and median prices of an item
		JSONObject price = new JSONObject(responseBody);
		String lowPrice = price.getString("lowest_price");
		return lowPrice;
	}
	
	public static String parseItem(String responseBody) {
		//Method retrieves all cases in CSGO inventory and stores to array
		JSONObject inventoryInfo = new JSONObject(responseBody);
		JSONArray itemAssets = inventoryInfo.getJSONArray("assets");
		JSONArray itemInfo = inventoryInfo.getJSONArray("descriptions");
		
		for (int i = 0; i<itemInfo.length(); i++) {
			String itemClassId = itemInfo.getJSONObject(i).getString("classid");
			String itemName = itemInfo.getJSONObject(i).getString("market_hash_name");
			if (itemName.contains("Case")) {
				item.addCases(itemName, itemClassId);
//				item itemName = new item(itemName,itemClassId);
//				item.addCases(new item(itemName,itemClassId));
			}
		}
		for (int j = 0; j<itemAssets.length(); j++) {
			String itemClassId = itemAssets.getJSONObject(j).getString("classid");
			idList.add(itemClassId);
		}
		
		for (item cases : item.getCaseItems().values()) {
			cases.setQuantity(Collections.frequency(idList,cases.getId()));
		}

		return null;
	}
}
