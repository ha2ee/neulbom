<%@page import="VO.test0000"%>
<%@page import="VO.FreeBoardVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   request.setCharacterEncoding("utf-8");
   String contextPath = request.getContextPath();
   System.out.println(contextPath);

   ArrayList list = (ArrayList)request.getAttribute("list");
   System.out.println(list);
   System.out.println("여기까지 합격");
   test0000 testvo = (test0000) request.getAttribute("testvo");
   int count = testvo.getCount();
   
/*    int count = Integer.parseInt(request.getParameter("count"));
   System.out.print("count값 : " + count);
//   int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	 int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//   System.out.print(pageSize);
   int currentPage = Integer.parseInt( request.getParameter("currentPage") );
   System.out.print(currentPage);
//   String count = request.getParameter("count");
   
   System.out.print("잘 오지...?");
 */ %>    

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

   
   //글제목 하나를 클릭했을때 글 번호를 매개변수로 받아서 <form>로
   //글번호에 해당되는 글의 정보를 DB로부터 조회 요청을 BoardController로 합니다.
   function fnRead(val){
      document.frmRead.action = "<%=contextPath%>/FileBoard/read.bo";
      document.frmRead.b_idx.value = val;
      document.frmRead.submit();
   }



  -->

<%--    <%
   //페이징처리
      int totalRecord = 0;
      int numPerPage = 5;   //한페이지에 보여줄 글 개수
      int pagePerBlock =5; //한 블럭당 묶여질 페이지 번호 개수
      int beginPerPage = 0;       //각 페이지 처음
      
      int totalPage = 0;   //총 페이지 수
      int totalBlock = 0;   //총 블락 수
      int nowPage = 1;   //현재 페이지
      int nowBlock = 0;   //현재 블럭
      
      
      
      totalRecord = count;
      beginPerPage = nowPage * numPerPage;
      totalPage = (int) Math.ceil( (double) totalRecord / numPerPage);
      totalBlock = (int) Math.ceil(  ((double) totalPage / pagePerBlock));
      
      
      
      
   %>

 --%>




    <!--요소 시작-->
    <div class="Contents-Box">
      <!--제목-->
<!--       <form class="Form-Box Title-And-Del" action="#"> -->
        <div class="Title">
          <span>자 유 게 시 판</span>
        </div>
        <form action="<%=contextPath%>/freeboard/searchlist.fb" 
							method="post" 
							name="frmSearch" onsubmit="javascript:fnSearch();" >
							
	            	<td colspan="2">
	            		<div align="right"> 
		            		<select name="key">
		              			<option value="titleContent">제목 + 내용</option>
		              			<option value="nickname">작성자</option>
		              		</select>
		              	</div>
	              	</td>
		            <td width="26%">
		            	<div align="center"> &nbsp;
		            		<input type="text" name="word" id="word"/>
		            		<input type="submit" id="go" value="검색하기">
		            	</div>
		            </td>
	            </form>
<!--       </form> -->
      <!--목록-->
      <div class="List-Box">
        <ul>
          <li>
            <span>글번호</span>
          </li>
          <li>
            <span>닉네임</span>
          </li>
          <li>
             <span>제목</span>
          </li>
          <li>
            <span>작성일</span>
          </li>
          <li>
            <span>조회수</span>
          </li>
          <li>
            <span>추천</span>
          </li>
        </ul>
        <!--1-->
        <!--{{#each contents}}-->
       
        <%
           for(int i = 0 ; i <list.size();i++){
              FreeBoardVo vo =(FreeBoardVo) list.get(i);
              
        %>
         <a href="javascript:fnRead('<%=vo.getB_idx()%>')" class="List-1 flex">
                <div style="width: 5%">
                  <span><%=vo.getB_idx()%></span>   
                </div>
                <div style="width: 15%">
                  <span><%=vo.getB_nickname() %></span>
                </div>
                <div style="width: 50%">
                  <span><%=vo.getB_title() %></span>
                </div>
                <!--타이틀 옆에 댓글 수 나오도록 작업 -->
                <div style="width: 15%">
                  <span><%=vo.getB_date() %></span>
                </div>
                <div style="width: 7.5%">
                  <span><%=vo.getB_cnt() %></span>
                </div>
                <div style="width: 7.5%">
                  <span><%=vo.getB_like() %></span>
                </div>
         </a>
        <%      
           }
        %>

      </div>
     
     
      <!--글쓰기 버튼-->
<%--       <form class="Form-Box List-Move-Number" method="post" action="<%=contextPath%>/freeboard/write.fb"> --%>
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
              <a href="<%=contextPath%>/freeboard/write.fb">글쓰기</a>
<!--           <input type="submit" value="글쓰기"> -->
        </div>
      </form>
    </div>
   
   
    <form name="frmRead">
       <input type="hidden" name="b_idx">
       <input type="hidden" name="my_id" value="inseop">
       
    </form>
   
   
   <% 
 	int pageSize = 5;

 	String pageNum = request.getParameter("pageNum");
	if(pageNum == null){
		pageNum = "1";
	}
	int currentPage = Integer.parseInt(pageNum);	
	int startRow = (currentPage - 1) * pageSize + 1;

	%>
   
   
   <div id= "page_control">
   		<%
   		
   		if(count != 0){
   			int pageCount = count / pageSize + (count % pageSize==0?0:1);
   			int pageBlock = 5;
   			
    		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
   			
   			int endPage = startPage + pageBlock-1;
   			if(endPage > pageCount){
   				endPage = pageCount; 
   				
   			}
   		
   		%>
   		
   		<%
   			if(startPage > pageBlock){ 
   		%>
   			<a href="<%=contextPath%>/freeboard/list.fb?pageNum = <%=startPage-pageBlock%>&startRow=<%=startRow%>&pageSize=<%=pageSize%>">Prev</a>
   		<%}%>
   		
   		<%
   			for(int i = startPage; i <= endPage;i++){
   		%>
   			<a href="<%=contextPath%>/freeboard/list.fb?pageNum=<%=i%>&startRow=<%=startRow%>&pageSize=<%=pageSize%>"><%=i%></a>
   		<%}%>
   		<%
   			if(endPage < pageCount){
   				System.out.println(startPage);
   		%>
   			<a href="<%=contextPath%>/freeboard/list.fb?pageNum =<%=startPage+pageBlock%>&startRow=<%=startRow%>&pageSize=<%=pageSize%>">Next</a>
   		<%}%>
   		<%}%>
   		
   
   </div>
   
   
   
      
 
 <script type="text/javascript">
 
    function fnRead(val){
       document.frmRead.action = "<%=contextPath%>/freeboard/read.fb";
       document.frmRead.b_idx.value = val;
       document.frmRead.submit();
    }
    
    function fnSearch(){
        var word = document.getElementById("word").value;
        if(word == null || word == ""){
           alert("검색어를 입력하세요.");
           document.getElementById("word").focus();
           return false;
        }
        else{
//         	var wordValue = document.getElementById("word").value;
<%--         	document.getElementById("go").setAttribute("href","<%=contextPath%>/freeboard/list.fb?word="); --%>
//         	alert(wordValue);	
           document.frmSearch.submit();
        }
     }
    
    
 
 </script>
 

</body>
</html>