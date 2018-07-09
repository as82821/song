<%@page import="dao.Restaurant_detailDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>행여행여</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<!-- css -->
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/fancybox/jquery.fancybox.css" rel="stylesheet">
<link href="css/jcarousel.css" rel="stylesheet" />
<link href="css/flexslider.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />


<!-- Theme skin -->
<link href="skins/default.css" rel="stylesheet" />

<!-- =======================================================
    Theme Name: Moderna
    Theme URL: https://bootstrapmade.com/free-bootstrap-template-corporate-moderna/
    Author: BootstrapMade
    Author URL: https://bootstrapmade.com
	======================================================= -->

</head>
<body>
	<%
		String id = request.getParameter("contentid");
		if (id == null) {
			response.sendRedirect("restaurant.jsp");
		}

		Restaurant_detailDAO dtdao = Restaurant_detailDAO.getInstance();
		dtdao.getinfo(Integer.parseInt(id));
	%>
	<div id="wrapper">
		<!-- start header -->
		<%@include file="header.jsp"%>
		<!-- end header -->

		<section id="inner-headline">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<ul class="breadcrumb">
						<li><a href="main.jsp"><i class="fa fa-home"></i></a><i
							class="icon-angle-right"></i></li>
						<li class="active"><a href="restaurant.jsp">음식점</a></li>
						<li class="active"><%=dtdao.title %> 상세 정보</li>
					</ul>
				</div>
			</div>
		</div>
		</section>
		<section id="content">
		<div class="container">
			<div class="row">
				<!-- 음식점 사진, 개요 -->
				<div class="col-lg-8">
					<article>
					<div class="post-image">
						<div class="post-heading">
							<h3><%=dtdao.title%></h3>
						</div>
						<img class="detail_image" src="<%=dtdao.image%>" alt="이미지" />
					</div>
					<h5><%=dtdao.addr%></h5>
					<hr color="black" size="5px">
					<h5>개요</h5>
					<p>
						<%=dtdao.overview%>
					</p>

					</article>

				</div>
				<!-- 음식점 사진, 개요 끝 -->

				<!-- 기타 기능 -->
				<div class="col-lg-4">
					<aside class="right-sidebar">
					<div class="widget">
						<h5 class="widgetheading">카테고리</h5>
						<ul class="cat">
							<li><i class="icon-angle-right"></i><%=dtdao.cat3%></li>
						</ul>
					</div>
					<div class="widget">
						<h5 class="widgetheading">위치정보</h5>
						<ul class="recent">
							<li>
								<form action="restaurant_map.jsp" method="post">
									<input type="hidden" name="lat" value="<%=dtdao.lat%>">
									<input type="hidden" name="lng" value="<%=dtdao.lng%>">
									<input type="hidden" name="id" value="<%=id%>">
									<input type="submit" value="지도 보기">
								</form>

							</li>
						</ul>
					</div>
					</aside>
				</div>
				<!-- 기타 기능 끝 -->
			</div>
		</div>

		</section>
		<!-- start footer -->
		<%@include file="footer.jsp"%>
		<!-- end footer -->
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
