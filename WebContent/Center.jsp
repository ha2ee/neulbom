<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	request.setCharacterEncoding("utf-8");
	String contextPath = request.getContextPath();
%>

<title>Slider</title>
<!-- 3 Bootstrap core CSS --> 
<link href="css/bootstrap.min.css" rel="stylesheet">
<head>

 <style>
	.carousel-inner>.item>a>img, .carousel-inner>.item>img, .img-responsive, .thumbnail a>img, .thumbnail>img {
    display: block;
    max-width: 100%;
    height: 500px;
    }
    .carousel-indicators {
    position: relative;
    top: 480px;
    bottom: 480px;
    left: 55%;			
    z-index: 15;
    width: 60%;
    padding-left: 0;
    margin-left: -35%;
    text-align: center;
    list-style: none;
    }
    .image-text1{
    position: relative;
    top: -110px;
    bottom: 500px;
    right: 40%;			
    z-index: 10;
    width: 50%;
    padding-left: 0;
    list-style: none;
    }
    .image-text2{
    position: relative;
    top: -110px;
    bottom: 500px;
    right: 35%;			
    z-index: 10;
    width: 50%;
    padding-left: 0;
    list-style: none;
    }
    .image-text3{
    position: relative;
    top: -110px;
    bottom: 500px;
    right: 40%;			
    z-index: 10;
    width: 50%;
    padding-left: 0;
    list-style: none;
    }
    .image-text4{
    position: relative;
    top: -110px;
    bottom: 500px;
    right: 40%;			
    z-index: 10;
    width: 50%;
    padding-left: 0;
    list-style: none;
    }
    
</style> 

<body>
<!-- Images Slider Start -->
<div id="carousel-example-generic" class ="carousel slide" data-ride="carousel">
<!-- Indicators -->
<!-- carousel의 지시자 -->
<!-- 지시자라고는 하는데 ol태그의 class에 carousel-indicators를 넣는다. -->
<ol class="carousel-indicators">
<!-- li는 이미지 개수만큼 추가하고 data-target은 carousel id를 가르킨다. -->
<!-- data-slide-to는 순서대로 0부터 올라가고 0은 active를 설정한다. -->
<!-- 딱히 이 부분은 옵션별로 설정하게 없다. -->
<li data-target= "#carousel-example-generic" data-slide-to="0" class="active"></li> 
<li data-target= "#carousel-example-generic" data-slide-to="1"></li>
<li data-target="#carousel-example-generic" data-slide-to="2"></li>
<li data-target="#carousel-example-generic" data-slide-to="3"></li>
</ol>

<!-- 실제 이미지 아이템 -->
<!-- class는 carousel-inner로 설정하고 role은 listbox에서 설정한다. -->
<div class="carousel-inner" role="listbox">
<!-- 이미지의 개수만큼 item을 만든다. 
중요한 포인트는 carousel-indicators의 li 태그 개수와 item의 개수는 일치해야 한다. -->
<!-- Wrapper for slides -->
<div class="carousel-inner" role="list box"> <div class="item active">
<img src="<%=contextPath%>/images/slider1.png" alt="slide1">
<div class="carousel-caption">
<div class="image-text1">
<h4 style="font-weight:bold">늘봄샵</h4>
	__________________________ <br>
<h1>내 반려견에게</h1> 
<h3>필요한것은 뭘까요?</h3> <br>
<a style="text-decoration: none" href="<%=contextPath%>/nbShop/pet.jsp">
<button style="color: white; border:none; border-radius: 20px; background-color:lightpink; font-size: 15px">늘봄샵 바로가기</button></a>
</div>
</div>
</div>
<div class="item">
<img src="<%=contextPath%>/images/slider2.png" alt="slide2">
<div class="carousel-caption">
<div class="image-text2">
 <h4 style="font-weight:bold">수강신청</h4>
	__________________________ <br>
<h1>함께 살아가기 위한</h1> 
<h3>수업을 받아보세요!</h3> <br>
<a style="text-decoration: none" href="<%=contextPath%>/nbShop/trainer.jsp">
<button style="color: white; border:none; border-radius: 20px; background-color:lightpink; font-size: 15px">수강신청 바로가기</button></a>
</div>
</div>
</div>

<div class="item">
<img src= "<%=contextPath%>/images/slider3.png" alt="slide3">
<div class="carousel-caption">
<div class="image-text3">
<h4 style="font-weight:bold">수강후기</h4>
	__________________________ <br>
<h1>수강후기를</h1> 
<h3>들려주세요!</h3> <br>
 <a style="text-decoration: none" href="<%=contextPath%>/nbBoard/">
<button style="color: white; border:none; border-radius: 20px; background-color:lightpink; font-size: 15px">수강후기 바로가기</button></a>  
</div>
</div>
</div>

<div class="item">
<img src= "<%=contextPath%>/images/slider4.png" alt="slide4">
<div class="carousel-caption">
<div class="image-text4">
<h4 style="font-weight:bold">자유게시판</h4>
	__________________________ <br>
<h1>자유롭게</h1> 
<h3>소통해봐요!</h3> <br>
 <a style="text-decoration: none" href="<%=contextPath%>/nbBoard/list.jsp">
<button style="color: white; border:none; border-radius: 20px; background-color:lightpink; font-size: 15px">소통하러가기</button></a>  
</div>
</div>
</div>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/run_prettify.js"></script>

</body>
</html>