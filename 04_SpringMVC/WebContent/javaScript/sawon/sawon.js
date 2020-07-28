/**
 * http://usejsdoc.org/
 */

var xhr=null;
var arr=null;
function startRequest(){
	if(window.XMLHttpRequest){
		xhr=new XMLHttpRequest;
	}else{
		xhr=new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function toServer(root){
	arr=new Array();
	
	var departmentName=document.getElementById("sawonForm").departmentName.value;
	//arr.push(departmentName);
	//alert(arr.join("\n"));
	
	//요청이 항상 root를 통해 넘어가므로 root도 같이 넘겨줘야한다. 함수 파라미터로 root 넣어준다
	if(departmentName != ""){
		startRequest();
		url=root+"/sawon/listOk.do";
		
		xhr.open("POST", url, true);
		xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
		xhr.send("departmentName="+departmentName);
		xhr.onreadystatechange=resultProcess;
	}
}
function resultProcess(){
	
	if(xhr.readyState==4 && xhr.status==200){
		arr.push(xhr.responseText);
		
		var resultDisp=document.getElementById("resultDisp");
		resultDisp.innerHTML=xhr.responseText;
	}
	//alert(arr.join("\n"));
}