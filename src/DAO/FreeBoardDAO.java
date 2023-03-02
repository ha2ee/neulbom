package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import VO.BoardVo;
import VO.FileBoardVo;
import VO.FreeBoardVo;
import VO.LikeVo;


public class FreeBoardDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	public FreeBoardDAO() {
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
//------------------------------------------------------------------------------------------- 기본적으로 필요한 부분

	public ArrayList boardListAll(int startRow, int pageSize) {
		ArrayList list = new ArrayList();//6         //5
		FreeBoardVo vo;
		if(startRow>pageSize) {
			pageSize = startRow + pageSize;
		}
		
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM ( SELECT ROWNUM RM, B_IDX, B_ID,B_NICKNAME,B_TITLE,B_CONTENT,B_GROUP,B_LEVEL,B_DATE,B_CNT,B_FILE,B_LIKE FROM (SELECT * FROM FREE_BOARD ORDER BY B_IDX DESC)) WHERE RM BETWEEN ? AND ?";
//			String sql = "SELECT * FROM FREE_BOARD";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, pageSize);

			rs = pstmt.executeQuery();

			while(rs.next() ) {
				vo = new FreeBoardVo(
								rs.getInt("b_idx"), 
								rs.getString("b_id"), 
								rs.getString("b_nickname"), 
								rs.getString("b_title"), 
								rs.getString("b_content"),
								rs.getInt("b_group"), 
								rs.getInt("b_level"),
								rs.getDate("b_date"),
								rs.getInt("b_cnt"),
								rs.getString("b_file"),
								rs.getInt("b_like")
									);

				list.add(vo);
				System.out.println("list담았다!");
			}
			
		} catch (Exception e) {
			System.out.println("boardListAll 메소드에서 에러가 발생하였습니다. 이유는 ? --> " +e);
			e.printStackTrace();
		} finally {
			closeResource();
		}
		return list;
	}

	public int getTotalRecord() {
		int count = 0;
		try {
			con = ds.getConnection();

			String sql = "SELECT COUNT(*) FROM FREE_BOARD";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1); 
			}
			
		} catch (Exception e) {
			System.out.println("getTotalRecord 메소드에서 에러가 발생하였습니다. 이유는 ? --> " +e);
			e.printStackTrace();
		} finally {
			closeResource();
		}
		
		return count;
	}
	public int getTotalRecordserch(String key, String word) {
		int count = 0;
		try {
			con = ds.getConnection();

			String sql = "SELECT COUNT(*) FROM FREE_BOARD";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1); 
			}
			
		} catch (Exception e) {
			System.out.println("getTotalRecord 메소드에서 에러가 발생하였습니다. 이유는 ? --> " +e);
			e.printStackTrace();
		} finally {
			closeResource();
		}
		
		return count;
	}

	public int insertBoard(FreeBoardVo vo) {
		int result = 0 ;
		System.out.println(vo.getB_id() +"/"+ vo.getB_nickname() +"/"+ vo.getB_title() +"/"+ vo.getB_content() +"/"+ vo.getB_file()  );
		try {
			con = ds.getConnection();
			
			String sql = "INSERT INTO FREE_BOARD VALUES(FREEBOARD_SEQ.NEXTVAL,?,?,?,?,0,0,SYSDATE,0,?,0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getB_id());
			pstmt.setString(2, vo.getB_nickname());
			pstmt.setString(3, vo.getB_title());
			pstmt.setString(4, vo.getB_content());
			pstmt.setString(5, vo.getB_file());
			result = pstmt.executeUpdate();
			System.out.println(result);
			
		} catch (Exception e) {
			System.out.println("insertBoard 메소드에서 에러가 발생하였습니다. 이유는 ? --> " +e);
			e.printStackTrace();
		} finally {
			closeResource();
		}
		
		return result;
	}

	public FreeBoardVo boardRead(int b_idx) {
		FreeBoardVo vo = null;
	
		try {
			con = ds.getConnection();

			String sql = "SELECT * FROM FREE_BOARD WHERE B_IDX=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_idx);
			rs = pstmt.executeQuery();

			if(rs.next()) {
			vo = new FreeBoardVo();
			
			vo.setB_idx(rs.getInt("b_idx"));
			vo.setB_id(rs.getString("b_id"));
			vo.setB_nickname(rs.getString("b_nickname"));
			vo.setB_title(rs.getString("b_title"));
			vo.setB_content(rs.getString("b_content"));
			vo.setB_group(rs.getInt("b_group"));
			vo.setB_level(rs.getInt("b_level"));
			vo.setB_date(rs.getDate("b_date"));
			vo.setB_cnt(rs.getInt("b_cnt"));
			vo.setB_file(rs.getString("b_file"));
			vo.setB_like(rs.getInt("b_like"));
			
			System.out.println(vo.getB_idx());
			}
		
			
		} catch (Exception e) {
			System.out.println("boardRead 메소드에서 에러가 발생하였습니다. 이유는 ? --> " +e);
			e.printStackTrace();
		} finally {
			closeResource();
		}
		
		
		return vo;
	}
	
