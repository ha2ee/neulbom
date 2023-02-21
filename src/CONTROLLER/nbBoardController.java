package CONTROLLER;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.apache.commons.io.FileUtils;

import DAO.FileBoardDAO;
import DAO.MemberDAO;
import VO.FreeBoardVo;
import VO.FileBoardVo;
import VO.MemberVo;


//게시판 관련 기능 요청이 들어오면 호출되는 사장님(컨트롤러)
@WebServlet("/nb_board/*")
public class nbBoardController extends HttpServlet{
	
	//BoardDAO객체를 저장할 참조변수 선언
	FileBoardDAO fileboarddao;
	
	//MemberDAO객체를 저장할 참조변수 선언
	MemberDAO memberdao;
	
	//MemberVo객체를 저장할 참조변수 선언
	MemberVo membervo;
	
	private static final String ARTICLE_REPO = "C:\\file_repo";
	
	
	
	@Override
	public void init() throws ServletException {
		fileboarddao = new FileBoardDAO();
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
		PrintWriter out = null;
		//서블릿으로 요청한 주소를 request에서 얻기
		String action = request.getPathInfo();//2단계 요청 주소
		System.out.println("요청한  주소 : "+action);
		
		
		// 1) /free.bo          <-  "소통 하기"  -> " 자 유 게 시 판 " 요청
		// 2) /trainer.bo       <-  "소통 하기"  -> " 훈 련 사 상 담 " 요청
		// 3) /review.bo       <-  "소통 하기"   -> "  수  강  후  기 "요청
		// 4) /qna.bo       <-  "소통 하기"   -> "  qna "요청
		//////////////////////////////////////////////////////////////////////////////////////////////////
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
		FileBoardVo fileboardvo = null;
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
	         
	         request.setAttribute("center", "board/FileBoardwrite.jsp");
	         request.setAttribute("membervo", membervo);
	         
	         request.setAttribute("nowPage", request.getParameter("nowPage"));
	         request.setAttribute("nowBlock", request.getParameter("nowBlock"));
	         
	         nextPage = "/CarMain.jsp";
	         break;
	         
		      case "/writePro.bo":
		        
			
	         int articleNO = 0; //글번호 폴더를 생성하기 위해 글번호를 받아 저장할 변수
	         
	         //업로드 후 업로드한 파일명을 담고 있는 해쉬맵을 반환 받는다.
	         Map<String, String> articleMap = upload(request, response);
	         
	         //작성한 글 정보(업로드할 파일정보 포함)를 HashMap에서 꺼내오기
	         String writer = articleMap.get("writer"); //작성자
	         String email = articleMap.get("email"); //이메일
	         String title = articleMap.get("title"); //제목
	         String content = articleMap.get("content"); //내용
	         String pass = articleMap.get("pass"); //글 비밀번호
	         String id = articleMap.get("writer_id"); //글 작성자 아이디
	         String sfile = articleMap.get("filename"); //글을 작성할때 업로드 하기 위해 첨부한 파일명
	         
	         
	         //요청한 값을 FileBoardVo객체의 각 변수에 저장
	         fileboardvo = new FileBoardVo();
	         fileboardvo.setB_name(writer);
	         fileboardvo.setB_email(email);
	         fileboardvo.setB_title(title);
	         fileboardvo.setB_content(content);
	         fileboardvo.setB_pw(pass);
	         fileboardvo.setB_id(id);
	         fileboardvo.setsfile(sfile);
	         
	         //작성한 글 내용을 DB에 insert하고 추가 시킨 글의 글번호를 조회 후 반환 받습니다.
	         articleNO = fileboarddao.insertBoard(fileboardvo);
	         
	         if(sfile != null && sfile.length() != 0) {
	            File srcFile = new File(ARTICLE_REPO+"\\temp\\"+sfile);
	            File destDir = new File(ARTICLE_REPO+"\\"+articleNO);
	            
	            //DB에 추가한 글에 대한 글번호를 조회해서 가져왔기 때문에 글 번호 폴더 생성
	            destDir.mkdirs();
	            
	            //temp폴더에 업로드된 파일을 글번호폴더로 이동시키자
	            FileUtils.moveFileToDirectory(srcFile, destDir, true);
	         
	         }
	         out = response.getWriter();
	         out.print("<script>");
	         out.print(" alert( '글 추가 성공!' );");
	         out.print(" location.href='http://localhost:8090/CarProject/FileBoard/list.bo'");
	         out.print("</script>");
	         
	         return;


		
		//게시판 모든 글 조회 요청
		case "/list.bo":
			
			session = request.getSession();
			String loginid = (String)session.getAttribute("id");
			
			
			//요청한 값을 이용해 응답할 값 마련(글 조회)
			list = fileboarddao.boardListAll();
			count = fileboarddao.getTotalRecord();
			
			//list.jsp페이지의 페이징 처리 부분에서
			//이전 또는 다음 또는 각 페이지 번호를 클릭했을때.. 요청받는 값 얻기
			String nowPage = request.getParameter("nowPage");
			String nowBlock = request.getParameter("nowBlock");
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("id", loginid);
			request.setAttribute("nowPage", nowPage);
			request.setAttribute("nowBlock", nowBlock);
			
			request.setAttribute("center", "board/FileBoardlist.jsp");
			
			nextPage = "/CarMain.jsp";
			break;
		
		// 게시판에서 찾고자 하는 내용을 쓰고 검색했을때
		case "/searchlist.bo":
			
			//요청한 값 얻기 (검색을 위해 선택한 option의 값 하나, 입력한 검색어)
			key = request.getParameter("key");
			word = request.getParameter("word");
			
			//요청한 값을 이용해 응답할 값 마련(글 조회)
			list = fileboarddao.boardList(key,word);
			count = fileboarddao.getTotalRecord(key, word);
			
			request.setAttribute("center", "board/list.jsp");
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			
			nextPage = "/CarMain.jsp";
			break;	
		
		// 게시판에서 제목을 클릭해서 내용을 보려고 할때
		case "/read.bo":	
			
			
			//요청한 값 얻기
			String idx = request.getParameter("idx");
			nowPage = request.getParameter("nowPage");
			nowBlock = request.getParameter("nowBlock");
			
			// 요청한 값 출력해보기
			System.out.println(idx);
			System.out.println(nowPage);
			System.out.println(nowBlock);
			
			//글 번호 (b_idx)를 이용해 수정 또는 삭제를 위해 DB로 부터 조회하기
			fileboardvo = fileboarddao.boardRead(idx);
			
			// 중앙화면에 read.jsp로 전달하기 위해 setAttribute로 담음
			// 페이지번호, 페이지블럭번호, 글번호 3가지
			request.setAttribute("nowPage", nowPage);
			request.setAttribute("nowBlock", nowBlock);
			request.setAttribute("idx", idx);
			
			// 글번호로 조회한 정보도 받아와서 설정
			request.setAttribute("fileboardvo", fileboardvo);
			
			// 중앙화면에 요청할 주소를 read.jsp로 설정
			request.setAttribute("center", "board/FileBoardread.jsp");

			
			nextPage = "/CarMain.jsp";
			
			break;
			
			// 글을 수정하기 위해 입력한 비밀번호가 DB에 저장 되어있는지 요청
			case "/password.bo":
				
				// 글에대한 글번호와 입력한 비밀번호 얻기
				String idx_ = request.getParameter("idx");
				String password = request.getParameter("pass");
				
				// DB 작업
				boolean resultPass = fileboarddao.passCheck(idx_, password);
				
				// 얻어온 값 확인 출력
				System.out.println(resultPass);
				
				out = response.getWriter();
				
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
				String idx__ = request.getParameter("idx");
				email = request.getParameter("email");
				title = request.getParameter("title");
				content = request.getParameter("content");
				
				
				// DB 작업
				int result = fileboarddao.boardUpdate(idx__, email, title, content);
				
				// 얻어온 값 확인 출력
				System.out.println(result);
				
				out = response.getWriter();
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
			String delete_idx = 	request.getParameter("idx");
			
			// DELETE 작업을위해 boardao에서 idx 값 얻어오기
			String result__ = fileboarddao.deleteBoard(delete_idx);
			
			// 요청값 잘받아왔는지 출력해보기
			System.out.println(result__);
			
			out = response.getWriter();
			out.write(result__);
			
			return;
			
			
		// 답변버튼을 눌러서 띄워질 화면을 요청
			
		case "/reply.bo":
			

			//요청 값 얻기 (게시판 글번호)
			String b_idx__ = request.getParameter("idx");
			
			String reply_id = request.getParameter("id");
			
			String name = request.getParameter("name");
			
			MemberVo reply_vo = memberdao.memberOne(reply_id);
						
			// 글 번호 바인딩 시키기
			request.setAttribute("idx", b_idx__);
			
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
			String super_b_idx = request.getParameter("super_idx");
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
			fileboarddao.replyInsertBoard(super_b_idx, reply_id_, reply_name, reply_email, reply_title, reply_content, reply_pass);
			
			// 답변 추가 성공 후 다시 전체글을 조회하기 위한 요청주소를 nextPage에 저장
			
			nextPage = "/FileBoard/list.bo";
			
		case "/Download.bo": //다운로드 요청이 들어오면
	         
	         //다운로드할 폴더번호 경로와 다운로드할 파일명 얻기
	         idx = request.getParameter("path");
	         name = request.getParameter("filename");
	         
	         System.out.println(idx);
	         System.out.println(name);
	         
	         //다운로드할 파일이 저장되어 있는 경로를 만들어서 변수에 저장
	         String filePath = ARTICLE_REPO + "\\" + idx;
	         
	         File f = new File(filePath + "\\" + name);
	         
	         OutputStream outputStream = response.getOutputStream();
	         
	         /*응답 헤더를 통한 캐시제어*/
	         response.setHeader("Cache-Control", "no-cache");
	         response.addHeader("Cache-Control", "no-store");
	         
	         response.setHeader("Content-Disposition", "attachment; fileName=\""+URLEncoder.encode(name,"utf-8")+"\";");
	         
	         //다운로드할 파일과 연결된 입력스트림 통로 얻기
	         //1바이트 단위씩 읽어들일 통로
	         FileInputStream fileInputStream = new FileInputStream(f);
	         
	         byte[] buffer = new byte[1024 * 8];
	         
	         while(true) {
	        	 
	        	 count = fileInputStream.read(buffer);
	        	 
	        	 if(count == 1) {
	        		 break;
	        	 }
	        	 
	        	 outputStream.write(buffer, 0, count);
	        	 
	        	 
	         }
	         
	         //자원해제
	         fileInputStream.close();
	         outputStream.close();
			
		default:
			break;
		}
		
		
		//포워딩 (디스패처 방식)
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
	
