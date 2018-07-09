<%@page import="member.LoginDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		LoginDAO ldao = LoginDAO.getInstance();
		
		int check = ldao.logincheck(id, pw);

		if (check == 1) {
			session.setAttribute("id", id);
			response.sendRedirect("main.jsp");
		} else if (check == 0) {
	%>
	<script>
		alert("비밀번호를 확인해주세요!");
		history.go(-1);
	</script>
	<%
		} else if (check == -1) {
	%>
	<script>
		alert("해당 아이디가 존재하지 않습니다!");
		history.go(-1);
	</script>

	<%
		}
	%>
</body>
</html>