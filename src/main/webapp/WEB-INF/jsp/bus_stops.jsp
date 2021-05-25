<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Przystanki</title>
    <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
  </head>
  <body>
    <%@ include file="header.jsp" %>
    <h2 align="center">Przystanki</h2>
    <div class="container">
      <table class="table table-striped table-bordered">
        <thead class="thead-dark">
          <th scope="row">Id</th>
          <th scope="row">Nazwa</th>
          <th scope="row">Lokalizacja</th>
          <th scope="row">Rozkład jazdy</th>
          <th scope="row">Edytuj</th>
          <th scope="row">Usuń</th>
        </thead>
        <tbody>
          <c:forEach items="${busStops}" var="busStop" >
            <tr>
              <td>${busStop.id}</td>
              <td>${busStop.name}</td>
              <td>${busStop.location}</td>
              <td>
                <spring:url value="/bus_stop/${busStop.id}/timetable" var="timetableURL" />
                <a class="btn btn-info" href="${timetableURL}" role="button" >Rozkład jazdy</a>
              </td>
              <td>
                <spring:url value="/bus_stop/${busStop.id}/edit" var="editURL" />
                <a class="btn btn-info" href="${editURL}" role="button" >Edytuj</a>
              </td>
              <td>
                <spring:url value="/bus_stop/${busStop.id}/delete" var="deleteURL" />
                <a class="btn btn-danger" href="${deleteURL}" role="button" >Usuń</a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <spring:url value="/bus_stops/add" var="addURL" />
      <a class="btn btn-success" href="${addURL}" role="button" >Dodaj przystanek</a>
    </div>
  </body>

