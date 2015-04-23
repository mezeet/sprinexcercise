package com.webjjang.board.view;

import java.util.List;

import com.webjjang.board.model.BoardBean;
import com.webjjang.board.model.BoardCommentBean;
import com.webjjang.util.Util;

public class BoardDisplay {

	public BoardBean inputBoardData(){
		BoardBean bean = new BoardBean();
		// 데이터를 받는다. - 글제목, 글내용, 작성자
		bean.setTitle(Util.inputMenuAndData("글제목:"));
		bean.setContent(Util.inputMenuAndData("글내용:"));
		bean.setWriter(Util.inputMenuAndData("작성자:"));
		return bean;
	}
	
	public void list(List<BoardBean> list) {
		// TODO Auto-generated method stub
		// 제목 출력
		System.out.println("글번호/글제목/작성자/작성일/조회수");
		Util.printChar('-', 40);
		for(BoardBean bean : list){
			System.out.print(bean.getNo()+"/");
			System.out.print(bean.getTitle()+"/");
			System.out.print(bean.getWriter()+"/");
			System.out.print(bean.getWdate()+"/");
			System.out.print(bean.getTarget());
			System.out.println();
		}
		Util.printChar('-', 40);
		System.out.println();
	}

	public void view(BoardBean bean,List<BoardCommentBean> list) {
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
		// 댓글 출력
		if(list!= null && list.size()!=0){
			System.out.println("[댓글]");
			for(BoardCommentBean cbean: list){
				System.out.print(cbean.getNo()+"/");
				System.out.print(cbean.getContent()+"/");
				System.out.print(cbean.getWriter()+"/");
				System.out.print(cbean.getWdate());
				System.out.println();
			}
			Util.printChar('-', 40);
		}
		System.out.println();
	}

	public void updateView(BoardBean bean) {
		// TODO Auto-generated method stub
		System.out.println("1.글제목:"+bean.getTitle());
		System.out.println("2.글내용:"+bean.getContent());
		System.out.println("3.작성자:"+bean.getWriter());
		
	}

}
