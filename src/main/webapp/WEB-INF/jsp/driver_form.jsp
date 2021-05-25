<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>${action} kierowcę</title>
    <link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
  </head>
  <body>
    <%@ include file="header.jsp" %>
    <div class="container">
      <spring:url value="/driver/add" var="addURL" />
      <h2>${action} kierowcę</h2>
      <form:form modelAttribute="driverForm" method="post" action="${addURL}" cssClass="form" >
        <form:hidden path="id"/>
        <div class="form-group">
          <label>Nazwisko</label>
          <form:input path="lastname"  id="lastname" />
        </div>
        <div class="form-group">
          <label>Imię</label>
          <form:input path="firstname"  id="firstname" />
        </div>
        <button type="submit" class="btn btn-success">Zapisz</button>
      </form:form>
    </div>
  </body>
</html>
