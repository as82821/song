<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="popscore.*"%>
<%@page import="popscore.pop.*"%>
<%@page import="popscore.ranking.*"%>
<%@page import="hybridscore.*"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.sql.*"%>
<%@page import="make_finalList.MakeFinalList" %>
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
		MakeFinalList makelist=new MakeFinalList();
		makelist.makelist();
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
						<li class="active">맞춤 여행지</li>
					</ul>
				</div>
			</div>
		</div>
		</section>
		<section id="content">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="clearfix"></div>
						<div class="row">
						<section id="projects">
						<ul id="thumbs" class="portfolio">
							<!-- 사용자가 요청한 관광지는 관광지id:점수로 랭킹이 매겨져서 전송됨. 관광지id를 세션에 저장하고 상세정보페이지에서 보여준다? -->
							<!-- Item Project and Filter Name -->
							<%
								for (int i = 0; i < makelist.placelist.size(); i++) {
							%>
							<!-- 1번시작 -->
							<li class="item-thumbs col-lg-3 design" data-id="id-0" data-type="graphic">
								<!-- Fancybox - Gallery Enabled - Title - Full Image --> <!-- 사진을 클릭했을 때 나오는 이미지, 설명 -->

								<!-- Thumb Image and Description --> <!-- 그냥 보여지는 이미지 -->
								<a href="place_detail.jsp?contentid=<%=makelist.placelist.get(i)%>">
								<img src="<%=makelist.imagelist.get(i)%>" alt="관광지 사진"></a>
								<h4 id="venue_nationwide_name_1"><%=makelist.titlelist.get(i) %></h4>
								
								<p id="venue_nationwide_city_1"	style="display: inline-block; float: right;"><%=makelist.addrlist.get(i) %></p>
							</li>
							<!-- End Item Project -->
							<!-- 1번끝 -->
							<%
								}
							%>
						</ul>
						</section>
					</div>
				</div>
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
