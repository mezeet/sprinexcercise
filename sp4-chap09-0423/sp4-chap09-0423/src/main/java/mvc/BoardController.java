package mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	
	@RequestMapping("/board/list.do")
	public String list(Model model,
										@RequestParam(value="title", required=false) String title)
	{
			
		model.addAttribute("title",title);
			
		// webapp/WEB-INF/view/board/list.jsp 로 포워드~~~
		return "board/list";
	}

}
