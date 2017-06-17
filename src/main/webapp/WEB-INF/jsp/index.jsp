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
    <c:url value="${request.contextPath}/static" var="spath"/>
    <title><spring:message code="jsp.index.title"/></title>
    <link rel="stylesheet" href="${spath}<spring:theme code='styleSheet'/>" type="text/css"/>
    <style></style>
  </head>

  <body style="background-color:<spring:theme code="bgColor"/>">
    <div class="container-fluid bgimg pad flex-container">
      <div class="row pad">
        <div class="col-sm-7">
          <h1><spring:message code="jsp.index.title"/></h1>

          <p><spring:message code="jsp.index.description"/>
          </p>

        </div>
        <div class="col-sm-5 has-error">
          <form:form servletRelativeAction="/login" method="POST" modelAttribute="loginUser" class="form-group">
            <form:errors path="*" cssClass="text-warning"></form:errors>
            <label class="form-control-static"><spring:message code="jsp.index.username"/></label>
            <spring:message code='jsp.index.username.hint' var="username_hint"/>
            <form:input type="text" path="username" cssClass="form-control" placeholder="${username_hint}"/>

            <label class="form-control-static"><spring:message code="jsp.index.password"/></label>
            <spring:message code='jsp.index.password.hint' var="password_hint"/>
            <form:password path="password" class="form-control" placeholder="${password_hint}"/>

            <br/>
            <spring:message code='jsp.index.button.submit' var="button_sumit"/>
            <input type="submit" class="btn btn-success form-control" value="${button_sumit}"/>
          </form:form>
        </div>
      </div>
    </div>
    <div class="container-fluid flex-container">
      <div class="row">
        <div class="col-sm-7 theme-flags-group">
          <a href="${theAction}?theme=green">
            <span class="theme-flags green-theme"></span>
          </a>
          <a href="${theAction}?theme=dark">
            <span class="theme-flags dark-theme"></span>
          </a>
        </div>
        <c:url value="${request.contextPath}/login" var="theAction"/>
        <div class="col-sm-5 flags-group">
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
