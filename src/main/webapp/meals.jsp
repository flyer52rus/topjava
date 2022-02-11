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
<form action="" method="post" >
    <button name="add" value="true" >Add</button>
</form>
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
            <td>
                <form action="" method="post" >
                    <button name="edit" value="${mealTo.id}" >Edit</button>
                </form>
            </td>
            <td>
                <form action="" method="post" >
                    <button name="delete" value="${mealTo.id}" >Delete</button>
                </form>
            </td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
