

public class Snippet {
	public static void main(String[] args) {
		System.out.format("%-14s %-14s %-14s %-14s %-14s %-14s %-14s %-14s " + System.getProperty("line.separator"), "Item Number", "Product Name",
				"Price", "Inventory Qty" , "Threshold Qty", "Restock Qty", "Supplier Name", "Order Number");
	}
}