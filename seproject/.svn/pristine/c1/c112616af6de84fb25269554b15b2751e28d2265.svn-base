package com.webjjang.qna.view;

import java.util.List;

import com.webjjang.board.model.BoardBean;
import com.webjjang.board.model.BoardCommentBean;
import com.webjjang.qna.model.QnaBean;
import com.webjjang.util.Util;

public class QnaDisplay {

	public QnaBean inputQnaData(){
		QnaBean bean = new QnaBean();
		// 데이터를 받는다. - 글제목, 글내용, 작성자
		bean.setTitle(Util.inputMenuAndData("글제목:"));
		bean.setContent(Util.inputMenuAndData("글내용:"));
		bean.setWriter(Util.inputMenuAndData("작성자:"));
		return bean;
	}
	
	public void list(List<QnaBean> list) {
		// TODO Auto-generated method stub
		// 제목 출력
		System.out.println("글번호/글제목/작성자/작성일/조회수/"
				+"관련글/순서/들여쓰기/부모글");
		Util.printChar('-', 50);
		// 들여쓰기 배수 설정
		int multi = 3;
		for(QnaBean bean : list){
			System.out.print(bean.getNo()+"/");
			Util.printChar
			(' ', bean.getLevNo()*multi, true);
			System.out.print(bean.getTitle()+"/");
			System.out.print(bean.getWriter()+"/");
			System.out.print(bean.getWdate()+"/");
			System.out.print(bean.getTarget()+"/");
			System.out.print(bean.getRefNo()+"/");
			System.out.print(bean.getOrdNo()+"/");
			System.out.print(bean.getLevNo()+"/");
			System.out.print(bean.getParentNo()+"/");
			System.out.println();
		}
		Util.printChar('-', 50);
		System.out.println();
	}

	public void view(QnaBean bean) {
		// TODO Auto-generated method stub
		// 해당 글 출력
		Util.printChar('-', 40);
		System.out.println("글번호:"+bean.getNo());
		System.out.println("글제목:"+bean.getTitle());
		System.out.println("글내용:"+bean.getContent());
		System.out.println("작성자:"+bean.getWriter());
		System.out.println("작성일:"+bean.getWdate());
		System.out.println("조횟수:"+bean.getTarget());
		Util.printChar('-', 40);
	}

	public void updateView(BoardBean bean) {
		// TODO Auto-generated method stub
		System.out.println("1.글제목:"+bean.getTitle());
		System.out.println("2.글내용:"+bean.getContent());
		System.out.println("3.작성자:"+bean.getWriter());
		
	}

}
