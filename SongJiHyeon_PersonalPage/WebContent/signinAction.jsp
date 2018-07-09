<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="user.User"%>
<%@ page import="user.UserDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	String in_userid = null;
	in_userid = request.getParameter("userid");
	int result = UserDAO.signin(in_userid, request.getParameter("pw")); // 로그인 시도.
	if (result == 1) {
		User user = new User();
		user = UserDAO.getUserinfo(in_userid);
		session.setAttribute("s_userid", user.getUserid());
		session.setAttribute("s_age", user.getAge());
		session.setAttribute("s_gender", user.getGender());
		session.setAttribute("s_username", user.getName());
		session.setAttribute("s_pw", user.getPw());
	}
	out.print(result);	//확인용
%>