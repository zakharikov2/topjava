<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<form method="get">
    <table border="1">

        <th>Id</th>
        <th>Дата, Время</th>
        <th>Описание</th>
        <th>Каллории</th>

        <jsp:useBean id="meals" scope="request" type="java.util.List"/>
        <c:forEach var="meal" items="${meals}">
            <tr style="color:${meal.excess ? 'red' : 'green'}">
                <td>${meal.id}</td>
                <td>${meal.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
                <td align="center">${meal.description}</td>
                <td align="center">${meal.calories}</td>
            </tr>
        </c:forEach>
    </table>
</form>

<h2>CRUD operations</h2>

<form method="post">
    <label>Id:
        <input type="number" name="idToUpdate">
    </label>
    <label>Дата, Время:
        <input type="datetime-local" name="dateTime" required>
    </label>
    <label>Описание:
        <input type="text" name="description" required>
    </label>
    <label>Каллории:
        <input type="number" name="calories" required>
    </label>
    <button type="submit">Добавить/Изменить</button>
</form>

<form method="post">
    <label>Id:
        <input type="number" name="idToDelete" required>
    </label>
    <button type="submit">Удалить</button>
</form>

</body>
</html>