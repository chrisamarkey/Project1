import java.util.ArrayList;
import java.util.Date;

public class CompletedSale {
	
	//private int saleID;
	//private int regID;
	//private String userID;
	//private Date saleDateTime;
	//private float saleTotal;
	private ArrayList <Sale> sale = new ArrayList <Sale>();
	private ArrayList <Item> itemInSale = new ArrayList <Item>();

	CompletedSale (ArrayList <Sale> sale, ArrayList <Item> itemInSale) {
		
		//int saleID, int regID, String userID, Date saleDateTime, float saleTotal,
		//this.saleID = saleID;
		//this.regID = regID;
		//this.userID = userID;
		//this.saleDateTime = saleDateTime;
		//this.saleTotal = saleTotal;
		this.sale = sale;
		this.itemInSale = itemInSale;
		
	}
	
	//public int getRegID() {
		
		//return regID;
		
	//}
	//public void setRegID(int regID) {
		
		//this.regID = regID;
		
	//}
	
	//public void setUserID(String newUserID) {
		
		//userID =newUserID;
	//}
	
	//public String getUserID() {
		
		//return userID;
		
	//}
	
	//public void setSaleID (int saleID) {
		
		//this.saleID =saleID;
	//}
	
	//public int getSaleID() {
		
		//return saleID;
		
	//}
	
	//public void setSaleTotal (float saleTotal) {
		//this.saleTotal = saleTotal;
	//}
	
	public void setItemArrayList (ArrayList<Item> itemInSale) {
		
		this.itemInSale = itemInSale;
	}
	
	public ArrayList<Item> getItemArrayList () {
		
		return itemInSale;
	}
	
	public void setSaleInfoArrayList (ArrayList<Sale> sale) {
		
		this.sale = sale;
	}
	
	public ArrayList<Sale> getSaleArrayList () {
		
		return sale;
	}
	
	public String toString () {
		
		String items="";
		
		int rowNumber =1;
		
		for (int i = 0; i<itemInSale.size(); i++ ) {
			
			items +="("+rowNumber+") "+ itemInSale.get(i);
			rowNumber++;
			
		}
		
		//"Sale Number: " + saleID + " register ID: " + regID+ " user ID: " +userID +" sale date & date: " +saleDateTime +"  sale total: " +saleTotal +"\n"
		return sale.get(0)+items;
		
	}
	

	

}
