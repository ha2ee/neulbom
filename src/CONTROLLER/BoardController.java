package CONTROLLER;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;

import com.oreilly.servlet.multipart.*;
import com.oreilly.servlet.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import DAO.BoardDAO;
import DAO.FreeBoardDAO;
import DAO.MemberDAO;
import VO.FreeBoardVo;
import VO.MemberVo;
import VO.test0000;


//게시판 관련 기능 요청이 들어오면 호출되는 사장님(컨트롤러)
@WebServlet("/freeboard/*")
public class BoardController extends HttpServlet{
	
	//FreeBoardDAO객체를 저장할 참조변수 선언
 	FreeBoardDAO boarddao;
	
	//MemberDAO객체를 저장할 참조변수 선언
//	MemberDAO memberdao;
	
	//MemberVo객체를 저장할 참조변수 선언
//	MemberVo membervo;
	
	private static String ARTICLE_REPO = "/Users/inseop/Desktop/팀프로젝트/upload";
	List items;
	FileItem fileItem;
 	
	@Override
	public void init() throws ServletException {
		boarddao = new FreeBoardDAO();
//		memberdao = new MemberDAO();
//		membervo = new MemberVo();
		
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
		
		//조건에 따라서 포워딩 또는 보여줄 VIEW주소 경로를 저장할 변수
		String nextPage = null;
		//요청한 중앙화면 뷰 주소를 저장할 변수
		String center = null;
		//BoardVo객체를 저장할 참조변수 선언
		FreeBoardVo vo = null;
		test0000 testvo = null;
		ArrayList list = null;
//		String key = null;
//		String word = null;
//		String memberid = null;
		
	      switch (action) {
	      //새글 입력하는 화면 요청!
	      case "/list.fb":
	    	  
	    		String pageNum = request.getParameter("pageNum");
	    		int startRow = Integer.parseInt(request.getParameter("startRow"));
	    		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	    		
	    		
	    	  list = boarddao.boardListAll(startRow,pageSize);
	    	  int count = boarddao.getTotalRecord();
	    	  testvo = new test0000();
	    	  testvo.setCount(count);
	    	  
        	  request.setAttribute("list", list);
 	    	  request.setAttribute("testvo", testvo);

	    	  
        	  request.setAttribute("center", "nbBoard/list.jsp");
	    	  nextPage = "/nbMain.jsp";
	    	  break;
	    	  
	      case "/write.fb":
	    	  
	    	 request.setAttribute("center", "nbBoard/write.jsp");
	         nextPage = "/nbMain.jsp";
	         break;
	         
	         
		case "/writePro.fb":
//			//요청한 값 얻기
			
//			//세션값으로 아이디 + 닉네임을 구할 것입니다.
			String id = "inseop";
			String nickname = "seeeop2";
			
//			//업로드 작업 중ㅇ...
			String directory ="/Users/inseop/Desktop/팀프로젝트/upload";
			System.out.println(directory);
			int maxSize = 1024 * 1024 * 100;
			String encoding = "utf-8";
//			
			MultipartRequest multipartRequest = new MultipartRequest(request, directory,maxSize,encoding,new DefaultFileRenamePolicy());
			System.out.println("오니?");
			String title = multipartRequest.getParameter("title");
			String content = multipartRequest.getParameter("editor1");
			String fileName = multipartRequest.getOriginalFileName("fileName");
			String fileRealName = multipartRequest.getFilesystemName("file");
//			//여기까지
			

			vo= new FreeBoardVo(id, nickname, title, content, fileName);
			int result = boarddao.insertBoard(vo);
			
			if(result ==1) {
				out.println("<script>");
				out.println("alert('작성 성공!')");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('작성 실패!')");
				out.println("</script>");
			}
	         nextPage = "/freeboard/list.fb";
	         
	         break;
			
	         
		// 게시판에서 제목을 클릭해서 내용을 보려고 할때
		case "/read.fb":	
			
//			//요청한 값 얻기
			int b_idx = Integer.parseInt( request.getParameter("b_idx") );
			System.out.println(b_idx);
//			//글 번호 (b_idx)를 이용해 수정 또는 삭제를 위해 DB로 부터 조회하기
			vo = boarddao.boardRead(b_idx);
			
//			// 중앙화면에 read.jsp로 전달하기 위해 setAttribute로 담음
//			// 페이지번호, 페이지블럭번호, 글번호 3가지
//			request.setAttribute("nowPage", nowPage);
//			request.setAttribute("nowBlock", nowBlock);
//			request.setAttribute("b_idx", b_idx);
//			
			request.setAttribute("vo", vo);
			request.setAttribute("center", "nbBoard/read.jsp");

	        nextPage = "/nbMain.jsp";
			break;
			
//			//요청한 값을 BoardVo객체의 각 변수에 저장
//			vo = new FreeBoardVo();
//			vo.setB_name(writer);
//			vo.setB_email(email);
//			vo.setB_title(title);
//			vo.setB_content(content);
//			vo.setB_pw(pass);
//			vo.setB_id(id);
//			
//			//응답할 값 마련 (DB에 새글 정보를 INSERT 후 성공하면 추가메세지 마련)
//			// result=1 -> DB에 새글 정보 추가 성공
//			// result=0 -> DB에 새글 정보 추가 실패
//			int result = boarddao.insertBoard(vo);
//			// "1" 또는 "0"
//			String go = String.valueOf(result);
//			
//			//write.jsp로 ($.ajax()메소드 내부의 success:function(data)의 data매개변수로 전달)
//			if(go.equals("1")) {
//				out.print(go);
//			}else {
//				out.print(go);
//			}
//			return;
//		
		//게시판 모든 글 조회 요청
		// 게시판에서 찾고자 하는 내용을 쓰고 검색했을때
		case "/searchlist.fb":
			
//			//요청한 값 얻기 (검색을 위해 선택한 option의 값 하나, 입력한 검색어)
			String key = request.getParameter("key");
			String word = request.getParameter("word");
			System.out.println(key);
			System.out.println(word);
			
			
//			//요청한 값을 이용해 응답할 값 마련(글 조회)
			list = boarddao.boardList(key,word);
			count = boarddao.getTotalRecordserch(key, word); 
//			
			request.setAttribute("center", "nbBoard/list.jsp");
			request.setAttribute("list", list);
			request.setAttribute("count", count);
//			
			 nextPage = "/nbMain.jsp";
			break;
//		
//			
//			// 글을 수정하기 위해 입력한 비밀번호가 DB에 저장 되어있는지 요청
//			case "/password.bo":
//				
//				// 글에대한 글번호와 입력한 비밀번호 얻기
//				String b_idx_ = request.getParameter("b_idx");
//				String password = request.getParameter("pass");
//				
//				// DB 작업
//				boolean resultPass = boarddao.passCheck(b_idx_, password);
//				
//				// 얻어온 값 확인 출력
//				System.out.println(resultPass);
//				
//				if(resultPass == true) {
//					out.write("비번일치함");
//					return;
//				}else {
//					out.write("비번틀림");
//					return;
//				}
//				
//			// 글을 수정하기 위해 비번을 입력해놓고 수정 버튼을 눌렀을때 요청
//			case "/boardUpdate.bo":
//				
//				// 글에대한 글번호와 입력한 비밀번호 얻기
//				b_idx = request.getParameter("b_idx");
//				email = request.getParameter("email");
//				title = request.getParameter("title");
//				content = request.getParameter("content");
//				
//				
//				// DB 작업
//				result = boarddao.boardUpdate(b_idx, email, title, content);
//				
//				// 얻어온 값 확인 출력
//				System.out.println(result);
//				
//				if(result == 1) {
//					out.write("수정성공");
//					return;
//				}else {
//					out.write("수정실패");
//					return;
//				}
//				
//		// 글을 삭제하기 위해 버튼을 눌렀을때 요청
//		case "/deleteBoard.bo":
//		
//			// 요청 값 얻기
//			String delete_idx = 	request.getParameter("b_idx");
//			
//			// DELETE 작업을위해 boardao에서 idx 값 얻어오기
//			String result__ = boarddao.deleteBoard(delete_idx);
//			
//			// 요청값 잘받아왔는지 출력해보기
//			System.out.println(result__);
//			
//			out.write(result__);
//			
//			return;
//			
//			
//		// 답변버튼을 눌러서 띄워질 화면을 요청
//			
//		case "/reply.bo":
//			
//
//			//요청 값 얻기 (게시판 글번호)
//			String b_idx__ = request.getParameter("b_idx");
//			
//			String reply_id = request.getParameter("id");
//			
//			String name = request.getParameter("name");
//			
//			MemberVo reply_vo = memberdao.memberOne(reply_id);
//						
//			// 글 번호 바인딩 시키기
//			request.setAttribute("b_idx", b_idx__);
//			
//			request.setAttribute("id", reply_id);
//			
//			request.setAttribute("name", name);
//			
//			request.setAttribute("vo", reply_vo);
//			
//			// 중앙화면 주소 바인딩
//			request.setAttribute("center", "board/reply.jsp");
//			
//			// nextPage 변수에 CarMain.jsp 설정 해서 요청하기
//			nextPage = "/CarMain.jsp";
//			
//			break;
//			
//		// 답변글 DB에 추가 요청
//		case "/replyPro.bo":
//			
//			// 부모글 글번호 + 작성한 답변글 내용 얻기
//			// 부모 글번호 얻기
//			String super_b_idx = request.getParameter("super_b_idx");
//			// 아이디 얻기
//			String reply_id_ = request.getParameter("reply_id");
//			// 작성자명 얻기
//			String reply_name = request.getParameter("reply_name");
//			// 작성자 이메일 얻기
//			String reply_email = request.getParameter("email");
//			// 제목 얻기
//			String reply_title = request.getParameter("title");
//			// 내용 얻기
//			String reply_content = request.getParameter("content");
//			// 비밀번호 얻기
//			String reply_pass = request.getParameter("pass");
//			
//			// DB에 입력한 답변글을 추가
//			boarddao.replyInsertBoard(super_b_idx, reply_id_, reply_name, reply_email, reply_title, reply_content, reply_pass);
//			
//			// 답변 추가 성공 후 다시 전체글을 조회하기 위한 요청주소를 nextPage에 저장
//			
//			nextPage = "/board1/list.bo";
			
		default:
			break;
		}
		
		
		//포워딩 (디스패처 방식)
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
	//파일을 웹애플리케이션 서버의 하드디스크 공간에 업로드 하는 기능의 메소드 
	private Map<String, String> upload(HttpServletRequest request, 
									   HttpServletResponse response) 
									   throws ServletException, IOException{
		
		//가변 길이 메모리 
		Map<String, String> articleMap = new HashMap<String, String>();
		
		//한글처리 
		request.setCharacterEncoding("UTF-8");
		
		//인코딩 방식 UTF-8 문자열을 변수에 저장
		String encoding = "UTF-8";
		
		//업로드할 파일 폴더경와 연결된 File객체 생성
		File currentDirPath = new File(ARTICLE_REPO);
		
		//업로드할 파일 데이터를 임시로 저장할 객체 메모리 생성
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//임시 메모리의 최대 사이즈를 1메가 바이트로 설정
		factory.setSizeThreshold(1024*1024*1);
		//임시 메모리에 파일업로드시~ 지정한 1메가 바이트크기를 넘길경우
		//실제 업로드될 파일 폴더 경로를 설정
		factory.setRepository(currentDirPath);
		
		/*
		  참고. DiskFileitemFactory클래스는 
		           업로드 파일의 크기가 지정한 임시메모리의 크기를 넘기기전까지는
		           업도르 한 파일 데이터를 임시메모리에 저장하고
		           지정한 임시메모리 크기를 넘길 경우 최종 업로드할 폴더에 업로드 시킨다.    
		 */
		
		//파일 업로드할 메모리를 생성자쪽으로 전달받아 저장하여 생성되는
		//파일업로드 기능을 처리할 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
	
		try {
			
			//uploadForm.jsp에서 업로드요청할 파일의 정보,
			//입력한 문자열 정보들이 저장된 request객체 메모리를
			//ServletFileUpload객체의 parseRequest메소드 호출시 
			//매개변수로 전달 하면 
			//request객체메모리에 저장된 요청하는 아이템들을
			//파싱(추출)해서 DiskFileItem객체에 각각 저장한 후 
			//DiskFileItem객체들을  ArrayList배열에 추가합니다.
			//그 후 ArrayList배열 자체를 반환 받습니다. 
			 items  = upload.parseRequest(request);
			 
			 //ArrayList가변 길이 배열의 크기만큼(DiskFileItem객체의 갯수만큼) 반복
			 for(int i=0;  i<items.size(); i++) {
				 
				 //ArrayList가변 배열에서..DiskFileItem객체를 얻는다.
				 fileItem = (FileItem)items.get(i);
				 
				 //DiskFileItem객체(요청한 아이템 하나의 정보)가  
				 //파일 아이템이 아닐경우
				 if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
					
					    //DiskFileItem객체(요청한 아이템 하나의 정보)가  
				 }else {//파일일 경우 
					 System.out.println("파라미터:" + fileItem.getFieldName());
					 System.out.println("파일명:" + fileItem.getName());
					 System.out.println("파일크기:" + fileItem.getSize() + "bytes");
					 
					 //업로드할 파일의 크기가 0보다 크다면?(업로드할 파일이 있다면?)
					 if(fileItem.getSize() > 0) {
						 
						 //업로드할 파일명을 얻어  파일명의 뒷에서부터 \\문자열이 포함되어 있는지
						 //index위치번호를 알려주는데 없으면 -1을 반환함
						 int idx = fileItem.getName().lastIndexOf("\\");
			
						 System.out.println(idx);
			
						 if(idx == -1) {//업로드할 파일명에 \\문자열이 포함되어 있지 않으면?
							 idx = fileItem.getName().lastIndexOf("/"); // -1 얻기
							 System.out.println("안녕:" + idx);
						 }
						 
						 //업로드할 파일명 얻기
						 String fileName = fileItem.getName().substring(idx+1);
						 //업로드할 파일 경로 + 파일명을 만들어서 그경로에 접근할 File객체 생성
						 File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						 
						 //해쉬맵에 업로드한 파일명 저장 
						 articleMap.put(fileItem.getFieldName(), fileName);
						 
						 //실제 위 경로에 파일 업로드
						 fileItem.write(uploadFile);
					 }				 
				 }		 
			 }		
		}catch(Exception e) {
			
			e.printStackTrace();
		}	
		return articleMap;	
	}//upload메소드 끝
	
}