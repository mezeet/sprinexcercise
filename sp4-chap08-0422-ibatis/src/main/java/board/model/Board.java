package board.model;

public class Board {
	
	 private int no,target;
	 private String title,writer,wdate, content;
	
	 // 생성자가 많은 이유는, 각 CRUD 상황 별로 세팅에 필요한 값이 다르기 때문이다.
	 // 상황이 점점 많아지면 생성자 오버로딩도 많아지게 된다.
	 
	 //board 공통 생성자.
	 public Board(String title, String writer){
		 this.title = title;
		 this.writer=writer;
	 }
	 
	 // write 용 생성자.
	 public Board(String title, String content, String writer){
		 this(title,writer);
		 this.content=content;
	 }
	 
	 // update 용 생성자.
	 public Board(int no,String title, String content, String writer){
		 this(title,writer,content);
		 this.no=no;
	 }
	
	 // list 용 생성자
	 public Board(int no, String title, String writer, String wdate, int target){
		 this(title,writer);
		 this.no = no;
		 this.wdate  = wdate;
		 this.target = target;
	 }
	 // view 용 생성자.
	 public Board(int no, String title, String content, String writer, String wdate, int target){
		 this(no,title,writer,wdate,target);
		 this.content=content;
	 }
	public int getNo() {
		return no;
	}
	public int getTarget() {
		return target;
	}
	public String getTitle() {
		return title;
	}
	public String getWriter() {
		return writer;
	}
	public String getWdate() {
		return wdate;
	}
	public String getContent() {
		return content;
	}
	
	public String toString(){
		return "글번호 :"+no+"제목 :"+title+"작성자 :"+writer+"작성일 :"+wdate+"조회수 :"+target;
		
	}
	 
	 

}
