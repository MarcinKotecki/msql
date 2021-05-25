<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Rozkład jazdy</title>
    <link href="/../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="/../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="/../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
  </head>
  <body>
    <%@ include file="header.jsp" %>
    <h2 align="center">Rozkład jazdy (Przystanek ${busStop.name})</h2>
    <div class="container">
      <c:forEach items="${timetables}" var="timetable" >
        <div class="container alert alert-secondary">
          <div class="row">
            <div class="col">
              <div class="container">
                <div class="row">
                  <c:forEach items="${timetable.times}" var="timegroup" >
                    <div class="col-sm">
                      <h5>${timegroup.x}</h5>
                      <table class="table table-striped table-bordered">
                        <thead class="thead-light">
                          <th style="width: 50px !important" scope="row">Godzina</th>
                          <th style="width: 200px !important" scope="row">Minuty</th>
                        </thead>
                        <tbody>
                          <c:forEach items="${timegroup.y}" var="time" >
                            <tr>
                              <td>${time.x}</td>
                              <td>
                                <c:forEach items="${time.y}" var="timey" >
                                  ${timey}
                                </c:forEach>
                              </td>
                            </tr>
                          </c:forEach>
                        </tbody>
                      </table>
                    </div>
                  </c:forEach>
                </div>
              </div>
            </div>
            <div class="col-md-auto">
              <p>
              <h3>Linia <b>${timetable.route.name}</b> </h3>
              <ul>
                <c:forEach items="${timetable.stops}" var="stop" >
                  <li>
                    <c:choose>
                      <c:when test="${stop.id==busStop.id}">
                        <h5><b> ${stop.name} </b></h5>
                      </c:when>
                      <c:otherwise>
                        ${stop.name}
                      </c:otherwise>
                    </c:choose>
                  </li>
                </c:forEach>
              </ul>
              </p>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </body>
</html>
