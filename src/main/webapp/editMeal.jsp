<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>

<html lang="ru">
<head>
    <title>${id != null ? "EditMeal" : "AddMeal" }</title>
</head>
<body>
<br>
<h2> ${id != null ? "Edit Meal" : "Add Meal" } </h2>
<br>
<hr>
<br>
<form method="post" action="meals">
    <dl>
        <dt>Date and Time: </dt>
        <dd><input type="datetime-local" name="dateTime" value="${meal.dateTime == null ? "" : meal.dateTime}" placeholder="${meal.dateTime}"></dd>
        <dt> Обязательный формат даты: yyyy-mm-ddTHH:mm </dt>
    </dl>
    <dl>
        <dt>Description</dt>
        <dd><input type="text" name="description" value="${meal.description == null ? "" : meal.description}" placeholder="${meal.description}"></dd>
    </dl>
    <dl>
        <dt>Calories</dt>
        <dd><input type="number" name="calories" value="${meal.calories == null ? "" : meal.calories}" placeholder="${meal.calories}"></dd>
    </dl>
    <a><input type="hidden" name="id" value="${id != null ? id : null}"></a>
    <button type="submit" name="save" value="true">Save</button> <button name="cancel" value="true" >Cancel</button>
</form>
</body>
</html>




