<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit An Existing Household</title>
</head>
<body>
<form action ="editHouseholdDetailsServlet" method="post">
<input type ="hidden" name ="id" value="${householdToEdit.id}">
Household Name: <input type ="text" name ="householdName" value="${householdToEdit.householdName}"><br/>
Owner Name: <input type ="text" name ="ownerName" value="${householdToEdit.owner.ownerName}"><br/>
Available Items:<br/>
<select name="allPetsToAdd" multiple size="6">
<c:forEach items="${requestScope.allPets}" var="currentitem">
<option value ="${currentitem.id}">
${currentitem.type} |
${currentitem.name}
</option>
</c:forEach>
</select><br/>
<input type ="submit" value="Edit Household and Add Pets">
</form>
<a href ="index.html">Go add new pets instead.</a>
</body>
</html>