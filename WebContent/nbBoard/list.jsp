<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String contextPath = request.getContextPath();
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--테일윈드-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.17/tailwind.min.css">
<!--폰트어썸-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet" type="text/css" href="../css/Team_FreeBoard.css">
</head>
<body>
<!--
function fnSearch(){
		var word = document.getElementById("word").value;
		if(word == null || word == ""){
			alert("검색어를 입력하세요.");
			document.getElementById("word").focus();
			return false;
		}
		else{
			document.frmSearch.submit();
		}
	}
	
	//글제목 하나를 클릭했을때 글 번호를 매개변수로 받아서 <form>로
	//글번호에 해당되는 글의 정보를 DB로부터 조회 요청을 BoardController로 합니다.
	function fnRead(val){
		document.frmRead.action = "<%=contextPath%>/FileBoard/read.bo";
		document.frmRead.b_idx.value = val;
		document.frmRead.submit();
	}



  -->


<%--
	
	
	//페이징 처리 변수
	
	int totalRecord = 0; //board테이블에 저장된 글의 총 개수
	int numPerPage = 5; //한 페이지당 조회해서 보여줄 글 개수
	int pagePerBlock = 3; //한 블럭당 묶여질 페이지 번호 개수 
						 // 1 2 3 < - 한블럭으로 묶음
	int totalPage = 0; //총 페이지수
	int totalBlock = 0; //총 블럭수
	int nowPage = 0; //현재 사용자에게 보여지고 있는 페이지가 위치한 번호 저장
	int nowBlock = 0; //클릭한 페이지 번호가 속한 블럭 위치 번호 저장
	int beginPerPage = 0; //각 페이지마다 보여지는 시작 글번호(맨위의 글번호) 저장
	
	//BoardController에서 재요청해서 전달한 request에 담긴 ArrayList배열 꺼내오기
	//조회된 글들
	ArrayList list = (ArrayList)request.getAttribute("list");
	
 	//조회된 총 글 갯수
	totalRecord = (Integer)request.getAttribute("count");
	
	//현재 페이지 번호를 클릭했다면?
	if( request.getAttribute("nowPage") != null ){
		nowPage = Integer.parseInt( request.getAttribute("nowPage").toString());
	}
	
	//각 페이지 번호에 보여지는 글 목록의 가장위의 글에 대한 글번호 구하기
	beginPerPage = nowPage * numPerPage;
	/*
		beginPerPage변수 설명
		예를 들어 한페이지당 보여질 글의 개수가 6개라고 가정할때...
		1번페이지 일 경우.. 1번페이지에 보여질 시작 글번호는 6이다.
		nowPage * numPerPage;
		1페이지 번호 * 한페이지당 보여지는 글의 개수6; = 시작글번호 6
		
		2페이지 일 경우.. 2번페이지에 보여질 시작 글번호는 12이다.
		nowPage * numPerPage;
		1페이지 번호 * 한페이지당 보여지는 글의 개수6; = 시작글번호 12
	*/
	
	//글이 몇개인지에 따른 총 페이지 번호 개수
	//총 페이지 번호 개수 = 총 글의 갯수 / 한페이지당 보여질 글 개수
	//참고! 하나의 글이 더 오버할 경우 마지막페이지에 하나의 글을 보여줘야 하기 때문에 올림 처리
	totalPage = (int)Math.ceil((double)totalRecord/numPerPage);
		
	//클릭한 페이지 번호가 속한 블럭 위치 번호 구하기
	if(request.getAttribute("nowBlock") != null){
		//BoardController로 부터 전달 받는다.
		nowBlock = Integer.parseInt(request.getAttribute("nowBlock").toString());
		
	}
	
	totalBlock = (int)Math.ceil((double)totalPage/pagePerBlock);
	
	String id = (String)session.getAttribute("id");
	
--%>	





    <!--요소 시작-->
    <div class="Contents-Box">
      <!--제목-->
      <form class="Form-Box Title-And-Del" action="#">
        <div class="Title">
          <span>공지사항</span>
        </div>
        <div class="Sel-And-Del-Btn">
        	<input type="text" >
          <input class="Del-Btn" type="submit" value="검색">
        </div>
      </form>
      <!--목록-->
      <div class="List-Box">
        <ul>
          <li>
            <span>목록</span>
          </li>
          <li>
            <span>ID</span>
          </li>
          <li>
          	<span>제목</span>
          </li>
          <li>
            <span>작성일</span>
          </li>
        </ul>
        <!--1-->
        <!--{{#each contents}}-->
         <a href="#" class="List-1 flex">
          <div>
            <span>01</span>	
          </div>
          <div>
            <span>020</span>
          </div>
          <div>
            <span>게시판 제목이 표출됩니다.</span>
          </div>
          <div>
            <span>2021.09.27 15 : 00</span>
          </div>
        </a> 

        <!--{{/each}}-->
      </div>
      
      
      <!--글쓰기 버튼-->
      <form class="Form-Box List-Move-Number" method="post" action="<%=contextPath%>/freeboard/write.fb">
        <div class="Number">
          <!--1-->
          <div class="mave-btn-1">
            <div class="page-text 1-page-text">
              <span id="li{{this}}">
                <!--{{this}}-->
<!--                 1 -->
              </span>
            </div>
          </div>
          <!--2-->
          <div class="mave-btn-2" >
            <div class="page-text 1-page-text">
              <span id="li{{this}}">
                <!--{{this}}-->
<!--                 2 -->
              </span>
            </div>
          </div>
          <!--3-->
          <div class="mave-btn-3" >
            <div class="page-text 1-page-text">
              <span id="li{{this}}">
                <!--{{this}}-->
<!--                 3 -->
              </span>
            </div>
          </div>
        </div>
        <!--작성하기 버튼-->
        <div class="Up-Btn">
        
   		  	<input type="hidden" name="gildong" value="hong">
          <input type="submit" value="글쓰기">
        </div>
      </form>
    </div>
 
 
 

</body>
</html>