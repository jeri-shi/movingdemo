
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css" />
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css" />
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous" />
  <!-- Optional theme -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous" />
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" />
  <!-- Latest compiled and minified JavaScript -->
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  <title>Moving Demo</title>
  <style>
    body {
      font: 400 15px Lato, sans-serif;
      line-height: 1.8;
      color: white;
    }

    .pad {
      padding-top: 120px;
      padding-bottom: 120px;
    }

    .flex-container {
      display: flex;
      justify-content: center;
    }
  </style>
</head>

<body style="background-color: #338055">
  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#demo">
          <span class="glyphicon glyphicon-menu-hamburger"></span>
        </button>
        <a class="navbar-brand" style="padding: 0 0 0 0" href="#">
          <img class="img-responsive" height="64" width="64" src="${request.contextPath}static/img/moving.png" alt="Moving"/>
        </a>
      </div>

      <div class="collapse navbar-collapse" id="demo">
        <ul class="nav navbar-nav">
          <li class="active"><a href="#">Current Tasks</a></li>
          <li><a href="#">Dashboard</a></li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Management <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li class="dropdown-header">Daily Activities</li>
              <li><a href="#">Champin</a></li>
              <li><a href="#">Coupon</a></li>
              <li><a href="#">Price</a></li>
              <li><a href="#">Orders</a></li>
              <li class="divider"></li>
              <li class="dropdown-header">Objects Management</li>
              <li><a href="#">Cars</a></li>
              <li><a href="#">Bicicyles</a></li>
              <li><a href="#">Chargings</a></li>
              <li><a href="#">Umbrellas</a></li>
              <li class="divider"></li>
              <li class="dropdown-header">User Management</li>
              <li><a href="#">Users</a></li>
              <li class="divider"></li>
              <li class="dropdown-header">Admin Management</li>
              <li><a href="#">Employees</a></li>
              <li><a href="#">Roles</a></li>
              <li><a href="#">Stations</a></li>
            </ul>
          </li>
        </ul>
        <!--form class="navbar-form">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="Search"  />
            <button type="submit" class="btn btn-default">Search</button>
          </div>
        </form-->
        <c:url value="${request.contextPath}/logout" var="theAction"/>
        <ul class="nav navbar-nav navbar-right">
          <li>
            <a href="#"><span class="glyphicon glyphicon-user"></span> ${sessionUserName}</a>
          </li>
          <li>
              <form:form action="${theAction}" class="navbar-form">
                <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-share"></span> <spring:message code="jsp.home.signout" /></button>
              </form:form>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  Home + <sec:authentication property="principal"/>
  
  <br/>
  <p>SecurityContext: <sec:authentication property="principal"/> </p>
  <p>Session Context: <c:out value="${sessionUserName}"/></p>
  <p>Radio : <c:out value="${radio}" /> </p>
</body>

</html>
