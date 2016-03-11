<%@page import="bean.Reservation"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="manager" 
	class="manager.Manager"
	scope="session" />
<jsp:useBean id="validation" 
	class="validation.Validation"
	scope="request" />
	
	
	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Réservation pour le billet n°${manager.code}</title>
</head>
<body>
<jsp:include page="../commun_page_menu/menu.jsp" />
	
		<h1>Réservation pour le billet n°${manager.code}</h1>
	<%

	ArrayList<Reservation> lr = manager.listeReservation();
	
	if(lr!=null){
		out.println("<ul>");
		for (Reservation r : lr) {
			out.println("<li>"+r.getAnimation().getAnimation().getNom()+"</li>");
			out.println("<li>"+r.getAnimation().getDate()+"</li>");
			
		}
		out.println("</ul>");
	}else{
		out.println("Aucune réservation trouvée");
	}
%>
	
</body>
</html>