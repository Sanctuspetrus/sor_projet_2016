<%@page import="bean.Livre"%>
<%@page import="base.Base"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lister livres</title>
</head>
<body>
<jsp:include page="../commun_page_menu/menu.jsp" />

<h1>Liste des livres</h1>
<%
	
	Base base = new Base();
	base.ouvrir();
	ArrayList <Livre>lst =
			base.listerLivres("%");
	base.fermer();
	
	out.println("<ul>");
	for (Livre l : lst) {
		out.println("<li>"+l.getTitre()+"</li>");
	}
	out.println("</ul>");
%>

</body>
</html>