	// 파일을 웹에플리케이션 서버의 하드 디스크 공간에 업로드 하는 기능의 메소드
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		  Map<String, String> articleMap = new HashMap<String, String>();
		
		  //한글처리
	      request.setCharacterEncoding("utf-8");
	      
	      //인코딩 방식 UTF-8 문자열을 변수에 저장
	      String encoding = "UTF-8";
	      
	      //업로드할 파일 폴더 경로와 연결된 File객체 생성
	      File currentDirPath = new File(ARTICLE_REPO);
	      
	      //업로드 할 파일 데이터를 임시로 저장할 객체 메모리 생성
	      DiskFileItemFactory factory = new DiskFileItemFactory();
	      //임시 메모리의 최대 사이즈를 1메가 바이트로 설정
	      factory.setSizeThreshold(1024*1024*1);
	      //임시 메모리에 파일업로드시~ 지정한 1메가 바이트크기를 넘길경우
	      //실제 업로드될 파일 폴더 경로를 설정
	      factory.setRepository(currentDirPath);
	      
	      /*
	         참고. DiskFileItemFactory 클래스는
	            업로드 파일의 크기가 지정한 임시메모리의 크기를 넘기기전까지는
	            업로드 한 파일 데이터를 임시메모리에 저장하고
	            지정한 임시메모리 크기를 넘길 경우 최종 업로드할 폴더에 업로드하여 저장시킨다.
	      */
	      
