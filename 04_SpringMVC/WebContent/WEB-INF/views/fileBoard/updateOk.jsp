<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>파일 게시판</title>
</head>
<body>
	<c:if test="${check>0 }">
		<script type="text/javascript">
			alert("수정완료");
			location.href="${root}/fileBoard/read.do?boardNumber=${boardNumber}&pageNumber=${pageNumber}"
		</script>
	</c:if>
	<c:if test="${check==0 }">
		<script type="text/javascript">
			alert("수정실패");
			location.href="${root}/fileBoard/update.do?boardNumber=${boardNumber}"
		</script>
	</c:if>
</body>
</html>