<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book</title>
<link rel="stylesheet" type="text/css" href="/css/book.css">
</head>
<body>
	<div class="container">
		<div class="banner">
			<h1>${book.title}</h1>
			<a href="http://localhost:8080/books">Back to the shelves</a>
		</div>
		<div class="details">
			<c:choose>
				<c:when test="${loggedUser.id == book.user.id}">
					<h3>You read <span style="color: purple"> ${book.title}</span> by <span style="color: green">${book.author}.</span></h3>
					<h3>Here are your thoughts:</h3>
				</c:when>
				<c:otherwise>
					<h3><span style="color: red">${book.user.userName}</span> read <span style="color: purple"> ${book.title}</span> by <span style="color: green">${book.author}.</span></h3>
					<h3>Here are ${book.user.userName}'s thoughts:</h3>
				</c:otherwise>
			</c:choose>
			<p>${book.myThoughts}</p>
		</div>
		<c:if test="${loggedUser.id == book.user.id}">
			<div class="actions">
				<form action="/books/${book.id}/edit" method="get">
					<input type="hidden" value="Edit"/>
					<button class="edit">Edit</button>
				</form>
				<form action="/${book.id}/delete" method="post">
			      	<input type="hidden" name="_method" value="delete" />
			      	<button class="delete">Delete</button>
			    </form>
			</div>
		</c:if>
	</div>
</body>
</html>