/**
 * http://usejsdoc.org/
 */

function deleteToServer(bunho, root){
	//자바스크립트에서의 요청
	var url=root+"/reply/replyDelete.do";
	var param="bunho="+bunho;
	
	var getUrl=url+"?"+param;
	
	$.ajax({
		url:getUrl,
		type:"get",
		dataType:"text",
		success:deleteProcess
	});
}

function deleteProcess(data){
	var obj=$.parseJSON(data);
	//alert("삭제됨:"+obj.bunho);
	
	var bunho="#"+obj.bunho;
	
	$(bunho).remove();
}
