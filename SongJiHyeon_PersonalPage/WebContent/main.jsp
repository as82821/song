<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@include file="common.jsp"%>
<body>
	<div id="wrapper">
		<!-- start header -->
		<%@include file="header.jsp"%>
		<!-- end header -->

		<section id="featured"> <!-- start slider -->
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<!-- Slider -->
					<div id="main-slider" class="flexslider">
						<ul class="slides">
							<li><img src="img/slides/attr2.jpg" alt="" />
								<div class="flex-caption">
									<h3>여행지</h3>
									<p>널리 여행하면, 현명해진다!<br />전국 방방곡곡 여행지 둘러보기</p>
									<a href="attraction.jsp" class="btn btn-theme">여행지 검색</a>
								</div></li>
							<li><img src="img/slides/aboutsong.jpg" alt="" />
								<div class="flex-caption">
									<h3>저를 소개합니다</h3>
									<p>"송지현을 소개합니다"</p>
									<a href="Portfolio.jsp" class="btn btn-theme">소개 보러가기</a>
								</div>
							</li>
						</ul>
					</div>
					<!-- end slider -->
				</div>
			</div>
		</div>
		</section>

		<section class="callaction">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="big-cta">
							<div class="cta-text">
								<h2 class="font-noto">
									2013039065 송지현 &nbsp; <span>정보응용실험</span> 개인 웹페이지 제작 과제
								</h2>
								<h2 class="font-noto">
									한국관광공사 API를 이용한 관광지 목록 제공과 관광지에 리뷰를 남길 수 있습니다.
								</h2>
								<h2 class="font-noto">
									사용가능 언어, 관심분야, 취미와 같은 저에 대한 정보를 제공합니다. 
								</h2>
							</div>
						</div>
					</div>
				</div>
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