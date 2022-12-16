<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- header -->
	<tiles:insertAttribute name="header"/>
	<hr color="red"/>
	
	<!-- menu -->
	<tiles:insertAttribute name="menu"/>
	<hr color="pink"/>
	
	<!-- content -->
	<tiles:insertAttribute name="content"/>
	<hr color="blue"/>
	
	<!-- footer -->
	<tiles:insertAttribute name="footer"/>
</body>
</html>