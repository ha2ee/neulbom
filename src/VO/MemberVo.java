package VO;

import java.sql.Date;

//회원 한사람의 정보를 DB로 부터 조회해서 저장할 변수가 있는 VO클래스
//입력한 회원정보를 DB에 INSERT추가 하기 전 임시로 저장할 변수가 있는 VO클래스
public class MemberVo {
	
	private String id,pass,name, nickname;
	private Date reg_date;
	private int age;
	private String gender,address,email,tel,hp;
	
	public MemberVo() {
		
	}
	
	
	
	public MemberVo(String id, String pass, String name, 
					int age, String gender, String address, String email,
					String tel, String hp) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.tel = tel;
		this.hp = hp;
	}



	public MemberVo(String id, String pass, String name, 
					Date reg_date, int age, String gender, String address,
					String email, String tel, String hp) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.reg_date = reg_date;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.email = email;
		this.tel = tel;
		this.hp = hp;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	
	
	
	
}
