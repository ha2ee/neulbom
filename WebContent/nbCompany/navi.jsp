<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
.form-control {
	/* width:30%; */
	min-width: 30%;
}

#bigdiv {
	/* 	border: solid 1px black; */
	width: 100%;
	height: 700px;
	position: relative;
	margin: 0 auto;
	top: 0;
	left: 0;
	right: 0;
}

#middlediv {
	/* 	border: solid 1px black; */
	width: 30%;
	height: 30%;
	float: left;
	position: relative;
	top: 0;
	left: 250px;
	right: 0;
	margin: 0 auto;
}

#company {
	position: relative;
	right: 250px;
}

#map {
	margin: 0 auto 0 auto;
	width: 30%;
	float: right;
	right: 250px;
}

#kmap {
	padding: 10px 45px;
}
</style>


</head>
<div>
	<body>
		<div id="company">
			<h1>(주) 늘 봄</h1>
			<br>
			<h5>경남 양산시 중부동 686-7 양산역프라자 2층</h5>

			<h5>대표전화 : 1800-0000</h5>

			<h5>업무시간 ▶ 09:00 ~ 18:00 / 점심시간 12:00 ~ 13:00</h5>

			<h6>(토,일요일,공휴일)은 휴무입니다.</h6>
			<br> <br> <br> <br>

		</div>

		<div id="bigdiv"> 
			<form name="mailform" action="MAILTO:kisschu@naver.com" method="post" enctype="text/plain" >
			<div id="middlediv">
				<h2>무엇이든 물어보세요!</h2>
				<br>
				<div id="askname" class="mb-3" >
					<input type="text" class="form-control" id="formGroupExampleInput"
						placeholder="이름" name="name">
				</div>
				<div id="askemail" class="mb-3">
					<input type="text" class="form-control" id="formGroupExampleInput2"
						placeholder="이메일" name="email">
				</div>
				<div id="askhp" class="mb-3">
					<input type="text" class="form-control" id="formGroupExampleInput3"
						placeholder="전화번호" name="hp">
				</div>
				<div id="askcontent" class="mb-3">
					<textarea class="form-control" id="exampleFormControlTextarea1"
						rows="8" placeholder="하고싶은말을 적어주세요." name="text"></textarea>
				</div>

				<div>
					<input type="submit" value="제출" class="btn btn-light" >
					
				</div>
			</div>
			</form>
			<div id="map" style="width: 500px; height: 300px;">

			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2c389d4627b346ffd6ecdd0ee427f6f4"></script>
			<script>
				var container = document.getElementById('map');
				var options = {
					// 지도가 표시될 중심 위치 입니다 위도,경도
					center : new kakao.maps.LatLng(35.338397270427365,
							129.0270503412126),
					level : 2
				};

				// 지도를 생성합니다
				var map = new kakao.maps.Map(container, options);

				// 마커가 표시될 위치입니다 위도,경도
				var markerPosition = new kakao.maps.LatLng(35.33788895330836,
						129.02753802495232);

				// 마커를 생성합니다
				var marker = new kakao.maps.Marker({
					position : markerPosition
				});

				// 마커가 지도 위에 표시되도록 설정합니다
				marker.setMap(map);

				var iwContent = '<div id="kmap" style=" padding : 10px 45px; ">(주)늘 봄<br><a href="https://map.kakao.com/link/to/양산직업학교,35.33788895330836,129.02753802495232" style="color:green;" target="_blank">[길 찾기]<br></a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
				iwPosition = new kakao.maps.LatLng(35.33788895330836,
						129.02753802495232); //인포윈도우 표시 위치입니다

				// 인포윈도우를 생성합니다
				var infowindow = new kakao.maps.InfoWindow({
					position : iwPosition,
					content : iwContent
				});

				// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
				infowindow.open(map, marker);
			</script>
		</div>
</div>
</div>

</body>
</html>