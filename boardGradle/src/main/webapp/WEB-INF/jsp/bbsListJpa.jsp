<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>게시판</title>
<style>
	article { padding :10px; }
</style>
</head>
<body>
  <article>
    <h2>게시판 만들기</h2>
    <br>
	<table  class="table table-striped">
	  <thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
	  </thead>
	  <tbody id="tableBody">
		<c:forEach var="list" items="${list}">
		<tr>
			<td>${list.seq}</td>
			<td><a href="/jpa/detail/${list.seq}">${list.title}</a></td>
			<td>${list.user}</td>
		</tr>
		</c:forEach>
	  </tbody>
	</table>
	
	
	<div class="row" style="width:100%; text-align:right; padding:10px;">
	    <div class="col-sm-4"> </div>
	    <div class="col-sm-4"> </div>
	    <div class="col-sm-4">
	    	<button type="button" class="btn btn-primary" id="btnWrite">글쓰기</button>
	    </div>
	</div>
	
		
  </article>
  <script>
	$(document).ready(function(){
		$("#btnWrite").click(function(){
			location.href = "/jpa/add";
		});
	});
    
  </script>
</body>
</html>
