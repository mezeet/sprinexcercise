package board.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import board.dao.BoardDao;
import board.model.Board;

public class BoardViewService {
	
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
		Board board= boardDao.view(2);
		System.out.println("게시판 글보기=========================");
		System.out.println("글번호 :"+board.getNo());
		System.out.println("글제목 :"+board.getTitle());
		System.out.println("작성자 :"+board.getWriter());
		System.out.println("내용 :"+board.getContent());
		System.out.println("작성일 :"+board.getWdate());
		System.out.println("조회수 :"+board.getTarget());
	}
	
}
