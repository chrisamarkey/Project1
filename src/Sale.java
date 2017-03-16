


	import java.util.Date;
	

		public class Sale {

		private int saleID;
		private int regID;
		private String userID;
		private Date saleDateTime;
		private float saleTotal;
	
		Sale (int saleID, int regID, String userID, Date saleDateTime, float saleTotal) {
			
			this.saleID = saleID;
			this.regID = regID;
			this.userID = userID;
			this.saleDateTime = saleDateTime;
			this.saleTotal = saleTotal;
			
		}
		
		public int getRegID() {
			
			return regID;
			
		}
		public void setRegID(int regID) {
			
			this.regID = regID;
			
		}
		
		public void setUserID(String newUserID) {
			
			userID =newUserID;
		}
		
		public String getUserID() {
			
			return userID;
			
		}
		
		public void setSaleID (int saleID) {
			
			this.saleID =saleID;
		}
		
		public int getSaleID() {
			
			return saleID;
			
		}
		
		public void setSaleTotal (float saleTotal) {
			this.saleTotal = saleTotal;
		}
		
		public String toString () {
			
			return "Sale Number: " + saleID +";" +" register ID: " + regID+";"+ " user ID: " +userID +";"+" sale date & time: " +saleDateTime +";"+"  sale total: " +saleTotal +"\n";
			
		}
		

		

	}

