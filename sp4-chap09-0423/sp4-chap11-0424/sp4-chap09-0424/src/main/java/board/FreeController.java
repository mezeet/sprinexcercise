package board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.service.BoarViewService;


@Controller
public class FreeController {
	
	private BoarViewService boardViewService;
	
	public void setBoardViewService(BoarViewService boardViewService){this.boardViewService = boardViewService;}

	@RequestMapping(value="/board/freeview.do")
	public String view(Model model,@RequestParam(value="no",required=true) int no){
	   FreeBoard freeBoard = boardViewService.view(no);
	    model.addAttribute("freeBoard", freeBoard);
		return "freeboard/view";
	}
	
	
	@RequestMapping(value="/board/freelist.do")
	public String list(Model model){
	
		return "freeboard/list";
	}
}
