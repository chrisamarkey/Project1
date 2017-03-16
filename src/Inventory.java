
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Iterator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Inventory {

	// private int orderNum = 0;
	// private LocalDateTime orderDateTime;
	boolean received;
	private int itemNum = 0;
	private Item returnItem;
	private String productName = null;
	private int quantity = 0;
	private int inventoryQty = 0;
	private int thresholdQty = 0;
	private int reStockQty = 0;
	private float price = 0;
	private String managerUserName = null;
	private String managerPassword = null;
	String fileName = "inventory.txt";
	
	SimpleDateFormat dateFormat = new SimpleDateFormat( "MM/dd/yy-HH:mm:ss" );
	String dateTime = dateFormat.format(new Date());

	// public static void main(String[] args) throws Exception {

	// Supplier s1 = new Supplier("Vigero", 111);
	// Supplier s2 = new Supplier("Dole", 111);
	// Supplier s3 = new Supplier("Burlington", 111);
	// Supplier s4 = new Supplier("Nike", 111);
	// Supplier s5 = new Supplier("Swiss", 111);
	// Supplier s6 = new Supplier("Chiquita", 111);
	// Supplier s7 = new Supplier("Gaterade", 111);
	// Supplier s8 = new Supplier("V8", 111);

	// ArrayList<String> p1 = new ArrayList<>(Arrays.asList("1", "tomato",
	// "1.00", "100", "10", "100",
	// s1.getSupplierName(), Integer.toString(s1.getOrderNum())));
	// ArrayList<String> p2 = new ArrayList<>(Arrays.asList("2", "orange",
	// "0.50", "110", "15", "110",
	// s2.getSupplierName(), Integer.toString(s2.getOrderNum())));
	// ArrayList<String> p3 = new ArrayList<>(Arrays.asList("3", "coat",
	// "30.00", "20", "5", "20", s3.getSupplierName(),
	// Integer.toString(s3.getOrderNum())));
	// ArrayList<String> p4 = new ArrayList<>(Arrays.asList("4", "hat", "15.00",
	// "30", "10", "30", s4.getSupplierName(),
	// Integer.toString(s4.getOrderNum())));
	// ArrayList<String> p5 = new ArrayList<>(Arrays.asList("5", "backpack",
	// "20.00", "40", "8", "40",
	// s5.getSupplierName(), Integer.toString(s5.getOrderNum())));
	// ArrayList<String> p6 = new ArrayList<>(Arrays.asList("6", "banana",
	// "0.75", "100", "10", "100",
	// s6.getSupplierName(), Integer.toString(s6.getOrderNum())));
	// ArrayList<String> p7 = new ArrayList<>(Arrays.asList("7", "drink",
	// "1.50", "80", "25", "80", s7.getSupplierName(),
	// Integer.toString(s7.getOrderNum())));
	// ArrayList<String> p8 = new ArrayList<>(Arrays.asList("8", "apple",
	// "0.90", "75", "20", "75", s8.getSupplierName(),
	// Integer.toString(s8.getOrderNum())));

	// try
	// {
	// File file = new File(fileName);
	// if (file.delete()) {
	// System.out.println("The " + file.getName() + "inventory file has been
	// updated as shown here: ");
	// } else {
	// System.out.println("Inventory list " + file.getName() + " has been
	// created as shown here; ");
	// }
	// }
	// catch(
	// Exception e)
	// {
	// e.printStackTrace();
	// }
	// for (int i = 0; i < 5; i++) {
	// if (i == 0) {
	// createInventory(p1, fileName);
	// createInventory(p2, fileName);
	// createInventory(p3, fileName);
	// createInventory(p4, fileName);
	// createInventory(p5, fileName);
	// createInventory(p6, fileName);
	// createInventory(p7, fileName);
	// createInventory(p8, fileName);
	// }
	// // Need to pass productName and saleQty into this readInventory
	// // class
	// // below to update inventory quantities.
	// // Also need to add an if statement to not execute the
	// // updateInventory method when creating a new inventory list.
	// if (i == 1)
	// updateInventory(fileName, "banana", 91, 0);
	// if (i == 2)
	// updateInventory(fileName, "apple", 55, 0);
	// if (i == 3)
	// updateInventory(fileName, "orange", 100, 0);
	// if (i == 4)
	// updateInventory(fileName, "tomato", 90, 0);
	// }
	// }

	// public static void createInventory(ArrayList<String> a, String fileName)
	// throws IOException {
	// //Need to add Try Catch to this method.
	// Iterator<String> Iterator = a.iterator();
	// //File inventoryFile = new File(fileName);
	// BufferedWriter outBuffer = new BufferedWriter(new FileWriter(fileName,
	// true));
	// //if (inventoryFile.exists()) {
	// //while (Iterator.hasNext()) {
	// //String theString = Iterator.next();
	// //String betterString = theString + " ";
	// //outBuffer.append(betterString);
	// //}
	// //} else {
	// while (Iterator.hasNext()) {
	// String theString = Iterator.next();
	// String betterString = theString + " ";
	// outBuffer.write(betterString);
	// }
	// // }
	// outBuffer.write("\n");
	// outBuffer.close();
	// }

//	Date date = new Date();

	public Inventory(String productNames, int saleQty) throws IOException {
		this.productName = productNames;
		this.quantity = saleQty;
		updateInventory(fileName, productName, quantity);
	}

	public Inventory(String itemNum) throws IOException {
		returnItem = readInventory(fileName, Integer.parseInt(itemNum));
	}

	public Item returnToRegister() {
		return returnItem;
	}

	public Item readInventory(String fileName, int itemNum) throws IOException {
		Item littleItem = null;
		// Need to add Try Catch to this method.
		File inventoryFile = new File(fileName);
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		if (inventoryFile.exists()) {
			while (br.ready()) {
				String readStr = br.readLine();
				String[] output = readStr.split(" ");
				// System.out.println("Output Length: " + output.length);
				if (Integer.parseInt(output[0]) == itemNum) {
					System.out.println(itemNum);
					Item item = new Item(Integer.toString(itemNum), output[1], Float.valueOf(output[2]));

					littleItem = item;
				}
			}
			br.close();
		}
		return littleItem;

	}

	public <Format> void updateInventory(String fileName, String Name, int qtySold) throws IOException {
		// Need to add Try Catch to this method.
		Supplier supplier = new Supplier();
		File inventoryFile = new File(fileName);
		String filewrite = "";
		String fileOutput = "";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		if (inventoryFile.exists()) {
			while (br.ready()) {
				String readStr = br.readLine();
				String[] output = readStr.split(" ");
				// System.out.println("Output Length: " + output.length);
				if (output[1].equals(Name)) {
					System.out.println(Name);
					System.out.format(
							"%-14s %-14s %-14s %-14s %-14s %-14s %-14s %-14s %-20s " + System.getProperty("line.separator"),
							"Item Number", "Product Name", "Price", "Inventory Qty", "Threshold Qty", "Restock Qty",
							"Supplier Name", "Order Number", "Date Time");
					int qty = Integer.parseInt(output[3]);
					int threshold = Integer.parseInt(output[4]);
					int reorder = Integer.parseInt(output[5]);
					if (qty - qtySold <= threshold) {
						qty = reorder + qty - qtySold;
						supplier.setOrderNum();
						output[7] = Integer.toString(supplier.getOrderNum());
					} else {
						qty = qty - qtySold;
					}
					readStr = output[0] + " " + output[1] + " " + output[2] + " " + Integer.toString(qty) + " "
							+ Integer.toString(threshold) + " " + Integer.toString(reorder) + " " + output[6] + " "
							+ output[7] + " " + dateTime;
					filewrite += readStr + System.getProperty("line.separator");
				} else {
					filewrite += readStr + System.getProperty("line.separator");
				}
				fileOutput += String.format("%-14s %-14s %-14s %-14s %-14s %-14s %-14s %-14s %-20s", output[0],
						output[1], output[2], output[3], output[4], output[5], output[6], output[7],
						dateTime + System.getProperty("line.separator"));
			}

		} else {
			System.out.println("File Doesn't exist");
		}
		br.close();
		// Added delete inventory file here:
		// inventoryFile.delete();
		BufferedWriter outBuffer = new BufferedWriter(new FileWriter(fileName));
		System.out.println(fileOutput);
		outBuffer.write(filewrite);
		outBuffer.close();

	}

	// public void setOrderNum() {
	// System.out.println("I'm in Inventory's setOrderNum!" + this.itemNum);
	// Random rand = new Random();
	// orderNum = rand.nextInt(100);
	//
	// }
	//
	// public int getOrderNum() {
	// return orderNum;
	// }

//	public void setDate(Date DateTime) {
//		this.dateTime = DateTime;
//	}

//	public Date getDate() {
//		return dateTime;
//	}

	// public boolean received(boolean shipped) {
	// return true;
	// }

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductName() {
		return productName;
	}

	// Need to tie productName all of the signature variables below except
	// manager and received

	public void setItemNum(int itemNumber) {
		this.itemNum = itemNumber;
	}

	public int getItemNum() {
		return itemNum;
	}

	public void setInventoryQty(int inventoryQty) {
		this.inventoryQty = inventoryQty;
	}

	public float getInventoryQty(int inventoryQty) {
		return inventoryQty;
	}

	public void reStock(int restockQty) {
		this.inventoryQty = this.reStockQty + inventoryQty;
	}

	public void removeItem(String productName) {
		this.productName = "removed from stock";
	}

	public void setItemThreshold(int thresholdQty) {
		this.thresholdQty = thresholdQty;
	}

	public int getItemThreshold() {
		return thresholdQty;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getPrice() {
		return price;
	}

	public void setManagerUserName(String managerUserNames) {
		this.managerUserName = managerUserNames;
	}

	public String getManagerUserName() {
		return managerUserName;
	}

	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}

	public String getManagerPassword() {
		return managerPassword;
	}

	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat( "MM/dd/yy" );
		String dateTime = dateFormat.format(new Date());
		return dateTime;
	}
}
