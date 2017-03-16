

	import java.io.*;
	import java.lang.reflect.Type;
	import java.util.ArrayList;
	import com.google.gson.Gson;
	import com.google.gson.reflect.TypeToken;
	import com.google.gson.stream.JsonReader;

	public class SalesReportReader {
		
		String SALE_COMPLETED_FILE= "completedSale.json";
		ArrayList <Item> itemInSale = new ArrayList <Item>();
		ArrayList <Sale> sale = new ArrayList <Sale> ();
		ArrayList<CompletedSale> completedSaleReport;
		Type SALECOMPLETEDTYPE = new TypeToken<ArrayList<CompletedSale>>() {}.getType();
		ArrayList<CompletedSale> data;
		
	public SalesReportReader(CompletedSale updateSaleReport) throws FileNotFoundException {

		Gson gson = new Gson();
		
		try (JsonReader jreader = new JsonReader(new FileReader(SALE_COMPLETED_FILE))) {
				
				data= gson.fromJson(jreader, SALECOMPLETEDTYPE);
				
				if	(data!=null) {
				
				completedSaleReport = new ArrayList<CompletedSale>(data);
				completedSaleReport.add(updateSaleReport);
				}
			
			else {
				
				completedSaleReport = new ArrayList<CompletedSale>();
				completedSaleReport.add(updateSaleReport);
				data =completedSaleReport;
			}
			
			for (int x =0; x<completedSaleReport.size(); x++) {
			sale =completedSaleReport.get(x).getSaleArrayList();
			itemInSale = completedSaleReport.get(x).getItemArrayList();
			
			int rowNumber =1;
				System.out.println(sale.get(0));
				for(int y = 0; y<itemInSale.size();  y++) {
					System.out.println("("+rowNumber+") " +itemInSale.get(y));
					rowNumber++;
				}
			}

			
			}
	
		
		catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		gsonWriter(SALE_COMPLETED_FILE, completedSaleReport);
	}
	
	
	
	
		public void gsonWriter (String fileName, ArrayList<CompletedSale> obj) {
		
			
			Gson gson = new Gson();
		
			String json = gson.toJson(obj);
			
			try (FileWriter writer = new FileWriter(fileName)) {
				gson.toJson(obj, writer);
			}
			
			catch (IOException e) {
				e.printStackTrace();
			}
		}

	
	
	
	
	
	
	}


	
	
	

