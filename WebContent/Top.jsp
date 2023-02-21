<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>

//메인 메뉴에 마우스가 올려지면 토글하는 함수 생성

$(document).ready(function(){
	$(".list_gnb li").on("mouseover", function(){
		$("#mega-menu").stop().slideDown("fast");
	})
	
	$("#mega-menu").on("mouseleave", function(){
		$("#mega-menu").stop().slideUp("slow");
	})

});


//	함수로 캐러셀 동작 시키기
$(document).ready(function() {
	    $("#news-slider1").owlCarousel({
	        items:3,
	        itemsDesktop:[1199,3],
	        itemsDesktopSmall:[1000,2],
	        itemsMobile : [650,1],
	        navigationText:false,
	        autoPlay:true
	    });
		});


</script>
<style>
/*  나눔 고딕 글꼴 임포트 */
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap');
/* 나눔 고딕 CSS 설정 */

* {
	font-family: 'Nanum Gothic', sans-serif;
}



/* 중앙정렬을 위한 테이블 CSS 설정 */


/*  header 영역이 항상 TOP쪽에 표시될 수 있도록 CSS 추가  */

  
.nb_top {
  position : fixed;
  width : 100%;
  height : 100px;
  margin : 0 auto 0 auto; 
  align-content : center;
  background-color : white;
  text-align: center;
  z-index: 3;
}



/* 커스텀 로고 영역 */

@import url(https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css);
@import url(https://fonts.googleapis.com/css?family=Lato:400,300,900);

body{
	padding-top : 250px;
}

#center{
	z-index: 2;
}

#header, #middle, #footer {
	width : 1200px;
	margin : 0 auto 0 auto;
	text-align: center;
}

#header {

	position : fixed;
	left : 0;
	right : 0;
	top : 0;
	height : 250px;
	background-color : white;
	z-index: 3;
	
}

#middle {
	position : relative;
	display: contents;
	text-align : center;
	z-index: 2;
	
}

#footer {
	top : 500px;
	height: 500px;
	display : contents;
	text-align: center;
	position : relative;
	height : auto;
	z-index: 1;
}

/* Slider */
#textSlider.row {

	position: relative;
	left : 0;
	right : 0;
	top : 0;
	width:100%;
	height:100%;
}

#textSlider div {
	
	right : 0;
	position : relative;
	width : 100%;
	color: black;
	text-transform: uppercase;
}


.scroller {
/* 	기본값 70pt */
   height: 70pt;
/*    기본값 70pt */
/*    line-height: 70pt; */
/*    width : 200px; */
   overflow: hidden;
}

/* scroller 안에 .inner 클래스 안에 p 태그 지정 해서 line-height: 1.0줌 */

.scroller .inner p{
		padding-top : 7px;
		margin : 0 auto 0 auto;
		line-height: 1;
		width : 100%;
}

.scroller .inner { 
	
	font-size : 65pt;
	animation: 10s normal infinite running scroll; }
	
@keyframes scroll {
   0%  	{margin-top: 0px;}
   15% 	{margin-top: 0px;}

   25%  {margin-top: -70pt;}
   40%  {margin-top: -70pt;}
   
   50%  {margin-top: -140pt;}
   65%  {margin-top: -140pt;}

   75%  {margin-top: -210pt;}
   90%  {margin-top: -210pt;}
   
   100% {margin-top: 0px;}
}

/* 로고 에 대한 CSS 설정 */
#menu1 {

/* 	border : 1px solid black; */
	
	left : 0;
	right : 0;
	margin : 0 auto 0 auto;
	position : fixed;
	height : 100px;
	width : 100%;
	text-align : center;
	background-color: white;
}


/*  @ 네브바 + 날씨 API CSS 설정 */
#menu2 {

/* 	border : 1px solid black; */
	
	position : fixed;
	top : 100px;
	background-color: white;
	width : 100%;
	height : 150px;
	align-items : center;
	list-style : none;
	left : 0;
	right : 0;
}


