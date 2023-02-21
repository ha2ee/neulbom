<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<style>

	* {
	  margin: 0;
	  padding: 0;
	}
	
	li {
	  list-style: none;
	}
	
	.slideContainer {
	  width: 100%;
	  position: relative;
	}
	
	/* -아래 pageBt abosolute로 위치를 잡기 위하여 부모에 position: relative 필요 */
	.slideWrap {
	  width: calc(100%*6);
	  overflow: hidden;
	  margin-left: calc(-100%*1);
	  position: relative;
	  z-index: 1;
	  height: 480px; <%-- 이미지 사이즈 --%>
	}
	
	/* 총600장을 나열하기위하여 calc(100%*6) 또는 600%, 맨처음 1번째 이미지 가기위하여 margin-left: calc(-100%*0) */
	.slide {
	  float: left;
	}
	
	.slide img {
		width: 100%;
		height: 480px;
		}
	
	.pageBtWrap {
	  z-index: 2;
	  position: absolute;
	  left: 0;
	  bottom: 7%;
	  width: 100%;
	  text-align: center;
	}
	
	.pageBtWrap li {
	  display: inline;
	}
	
	/* 부모요소를 text-align: center, li를 inline요소로 바꿔 중간 정렬, float: left 사용할 필요없음 */
	.pageBtWrap li .pageBt {
	  display: inline-block;
	  width: 12px;
	  height: 12px;
	  border-radius: 50%;
	  margin-left: 10px;
	  background-color: #fff;
	}
	
	.pageBtWrap li .pageBt.addPageBt {
	  background-color: #ff0;
	}
	</style>
<title>Center.jsp</title>
</head>
<body>

test
<!-- 3) Center 영역 -->
    <!-- slider-start -->
    
 <div class="slideContainer">
  <ul class="slideWrap">
    <li class="slide slide4"><img src="<%=contextPath%>/images/slider1.jpg"></li>
    <li class="slide slide1"><img src="<%=contextPath%>/images/slider2.jpg"></li>
    <li class="slide slide2"><img src="<%=contextPath%>/images/slider3.jpg"></li>
    <li class="slide slide3"><img src="<%=contextPath%>/images/slider4.jpg"></li>
    <li class="slide slide4"><img src="<%=contextPath%>/images/slider1.jpg"></li>
    <li class="slide slide1"><img src="<%=contextPath%>/images/slider2.jpg"></li>
  </ul>
  <ul class="pageBtWrap">
    <li><a href="#" class="pageBt addPageBt"></a></li>
    <li><a href="#" class="pageBt"></a></li>
    <li><a href="#" class="pageBt"></a></li>
    <li><a href="#" class="pageBt"></a></li>
  </ul>
</div>


    <!-- slider-close -->

	
</body>
</html>