package convertp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/*
 * u.data를 콤마로 구분되어 있는 csv파일로 만들기 위한 클래스
 * u.data를 읽어와 movies.csv파일을 만든다.
 * u.data에는 타임스탬프도 있으나 이 프로젝트에서는 타임스탬프를 지웠다.
 * 0번에는 사용자(userid),아이템(itemid),점수(rating)이다. 
 */

public class MovieDateConvert {

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("data/u.data"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("data/movies.csv"));
		
		String line;
		while((line=br.readLine())!=null) {
			String[] values=line.split("\\t",-1);
			bw.write(values[0]+","+values[1]+","+values[2]+"\n");
		}
		br.close();
		bw.close();
	}

}
