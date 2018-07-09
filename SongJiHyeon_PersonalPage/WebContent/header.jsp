<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					<li class="active"><a href="main.jsp">Home</a></li>
					<li><a href="attraction.jsp">여행지</a></li>
					<li><a href="Portfolio.jsp">송지현 소개</a></li>
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