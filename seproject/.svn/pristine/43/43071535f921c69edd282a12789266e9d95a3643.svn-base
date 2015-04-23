package com.webjjang.board.service;

import com.webjjang.action.ControlerInterface;
import com.webjjang.menu.MenuList;
import com.webjjang.util.Util;

public class Board implements ControlerInterface{
	public void process(){
		// 제목 출력
		String str="자유 게시판 입니다.";
		Util.printTitleAndMenu('=',Util.toStringArray(str));
		// 메뉴 반복문
		while(true){
			// 메뉴를 출력한다.
			Util.printTitleAndMenu
			(MenuList.getMenu("boardMenu"));
			// 메뉴 입력
			String menu = 
				Util.inputMenuAndData("메뉴를 선택하시오:");
			// 메뉴 처리
			if(process(menu)) return;
		}
	}

	private boolean process(String menu) {
		// TODO Auto-generated method stub
		// 실행할 객체를 생성 처리
		BoardInterface obj = null;
		boolean returnVal=false;
		switch (menu) {
		case "1": //리스트
			System.out.println("게시판 리스트");
			obj = new BoardList();
			break;
		case "2": //글보기
			System.out.println("게시판 글보기");
			obj = new BoardView();
			break;
		case "3": //글쓰기
			System.out.println("게시판 글쓰기");
			obj = new BoardInsert();
			break;
		case "4": //글수정
			System.out.println("게시판 글수정");
			obj = new BoardUpdate();
			break;
		case "5": //글삭제
			System.out.println("게시판 글삭제");
			obj = new BoardDelete();
			break;
		case "6": //조회
			System.out.println("게시판 조회");
			obj = new BoardSearch();
			break;
		case "0":
			System.out.println("이전 메뉴");
			returnVal = true;
			break;
		default:
			System.out.println("잘못된 메뉴를 선택하셨습니다.");
		} //end of switch
		// 생성된 객체를 실행. 단 null이 아닌 경우만
		if(obj!=null) obj.service();
		
		return returnVal;
		
	}// end of process()
}
