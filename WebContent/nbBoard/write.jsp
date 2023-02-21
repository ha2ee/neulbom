<%@page import="VO.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
	String contextPath = request.getContextPath();
	String nowPage = request.getParameter("nowPage");
	String nowBlock = request.getParameter("nowBlock");
	MemberVo vo = (MemberVo)request.getAttribute("membervo");
	String email = vo.getEmail();
	String name = vo.getName();
	String nickname = vo.get
%>

<%
	String id = (String)session.getAttribute("id");
	if(id == null){//로그인 하지 않았을경우
%>		
	<script>	
		alert("로그인 하고 글을 작성하세요!"); 
		history.back(); 
 	</script>
<% 	}%>

<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover">
	<!-- 클래식 에디터 -->
	<script src="https://cdn.ckeditor.com/ckeditor5/29.1.0/classic/ckeditor.js"></script>
	<title>Home</title>
	
	
	
	<!-- 넓이 높이 조절 -->
	<style>
	#w_table{
 	 position: absolute;
 	 top:50%;
 	 left: 50%;
 	 transform: translate(-50% , -50%)
 	 
 	 }
	.ck.ck-editor {
    	max-width: 500px;
    	magin : auto;
	}
	.ck-editor__editable {
	    min-height: 300px;

	}
	</style>
</head>
<body>
	<div id="w_table">
	    <h3>게시판 내용을 적어주세요</h3>
	    <div id="classic">
	        <p>여기에 내용을 적으면 됩니다.</p>
	    </div>
    </div>
    <script>
        ClassicEditor
            .create( document.querySelector( '#classic' ))
            .catch( error => {
                console.error( error );
            } );
    </script>
</body>
</html>