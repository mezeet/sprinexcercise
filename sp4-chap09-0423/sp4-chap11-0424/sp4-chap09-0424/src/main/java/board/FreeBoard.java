package board;

/**
 * @author crown204
 *
 */
public class FreeBoard extends Board{

	public FreeBoard(){
		
	}
	
    //	삽입용 생성자 메소드
	public FreeBoard(int no, String title, String content, String writer){
		this.setNo(no);
		this.setTitle(title);
		this.setContent(content);
		this.setWriter(writer);
	}
}
