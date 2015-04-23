package qna.dao;

import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import qna.model.Qna;

public class QnaDao {

		//jdbc 템플릿을 사용해서 목록을 조회하고 그 결과값을 List<Qna>로 반환한다.

		//1. jbdc 객체 변수 선언
	   private JdbcTemplate jdbcTemplate;
	   
	  // 1-1. Jdbc 템플릿을 생성하기 위해서 생성자로 DataSource 를 주입받는다.
	  public QnaDao(DataSource dataSource){
		  this.jdbcTemplate = new JdbcTemplate(dataSource);
	  	}
	  
	   // 2. 목록을 조회하고 결과값을 List<Qna> 로 반환하는 메소드
	   public List<Qna> list(){
		   
		   // 2-1. oracle DB 에 질의할 쿼리문 준비. 
		   String sql = " select no,title,writer,wdate,target "
		   						+ " from qna "
		   						+ " order by no desc "; 
		   
		   // 2-2. jdbcTemplate.query(sql, rowMapper) 형태로 쿼리를 한다.
		   //      List<Qna> = RowMapper<Qna> 형태를 취한다.
		   //      mapRow(결과묶음, 행번호)로 List 객체와 ResultSet 객체의 행을 한 행 단위로 맵핑(연결)한다.
		      //             행이 한 개 이건, 두 개인건 상관이 없다.
		   List<Qna> qList = jdbcTemplate.query
				   (sql,
						   new RowMapper<Qna>() {
					   				// 2-2-1. jdbc 템플릿이 쿼리 수행 후 결과묶음을 자동으로 생성한다. 
					          			//                 또한 rs.next() 를 안해도 한 번 끝나면 jdbc 템플릿측에서 rs.next() 해준다.
					   					//                 즉 우리는 넘겨진 rs에서 값을 꺼내서 Qna bean(vo)에 담으면 된다.
					   				@Override
					   				public Qna mapRow(ResultSet rs, int rowNum) throws java.sql.SQLException 
					   					{
					   					// 2-2-2-1. Qna 에 생성자로 rs 의 값을 설정한다.
					   					Qna qna = new Qna
					   							(
					   								rs.getInt		("no"),
					   								rs.getString("title"),
					   								rs.getString("writer"),
					   								rs.getString("wdate"),
					   								rs.getInt		("target")
					   							);
					   					    // 2-2-2-2.  값을 설정한 Qna 의 객체 qna 를 RowMapper 객체(컬렉션)에 반환.
					   						// 비 스프링에서는 list.add(qna) 부분에 해당한다. - 정확히는 리턴을 하고 나면,
					   					// JdbcTemplate 에서 저렇게 처리 할 것이다. 
					   					return qna;
					   					};
				   				}
						);
		   // JdbcTemplate 으로 만들어진 자바객체인 List<Qna> 를 호출한 곳으로 반환한다.
		   return qList;
	   }
}
