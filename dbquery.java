import java.io.*;

public class dbquery {
	public static void main(String[] args) {
		FileReader input = null;

		try{
			input = new FileReader("result.dat");
			String record = "";			
			int c = 0;
			while(c != -1) {
				while((c = input.read()) != 37 && c != -1 && c != 10) {
					record += (char)c;
				} 
				System.out.println(record);
				record="";
			}
		} catch(Exception e){
			System.out.println(e);
		}

	}


}
