<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/blog2/user?cmd=join" method="post"
		onsubmit="return valid()">
		<div class="d-flex justify-content-end">
			<button type="button" class="btn btn-info" onClick="usernameCheck()">중복체크</button>
		</div>

		<div class="form-group">
			<input type="text" name="username" id="username" class="form-control"
				placeholder="Enter Username" required />
		</div>

		<div class="form-group">
			<input type="password" name="password" class="form-control"
				placeholder="Enter Password" required />
		</div>
		<br/>
		<br/>
		
		<div class="form-group">
			<input type="name" name="name" class="form-control"
				placeholder="Enter name" required />
		</div>
		
		<div class="form-group">
			<input type="age" name="age" class="form-control"
				placeholder="Enter age" required />
		</div>
		
		<div class="form-group">
			<input type="residentnumber" name="residentnumber" class="form-control"
				placeholder="Enter residentnumber 주민번호" required />
		</div>

		<div class="d-flex justify-content-end">
			<button type="button" class="btn btn-info" onClick="goPopup();">주소검색</button>
		</div>
		<div class="form-group">
			<input type="text" name="address" id="address" class="form-control"
				placeholder="Enter Address" required readonly />
		</div>
		
		<div class="form-group">
			<select name="role">
         <option>user</option>
         <option>admin</option>
         </select>
		</div>
		
		<br />
		<button type="submit" class="btn btn-primary">회원가입완료</button>
	</form>
</div>

<script>
	var isChecking = false; //아직 가입을 할 수 없게 할려한다.
	function valid(){
		if(isChecking == false){
			alert("아이디 중복체크를 해주세요");
		}
		return isChecking;
	}
	function usernameCheck(){
		// DB에서 확인해서 정상이면 isChecking = true
		var username = $("#username").val();	//ajax가 가지고 있는 문법으로 username 값을 얻을 수 있음
		$.ajax({
			type: "POST",
			url: "/blog2/user?cmd=usernameCheck",
			data: username,
			contentType: "text/plain; charset=utf-8",
			dataType: "text"  // 응답 받을 데이터의 타입을 적으면 자바스크립트 오브젝트로 파싱해줌.
		}).done(function(data){
			if(data === 'ok'){ // 유저네임 있다는 것
				isChecking = false;
				alert('유저네임이 중복되었습니다.')
			}else{
				isChecking = true;
				$("#username").attr("readonly", "readonly");
				alert("해당 유저네임을 사용할 수 있습니다.")
			}
		});
	}

	function goPopup() {
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("/blog2/user/jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");
	}
	function jusoCallBack(roadFullAddr) {
		var addressEl = document.querySelector("#address");
		addressEl.value = roadFullAddr;
	}
</script>
</body>
</html>