package mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	
	@RequestMapping("/board/list.do")
	public String list(Model model, String title)
	{
		// webapp/WEB-INF/view/board/list.jsp 로 포워드~~~
		return "board/list";
	}
	
	@RequestMapping("/board/view.do")
	public String list(Model model,	@RequestParam(value="no", required=false) int no)
	{
		model.addAttribute("no",no);
		// webapp/WEB-INF/view/board/list.jsp 로 포워드~~~
		return "board/view";
	}
	
	@RequestMapping("/board/write.do")
	public String write()
	{
		return "board/write";
	}
	
	@RequestMapping(value="/board/writeProcess.do")
	public String writeProcess(Model model, Board board)
	{
		model.addAttribute("board", board);		
		// webapp/WEB-INF/view/board/list.jsp 로 포워드~~~
		return "board/writeResult";
	}
	
	@RequestMapping("/board/update.do")
	public String update()
	{
		return "board/update";
	}
	
	@RequestMapping(value="/board/udateProcess.do")
	public String updateProcess(Model model, Board board)
	{
		model.addAttribute("board", board);		
		// webapp/WEB-INF/view/board/list.jsp 로 포워드~~~
		return "board/updateResult";
	}

}
