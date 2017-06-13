<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css"/>
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css"/>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous"/>
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet"/>
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
      .bgimg {
        background-image: url("static/img/bg.svg");
        background-repeat: no-repeat;
        background-position: 0 0 0 0;
        background-size: 100% auto;
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
    <div class="container-fluid bgimg pad flex-container">
      <div class="row pad">
        <div class="col-sm-7">
          <h1>Moving Demo</h1>

          <p>This is a moving demo program. It will provide you a shared moving fast experiences</p>

        </div>
        <div class="col-sm-5 has-error">
          <c:url value="${request.contextPath}/login" var="theAction"/>
          <form:form action="${theAction}" method="POST" modelAttribute="loginUser" class="form-group">
            <form:errors path="*" cssClass="text-warning"></form:errors>
            <div class="text-warning">
              User Name or Password is not right.
            </div>

            <label class="form-control-static">User Name</label>
            <form:input type="text" path="username" cssClass="form-control" placeholder="Input a username"/>

            <label class="form-control-static">Password</label>
            <form:password path="password" class="form-control" placeholder="Input a password"/>

            <br/>
            <input type="submit" class="btn btn-success form-control" value="Sign In"/>
          </form:form>
        </div>
      </div>
    </div>

  </body>

</html>
