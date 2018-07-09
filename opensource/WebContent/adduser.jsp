<%@page import="member.MemberDAO"%>
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
		request.setCharacterEncoding("utf-8");
	%>
	시발거
	<jsp:useBean id="mem" class="member.Member">
		<jsp:setProperty property="*" name="mem" />
	</jsp:useBean>
	<%
		MemberDAO dao = MemberDAO.getInstance();
		int value=100;
		System.out.println(mem.getId());
		value = dao.insertMember(mem);
		System.out.println("여기서"+value);
		if (value == -1) {
	%>
	<script>
		alert("중복된 아이디입니다!");
		history.go(-1);
	</script>
	<%
		} else if (value == 1) {
			response.sendRedirect("login.jsp");
		}
	%>
</body>
</html>