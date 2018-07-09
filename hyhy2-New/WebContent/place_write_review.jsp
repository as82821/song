<%@page import="service.GetReviewListService"%>
<%@page import="service.ReviewListView"%>
<%@page import="model.Review"%>
<%@page import="dao.Place_detailDAO" %>
<%@page import="jdbc.connection.ConnectionProvider" %>
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
<link href="skins/default.css" rel="stylesheet" />

<link href="css/star.css" rel="stylesheet" />
<script src="js/starjs.js"></script>


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
		
		int contentid = 0;
		Place_detailDAO dtdao=Place_detailDAO.getInstance();

		String id = request.getParameter("contentid");
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
						<li><a href="main.jsp"><i class="fa fa-home"></i></a><i	class="icon-angle-right"></i></li>
						<li class="active"><a href="attraction.jsp">여행지</a></li>
						<li class="active"><a href="place_showranking1.jsp">맞춤 여행지</a></li>
						<li class="active"><a href="place_detail.jsp?contentid=<%=id%>"><%=dtdao.title %> 상세 정보</a></li>
						<li class="active"><%=dtdao.title %> 리뷰 작성</li>
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
					</div>
					<hr>
					<form action="place_write_review_action.jsp" id="reviewform" method="post">
						<!-- 세션에서 유저 아이디 가져오는 코드로 변경할 것 -->
						<input type="hidden" name="userid" value="user1">
						 <input	type="hidden" name="contentid" value="<%=id%>">
						 
						 <h3>평점</h3>
						 	<span class="star-input">
 								 <span class="input">
    								<input type="radio" name="score" id="p1" value="0.5"><label for="p1">1</label>
    								<input type="radio" name="score" id="p2" value="1"><label for="p2">2</label>
   									<input type="radio" name="score" id="p3" value="1.5"><label for="p3">4</label>
    								<input type="radio" name="score" id="p4" value="2"><label for="p4">4</label>
   									<input type="radio" name="score" id="p5" value="2.5"><label for="p5">5</label>
  									<input type="radio" name="score" id="p6" value="3"><label for="p6">6</label>
    								<input type="radio" name="score" id="p7" value="3.5"><label for="p7">7</label>
    								<input type="radio" name="score" id="p8" value="4"><label for="p8">8</label>
    								<input type="radio" name="score" id="p9" value="4.5"><label for="p9">9</label>
   									<input type="radio" name="score" id="p10" value="5"><label for="p10">10</label>
 								</span>
							</span>
						
						<h3>리뷰내용</h3>
						<textarea name="reviewcontent" cols="80" rows="8"
							style="width: 100%; height: 20%"></textarea>
						<br> <br>
						<div class="btn-group">
							<input type="button"
								onclick="document.getElementById('reviewform').submit();"
								value="리뷰 남기기" class="btn btn-theme" />
						</div>
					</form>
					</article>

				</div>
				<!-- 관광지 사진, 개요 끝 -->

				<!-- 기타 기능 -->
				<div class="col-lg-4">
					<aside class="right-sidebar">
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

							<%
								for (int i = 1; i <= viewdata.getPageTotalCount(); i++) {
									if(i>5)
										break;
							%>
							<ul class="pagination">
								<% if(Integer.parseInt(request.getParameter("page"))==i){
								
								%>
								<li class="active"><a href="place_write_review.jsp?contentid=<%=id%>&page=<%=i%>"><%=i%></a></li>
								<%
									}else{
								%>
								<li><a href="place_write_review.jsp?contentid=<%=id%>&page=<%=i%>"><%=i%></a></li>
								<%} %>
							</ul>
							<%
								}
							%>
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
