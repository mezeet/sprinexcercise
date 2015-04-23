package com.webjjang.shop.view;

import java.util.List;

import com.webjjang.board.model.BoardBean;
import com.webjjang.board.model.BoardCommentBean;
import com.webjjang.shop.model.PriceBean;
import com.webjjang.shop.model.ShopBean;
import com.webjjang.util.Util;

public class ShopDisplay {

	public ShopBean inputShopData(){
		ShopBean bean = new ShopBean();
		// 데이터를 받는다. - 분류번호,상품명,설명, 제조사,가격
		// 추가적인 입력:제조일, 유통기간, 판매시작일, 판매 종료일
		System.out.println("1:상의,2:하의,3:아웃도어,4:이너웨어,5:악세사리");
		bean.setCateNo(Util.inputInt("분류번호:"));
		bean.setName(Util.inputMenuAndData("상품명:"));
		bean.setContent(Util.inputMenuAndData("상품설명:"));
		bean.setCompany(Util.inputMenuAndData("제조사:"));
		bean.setPrice(Util.inputInt("가격:"));
		return bean;
	}
	
	public PriceBean inputPriceData(){
		PriceBean bean = new PriceBean();
		// 데이터를 받는다. - 가격,판매시작일, 판매 종료일
		bean.setPrice(Util.inputInt("상품가격:"));
		bean.setStartDate(Util.inputMenuAndData("판매시작일:"));
//		bean.setEndDate(Util.inputMenuAndData("판매종료일:"));
		return bean;
	}
	
	public void list(List<ShopBean> list) {
		// TODO Auto-generated method stub
		// 제목 출력
		System.out.println("상품코드/분류/상품명/제조사/가격");
		Util.printChar('-', 40);
		for(ShopBean bean : list){
			System.out.print(bean.getCode()+"/");
			System.out.print(bean.getCategory()+"/");
			System.out.print(bean.getName()+"/");
			System.out.print(bean.getCompany()+"/");
			System.out.print(bean.getPrice());
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
