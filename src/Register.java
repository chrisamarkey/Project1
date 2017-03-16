
import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//this is needed for try (JsonReader jreader = new JsonReader(new FileReader(SALE_COMPLETED_FILE))) 
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class Register {

	private int regID;
	private String userID;
	private static int saleIDNum = 0;
	private int regItemNum; // (note: this keeps track of 1st item, 2nd item,
							// 3rd item and etc)
	private String itemNum;
	private String productName;
	private float price;
	private int saleQty;
	private float saleTotal;
	private Date saleDateTime;
	private boolean saleSuccess = true;
	private boolean cancelSaleTrigger = true;
	private ArrayList<Item> itemInSale = new ArrayList<Item>();
	private ArrayList<Sale> sale = new ArrayList<Sale>();
	private final String HELP_COMMAND = "help";
	private final String CANCEL_ITEM_COMMAND = "cancel item";
	private final String PRINT_RECEIPT_COMMAND = "print receipt";
	private final String CANCEL_CURRENT_SALE_COMMAND = "cancel sale";
	private final String SALE_COMPLETE_COMMAND = "sale completed";

	private final String SALE_COMPLETED_FILE = "completedSale.json";
	private static final Type SALECOMPLETEDTYPE = new TypeToken<ArrayList<CompletedSale>>() {
	}.getType();

	public Register(int regID, String userID) throws IOException {

		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);

		String userCommand;

		this.regID = regID;
		this.userID = userID;

		regItemNum = 1;

		do {
			try {

				System.out.println("Reg Item " + regItemNum + " Please enter in item: ");

				itemNum = input.nextLine();

				switch (itemNum) {
				case HELP_COMMAND:
					help();
					break;
				case CANCEL_ITEM_COMMAND:
					cancelItem();
					break;
				case PRINT_RECEIPT_COMMAND:
					printRecipt();
					break;
				case CANCEL_CURRENT_SALE_COMMAND:
					cancelSale();
					break;
				case SALE_COMPLETE_COMMAND:
					saleComplete();
					break;

				// need to get name of the item. this will be added when project
				// is put togeather

				default:

					Inventory inventory = new Inventory(itemNum);
					productName = inventory.returnToRegister().getProductName();
					price = inventory.returnToRegister().getPrice();

					System.out.println("You entered item num: " + itemNum + " with product name and price:  "
							+ productName + " " + price);
					System.out.println("Reg Item " + regItemNum + " Please enter quantity:  ");
					saleQty = input.nextInt();

					Item item1 = new Item(itemNum, productName, saleQty, price);// will
																				// need
																				// to
																				// udpate
																				// this
																				// with
																				// item
																				// name

					itemInSale.add((regItemNum - 1), item1);
					input.nextLine();
					regItemNum++;

					break;
				}
			}

			catch (InputMismatchException e) {

				System.out.println("Please enter a number without alphabet or symbol");
				input.nextLine();
			}

			catch (NumberFormatException e) {

				System.out.println("Please enter a number without alphabet or symbol");
				input.nextLine();
			}

		} while (cancelSaleTrigger && saleSuccess);
		// while (!SALE_COMPLETE_COMMAND.equals(itemNum.toLowerCase()) ||
		// !CANCEL_CURRENT_SALE_COMMAND.equals(itemNum.toLowerCase()));

	}

	private void help() {
		System.out.println("Here is a list of commands.");
		System.out.println("  ");
		System.out.println(SALE_COMPLETE_COMMAND + ": enter when sale has been completed");
		System.out.println(CANCEL_ITEM_COMMAND + ": enter to cancel item");
		System.out.println(PRINT_RECEIPT_COMMAND + ": initiates print receipt command");
		System.out.println(CANCEL_CURRENT_SALE_COMMAND + ": cancel current sale and return to command prompt");
	}

	public void date() {

		Date newDate = new Date();

		saleDateTime = newDate;
	}

	private void cancelItem() {
		Scanner input = new Scanner(System.in);

		printRecipt();
		System.out.println("Please enter Reg Num for the item you want to cancel");
		itemNum = input.nextLine();

		itemInSale.remove((Integer.parseInt(itemNum) - 1));
		System.out.println("Please verify if item has been removed");
		printRecipt();
		regItemNum = regItemNum - 1;

	}

	private void updateInventory() throws IOException {

		// update inventory by instantiating the inventory class by passing the
		// itemNum and how many of that item got sold
		// for each of the item sold.
		for (int i = 0; i < itemInSale.size(); i++) {
			Inventory inventory = new Inventory(itemInSale.get(i).getProductName(), itemInSale.get(i).getInputQTY());
		}

	}

	private void printRecipt() {
		float receiptTotal;
		System.out.println("");
		date();
		Sale newSale = new Sale((saleIDNum + 1), regID, userID, saleDateTime, totalCalculator(itemInSale));
		ArrayList<Sale> receiptSale = new ArrayList<Sale>();
		receiptSale.add(0, newSale);
		CompletedSale completedSale = new CompletedSale(receiptSale, itemInSale);
		System.out.println(completedSale);
		System.out.println("");
	}

	public float totalCalculator(ArrayList<Item> arrayList) {

		float total = 0;

		for (int i = 0; i < arrayList.size(); i++) {
			total += (arrayList.get(i).getPrice() * arrayList.get(i).getInputQTY());

		}

		return total;

	}

	private void cancelSale() {

		cancelSaleTrigger = false;
		itemNum = CANCEL_CURRENT_SALE_COMMAND;

		System.out.println("You canceled current sale");

	}

	private void updateSalesReport() {

		CompletedSale completedSale = new CompletedSale(sale, itemInSale);
		// ArrayList<CompletedSale> updateSales = new
		// ArrayList<CompletedSale>();
		// updateSales.add(completedSale);
		try {
			SalesReportReader sr = new SalesReportReader(completedSale);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updateCashierReport() {

	}

	private void saleComplete() throws IOException {

		date();
		saleSuccess = false;
		System.out.println("Sale successfully completed");

		saleTotal = totalCalculator(itemInSale);
		System.out.println("Total sale is: " + saleTotal);

		// updates the drawerAmount based on item sold for this sale
		Command.drawerAmount += saleTotal;
		System.out.println("Current drawer total is: " + Command.drawerAmount);

		Sale newSale = new Sale((saleIDNum + 1), regID, userID, saleDateTime, totalCalculator(itemInSale));
		sale.add(0, (newSale));

		printRecipt();
		saleIDNum++;
		updateSalesReport();
		updateCashierReport();
		updateInventory();

		// increment each time we make a new sale. this also needs to account

	}

	public void setRegID(int regID) {

		this.regID = regID; // might not want to set reg id here. will delete
							// later based on gruop

	}

	public int getRegID() {

		return regID;

	}

	public String getUserID() {

		return userID;

	}

	public void setRegItemNum(int regItemNum) {

		this.regItemNum = regItemNum;

	}

	public int getRegItemNum() {

		return regItemNum;

	}

	public void setProductName(String productName) {

		this.productName = productName;
	}

	public String getProductName() {

		return productName;
	}

	public float getPrice() {

		return price;

	}

	public void setSaleQty(int saleQty) {

		this.saleQty = saleQty;

	}

	public int getSaleQty() {

		return saleQty;

	}

	public void setSaleTotal(int saleTotal) {

		this.saleTotal = saleTotal;

	}

	public float getSaleTotal() {

		return saleTotal;

	}

}
