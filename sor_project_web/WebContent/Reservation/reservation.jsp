<%@page import="bean.Billet"%>
<%@page import="bean.Reservation"%>
<%@page import="bean.Animation"%>
<%@page import="bean.DateAnimation"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="manager" class="manager.Manager" scope="session" />
<jsp:useBean id="client" class="rmi.ClientRMI" scope="session" />
<jsp:useBean id="listeReservation" class="rmi.ClientRMI" scope="session" />
<jsp:useBean id="validation" class="validation.Validation"
	scope="request" />




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Réservation(s) pour le billet n°${manager.code}</title>
<style>
table, th, td {
    border: 1px solid black;
}
</style>
</head>
<body>
<jsp:include page="../commun_page_menu/menu.jsp" />
	<table>
	<%
		ArrayList<Reservation> listeRes = client.listeReservation(1234);
		for(int i=0;i<listeRes.size();i++){
			out.println("<tr>");
			out.println("<td>"+listeRes.get(i).getAnimation().getDate()+"</td>");
			out.println("<td>"+listeRes.get(i).getAnimation().getAnimation().getNom()+"</td>");
			out.println("<td>"+listeRes.get(i).getBillet().getIdBillet()+"</td>");
			out.println("</tr>");
		}
	
	%>
	</table>
</body>
</html>