<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>${action} kurs</title>
    <link href="/../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
  </head>
  <body>
    <%@ include file="header.jsp" %>
    <div class="container">
      <h2>${action} kurs</h2>
      <spring:url value="/route/${routeId}/courses/add" var="addURL" />
      <form method="post" action="${addURL}" >
        <input type="hidden" id="routeId" name="routeId" value="${routeId}">
        <div class="form-group">
          <label>Typ</label>
          <select name="type" id="type">
            <option value="dni robocze" >dni robocze</option>
            <option value="soboty" >soboty</option>
            <option value="niedziele/święta" >niedziele/święta</option>
          </select>
        </div>
        <div class="form-group">
          <label>Przystanki</label>
          <c:forEach items="${busStops}" var="busStop" >
            <div>
              <label>${busStop.busStop.name}</label>
              <input name="bs${busStop.id}" id="bs${busStop.id}"/>
            </div>
          </c:forEach>
        </div>
        <button type="submit" class="btn btn-success">Zapisz</button>
      </form>
    </div>
  </body>
</html>
