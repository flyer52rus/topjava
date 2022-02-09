<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>

<html lang="ru">
<head>
    <title>EditMeal</title>
</head>
<body>
<br>
<h2> Edit Meal </h2>
<br>
<br>
<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<form method="post" action="meals">
    <dl>
        <dt>Date and Time: </dt>
        <dd><input type="datetime-local" name="dateTime" value="${meal.dateTime}" placeholder="${meal.id}"></dd>
        <dt> Обязательный формат даты: yyyy-mm-ddTHH:mm </dt>
    </dl>
    <dl>
        <dt>Description</dt>
        <dd><input type="text" name="description" value="${meal.description}" placeholder="${meal.description}"></dd>
    </dl>
    <dl>
        <dt>Calories</dt>
        <dd><input type="number" name="calories" value="${meal.calories}" placeholder="${meal.calories}"></dd>
    </dl>
    <a><input type="hidden" name="id" value="${meal.id}"></a>
    <button type="submit">Save</button>
</form>
</body>
</html>




