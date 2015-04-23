package qna.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import qna.model.Qna;

// Database 에 있는 QNA 테이블에 접근하는 객체
public class QnaDao_old {
	// 1. list() 메소드를 호출하면, QNA 테이블에  있는 글(Qna)의 목록(List)을 =List<Qna> 가져와서 호출한 곳으로 반환(return)한다.
	public List<Qna> list() {
		// 1-1. List<Qna> 에다가 목록을 가져오는 쿼리의 결과값을 저장한다.
			// 1-1-1. 저장할 변수인 List<qna> 생성
				List<Qna> lq = null;
				Qna qna = null;
			// 1-1-2. 쿼리를 날린다.
				//1-1-2-1. 날릴 쿼리문을 작성
				String sql ="select no, title, writer,to_char(wdate, 'yyyy-MM-dd') wdate,target from qna";
				//1-1-2-2. 커넥션 생성
				Connection con = null;
				
					//1-1-2-2-1. 접속 정보 설정
					String driver = "oracle.jdbc.driver.OracleDriver";
	        String url = "jdbc:oracle:thin:@211.183.7.63:1521:orcl";
	        String user = "java21";
	        String password = "java21";
	
	        // 1-1-2-2-2. driver 를 메모리에 생성
	        try {
		            Class.forName(driver); 
		        } catch (ClassNotFoundException e) {
		            System.out.println("드라이버를 찾을 수 없습니다.");
		        	}
	     
	        		
	        con = DriverManager.getConnection(url, user, password);
					PreparedStatement pstmt=null;
					pstmt = con.prepareStatement(sql);
					// rs 의 포인터가 0번인 상태다.
					ResultSet rs = pstmt.executeQuery();
					
					con.close();
					pstmt.close();
					rs.close();
					

					
					
					// rs 의 포인터를 1증가시킨 값이 있으면! 실행
					while(rs.next()){

						// rs에서 한 행의 값을 꺼낸다.
						//  열의 이름은 no, title, writer, wdate, target 이다.
					
						int no = rs.getInt("no");
						String title = rs.getString("title");
					
						// 	qna 에 값을 넣는다.
					  qna = new Qna();
						qna.setNo(rs.getInt("no")); 
						qna.setTitle(rs.getString("title"));
						qna.setWriter(rs.getString("writer"));
						qna.setWdate(rs.getString("wdate"));
						qna.setTarget(rs.getInt("target"));
					
						lq.add(qna);
						
					}
					
			// 1-1-3. 변수에 쿼리 값을 담는다.
				
	    // 1-2. 결과값(List<Qna>) 돌려(return)준다.
		return lq;
	}
}
