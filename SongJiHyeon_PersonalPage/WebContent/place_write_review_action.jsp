<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
    <%@page import="model.Review"%>
<%@page import="service.WriteReviewService "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%request.setCharacterEncoding("utf-8"); %>
	<jsp:useBean id="review" class="model.Review">
		<jsp:setProperty name="review" property="*" />
	</jsp:useBean>
	<%
		WriteReviewService writeservice=WriteReviewService.getInstance();
		writeservice.write(review);
	%>
	리뷰작성완료
	<%response.sendRedirect("attraction.jsp"); %>
</body>
</html>