/* @ 네브바에 대한 CSS 설정 */
#main_menu {


/* 	border : 1px solid black; */
	
	left:0;
	right:0;
	top : 100px;
	position: fixed;
	height: 150px;
	width : 100%;
}

/* 로그인에 대한 CSS 설정 */

#nb_login {
	position : relative;
	top : 0;
	left : 1292px;
	right : 0;
	height: 100px;
	width : 250px;
}

#login_box {
	text-align: center;
	height : 100px;
	width : 100%;	
	position: absolute;
	bottom : 85px;
}

#login_box > li {
	
	float : left;
	left : 25px;
	position: relative;
	top : 10px;
	text-align: center;
	margin : 0 10px;
	padding : 3px;

}

	
/* 	1) "메뉴"에 대한 CSS 설정 */
.list_gnb {

	position : fixed;
	margin : 0;
	padding : 0;
	list-style: none;
	float : left;
	width : 100%;
	height : 150px;
	top : 100px
}

/*  1-1) 메뉴의 정렬에 대한 CSS 설정 */
.list_gnb>li{

/* 	border : 1px solid red; */
	
/* 	margin : 0 0 0 -250px; */
	position : fixed;
	top : 100px;
	left : 360px;
	right : 360px;
	height : 150px;
}

/* 1-2) 메뉴 버튼이 활성화 되었을때, CSS 디자인 */


/* 1-3) 버튼 하나하나에 대한 CSS 디자인 */
.list_gnb>li>a {
	
	top : 65px;
	position : relative;
 	font-weight : 400;
 	color : black; 
	text-align: center;
	left : -150px;
	padding : 15px;
	font-size : 37px;
	color : black;
	text-decoration: none;
	left : -130px;
	margin : 15px;
}

/* 1-4) 버튼 하나하나가 활성화 되었을 CSS 디자인 */
 .list_gnb>li>a:hover { 
/*  	border-bottom: 3px solid #ffebcd;  */
	border : silver;
 	background-color: #ffebcd;
 	border-radius: 30px;
 } 

/* 1-5) 나열된 메뉴가 활성화 되었을 때 메가메뉴를 어떻게 컨트롤 할건지에 대한 CSS 디자인 */
/* .list_gnb > li:hover #mega-menu { */
/* 	opacity: 1; */
	
/* } */

/* 드롭  된 큰 메뉴에 대한 스타일 조정 */
#mega-menu {

 	font-weight : 900;
	width : 100%;
	height : 170px;
	background: white;
	position : fixed;
	top : 249px;
	left : 0;
	right : 0;
	border-radius: 3px;
	display : none;
}

/* 드롭  된 각 메뉴에 대한 스타일 조정 */
#mega-menu > ul {
	text-align : center;
	color : black;
	font-size : 16px;
	float : left;
}

/* 드롭 된 메뉴 전체의 스타일 조정 */
#mega-menu > ul >li{
	padding : 8px;
	text-align : center;
}
#mega-menu > ul >li:hover{
	border-radius :20px;
	background-color : #ffebcd;
	text-align : center;
}

/* 드롭 된 메뉴의 간격 조정 */
.list_lnb_01 {
 	margin-left : 490px;  
 		width: 130px;
}
.list_lnb_02 {
 	margin-left : 90px; 
 	width : 150px;
}
.list_lnb_03 {
 	margin-left : 80px; 
 		width: 130px;
}
.list_lnb_04 {
 	margin-left : 65px; 
 		width: 130px;
}

/* 드롭 된 메뉴의 스타일 조정 */
#mega-menu > ul >li > a{
	font-size : 17px;
 	font-weight : inherit;
	text-align : center;
	color : black;
	text-decoration: none;
}

