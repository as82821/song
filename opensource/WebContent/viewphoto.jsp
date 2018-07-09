<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String name = "";
		int cnt = 0;
		String fpath = "C:/Users/CBNU/Desktop/사진/female/10s20s/thin/springfall";
		try {
			File folder = new File(fpath);

			/*String files[] = folder.list();
			for (int i = 0; i < files.length; i++) {
				cnt++;
			} */
			System.out.println("시발ㅇㄹㅇ");
			File[] listOfFiles = folder.listFiles();
			//System.out.println(listOfFiles[1]);
			for (File file : listOfFiles) {
				if (file.isFile()) {
					//System.out.println(file.getName());
					name = file.getName();
	%>
	<img src="<%=fpath%>/<%=name%>"/>
	<%
		System.out.println(name);
				}
			}
			System.out.println(cnt);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("시발");
		}
	%>


</body>
</html>