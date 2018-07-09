package convert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class ReviewDataConvert {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new FileReader("C:\\ProgramData\\MySQL\\MySQL Server 5.7\\Uploads\\3000.csv"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("data\\3000user.csv"));

		String line;
		while((line=br.readLine())!=null) {
			String value=line.substring(4);
			bw.write(value+"\n");
		}
		br.close();
		bw.close();

	}

}
