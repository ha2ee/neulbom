package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import VO.BoardVo;
import VO.MemberVo;

public class BoardDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	
	
	
	public BoardDAO() {
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

	

	
	//입력한 새글 정보를 DB에 추가 하는 메소드
	public int insertBoard(BoardVo vo) {
		int result = 0;
			
		try {
			//DB접속
			con = ds.getConnection();
			
			// 1) 새글달기에 대한 규칙 업데이트 하기 (두번째 부터 입력되는 주글들의 pos를 1증가시킨다.
			String sql = "update free_board set b_group = b_group + 1";
			
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			// 2) 새글달기에 대한 규칙 업데이트 하기(b_group, b_level 을 0 , 0 으로 인서트)
			//insert SQL문 만들기
			sql = "insert into free_board (b_idx, b_id, b_nickname, "
							+ "b_title, b_content, b_group, "
							+ "b_level, b_date, b_update_date, "
							+ "b_cnt, b_file, b_like) "
							+ " values (boarder_b_idx.nextVal, ?,?,?,?,0,0,sysdate,sysdate,0,?,0)";
																	
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getB_id());
			pstmt.setString(2, vo.getB_nickname());
			pstmt.setString(3, vo.getB_title());
			pstmt.setString(4, vo.getB_content());
			pstmt.setString(5, vo.getB_file());
	
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertBoard 메소드 내부에서 오류 !");
			e.printStackTrace();
		}finally {
			closeResource();
		}
		
		return result;
	}
	
	
	/*		
	
	
	//현재 게시판 DB에 저장된 글의 총 수 조회 하는 메소드
		public int getTotalRecord(String key, String word) {
			String sql = null;
			
			//조회된 글의 글수 저장
			int total = 0;
			
			if(!word.equals("")) {//검색어를 입력했다면?
				
				if(key.equals("titleContent")) {//검색기준값  제목+내용을 선택했다면? 
				
					sql = "select count(*) as cnt from board "
						+ " where b_title like '%"+ word + "%' or"
							  + " b_content like '%"+ word+"%'";
				
				}else {//"name" 검색기준값 작성자를 선택했다면?
				
					sql = "select count(*) as cnt from board "
						+ " where b_name like '%"+ word + "%'";				
				}
				
			}else {//검색어 입력 안했아면?
				
				sql = "select count(*) as cnt from board";
			}
				
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				rs.next();
				total = rs.getInt("cnt");
				 
			}catch (Exception e) {
				System.out.println("getTotalRecord메소드에서 오류");
				e.printStackTrace();
			}finally {
				closeResource();
			}
		
			return total;
		}
	
	//현재 게시판 DB에 있는 모든 글들을 조회 하는 메소드
	public ArrayList boardListAll() {
		String sql = null;
		
		// 보드테이블의 b_idx 번호 값을 오름차순 정렬한다.
		sql = "select * from board order by b_group asc";
		
		ArrayList list = new ArrayList();
		
		
		try {
			con = ds.getConnection();
			sql = "select * from board order by b_group asc";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			//조회된 Result의 정보를 한행 단위로 꺼내서
			//BoardVo객체에 한행씩 저장 후 BoardVo객체들을 ArrayList배열에 하나씩 추가해서 저장
			while(rs.next()) {
				BoardVo vo = new BoardVo(rs.getInt("b_idx"),
										rs.getString("b_id"),
										rs.getString("b_pw"),
										rs.getString("b_name"), 
										rs.getString("b_email"), 
										rs.getString("b_title"), 
										rs.getString("b_content"), 
										rs.getInt("b_group"), 
										rs.getInt("b_level"), 
										rs.getDate("b_date"), 
										rs.getInt("b_cnt"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			System.out.println("boardListALL 메소드 내부에서 오류!");
			e.printStackTrace();
		} finally {
			closeResource();
		}
		
		
		
		return list;
	}
	
	
	
	//현재 게시판 DB에 있는 글들을 조회 하는 메소드
	//조건 : 선택한 검색기준값과 입력한 검색어 단어를 이용해 글들을 조회!
	public ArrayList boardList(String key,String word) {
		String sql = null;
		
		
		ArrayList list = new ArrayList();
		
		if(!word.equals("")) {//검색어를 입력했다면 ?
			
			if(key.equals("titleContent")) {//검색 기준값 제목+내용을 선택했다면 ?
				
				sql = "select * from board "
						+ " where b_title like '%"+word+"%' "
						+ " or b_content like '%"+word+"%' order by b_group asc";
			}else{//검색 기준값 작성자를 선택했다면?
				
				sql = "select * from board where b_name like '%"+word+"%' order by b_group asc";
			}
			
			
		}else {//검색어를 입력하지 않았다면?
			//모든 글 조회
			//조건 -> b_idx열의 글번호 데이터들을 기준으로 해서 내림 차순으로 정렬 후 조회!
			sql = "select * from board order by b_group asc";
			
			//참고. 정렬 조회 -> order by 정렬기준열 desc(내림차순) 또는 asc(오름차순)
		}
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			//조회된 Result의 정보를 한행 단위로 꺼내서
			//BoardVo객체에 한행씩 저장 후 BoardVo객체들을 ArrayList배열에 하나씩 추가해서 저장
			while(rs.next()) {
				BoardVo vo = new BoardVo(rs.getInt("b_idx"),
										rs.getString("b_id"),
										rs.getString("b_pw"),
										rs.getString("b_name"), 
										rs.getString("b_email"), 
										rs.getString("b_title"), 
										rs.getString("b_content"), 
										rs.getInt("b_group"), 
										rs.getInt("b_level"), 
										rs.getDate("b_date"), 
										rs.getInt("b_cnt"));
				list.add(vo);
			}
			
		} catch (Exception e) {
			System.out.println("boardList 메소드 내부에서 오류!");
			e.printStackTrace();
		} finally {
			closeResource();
		}
		
		
		
		return list;
	}

	public BoardVo boardRead(String b_idx) {
		String sql = null;
		
		sql = "select * from board WHERE b_idx=?";
		
		BoardVo vo = null;
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(b_idx));
			
			System.out.println(sql);
			
			rs = pstmt.executeQuery();
			
			//조회된 Result의 정보를 한행 단위로 꺼내서
			//BoardVo객체에 한행을 저장
			if(rs.next()) {
				vo = new BoardVo(rs.getInt("b_idx"),
										rs.getString("b_id"),
										rs.getString("b_pw"),
										rs.getString("b_name"), 
										rs.getString("b_email"), 
										rs.getString("b_title"), 
										rs.getString("b_content"), 
										rs.getInt("b_group"), 
										rs.getInt("b_level"), 
										rs.getDate("b_date"), 
										rs.getInt("b_cnt"));
			}
			
		} catch (Exception e) {
			System.out.println("boardread 메소드 내부에서 오류!");
			e.printStackTrace();
		} finally {
			closeResource();
		}
		
		
		
		return vo;
	}

	public boolean passCheck(String b_idx_, String password) {
		String sql = null;
		
		//result 변수 생성 후 초기화
		boolean result = false;
		
		sql = "select * from board WHERE b_idx=? AND b_pw=? order by b_idx desc";
		
		
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,b_idx_);
			pstmt.setString(2,password);
			
			System.out.println(sql);
			
			rs = pstmt.executeQuery();
			
			// 조회가 되면 비밀번호가 있음
			// true = 비밀번호가 DB에 있음
			// false = 비밀번호가 DB에 없음
			if(rs.next()) {
				result = true;
			}else {
				result = false;
			}
			
		} catch (Exception e) {
			System.out.println("passCheck 메소드 내부에서 오류!");
			e.printStackTrace();
		} finally {
			closeResource();
		}
		
		return result;
	}
	
	//현재 게시판 DB에 저장된 글의 총 수 조회 하는 메소드
		public int getTotalRecord() {
			String sql = null;
			
			//조회된 글의 글수 저장
			int total = 0;
			
			sql = "select count(*) as b_cnt from board";
					
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				rs.next();
				total = rs.getInt("b_cnt");
				 
			}catch (Exception e) {
				System.out.println("getTotalRecord메소드에서 오류");
				e.printStackTrace();
			}finally {
				closeResource();
			}
		
			return total;
		}

		public int boardUpdate(String b_idx, String email, String title, String content) {
			String sql = null;

			//result 변수 생성 후 초기화
			int resultUpdate = 0;
			
			try {
				con = ds.getConnection();
				
		         sql = "UPDATE board SET"
		                  + " b_email=?, b_title=?, b_content=?"
		                  + " WHERE b_idx=?";

			
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, email);
				pstmt.setString(2, title);
				pstmt.setString(3, content);
				pstmt.setInt(4,  Integer.parseInt(b_idx));
				
				System.out.println(sql);
				
				resultUpdate = pstmt.executeUpdate();
				
				
			} catch (Exception e) {
				System.out.println("boardUpdate 메소드 내부에서 오류!");
				e.printStackTrace();
			} finally {
				closeResource();
			}
			
			return resultUpdate;
		}
		
		// 요청한 글번호를 받아와서 DB에서 삭제하는 작업
		public String deleteBoard(String delete_idx) {
			String sql = null;
			
			//result 변수 생성 후 초기화
			String delete_result = null;
			
			try {
				
				//커넥팅 풀에 접속
				con = ds.getConnection();
				
				 // sql 문으로 전달한 DELETE 문 작성
				sql = "DELETE FROM board WHERE b_idx='"+delete_idx+"'";

		         
		         System.out.println(sql);
		                  

			
				pstmt = con.prepareStatement(sql);
				// sql 구문으로 DELETE 문을 실행 한뒤에 그 값을 저장
				// 삭제 성공했을 경우 1을 반환, 안되면 0을 반환
				int val = pstmt.executeUpdate();
				
				// sql에 DELETE문 잘 들어갔는지 출력해보기
				System.out.println(val);
				
				
				if(val == 1) {
					delete_result = "삭제성공";
				}else {
					
					delete_result = "삭제실패";
				}
				
			} catch (Exception e) {
				System.out.println("deleteBoard 메소드 내부에서 오류!");
				e.printStackTrace();
			} finally {
				closeResource();
			}
			
			
			return delete_result;
		}
		
		public void replyInsertBoard(String super_b_idx,
										  String reply_id_,
										  String reply_name,
										  String reply_email,
										  String reply_title,
										  String reply_content,
										  String reply_pass) {
			String sql = null;
			
			
			try {
				
				System.out.println("replyInsertBoard 메소드 실행");
				con = ds.getConnection();
				
				//1. 부모글의 글번호를 이용해 b_group열의 값과, b_level열의 값을 조회
				 sql = "SELECT b_group, b_level from board where b_idx=?";
				
				pstmt = con.prepareStatement(sql);
				System.out.println(sql);
				pstmt.setInt(1, Integer.parseInt(super_b_idx));
				rs = pstmt.executeQuery();
				rs.next();
				// 부모글의 그룹번호
				String b_group = rs.getString("b_group");
				
				// 부모글의 들여쓰기 정도 레벨값
				String b_level = rs.getString("b_level");
				
				
				// 2. 부모글의 b_group(그룹번호)보다 큰 그룹번호를 가진 글이 있다면
				// 1증가하여 UPDATE 시켜야 합니다
				sql = "UPDATE board set b_group = b_group + 1 WHERE b_group > ?";
				
				System.out.println(sql);
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(b_group));
				pstmt.executeUpdate();
				
				// 3. 처리한 값들을 insert SQL 문에 넣어서 DB에 값 입력
				// 답변글 insert SQL 문 만들기
				sql = "insert into board "
					    + "(b_idx, b_id, b_pw, b_name, "
					    + " b_email, b_title, b_content, "
					    + " b_group, b_level, b_date, b_cnt) "
					    + " values(boarder_b_idx.nextVal,"
					    + " ?, ?, ?, ?, ?, ?, ?, ?, sysdate, 0)";
				
				System.out.println(sql);
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, reply_id_);
				pstmt.setString(2, reply_pass);
				pstmt.setString(3, reply_name);
				pstmt.setString(4, reply_email);
				pstmt.setString(5, reply_title);
				pstmt.setString(6, reply_content);
				// 답변글의 b_group 번호를 부모글 보다 +1 증가한 값으로 입력
				pstmt.setInt(7, Integer.parseInt(b_group) +1);
				// 답변글의 b_level 번호를 부모글 보다 +1 증가한 값으로 입력
				pstmt.setInt(8, Integer.parseInt(b_level) +1);
				
				pstmt.executeUpdate();
				
			
			} catch (Exception e) {
				System.out.println("replyInsertBoard 메소드 내부에서 SQL 실행 오류");
				e.printStackTrace();
			} finally {
				closeResource();
			}
				
		}
				
		
	
		
		*/
		
}
