import java.util.Random;

public class Supplier {
	
	private String supplierName = null;
//	private String productName = null;
//	private int itemNum = 0;
	private int orderNum = 0;
//	private boolean shipped = false;
	
	   public Supplier (String supplierNames, int orderNumber)
	   {
	      supplierName = supplierNames;
	      orderNum = orderNumber;
	   }
	   
	   public Supplier (int orderNumber)
	   {
	      orderNum = orderNumber;
	   }
	
	public Supplier() {
		// TODO Auto-generated constructor stub
	}
		
	public void setSupplierName(String supplierName){
		this.supplierName = supplierName;
	}
	
	public String getSupplierName(){
		return supplierName;
	}

//	public void setItemNum(int itemNum){
//		this.itemNum = itemNum;
//	}
//	
//	public int getItemNum(){
//		return itemNum;
//	}
	
//	public void setProductName(String productName){
//		this.productName = productName;
//	}
//	
//	public String getProductName(){
//		return productName;
//	}
	
	public void setOrderNum(){
		Random rand = new Random();
		orderNum = rand.nextInt(100);
//		this.orderNum = orderNum;
	}
	
	public int getOrderNum(){
		if (this.orderNum != 111) return orderNum;
		else return 0;
	}
	
	public boolean getShipped(boolean shipped) {
		return shipped;
	}
	
	public String toString(){
		return null;
	}
	
}
