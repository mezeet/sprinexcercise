package board;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class FreeBoardDao {

	private JdbcTemplate jdbcTemplate;
	
	public void setJbcTemplcate(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
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
