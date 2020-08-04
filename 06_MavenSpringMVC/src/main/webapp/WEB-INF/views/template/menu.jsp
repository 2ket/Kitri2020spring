<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${root }/member/test.do">Spring MVC Start!!!</a>
	
	<h3>회원가입</h3>
	<a href="${root }/member/register.do">회원가입</a>
	
	<c:if test="${memberLevel == null }">
		<h3>로그인</h3>
		<a href="${root }/member/login.do">로그인</a>
	</c:if>
	<c:if test="${memberLevel != null }">
		<h4>로그아웃</h4>
		<a href="${root }/member/logout.do">로그아웃</a>
		<h4>회원수정</h4>
		<a href="${root }/member/update.do">회원수정</a>
		<h4>회원탈퇴</h4>
		<a href="${root }/member/delete.do">회원탈퇴</a>
	</c:if>
	
	<br><br>
	<h3>파일게시판</h3>
	<a href="${root }/fileBoard/write.do">글쓰기</a>
	<br>
	<a href="${root }/fileBoard/list.do">리스트</a>
</body>
</html>