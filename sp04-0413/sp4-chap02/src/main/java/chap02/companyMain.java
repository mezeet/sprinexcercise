package chap02;

import org.springframework.context.support.GenericXmlApplicationContext;

public class companyMain {

	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		Company c = ctx.getBean("company",Company.class);
		System.out.println(c.toString());
		
		ctx.close();

	}

}
