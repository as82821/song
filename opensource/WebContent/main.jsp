<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="org.json.simple.*"%>
<%@page import="org.json.simple.parser.*"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>당신은 패셔니스타</title>

<!-- Bootstrap -->
<link href="./css/nomalize.css" rel="stylesheet">
<link href="./css/bootstrap.min.css" rel="stylesheet">
<style>
@import
	url(http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400);

@font-face {
	font-family: 'NanumBarunGothic';
	src: url("../fonts/NanumBarunGothic.eot");
	src: local("☺"), url("../fonts/NanumBarunGothic.woff") format("woff");
}

a:hover {
	text-decoration: none;
}

body {
	font-family: "NanumBarunGothic", sans-serif;
	padding-top: 0px;
	background-size: 100%
}

.one {
	position: fixed;
	top: 100px;
}

.container-fluid {
	padding: 0;
}
/* nav bar customize */
.navbar {
	background-color: #fff;
	border: none;
	padding-bottom: 10px;
	font-family: 'Source Sans Pro', sans-serif;
	font-weight: 300;
	font-size: 18px;
	height: 90px;
	text-transform: capitalize;
	border-bottom: 1px solid #AAAAAA
}

.navbar-toggle {
	position: relative;
	margin-top: 40px;
	top: 2px;
}

.navbar-nav {
	padding-right: 10px;
	margin-top: 20px;
	background-color: #fff
}

.navbar-nav li {
	margin: 0 20px;
}

.navbar-brand {
	padding-left: 20px;
}

.navbar-collapse {
	padding-top: 10px;
	background-color: #fff
}

.navbar-default .navbar-nav>li>a:hover {
	color: #FF8000
}

.navbar-default .navbar-nav>li.active>a, .navbar-default .navbar-nav>li.active>a:hover,
	.navbar-default .navbar-nav>li.active>a:focus {
	color: #FF8000;
	background-color: #fff
}

.control {
	position: inherit;
	top: 50%;
	z-index: 5;
	display: inline-block;
	right: 50%;
}

@media ( max-width : 320px) {
	.navbar-brand img {
		width: 80%;
		height: auto;
		margin-top: -100px
	}
}

@media ( max-width : 980px) {
	.navbar-nav li {
		margin: 0 10px;
	}
}
/* 아이콘 부분 */
.service {
	margin: 40px 0;
}

.service a {
	display: block;
}

.icontxt {
	display: block;
	color: #2E2F28;
	text-align: left;
}

.icontxt h4 {
	font-weight: bold;
	font-family: 'Source Sans Pro';
	font-size: 20px;
	text-transform: uppercase;
	text-align: left;
}

.icons {
	float: left;
	display: block;
	font-size: 30px;
	color: #fff;
	background-color: #FF8000;
	border-radius: 50%;
	text-align: center;
	margin-right: 15px;
	padding: 20px;
	border: 4px solid #FF8040;
	transition: all 0.3s;
}

.service a:hover .icons {
	background-color: #0080C0;
}

.service a:hover .icontxt h4 {
	color: #0080FF;
}

@media ( min-width : 768px) {
	.icons {
		margin: 0 auto;
		position: relative;
		left: 30%;
	}
	.icontxt h4 {
		text-align: center;
	}
}