public ArrayList boardList(String key,String word) {
		
		String sql = null;
		
		ArrayList list = new ArrayList();
		
		if(!word.equals("")) {//검색어를 입력했다면 ?
			
			if(key.equals("titleContent")) {//검색 기준값 제목+내용을 선택했다면 ?
				
				sql = "select * from free_board "
						+ " where b_title like '%"+word+"%' "
						+ " or b_content like '%"+word+"%' order by b_group asc";
			}else{//검색 기준값 작성자를 선택했다면?
				
				sql = "select * from free_board where b_nickname like '%"+word+"%' order by b_group asc";
			}
			
		}else {//검색어를 입력하지 않았다면?
			//모든 글 조회
			//조건 -> b_idx열의 글번호 데이터들을 기준으로 해서 내림 차순으로 정렬 후 조회!
			sql = "select * from free_board order by b_group asc";
			
			//참고. 정렬 조회 -> order by 정렬기준열 desc(내림차순) 또는 asc(오름차순)
		}
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			//조회된 Result의 정보를 한행 단위로 꺼내서
			//BoardVo객체에 한행씩 저장 후 BoardVo객체들을 ArrayList배열에 하나씩 추가해서 저장
			while(rs.next()) {
				FreeBoardVo vo = new FreeBoardVo(rs.getInt("b_idx"), 
						rs.getString("b_id"), 
						rs.getString("b_nickname"), 
						rs.getString("b_title"), 
						rs.getString("b_content"),
						rs.getInt("b_group"), 
						rs.getInt("b_level"),
						rs.getDate("b_date"),
						rs.getInt("b_cnt"),
						rs.getString("b_file"),
						rs.getInt("b_like"));
				list.add(vo);
				System.out.println(list);
			}
			
		} catch (Exception e) {
			System.out.println("boardList 메소드 내부에서 오류!");
			e.printStackTrace();
		} finally {
			closeResource();
		}
		
		
		
		return list;
	}

public LikeVo checkLike(String sessionId, int b_idx) {
	LikeVo vo = null;
	try {
		con = ds.getConnection();

		String sql = "SELECT * FROM LIKE_TABLE WHERE FREEBOARD_B_ID= ? AND FREEBOARD_B_IDX = ? ";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, sessionId);
		pstmt.setInt(2, b_idx);
		
		rs = pstmt.executeQuery();

		if(rs.next()) {
			vo = new LikeVo(rs.getInt("like_number"),
						   rs.getString("freeBoard_b_id"),
						   rs.getInt("freeBoard_b_idx"),
						   rs.getInt("like_check")
						   );
		}
	
		
	} catch (Exception e) {
		System.out.println("checkLike 메소드에서 에러가 발생하였습니다. 이유는 ? --> " +e);
		e.printStackTrace();
	} finally {
		closeResource();
	}
	
	return vo;
}

public int insertLikeBoard(int b_idx2, String id3) {
	int result = 0;
	
	try {
		con = ds.getConnection();

		String sql = "INSERT INTO LIKE_TABLE VALUES(LIKE_BOARD_SEQ.NEXTVAL,?,?,1)";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id3);
		pstmt.setInt(2, b_idx2);
		
		pstmt.executeUpdate();
		
		sql = "UPDATE FREE_BOARD SET B_LIKE = B_LIKE + 1 WHERE B_IDX=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, b_idx2);
		
		result = pstmt.executeUpdate();
		
	} catch (Exception e) {
		System.out.println("checkLike 메소드에서 에러가 발생하였습니다. 이유는 ? --> " +e);
		e.printStackTrace();
	} finally {
		closeResource();
	}
	
	return result;
}

public int getOnlyLikeCount(int b_idx2) {
	int result = 0;
	FreeBoardVo vo =null;
	try {
		con = ds.getConnection();

		String sql = "SELECT B_LIKE FROM B_IDX=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, b_idx2);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			vo = new FreeBoardVo();
			vo.setB_like(rs.getInt("b_like"));
		}
		result = vo.getB_like();
		
	} catch (Exception e) {
		System.out.println("checkLike 메소드에서 에러가 발생하였습니다. 이유는 ? --> " +e);
		e.printStackTrace();
	} finally {
		closeResource();
	}
	return result;
}

	
	
	
	
}


