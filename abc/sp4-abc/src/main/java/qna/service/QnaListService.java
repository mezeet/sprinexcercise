package qna.service;

import java.util.List;

import qna.dao.QnaDao;
import qna.model.Qna;

public class QnaListService {

	// QnaDao 객체
	private QnaDao qnaDao;
	
	// 1-2-1. QnaDao 객체 주입받는다.
	public QnaListService(QnaDao qnaDao){
		
		this.qnaDao = qnaDao;
	}
	
	// 1. 목록을 보여주는데 -외부에서 process()메소드를 호출하면, QnaDao.list() 메소드 실행해서 목록을 받아오고 출력한다
	// 1-1. process 메소드 정의
	public void process(){
		
		// 1-2. QnaDao.list() 메소드 실행해서 목록을 받아오고 출력한다	
		
		// 1-2-2. QnaDao.list() 메소드 호출
		// 1.-2-3. 결과값을 List<Qna> 목록을 받는다.
      
		List<Qna> qnaList = qnaDao.list();
		// 1-2-4. 출력한다.
		// for(원소타입 원소이름 : 목록)
		for(Qna qna : qnaList){
			System.out.println(qna);
		}

	}
	
}