	      //파일 업로드할 메모리를 생성자 쪽으로 전달받아 저장하여 생성되는 파일업로드 기능을 처리할 객체생성
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      
	      try {
	         
	         //uploadForm.jsp에서 업로드요청할 파일의 정보,
	         //입력한 문자열 정보들이 저장된 request객체 메모리를
	         //ServletFileUpload객체의 parseRequest메소드 호출시
	         //매개변수로 전달 하면 request객체 메모리에 저장된
	         //요청하는 아이템들을 파싱(추출)해서 DiskFileItem객체에 각각 저장한 후 
	         //DiskFileItem객체들을 ArrayList배열에 추가합니다.
	         //그 후 ArrayList배열 자체를 반환 받습니다.
	         List items = upload.parseRequest(request);
	         
	         //ArrayList가변 길이 배열의 크기만큼(DiskFileItem객체의 갯수만큼) 반복
	         for(int i=0; i<items.size(); i++ ) {
	            
	            //ArrayList가변 배열에서.. DiskFileItem객체를 얻는다.
	            FileItem fileItem = (FileItem)items.get(i);
	            
	            //DiskFileItem객체(요청한 아이템 하나의 정보)가 파일 아이템이 아닐경우
	            if(fileItem.isFormField()) {
	               System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
	               
	               //해쉬맵에 업로드할 파일명과 받아온 value값을 저장
	               articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
	               
	            }else {//DiskFileItem객체(요청한 아이템 하나의 정보)가 파일일 경우
	               System.out.println("파라미터: "+fileItem.getFieldName());
	               System.out.println("파일명: "+fileItem.getName());
	               System.out.println("파일크기: "+fileItem.getSize() + "bytes");
	            
	               //업로드할 파일의 크기가 0보다 크다면?(업로드할 파일이 있다면?)
	               if(fileItem.getSize() > 0) {
	                  
	                  //업로드할 파일명을 얻어 파일명의 뒤에서부터 \\문자열이 포함되어 있는지
	                  //index위치번호를 알려주는데 없으면 -1을 반환함
	                  int idx = fileItem.getName().lastIndexOf("\\");
	                  
	                  System.out.println(idx);
	                  
	                  if(idx == -1) {//업로드할 파일명에 \\문자열이 포함되어 있지 않으면?
	                     
	                     idx = fileItem.getName().lastIndexOf("/");
	                     
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
	         
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
		
		return articleMap;
	}
	
	
}
