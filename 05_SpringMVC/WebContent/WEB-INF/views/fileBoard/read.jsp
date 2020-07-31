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
<link type="text/css" rel="stylesheet" href="${root }/CSS/board/boardStyle.css">
<style type="text/css">
	label+span{
	margin: 4px 0px;
	margin-left: 5px;
	line-height: 4px;
	}
	
	#contents {
	height: 190px;
}
#board div>div {
	overflow: hidden;
}
</style>

<script type="text/javascript">
	function replyFunc(root, boardNumber, groupNumber, sequenceNumber, sequenceLevel){
		var url=root+"/fileBoard/write.do?boardNumber="+boardNumber;
		url+="&groupNumber="+groupNumber+"&sequenceNumber="+sequenceNumber+"&sequenceLevel="+sequenceLevel;
		location.href=url;
	}
	
	function delFun(root, boardNumber, pageNumber){
		var url=root+"/fileBoard/delete.do?boardNumber="+boardNumber+"&pageNumber="+pageNumber;
		location.href=url;
		/* 
		var value=confirm("정말 삭제하시겠습니까?");
		if(value==true){
			var url=root+"/fileBoard/deleteOk.do?boardNumber="+boardNumber+"&pageNumber="+pageNumber;
			location.href=url;
		}else{
			alert("취소되었습니다.");
			return false;
		}
		 */
	}
	
	function updateFunc(root, boardNumber, pageNumber){
		var url=root+"/fileBoard/update.do?boardNumber="+boardNumber+"&pageNumber="+pageNumber;
		location.href=url;
		
	}

</script>
</head>
<body>
	<div id="board"><!-- 전체 폼 -->
		
			<input type="hidden" name="boardNumber" value="${boardNumber }">
			<input type="hidden" name="groupNumber" value="${groupNumber }">
			<input type="hidden" name="sequenceNumber" value="${sequenceNumber }">
			<input type="hidden" name="sequenceLevel" value="${sequenceLevel }">
			<div style="text-align:right;">
				<span><a href="${root }/fileBoard/list.do">글목록</a></span>
			</div>
			
			<div><!-- 테두리용 -->
				<div>
					<label>글번호</label>
					<span>${boardDto.boardNumber }</span>
				</div>
				<div>
					<label>제목</label>
					<span>${boardDto.subject }</span>
				</div>
				<div>
					<label>작성자</label>
					<span>${boardDto.writer }</span>
				</div>
				<div>
					<label>조회수</label>
					<span>${boardDto.readCount }</span>
					</div>
				<div>
					<label>작성일</label>
					<span>
						<fmt:formatDate value="${boardDto.writeDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</span>
				</div>
				<div>
					<label>이메일</label>
					<span>${boardDto.email }</span>
				</div>
				<div id="contents">
					<label>내용</label>
					<span>${boardDto.content }</span>
				</div>
			<c:if test="${boardDto.fileSize!=0 }">
				<div>
					<label>첨부파일</label>
					<span><a href="${root}/fileBoard/downLoad.do?boardNumber=${boardDto.boardNumber}">${boardDto.fileName }</a></span>
				</div>
			</c:if>
				
				<div style="text-align:center;"><!-- 버튼부분 -->
					<input type="button" value="글수정" onclick="updateFunc('${root}', '${boardDto.boardNumber }', '${pageNumber }')"/>
					<input type="button" value="글삭제" onclick="delFun('${root}', '${boardDto.boardNumber }', '${pageNumber }')"/>
					<input type="button" value="답글" onclick="replyFunc('${root}', '${boardDto.boardNumber }', '${boardDto.groupNumber }', '${boardDto.sequenceNumber }', '${boardDto.sequenceLevel }')"/>
					<input type="button" value="글목록" onclick="location.href='${root}/fileBoard/list.do?pageNumber=${pageNumber }'"/>
				</div>
			</div><!-- //테두리용 -->
		
	</div>
</body>
</html>