<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h3>유저 list 페이지입니다</h3>
	<div class="m-2">
		<form class="form-inline d-flex justify-content-end"
			action="/blog/user">
			<input type="hidden" name="cmd" value="search" /> <input
				type="hidden" name="page" value="0" /> <input type="text"
				name="keyword" class="form-control mr-sm-2" placeholder="Search">
			<button class="btn btn-primary m-1">검색</button>

		</form>
	</div>
	<div class="progress col-md-12 m-2">
		<div class="progress-bar" style="width: 100"></div>
	</div>

	<c:forEach var="userList" items="${userList}" varStatus="stauts">
		<div class="card col-md-12 m-2" id="List-${userList.username}">
			<div class="card-body">
				<h4 class="card-title">닉네임 : ${userList.username}</h4>
				<h6 class="card-title">이름 : ${userList.name}</h6>
				<h6 class="card-title">권한 : ${userList.userRole }</h6>
				<h6 class="card-title">주소 : ${userList.address}</h6>

				<c:choose>
					<c:when test="${sessionScope.principalAdmin.username != null}">
						<a onclick="deleteList('${userList.username}')" class="btn btn-primary">삭제하기</a>
					</c:when>
					
					<c:when test="${sessionScope.principal.username == userList.username}">
						<a onclick="deleteList('${userList.username}')" class="btn btn-primary">삭제하기</a>
					</c:when>

				</c:choose>
			</div>
			<br />
		</div>

	</c:forEach>

</div>
<script>

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
</script>

</body>
</html>