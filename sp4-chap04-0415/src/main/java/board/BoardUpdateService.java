package board;

import org.springframework.beans.factory.annotation.Autowired;

public class BoardUpdateService {
	
	private BoardDao boardDao;
	
	@Autowired
	public BoardUpdateService(BoardDao boardDao){
		this.boardDao = boardDao;
	}
	
	public void service(){
		System.out.println("BoardUpdateService 에 세터방식으로 주입된 의존성,");
		System.out.println("BoardUpdateService service() 실행");
		boardDao.view();
	}

}
