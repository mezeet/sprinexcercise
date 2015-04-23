package board;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:board.xml");
		BoardUpdateService boardUpdateService = ctx.getBean("boardUpdateService",BoardUpdateService.class);
	  BoardDeleteService boardDeleteService = ctx.getBean("boardDeleteService",BoardDeleteService.class);
	  
	  boardUpdateService.service();
	  boardDeleteService.service();
	}

}
