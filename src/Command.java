import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Command {
	
	public static float drawerAmount; //need a way to set drawer amount to the most recent drawer amount upon start of program, if the drawer was not emptied out.
	
	private int regID;	
	private String userID;
	private Date logOff;
	private Date logOn;
	private boolean currentLoggedIn=false;
	
	private final String USERID1="1";
	private final String USERID2="2";
	private final String USERID3="3";
	private final String userPassword="1";
	
	private final String HELP_COMMAND="help";
	private final String LOGIN_COMMAND ="login";
	private final String LOGOUT_COMMAND="logout";
	private final String END_PROGRAM_COMMAND="end program";
	private final String CURRENT_USER_COMMAND ="current user";
	private final String NEW_SALE_COMMAND="new sale";
	private final String PRINT_RECEIPT_COMMAND="print receipt";
	private final String RETURN_ITEM_COMMAND="return item";
	private final String DRAWER_AMOUNT_COMMAND ="current drawer";
	private final String CANCEL_SALE_COMMAND="cancel sale";
	
	
	public Command () throws IOException {
		
		String userCommand;
		Scanner input = new Scanner (System.in);
		
		setLogIn();
		
		System.out.println("Hello welcome to Awesome Tiger Store!");
		System.out.println("-------------------------------------");
		System.out.println("-------------------------------------");
		System.out.println("-------------------------------------");
		System.out.println("  ");
		
		do {
		
			System.out.println("Please enter in new command (type 'help' for list of commands):  ");
			userCommand = input.nextLine();
			
			while (!currentLoggedIn) {
				setLogIn();
			}
			
			switch (userCommand.toLowerCase()) {
			case HELP_COMMAND: 
				help();
				break;
			case LOGIN_COMMAND: 
				System.out.println("Logging in");
				setLogIn();
				break;
			case LOGOUT_COMMAND: 
				System.out.println("Logging out");
				setLogOut();
				break;
			case END_PROGRAM_COMMAND: 
				System.out.println("Ending program");
				endCurrentProgram();
				break;
			case CURRENT_USER_COMMAND:
				System.out.println("Current user is: " + getUserID());
				break;
			case NEW_SALE_COMMAND: 
				System.out.println("You entered new sale command");
				newSale();
				break;
			case PRINT_RECEIPT_COMMAND: 
				System.out.println("You entered print receipt command");
				printReceipt();
				break;
			case RETURN_ITEM_COMMAND:
				System.out.println("You entered return item command");
				returnItem();
				break;
			case DRAWER_AMOUNT_COMMAND:
				System.out.println("Current drawer amount is: " + drawerAmount );
				break;
			default:
				System.out.println("Invalid command... please enter in new command");
				break;
			}
		}
		while (!END_PROGRAM_COMMAND.equals(userCommand.toLowerCase()) );
		
	input.close();	
	}
	

	public void help() {
		System.out.println("Here is a list of commands.");
		System.out.println("  ");
		System.out.println(LOGIN_COMMAND + ": user logs in");
		System.out.println(LOGOUT_COMMAND+ ": user logs out");
		System.out.println(END_PROGRAM_COMMAND+": ends this program");
		System.out.println(CURRENT_USER_COMMAND + ": returns current user id");
		System.out.println(NEW_SALE_COMMAND + ": initiates new sale command");
		System.out.println(PRINT_RECEIPT_COMMAND + ": prints receipt");
		System.out.println(RETURN_ITEM_COMMAND + ": initiates return item command");
		System.out.println(DRAWER_AMOUNT_COMMAND +": returns current total drawer amount"); 
	}
	
	public void setRegID(int regID) {
		
		this.regID = regID;
		
	}
	
	public int getRegID() {
		
		return regID;
		
	}
	
	public void setUserID(String newUserID) {
		
		userID =newUserID;
	}
	
	public String getUserID() {
		
		return userID;
		
	}
	
	public String getUserPwd() {
		
		return userPassword;
		
	}
	
	//public int getMgrID() {
		
		
	//}
	
	//public String getMgrPwd() {
		
		
	//}
	
	public void setLogIn() {
		
		Scanner input = new Scanner (System.in);
		
		if (currentLoggedIn) { 
			
			System.out.println("You are already logged in.  Please logout, before logging in with different user");
			
		}
		
		else {
		
		String dummyUser;			
		String dummyPass;
		
		do {
			
		
		System.out.println("Please enter in your user name: ");
		dummyUser = input.nextLine();
		
		input.nextLine();
		
		System.out.println("Please enter in your password: ");		
		dummyPass = input.nextLine();
		
		}
		
		while (!dummyUser.equals(USERID1) && !dummyPass.equals(userPassword) || !dummyUser.equals(USERID2) && !dummyPass.equals(userPassword)|| !dummyUser.equals(USERID3) && !dummyPass.equals(userPassword));

		currentLoggedIn =true;	
		
		userID = dummyUser;
		
		Date date = new Date();
		
		logOn =date;
		
		try {
			
			System.out.println("Please enter register number: ");
			regID = input.nextInt();
			
		}
		
		catch (InputMismatchException e ) {

			
			System.out.println("Please enter a number without alphabet or symbol");
			input.nextLine();
		}
		
		System.out.println("Logged in at: " + logOn);
		
		}
		
	}
	
	public Date getLogIn() {
		
		return logOn;


	}
	
	public void setLogOut() {
		
		currentLoggedIn =false;
		
		Date date = new Date();
		
		logOff =date;
		
		System.out.println("You logged off at: " +logOff);
		//probably will have to do additional things here
		
	}
	
	public Date getLogOut() {
		
		return logOff;
		
	}
	
	public void endCurrentProgram() {
		
		//need to enter in what happens when program ends
		
	}
	
	public void newSale () throws IOException {
		
		Register newRegister = new Register (regID, userID); //will need to udpate when other parts of the program is completed
		
	}
	
	public void printReceipt () {
		
		//this will need the rest of the program to be completed
	}
	
	public void returnItem() {
		
		//this will need the rest of the program to be completed
		
	}
	
	private void setDrawerAmount () {
		
		//need to set drawer amount to the last drawer amount when the program ended previously.  
		
	}
	


	

}
