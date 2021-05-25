<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Kursy</title>
    <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
  </head>
  <body>
    <%@ include file="header.jsp" %>
    <h2 align="center">Kursy (linia ${routeName})</h2>
    <div class="container">
      <table class="table table-striped table-bordered">
        <thead class="thead-dark">
          <th scope="row">Id</th>
          <th scope="row">Typ</th>
          <th scope="row">Przystanki</th>
          <th scope="row">Edytuj</th>
          <th scope="row">Usuń</th>
        </thead>
        <tbody>
          <c:forEach items="${courses}" var="course" >
            <tr>
              <td>${course.id}</td>
              <td>${course.type}</td>
              <td>
                <c:forEach items="${course.busStops}" var="busStop" >
                  <div>${busStop.time} - ${busStop.busStopOnRoute.busStop.name}</div>
                </c:forEach>
              </td>
              <td>
                <spring:url value="/course/${course.id}/edit" var="editURL" />
                <a class="btn btn-info" href="${editURL}" role="button" >Edytuj</a>
              </td>
              <td>
                <spring:url value="/course/${course.id}/delete" var="deleteURL" />
                <a class="btn btn-danger" href="${deleteURL}" role="button" >Usuń</a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <spring:url value="/route/${routeId}/courses/add" var="addURL" />
      <a class="btn btn-success" href="${addURL}" role="button" >Dodaj kurs</a>
    </div>
  </body>
