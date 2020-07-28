<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<c:set var="root" value="${pageContext.request.contextPath }"/>
<head>
	<meta charset="UTF-8">
	<title>우편번호 검색</title>
	<script type="text/javascript" src="${root }/javaScript/member/register.js"></script>
</head>
<body>
	<form action="${root }/member/zipcode.do" method="post">
		<div align="center">
			<table>
				<tr>
					<td colspan="2" style="text-align:center;">
						우편번호를 검색하세요.
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="dong">
						<input type="submit" value="검색">
					</td>
				</tr>
			</table>
			
		</div>
	
	</form>
	
	<div align="center">
		<table>
		<c:choose>
			<c:when test="${zipcodeList.size()==0 }">
				<tr>
					<td>검색된 결과가 없습니다.</td>
				</tr>
			</c:when>
			<c:when test="${zipcodeList.size()>0 }">
				<tr>
					<td>아래 우편번호를 클릭하세요.</td>
				</tr>
				<c:forEach var="zipDto" items="${zipcodeList }">
					<tr>
						<td>
							<a href="javascript:sendAddress('${zipDto.zipcode }', '${zipDto.sido }', '${zipDto.gugun }', '${zipDto.dong }', ' ${zipDto.ri }', ' ${zipDto.bunji }');">
								${zipDto.zipcode }
								 ${zipDto.sido }
								 ${zipDto.gugun }
								 ${zipDto.dong }
								 ${zipDto.ri }
								 ${zipDto.bunji }
							 </a>
						</td>
					</tr>
				
				</c:forEach>
			</c:when>
		</c:choose>
		</table>
	
	</div>
	
	<div align="center">
		<a href="javascript:self:close()">닫기</a>
	</div>
	
	
</body>
</html>