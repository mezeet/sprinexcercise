package board.service;

import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardMain {
	
	public static void main(String[] args) {
		
		// 스프링의 어플리케이션컨텍스트를 생성한다.
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx.xml");
		BoardListService bls = ctx.getBean("boardList",BoardListService.class);
		bls.process();
		
		BoardViewService bvs = ctx.getBean("boardView",BoardViewService.class);
		bvs.process();
		
		BoardWriteService bws = ctx.getBean("boardWrite",BoardWriteService.class);
		bws.process();
		
		BoardUpdateService bus = ctx.getBean("boardUpdate",BoardUpdateService.class);
		bus.process();
		
		ctx.close();		
	}

}
