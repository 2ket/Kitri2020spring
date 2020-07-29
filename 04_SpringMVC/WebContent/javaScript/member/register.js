/**
 * 
 */
//interest 여러개 찍은거 한줄 String으로 만들어서 데이터 보내주기
var idCheckButt=false;
function createForm(obj){
	var str="";
	for(var i=0; i<obj.interest.length; i++){
		if(obj.interest[i].checked==true){
			str += obj.interest[i].value + ",";
		}
	}
////미입력시 뜨는 문구
//	//아이디 미입력시 출력문구
//	if(obj.id.value==""){
//		alert("아이디를 반드시 입력하세요.");
//		obj.id.focus();
//		return false;
//	}
//	//비밀번호 미입력시
//	if(obj.pw.value==""){
//		alert("비밀번호를 반드시 입력하세요.");
//		obj.pw.focus();
//		return false;
//	}
//	//이름 미입력시
//	if(obj.name.value==""){
//		alert("이름을 반드시 입력하세요.");
//		obj.name.focus();
//		return false;
//	}
//	//주민번호 미입력시
//	if(obj.jumin1.value==""){
//		alert("주민번호를 입력하세요");
//		obj.jumin1.focus();
//		return false;
//	}
//	if(obj.jumin2.value==""){
//		alert("주민번호를 입력하세요");
//		obj.jumin2.focus();
//		return false;
//	}
//	//이메일 미입력시
//	if(obj.email.value==""){
//		alert("이메일을 반드시 입력하세요.");
//		obj.email.focus();
//		return false;
//	}
//	//우편번호 미입력시
//	if(obj.zipCode.value==""){
//		alert("우편번호를 반드시 입력하세요.");
//		obj.zipCode.focus();
//		return false;
//	}
//	//주소 미입력시
//	if(obj.addr.value==""){
//		alert("주소를 반드시 입력하세요.");
//		obj.addr.focus();
//		return false;
//	}
//	//직업 미선택시
//	if(obj.job.value==""){
//		alert("직업을 반드시 선택하세요.");
//		obj.job.focus();
//		return false;
//	}
//	//메일링 미선택시
//	if(obj.mailing.value==""){
//		alert("메일 수신 동의에 답해주세요.");
//		obj.mailing[0].focus();
//		return false;
//	}
//	//관심분야 미선택시
//	if(str==""){
//		alert("관심분야를 최소 1개를 선택해주세요.");
//		obj.interest[0].focus();
//		return false;
//	}
//	
//	
//	
////그 외 유효성 검사
//	//비밀번호 확인 틀릴시
//	if(obj.pw.value!=obj.pwCheck.value){
//		alert("비밀번호를 확인하세요.");
//		obj.pw.focus();
//		return false;
//	}
//	//비밀번호 7글자 이상
//	if(obj.pw.value.length<7){
//		alert("비밀번호는 7자 이상이어야 합니다..");
//		obj.pw.focus();
//		return false;
//	}
//	//주민번호 앞6자리 확인
//	if(obj.jumin1.value.length!=6){
//		alert("주민번호 앞자리는 6자여야 합니다.");
//		obj.jumin1.focus();
//		return false;
//	}
//	//주민번호 앞7자리 확인
//	if(obj.jumin2.value.length!=7){
//		alert("주민번호 뒷자리는 7자여야 합니다.");
//		obj.jumin2.focus();
//		return false;
//	}
//	//아이디 중복확인 버튼 안눌렀을때
//	if(obj.id.disabled==false){
//		if(idCheckButt==false){
//			alert("아이디 중복확인을 해주세요.");
//			obj.id.focus();
//			return false;
//		}
//	}
//	
	obj.resultInterest.value=str;
	
	// Spring에서는 JSP파일에서의 name과 Dto.java 파일내 필드명과 DB 테이블의 필드명 세개가 전부 동일해야 값을 주고받을 수 있다.
}

//아이디 중복체크 팝업으로 뜨게
//function idCheck(obj, root){	
//	var url=root+"/member/idCheck.do?id="+obj.id.value;
//	idCheckButt=true;
//	window.open(url,"","width=400, height=300");
//}
function idCheck(root){//아이디 중복 확인을 누르면 입력한 아이디를 가지고 들어가야한다.
   if($("input[name = 'id']").val() == ""){
      alert("아이디를 입력하세요");
      $("input[name = 'id']").focus();
      return false;
   }
   
   var url = root+"/member/idCheck.do?id=" + $("input[name = 'id']").val();
   //alert(url);
   
   window.open(url, "", "width = 250, height = 150");
}

//우편번호 검색 버튼 누르면 팝업뜨게
function zipcodeReader(root){
	var url=root+"/member/zipcode.do";
	window.open(url, "", "width=400, height=500, scrollbars=yes");
}

//우편번호 클릭시 일어나는 기능
function sendAddress(zipcode, sido, gugun, dong, ri, bunji){
	if(confirm("이 주소를 사용하겠습니까?")==true){
		var address=sido+gugun+dong+ri+bunji;
		//opener.createForm1.zipCode.value=zipcode;
		//opener.createForm1.addr.value=address;
		//window.close();
		$(opener.document).find("input[name='zipCode']").val(zipcode);
		$(opener.document).find("input[name='addr']").val(address);
		self.close();
	}else{
		return false;
	}
}