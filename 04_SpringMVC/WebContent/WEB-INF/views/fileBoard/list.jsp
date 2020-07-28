<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<html>
<head>
<meta charset="UTF-8">
<title>파일 게시판</title>
</head>
<body>
	<div align="center"> <!-- style="margin-top: 100px" 줘도 됨 -->
		<table>
			<tr>
				<td width="530" align="right">
				<a href="${root}/fileBoard/write.do">글쓰기</a>
				</td>
			</tr>
		</table>
	</div>
	
	<c:if test="${count==0 || boardList.size()==0}">
		<div align="center">
			게시판에 저장된 글이 없습니다.
		</div>
	</c:if>
	
	<c:if test="${count > 0}">
	<div align="center"> <!-- style="margin-top: 100px" 줘도 됨 -->
		<table border="1">
			<tr>
				<td align="center" width="50">번호</td>
				<td align="center" width="250">제목</td>
				<td align="center" width="70">작성자</td>
				<td align="center" width="100">작성일</td>
				<td align="center" width="50">조회수</td>
			</tr>
			
			<c:forEach var="boardDto" items="${boardList}"> <!-- 반복해서 뽑아줘야 하므로 forEach써줌 -->
			<tr>
				<td width="50">${boardDto.boardNumber}</td>
				<td width="250">
					<c:if test="${boardDto.sequenceLevel > 0 }">
						<c:forEach begin="0" end="${boardDto.sequenceLevel}">
							&nbsp;
						</c:forEach>
						[답글]
					</c:if>
					<a href="${root }/fileBoard/read.do?boardNumber=${boardDto.boardNumber}&pageNumber=${currentPage}">${boardDto.subject}</a>
					<c:if test="${boardDto.fileSize!=0 }">
						💾
					</c:if>
				</td>
				<td width="70">${boardDto.writer}</td>
				<td width="150">
					<fmt:formatDate  value="${boardDto.writeDate}" pattern="YYYY-MM-dd HH:mm:ss"/>
				</td>
				<td align="center" width="50">${boardDto.readCount}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	</c:if>
	
	<div align="center">
		<%--
			강사님이 짠 개념
			if) 전체 레코드 수(게시글 수)가 100개 일 때,
			1.한페이지 당 게시물 수:10개 (내가 10개씩 뿌린다고 했을 떄)
			2.총 페이지수= 10page. why? 전체레코드수 100/한 페이지 당 게시물 수 10
			
			if) 전체 레코드 수(게시글 수)가 101개 일때,
			1.한페이지 당 게시물 수:10개 (내가 10개씩 뿌린다고 했을 떄)
			2.총 페이지수= 11page. why? (전체레코드수 101/한 페이지 당 게시물 수 10) + 1page
			
			3.페이지번호 블록:10
						  [1][2][3][4][5]...[10]
						   요청 페이지 번호가 5이면 시작번호1, 끝번호 10
						   요청 페이지 번호가 11이면 시작번호11, 끝번호 20 인 작업 필요.
						  **단 (다음, 이전)은 맨 마지막에 생각한다.
						  pageBlock, currentPage:시작번호, 끝번호
						  
						  int startPage= (int) ((currentPage-1)/pageBlock)*pageBlock+1
						  						(3-1)=2/10=0.2 ->0 0+1=1
						  						
						  int endPage =  startPage+pageBlock-1
											1+10-1 = 10
											31+10-1= 40
			4. boardSize, currentPage, count: Command Data(얘가 보내줌)
			
			
		 --%>
		 <fmt:parseNumber var="pageCount" value="${count/boardSize + (count%boardSize==0?0:1)}" integerOnly="true"/>
		 <!-- 3항식으로 표현함 count=전체 게시글 개수 boardSize=내가 정한 개수에서 두개 나눴을 때 나머지 0이면0, 나머지 0아니면 +1해줌
		  -->
		  <!-- 여기서 ${pageCount}찍어주면 1.3처럼 나옴 처리 나중에 해주면 됨 -->
		  <c:set var="pageBlock" value="${5}"/>		<%-- 페이징 넘버 표시할 갯수 --%>
		  
		  <fmt:parseNumber var="result" value="${(currentPage-1)/pageBlock}" integerOnly="true"/>
		  <c:set var="startPage" value="${result*pageBlock +1}"/>
		  <c:set var="endPage" value="${startPage+pageBlock-1}"/>
		  <%--${startPage}, ${endPage}, --%>
		  
	
	<c:if test="${endPage > pageCount }">
		<c:set var="endPage" value="${pageCount }"/>
	</c:if>
	<%--${startPage}, ${endPage} --%>
	
	<c:if test="${startPage > pageBlock }">
		<a href="${root }/fileBoard/list.do?pageNumber=${startPage-pageBlock}">이전</a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<a href="${root }/fileBoard/list.do?pageNumber=${i}">[${i }]</a>
	</c:forEach>
	
	<c:if test="${endPage < pageCount }">
		<a href="${root }/fileBoard/list.do?pageNumber=${startPage+pageBlock}">[다음]</a>
	</c:if>
	</div>
</body>
</html>