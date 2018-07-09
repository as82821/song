<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO"%>
<%@ page import="user.User" %>
<%@ page import="java.io.PrintWriter"%>

<%
	request.setCharacterEncoding("UTF-8");
	
	User user = new User();
	user.setUserid(session.getAttribute("s_userid").toString());
	user.setPw(request.getParameter("pw"));
	user.setAge(Integer.parseInt(request.getParameter("age")));
	user.setName(request.getParameter("username"));
	user.setGender(session.getAttribute("s_gender").toString());
	
	int result = UserDAO.updateUser(user);
	if(result>0){
		session.setAttribute("s_userid", user.getUserid());
		session.setAttribute("s_age", user.getAge());
		session.setAttribute("s_gender", user.getGender());
		session.setAttribute("s_username", user.getName());
		session.setAttribute("s_pw", user.getPw());
		
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('프로필이 변경되었습니다.')");
		script.println("location.href = 'profile.jsp'");
		script.println("</script>");
	}
	else{
		%>
		<script>
			history.go(-1);
		</script>
		<%
	}
%>


