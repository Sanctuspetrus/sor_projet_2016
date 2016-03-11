<%@page import="bean.Animation"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:useBean id="manager" class="manager.Manager" scope="session" />
<jsp:useBean id="validation" class="validation.Validation"
	scope="request" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des animations</title>
</head>
<body>
<jsp:include page="../commun_page_menu/menu.jsp" />
	<h1>Liste des animations</h1>
	<%

	ArrayList<Animation> la = manager.listeAnimation();
	
	if(la!=null){
		out.println("<ul>");
		for (Animation a : la){
			out.println("<li>"+a.getNom()+"</li>");
			out.println("<li>"+a.getDescription()+"</li>");
			out.println("<li>"+a.getDuree()+" minutes</li>");
			out.println("<li>Places "+a.getNbPlacesDispo()+"/"+a.getNbPlacesTotal()+"</li>");
			out.println("<li>Groupe "+a.getGroupe().getNom()+"</li>");
			
		}
		out.println("</ul>");
	}else{
		out.println("Aucune animation trouvée");
	}
%>
</body>
</html>