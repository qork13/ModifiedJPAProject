<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Household Lists</title>
</head>
<body>
<form method ="post"action ="householdNavigationServlet">
<table>
<c:forEach items="${requestScope.allHouseholds}" var="currentlist">
<tr>
<td><input type="radio" name="id" value="${currentlist.id}"></td>
<td><h2>${currentlist.householdName}</h2></td></tr>
<tr><td colspan="3">Owner:${currentlist.owner.ownerName}</td></tr>
	<c:forEach var ="listVal" items ="${currentlist.listOfPets}">
		<tr><td></td><td colspan="3">${listVal.type}, ${listVal.name}</td></tr>
	</c:forEach>
</c:forEach>
</table>
<input type ="submit" value ="edit" name="doThisToHousehold">
<input type ="submit" value ="delete" name="doThisToHousehold">
<input type="submit" value ="add" name ="doThisToHousehold">
</form>
<a href="addHouseholdServlet">Create a new Household</a>
<a href="index.html">Insert a new item</a>
</body>
</html>