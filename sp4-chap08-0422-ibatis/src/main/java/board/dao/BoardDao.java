package board.dao;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import board.model.Board;

/*
 * 개발할 때는 메소드 하나를 만들고, 테스트 완료 되면
 * 다음 메소드를 개발한다. 처음부터 다 짜고 진행하지 않음 ~~~~
 */

public class BoardDao {
	
	//	jdbc 템플릿을 이용하기 위해서 객체 변수 생성
	private JdbcTemplate jdbcTemplate;
	
	// JdbcTempate 을 만들기 이한 생성자
	public BoardDao(DataSource dataSource){

		// 데이타 소스를 받아서 템플릿을 만들어 준다.
		// 직접 안해주고, xml 에 JdbcTemplate 에다가 datasource 로 주입할 수 있다.
		this.jdbcTemplate = new JdbcTemplate(dataSource);		
	}
	
	// 글 목록을 출력하는 메소드
	public List<Board> list() {
		String sql="select no,title,writer,to_char(wdate, 'yyyy-mm-dd') wdate,target"
							+" from board order by no desc";
		/*
		 * JdbcTemplate 에서 
		 * queryForObject() : 하나의 레코드 가져오기
		 * query() : 여러 레코드를 가져오기
		 */
		List<Board> results = jdbcTemplate.query
				(sql,
					// 클래스 생성을 바로 하고 있다.
					new RowMapper<Board>() {
						//  RowMapper 에 있는 추상메소드 오버라이드
     					// 그러니까. 원래 스프링 메소드 무시하고 그냥 우리가 rs 를 bean 으로 맵 하는 것 직접 구현 
						@Override	
						public Board mapRow(ResultSet rs, int rowNum)
							throws SQLException {
								Board board =
									new Board(rs.getInt("no"), rs.getString("title"), 
														rs.getString("writer"), rs.getString("wdate"),
														rs.getInt("target"));
							return board;
						}
				});
		return results;
	}
	
	
	// 글번호를 매개변수로 받아 글을 보여주는 메소드
	public Board view(int no){
		String sql="select no,title,writer,to_char(wdate, 'yyyy-mm-dd') wdate,target,content"
				+" from board"
				+" where no=?";
		
		// queryForObject(sql문 , 결과를 연결할 타입 new RowMapper<Board>(), 물음표 대체할 인자)
		Board board = jdbcTemplate.queryForObject(sql,
				new RowMapper<Board>() {
					@Override
					public Board mapRow(ResultSet rs, int rowNum) throws SQLException{
						Board board =	new Board
														(	rs.getInt("no"),
													rs.getString("title"),
													rs.getString("content"),
													rs.getString("writer"),
													rs.getString("wdate"),
													rs.getInt("target") );
					return board;
					}
				}//RawMapper<Board> 익명 클래스의 끝
				, no); 
		return board;
	}
	
	// 글을 삽입하는 메소드
	// 이너 클래스에 객체 board 를 전달할 수 없기에 임시로 final 상태로 만들어 접근 가능하게 해준다.
	// final 로 만들지 않으면 Data 가 전달되지 않는다.
	public int write(final Board board){
		
		// insert, update, delete 를 실행할 때는 jdbcTemplate.update() 메소드를 사용한다.
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) 
					throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into BOARD (no, title, content, writer) "+
						"values (board_seq.nextval, ?, ?, ?)");
				pstmt.setString(1,  board.getTitle());
				pstmt.setString(2,  board.getContent());
				pstmt.setString(3,  board.getWriter());
				return pstmt;
			}
		});
		
		return 1;	
	}
	
	// 글을 갱신하는 메소드
	public int update(Board board){
		// jdbcTemplate.update(sql, args)
		jdbcTemplate.update("update BOARD set title = ?, content = ?, writer=? where no = ?",
											board.getTitle(), board.getContent(), board.getWriter(),board.getNo());
		return 1;
	}
	
	// 글을 삭제하는 메소드
	public int delete(int no){
		// jdbcTemplate.update(sql, args)
				jdbcTemplate.update("delete BOARD where no = ?",no);
		return 1;
	}
	
	// 전제 글 수 가져오는 메소드
	public int count(){
		
		return 0;
	}
	
	// 글번호를 인자로 받아서 조회수 1증가하는 메소드
	public int increase(int no){
		
		return 0;
	}
}

