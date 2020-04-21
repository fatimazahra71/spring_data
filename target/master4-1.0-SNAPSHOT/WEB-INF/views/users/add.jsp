<%--
  Created by IntelliJ IDEA.
  User: binizmohamed
  Date: 4/6/20
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Ajouter utilisateur</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>

<div class="container">
    <jsp:directive.include file="../layout/header.jsp" />
    <header class="col-lg-12">
        <h1>Ajouter un utilisateur</h1>
        <form:form method="post" action="${pageContext.request.contextPath}/user/save" modelAttribute="user" >
            <form:input path="id" type="hidden" />
            <div class="form-group">
                <label for="email">Email</label>
                <form:input path="email" cssClass="form-control"  placeholder="email" />
                <form:errors path="email" cssClass="alert-danger" />
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <form:password path="password" cssClass="form-control"  placeholder="password" />
                <form:errors path="password" cssClass="alert-danger" />

            </div>
            <div class="form-group">
                <label for="created">Date de creation</label>
                <form:input type="date" path="created" cssClass="form-control"  placeholder="date de creation" />
                <form:errors path="created" cssClass="alert-danger" />

            </div>
            <div class="form-group">
                <label for="modified">Date de modification</label>
                <form:input type="date" path="modified" cssClass="form-control"  placeholder="date de modification" />
                <form:errors path="modified" cssClass="alert-danger" />

            </div>


            <div class="form-check">
                <label class="form-check-label">
                    Articles
                </label>

                <c:forEach items="${articles}"   var="article">
                    <c:choose>
                        <c:when test="${article.used}">
                            <form:checkbox path="articlesList" value="${article.id}" label="${article.title}" checked="checked" />
                        </c:when>
                        <c:otherwise>
                            <form:checkbox path="articlesList" value="${article.id}" label="${article.title}"  />
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
                <form:errors path="articlesList" cssClass="alert-danger" />
            </div>

            <input type = "submit" value = "Submit"/>
        </form:form>
    </header>
</div>
</body>
</html>
