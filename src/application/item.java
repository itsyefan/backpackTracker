package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class item {
	private static HashMap<String, item> objMap= new HashMap<String, item>();
	private static List<item> caseItems = new ArrayList<>();
	private String itemName;
	private String itemPrice;
	private int quantity;
	private String id;
	
	public item(String name, String classId) {
		this.itemName = name;
		this.id = classId;
	}
	
	public String getPrice() {
		return itemPrice;
	}

	public void setPrice(String number) {
		this.itemPrice = number;
	}

	public String getName() {
		return itemName;
	}
	
	public String getId() {
		return id;
	}
	
	public void setQuantity(int number) {
		this.quantity = number;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public static void addCases(String name,String classId) {
		objMap.put(name, new item(name,classId));
	}
	
	public static HashMap<String,item> getCaseItems(){
		return objMap;
	}

}