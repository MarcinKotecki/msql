<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Kursy kierowcy</title>
    <link href="/../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="/../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="/../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
    <spring:url value="/driver/${driver.id}/course_assignments/?month=" var="filterURL" />
    <script>
      window.onload = function() {
          month_filter_select = document.getElementById("month_filter");
              month_filter_select.addEventListener("change", function() {
                  window.location.replace("${filterURL}" + month_filter_select.value);
              });
      };
    </script>
  </head>
  <body>
    <%@ include file="header.jsp" %>
    <h2 align="center">Kursy (kierowca ${driver.id}. ${driver.lastname} ${driver.firstname})</h2>
    <div class="container">
      <div>
        <div class="form-group">
          <label>Miesiąc</label>
          <select name="month_filter" id="month_filter">
            <option disabled selected value> -- wybierz -- </option>
            <option value="" >wszystkie</option>
            <c:forEach items="${months}" var="monthf" >
              <option value="${monthf}" >${monthf}</option>
            </c:forEach>
          </select>
        </div>
      </div>
      <table class="table table-striped table-bordered">
        <thead class="thead-dark">
          <th scope="row">Id</th>
          <th scope="row">Autobus</th>
          <th scope="row">Data</th>
          <th scope="row">Kurs</th>
          <th scope="row">Edytuj</th>
          <th scope="row">Usuń</th>
        </thead>
        <tbody>
          <c:forEach items="${courseAssignments}" var="courseAssignment" >
            <tr>
              <td>${courseAssignment.id}</td>
              <td>${courseAssignment.bus.registrationNumber}</td>
              <td>${courseAssignment.day}</td>
              <td>
                Linia ${courseAssignment.course.route.name}<br>
                Kurs ${courseAssignment.course.id}<br>
                <c:forEach items="${courseAssignment.course.busStops}" var="busStop" >
                  <div>${busStop.time} - ${busStop.busStopOnRoute.busStop.name}</div>
                </c:forEach>
              </td>
              <td>
                <spring:url value="/course_assignment/${courseAssignment.id}/edit" var="editURL" />
                <a class="btn btn-info" href="${editURL}" role="button" >Edytuj</a>
              </td>
              <td>
                <spring:url value="/course_assignment/${courseAssignment.id}/delete" var="deleteURL" />
                <a class="btn btn-danger" href="${deleteURL}" role="button" >Usuń</a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <spring:url value="/driver/${driver.id}/course_assignments/add" var="addURL" />
      <a class="btn btn-success" href="${addURL}" role="button" >Dodaj kurs</a>
    </div>
  </body>