@media ( max-width : 360px) {
	.icontxt p {
		display: none;
	}
	.icontxt {
		line-height: 10px;
		padding: 0;
		margin: 0;
	}
	.icontxt h4 {
		display: none;
	}
	.icons {
		padding: 20px;
		margin: 10px 0 10px 20px;
	}
	.book img {
		width: 50%;
		height: auto;
	}
	.control {
		display: none;
	}
}
</style>
</head>
<body>

	<%
						long todaytemp = 0;
						long todaysky = 0;
						String source = "";

						String addr = "http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?serviceKey=";
						String servicekey = "LcH3i5zoCYPXGxjxAQ05YHXBL9bBcQ2WR3Oy968Ej6X%2Faue5ZxcZEuCMYMMkHA57dnkmr7uQhfb9e6euhWv58A%3D%3D";
						String parameter1 = "";
						String parameter2 = "";

						Calendar cal = Calendar.getInstance();

						int year = cal.get(cal.YEAR);
						int month = cal.get(cal.MONTH) + 1;
						int date = cal.get(cal.DATE);
						String result = "";
						String smonth = "";
						String sdate = "";

						if (month < 10)
							smonth = "0" + month;
						else
							smonth = "" + month;

						if (date < 10)
							sdate = "0" + date;
						else
							sdate = "" + date;

						result = "" + year + smonth + sdate;
						String today = result;
						//System.out.println(date);
						parameter1 = "&base_date=" + today;
						parameter2 = "&base_time=0500&nx=68&ny=106&numOfRows=10&pageSize=10&pageNo=1&startPage=1&_type=json";
						addr = addr + servicekey + parameter1 + parameter2;
						//System.out.println(addr);
						URL url = new URL(addr);
						BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
						String inLine;
						String json = "";

						while ((inLine = br.readLine()) != null) {
							json = inLine;
						}
						br.close();

						int cnt = 0;

						JSONParser parser = new JSONParser();
						// 원본 데이터 JSONObject에 넣음
						JSONObject object = (JSONObject) parser.parse(json);
						// response부분 파싱
						JSONObject responseObject = (JSONObject) object.get("response");
						// body부분 파싱
						JSONObject bodyObejct = (JSONObject) responseObject.get("body");
						// items부분 파싱
						JSONObject itemsObject = (JSONObject) bodyObejct.get("items");
						// item부분 JSONArray형에 넣음
						JSONArray arr = (JSONArray) itemsObject.get("item");

						for (int i = 0; i < arr.size(); i++) {
							JSONObject arrobj = (JSONObject) arr.get(i);

							String category = (String) arrobj.get("category");
							if (category.equals("POP") && cnt == 1)
								continue;
							if (category.equals("UUU") || category.equals("VEC") || category.equals("VVV")
									|| category.equals("WSD"))
								continue;
							if (category.equals("POP"))
								cnt++;
							long baseDate = (Long) arrobj.get("baseDate");
							String baseTime = (String) arrobj.get("baseTime");
							long fcstDate = (Long) arrobj.get("fcstDate");
							String fcstTime = (String) arrobj.get("fcstTime");
							long fcstValue = (Long) arrobj.get("fcstValue");
							long nx = (Long) arrobj.get("nx");
							long ny = (Long) arrobj.get("ny");

							//JudgeCategory judge = new JudgeCategory(category, fcstValue);
							//judge.printfInfo(category, fcstValue);
							//System.out.println("온도"+fcstValue);
							if (category.equals("T3H")) {
								todaytemp = fcstValue;
							}
							if (category.equals("SKY")) {
								todaysky = fcstValue;
							}
						}

						if (todaysky == 1) {
							source = "./imgs/그림1.png";
						} else if (todaysky == 2) {
							source = "./imgs/그림1.png";
						} else if (todaysky == 3) {
							source = "./imgs/그림1.png";
						} else if (todaysky == 4) {
							source = "./imgs/흐림.jpg";
						}
						System.out.println(source);
					%>
	<div class="container">
		<!-- 스크롤이 되면 나타나는 메뉴 -->
		<nav class="navbar navbar-fixed-top topfix hidden" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><img src="./imgs/smalllogo"
					alt=""> </a>
			</div>
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">메뉴</a></li>
					<li><a href="#">메뉴2</a></li>
					<li><a href="#">메뉴3</a></li>
				</ul>
				<form action="logout.jsp" class="navbar-form navbar-right" role="search">
					<%=session.getAttribute("id") %> 님 환영합니다!
					<button type="submit" class="btn btn-default">로그아웃</button>
				</form>
			</div>
		</div>
		</nav>

		<div class="menu">
			<!-- 초기 메뉴  -->
			<div class="row">
				<div class="col-xs-8">
					<img src="./imgs/hat.jpg" width="500" alt="">
				</div>
				<div class="col-xs-4">
						<form action="logout.jsp" class="navbar-form navbar-right" role="search">
					<%=session.getAttribute("id") %> 님 환영합니다!
					<button type="submit" class="btn btn-warning">로그아웃</button>
				</form>
				</div>
			</div>
			<nav class="navbar navbar-default" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="start.html">소개 </a></li>
					<li><a href="collection1.html">사진 모음</a></li>
					<li><a href="#">개발자이야기</a></li>
				</ul>
			</div>
			</nav>
		</div>
		<div class="container-fluid">
			<!-- nav bar 부분 -->

			<%
		
				Connection conn = null;
				PreparedStatement pstmt = null;
				PreparedStatement pstmt2 = null;
				ResultSet rs=null;
				ResultSet rs2=null;
				String body="";
				String sex="";
				String age="";
				String season;
				String imgurl="";
				String up[]=new String[5];
				String down[]=new String[5];
				int i=0;

				try {
					String driver = "com.mysql.cj.jdbc.Driver";
					String url1 = "jdbc:mysql://localhost:3306/weatherfit?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
					String username = "root";
					String pwd = "1234";
					Class.forName(driver);
					conn = DriverManager.getConnection(url1, username, pwd);
					pstmt = conn.prepareStatement("select * from signup where id=(?)");
					pstmt.setString(1, (String)session.getAttribute("id"));
					rs=pstmt.executeQuery();
					while(rs.next()){
						body=rs.getString("body");
						sex=rs.getString("sex");
						age=rs.getString("age");
					}
					
					if(Integer.parseInt(age)>=10&Integer.parseInt(age)<30)
						age="10s20s";
					else if(Integer.parseInt(age)>=30)
						age="30s40s";
					
					if(todaytemp<5)
						season="winter";
					else if(todaytemp>20)
						season="summer";
					else
						season="springfall";
						
					imgurl="imgs/"+sex+"/"+age+"/"+body+"/"+season;
					
					pstmt2=conn.prepareStatement("select url from upurltable where sex=(?) and age=(?) and body=(?) and season=(?)");
					pstmt2.setString(1,sex);
					pstmt2.setString(2,age);
					pstmt2.setString(3,body);
					pstmt2.setString(4,season);
					rs2=pstmt2.executeQuery();
					while(rs2.next()){
						up[i]=rs2.getString("url");
						System.out.println(rs2.getString("url"));
						i++;
					}
					
					i=0;
					
					pstmt2=conn.prepareStatement("select url from bottomurltable where sex=(?) and age=(?) and body=(?) and season=(?)");
					pstmt2.setString(1,sex);
					pstmt2.setString(2,age);
					pstmt2.setString(3,body);
					pstmt2.setString(4,season);
					rs2=pstmt2.executeQuery();
					while(rs2.next()){
						down[i]=rs2.getString("url");
						System.out.println(rs2.getString("url"));
						i++;
					}
					i=0;
					System.out.println(imgurl);
				} catch (ClassNotFoundException ce) {
					ce.printStackTrace();
					System.out.println(ce.getMessage());
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			%>

			<img src="imgs/10.png" width="100"
				style="margin-left: auto; margin-right: auto; display: block;" />
			<div class="container" id="one">
				<div class="row">
					<div class="col-md-2">


						<div class="panel panel-danger" style="position: relative;">
							<div class="panel-heading">
								<h3 class="panel-title">옷  정보</h3>
								<div class="panel-body">
									<div class="table-responsive">
										상의
										<div class="w3-content2" style="max-width: 200px">
										 	<a href="<%=up[0] %>" target="_blank">
											<img class="mySlides2" src="<%=imgurl %>/1-1.jpg" style="width: 100%"></a>
											<a href="<%=up[1] %>" target="_blank">
											<img class="mySlides2" src="<%=imgurl %>/2-1.jpg" style="width: 100%"></a>
											<a href="<%=up[2] %>" target="_blank">
											<img class="mySlides2" src="<%=imgurl %>/3-1.jpg" style="width: 100%"></a>
											<a href="<%=up[3] %>" target="_blank">
											<img class="mySlides2" src="<%=imgurl %>/4-1.jpg" style="width: 100%"></a>
											<a href="<%=up[4] %>" target="_blank">
											<img class="mySlides2" src="<%=imgurl %>/5-1.jpg" style="width: 100%"></a>
				
										</div>
										하의
										<div class="w3-content3" style="max-width: 200px">
											<a href="<%=down[0] %>" target="_blank">
											<img class="mySlides3" src="<%=imgurl %>/1-2.jpg" style="width: 100%"></a>
											<a href="<%=down[1] %>" target="_blank">
											<img class="mySlides3" src="<%=imgurl %>/2-2.jpg" style="width: 100%"></a>
											<a href="<%=down[2] %>" target="_blank">
											<img class="mySlides3" src="<%=imgurl %>/3-2.jpg" style="width: 100%"></a>
											<a href="<%=down[3] %>" target="_blank">
											<img class="mySlides3" src="<%=imgurl %>/4-2.jpg" style="width: 100%"></a>
											<a href="<%=down[4] %>" target="_blank">
											<img class="mySlides3" src="<%=imgurl %>/5-2.jpg" style="width: 100%"></a>
											
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-2">
					</div>
					<div class="col-md-6">
						<!--캐러셀  -->

						<div class="w3-content" style="max-width: 700px;">
						<!-- 전체이미지 -->
						
							<img class="mySlides" src="<%=imgurl %>/1.jpg" style="width:75%">
							<img class="mySlides" src="<%=imgurl %>/2.jpg" style="width:75%">
							<img class="mySlides" src="<%=imgurl %>/3.jpg" style="width:75%">
							<img class="mySlides" src="<%=imgurl %>/4.jpg" style="width:75%">
							<img class="mySlides" src="<%=imgurl %>/5.jpg" style="width:75%">
							
						
						</div>
						<div class="w3-center ">

							<div class="w3-section">
								<!-- 요기-->
								<div class="col-lg-2 col-md-4 col-sm-5 col-xs-5"></div>

								<div class="col-lg-5 col-md-3 col-sm-2 col-xs-2">
									<button class="w3-btn btn btn-warning" onclick="plusDivs(-1)">❮
										Prev</button>
									<button class="w3-btn btn btn-warning" onclick="plusDivs(1)">Next
										❯</button>
								</div>
								<div class="col-lg-2 col-md-5 col-sm-5 col-xs-5"></div>
								<!-- 요기-->
							</div>
						</div>

						<script>
							var slideIndex = 1;
							var slideIndex2 = 1;
							var slideIndex3 = 1;
							showDivs(slideIndex);
							showDivs2(slideIndex2);
							showDivs3(slideIndex3);

							function plusDivs(n) {
								if(slideIndex>5){
									slideIndex = 1;
								}
								if(slideIndex<1){
									slideIndex = 5;
								}
								if(slideIndex2>5){
									slideIndex2 = 1;
								}
								if(slideIndex2<1){
									slideIndex2 = 5;
								}
								if(slideIndex3>5){
									slideIndex3 = 1;
								}
								if(slideIndex3<1){
									slideIndex3 = 5;
								}

								showDivs(slideIndex += n);
								showDivs2(slideIndex2 += n);
								showDivs3(slideIndex3 += n);

							}

							function currentDiv(n) {

								showDivs(slideIndex = n);
								showDivs2(slideIndex2 = n);
								showDivs3(slideIndex3 += n);
							}

							function showDivs(n) {
								var i;
								var x = document
										.getElementsByClassName("mySlides");

								if (n > x.length) {
									slideIndex = 1
								}
								if (n < 1) {
									slideIndex = x.length
								}
								;
								for (i = 0; i < x.length; i++) {
									x[i].style.display = "none";
								}

								x[slideIndex - 1].style.display = "block";

							}
							function showDivs2(n) {
								var i;
								var x = document
										.getElementsByClassName("mySlides2");

								if (n > x.length) {
									slideIndex = 1
								}
								if (n < 1) {
									slideIndex = x.length
								}
								;
								for (i = 0; i < x.length; i++) {
									x[i].style.display = "none";
								}

								x[slideIndex - 1].style.display = "block";

							}

							function showDivs3(n) {
								var i;
								var x = document
										.getElementsByClassName("mySlides3");

								if (n > x.length) {
									slideIndex = 1
								}
								if (n < 1) {
									slideIndex = x.length
								}
								;
								for (i = 0; i < x.length; i++) {
									x[i].style.display = "none";
								}

								x[slideIndex - 1].style.display = "block";

							}
						</script>







					</div>
					<!--// 캐러셀 부분 끝  -->
					<!-- 날씨시작 -->
				
					<div class="col-md-2">
						<div class="panel panel-success">
							<div class="panel-heading">
								<h3 class="panel-title">날씨</h3>
								<div class="panel-body">
									<div class="table-responsive">
										<table class="table table-bordered">
											<tr>
												<img src="<%=source%>" style="width:100px" />
											</tr>

										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="panel panel-warning">
							<div class="panel-heading">
								<h3 class="panel-title">기온</h3>
								<div class="panel-body">
									<div class="table-responsive">
										<table class="table table-bordered">
											<tr>
												<td class="text-center "><strong>오늘의 기온 : <%=todaytemp%>˚C
												</strong></td>
											</tr>

										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>



			</div>
		</div>

		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
		<script src="./js/bootstrap.min.js"></script>
		<script src="./js/aanimatescroll.min.js"></script>
		<script>
			$('.carousel').carousel();
		</script>
</body>
</html>