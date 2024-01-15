<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/header.jsp"></jsp:include>
<title>회원가입</title>

	<h1>member Register Page</h1>
	
	<!-- email,pwd,nickname -->
	
<form action="/member/register" method="post">
	<div class="mb-3">
  <label for="e" class="form-label">Email</label>
  <input type="email"  name="email" class="form-control" id="e" placeholder="email">
</div>
<div class="mb-3">
  <label for="p" class="form-label">Password</label>
  <input type="password"  name="pwd" class="form-control" id="p">
</div>
<div class="mb-3">
  <label for="n" class="form-label">NickName</label>
  <input type="text" name="nickname" class="form-control" id="n" placeholder="NickName">
</div>
<button type="submit" class="btn btn-dark">회원가입</button>
</form>
	
	
<jsp:include page="../layout/footer.jsp"></jsp:include>
</html>