package CONTROLLER;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.BoardDAO;
import DAO.MemberDAO;
import VO.FreeBoardVo;
import VO.MemberVo;


//게시판 관련 기능 요청이 들어오면 호출되는 사장님(컨트롤러)
@WebServlet("/freeboard/*")
public class BoardController extends HttpServlet{
	
	//BoardDAO객체를 저장할 참조변수 선언
	BoardDAO boarddao;
	
	//MemberDAO객체를 저장할 참조변수 선언
	MemberDAO memberdao;
	
	//MemberVo객체를 저장할 참조변수 선언
	MemberVo membervo;
	
	@Override
	public void init() throws ServletException {
		boarddao = new BoardDAO();
		memberdao = new MemberDAO();
		membervo = new MemberVo();
		
		
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

	private void doHandle(HttpServletRequest request, 
							HttpServletResponse response) 
							throws ServletException, IOException {
		
		//한글처리
		request.setCharacterEncoding("utf-8");
		//웹브라우저로 응답할 데이터 종류 설정
		response.setContentType("text/html;charset=utf-8");
		//웹브라우저와 연결된 출력 스트림 통로 만들기
		PrintWriter out = response.getWriter();
		//서블릿으로 요청한 주소를 request에서 얻기
		String action = request.getPathInfo();//2단계 요청 주소
		System.out.println("요청한  주소 : "+action);
		// /write.bo	  		<- 새글 작성 화면 요청!
		// /writePro.bo 		<- 입력한 새글 정보를 DB에 추가 요청!
		// /list.bo 		  		<- DB에 저장된 글 목록을 조회 하여 보여주는 요청 주소
		// /read.bo 	  		<- 게시판에서 제목을 눌러서 글을 수정하려할때 보여주는 요청 주소
		// /password.bo		<- 게시판에서 제목을 누른 뒤 비밀번호를 입력 받으려 할때 요청 하는 주소
		// /boardUpdate.bo  <- 게시판 -> 제목 눌리고 -> 비번 입력후 -> 수정 다하고 -> 수정 버튼 눌렀을때 요청
		// /deleteBoard.bo   <- 게시판 -> 제목 눌리고 -> 비번 입력후 -> 삭제 버튼 눌렀을때 요청 주소
		// /reply.bo           <- 게시판 -> 제목 눌리고 -> 답변 버튼을 클릭했을때 요청 주소
		
		//조건에 따라서 포워딩 또는 보여줄 VIEW주소 경로를 저장할 변수
		String nextPage = null;
		//요청한 중앙화면 뷰 주소를 저장할 변수
		String center = null;
		//BoardVo객체를 저장할 참조변수 선언
		FreeBoardVo vo = null;
		ArrayList list = null;
		int count = 0;
		String key = null;
		String word = null;
		String memberid = null;
		
	      switch (action) {
	      //새글 입력하는 화면 요청!
	      case "/write.bo":
	         
	         //새글을 입력하는 화면에 로그인한 회원의 이름, 아이디, 이메일을 보여주기 위해
	         //member테이블에서 SELECT하여 가져와야 합니다.
	         HttpSession session = request.getSession();
	         memberid = (String)session.getAttribute("id");
	         
	         System.out.println(memberid);
	         
	         membervo = memberdao.memberOne(memberid);
	         
	         request.setAttribute("center", "board/write.jsp");
	         request.setAttribute("membervo", membervo);
	         
	         request.setAttribute("nowPage", request.getParameter("nowPage"));
	         request.setAttribute("nowBlock", request.getParameter("nowBlock"));
	         
	         nextPage = "/CarMain.jsp";
	         break;

			
		case "/writePro.bo":
			//요청한 값 얻기
			String writer = request.getParameter("w");
			String email = request.getParameter("e");
			String title = request.getParameter("t");
			String content = request.getParameter("c");
			String pass = request.getParameter("p");
			String id = request.getParameter("i");
			
			//요청한 값을 BoardVo객체의 각 변수에 저장
			vo = new FreeBoardVo();
			vo.setB_name(writer);
			vo.setB_email(email);
			vo.setB_title(title);
			vo.setB_content(content);
			vo.setB_pw(pass);
			vo.setB_id(id);
			
			//응답할 값 마련 (DB에 새글 정보를 INSERT 후 성공하면 추가메세지 마련)
			// result=1 -> DB에 새글 정보 추가 성공
			// result=0 -> DB에 새글 정보 추가 실패
			int result = boarddao.insertBoard(vo);
			// "1" 또는 "0"
			String go = String.valueOf(result);
			
			//write.jsp로 ($.ajax()메소드 내부의 success:function(data)의 data매개변수로 전달)
			if(go.equals("1")) {
				out.print(go);
			}else {
				out.print(go);
			}
			return;
		
		//게시판 모든 글 조회 요청
		case "/list.bo":
			
			session = request.getSession();
			String loginid = (String)session.getAttribute("id");
			
			
			//요청한 값을 이용해 응답할 값 마련(글 조회)
			list = boarddao.boardListAll();
			count = boarddao.getTotalRecord();
			
			//list.jsp페이지의 페이징 처리 부분에서
			//이전 또는 다음 또는 각 페이지 번호를 클릭했을때.. 요청받는 값 얻기
			String nowPage = request.getParameter("nowPage");
			String nowBlock = request.getParameter("nowBlock");
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("id", loginid);
			request.setAttribute("nowPage", nowPage);
			request.setAttribute("nowBlock", nowBlock);
			
			request.setAttribute("center", "board/list.jsp");
			
			nextPage = "/CarMain.jsp";
			break;
		
		// 게시판에서 찾고자 하는 내용을 쓰고 검색했을때
		case "/searchlist.bo":
			
			//요청한 값 얻기 (검색을 위해 선택한 option의 값 하나, 입력한 검색어)
			key = request.getParameter("key");
			word = request.getParameter("word");
			
			//요청한 값을 이용해 응답할 값 마련(글 조회)
			list = boarddao.boardList(key,word);
			count = boarddao.getTotalRecord(key, word);
			
			request.setAttribute("center", "board/list.jsp");
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			
			nextPage = "/CarMain.jsp";
			break;	
		
		// 게시판에서 제목을 클릭해서 내용을 보려고 할때
		case "/read.bo":	
			
			
			//요청한 값 얻기
			String b_idx = request.getParameter("b_idx");
			nowPage = request.getParameter("nowPage");
			nowBlock = request.getParameter("nowBlock");
			
			// 요청한 값 출력해보기
			System.out.println(b_idx);
			System.out.println(nowPage);
			System.out.println(nowBlock);
			
			//글 번호 (b_idx)를 이용해 수정 또는 삭제를 위해 DB로 부터 조회하기
			vo = boarddao.boardRead(b_idx);
			
			// 중앙화면에 read.jsp로 전달하기 위해 setAttribute로 담음
			// 페이지번호, 페이지블럭번호, 글번호 3가지
			request.setAttribute("nowPage", nowPage);
			request.setAttribute("nowBlock", nowBlock);
			request.setAttribute("b_idx", b_idx);
			
			// 글번호로 조회한 정보도 받아와서 설정
			request.setAttribute("vo", vo);
			
			// 중앙화면에 요청할 주소를 read.jsp로 설정
			request.setAttribute("center", "board/read.jsp");

			
			nextPage = "/CarMain.jsp";
			
			break;
			
			// 글을 수정하기 위해 입력한 비밀번호가 DB에 저장 되어있는지 요청
			case "/password.bo":
				
				// 글에대한 글번호와 입력한 비밀번호 얻기
				String b_idx_ = request.getParameter("b_idx");
				String password = request.getParameter("pass");
				
				// DB 작업
				boolean resultPass = boarddao.passCheck(b_idx_, password);
				
				// 얻어온 값 확인 출력
				System.out.println(resultPass);
				
				if(resultPass == true) {
					out.write("비번일치함");
					return;
				}else {
					out.write("비번틀림");
					return;
				}
				
			// 글을 수정하기 위해 비번을 입력해놓고 수정 버튼을 눌렀을때 요청
			case "/boardUpdate.bo":
				
				// 글에대한 글번호와 입력한 비밀번호 얻기
				b_idx = request.getParameter("b_idx");
				email = request.getParameter("email");
				title = request.getParameter("title");
				content = request.getParameter("content");
				
				
				// DB 작업
				result = boarddao.boardUpdate(b_idx, email, title, content);
				
				// 얻어온 값 확인 출력
				System.out.println(result);
				
				if(result == 1) {
					out.write("수정성공");
					return;
				}else {
					out.write("수정실패");
					return;
				}
				
		// 글을 삭제하기 위해 버튼을 눌렀을때 요청
		case "/deleteBoard.bo":
		
			// 요청 값 얻기
			String delete_idx = 	request.getParameter("b_idx");
			
			// DELETE 작업을위해 boardao에서 idx 값 얻어오기
			String result__ = boarddao.deleteBoard(delete_idx);
			
			// 요청값 잘받아왔는지 출력해보기
			System.out.println(result__);
			
			out.write(result__);
			
			return;
			
			
		// 답변버튼을 눌러서 띄워질 화면을 요청
			
		case "/reply.bo":
			

			//요청 값 얻기 (게시판 글번호)
			String b_idx__ = request.getParameter("b_idx");
			
			String reply_id = request.getParameter("id");
			
			String name = request.getParameter("name");
			
			MemberVo reply_vo = memberdao.memberOne(reply_id);
						
			// 글 번호 바인딩 시키기
			request.setAttribute("b_idx", b_idx__);
			
			request.setAttribute("id", reply_id);
			
			request.setAttribute("name", name);
			
			request.setAttribute("vo", reply_vo);
			
			// 중앙화면 주소 바인딩
			request.setAttribute("center", "board/reply.jsp");
			
			// nextPage 변수에 CarMain.jsp 설정 해서 요청하기
			nextPage = "/CarMain.jsp";
			
			break;
			
		// 답변글 DB에 추가 요청
		case "/replyPro.bo":
			
			// 부모글 글번호 + 작성한 답변글 내용 얻기
			// 부모 글번호 얻기
			String super_b_idx = request.getParameter("super_b_idx");
			// 아이디 얻기
			String reply_id_ = request.getParameter("reply_id");
			// 작성자명 얻기
			String reply_name = request.getParameter("reply_name");
			// 작성자 이메일 얻기
			String reply_email = request.getParameter("email");
			// 제목 얻기
			String reply_title = request.getParameter("title");
			// 내용 얻기
			String reply_content = request.getParameter("content");
			// 비밀번호 얻기
			String reply_pass = request.getParameter("pass");
			
			// DB에 입력한 답변글을 추가
			boarddao.replyInsertBoard(super_b_idx, reply_id_, reply_name, reply_email, reply_title, reply_content, reply_pass);
			
			// 답변 추가 성공 후 다시 전체글을 조회하기 위한 요청주소를 nextPage에 저장
			
			nextPage = "/board1/list.bo";
			
		default:
			break;
		}
		
		
		//포워딩 (디스패처 방식)
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
	
	
}
