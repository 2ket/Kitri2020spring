/**
 * http://usejsdoc.org/
 */
var root=null;

function selectToServer(bunho,requestRoot){
	root=requestRoot;
	
	var url=root+"/reply/replySelect.do";
	var param="bunho="+bunho;
	var getUrl=url+"?"+param;
	
	$.ajax({
		url:getUrl,
		type:"get",
		dataType:"text",
		success:selectUpdProcess
	});
	
}
function selectUpdProcess(data){
	var obj=$.parseJSON(data);
	var bunho=obj.bunho;
	var reply=obj.line_reply;
	//var user_ip=obj.user_ip;
	alert(bunho+" "+reply);
	var updateDiv="<div id='up"+bunho+"'>";
	updateDiv+="<input type='text' value='"+reply+"'>";
	updateDiv+="<input type='button' value='수정' id='updBtn'>";
	updateDiv+="</div>";
	
	updBunho="#"+bunho;
	$(updBunho).prepend(updateDiv);
}



//function selectFromServer(){
//	if(xhr.readyState==4 && xhr.status==200){
//		var result=xhr.responseText.split(",");
//		var bunho=result[0].trim();
//		var reply=result[1].trim();
//		
//		//arr.push("수정선택"+bunho+", "+reply);
//		
//		
//		
//		//새로운 Input 태그 생성
//		var div=document.createElement("div");
//		div.id="up"+bunho;
//		var inputText=document.createElement("input");
//		var inputBtn=document.createElement("input");
//		inputText.setAttribute("type", "text");
//		inputText.value=reply;
//		inputBtn.setAttribute("type", "button");
//		inputBtn.value="수정";
//		inputBtn.onclick=function(){
//			updateToServer(bunho, inputText.value);
//		}
//		
//		div.appendChild(inputText);
//		div.appendChild(inputBtn);
//		
//		var bunhoDiv=document.getElementById(bunho);
//		bunhoDiv.appendChild(div);
//		
//	}
//	
//}
//
//function updateToServer(bunho, value){
//	var url=root+"/reply/replyUpdate.do";
//	var param="bunho="+bunho+"&value="+value;
//	
//	sendRequest("POST", url, param, updateFromServer);
//}
//function updateFromServer(){
//	if(xhr.readyState==4 && xhr.status==200){
//		var result=xhr.responseText.split(",");
//		var bunho=result[0].trim();
//		var reply=result[1].trim();
//		
//		var bunhoDiv=document.getElementById(bunho);
//		
//		var span=bunhoDiv.getElementsByTagName("span");
//		
//		span[2].innerText=reply;
//		
//		var upDiv=document.getElementById("up"+bunho);
//		bunhoDiv.removeChild(upDiv);
//		
//	}
//	//alert(arr.join("\n"));
//}