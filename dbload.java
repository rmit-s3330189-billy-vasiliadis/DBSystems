import java.io.*;

public class dbload {
	
	static int recordSize;

	public static void main(String[] args) {
		int pageSize = 4096;
		int noOfBytesRead = 0;
		int noOfPages = 0;
		int noOfRecords = 0;
	
		BufferedReader input = null;
		DataOutputStream output = null;

		try{
			input = new BufferedReader(new FileReader("test.csv"));
			output = new DataOutputStream(new FileOutputStream("result.txt")); 
			//we ignore the first line which is the schema, we don't want to write this to the heap file
			String line = input.readLine();
			while((line = input.readLine()) != null) {
				String[] record = line.split("\t");
				printTokens(record);
				
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}	
	
	public static void printTokens(String[] record) {
		for(int i = 0 ; i < record.length ; i++) {
			System.out.println(record[i]);
		}	
	}
}
