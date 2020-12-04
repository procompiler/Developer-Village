<%@page import="com.devil.domain.Badge"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뱃지 목록</title>
<link rel="stylesheet" type="text/css" href='../style.css'>
<link rel="stylesheet"
  href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
  integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
  crossorigin="anonymous" />
</head>
<jsp:include page="/header.jsp"></jsp:include>
	<h1>뱃지 목록</h1>
	<a href='form.html'>뱃지 추가</a><br>

	<%
	  List<Badge> list = (List<Badge>) request.getAttribute("list");
	%>

	<table border='1'>
		<thead>
			<tr>
				<th>번호</th>
				<th>뱃지이름</th>
				<th>뱃지사진</th>
				<th>뱃지내용</th>
			</tr>
		</thead>
		<tbody>
			<%
			  for (Badge badge : list) {
			%>
			<tr>
				<td><%=badge.getNo()%></td>
				<td id="name"><a href='detail?no=<%=badge.getNo()%>'
					style='color: black;'><%=badge.getName()%></a></td>
				<td><img style="width: 80px;" src="../upload/badge/<%=badge.getPhoto()%>_20x20.jpg"></td>
				<td><%=badge.getContent()%></td>
			<tr>
				<%
				  }
				%>
			
		</tbody>
	</table>
	<p>
	 <%
    String keyword = request.getParameter("keyword");
  %>
	<form action='list' method='get'>
		검색어: <input type='text' name='keyword' value='<%=keyword != null ? keyword : ""%>'>
		<button>검색</button>
	</form>
	</p>
</body>
</html>