package board.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import board.dao.BoardDao;
import board.model.Board;

public class BoardListService {
	
	private BoardDao boardDao;
	
	// 의존하는 BoardDao 객체를 주입받기 위한 세터 메소드 작성
	public void setBoardDao(BoardDao boardDao){
		// 매개변수로 전달받은 BoardDao 객체를 받아서 내부에 BoardDao 객체변수에 연결한다.
		this.boardDao = boardDao;
	}

	// 트랜젝션 어노테이션 적어주면, 
	// 커넥션을 주입할 때 자동으로 오토커밋 off, 익셉션 발생하면 자동 롤백을 적어준다.
	// 일종의 Apspect 프로그램
	@Transactional
	public void process(){
		System.out.println("BoardListService.process() 실행");
		List<Board> list = boardDao.list();
		
		for(Board board:list){
			// board.toString();
			System.out.println(board);
			
		}
	}
	
}
