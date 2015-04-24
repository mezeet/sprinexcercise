package board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.service.FreeViewService;

@Controller
public class FreeController {
	
	private FreeViewService freeViewService;
	
	public void setFreeViewService(FreeViewService freeViewService){this.freeViewService = freeViewService;}

	@RequestMapping(value="/board/freeview.do")
	public String view(Model model,@RequestParam(value="no",required=true) int no){
	   FreeBoard freeBoard = freeViewService.view(no);
	    model.addAttribute("freeBoard", freeViewService.view(no));
		return "freeboard/view";
	}
		
	@RequestMapping(value="/board/freelist.do")
	public String list(Model model){
		return "freeboard/list";
	}
}
