<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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



<div class="All-box flex text-white">
  <div class="w-full">
    <!--요소 시작-->
    <div class="Contents-Box">
      <!--제목-->
      <form class="Form-Box Title-And-Del" action="#">
        <div class="Title">
          <span>공지사항</span>
        </div>
        <div class="Sel-And-Del-Btn">
        	<input type="text" >
          <input class="Del-Btn" type="submit" value="검색" onclick='this.form.action= "#"'>
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
      <!--숫자-->
      <form class="Form-Box List-Move-Number" method="post">
        <div class="Number">
          <!--1-->
          <div class="mave-btn-1" onclick='test({{this}})'>
            <div class="page-text 1-page-text">
              <span id="li{{this}}">
                <!--{{this}}-->
                1
              </span>
            </div>
          </div>
          <!--2-->
          <div class="mave-btn-2" onclick='test({{this}})'>
            <div class="page-text 1-page-text">
              <span id="li{{this}}">
                <!--{{this}}-->
                2
              </span>
            </div>
          </div>
          <!--3-->
          <div class="mave-btn-3" onclick='test({{this}})'>
            <div class="page-text 1-page-text">
              <span id="li{{this}}">
                <!--{{this}}-->
                3
              </span>
            </div>
          </div>
        </div>
        <!--작성하기 버튼-->
        <div class="Up-Btn">
          <input type="submit" value="글쓰기" name="submit" />
        </div>
      </form>
    </div>

  </div>
</div>



</body>
</html>