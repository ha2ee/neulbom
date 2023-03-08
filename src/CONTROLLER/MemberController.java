package CONTROLLER;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import DAO.OrderDAO;
import VO.MemberVo;
import VO.nbOrderVo;
import VO.nbTrOrderVo;
import VO.trMemberVo;




@WebServlet("/member/*")
public class MemberController extends HttpServlet{
	
	
	// 오더 메소드를 만들 오더dao 호출
	OrderDAO orderdao;
	
	// 일반회원 가입 메소드를 만들 MemberDao 호출
	MemberDAO memberdao;
	
	// 트레이너 정보를 조회할 trMemberVo를 호출
	trMemberVo trmembervo;
	
	// 조회 또는 저장 시킬 오더vo, tr오더vo 호출
	nbOrderVo nbordervo;
	nbTrOrderVo nbtrordervo; 
	
	@Override
	public void init() throws ServletException {
		orderdao = new OrderDAO();
		memberdao = new MemberDAO();

	}
	
	@Override
	protected void doGet(HttpServletRequest request, 
						HttpServletResponse response) 
						throws ServletException, IOException {
		doHandle(request,response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, 
						HttpServletResponse response) 
						throws ServletException, IOException {
		doHandle(request,response);
		
	}
	protected void doHandle(HttpServletRequest request, 
							HttpServletResponse response) 
							throws ServletException, IOException {
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//1) nController Option /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 한글 처리
		request.setCharacterEncoding("utf-8");
		// 웹브라우저로 응답할 데이터 종류 설정
		response.setContentType("text/html;charset=utf-8");
		// 웹브라우저와 연결된 출력 스트림 통로 만들기
		PrintWriter out = response.getWriter();
		// 서블릿으로 요청한 주소를  request에서 얻기
		String action = request.getPathInfo();
		// 2단계 요청주소 출력하기
		System.out.println("2단계 요청 주소: "+action);
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//2) nController INFO ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// 1) 	/login.me 					<- 				"로그인" 	    							  											     요청
		// 1-1) /loginPro.me				<-				"로그인"										 아이디 & 비밀번호 작성 후 로그인 버튼 요청
		// 1-2  /logout.me					<-				"로그아웃"																				 요청
		// 2)   	/joinCategory.me          <-             "회원 가입 유형"                                                                  선택    요청
		// 2-1)	/memJoin.me				<-				"회원 가입 유형 " 		-> 			"일반 	 회원 가입"						 선택시 요청
		// 2-2) /memJoinPro.me			<-				"회원 가입 " 				-> 			"일반   회원 가입" 		-> 			"회원 가입"요청
		// 3)	/trJoin.me					<-				"회원 가입 유형 " 		-> 			"훈련사 회원 가입"						 선택시 요청
		// 4) 	/change.me				<-				"회원 정보" 																				 요청
		// 4-1) /delete.me					<-				"회원 정보 삭제" 																			 요청
		// 4-2) /updateinfo.me			<-				"회원 정보 수정" 																			 요청
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		
		
		// nextPage 변수 생성 및 초기화
		String nextPage = null;
		
		// # 1) "로그인" 버튼을 눌러 요청 했을 때,
		if(action.equals("/login.me")) {
			
			System.out.println("MemberController -> login.me 호출!");
			
			//중앙화면 주소 바인딩
			request.setAttribute("center", "nbMember/login.jsp");

			//전체 메인화면 주소 저장
			nextPage = "/nbMain.jsp";
			
			
		// # 1-1) "로그인" -> 아이디&비밀번호 입력 후 로그인 버튼을 요청 했을 때,
		}else if(action.equals("/loginPro.me")) {
			
			System.out.println("nbMemberController -> /loginPro.me 요청!");
			
			String login_id = request.getParameter("memLoginId");
			String login_pass = request.getParameter("memLoginPw");
			
			System.out.println("입력한 id : "+login_id);
			System.out.println("입력한 pw : "+login_pass);
			
			//요청한 값을 이용해 클라이언트의 웹브라우저로 응답할 값을 마련
			//요약 : DB작업 등의 비즈니스로직처리
			//check변수에 저장되는 값이 1이면 아이디, 비밀번호 맞음
			//                   0이면 아이디맞음, 비밀번호 틀림
			//					 -1이면 아이디 틀림 
			int check = memberdao.userCheck(login_id,login_pass);
			
			if(check == 0) {//아이디 맞고 비밀번호 틀림
				out.println("<script>");
				out.println("window.alert('비밀번호 틀림');");
				out.println("history.go(-1);");
				out.println("</script>");
				
				return;
				
			}else if(check == -1){//아이디 틀림
				out.println("<script>");
				out.println("window.alert('아이디 틀림');");
				out.println("history.go(-1);");
				out.println("</script>");
				
				return;
				
			}
			
			//check = 1 아이디 맞음, 비밀번호 맞음
			//로그인 처리를 위해 session메모리 영역에 세션값 저장 후 포워딩
			
			//session메모리 생성
			HttpSession session = request.getSession();
			//session메모리에 입력한 아이디 바인딩 (저장)
			session.setAttribute("id", login_id);
	
			//메인화면 view 주소
			nextPage = "/nbMain.jsp";
			
		// # 1-2) "로그아웃"을 요청 했을 때,
		}else if(action.equals("/logout.me")) {		
		
			System.out.println("nbMemberController -> /logout.me 요청!");
			
			//기존에 생성했던 session메모리 얻기
			HttpSession session_ = request.getSession();
			session_.invalidate(); //세션에 저장된 아이디 제거
			
			//메인화면 view 주소
			nextPage = "/nbMain.jsp";
			
			

		// # 2) "회원가입" 버튼을 눌러 요청 했을 때,
		}else if(action.equals("/joinCategory.me")) {
			
			System.out.println("MemberController -> joinCategory.me 호출!");
			
			//중앙화면 주소 바인딩
			request.setAttribute("center", "nbMember/joinCategory.jsp");

			//전체 메인화면 주소 저장
			nextPage = "/nbMain.jsp";
			
		// # 2-1) "회원가입" -> "일반회원가입" 버튼을 눌러 요청 했을 때,
		}else if(action.equals("/memJoin.me")) {
			
			System.out.println("MemberController -> memJoin.me 호출!");
			
			//중앙화면 주소 바인딩
			request.setAttribute("center", "nbMember/mem_join.jsp");
			
			//전체 메인화면 주소 저장
			nextPage = "/nbMain.jsp";
		
		// # 2-2) "회원가입" -> "일반회원가입" -> "회원 가입" 버튼을 눌러 요청 했을 때,	
		}else if(action.equals("/memJoinPro.me")){
			
			System.out.println("MemberController -> memJoinPro.me 호출!");
			
			String mem_id = request.getParameter("id");
			String mem_name = request.getParameter("name");
			String mem_nick= request.getParameter("nickname");
			String mem_pw = request.getParameter("pass");
			String mem_email = request.getParameter("email");
			String mem_hp = request.getParameter("hp");
			String mem_gender = request.getParameter("gender");
			String mem_birth = request.getParameter("birth");
			String mem_address1 = request.getParameter("address1");
			String mem_address2 = request.getParameter("address2");
			String mem_address3 = request.getParameter("address3");
			String mem_address4 = request.getParameter("address4");
			String mem_pet = request.getParameter("pet");
			
			MemberVo mem_vo = new MemberVo(mem_id, mem_name, mem_nick, mem_pw, mem_email, mem_hp, mem_birth, mem_gender, mem_pet, mem_address1, mem_address2, mem_address3, mem_address4);
				   
				memberdao.insertMember(mem_vo);
				memberdao.insertMemAddress(mem_vo);
			
			//전체 메인화면 주소 저장
			nextPage="/nbMain.jsp";
			
			
		
		// # 3) "회원가입" -> "훈련사회원가입" 버튼을 눌러 요청 했을 때,
		}else if(action.equals("/trJoin.me")) {
			
			System.out.println("MemberController -> trJoin.me 호출!");
			
			//중앙화면 주소 바인딩
			request.setAttribute("center", "nbMember/tr_join.jsp");
			
			//전체 메인화면 주소 저장
			nextPage = "/nbMain.jsp";
			
		// # 3-1) 	"회원가입" -> "훈련사회원가입" ->  "회원 가입" 버튼을 눌러 요청 했을 때,	
		}else if(action.equals("/trJoinPro.me")) {
			
			System.out.println("MemberController -> trJoinPro.me 호출!");
			
			String tr_id = request.getParameter("id");
			String tr_name = request.getParameter("name");
			String tr_nick= request.getParameter("nickname");
			String tr_pw = request.getParameter("pass");
			String tr_email = request.getParameter("email");
			String tr_hp = request.getParameter("hp");
			String tr_gender = request.getParameter("gender");
			String tr_birth = request.getParameter("birth");
			String tr_address1 = request.getParameter("address1");
			String tr_address2 = request.getParameter("address2");
			String tr_address3 = request.getParameter("address3");
			String tr_address4 = request.getParameter("address4");
			String tr_address5 = request.getParameter("address5");
			String tr_pet = request.getParameter("pet");
			
			MemberVo tr_vo = new MemberVo(tr_id, tr_name, tr_nick, tr_pw, tr_email, tr_hp, tr_birth, tr_gender, tr_address1, tr_address2, tr_address3, tr_address4, tr_address5);
					
			memberdao.insertTrMember(tr_vo);
			memberdao.insertTrMemAddress(tr_vo);
			
			
			nextPage="/nbMain.jsp";

		// # 4) 	"회원 정보" 요청 했을 때,
		}else if(action.equals("/change.me")) {
			
			System.out.println("MemberController -> change.me 호출!");
			
			//중앙화면 주소 바인딩
			request.setAttribute("center", "nbMember/change.jsp");

			//전체 메인화면 주소 저장
			nextPage="/nbMain.jsp";
		
		
		// # 4-1) 	"회원 정보 삭제" 요청 했을 때,
		}else if(action.equals("/delete.me")) {
		
			System.out.println("nbMemberController -> /delete.me 요청!");
			
			//삭제할 아이디 값을 얻어와 변수에 저장
			String deleteId = request.getParameter("id");
			
			boolean mem_result = memberdao.memDelete(deleteId);
			boolean tr_result = memberdao.trDelete(deleteId);
			
			if (mem_result == true || tr_result == true) {
				out.write("삭제 성공");
				return;
				
			}else if(mem_result == false && tr_result == false) {
				out.write("삭제 실패");
				return;
				
			}
				
		// # 4-2) 	"회원 정보 수정" 요청 했을 때,
		}else if(action.equals("/updateInfo.me")) {

			System.out.println("nbMemberController -> /updateInfo.me 요청!");
			
			String up_id = request.getParameter("id");
			String up_pw = request.getParameter("pw");
			String up_nick = request.getParameter("nick");
			String up_name = request.getParameter("name");
			String up_hp = request.getParameter("hp");
			String up_birth = request.getParameter("birth");
			String up_email = request.getParameter("email");
			String up_gender = request.getParameter("gender");
			String up_address1 = request.getParameter("address1");
			String up_address2 = request.getParameter("address2");
			String up_address3 = request.getParameter("address3");
			String up_address4 = request.getParameter("address4");
			String up_address5 = request.getParameter("address5");
			
			int up_MemResult = memberdao.memUpdate(up_id, up_pw, up_nick, up_name, up_hp, up_birth, up_email, up_gender);
			int up_TrResult = memberdao.trUpdate(up_id, up_pw, up_nick, up_name, up_hp, up_birth, up_email, up_gender);
			int up_MemAddResult = memberdao.memAddUpdate(up_id, up_address1,up_address2,up_address3,up_address4,up_address5);
			int up_TrAddResult = memberdao.trAddUpdate(up_id, up_address1,up_address2,up_address3,up_address4,up_address5);

			
			if ((up_MemResult == 1 && up_MemAddResult == 1 ) || (up_TrResult == 1 && up_TrAddResult == 1 )) {
				out.write("수정 성공");
				return;
				
			}else if((up_MemResult == 0  ||  up_MemAddResult == 0 ) && (up_TrResult == 0 || up_TrAddResult == 0 )) {
				out.write("수정 실패");
				return;
			}

			//전체 메인화면 주소 저장
			nextPage="/nbMain.jsp";
			
		}
		
		//포워딩 (디스패처 방식)
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		
	}
}
