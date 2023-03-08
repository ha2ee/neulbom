package VO;

import java.sql.Date;

//회원 한사람의 정보를 DB로 부터 조회해서 저장할 변수가 있는 VO클래스
//입력한 회원정보를 DB에 INSERT추가 하기 전 임시로 저장할 변수가 있는 VO클래스
public class MemberVo {
	

	//일반회원 테이블 
	private String mem_id;           
	private String mem_name; 
	private String mem_nick;
	private String mem_pw;        
	private String mem_email;      
	private String mem_hp;          
	private String mem_birth;      
	private String mem_gender;      
	private Date mem_joindate;         
	private String mem_pet;
	
	
	//트레이너 테이블
	private String tr_id;           
	private String tr_name; 
	private String tr_nick;
	private String tr_pw;        
	private String tr_email;      
	private String tr_hp;          
	private String tr_birth;      
	private String tr_gender;      
	private Date tr_joindate;         
	

	//일반회원 주소 테이블
	private String mem_address1;
	private String mem_address2;
	private String mem_address3;
	private String mem_address4;
	private String mem_address5;
	
	
	//트레이너 주소 테이블
	private String tr_address1;
	private String tr_address2;
	private String tr_address3;
	private String tr_address4;
	private String tr_address5;
	
	
	
	public MemberVo(String mem_id, String mem_name, String mem_nick, String mem_pw, String mem_email, String mem_hp,
			String mem_birth, String mem_gender, Date mem_joindate, String mem_pet, String mem_address1,
			String mem_address2, String mem_address3, String mem_address4, String mem_address5) {
		super();
		this.mem_id = mem_id;
		this.mem_name = mem_name;
		this.mem_nick = mem_nick;
		this.mem_pw = mem_pw;
		this.mem_email = mem_email;
		this.mem_hp = mem_hp;
		this.mem_birth = mem_birth;
		this.mem_gender = mem_gender;
		this.mem_joindate = mem_joindate;
		this.mem_pet = mem_pet;
		this.mem_address1 = mem_address1;
		this.mem_address2 = mem_address2;
		this.mem_address3 = mem_address3;
		this.mem_address4 = mem_address4;
		this.mem_address4 = mem_address5;
	}
	
	public MemberVo(String mem_id, String mem_name, String mem_nick, String mem_pw, String mem_email, String mem_hp,
			String mem_birth, String mem_gender, String mem_pet, String mem_address1,
			String mem_address2, String mem_address3, String mem_address4, String mem_address5) {
		super();
		this.mem_id = mem_id;
		this.mem_name = mem_name;
		this.mem_nick = mem_nick;
		this.mem_pw = mem_pw;
		this.mem_email = mem_email;
		this.mem_hp = mem_hp;
		this.mem_birth = mem_birth;
		this.mem_gender = mem_gender;
		this.mem_pet = mem_pet;
		this.mem_address1 = mem_address1;
		this.mem_address2 = mem_address2;
		this.mem_address3 = mem_address3;
		this.mem_address4 = mem_address4;
		this.mem_address4 = mem_address5;

	}

	public MemberVo(String tr_id, String tr_name, String tr_nick, String tr_pw, String tr_email, String tr_hp,
			String tr_birth, String tr_gender, Date tr_joindate, String tr_address1, String tr_address2,
			String tr_address3, String tr_address4, String tr_address5) {
		super();
		this.tr_id = tr_id;
		this.tr_name = tr_name;
		this.tr_nick = tr_nick;
		this.tr_pw = tr_pw;
		this.tr_email = tr_email;
		this.tr_hp = tr_hp;
		this.tr_birth = tr_birth;
		this.tr_gender = tr_gender;
		this.tr_joindate = tr_joindate;
		this.tr_address1 = tr_address1;
		this.tr_address2 = tr_address2;
		this.tr_address3 = tr_address3;
		this.tr_address4 = tr_address4;
		this.tr_address4 = tr_address5;
	}
	
