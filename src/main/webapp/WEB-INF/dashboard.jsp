<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	<div class="container">
		<div class="banner">
			<div class="title">
				<h1>Welcome, <c:out value="${loggedUser.userName}"/>!</h1>
				<h4>Books from everyone's shelves:</h4>
			</div>
			<div class="links">
				<a href="http://localhost:8080/logout">Logout</a>
				<a href="http://localhost:8080/books/new">+ Add to my shelf!</a>
			</div>
		</div>
		<div class="table">
			<table class="table table-striped">
			  <thead>
			    <tr>
			      <th scope="col">ID</th>
			      <th scope="col">Title</th>
			      <th scope="col">Author Name</th>
			      <th scope="col">Posted By</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<c:forEach var="book" items="${books}">
				    <tr>
				      <td><c:out value="${book.id}"/></td>
				      <td><a href="http://localhost:8080/books/${book.id}"><c:out value="${book.title}"/></a></td>
				      <td><c:out value="${book.author}"/></td>
				      <td><c:out value="${book.user.userName}"/></td>
				    </tr>
				</c:forEach>
			  </tbody>
			</table>
		</div>
	</div>
</body>
</html>