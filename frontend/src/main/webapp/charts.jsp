<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>

<div class="sensor_table">
    <h1>All sensor stats</h1>
    <table>
        <c:forEach items="${sensors}" var="sensor">
            <tr>
                <td>${sensor.id}</td>
                <td>${sensor.details.title}</td>
                <td>${sensor.details.description}</td>
                <td>${sensor.details.location}</td>
                <td>
                    <c:forEach items="${sensor.features}" var="feature">
                        ${feature.key}: ${feature.value.type}<br/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="/WEB-INF/jspf/counters.jspf" %>
<%@include file="/WEB-INF/jspf/footer.jspf" %>