import java.io.*;

public class dbload {
	
	static int recordSize;

	public static void main(String[] args) {
		int pageSize = Integer.parseInt(args[1]);
		String datafile = args[2];
		int noOfBytesRead = 0;
		int noOfPages = 1;
		int noOfRecords = 0;
	
		BufferedReader input = null;
		DataOutputStream output = null;

		try{
			input = new BufferedReader(new FileReader(datafile));
			output = new DataOutputStream(new FileOutputStream("heap." + pageSize + ".dat")); 
			//we ignore the first line which is the schema, we don't want to write this to the heap file
			String line = input.readLine();
			while((line = input.readLine()) != null) {
				String[] record = line.split("\t");
				setSizeOfRecord(record);
				if((noOfBytesRead + recordSize) > pageSize) {
					noOfBytesRead = recordSize;
					noOfPages++;
					//write out a new line, then write out the record on the new page
					output.write(10);
					writeOutRecord(record, output);
					
				} else {
					noOfBytesRead += recordSize;
					writeOutRecord(record, output);
				}
				noOfRecords++;
			}
			System.out.println("Number of pages written: " + noOfPages);
			System.out.println("Number of records written: " + noOfRecords);
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

	public static void writeOutRecord(String[] record, DataOutputStream output) {
		try {
			byte[] data;
			int i;
			for(i = 0 ; i < record.length-1 ; i++) {
				data = record[i].getBytes();
				output.write(data);
				output.write(44);
			}
			data = record[i++].getBytes();
			output.write(data);
			output.write(37);
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
