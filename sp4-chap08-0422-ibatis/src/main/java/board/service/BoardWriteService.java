package board.service;

import java.util.List;

import board.dao.BoardDao;
import board.model.Board;

public class BoardWriteService {

		private BoardDao boardDao;
		
		public void setBoardDao(BoardDao boardDao){
			this.boardDao = boardDao;
		}
		
		public void process(){
		
		System.out.println("보드라이트서비스.process()");
		// Board 생성은 view 용에 맞춰서 생정자를 넣어준다.
		
		Board board = new Board("제목입니다.","제목입니다.","작성자");
		boardDao.write(board);
		
		List<Board> list = boardDao.list();
		
		for(Board bd:list ){
			System.out.println(bd);
		}
		
		}
}
