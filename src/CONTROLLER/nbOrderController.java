package CONTROLLER;

import java.io.IOException;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.OrderDAO;
import VO.MemberVo;
import VO.PetVo;
import VO.TrainerVo;
import VO.eduOrderVo;

@WebServlet("/nbOrder/*")
public class nbOrderController extends HttpServlet{
	

	//OrderDAO객체를 저장할 참조변수 선언
	OrderDAO orderdao;
	
	//트레이너 정보를 조회할 TrainerVo를 호출
	TrainerVo trainervo;
	
	// 회원 , 펫 정보를 조회할 각 vo를 호출
	MemberVo membervo;
	PetVo petvo;
	
	@Override
	public void init() throws ServletException {
		orderdao = new OrderDAO();
		
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
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// nbOrderController INFO ////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// #1)      /petUpdate.od					<- 수강신청 페이지      			   반려견 정보 조회
		// #2) 		/eduOrder.od			 		<- 수강신청 페이지					 최종 결제 요청
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		//조건에 따라서 포워딩 또는 보여줄 VIEW주소 경로를 저장할 변수
		String nextPage = null;
		//요청한 중앙화면 뷰 주소를 저장할 변수
		String center = null;
		
		switch (action) {
		
		
			// #1) 수강신청 페이지에서 반려견 정보 가져오기 버튼을 눌렀을 때,
			case "/petUpdate.od":
				
			//petvo를 초기화 한다.
			PetVo petvo;
				
			System.out.println("nbOrderController -> petUpdate.od 호출 !");	

			// 1) 조회할 아이디 값을 가져온다.
			HttpSession session = request.getSession();
			String login_id = (String)session.getAttribute("id");
			
			System.out.println("login_id :" + login_id);
			
			// 2) 조회할 아이디를 저장 시킨다.
			request.setAttribute("id", login_id);
			
			// 2-1) petVo를 통해서 DB에 값이 있으면 가져오고 없으면 다른값을 반환시킨다.
			petvo = orderdao.checkPet(login_id);

			//리턴받은 petvo를 setAttribute에 담아서 전달한다.
			request.setAttribute("petvo", petvo);
			
			nextPage = "/nbShop/popup1.jsp";
			
			
				break;
		
		
		 	
			// #2) 수강신청 최종 결제 요청
			case "/eduOrder.od":
				
			System.out.println("nbOrderController -> eduOrder.od 호출 !");	
			
			// 1) 받아온 값들을 변수에 저장한다.
			String edu_id = request.getParameter("edu_id");
			String edu_name = request.getParameter("edu_name");
			String edu_hp = request.getParameter("edu_hp");
			String edu_email = request.getParameter("edu_email");
			String edu_address1 = request.getParameter("edu_address1");
			String edu_address2 = request.getParameter("edu_address2");
			String edu_address3 = request.getParameter("edu_address3");
			String edu_address4 = request.getParameter("edu_address4");
			String edu_address5 = request.getParameter("edu_address5");
			
			String pet_img = request.getParameter("pet_img");
			String pet_name = request.getParameter("pet_name");
			String pet_type = request.getParameter("pet_type");
			int	   pet_age = Integer.parseInt(request.getParameter("pet_age"));
			int	   pet_weight = Integer.parseInt(request.getParameter("pet_weight"));
			String pet_gender = request.getParameter("pet_gender");
			String pet_op = request.getParameter("pet_op");
			String tr_img = request.getParameter("tr_img");
			String tr_name = request.getParameter("tr_name");
			String tr_hp = request.getParameter("tr_hp");
			String date1 = request.getParameter("date1");
			String date2 = request.getParameter("date2");
			String date3 = request.getParameter("date3");
			String date4 = request.getParameter("date4");
			String date5 = request.getParameter("date5");
			String date6 = request.getParameter("date6");
			String date7 = request.getParameter("date7");
			int	   edu_cnt = Integer.parseInt(request.getParameter("edu_cnt"));
			String edu_totalprice = request.getParameter("edu_totalprice");
			
			
			// 2) 변수를 eduordervo로 저장시킨다.
			eduOrderVo eduordervo = new eduOrderVo();
			
			// *1) 회원 정보
			eduordervo.setEdu_id(edu_id);
			eduordervo.setEdu_name(edu_name);
			eduordervo.setEdu_hp(edu_hp);
			eduordervo.setEdu_email(edu_email);
			eduordervo.setEdu_address1(edu_address1);
			eduordervo.setEdu_address2(edu_address2);
			eduordervo.setEdu_address3(edu_address3);
			eduordervo.setEdu_address4(edu_address4);
			eduordervo.setEdu_address5(edu_address5);
			// *2) 펫 정보
			eduordervo.setPet_img(pet_img);
			eduordervo.setPet_name(pet_name);
			eduordervo.setPet_type(pet_type);
			eduordervo.setPet_age(pet_age);
			eduordervo.setPet_weight(pet_weight);
			eduordervo.setPet_gender(pet_gender);
			eduordervo.setPet_op(pet_op);
			// *3) 훈련사 정보
			eduordervo.setTr_img(tr_img);
			eduordervo.setTr_name(tr_name);
			eduordervo.setTr_hp(tr_hp);
			// *4) 예약 정보
			eduordervo.setDate1(date1);
			eduordervo.setDate2(date2);
			eduordervo.setDate3(date3);
			eduordervo.setDate4(date4);
			eduordervo.setDate5(date5);
			eduordervo.setDate6(date6);
			eduordervo.setDate7(date7);
			eduordervo.setEdu_cnt(edu_cnt);
			eduordervo.setEdu_totalprice(edu_totalprice);
			
			
			// 3) OrderDAO 객체의 insertEduOrder메소드 호출 시 저장한 값들을 전달한다.
			orderdao.insertEduOrder(eduordervo);
			
			PrintWriter pw1 = response.getWriter();
			pw1.print("<script>"+"alert('예약되었습니다.');"+"</script>");

			
			nextPage = "/nbMain.jsp";
			
			
				break;

			default:
				break;
				

		}
		
		
		//포워딩 (디스패처 방식)
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
	
}
