package chap02;

import org.springframework.context.support.GenericXmlApplicationContext;

public class main {

	public static void main(String[] args) {
		
		// Context 란 특정 목적을 위해 실행할 것을 담아놓는 것이다.
		// xml어플리케이션문백 타입으로 ctx 인스턴스 생성
		// 인수로는 메이븐의 기본 classpath(src/main/resources) 안에 applicationContext.xml 이다.
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:applicationContext.xml");
		// 이제 이 문맥에서 정의된 greeter bean 을 가져오고, 이 때 제너릭의 타입이 될 클래스로 Greeter.class 다.
		// bean 은 객체를 생성해서 저장해놓는 곳이다.
		// 즉 ctx 는 생성된 여러 bean 을 가지고 있다. 이런경우를 알지? 컬렉션!!!
		// 그래서 해당 bean 을 꺼내서 쓰면 리턴타입이 object 니까.  Greeter.class 로 캐스팅 한다.
		// (Greeter)
		Greeter g = ctx.getBean("greeter",Greeter.class);
		// 문자열 msg 에다가 greet 메소드를 호출한다?
		// Greeter 에 greet 메소드를 호출한다.
		// 
		String msg = g.greet("스프링", "갑돌이");
		System.out.println(msg);
		
		// ctx 컬렉션에서 printname bean 을 꺼내는데 PrintName 클래스로 제너릭을 정해줘서 형변환한다.
		PrintName p1 = ctx.getBean("printname",PrintName.class );
		p1.printName();
		
		// ctx 에 여러 bean 인스턴스가 생성되어 있는데 쓸모가 없으니 다 폐기시켜버린다.
		// ApplicationContext 인터페이스에는 close() 가 없지만
		// GenericXmlApplicationContext  에는 close() 가 있다.
		ctx.close();			
		}
}
