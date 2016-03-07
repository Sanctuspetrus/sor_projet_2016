<%@page import="bean.Livre"%>
<%@page import="base.Base"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="manager" 
	class="manager.Manager"
	scope="session" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
</head>
<body>
<jsp:include page="../commun_page_menu/menu.jsp" />
<h1>Accueil</h1>

<c:if test="${manager.identifie}">
<p>Bonjour ${manager.nom}, l'identification a été effectuée</p>
</c:if>

</body>
</html>