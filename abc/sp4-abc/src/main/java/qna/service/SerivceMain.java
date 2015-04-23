package qna.service;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SerivceMain {

	public static void main(String[] args) {
	
	GenericXmlApplicationContext ctx 
		= new GenericXmlApplicationContext("classpath:appCtx.xml");
			

	QnaListService qList = ctx.getBean("qList",QnaListService.class); 
	// 1-2.생성된 객에 process() 메소드를 호출한다.
	qList.process();
	System.out.println("process 가 종료 됐으니가 실행됨");
	}
}
