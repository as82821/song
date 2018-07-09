<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.User" %>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html lang="ko">

<%@include file="common.jsp"%>


<style>
#viewProfile tr {
	margin:30px 0 0 30px;
	width:60%
	padding:0 20% 0 20%;
}
#viewProfile th {
	width:21%;
}
#viewProfile td {
	font-size: 16px;
	width:40%;
}
#viewProfile tr {
}
</style>
<body>
<%
request.setCharacterEncoding("utf-8");
if(session.getAttribute("s_userid")==null){
	response.sendRedirect("signin.jsp");
}
%>
	<div id="wrapper">
		<!-- start header -->
		<header>
	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="main.jsp"><img src="img/logo.png" alt="hyhy_logo" style="height:60px;"/></a>
			</div>
			<div class="navbar-collapse collapse ">
				<ul class="nav navbar-nav" id="header-menu">
					<li><a href="main.jsp">Home</a></li>
					<li><a href="attraction.jsp">여행지</a></li>
					<li><a href="restaurant.jsp">음식점</a></li>
					<%
					if(session.getAttribute("s_userid")==null){
					%>
						<li><a href="signin.jsp">로그인</a></li>
					<%
					} else {
					%>
						<li class="dropdown"><a href="#" class="dropdown-toggle "
							data-toggle="dropdown" data-hover="dropdown" data-delay="0"
							data-close-others="false"><%=session.getAttribute("s_username") %>님<b class=" icon-angle-down"></b></a>
							<ul class="dropdown-menu">
								<li><a href="profile.jsp">프로필 보기</a></li>
								<li><a href="logoutAction.jsp">로그아웃</a></li>
							</ul>
						</li>
					<%
					}
					%>
					
				</ul>
			</div>
		</div>
	</div>
</header>
		<!-- end header -->
		<section id="inner-headline">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<ul class="breadcrumb">
							<li><a href="main.jsp"><i class="fa fa-home"></i></a><i class="icon-angle-right"></i></li>
							<li class="active">내 프로필</li>
						</ul>
					</div>
				</div>
			</div>
		</section>
		
		<section id="content">
			<div class="container">
				<div class="row">
					<div class="col-lg-8">
					<!--.............화면 구성.............-->						
					<div class="card-header">
								<ul class="nav nav-pills red" style="margin:0;">
									<li class="nav-item"><a class="nav-link active"
										href="#viewProfile" data-toggle="tab">프로필 보기</a></li>
									<li class="nav-item"><a class="nav-link"
										href="#changeProfile" data-toggle="tab">프로필 변경</a></li>
								</ul>
							</div>
							<!-- end of card-header -->
							<div class="card-body">
								<div class="tab-content">
									<!-- viewProfile -->
									<div class="active tab-pane" id="viewProfile">
										<table class="table table-condensed">
											<caption style="font-size: 25px; margin-bottom:20px;"><%=session.getAttribute("s_username") %>님의 프로필</caption>
											<tbody>
											<tr>
												<th>ID</th>
												<td><%=session.getAttribute("s_userid") %></td>
											</tr>
											<tr>
												<th>PW</th>
												<td><%=session.getAttribute("s_pw") %></td>
											</tr>
											<tr>
												<th>이름</th>
												<td><%=session.getAttribute("s_username") %></td>
											</tr>
											<tr>
												<th>나이</th>
												<td><%=session.getAttribute("s_age") %></td>
											</tr>
											<tr>
												<th>성별</th>
												<td><%
												if(session.getAttribute("s_gender")!=null){
												if(session.getAttribute("s_gender").equals("M")) out.print("남자"); else out.print("여자"); }%></td>
											</tr>
											</tbody>
										</table>
									</div>
									<!-- end of viewProfile -->

									<!-- changeProfile -->
									<div class="tab-pane" id="changeProfile">
										<form class="form-horizontal" action="userinfoAction.jsp"
											method="POST" onsubmit="return checkInput()">
											<div class="form-group">
												<label for="pw" class="col-sm-2 control-label">비밀번호</label>
												<div class="col-sm-10">
													<input type="password" class="form-control" name="pw" id="pw">
												</div>
											</div>
											<div class="form-group">
												<label for="pwconf" class="col-sm-2 control-label">비밀번호 확인</label>
												<div class="col-sm-10">
													<input type="password" class="form-control" name="pwconf" id="pwconf">
												</div>
											</div>
											<div class="form-group">
												<label for="username" class="col-sm-2 control-label">이름</label>
												<div class="col-sm-10">
													<input type="text" class="form-control" name="username" id="username">
												</div>
											</div>
											<div class="form-group">
												<label for="age" class="col-sm-2 control-label">나이</label>
												<div class="col-sm-10">
													<input type="text" class="form-control" id="age" name="age">
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-offset-2 col-sm-10">
													<input type="submit" class="form-control" value="변경하기"
														style="background-color: #ffc000"></input>
												</div>
											</div>
										</form>
									</div>
									<!-- end of changeProfile -->
									
								</div>
								<!-- end of tab-content -->
							</div>
							<!-- /.card-body -->
					
					
					</div>
					

				</div>
			</div>
		</section>
		<%@include file="footer.jsp"%>
	</div>
	<a href="#" class="scrollup"><i class="fa fa-angle-up active"></i></a>
	<!-- javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/jquery.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.fancybox.pack.js"></script>
	<script src="js/jquery.fancybox-media.js"></script>
	<script src="js/google-code-prettify/prettify.js"></script>
	<script src="js/portfolio/jquery.quicksand.js"></script>
	<script src="js/portfolio/setting.js"></script>
	<script src="js/jquery.flexslider.js"></script>
	<script src="js/animate.js"></script>
	<script src="js/custom.js"></script>

</body>

</html>
    