/* 드롭다운 버튼 구현을 위한 style 임포트 끝*/


 .nb_top > .login1 > ul{
	left: 940;
	position :  relative;
	height: 100px;
	width: 200px;
	left :  940px;
	list-style: none;
}

 .nb_top >  .login1 > ul > li > a {
	font-family: 'Nanum Gothic', sans-serif;
	font-size : 15px;
	text-transform: uppercase;
	font-weight : 700;
	padding: 7px 21px;
	margin-bottom : 4px;
	letter-spacing: .75px;
	border-radius : 3px;
	border : transparent;
	transition : all 0.3s;
	position : relative;
	display: inline-block;
	background : #fff;
}

 .nb_top > .login1 > ul > li > a:hover {
	border-radius : 20px;
	background-color : #ffebcd;
}

   #weather1 {
	
/* 	border : 1px solid black; */
	
	position : relative;
	text-align : center;
	height : 150px;
	width : 250px;
	left : 466px;
}

.inner p {
  background-color :white;
}

/* header 영역 고정 완료 */

/*  로그인,회원가입,마이페이지,장바구니 버튼 백그라운드 컬러변경 시 사용 */
.btn:hover {
  background: #ffebcd;
  text-decoration: none;
  border-radius: 20px;
}
.btn:focus {
  outline: none;
}

.btn {
	font-family: 'Nanum Gothic', sans-serif;
	font-size : 15px;
	text-transform: uppercase;
	font-weight : 700;
	padding: 7px 21px;
	margin-bottom : 4px;
	letter-spacing: .75px;
	border-radius : 3px;
	border : transparent;
	transition : all 0.3s;
	position : relative;
	display: inline-block;
	background : #fff;
}

</style>


<!--          			# 부트스트랩 플러그인  관련 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Digital marketing courses website template that can help you boost your business courses website and its completely free.">
    <meta name="keywords" content="seo,free website template, free responsive website template, seo marketing, search engine optimization, web analytics">
    <!-- Bootstrap -->
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <!-- Style CSS -->
    <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
    <!-- 구글 폰트 -->
<!--     <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet"> -->
<!--     FontAwesome CSS -->
<%--     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/fontello.css"> --%>
<%--     <link href="<%=request.getContextPath()%>/css/font-awesome.min.css" rel="stylesheet"> --%>
    <link href="<%=request.getContextPath()%>/css/owl.carousel.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/owl.theme.default.css" rel="stylesheet">

<!--          			# 부트스트랩 플러그인  관련 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!--          			# 홈버튼 플러그인  관련 -->
<i class="bi bi-house"></i>
</svg>

<title>Top.jsp</title>

<!--          			# 플러그인  관련 -->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=request.getContextPath()%>/js/jquery.min.js" type="text/javascript"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/menumaker.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.sticky.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/sticky-header.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/owl.carousel.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/slider.js"></script>
</head>    
<body>    
 <div class="nb_top" >
<!--          			# 메인 로고  관련 -->
<!--  #   menu1 :  슬라이더 + 로그인 버튼 4개  -->
    <div id = "menu1" >
		<div id = "textSlider" class="row">
					<div class="scroller">
							<div class="inner">
								<p align="center">늘 봄</p>
								<p align="center">いつも春</p>
								<p align="center">Always See</p>
								<p align="center">Always Spring</p>
							</div>
					</div>
		</div>
		<!--                  1) 로그인 & 로그아웃 & 마이페이지 & 장바구니 버튼 -->
		<div id = "nb_login">
					<ul id = "login_box">	                    			
		               <li><a href="<%=request.getContextPath()%>/nb/login.member?center=/nbMember/login.jsp" class="btn">로그인</a></li>
		               <li><a href="<%=request.getContextPath()%>/nb/mypage.member?center=/nbMember/mypage.jsp" class="btn">마이페이지</a></li>
		               <li><a href="<%=request.getContextPath()%>/nb/join.member?center=/nbMember/join.jsp" class="btn">회원가입</a></li>
		               <li><a href="<%=request.getContextPath()%>/nb/cart.member?center=/nbMember/cart.jsp" class="btn">장바구니</a></li>
					</ul>
			</div>	
	</div>
	
