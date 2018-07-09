<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% %>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<%@include file="common.jsp"%>
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
.frm-wrapper {
	padding:10px 0 10px 0;
}
.frm-wrapper label {
	margin-left:5px;
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
                              pw: $('#pw').val(),
                              pwconf: $('#pwconf').val(),
                              gender: $('#gender').val(),
                              age: $('#age').val(),
                              username: $('#username').val()
            };
            $.ajax({
                      type: "POST",
                      url: action,
                      data: form_data,
                      success: function(response) {
                          if(response==1){
                            swal({
                                type: 'error',
                                title: 'Oops...',
                                text: '정보를 빈칸없이 입력해주세요.',
                                footer: '<a></a>',
                		      })
                          }else if(response==2){                            
                              swal({
                                type: 'error',
                                title: 'Oops...',
                                text: '비밀번호와 비밀번호 확인의 값이 다릅니다!',
                                footer: '<a></a>',
                		      })
                          }else if(response==3){
                              swal({
                                type: 'error',
                                title: 'Oops...',
                                text: '계정 이름이 이미 존재합니다!',
                                footer: '<a></a>',
                		      })
                          }else if(response==4){
                              //
                            swal({
                                type: 'success',
                                title: 'Success',
                                text: '회원가입에 성공하였습니다!',
                                footer: '<a></a>',
                              }).then(function(result) {
                                if (result) {
                                    location.replace('main.jsp');
                                }
                              }).done();
                              //
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
			<form class="form-signup" id="frmLogin" action="signupAction.jsp" method="post">
				<div id="signin-header">
					<h2 class="form-signin-heading" style="display: inline-block; float:left; width:75%;">
						<b>행<span class="yellow-span">여행</span>여</b> 회원가입
					</h2>
					<a href="signin.jsp"> ← SIGN IN </a>
				</div>
				<div class="frm-wrapper" style="clear: both;">
					<label for="userid">사용자아이디</label>
					<input type="text" id="userid" name="userid" class="form-control"
					placeholder="사용자 ID" required autofocus style="border-radius: 5px;" data-validate="계정 이름을 입력하세요">
				</div>
				<div class="frm-wrapper">
					<label for="pw">비밀번호</label>
					<input type="password" id="pw" name="pw" class="form-control" placeholder="********" required
					style="border-radius: 5px;" data-validate="비밀번호를 입력하세요">
					<label for="pwconf" style="padding-top:5px;">비밀번호 확인</label>
					<input type="password" id="pwconf" name="pwconf" class="form-control" placeholder="********" required
					style="border-radius: 5px;" data-validate="비밀번호 확인을 입력하세요">
				</div>
				<div class="frm-wrapper">
					<label for="username">사용자 이름</label>
					<input type="text" id="username" name="username" class="form-control"
					placeholder="이름" required style="border-radius: 5px;" data-validate="이름을 입력하세요">
				</div>
				<div class="frm-wrapper">
					<div class="btn-group" data-toggle="buttons">
					<label>성별</label><br />
					<input type="radio" name="gender" id="male" value="M" autocomplete="off" checked>
					<label for="male" style="margin:0 15px 0 0;">남자
					</label>
    				<input type="radio" name="gender" id="female" value="F" autocomplete="off">
  					<label for="female" style="margin:0;">여자
  					</label>
					</div>
				</div>
				<div class="frm-wrapper">
					<label for="age">나이</label>
					<input type="text" id="age" name="age" class="form-control"
					placeholder="사용자 연령" required autofocus style="border-radius: 5px;" data-validate="나이를 입력하세요">
				</div>
				<div class="frm-wrapper">
					<input type="button" id="btnLogin" class="btn-lg btn-success btn-block signup-btn" type="submit" value="SIGN UP"
					style="border-radius:5px">
				</div>				
			</form>
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