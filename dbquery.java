import java.io.*;

public class dbquery {
	private static String bn_name;	

	public static void main(String[] args) {
		FileReader input = null;
		bn_name = "(.*)" + args[0].toLowerCase() + "(.*)";
		String datafile = "heap." + args[args.length - 1] + ".dat";

		try{
			input = new FileReader(datafile);
			String record = "";			
			int c = 0;
			while(c != -1) {
				while((c = input.read()) != 37 && c != -1 && c != 10) {
					record += (char)c;
				} 
				if(record != "" && checkBnName(record)) {
					System.out.println(record);
				}
				record="";
			}
		} catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static boolean checkBnName(String record) {
		String[] temp = record.split(",");
		String name = temp[1].toLowerCase();
		if(name.matches(bn_name)) {
			System.out.println("Record found: ");
			return true;
		}
		return false;
	}
}
