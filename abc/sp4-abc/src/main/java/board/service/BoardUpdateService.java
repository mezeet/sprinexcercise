package board.service;

import board.dao.BoardDao;
import board.model.Board;

public class BoardUpdateService {

	private BoardDao boardDao;
	
	public void setBoardDao(BoardDao boardDao){
		this.boardDao = boardDao;
	}
	
	public void process(){
		Board board = new Board(250,"생성자","유동적이야","이영환");
		
		boardDao.list().toString();
	}
}
