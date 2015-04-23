package Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 키보드로 데이터를 입력 받아 
 * 스트링(문자열)로 전달하는 메소드 구현
 */
public class InputData {
	public static String inputData(){
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		try {
			return reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
