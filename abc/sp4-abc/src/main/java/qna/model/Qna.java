package qna.model;

public class Qna {

	private int no,target;
	private String title, writer,wdate;
	
	// 목록 조회시 rs 가 세팅될 생성자
	public Qna(int no, String title, String writer, String wdate, int target){
		this.no=no;
		this.title=title;
		this.writer=writer;
		this.wdate=wdate;
		this.target=target;
	}
	

}
