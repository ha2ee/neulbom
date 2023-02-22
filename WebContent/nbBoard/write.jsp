<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String contextPath = request.getContextPath();
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


<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>JS Bin</title>
  <style type="text/css">
  body { 
  margin : 0px ;
}
a{
  text-decoration : none ;
  color : black ;
}
a:hover{
  text-decoration : underline ;
}
ul,lu {
  list-style-type : none ;
  margin : 0px ;
  padding : 0px ;
}
5
#wrap {
  width : 860px ;
}
header {
  background: #eeeeee ;
  padding : 20px ;
}
header > img {
  float : left ;
  margin-right : 30px ;
  width : 120px ;
  height : 110px ;
}
header > h1 {
  padding : 0px ;
  margin : 0px ;
}
.clear {
  clear : both ;
}
.none {
  display : none ;
}

/*게시판*/
/*외곽잡기*/
#content_wrap {
  border : 1px solid red ;
  padding : 30px ;
  background : honeydew ;
  width : 100% ;
  font-size : 13px ;
}
#content_wrap ul {
  background : #ffffff ;
  border : 1px solid #dddddd ;
  padding : 10px ;
}
#content_wrap ul li{
  margin-bottom : 6px ;
}
#content_wrap ul li:nth-child(2){
  margin-bottom : 10px ;
}
#content_wrap .title {
  font-weight : bold ;
  display : inline-block ;
  width : 60px ;
}

/*내부잡기 shift+home+insert */
#content_wrap li:nth-child(1) div{
  display : inline-block ;
}
#content_wrap li:nth-child(1) .radiobox {
  font-size : 13px ;
  margin-left : 50px ;
}
/*쓰기영역 */
.writeWrap {
  padding : 10px ;
  margin : 0px ;
}

.writeWrap  .writeArea {
  width : 95% ;
  padding : 5% ;
  font-size : 12px ;
  color : #666666 ;
  
  min-height : 300px ;
}
.configWrap {
  border : none ;
}
.configWrap ul {
  margin : 0px ;
  background : #ffffff ;
  padding : 10px ;
}
.configWrap .tag .InputArea input{
  width: 500px ;
}
.end {
  border-top : 1px solid #dddddd ;
  background : white ;
  width : 100%;
  padding : 10px ;
  padding-top : 30px ;
}
.end a {
  font-size : 14px ;
  border : 1px solid gold ;
  background : khaki ;
  padding : 3px ;
  border-radius : 5px ;
}
.end a:hover {
  font-weight : bold ;
  text-decoration : none ;
}
  
  </style>
</head>
<body>
  <div id="wrap">
    <header>
      <img src="https://i.etsystatic.com/14912200/r/il/7c4834/1295206612/il_570xN.1295206612_nj87.jpg">  
      <h1>css활용강의</h1>
      <h2>게시판 글쓰기</h2>
    </header>
    <div id="content_wrap">
      <ul class="title_wrap">
       
        <li>
          <span class="title">제목</span>
          <input type="text" onfocus="value=''" value="게시글 제목을 입력하세요" style="width:500px"/>
        </li>
        <li>
          <span class="title" >파일 첨부</span>
          <input type="file" name="fileName" size="70">
        </li>
      </ul>
      <div class="writeWrap">
        <textarea class="writeArea" value=""></textarea>
      </div>
      
      
      <div class="end" align="center">
        <a href="<%=contextPath%>/freeboard/list.fb">저장하기</a>
        <a href="<%=contextPath%>/freeboard/list.fb">취소하기</a>
      </div>
    
    </div>
  </div>
  </body>
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  