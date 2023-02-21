<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover,shrink-to-fit=no ">
	
	<!-- 부트스트랩 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
	<!-- 클래식 에디터 -->
	<script src="https://cdn.ckeditor.com/ckeditor5/29.1.0/classic/ckeditor.js"></script>
	<title>Home</title>
	
	<style type="text/css">
	.btn-outline-primary{
	float: right;
	}
	</style>
	
	
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
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
	
</head>
<body>
	<div id="w_table">
        <input type="text" class="form-control" placeholder="제목을 입력해 주세요" aria-label="Username" aria-describedby="addon-wrapping">
	    
	    <div id="classic">
	        <p></p>
	    </div>
	    <button type="button" class="btn btn-outline-primary">등록</button>
	    <button type="button" class="btn btn-outline-primary">목록</button>
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