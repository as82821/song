<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>당신은 패셔니스타</title>

<!-- Bootstrap -->
<link href="./css/nomalize.css" rel="stylesheet">
<link href="./css/bootstrap.min.css" rel="stylesheet">
<style>
@import
	url(http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400);

@font-face {
	font-family: 'NanumBarunGothic';
	src: url("../fonts/NanumBarunGothic.eot");
	src: local("☺"), url("../fonts/NanumBarunGothic.woff") format("woff");
}

a:hover {
	text-decoration: none;
}

body {
	font-family: "NanumBarunGothic", sans-serif;
	padding-top: 90px;
}

#one {
	width: 35%;
}

.container-fluid {
	padding: 0;
}
/* nav bar customize */
.navbar {
	background-color: #fff;
	border: none;
	padding-bottom: 10px;
	font-family: 'Source Sans Pro', sans-serif;
	font-weight: 300;
	font-size: 18px;
	height: 90px;
	text-transform: capitalize;
	border-bottom: 1px solid #AAAAAA
}

.navbar-toggle {
	position: relative;
	margin-top: 40px;
	top: 2px;
}

.navbar-nav {
	padding-right: 10px;
	margin-top: 20px;
	background-color: #fff
}

.navbar-nav li {
	margin: 0 20px;
}

.navbar-brand {
	padding-left: 20px;
}

.navbar-collapse {
	padding-top: 10px;
	background-color: #fff
}

.navbar-default .navbar-nav>li>a:hover {
	color: #FF8000
}

.navbar-default .navbar-nav>li.active>a, .navbar-default .navbar-nav>li.active>a:hover,
	.navbar-default .navbar-nav>li.active>a:focus {
	color: #FF8000;
	background-color: #fff
}

.control {
	position: inherit;
	top: 50%;
	z-index: 5;
	display: inline-block;
	right: 50%;
}

@media ( max-width : 320px) {
	.navbar-brand img {
		width: 80%;
		height: auto;
		margin-top: -100px
	}
}

@media ( max-width : 980px) {
	.navbar-nav li {
		margin: 0 10px;
	}
}
/* 아이콘 부분 */
.service {
	margin: 40px 0;
}

.service a {
	display: block;
}

.icontxt {
	display: block;
	color: #2E2F28;
	text-align: left;
}

.icontxt h4 {
	font-weight: bold;
	font-family: 'Source Sans Pro';
	font-size: 20px;
	text-transform: uppercase;
	text-align: left;
}

.icons {
	float: left;
	display: block;
	font-size: 30px;
	color: #fff;
	background-color: #FF8000;
	border-radius: 50%;
	text-align: center;
	margin-right: 15px;
	padding: 20px;
	border: 4px solid #FF8040;
	transition: all 0.3s;
}

.service a:hover .icons {
	background-color: #0080C0;
}

.service a:hover .icontxt h4 {
	color: #0080FF;
}

@media ( min-width : 768px) {
	.icons {
		margin: 0 auto;
		position: relative;
		left: 30%;
	}
	.icontxt h4 {
		text-align: center;
	}
}

@media ( max-width : 360px) {
	.icontxt p {
		display: none;
	}
	.icontxt {
		line-height: 10px;
		padding: 0;
		margin: 0;
	}
	.icontxt h4 {
		display: none;
	}
	.icons {
		padding: 20px;
		margin: 10px 0 10px 20px;
	}
	.book img {
		width: 50%;
		height: auto;
	}
	.control {
		display: none;
	}
}
</style>
</head>
<body>
<%
	if(session.getAttribute("id")!=null){
		response.sendRedirect("main.jsp");
	}
%>

	<div class="container-fluid">
		<!-- nav bar 부분 -->



		<div class="container" id="one">
			<img src="imgs/10.png" width="100"
				style="margin-left: auto; margin-right: auto; display: block;" />
			<form class="form-horizontal" action="checkuser.jsp" method="post"
				role="form">
				<fieldset>
					<legend>로그인 </legend>
					<div class="form-group">
						<label for="address" class="col-xs-2 col-lg-2 control-label"><span
							class="glyphicon glyphicon-user"> </span>ID</label>
						<div class="col-xs-10 col-lg-10">
							<input type="text" name="id" class="form-control"
								placeholder="ID">
						</div>
					</div>
					<div class="form-group">
						<label for="address" class="col-xs-2 col-lg-2 control-label"><span
							class="glyphicon glyphicon-lock"> </span>암호</label>
						<div class="col-xs-10 col-lg-10">
							<input type="password" name="pw" class="form-control"
								placeholder="암호">
						</div>
					</div>
				</fieldset>


				<input type="submit" class="btn btn-primary col-xs-12 col-lg-12"
					style="margin: 3px;" value="로그인">

			</form>
			<button type="button" class="btn btn-danger col-xs-12 col-lg-12"
				style="margin: 3px;" onclick="location.href='inputuserinfo.jsp' ">
				회원가입</button>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>