package board.service;

import board.FreeBoard;
import board.FreeBoardDao;

public class BoarViewService {
	
	private FreeBoardDao freeBoardDao;
	
	public void setFreeBoardDao(FreeBoardDao freeBoardDao){this.freeBoardDao = freeBoardDao;}

	public FreeBoard view(int no){
		
		FreeBoard freeBoard = freeBoardDao.view(no);
		
		return freeBoard;
	}
	
}
