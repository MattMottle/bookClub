<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Book</title>
<link rel="stylesheet" type="text/css" href="/css/form.css">
</head>
<body>
	<div class="container">
	<h1>Edit Book</h1>
		<div class="form">
				<form:form action="/books/${book.id}/edit/process" method="post" modelAttribute="book">
				 <input type="hidden" name="_method" value="put">
					<div style="color: red;"><form:errors path="title"/></div>
					<div style="color: red;"><form:errors path="author"/></div>
					<div style="color: red;"><form:errors path="myThoughts"/></div>
					<p>
				        <form:label path="title">Title:</form:label>
				        <form:input path="title"/>
				    </p>
				    <p>
				        <form:label path="author">Author:</form:label>
				        <form:input path="author"/>
				    </p>
				    <p>
				        <form:label path="myThoughts">My thoughts:</form:label>
				        <form:textarea rows="4" path="myThoughts"/>     
				    </p>
				    <form:input type="hidden" path="user" value="${user.id}"/>
				    <form:input type="hidden" path="id" value="${book.id}"/>    
				    <button class="button">Submit</button>
				</form:form>
			</div>
		<a href="http://localhost:8080/books">Home</a>
		</div> 
</body>
</html>