import java.io.*;

public class dbquery {
	public static void main(String[] args) {
		FileReader input = null;
		if(args.length < 2) {
			System.out.println("Please provide arguments in this format: <text> <pagesize>");
			System.exit(0);
		}
		String datafile = "heap." + args[args.length - 1] + ".dat";
		String[] bn_name = new String[args.length - 1];
		for(int i = 0 ; i < bn_name.length ; i++) {
			bn_name[i] = "(.*)" + args[i].toLowerCase() + "(.*)";
		}

		try{
			input = new FileReader(datafile);
			String record = "";			
			int c = 0;
			System.out.println("Records found: ");
			long startTime = System.currentTimeMillis();
			while(c != -1) {
				while((c = input.read()) != 37 && c != -1 && c != 10) {
					record += (char)c;
				} 
				if(record != "" && checkBnName(record, bn_name)) {
					System.out.println(record);
				}
				record="";
			}
			long endTime = System.currentTimeMillis();
			System.out.println("\nMilliseconds Taken: " + (endTime - startTime));
		} catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static boolean checkBnName(String record, String[] bn_name) {
		String[] temp = record.split(",");
		String name = temp[1].toLowerCase();
		for(int i = 0 ; i < bn_name.length ; i++) {
			if(name.matches(bn_name[i])) {
				return true;
			}
		}
		return false;
	}
}
