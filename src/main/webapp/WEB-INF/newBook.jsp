<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Book</title>
<link rel="stylesheet" type="text/css" href="/css/form.css">
</head>
<body>
	<div class="container">
		<h1>Add a Book To Your Shelf!</h1>
		<form:form action="/books/new/process" method="post" modelAttribute="book">
			<div style="color: red"><form:errors path="title"/></div>
			<div style="color: red"><form:errors path="author"/></div>
			<div style="color: red"><form:errors path="myThoughts"/></div>
			<p>
				<form:label path="title">Title:</form:label>
				<form:input path="title"/>
			</p>
			<p>
				<form:label path="author">Author:</form:label>
				<form:input path="author"/>
			</p>
			<p>
				<form:label path="myThoughts">My Thoughts:</form:label>
				<form:textarea rows="4" path="myThoughts"/>
			</p>
			<p>
				<form:input type="hidden" path="user" value="${user.id}"/>
			</p>
			<button class="button">Create</button>
		</form:form>
		<a href="http://localhost:8080/books">Home</a>
	</div>
</body>
</html>