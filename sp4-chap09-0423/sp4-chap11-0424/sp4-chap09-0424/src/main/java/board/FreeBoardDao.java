package board;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class FreeBoardDao {

	private JdbcTemplate jdbcTemplate;
	
	public void setJbcTemplcate(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public FreeBoard view(int no){
		
		String sql="select title,content,writer from board where no = ?";
		FreeBoard freeBoard  = new JdbcTemplate().queryForObject(sql, FreeBoard.class, no);
		return freeBoard;
	}
	
	public List<FreeBoard> list(){
		return null;	
	}
}
