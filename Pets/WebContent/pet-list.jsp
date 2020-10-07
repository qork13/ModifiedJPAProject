<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show All Pets</title>
</head>
<body>
<!-- Create form to send to navigationServlet -->
<form method ="post" action ="navigationServlet">
<!-- Table to display all the pets in the mysql/AWS table as options to select in radio buttons-->
<table>
<c:forEach items="${requestScope.allPets}" var="currentpet">
<tr>
<td><input type="radio" name="id" value="${currentpet.id}"></td>
<td>${currentpet.type}</td>
<td>${currentpet.name}</td>
</tr>
</c:forEach>
</table>
<!-- Buttons to edit, delete, and add new pet -->
<input type ="submit" value ="edit" name="doThisToPet">
<input type ="submit" value ="delete" name="doThisToPet">
<input type="submit" value ="add" name ="doThisToPet">
</body>
</html>