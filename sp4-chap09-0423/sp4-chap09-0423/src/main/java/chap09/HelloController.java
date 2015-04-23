package chap09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//잘 보면, `extends 서블릿` 하지 않았는데도, @Controller 어노테이션을 통해서 서블렛을 실행한다.
@Controller
public class HelloController {
	// 예전 같으면 이 설정은 web.xml 에 일일히 모두 설정되어야 하는데
	// 그냥 스프링의 Dispatcher 가 알아서 처리하기 때문에 어노테이션 표시만 해도 
	// 주소가 맵핑이 된다.
	@RequestMapping("/hello")
	public String hello(Model model,
			// 위에 model은 처리 결과를 담아서 view 에 전달하게 될 값이 담기는 객체를 말한다.
			// EL 객체로 사용할 수 있다. 즉, request 객체의 역할을 한다.
			
			// 이 부분은 원래 다음의 형식으로 값을 얻어와야 하는데
			//String name = request.getParameter("name") 
			// 어노테이션으로 받아 온 다음 String name 으로 넘겨준다..
			// 즉, `/hello?name=이름` 으로 url 요청을 하면 
			@RequestParam(value = "name", required = false) String name) {
		
		// request.setAttribute("greeting", "안녕하세요, " + name)
		model.addAttribute("greeting", "안녕하세요, " + name);
		
		// 디스패처.포워드(jsp 파일) 과 같은데... 
		// 우리가 spring-mvc.xml 에  <mvc:jsp prefix="/WEB-INF/view/" /> 로 접두사를 설정해서
		// hello 만 찾으라고 하면 결과적으로 /WEB/INF/view/hello 가 되고,
		// .jsp 접미사는 기본으로 붙기 때문에 /WEB/INF/view/hello.jsp 가 최종적으로 요청된다.
		return "hello";
	}
}
