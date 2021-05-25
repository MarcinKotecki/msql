<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>${action} linię</title>
    <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
    <script>
      stops = 1

      function nextStop() {
        var elemTemp = document.querySelector('#busStopTemplate');
        var elemNew = elemTemp.cloneNode(true);
        elemNew.id = 'busStop' + stops;
        elemNew.name = 'busStop' + stops;
        elemNew.style = '';

        var div = document.createElement("div");
        div.id = "busStopD" + stops;
        div.appendChild(elemNew);

        var elemLast = document.querySelector('#busStopD' + (stops-1));
        elemLast.after(div);
        stops += 1;
      }
    </script>
  </head>
  <body>
    <%@ include file="header.jsp" %>
    <div class="container">
      <h2>${action} linię</h2>
      <spring:url value="/routes/add" var="addURL" />
      <form method="post" action="${addURL}" >
        <hidden path="id"/>
        <div class="form-group">
          <label>Nazwa</label>
          <input name="name" id="name"/>
        </div>
        <div class="form-group">
          <label>Typ</label>
          <input name="type" id="type"/>
        </div>
        <div class="form-group">
          <label>Przystanki</label>
          <select name="busStopTemplate" id="busStopTemplate" style="visibility: hidden;">
            <option hidden disabled selected value> -- wybierz przystanek -- </option>
            <c:forEach items="${busStops}" var="busStop" >
              <option value="${busStop.id}" >${busStop.name}</option>
            </c:forEach>
          </select>
          <br>
          <div id="busStopD0">
            <select name="busStop0" id="busStop0">
              <option hidden disabled selected value> -- wybierz przystanek -- </option>
              <c:forEach items="${busStops}" var="busStop" >
                <option value="${busStop.id}" >${busStop.name}</option>
              </c:forEach>
            </select>
          </div>
          <div>
            <input onclick="nextStop()" type="button" value="+"/>
          </div>
        </div>
        <button type="submit" class="btn btn-success">Zapisz</button>
      </form>
    </div>
  </body>
</html>
