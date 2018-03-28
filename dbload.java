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
				setSizeOfRecord(record);
				if((noOfBytesRead + recordSize) > pageSize) {
					noOfBytesRead = 0;
					noOfPages++;
					//write out a new line, then write out the record on the new page
				} else {
					noOfBytesRead += recordSize;
					//write out the record
				}
				noOfRecords++;
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}	
	
	public static void setSizeOfRecord(String[] record) {
		//I have decided to use a delimiter to seperate fields(,) and records(%), which totals nine delimiters per record
		//Each character is equal to 1 byte, therefore the initial record size is 9 bytes	
		recordSize = 9;
		for(int i = 0 ; i < record.length ; i++) {
			//For now, I am considering every field as a String, therefore to get the number of bytes, just get the length
			recordSize += record[i].length();		
		}
	}
}
