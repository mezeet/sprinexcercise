package mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoticeController {
	
	@RequestMapping("/notice/list.do")
	public String list(Model model, String title)
	{
		// webapp/WEB-INF/view/board/list.jsp 로 포워드~~~
		return "notice/list";
	}
	
	@RequestMapping("/notice/view.do")
	public String list(Model model,	@RequestParam(value="no", required=false) int no)
	{
			
		model.addAttribute("no",no);
			
		// webapp/WEB-INF/view/notice/list.jsp 로 포워드~~~
		return "notice/view";
	}

}
