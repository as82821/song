<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import = "java.io.PrintWriter"%>    
<%@page import="dao.PlaceDAO" %>

<!DOCTYPE html>
<html lang="en">
 
<%@include file="common.jsp"%>
<style>
.search-box {
}
.search-box select {
	font-weight: bold;
	border-radius: 15px 0 0 15px;
	padding-left: 15px;
}
.search-box input {
	float:left;
	border-radius: 0 15px 15px 0;
}
</style>
<%
	PlaceDAO rdao=PlaceDAO.getInstance();
	String[] nation={"자연관광지","휴양관광지","역사관광지","체험관광지","건축/조형물","관광자원","산업관광지","문화시설","쇼핑"};
%>
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
					<li ><a href="main.jsp">Home</a></li>
					<li class="active"><a href="attraction.jsp">여행지</a></li>
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
<body>

	<div id="wrapper">
		<!-- start header -->
		<section id="inner-headline">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<ul class="breadcrumb">
						<li><a href="main.jsp"><i class="fa fa-home"></i></a><i
							class="icon-angle-right"></i></li>
						<li class="active">여행지</li>
					</ul>
				</div>
			</div>
		</div>
		</section>
		
		<!-- end header -->
		<section class="callaction" style="padding-bottom:50px;">
			<div class="container"  style="background: url(img/attraction/Attraction.jpg) no-repeat; height:300px; padding-top:100px">
				<div class="row" style="margin-bottom: 0;" align="center">
						<h2>여행지를 검색하세요.</h2>
				</div>
				<div class="row">
					<div class="col-lg-2"></div>
					<div class="col-lg-8">
						<div class="form-group search-box">
							<form id="frmLogin" action="place_searchAction.jsp" method="post">
								<input type="text" class="form-control" id='pname' name="pname" placeholder="관광지명을 입력하세요" style="width: 85%; display: inline-block;">
								<input type='submit' id="btnLogin" class='search-submit' style="width: 15%;" value="검색">
							</form>
						</div>
					</div>
				</div>
			</div>
		</section>
		
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="big-cta">
							<div class="cta-text">
								<h2 class="font-noto">
									Travellers' Pick: &nbsp; <span>여긴 어때요?</span>
								</h2>
							</div>
						</div>
					</div>
				</div>
			</div>
		
		<section id="content">
			<div class="container">
						<%for(int i=0;i<nation.length;i++){ %>
				<div class="row">
					<div class="col-lg-12">
						<div>
							<h3 class="heading font-noto" style="display: inline-block;"><%=nation[i] %></h3>
							<span style="float: right; margin-top: 20px"><a href="#" style="font-size: 15px;">더보기</a></span>
						</div>
						<div class="row venue-list" style="float:none;">
							<ul>
							<%
								rdao.makerandomlist(nation[i]);
								//System.out.println("목록생성완료");
								for(int j=0; j<rdao.contentidlist.size(); j++) {
									rdao.getinfo(rdao.contentidlist.get(j));
							%>
								<li>
									<div class="col-lg-3">
										<div class="box font-noto venue-wrapper"
											id="venue_nationwide_1">
											<div class="thumbnail-image">
												<a href="place_detail.jsp?contentid=<%=rdao.contentidlist.get(j) %>">
												<img src="<%=rdao.image %>" style="background-size: contain;" 	onerror="this.src='../no_image.jpg'">
												</a>
											</div>
											<h4 id="venue_nationwide_name_1"><%=rdao.title %></h4>
											<p id="venue_nationwide_city_1"
												style="display: inline-block; float: right;"><%=rdao.addr %></p>
										</div>
									</div>
								</li>
							<% 
								}
								rdao.contentidlist.clear();
							%>							
							</ul>
						</div>
					</div>
				</div>
				<%
					} 
				%>
			</div>
		</section>

		<!-- start footer -->
		<%@include file="footer.jsp"%>
		<!-- end footer -->
	</div>
	<!-- start common-js -->
	<%@include file="footer-js.jsp" %>
	<!-- end common-js -->
</body>
</html>