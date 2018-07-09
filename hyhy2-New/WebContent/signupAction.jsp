<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.User" %>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" class="user.User" scope="page" />
<jsp:setProperty name="user" property="userid" />  
<jsp:setProperty name="user" property="pw" /> 
<jsp:setProperty name="user" property="gender" />

<%
	user.setName(request.getParameter("username"));
	if(!request.getParameter("pw").equals(request.getParameter("pwconf"))){ // 비밀번호 확인 오류
		out.print(2);
	} else {
		if (UserDAO.useridCheck(request.getParameter("userid")) == 0) { // 가입처리 
			user.setAge(Integer.parseInt(request.getParameter("age")));
			UserDAO.signup(user);
			out.print(4); //회원가입 처리
		} else { // id 중복 오류
			out.print(3); //이미 존재하는 id
		}
	}
%>