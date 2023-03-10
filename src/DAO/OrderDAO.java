package DAO;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import VO.MemberVo;
import VO.PetVo;
import VO.TrainerVo;
import VO.eduOrderVo;

public class OrderDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	public OrderDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/oracle");
			
		} catch (Exception e) {
			System.out.println("DB연결 실패! - "+ e);
		}
	}
	
	//자원해제 기능
	private void closeResource() {
		if(con != null)try {con.close();} catch (Exception e) {e.printStackTrace();}
		if(pstmt != null)try {pstmt.close();} catch (Exception e) {e.printStackTrace();}
		if(rs != null)try {rs.close();} catch (Exception e) {e.printStackTrace();}
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// OrderDAO INFO ////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// #1) 		checkTrainer()			 		메소드		<- 훈련사		 		정보 조회
	// #2)      checkMember()                메소드     <- 멤버		  	  	정보 조회
	// #3)		checkPet()						메소드		<- 반려견				정보 조회
	// #4)      checkDate()					메소드     <- 예약일정에서 이미 예약된 일정을 조회
	// #5)      insertEduOrder()				메소드		<- 수강신청			정보 등록
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	
	

	// 조회된 이름값으로 MEMBER_TRAINER DB에 저장 된 트레이너를 조회하고
	// 다시 리턴 해준다.
	public TrainerVo checkTrainer(String tr_name) {
		
		System.out.println("OrderDAO -> checkTrainer 호출!");
		System.out.println("String tr_name : "+tr_name);
		
		// 1) 트레이너를 조회할 멤버VO 초기화 시키기
		TrainerVo trainervo = null;
		
		try {
			
	        // 1) DB접속
	        con = ds.getConnection();
	        
	        // 2) SELECT문		
	        // sql 변수값에 가져온 이름으로  트레이너의 전화번호, 이미지를 조회한다. 
	 		String sql = "select TR_NAME, TR_HP, TR_IMG from MEMBER_TRAINER where TR_NAME=?";
			
	        
	        // 3) pstmt에 sql 문 저장 하기
	        pstmt = con.prepareStatement(sql);
	        
	        // 4) sql에서 ?로 된 구문에 tr_name 넣기
	        pstmt.setString(1, tr_name);
	        
	        // 5) resultset에 저장시키고 조회 해온다.
	        rs = pstmt.executeQuery();
	        
	        // 6) 조회해온 행이 있으면?
	        if(rs.next()) {
		            
        		// 7) trainervo에 저장한다.
	        	trainervo = new TrainerVo();
	        	trainervo.setTr_name(rs.getString("tr_name"));
	        	trainervo.setTr_hp(rs.getString("tr_hp"));
	        	trainervo.setTr_img(rs.getString("tr_img"));
	           
	         }
	        
			
			
		} catch (Exception e) {
		         System.out.println("OrderDAO -> ClickTrainer 메소드 내부에서 오류!");
		         e.printStackTrace();
			
		} finally {
			
			 closeResource();
			
		}
		
		// 8) trMembervo로 리턴해준다.
		return trainervo;
	}
	
	// #2) 수강신청 시 멤버 정보 조회 메소드
	public MemberVo checkMember(String login_id) {
		
		MemberVo membervo = null;
		System.out.println("OrderDAO -> checkMember() 메소드 호출!");
					
		
		try {
			
			//DB접속
			con = ds.getConnection();
			//매개변수 login_id로 받는 입력한 아이디에 해당되는 행을 조회 SELECT문
			String sql = "select * from ys_member where mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, login_id);
			rs = pstmt.executeQuery();
			
			//입력한 아이디로 조회한 행이 있으면?
			if(rs.next()) {
			
			// 하나씩 가져와서 vo에 담는다.
			membervo = new MemberVo();
			// 1) 회원 정보에 대한 부분
			membervo.setMem_id(rs.getString("mem_id"));
			membervo.setMem_name(rs.getString("mem_name"));
			membervo.setMem_email(rs.getString("mem_email"));
			membervo.setMem_hp(rs.getString("mem_hp"));
			membervo.setMem_address1(rs.getString("mem_address1"));
			membervo.setMem_address2(rs.getString("mem_address2"));
			membervo.setMem_address3(rs.getString("mem_address3"));
			membervo.setMem_address4(rs.getString("mem_address4"));
			membervo.setMem_address5(rs.getString("mem_address5"));

			}
			
			
			
		} catch (Exception e) {
			
			System.out.println("OrderDAO -> checkMember 메소드 내부에서 오류!");
			e.printStackTrace();
			
		}finally {
			
			closeResource();
		}
		
		return membervo;
		
	}
	
	// #3) 수강신청 시 반려견 정보 조회 메소드
	public PetVo checkPet(String login_id) {
		
		//petvo 객체를 생성해준다.
		PetVo petvo = new PetVo();
		
		System.out.println("OrderDAO -> checkPet() 메소드 호출!");
		

		try {
			
			//DB접속
			con = ds.getConnection();
			//매개변수 login_id로 받는 입력한 아이디에 해당되는 행을 조회 SELECT문
			// sql = id 값으로 저장 된 반려견 정보를 조회한다
			String sql = "select * from pet where p_mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, login_id);
			rs = pstmt.executeQuery();
			
			// 만약에 값들이 존재 한다면?
			if(rs.next()) {
				
				//petvo에 담는다.
				petvo.setP_name(rs.getString("p_name"));
				petvo.setP_age(Integer.parseInt(rs.getString("p_age")));
				petvo.setP_gender(rs.getString("p_gender"));
				petvo.setP_type(rs.getString("p_type"));
				petvo.setP_op(rs.getString("p_op"));
				petvo.setP_weight(Integer.parseInt(rs.getString("p_weight")));
				petvo.setP_img(rs.getString("p_img"));
				
				
			}
			
			
			
			
		} catch (Exception e) {
			
			System.out.println("OrderDAO -> checkPet 메소드 내부에서 오류!");
			e.printStackTrace();
			
		}finally {
			
			closeResource();
		}
		
		return petvo;
		
	}
	
	// #4) 이미 예약된 일정을 필터링 해서 반환해주는 메소드
	public List<String> checkDate(String tr_name) {
		
		List<String> list = new ArrayList<String>();
		
		System.out.println("OrderDAO -> checkDate 메소드 호출!");
		

		try {
			
			//DB접속
			con = ds.getConnection();
			//매개변수 tr_name으로 받는 입력한 이름에 해당되는 행을 조회 SELECT문
			// sql = tr_name 값으로 선택 된 훈련사의 예약 된 일정들을 조회해 온다.
			String sql = "select date1 from edu_order where tr_name=? " + 
					"union " + 
					"select date2 from edu_order where tr_name=? " + 
					"union " + 
					"select date3 from edu_order where tr_name=? " + 
					"union " + 
					"select date4 from edu_order where tr_name=? " + 
					"union " + 
					"select date5 from edu_order where tr_name=? " + 
					"union " + 
					"select date6 from edu_order where tr_name=? " + 
					"union " + 
					"select date7 from edu_order where tr_name=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, tr_name);
			pstmt.setString(2, tr_name);
			pstmt.setString(3, tr_name);
			pstmt.setString(4, tr_name);
			pstmt.setString(5, tr_name);
			pstmt.setString(6, tr_name);
			pstmt.setString(7, tr_name);
			rs = pstmt.executeQuery();
			
			// 만약에 값들이 존재 한다면?
			while(rs.next()) {
			
				//Array 배열에 저장시킨다.
				list.add(rs.getString("date1"));

			}
				
			System.out.println(list);
			
			
			
		} catch (Exception e) {
			
			System.out.println("OrderDAO -> checkPet 메소드 내부에서 오류!");
			e.printStackTrace();
			
		}finally {
			
			closeResource();
		}
		return list;
		
		
	}
	
	
	
	
	
	// #5) 수강신청 정보 등록 메소드
	public void insertEduOrder(eduOrderVo eduordervo) {
		
		System.out.println("OrderDAO -> InsertEduOrder 메소드 호출!");
		
		try {
			
			//커넥션 풀 연결
			con = ds.getConnection();
			
			// 매개변수로 전달받은 eduOrderVo의 각 변수에 저장되어 전달받은 값들을
			// sql 문을 이용해 insert 하기
			String sql = "insert into edu_order(edu_id, edu_name, edu_hp, edu_email, "
						+ "edu_address1, edu_address2, edu_address3, edu_address4, edu_address5, "
						+ "pet_img, pet_name, pet_type, pet_age, pet_weight, pet_gender, pet_op, "
						+ "tr_img, tr_name, tr_hp, "
						+ "date1, date2, date3, date4, date5, date6, date7, "
						+ "edu_cnt, edu_totalprice) "
						+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			// sql문을 pstmt 객체에 저장
			pstmt = con.prepareStatement(sql);
			
			// 1) 회원 정보 부분
			pstmt.setString(1, eduordervo.getEdu_id());
			pstmt.setString(2, eduordervo.getEdu_name());
			pstmt.setString(3, eduordervo.getEdu_hp());
			pstmt.setString(4, eduordervo.getEdu_email());
			pstmt.setString(5, eduordervo.getEdu_address1());
			pstmt.setString(6, eduordervo.getEdu_address2());
			pstmt.setString(7, eduordervo.getEdu_address3());
			pstmt.setString(8, eduordervo.getEdu_address4());
			pstmt.setString(9, eduordervo.getEdu_address5());
			// 2) 펫 정보 부분
			pstmt.setString(10, eduordervo.getPet_img());
			pstmt.setString(11, eduordervo.getPet_name());
			pstmt.setString(12, eduordervo.getPet_type());
			pstmt.setInt(13, eduordervo.getPet_age());
			pstmt.setInt(14, eduordervo.getPet_weight());
			pstmt.setString(15, eduordervo.getPet_gender());
			pstmt.setString(16, eduordervo.getPet_op());
			// 3) tr 정보 부분
			pstmt.setString(17, eduordervo.getTr_img());
			pstmt.setString(18, eduordervo.getTr_name());
			pstmt.setString(19, eduordervo.getTr_hp());
			// 4) tr 예약일 부분
			pstmt.setString(20, eduordervo.getDate1());
			pstmt.setString(21, eduordervo.getDate2());
			pstmt.setString(22, eduordervo.getDate3());
			pstmt.setString(23, eduordervo.getDate4());
			pstmt.setString(24, eduordervo.getDate5());
			pstmt.setString(25, eduordervo.getDate6());
			pstmt.setString(26, eduordervo.getDate7());
			pstmt.setInt(27, eduordervo.getEdu_cnt());
			pstmt.setString(28, eduordervo.getEdu_totalprice());
			
			//PreparedStatement실행객체메모리에 설정된 insert전체 문장을 DB의 테이블에 실행!
			pstmt.executeUpdate();
					
			
			
			
		} catch(Exception e) {
			
			System.out.println("insertEduOrder 메소드 내부에서 SQL 실행 오류 "+ e);
			
		} finally {
			
			closeResource();
			
		}
	
		
	}

		
}

