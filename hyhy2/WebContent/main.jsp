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
							<li><img src="img/slides/food1.jpg" alt="" />
								<div class="flex-caption">
									<h3>음식점</h3>
									<p>"음식은 두 가지로 나뉜다. 내가 먹어본 것, 내가  앞으로 먹어볼 것"</p>
									<a href="restaurant.jsp" class="btn btn-theme">음식점 검색</a>
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
									Travelers' Pick: &nbsp; <span>테마별 추천 장소</span>
								</h2>
							</div>
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
							<h3 class="heading font-noto" style="display: inline-block;">이번 여행은 이런 곳이 어떨까요?</h3>
							<span style="float: right; margin-top: 20px"><a	href="attraction.jsp" style="font-size: 15px;">더보기</a></span>
						</div>
						<div class="row venue-list" style="float:none;">
							<ul>
								<li>
									<div class="col-lg-3">
										<div class="box font-noto venue-wrapper"
											id="venue_nationwide_1">
											<div class="thumbnail-image">
												<img src="http://tong.visitkorea.or.kr/cms/resource/34/1568134_image2_1.jpg"
													style="background-size: contain;"
													onerror="this.src='../no_image.jpg'">
											</div>
											<h4 id="venue_nationwide_name_1">삼청공원</h4>
											<span class='star'> <span id="star-attraction-1"
												style="width: 70%"></span>
											</span>
											<p id="venue_nationwide_city_1"
												style="display: inline-block; float: right;">서울특별시 종로구 와룡공원길 41</p>
										</div>
									</div>
								</li>
								
								<li>
									<div class="col-lg-3">
										<div class="box font-noto venue-wrapper"
											id="venue_nationwide_1">
											<div class="thumbnail-image">
												<img src="http://tong.visitkorea.or.kr/cms/resource/16/2033916_image2_1.jpg"
													style="background-size: contain;"
													onerror="this.src='../no_image.jpg'">
											</div>
											<h4 id="venue_nationwide_name_1">흥천사</h4>
											<span class='star'> <span id="star-attraction-1"
												style="width: 80%"></span>
											</span>
											<p id="venue_nationwide_city_1"
												style="display: inline-block; float: right;">서울특별시 성북구 흥천사길 29</p>
										</div>
									</div>
								</li>
								
								<li>
									<div class="col-lg-3">
										<div class="box font-noto venue-wrapper"
											id="venue_nationwide_1">
											<div class="thumbnail-image">
												<img src="http://tong.visitkorea.or.kr/cms/resource/40/1568040_image2_1.jpg"
													style="background-size: contain;"
													onerror="this.src='../no_image.jpg'">
											</div>
											<h4 id="venue_nationwide_name_1">경복궁</h4>
											<span class='star'> <span id="star-attraction-1"
												style="width: 90%"></span>
											</span>
											<p id="venue_nationwide_city_1"
												style="display: inline-block; float: right;">서울특별시 종로구 사직로 161</p>
										</div>
									</div>
								</li>
								
								<li>
									<div class="col-lg-3">
										<div class="box font-noto venue-wrapper"
											id="venue_nationwide_1">
											<div class="thumbnail-image">
												<img src="http://tong.visitkorea.or.kr/cms/resource/81/1075281_image2_1.jpg"
													style="background-size: contain;"
													onerror="this.src='../no_image.jpg'">
											</div>
											<h4 id="venue_nationwide_name_1">광화문</h4>
											<span class='star'> <span id="star-attraction-1"
												style="width: 80%"></span>
											</span>
											<p id="venue_nationwide_city_1"
												style="display: inline-block; float: right;">서울특별시 종로구 사직로 161</p>
										</div>
									</div>
								</li>											
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
				<div class="row">
					<div class="col-lg-12">
						<div>
							<h3 class="heading font-noto" style="display: inline-block;">방문자 수가 높은 음식점, 여기 어때요?</h3>
							<span style="float: right; margin-top: 20px"><a
								href="restaurant.jsp" style="font-size: 15px;">더보기</a></span>
						</div>
						<div class="row venue-list" style="float:none;">
							<ul>
								<li>
									<div class="col-lg-3">
										<div class="box font-noto venue-wrapper"
											id="venue_nationwide_1">
											<div class="thumbnail-image">
												<img src="http://tong.visitkorea.or.kr/cms/resource/07/818107_image2_1.jpg"
													style="background-size: contain;"
													onerror="this.src='../no_image.jpg'">
											</div>
											<div class="venue-name">
											<p >가가와</p>
											</div>
											<span class='star'> <span id="star-attraction-1"
												style="width: 90%"></span>
											</span>
											<p id="venue_nationwide_city_1"
												style="display: inline-block; float: right;">서울특별시 노원구 화랑로 443</p>
										</div>
									</div>
								</li>
								
								<li>
									<div class="col-lg-3">
										<div class="box font-noto venue-wrapper"
											id="venue_nationwide_1">
											<div class="thumbnail-image">
												<img src="http://tong.visitkorea.or.kr/cms/resource/91/1805291_image2_1.jpg"
													style="background-size: contain;"
													onerror="this.src='../no_image.jpg'">
											</div>
											<div class="venue-name">
											<p >금홍</p>
											</div>
											<span class='star'> <span id="star-attraction-1"
												style="width: 90%"></span>
											</span>
											<p id="venue_nationwide_city_1"
												style="display: inline-block; float: right;">서울특별시 용산구 이촌로 182</p>
										</div>
									</div>
								</li>
								
								<li>
									<div class="col-lg-3">
										<div class="box font-noto venue-wrapper"
											id="venue_nationwide_1">
											<div class="thumbnail-image">
												<img src="http://tong.visitkorea.or.kr/cms/resource/69/1998869_image2_1.JPG"
													style="background-size: contain;"
													onerror="this.src='../no_image.jpg'">
											</div>
											<div class="venue-name">
											<p >낙서파전</p>
											</div>
											<span class='star'> <span id="star-attraction-1"
												style="width: 85%"></span>
											</span>
											<p id="venue_nationwide_city_1"
												style="display: inline-block; float: right;">서울특별시 동대문구 회기로28길 15</p>
										</div>
									</div>
								</li>
								
								<li>
									<div class="col-lg-3">
										<div class="box font-noto venue-wrapper"
											id="venue_nationwide_1">
											<div class="thumbnail-image">
												<img src="http://tong.visitkorea.or.kr/cms/resource/25/1717725_image2_1.jpg"
													style="background-size: contain;"
													onerror="this.src='../no_image.jpg'">
											</div>
											<div class="venue-name">
											<p >너비집</p>
											</div>
											<span class='star'> <span id="star-attraction-1"
												style="width: 70%"></span>
											</span>
											<p id="venue_nationwide_city_1"
												style="display: inline-block; float: right;">서울특별시 중구 명동9길 37-8</p>
										</div>
									</div>
								</li>
											
							</ul>
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