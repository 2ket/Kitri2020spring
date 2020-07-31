<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<c:set var="root" value="${pageContext.request.contextPath }"/>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원수정</title>
	<link rel="stylesheet" href="${root }/CSS/member/joinStyle.css">
	<script type="text/javascript" src="${root }/javaScript/member/register.js"></script>
</head>
<body>
	<jsp:include page="../../../index.jsp"/><br><br>
	
	<div id="container">
		<span>회원수정(*필수입력사항입니다.)</span>
		<div id="table_form">	<!-- 테두리 -->
			<form name="createForm1" action="${root }/member/updateOk.do" method="post" onsubmit="return createForm(this)">
			<input type="hidden" name="num" value="${memberDto.num }">
				<div>
					<label>아이디</label>
					<span>*<input type="text" name="id" value="${memberDto.id }" disabled="disabled">
					<%-- <input type="button" value="아이디중복" onclick="return idCheck(createForm1, '${root}')"> --%>
					</span>
				</div>
				<div>
					<label>비밀번호</label>
					*<input type="password" name="pw" value="${memberDto.pw }">
				</div>
				<div>
					<label>비밀번호 확인</label>
					*<input type="password" name="pwCheck" value="${memberDto.pw }">
				</div>
				<div>
					<label>이름</label>
					*<input type="text" name="name" value="${memberDto.name }" disabled="disabled">
				</div>
				<div>
					<label>주민번호</label>
					*<input type="text" name="jumin1" value="${memberDto.jumin1 }" disabled="disabled">-<input type="password" name="jumin2" value="${memberDto.jumin2 }" disabled="disabled">
				</div>
				<div>
					<label>이메일</label>
					<input type="email" name="email" value="${memberDto.email }">
				</div>
				<div>
					<label>우편번호</label>
					<input type="text" name="zipCode" value="${memberDto.zipCode }" readonly="readonly">
					<input type="button" onclick="zipcodeReader('${root}')" value="우편번호 검색">
				</div>
				<div>
					<label>주소</label>
					<input type="text" name="addr" value="${memberDto.addr }">
				</div>
				<div>
					<label>직업</label>
					<select name="job" id="job">
						<option value="">직업을 선택하세요</option>
						<option value="개발자">개발자</option>
						<option value="기획자">기획자</option>
						<option value="DBadmin">DBadmin</option>
					</select>
					${memberDto.job}
					<script type="text/javascript">
						createForm1.job.value="${memberDto.job}"
					</script>
				</div>
				<div>
					<label>메일수신</label>
					<input type="radio" name="mailing" value="yes"><span>yes</span>
					<input type="radio" name="mailing" value="no"><span>no</span>
					${memberDto.mailing}
					<script type="text/javascript">
						for(var i=0; i<createForm1.mailing.length;i++){
							if(createForm1.mailing[i].value=="${memberDto.mailing}"){
								createForm1.mailing[i].checked=true;
							}
						}
					</script>
				</div>
				<div>
					<label>관심분야</label>
					<input type="checkbox" value="economic" name="interest"><span>경제</span>
					<input type="checkbox" value="IT" name="interest"><span>IT</span>
					<input type="checkbox" value="music" name="interest"><span>음악</span>
					<input type="checkbox" value="art" name="interest"><span>미술</span>
					<input type="hidden" name="resultInterest">
					${memberDto.interest }
				 
					<c:forTokens var="interest" items="${memberDto.interest }" delims=",">
						<script type="text/javascript">
							for(var i=0; i<createForm1.interest.length; i++){
								if(createForm1.interest[i].value=="${interest}"){
									createForm1.interest[i].checked=true;
								}
							}
						</script>
					</c:forTokens>
				 <!-- 
					<script type="text/javascript">
						var text="${memberDto.interest}";
						var token=text.split(",");
						for(var i=0; i<createForm1.interest.length; i++){
							for(var j=0; j<token.length; j++){
								if(createForm1.interest[i].value==token[j]){
									createForm1.interest[i].checked=true;
								}
							}
						}
					</script>
					-->
				</div>
				
				<div id="form_button">
					<input type="submit" value="수정">
					<input type="reset" value="취소">
				</div>
			</form>
		
		</div>
	</div>
</body>
</html>