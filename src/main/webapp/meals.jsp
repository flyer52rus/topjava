<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<br>
<a href="edit?id=-1" > Add Meal </a>
<br>
<table border="3">
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach items="${mealsTo}" var="mealTo">
        <tr style="color: ${mealTo.excess ? 'red' : 'green'}">
            <td> ${fn:replace(mealTo.dateTime, 'T', ' ')} </td>
            <td> ${mealTo.description} </td>
            <td> ${mealTo.calories} </td>
            <td> <a href="edit?id=${mealTo.id}" > Edit </a> </td>
            <td> <a href="delete?id=${mealTo.id}"> Delete </a> </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
