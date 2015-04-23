package goods;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:goods.xml");
		GoodsListService goodsListService = ctx.getBean("goodsListService",GoodsListService.class);
		goodsListService.service();

	}

}
