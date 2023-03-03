<%@page import="VO.LikeVo"%>
<%@page import="java.util.Date"%>
<%@page import="VO.FreeBoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
<%
	request.setCharacterEncoding("UTF-8");
	String contextPath = request.getContextPath();
	FreeBoardVo vo = (FreeBoardVo) request.getAttribute("vo");
	//LikeVo vo2 =  (LikeVo)request.getAttribute("vo2");
	String title = vo.getB_title();
	String content = vo.getB_content();
	String nickname = vo.getB_nickname();
	Date date = vo.getB_date();
	int like = vo.getB_like();
	int cnt = vo.getB_cnt();
	int idx = vo.getB_idx();
	
	String id2 = (String) request.getAttribute("id2");
	
	
	
	
	
	System.out.println("id2 : " + id2);
	System.out.println("vo : " + vo);
//	System.out.println("vo2 : " + vo2);
	System.out.println("title : " + title);
	System.out.println("content : " + content);
	System.out.println("nickname : " + nickname);
	System.out.println("date : " + date);
	System.out.println("like : " + like);
	System.out.println("cnt : " + cnt);
	System.out.println("idx : " + idx);
	
	
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/d158a8723c.js" crossorigin="anonymous"></script>
</head>
<body>
	
	<div style="margin: 5% 5% 0 5%; ">
	<!-- 전체 width 바꾸기 위해서 존재하는  -->
	
	
		<div style="border: 1px solid black;"> 
			<div style="border: 1px solid black; margin: 3px;">
				<b style="font-size: 20px;"><%=title%></b> <br>
			</div>
			<div style="border: 1px solid black; text-align: center; height: 30px;" >
				<div style="border: 1px solid black; float: left;  margin: 3px;">
					<%=nickname %>     l 
				</div>
				<div style="border: 1px solid black; float: left;  margin: 3px;">
					<%=date %>
				</div>
				
				<div style="border: 1px solid black; float: right;  margin: 3px;">
					댓글
				</div>
				<div style="border: 1px solid black; float: right;  margin: 3px;">
					추천 <%=like %>
				</div>
				<div style="border: 1px solid black; float: right;  margin: 3px;">
					조회수 <%=cnt %>
				</div>
			</div>
			
		</div>
		<hr style="border: solid 1px grey;">
		
		<!-- 윗부분 끝 -->
		<div style="border: 1px solid black; height:300px; margin-bottom:2% ">
			<!-- 콘텐츠 들어온다. -->
			
			<%=content%>
		
			
			
	<!-- 	if 첨부 파일 있으면 위에 첨부하고
			없으면 내용만 삽입.
	 -->		
		
		</div>
		<!-- 중간부분 끝 -->
		
		
		<%
		if(id2 == null){
		%>
			<img src="../images/좋아요.png" id="likeimg" width="60px" height="60px" class="rounded-circle mt-2">
			<%=like%> <br><br> 추천 기능은 <a href="/member/login" type="button" id="newLogin" class="btn btn-outline-success">로그인</a> 후 사용 가능합니다.
		<%			
		} else{
		%>
			<div>
			<!-- 눌렀을 때 likeboard안에 likecheck가 되어있는지 아닌지 확인  -->
  				<input type="hidden" id="like_check" <%-- value="<%=vo2.getLike_check()%> --%>" > 
<!-- 				<input type="button" id = "likeimgg">
					<img class="rounded-circle likeimg" id="likeimg" src="../images/좋아요.png"
					width="60px" height="60px"> 
				</input>	
 -->					<a href="" id="likeimgg">
 		<%
 							if()
 		%>						
 							<i class="fa-regular fa-heart fa-2x"  id="likeimggg" ></i>
						</a>
				<p id="countLike"><%=like%></p>
				
			</div>
		<% 	
		}
		%>
		
		<script type="text/javascript">
<%-- 		$(document).ready(function () {
			
		let like_count = <%=like%>;
		let likeval = document.getElementById('like_check').value;	//0이면 like X 1이면 like O
		const b_idx = <%=idx%>
		const sessionId = <%=sessionId%>
		const likeimg = document.getElementById("likeimg")
	
		if (likeval > 0) {
			likeimg.src = "/resources/img/좋아요이후.png";
		}
		else {
			likeimg.src = "/resources/img/좋아요.png";
		}
	    // 좋아요 버튼을 클릭 시 실행되는 코드

	    
 --%>	    
	    $("#likeimgg").on("click", function (event) {
	    	event.preventDefault();
			let pLike = $("#countLike");
	    	let like_count = <%=like%>;
/*  			let likeval = document.getElementById('like_check').value;	//0이면 like X 1이면 like O
 */ 			let b_idx = <%=idx%>;
 			let id2 = "<%=id2%>";
 			let likeimg = document.getElementById("likeimgg");
 			console.log(like_count);
 			
	/* 		console.log(likeval);
			console.log(b_idx);
			console.log(id2);
			console.log(likeimg); */
	 		
	    	
 			$.ajax({
		      url: "<%=contextPath%>/freeboard/like.fb",
		      async : true,
		      type: 'POST',
		      dataType : "text",
		      data: { 
		    	  	  b_idx : b_idx,
			   	  	  id2 : id2
		    	  	},
		      success: function (data) {
		    	  
		    	  console.log(data);
 		          if (data == 1) {
		              $("#likeimggg").attr("class", "fa-solid fa-heart fa-2x");
		              pLike.text(like_count+1);
		          } else if(data == 2){
		        	  $("#likeimggg").attr("class","fa-regular fa-heart fa-2x")
		          }
		      }/* , error: function () {
		          $("#likeimg").attr("src", "../images/좋아요이후.png");
		          console.log('오타 찾으세요')
		      } */
		
 		  });
 		
		  });
		 /*  }); */
		</script>
		
		
		
		
		
		
		
		
		
		
		
		<div style="border: 1px solid black; height:100px; margin-bottom:2% ">
			댓글 들어온다.
		</div>
		
		<div style="border: 1px solid black; height:300px; margin-bottom:2% ">
		 게시글 목록 보여주고 싶은데
		</div>
		

	</div>

</body>
</html>