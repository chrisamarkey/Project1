import java.util.ArrayList;

public class Item {
	
	
	private String itemNum;
	private String productName;
	private int inputQTY;
	private float price;
	
	
	public Item (String itemNum, String productName,  int inputQTY, float price) {
		

		this.itemNum = itemNum;
		this.productName = productName;
		this.inputQTY = inputQTY;
		this.price = price;
		
		
	}
	
	
	public Item (String itemNum, String productName, float price) {
		

		this.itemNum = itemNum;
		this.productName = productName;
		this.price = price;
		
		
	}
	
	public void setItemNum( String itemNum) {
		
		this.itemNum= itemNum;
	}
	
	
	public String getItemNum () {
		
		return itemNum;
	}
	
	public void setInputQTY(int inputQTY ) {
		
		this.inputQTY = inputQTY;
		
	}
	
	public int getInputQTY () {
		return inputQTY;
	}
	
	public void setProductName (String productName ) {
		
		this.productName = productName;
	}
	
	public String getProductName (){
		
		return productName;
	}
	
	public void setPrice (float price) {
		this.price = price;
	}
	
	public float getPrice () {
		
		return price;
	}
	
	public String toString () {
		
		return "Item Num: " +itemNum+";" + " product name: " + productName +";"+ " price: "+ price+"\n";
	}

	

}
