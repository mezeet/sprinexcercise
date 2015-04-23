package Util; 
/*
 * 필요한 기능을 static 메소드로 선언
 */
public class Util {
	// 주어진 문자를 갯수만큼 찍자
	// 별표 10개 찍자 : Util.printChar('*', 10)
	public static void printChar(char ch, int cnt){
		for(int i = 1; i <= cnt; i++){
			System.out.print(ch);
		}
		System.out.println();
	}
	
	public static void printChar
	(char ch, int cnt, boolean notNewLine){
		for(int i = 1; i <= cnt; i++){
			System.out.print(ch);
		}
	}
	// 문자 배열을 받아서 갯수 만큼 출력한다.
	// 출력된 배열의 갯수가 cnt이면 줄바꿈한다.
	public static void printArrayString(String[] str, int cnt){
		// 배열의 숫자 만큼 반복처리
		for(int i = 1; i <= str.length; i++){
			System.out.print(str[i - 1]);
			if(i % cnt == 0){// 정해진 갯수만큼 출력 후
				System.out.println(); // 줄바꿈
			}else{
				System.out.print("   "); // 3칸 띄움
			}
		}
		if(str.length % cnt != 0) System.out.println();
	}
	
	// 입력 메시지 출력 후 데이터 입력 String return
	public static String inputMenuAndData(String msg) {
		System.out.print(msg);
		return InputData.inputData();
	}

	// 입력 받은 데이터가 숫자인 메소드
	public static int inputInt(String msg) {
		while (true) {
			try {
				System.out.print(msg);
				// String형 데이터를 숫자형으로 변환하고 int형에 넣는다
				return Integer.parseInt(InputData.inputData());
			} catch (NumberFormatException e) {
				// int형엔 문자가 들어갈수 없음으로 들어가면 인셉션이
				// 일어나며 밑으로 캐치문으로 이동한다
				System.out.println("숫자를 입력하세요!");
			}
		}
	}
	
	// 제목이나 메뉴를 특수문자와 함께 출력하는 메소드
	// ch:출력할 위,아래 특수문자
	// cnt:위, 아래에 출력할 특수문자 갯수
	// str[]:가운데 출력한 메뉴나 제목
	// sep:메뉴출력시 한줄에 출력될 메뉴의 갯수
	public static void printTitleAndMenu
	(char ch, int cnt, String str[], int sep){
		printChar(ch, cnt);
		printArrayString(str, sep);
		printChar(ch, cnt);
	}// end of printTitleAndMenu()
	// 제목이나 메뉴를 특수문자와 함께 출력하는 메소드
	// 출력할 특수 문자가 *인 기본값인 메소드
	public static void printTitleAndMenu
	(int cnt, String str[], int sep){
		char ch = '*'; // 기본값이 *
		printTitleAndMenu(ch, cnt, str, sep);
	}	
	// 제목이나 메뉴를 특수문자와 함께 출력하는 메소드
	// 한줄에 출력한 메뉴 갯수의 기본값이 3인 메소드
	public static void printTitleAndMenu
	(char ch, int cnt, String str[]){
		int sep = 3;
		printTitleAndMenu(ch, cnt, str, sep);
	}
	// 제목이나 메뉴를 특수문자와 함께 출력하는 메소드
	public static void printTitleAndMenu
	(int cnt, String str[]){
		char ch = '*';
		int sep = 3;
		printTitleAndMenu(ch, cnt, str, sep);
	}
	// 제목이나 메뉴를 특수문자와 함께 출력하는 메소드
	public static void printTitleAndMenu
	(char ch, String str[]){
		int cnt = 30;
		int sep = 3;
		printTitleAndMenu(ch, cnt, str, sep);
	}
	// 제목이나 메뉴를 특수문자와 함께 출력하는 메소드
	public static void printTitleAndMenu
	(String str[]){
		char ch = '*';
		int cnt = 30;
		int sep = 3;
		printTitleAndMenu(ch, cnt, str, sep);
	}
	// 1개의 String을 배열로 만들어 주는 메소드
	public static String[] toStringArray(String str){
		String[] arr = {str};
		return arr;
	}
}
