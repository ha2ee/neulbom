<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Bottom.jsp</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String contextPath = request.getContextPath();
%>

<!--          			# Footer 관련 -->

    <!-- footer start -->
    <div class="footer">
        <div class="container">
            <div class="row">
                <!-- footer-useful links-start -->
                <div class=" col-lg-3 col-md-3 col-sm-3 col-xs-12">
                    <div class="footer-widget">
                        <h3 class="footer-title" style="color:black;">( 바로 가기 )</h3>
                        <ul>
                            <li><a href="<%=request.getContextPath()%>/nb/Main">Home </a></li>
                            <li><a href="<%=request.getContextPath()%>/nb/Intro.do?center=/nbCompany/intro.jsp">회사 소개 </a></li>
                            <li><a href="<%=request.getContextPath()%>/nb/edu.do?center=/nbShop/trainer.jsp">수강 신청 </a></li>
                            <li><a href="<%=request.getContextPath()%>/nb/free.bo?center=/nbBoard/freeboard.jsp">자유 게시판 </a></li>
                            <li><a href="<%=request.getContextPath()%>/nb/trainer.bo?center=/nbBoard/trainerboard.jsp">훈련사 상담 </a></li>
                            <li> <a href="<%=request.getContextPath()%>/nb/qna.bo?center=/nbBoard/qna.jsp">Q&A</a></li>
                            <li> <a href="<%=request.getContextPath()%>/nb/navi.do?center=/nbCompany/navi.jsp">오시는 길</a></li>
                        </ul>
                    </div>
                </div>
                <!-- footer-useful links-close -->
                <!-- footer-contactinfo-start -->
                <div class=" col-lg-4 col-md-4 col-sm-4 col-xs-12">
                    <div class="footer-widget">
                        <h4 class="footer-title" style="color:black;"><br>( 늘 봄 고객지원 )<br></h4>
                        <div class="">
                            <ul>
                                <li> <i class="fa fa-map-marker"></i>경남 양산시 중부동 686-7 양산역프라자 2층</li>
                                <li><i class="fa fa-phone"></i>055-383-0000</li>
                                <li><i class="fa fa-envelope"></i>admin@nb.co.kr</li>
                            </ul>
                        </div>
                        <a style="border-radius: 20px; border:0; background:#FFB2D9; "href="#" class="btn btn-primary btn-sm">1:1 상담하기<br></a></div>
                </div>
                <!-- footer-contactinfo-close -->
                <!-- footer-about-start -->
                <div class="col-lg-5 col-md-5 col-sm-5 col-xs-12 ">
                    <div class="footer-widget">
                        <h4 class="footer-title" style="color:black;"><br>( 참고 )</h4>
                        <p>본 홈페이지는 1920x1080 해상도에 최적화 되어 있습니다. </p>
                    </div>
                </div>
                <!-- footer-about-close -->
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                    <div class="footer-line"></div>
                </div>
                <div class="col-lg-8 col-md-8 col-sm-6 col-xs-12">
                    <p>Copyright 2021. <i class="fa fa-love"></i><a href="<%=request.getContextPath()%>/nb/Main">nb All rights reserved</a>
                    </p>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
                    <div class="footer-social">
                        <a href="#"><span class="active"><i class="fa fa-facebook"></i></span></a>
                        <a href="#"><span class="active"><i class="fa fa-google-plus"></i></span> </a>
                        <a href="#"><span class="active"><i class="fa fa-twitter"></i> </span></a>
                        <a href="#"><span class="active"><i class="fa fa-instagram"></i></span> </a>
                </div>
            </div>
        </div>
        <!-- footer-address-close -->
    </div>
    <!-- footer close -->
</body>
</html>