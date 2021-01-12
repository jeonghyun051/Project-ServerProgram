function deleteList(username){
	// 세션의 유저의 id와 reply의 userId를 비교해서 같을때만!!
	//alert("댓글 아이디 : "+id);
	
	$.ajax({
		type : "post",
		url : "/blog2/user?cmd=delete&username="+username


	}).done(function(result) { 
		if (result == 1) {
			console.log(result);
			$("#List-"+username).remove();
		} else {
			alert("댓글삭제 실패");
		}
	});
}