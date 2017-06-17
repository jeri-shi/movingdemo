<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <sec:csrfMetaTags/>
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
    <title><spring:message code="jsp.index.title"/></title>

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
        padding-bottom: 80px;
      }
      .flex-container {
        display: flex;
        justify-content: center;
      }
      .bg-grey {
        background-color: grey;
      }
      
      .flags-group {
      	display:inline-flex !important;
      }
      .flags {
        display: block;
        margin: 5px;
        padding: 10px;
        width: 48px;
        height: 32px;
        background: url("static/img/flags.png");
        box-shadow: 2px 2px 5px #403f3f;
      }
      .small-flags {
        display: block;
        margin: 5px;
        padding: 10px;
        width: 33px;
        height: 22px;
        background: url("static/img/flags.png");
        background-size: 350px 210px;
        box-shadow: 1px 1px 3px #403f3f;
      }
      .flags-group > a >span:hover {
        box-shadow: 3px 3px 5px #301f1f;
      }
      .iceland-flag {
        background-position: -1px -1px;
      }
      .china-flag {
        background-position: -151px -172px;
      }
      .us-flag {
        background-position: -351px -87px;
      }

      .iceland-flag-small {
        background-position: -1px -1px;
      }
      .china-flag-small {
        background-position: -106px -121px;
      }
      .us-flag-small {
        background-position: -246px -61px;
      }
      .franch-flag-small {
        background-position: -281px -31px;
      }

    </style>
  </head>

  <body style="background-color: #338055">
    <div class="container-fluid bgimg pad flex-container">
      <div class="row pad">
        <div class="col-sm-7">
          <h1><spring:message code="jsp.index.title"/></h1>

          <p><spring:message code="jsp.index.description"/></p>

        </div>
        <div class="col-sm-5 has-error">
          <c:url value="${request.contextPath}/login" var="theAction"/>
          <form:form action="${theAction}" method="POST" modelAttribute="loginUser" class="form-group">
            <form:errors path="*" cssClass="text-warning"></form:errors>
            <label class="form-control-static"><spring:message code="jsp.index.username"/></label>
            <spring:message code='jsp.index.username.hint' var="username_hint"/>
            <form:input type="text" path="username" cssClass="form-control" placeholder="${username_hint}" />

            <label class="form-control-static"><spring:message code="jsp.index.password"/></label>
            <spring:message code='jsp.index.password.hint' var="password_hint"/>
            <form:password path="password" class="form-control" placeholder="${password_hint}"/>

            <br/>
            <spring:message code='jsp.index.button.submit' var="button_sumit"/>
            <input type="submit" class="btn btn-success form-control" value="${button_sumit}" />
          </form:form>
        </div>
      </div>
    </div>
    <div class="container-fluid flex-container">
      <div class="row">
        <div class="col-sm-12 flags-group">
          <a href="${theAction}?lang=en">
            <span class="small-flags us-flag-small"></span>
          </a>
          <a href="${theAction}?lang=zh" class="flag">
            <span class="small-flags china-flag-small"></span>
          </a>
          <a href="${theAction}?lang=is">
            <span class="small-flags iceland-flag-small"></span>
          </a>
          <a href="${theAction}?lang=fr">
            <span class="small-flags franch-flag-small"></span>
          </a>
        </div>
      </div>
    </div>
  </body>

</html>
