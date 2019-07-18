
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }
        .excess {
            color: red;
        }
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }
        dt {
            display: inline-block;
            width: 170px;
        }
        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meals</h2>

    <form method="get">
        <table border="0">
            <td>
                <dl>
                    <dt>From date:</dt>
                    <dd><input type="date" value="" name="dateFrom"></dd>
                </dl>
                <dl>
                    <dt>To date:</dt>
                    <dd><input type="date" value="" name="dateTo"></dd>
                </dl>
            </td>
            <td>
                <dl>
                    <dt>From time:</dt>
                    <dd><input type="time" value="" name="timeFrom"></dd>
                </dl>
                <dl>
                    <dt>To time:</dt>
                    <dd><input type="time" value="" name="timeTo"></dd>
                </dl>
            </td>
        </table>
        <input type="hidden" name="action" value="dateTimeFilter">
        <button type="submit">Filter</button>
        <input value="Cancel" type="button" onclick="location.href='meals'"/>
    </form>

    <a href="meals?action=create&userId=1">Add Meal</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <jsp:useBean id="meals" scope="request" type="java.util.List"/>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}&userId=1">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}&userId=1">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>