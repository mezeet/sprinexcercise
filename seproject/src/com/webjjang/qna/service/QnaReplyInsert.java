package com.webjjang.qna.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.webjjang.qna.dao.QnaDao;
import com.webjjang.qna.model.QnaBean;
import com.webjjang.qna.view.QnaDisplay;
import com.webjjang.util.DBUtil;
import com.webjjang.util.Util;

public class QnaReplyInsert implements QnaReplyInterface {

	@Override
	public void service(int no) { // no :부모글:보고있었던 글
		// TODO Auto-generated method stub
		// 제목출력
		String str="Q & A 답변 쓰기";
		Util.printTitleAndMenu(20,Util.toStringArray(str));
		// Q&A 의 글 입력 - 제목,내용,작성자
		QnaDisplay display = new QnaDisplay();
		
		QnaBean newBean = display.inputQnaData();
		// 입력한 글을 DB에 저장
		QnaDao dao = new QnaDao();
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			// 부모글에서 데이터 가져오기:no,title,refNo,ordNo,levNo
			QnaBean bean = dao.selectParent(con, no);
			// 실제적으로 입력하는 데이터 작성
			newBean.setRefNo(bean.getRefNo());
			newBean.setOrdNo(bean.getOrdNo()+1);
			newBean.setLevNo(bean.getLevNo()+1);
			newBean.setParentNo(bean.getNo());
			// 답변 제목인 경우 아무것도 입력하지 않으면 부모글
			// 앞에 "[답변]"을 추가하여 입력 데이터로 사용한다.
			if(newBean==null ||
				newBean.getTitle().equals(""))
				newBean.setTitle("[답변]"+bean.getTitle());
			// 관련글이 같으면서 새로 작성된 순서보다 크거나 같은
			// 순서는 1 증가 시켜준다.
			dao.incereaseReplyOrdNo
			(con, newBean.getRefNo(), newBean.getOrdNo());
			dao.insertReply(con, newBean);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
				try {
					if(con!=null && !con.getAutoCommit())
						con.setAutoCommit(true);
					if(con!=null) con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
		// 결과 출력
	}

}
