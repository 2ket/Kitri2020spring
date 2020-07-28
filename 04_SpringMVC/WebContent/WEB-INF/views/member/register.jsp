<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<c:set var="root" value="${pageContext.request.contextPath }"/>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link rel="stylesheet" href="${root }/CSS/member/joinStyle.css">
	<script type="text/javascript" src="${root }/javaScript/member/register.js"></script>
</head>
<body>
	<!--<jsp:include page="../../../index.jsp"/><br><br>-->
	
	<div id="container">
		<span>회원가입(*필수입력사항입니다.)</span>
		<div id="table_form">	<!-- 테두리 -->
			<form name="createForm1" action="${root }/member/registerOk.do" method="post" onsubmit="return createForm(this)">
			<!-- <input type="hidden" name="registerForm" value=""> -->
				<div>
					<label>아이디</label>
					<span>*<input type="text" name="id">
					<input type="button" value="아이디중복" onclick="return idCheck(createForm1, '${root}')"></span>
				</div>
				<div>
					<label>비밀번호</label>
					*<input type="password" name="pw">
				</div>
				<div>
					<label>비밀번호 확인</label>
					*<input type="password" name="pwCheck">
				</div>
				<div>
					<label>이름</label>
					*<input type="text" name="name">
				</div>
				<div>
					<label>주민번호</label>
					*<input type="text" name="jumin1">-<input type="text" name="jumin2">
				</div>
				<div>
					<label>이메일</label>
					<input type="email" name="email">
				</div>
				<div>
					<label>우편번호</label>
					<input type="text" name="zipCode" >
					<input type="button" onclick="zipcodeReader('${root}')" value="우편번호 검색">
				</div>
				<div>
					<label>주소</label>
					<input type="text" name="addr">
				</div>
				<div>
					<label>직업</label>
					<select name="job" id="job">
						<option value="">직업을 선택하세요</option>
						<option value="개발자">개발자</option>
						<option value="기획자">기획자</option>
						<option value="DBadmin">DBadmin</option>
					</select>
				</div>
				<div>
					<label>메일수신</label>
					<input type="radio" name="mailing" value="yes"><span>yes</span>
					<input type="radio" name="mailing" value="no"><span>no</span>
				</div>
				<div>
					<label>관심분야</label>
					<input type="checkbox" value="economic" name="interest"><span>경제</span>
					<input type="checkbox" value="IT" name="interest"><span>IT</span>
					<input type="checkbox" value="music" name="interest"><span>음악</span>
					<input type="checkbox" value="art" name="interest"><span>미술</span>
					<input type="hidden" name="resultInterest">
				</div>
				<div id="form_button">
					<input type="submit" value="가입">
					<input type="reset" value="취소">
				</div>
			</form>
		
		</div>
	</div>
</body>
</html>