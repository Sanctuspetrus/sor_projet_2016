<%@page import="bean.Identification"%>
<%@page import="bean.Livre"%>
<%@page import="base.Base"%>
<%@page import="manager.Manager"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="manager" 
	class="manager.Manager"
	scope="session" />
<jsp:useBean id="validation" 
	class="validation.Validation"
	scope="request" />
<%
/*
// création objet persistant
// equivalent jsp:useBean
Manager manager = (Manager)request.getSession().
	getAttribute("manager");
if (manager == null) {
	// si objet persistant pas encore créé
	manager = new Manager();
	request.getSession().setAttribute(
			"manager", manager);
}

*/

if (request.getParameter("submit") != null) {
	validation.nonVide(
			Identification.class, 
			"ident", 
			request.getParameter("ident"));
	validation.nonVide(
			Identification.class, 
			"mdp", 
			request.getParameter("mdp"));
	if (validation.isValide()) {
		String ident = validation.getValeurs().get("ident");
		String mdp = validation.getValeurs().get("mdp");
		if (ident.equals("ubo")) {
			manager.setIdentifie(true);
			manager.setNom(ident);
			response.sendRedirect("../accueil/accueil.jsp");
			return;
		}
		
				
	}
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enregistrement livre</title>
</head>
<body>
<jsp:include page="../commun_page_menu/menu.jsp" />

<div style="color:red;">
<p>
</p>
</div>

<h1>Identification</h1>

<form>
	<table>
		<tr>
			<td>Identifiant : </td>
			<td><input type="text" 
						value= "${validation.valeurs['ident']}"
						name="ident"/></td>
			<td>${validation.erreurs['ident']}</td>
		</tr>
		<tr>
			<td>Mot de passe : </td>
			<td><input type="password" 
						value= "${validation.valeurs['mdp']}"
						name="mdp"/></td>
			<td>${validation.erreurs['mdp']}</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit"
				value="Valider"
				name="submit" /></td>
		</tr>
	</table>
</form>

</body>
</html>