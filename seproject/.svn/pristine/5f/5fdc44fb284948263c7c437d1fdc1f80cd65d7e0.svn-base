package com.webjjang.main;

import com.webjjang.action.ControlerInterface;
import com.webjjang.board.service.Board;
import com.webjjang.member.model.Login;
import com.webjjang.menu.MenuList;
import com.webjjang.qna.service.Qna;
import com.webjjang.shop.service.Shop;
import com.webjjang.util.Util;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 회사 안내문 출력
		String str="웹짱(주) 오신 것을 환영합니다.";
		Util.printTitleAndMenu(Util.toStringArray(str));
		// 메뉴 반복문
		while(true){
			// 로그인 정보를 출력한다.
			printLogin();
			// 메뉴를 출력한다.
			if(Login.id==null){ //로그인을 하지 않은 경우
				Util.printTitleAndMenu
				(MenuList.getMenu("mainLogoutMenu"));
			}else{ //로그인을 한경우
				Util.printTitleAndMenu
				(MenuList.getMenu("mainLoginMenu"));
			}
			// 메뉴 입력
			String menu = 
				Util.inputMenuAndData("메뉴를 선택하시오:");
			// 메뉴 처리
			process(menu);
		}
	}

	private static void process(String menu) {
		// TODO Auto-generated method stub
		// 실행할 객체를 생성 처리
		ControlerInterface obj = null;
		switch (menu) {
		case "1": //회사소개
			System.out.println("회사소개");
			break;
		case "2": //쇼핑몰
			System.out.println("쇼핑몰");
			obj = new Shop();
			break;
		case "3": //게시판
			System.out.println("게시판");
			obj = new Board();
			break;
		case "4": //로그인 또는 로그아웃
			System.out.println("로그인 또는 로그아웃");
			if(Login.id==null){ //로그인 처리
				
			}else{ //로그아웃 처리
				
			}
			break;
		case "5": //회원가입 또는 회원 탈퇴
			System.out.println("회원가입");
			break;
		case "6": //Q&A
			System.out.println("Q&A");
			obj = new Qna();
			break;
		case "0":
			System.out.println("프로그램 종료");
			System.exit(0);
		default:
			System.out.println("잘못된 메뉴를 선택하셨습니다.");
		} //end of switch
		// 생성된 객체를 실행. 단 null이 아닌 경우만
		if(obj!=null) obj.process();
		
	}// end of process()

	private static void printLogin() {
		// TODO Auto-generated method stub
		if(Login.id!=null) //로그인이 되어 있으면 이름 출력
			System.out.println
			(Login.name+"님으로 접속하셨습니다.");
	}

}
