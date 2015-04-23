package board;

import org.springframework.beans.factory.annotation.Autowired;

public class BoardDeleteService {
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	public void setBoardDao(BoardDao boardDao){
		this.boardDao = boardDao;
	}
	
	public void service(){
		System.out.println("BoardDeleteService 에 세터방식으로 주입된 의존성,");
		System.out.println("BoardDeleteService service() 실행");
		boardDao.view();
	}

}
