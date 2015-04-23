package chap02;

public class Company {
	
	private String comName;
	private String comTel;
	
	public void setComName(String name){
		this.comName = name;
	}
	public void setComTel(String tel){
		this.comTel = tel;
	}
	
	public String toString(){
	String result = ""+comName+"회사의 전화번호는"+comTel+"입니다.";
		return result;
	}

}
