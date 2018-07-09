<%@page import="jdbc.connection.ConnectionProvider"%>
<%@page import="dao.Place_detailDAO"%>
<%@page import="service.GetReviewListService"%>
<%@page import="service.ReviewListView"%>
<%@page import="model.Review"%>
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
		request.setCharacterEncoding("utf-8");
	
		int contentid=0;
		Place_detailDAO dtdao=Place_detailDAO.getInstance();
	
		String id=request.getParameter("contentid");
		if (id == null) {
			response.sendRedirect("place_showranking1.jsp");
		} else {
			dtdao.getinfo(Integer.parseInt(id));
			contentid=Integer.parseInt(id);
		}
		
		String pageNumberStr = request.getParameter("page");
		int pageNumber = 1;
		if (pageNumberStr != null) {
			pageNumber = Integer.parseInt(pageNumberStr);
		}

		GetReviewListService reviewlistservice = GetReviewListService.getInstance();
		ReviewListView viewdata = reviewlistservice.getReviewList(pageNumber, contentid);
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
						<li><a href="main.jsp"><i class="fa fa-home"></i></a><i class="icon-angle-right"></i></li>
						<li class="active"><a href="attraction.jsp">여행지</a></li>
						<li class="active"><a href="place_showranking1.jsp">맞춤 여행지</a></li>
						<li class="active"><a href="place_detail.jsp?contentid=<%=id%>"><%=dtdao.title %> 상세 정보</a></li>
					</ul>
				</div>
			</div>
		</div>
		</section>
		<section id="content">
		<div class="container">
			<div class="row">
				<!-- 관광지 사진, 개요 -->
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
				<!-- 관광지 사진, 개요 끝 -->

				<!-- 기타 기능 -->
				<div class="col-lg-4">
					<aside class="right-sidebar">
					<div class="widget">
						<h5 class="widgetheading">카테고리</h5>
						<ul class="cat">
							<li><i class="icon-angle-right"></i><%=dtdao.cat1%></li>
							<li><i class="icon-angle-right"></i><%=dtdao.cat2%></li>
							<li><i class="icon-angle-right"></i><%=dtdao.cat3%></li>
						</ul>
					</div>
					<div class="widget">
						<h5 class="widgetheading">위치정보</h5>
						<ul class="recent">
							<li>
								<form action="place_map.jsp" method="post">
									<input type="hidden" name="lat" value="<%=dtdao.lat%>">
									<input type="hidden" name="lng" value="<%=dtdao.lng%>">
									<input type="hidden" name="id" value="<%=id%>"> <input
										type="submit" value="지도 보기">
								</form>

							</li>
						</ul>
					</div>

					<div class="widget">
						<h5 class="widgetheading">사용자 리뷰 작성</h5>
						<h5 class="widgetheading"><a href="place_write_review.jsp?contentid=<%=id%>&page=1">리뷰 작성하기</a></h5>
					</div>

					<div class="widget">
						<h5 class="widgetheading"><%=dtdao.title%>의 최신 사용자 리뷰
						</h5>
						<ul class="recent">

							<%
								if (viewdata.isEmpty()) {
							%>
							등록된 리뷰가 없습니다.
							<%
								}
							%>
							<%
								if (!viewdata.isEmpty()) {

									for (int i = 0; i < viewdata.reviewlist.size(); i++) {
							%>

							<li>
								<h6>
									아이디 :
									<%=viewdata.reviewlist.get(i).getUserid()%>
								</h6>
								<h6>
									평점 :
									<%=viewdata.reviewlist.get(i).getScore()%>
								</h6>
								<p><%=viewdata.reviewlist.get(i).getReviewcontent()%></p>
							</li>

							<%
								}
							%>

							<%
								}
							%>
						</ul>
						<h5 class="widgetheading"><a href="place_all_review.jsp?contentid=<%=id%>&page=1">전체 리뷰 보기</a></h5>
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
