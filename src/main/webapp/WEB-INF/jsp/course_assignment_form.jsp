<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>${action} kurs</title>
    <link href="/../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="/../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="/../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
  </head>
  <body>
    <%@ include file="header.jsp" %>
    <div class="container">
      <c:if test="${collision.isPresent()}">
        <div>
          <div class="alert alert-danger" role="alert">
            Nie można wykonać takiego przypisania z powodu poniższej kolizji:
          </div>
          <table class="table table-striped table-bordered">
            <thead class="thead-dark">
              <th scope="row"></th>
              <th scope="row">Id</th>
              <th scope="row">Autobus</th>
              <th scope="row">Kierowca</th>
              <th scope="row">Dzień</th>
              <th scope="row">Kurs</th>
            </thead>
            <tbody>
              <tr>
                <td>Przypisanie<br>z bazy</td>
                <td>${collision.get().id}</td>
                <td>${collision.get().bus.registrationNumber}</td>
                <td>${collision.get().driver.lastname} ${collision.get().driver.firstname}</td>
                <td>${collision.get().day}</td>
                <td>
                  Linia ${collision.get().course.route.name}<br>
                  Kurs ${collision.get().course.id}<br>
                  <c:forEach items="${collision.get().course.busStops}" var="busStop" >
                    <div>${busStop.time} - ${busStop.busStopOnRoute.busStop.name}</div>
                  </c:forEach>
                </td>
              </tr>
              <tr>
                <td>Przypisanie<br>nowe</td>
                <td>${assignment.get().id}</td>
                <td>${assignment.get().bus.registrationNumber}</td>
                <td>${assignment.get().driver.lastname} ${assignment.get().driver.firstname}</td>
                <td>${assignment.get().day}</td>
                <td>
                  Linia ${assignment.get().course.route.name}<br>
                  Kurs ${assignment.get().course.id}<br>
                  <c:forEach items="${assignment.get().course.busStops}" var="busStop" >
                    <div>${busStop.time} - ${busStop.busStopOnRoute.busStop.name}</div>
                  </c:forEach>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </c:if>
      <spring:url value="/driver/${driverId}/course_assignments/add" var="addURL" />
      <h2>${action} kurs</h2>
      <form:form modelAttribute="courseAssignmentForm" method="post" action="${addURL}" cssClass="form" >
        <form:hidden path="id"/>
        <form:hidden path="driver" value="${driverId}"/>
        <div class="form-group">
          <label>Kurs</label>
          <form:select path="course">
            <form:options items="${courses}" itemLabel="routeIdCourseId"></form:options>
          </form:select>
        </div>
        <div class="form-group">
          <label>Autobus</label>
          <form:select path="bus">
            <form:options items="${buses}" itemLabel="registrationNumber"></form:options>
          </form:select>
        </div>
        <div class="form-group">
          <label>Dzień (yyyy-mm-dd)</label>
          <form:input path="day"  id="day" />
        </div>
        <button type="submit" class="btn btn-success">Zapisz</button>
      </form:form>
    </div>
  </body>
</html>
