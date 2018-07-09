<%@page import="dao.Place_detailDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		/* String lat=request.getParameter("lat");
		if(lat==null){
			response.sendRedirect("main.jsp");
		} */
		
		String id=request.getParameter("id");
		Place_detailDAO dtdao = Place_detailDAO.getInstance();
		
		if (id == null) {
			response.sendRedirect("main.jsp");
		} else {
			dtdao.getinfo(Integer.parseInt(id));
		}
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
							<li class="active"><a href="place_detail.jsp?contentid=<%=id%>"><%=dtdao.title %> 상세 정보</a></li>
							<li class="active"><%=dtdao.title %> 위치</li>
						</ul>
					</div>
				</div>
			</div>
		</section>
		<section id="content">
			<div class="map">
				<div id="google-map" data-latitude="<%=request.getParameter("lat") %>" data-longitude="<%=request.getParameter("lng") %>"></div>
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
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAqVsYGHfI7oQiYAXAv81GC1yWqU5cXsWU"></script>
	<script src="js/animate.js"></script>
	<script src="js/custom.js"></script>
	<script>
		jQuery(document).ready(function($) {
			//Google Map
			var get_latitude = $('#google-map').data('latitude');
			var get_longitude = $('#google-map').data('longitude');

			function initialize_google_map() {
				var myLatlng = new google.maps.LatLng(get_latitude, get_longitude);
				var mapOptions = {
					zoom: 14,
					scrollwheel: false,
					center: myLatlng
				};
				var map = new google.maps.Map(document.getElementById('google-map'), mapOptions);
				var marker = new google.maps.Marker({
					position: myLatlng,
					map: map
				});
			}
			google.maps.event.addDomListener(window, 'load', initialize_google_map);
		});
	</script>
	<script src="contactform/contactform.js"></script>

</body>

</html>