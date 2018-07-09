<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import = "java.io.PrintWriter"%>    
<%@ page import = "java.util.LinkedList"%>
<%@ page import = "java.util.Queue"%>
<%@page import="dao.Place_detailDAO" %>
<%@page import="make_finalList.MakeFinalList" %>

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
	float:right;
	border-radius: 0 15px 15px 0;
}
</style>
<%
//.....................최근 방문한 목록 세션으로 유지하기.......................
	Queue<String> s_venue_queue = new LinkedList<String>(); //최근 본 목록 - 최대 8개
	if (session.getAttribute("s_venue_queue") != null) //목록이 존재하면
		s_venue_queue = (Queue<String>)session.getAttribute("s_venue_queue"); //리스트 불러옴.

	String current_venue;
	if (request.getParameter("venue") == null) {
//<script>alert('비정상적인 접근입니다!''); history.back();</script>
	} else {
		current_venue = request.getParameter("venue");
		if (s_venue_queue.contains(current_venue)) {	//venue가 목록에 있으면
			s_venue_queue.remove(current_venue);	//venue의 과거기록 삭제
		}
		if (s_venue_queue.size() == 8)
			s_venue_queue.poll();	//최근 목록이 가득찼으면 가장 오래된 장소 1개 삭제.
		s_venue_queue.offer(current_venue);	//venue를 최신으로 설정.
	}
	session.setAttribute("s_venue_queue", s_venue_queue);
	/*
	Object[] logs = s_venue_queue.toArray();
	//queue 값을 최신순으로 받아오기
	for (int i = logs.length - 1; i >= 0; i--) {
		out.println(logs[i].toString());
	}
	*/
//.....................최근 방문한 목록 세션으로 유지하기 끝.......................

	String city = "";
	if (request.getParameter("city") != null) //세션값이 존재하면
		city = request.getParameter("city");
	
	MakeFinalList makelist=new MakeFinalList();
	makelist.makelist();
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
						<h2>여행하고 싶은 지역을 검색하세요.</h2>
				</div>
				<div class="row">
					<div class="col-lg-2"></div>
					<div class="col-lg-8">
						<div class="form-group search-box">
							<form name="search_form" action="attraction.jsp" method="get">
								<select class="form-control" name="city" style="width: 85%; display: inline-block;">
									<option value="서울">서울</option>
									<option disabled>인천</option>
									<option disabled>부산</option>
									<option disabled>전주 / 전북지역</option>
									<option disabled>광주 / 전남지역</option>
									<option disabled>대구 / 경북지역 / 울릉도 / 독도</option>
									<option disabled>대전 / 충남지역</option>
									<option disabled>충청북도</option>
									<option disabled>강원도</option>
									<option disabled>제주도</option>
								</select>
								<input type='submit' class='search-submit' style="width: 15%;" value="검색">
							</form>
						</div>
					</div>
				</div>
			</div>
		</section>
		<section id="content">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div>
							<h3 class="heading font-noto" style="display: inline-block;">당신에게 꼭 맞는 여행지</h3>
							<span style="float: right; margin-top: 20px"><a href="place_showranking1.jsp" style="font-size: 15px;">더보기</a></span>
						</div>
						<div class="row venue-list" style="float:none;">
							<ul>
							<%
							for (int i = 0; i < 4; i++) {
							%>
								<li>
									<div class="col-lg-3">
										<div class="box font-noto venue-wrapper" id="venue_nationwide_1">
											<div class="thumbnail-image">
												<img src="<%=makelist.imagelist.get(i)%>" style="background-size: contain;"	onerror="this.src='../no_image.jpg'">
											</div>
											<h4 id="venue_nationwide_name_1"><%=makelist.titlelist.get(i)%></h4>
											<span class='star'> <span id="star-attraction-1" style="width: <%=65+5*i%>%"></span>
											</span>
											<p id="venue_nationwide_city_1" style="display: inline-block; float: right;">서울 특별시</p>
										</div>
									</div>
								</li>
							<%
								}
							%>							
							</ul>
						</div>
					</div>
				</div>
				
				<!-- divider -->
				<div class="row">
					<div class="col-lg-12">
						<div class="solidline"></div>
					</div>
				</div>
				<!-- end divider -->
				<!-- Portfolio Projects -->
		
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