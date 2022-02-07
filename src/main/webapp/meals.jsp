<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<table border="3">
    <tr>
        <th >Date</th>
        <th >Description</td>
        <th >Calories</td>
    </tr>
    <c:forEach items="${mealsTo}" var="mealsTo">
        <tr style="color: ${mealsTo.excess ? 'red' : 'green'}">
            <td> ${mealsTo.dateTime} </td>
            <td> ${mealsTo.description} </td>
            <td> ${mealsTo.calories} </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

