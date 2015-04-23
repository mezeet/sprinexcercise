package chap02;

/*
 * pojo : 원래 실행하려는 프로그램이다.
 */

public class Greeter {

	// applicatioinContext.xml 에서
	// 해당 변수를 속성으로 잡아주고, 값을 집어 넣는다.
	// "%s, 안녕하세요!" 로, 포맷을 집어넣은 문자열을 집어넣는다.
	private String format;
	
	// 
	public String greet(String guest, String host){
		// xml에서 집어넣은 "%s, 안녕하세요!" 에
		// %s 위치에 인자로 넘겨 받은 guest 문자열을 치환해서 돌려준다.
		// String 은 java.lang 에 집어넣어져 있으므로 새로 임포트 해줄 필요가 없다.
		return String.format(format, guest, host);
		
	}
	
	// applicationContext.xml 에서 jsp 방식으로 값을 설정할 수 있도록
	//  세터를 생성해준다. 
	public void setFormat(String format){
		this.format = format;
	}
	
}
