package board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class FreeController {
	
	// 테스트 용 자료 생성
	List<FreeBoard> freeBoardList = new ArrayList<FreeBoard>();
	
	FreeBoard fb1 = new FreeBoard(1,"제목1","내용1","작성자1");
	FreeBoard fb2 = new FreeBoard(2,"제목2","내용2","작성자2");
	FreeBoard fb3 = new FreeBoard(3,"제목3","내용3","작성자3");
	FreeBoard fb4 = new FreeBoard(4,"제목4","내용4","작성자4");

	@RequestMapping(value="/board/freelist.do")
	public String list(Model model){
		
		// 자료 세팅
		freeBoardList.add(fb1);
		freeBoardList.add(fb2);
		freeBoardList.add(fb3);
		freeBoardList.add(fb4);
		
		model.addAttribute("FBList", freeBoardList);
	
		return "freeboard/list";
	}
}
