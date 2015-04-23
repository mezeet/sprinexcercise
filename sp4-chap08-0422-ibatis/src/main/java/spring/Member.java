package spring;

public class Member {
	
	 private String id;
	 private String pw;
	 private String name;
	 private int age;
	 private String tel;
	 private int grade;
	 private String regdate;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	 
	public String toString(){
		
		return "아이디 :"+id+"암호:"+pw+"이름:"+name+"나이:"+age+"전번:"+tel+"등급:"+grade+"등록일:"+regdate;
	}
	
	 }
