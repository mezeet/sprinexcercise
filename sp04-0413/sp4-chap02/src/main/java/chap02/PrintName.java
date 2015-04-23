package chap02;

/*
 * pojo : 원래 실행하려는 프로그램이다.
 */

public class PrintName {

	// applicatioinContext.xml 에서
	// 해당 변수를 속성으로 잡아주고, 값을 집어 넣는다.
	// "%s, 안녕하세요!" 로, 포맷을 집어넣은 문자열을 집어넣는다.
	private String name;
	
	public void setName(String name){
		this.name=name;
	}
	public void printName(){
		System.out.println("자기이름" +name);
	}
}
