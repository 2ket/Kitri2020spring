/**
 * http://usejsdoc.org/
 */

var arr=new Array();
var root=null;
function writeToServer(requestRoot){
	root=requestRoot;
	var writeReply=$("#writeReply").val();
	var param="writeReply="+writeReply;
	var url=root+"/reply/replyWrite.do?"+param;
	arr.push(url);
	//alert(arr);
	
	$.ajax({
		url:url,
		type:"get",
		dataType:"text",
		success:writeProcess
	});
	
	
	function writeProcess(data){
		var obj=$.parseJSON(data);
		arr.push(obj.bunho);
		arr.push(obj.writeReply);
		//arr.push(obj.user_ip);
		//alert(arr.join("\n"));
		
		var bunho=obj.bunho;
		var reply=obj.writeReply;
		//var user_ip=obj.user_ip;
		
		$("#writeReply").val("");
		var newReplyText="<div class='replyDiv' id='"+bunho+"'>";
		newReplyText+="<span class='cssBunho'>"+bunho+"</span>";
		newReplyText+="<span class='cssReply'>"+reply+"</span>";
		//newReplyText+="<span class='cssIp'>"+user_ip+"</span>";
		newReplyText+="<span class='cssUpDel'>";
		newReplyText+="<a href='javascript:deleteToServer("+bunho+",\""+root+"\")'>삭제</a> &nbsp";
		
		newReplyText+="<a href='javascript:selectToServer(" + bunho + ",\"" + root + "\")'>수정</a>";
		newReplyText+="</span>";
		newReplyText+="</div>";
		
		$("#listAllDiv").prepend(newReplyText);
	}
	
}

//function writeFromServer(){
//	if(xhr.readyState==4 && xhr.status==200){

//		
//		var spanUpDel=document.createElement("span");
//		spanUpDel.className="cssUpDel";
//		
//		var aDelete=document.createElement("a");
//		//aDelete.href="javascript:deleteToServer('"+bunho+"', '"+root+"')";
//		//aDelete.style="color:blue;cursor:pointer;";
//		aDelete.href='#';
//		aDelete.innerHTML="삭제 &nbsp;";
//		aDelete.onclick=function(){
//			deleteToServer(bunho,root);
//		};
//		
//		
//		var aUpdate=document.createElement("a");
//		aUpdate.href='#';
//		aUpdate.innerHTML="수정";
//		aUpdate.onclick=function(){
//			selectToServer(bunho,root);
//		};
//		spanUpDel.appendChild(aDelete);
//		spanUpDel.appendChild(aUpdate);
//		
//		div.appendChild(spanBunho);
//		div.appendChild(spanReply);
//		div.appendChild(spanIp);
//		div.appendChild(spanUpDel);
//		listAllDiv.appendChild(div);
//		listAllDiv.insertBefore(div, listAllDiv.firstChild);
//		//alert(arr.join("\n"));
//	}
//}