<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
          <span class="title">게시판</span>
          <div class="board_select">
            <select value="게시판선택">
              <option selected disabled>게시판선택</option>
              <option value="ml">Deep Learning</option>
              <option value="board">자유게시판</option>
            </select>
          </div>
          <div class="radiobox">
            <!--
            <input type="checkBox" id="chkNotice1" name="chkB1">
            <label for="chkNotice1">공지사항으로 게시글쓰기</label>
            <input type="checkBox" id="chkNotice2" name="chkB2">
            <label for="chkNotice2">일반글로 게시글쓰기</label>
            -->
            <input type="radio" name="write" id="write_O">
            <label for="write_O">공지사항으로 게시글쓰기</label>
            <input type="radio" name="write" id="write_G">
            <label for="write_G">일반글로 게시글쓰기</label>
          </div>
        </li>
        <li>
          <span class="title">제목</span>
          <input type="text" onfocus="value=''" value="게시글 제목을 입력하세요" style="width:500px"/>
        </li>
        <li>
          <span class="title" style="font-size:10px;">동영상삽입</span>
        </li>
      </ul>
      <div class="writeWrap">
        <textarea class="writeArea" value=""></textarea>
      </div>
      <div class="configWrap">
        <ul>
          <li class="tag">
            <span class="title">태그달기</span>
            <div class="InputArea">
              <input type="text" id="tagInput" nae="tagInput" onfocus="value=''" value="태그와 태그는 쉼표로 구분하세요">
            </div>
          </li>
          <li>
            <span class="title">공개설정</span>
          </li>
          <li>
            <span class="title">검색설정</span>
          </li>
        </ul>
      </div>
      <div class="end">
        <a href="#">저장하기</a>
        <a href="#">취소하기</a>
      </div>
    
    </div>
  </div>
  </body>
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  