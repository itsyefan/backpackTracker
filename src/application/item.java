package application;

public class item {

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

}