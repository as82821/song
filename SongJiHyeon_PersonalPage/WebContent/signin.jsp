<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html lang="en">
<%@include file="common.jsp"%>	
<meta charset="UTF-8">
<style>
.limiter {
	padding-top : 10%;
}
#signin-header {
	
	vertical-align: middle;
}
#signin-header a{
	height:100%;
	font-size:18px;
	font-weight: bold;
	display: inline-block;
	float:right;
	width:25%;
	margin: 20px 0 20px 0;
}
.container {
	width:35%;
}
.form-control input {
	height:40px;
}
</style>
<%
if(session.getAttribute("s_userid")!=null) {
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('이미 로그인 되어있습니다.')");
	script.println("location.href = 'main.jsp'");
	script.println("</script>");
}
%>
<body>
  <script src="http://code.jquery.com/jquery-1.12.0.js"></script>
  <script>
    $(document).ready(function() {
      $('#btnLogin').click(function() {
        var action = $('#frmLogin').attr("action");
        var form_data = {
                          userid: $('#userid').val(),
                          pw: $('#pw').val()
        };
        $.ajax({
                  type: "POST",
                  url: action,
                  data: form_data,
                  success: function(response) {
                  	  if(response.trim() == 1){
                              //
                            swal({
                                type: 'success',
                                title: 'Success',
                                text: '로그인에 성공하였습니다!',
                                footer: '<a></a>',
                              }).then(function(result) {
                                if (result) {
                                    location.replace('main.jsp');
                                }
                              }).done();
                              //
                  	  } else if(response.trim() == 0) { // 비밀번호가 틀려 실패
                		  swal({
                		    type: 'error',
                		    title: 'Oops...',
                		    text: '비밀번호가 틀렸습니다.',
                		    footer: '<a>비밀번호 찾기</a>',
                		  })
                	  }else if(response.trim() == -1){ // 아이디가 틀려 실패 
                		  swal({
                  		    type: 'error',
                  		    title: 'Oops...',
                  		    text: '존재하지 않는 계정입니다.',
                  		    footer: '<a href>아이디 찾기</a>',
                  		  })
                	  }else if(response.trim() == -2){ // 데이터베이스 오류.
                		  swal({
                  		    type: 'error',
                  		    title: 'Oops...',
                  		    text: '데이터베이스에 문제가 있습니다!',
                  		    footer: '<a href>Why do I have this issue?</a>',
                  		  })
                	  }    	  
                  },
                  error: function() {
            		  swal({
                		    type: 'error',
                		    title: 'Oops...',
                		    text: 'AJAX Issue!',
                		    footer: '<a href>Why do I have this issue?</a>',
                		  })
                  }
        });
      });
    });
</script>
	<div class="limiter">
		<div class="container">
			<form class="form-signin" id="frmLogin" action="signinAction.jsp" method="post">
				<div id="signin-header">
					<h2 class="form-signin-heading" style="display: inline-block; float:left; width:75%;">
						<b>행<span class="yellow-span">여행</span>여</b> 로그인
					</h2>
					<a href="main.jsp"> ← Main </a>
				</div>
				<label for="userid" class="sr-only">사용자 ID</label>
				<input type="text" id="userid" name="userid" class="form-control"
					placeholder="사용자 ID" required autofocus style="border-radius: 5px 5px 0 0;">
				<label for="inputPassword" class="sr-only">Password</label>
				<input type="password" id="pw" name="pw" class="form-control" placeholder="********" required style="border-radius: 0 0 5px 5px;">
				<div class="checkbox">
          			<label><input type="checkbox" value="remember-me"> Remember me</label>
        		</div>
				<input type="button" id="btnLogin" class="btn btn-lg btn-success btn-block signup-btn" type="submit" value="SIGN IN"
				style="border-radius:5px" src="signinAction.jsp">
			</form>
			<div class="signup-wrapper">
				<p style="padding-top:20px;">아직 계정이 없으세요??</p>
				<center><button class="btn btn-warning btn-block signup-btn" style="border-radius:5px" onclick="location.href='signup.jsp'"> Sign Up </button></center>
			</div>
		</div>
	</div>
	
<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>