	public MemberVo(String tr_id, String tr_name, String tr_nick, String tr_pw, String tr_email, String tr_hp,
			String tr_birth, String tr_gender, String tr_address1, String tr_address2,
			String tr_address3, String tr_address4, String tr_address5) {
		super();
		this.tr_id = tr_id;
		this.tr_name = tr_name;
		this.tr_nick = tr_nick;
		this.tr_pw = tr_pw;
		this.tr_email = tr_email;
		this.tr_hp = tr_hp;
		this.tr_birth = tr_birth;
		this.tr_gender = tr_gender;
		this.tr_address1 = tr_address1;
		this.tr_address2 = tr_address2;
		this.tr_address3 = tr_address3;
		this.tr_address4 = tr_address4;
		this.tr_address4 = tr_address5;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_nick() {
		return mem_nick;
	}

	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_hp() {
		return mem_hp;
	}

	public void setMem_hp(String mem_hp) {
		this.mem_hp = mem_hp;
	}

	public String getMem_birth() {
		return mem_birth;
	}

	public void setMem_birth(String mem_birth) {
		this.mem_birth = mem_birth;
	}

	public String getMem_gender() {
		return mem_gender;
	}

	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
	}

	public Date getMem_joindate() {
		return mem_joindate;
	}

	public void setMem_joindate(Date mem_joindate) {
		this.mem_joindate = mem_joindate;
	}

	public String getMem_pet() {
		return mem_pet;
	}

	public void setMem_pet(String mem_pet) {
		this.mem_pet = mem_pet;
	}

	public String getTr_id() {
		return tr_id;
	}

	public void setTr_id(String tr_id) {
		this.tr_id = tr_id;
	}

	public String getTr_name() {
		return tr_name;
	}

	public void setTr_name(String tr_name) {
		this.tr_name = tr_name;
	}

	public String getTr_nick() {
		return tr_nick;
	}

	public void setTr_nick(String tr_nick) {
		this.tr_nick = tr_nick;
	}

	public String getTr_pw() {
		return tr_pw;
	}

	public void setTr_pw(String tr_pw) {
		this.tr_pw = tr_pw;
	}

	public String getTr_email() {
		return tr_email;
	}

	public void setTr_email(String tr_email) {
		this.tr_email = tr_email;
	}

	public String getTr_hp() {
		return tr_hp;
	}

	public void setTr_hp(String tr_hp) {
		this.tr_hp = tr_hp;
	}

	public String getTr_birth() {
		return tr_birth;
	}

	public void setTr_birth(String tr_birth) {
		this.tr_birth = tr_birth;
	}

	public String getTr_gender() {
		return tr_gender;
	}

	public void setTr_gender(String tr_gender) {
		this.tr_gender = tr_gender;
	}

	public Date getTr_joindate() {
		return tr_joindate;
	}

	public void setTr_joindate(Date tr_joindate) {
		this.tr_joindate = tr_joindate;
	}

	public String getMem_address1() {
		return mem_address1;
	}

	public void setMem_address1(String mem_address1) {
		this.mem_address1 = mem_address1;
	}

	public String getMem_address2() {
		return mem_address2;
	}

	public void setMem_address2(String mem_address2) {
		this.mem_address2 = mem_address2;
	}

	public String getMem_address3() {
		return mem_address3;
	}

	public void setMem_address3(String mem_address3) {
		this.mem_address3 = mem_address3;
	}

	public String getMem_address4() {
		return mem_address4;
	}

	public void setMem_address4(String mem_address4) {
		this.mem_address4 = mem_address4;
	}
	public String getMem_address5() {
		return mem_address5;
	}

	public void setMem_address5(String mem_address5) {
		this.mem_address5 = mem_address5;
	}

	public String getTr_address1() {
		return tr_address1;
	}

	public void setTr_address1(String tr_address1) {
		this.tr_address1 = tr_address1;
	}

	public String getTr_address2() {
		return tr_address2;
	}

	public void setTr_address2(String tr_address2) {
		this.tr_address2 = tr_address2;
	}

	public String getTr_address3() {
		return tr_address3;
	}

	public void setTr_address3(String tr_address3) {
		this.tr_address3 = tr_address3;
	}

	public String getTr_address4() {
		return tr_address4;
	}

	public void setTr_address4(String tr_address4) {
		this.tr_address4 = tr_address4;
	}
	public String getTr_address5() {
		return tr_address5;
	}

	public void setTr_address5(String tr_address5) {
		this.tr_address5 = tr_address5;
	}

	
	
	
	
}	