<!--  #   menu2 :  네브바 + 날씨 API  -->		

	<div id="menu2" align="center" >
		<nav id="main_menu">
			<ul class="list_gnb">
				<li style="font-family: 'Nanum Gothic', sans-serif;">
				<!-- 			 1-1)	홈 버튼 -->
				<a id ="homebtn" href="<%=request.getContextPath()%>/nb/Main" title="">
				<svg id="nb_homebtn" xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-houses" viewBox="0 0 16 16">
 				<path d="M5.793 1a1 1 0 0 1 1.414 0l.647.646a.5.5 0 1 1-.708.708L6.5 1.707 2 6.207V12.5a.5.5 0 0 0 .5.5.5.5 0 0 1 0 1A1.5 1.5 0 0 1 1 12.5V7.207l-.146.147a.5.5 0 0 1-.708-.708L5.793 1Zm3 1a1 1 0 0 1 1.414 0L12 3.793V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v3.293l1.854 1.853a.5.5 0 0 1-.708.708L15 8.207V13.5a1.5 1.5 0 0 1-1.5 1.5h-8A1.5 1.5 0 0 1 4 13.5V8.207l-.146.147a.5.5 0 1 1-.708-.708L8.793 2Zm.707.707L5 7.207V13.5a.5.5 0 0 0 .5.5h8a.5.5 0 0 0 .5-.5V7.207l-4.5-4.5Z"/>
				</svg></a>
				<!-- 			 1-2)	메뉴 버튼 -->
				<a href="<%=request.getContextPath()%>/nb/Intro.do?center=/nbCompany/intro.jsp"> 늘 봄이란?</a>
				<a href="<%=request.getContextPath()%>/nb/edu.do?center=/nbShop/trainer.jsp"> 수강 신청 </a>
				<a href="<%=request.getContextPath()%>/nb/free.bo?center=/nbBoard/freeboard.jsp"> 소통 하기</a>
				<a href="<%=request.getContextPath()%>/nb/pet.shop?center=/nbShop/pet.jsp"> 늘 봄샵</a>



			  </li>
			</ul>
		</nav>
		<!-- 					2) 날씨 구현 파트 -->
		<div id = "weather1">
			                    			
				<br><br><br>날씨 API 구현 예정
		</div>	 
	  </div>

<!--  #   mega-menu :  메가드롭 메뉴  -->
	<div id="mega-menu">
			<ul class="list_lnb_01">
				<li><a href="<%=request.getContextPath()%>/nb/Intro.do?center=/nbCompany/intro.jsp">회사 소개</a></li>
				<li><a href="<%=request.getContextPath()%>/nb/ci.do?center=/nbCompany/ci.jsp"> C I</a></li>
				<li><a href="<%=request.getContextPath()%>/nb/navi.do?center=/nbCompany/navi.jsp">오시는 길</a></li>
			</ul>
			<ul class="list_lnb_02">
				<li><a href="<%=request.getContextPath()%>/nb/edu.do?center=/nbShop/trainer.jsp">수강신청 가기</a></li>
			</ul>
			<ul class="list_lnb_03">
				<li><a href="<%=request.getContextPath()%>/freeboard/list.fb">자유 게시판</a></li>
				<li><a href="<%=request.getContextPath()%>/nb/trainer.bo?center=/nbBoard/trainerboard.jsp">훈련사 상담</a></li>
				<li><a href="<%=request.getContextPath()%>/nb/review.bo?center=/nbBoard/review.jsp">수강 후기</a></li>
				<li><a href="<%=request.getContextPath()%>/nb/qna.bo?center=/nbBoard/qna.jsp">Q&A</a></li>
			</ul>
			<ul class="list_lnb_04">
				<li><a href="<%=request.getContextPath()%>/nb/pet.shop?center=/nbShop/pet.jsp"> 샵으로 이동 </a></li>
		 </ul>
	</div>	
         
 </div>
<!-- header-close -->
<div id = "center1">

</div>
</body>
</html>