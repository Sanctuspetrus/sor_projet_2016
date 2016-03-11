<%@page import="bean.Animation"%>
<%@page import="bean.Groupe"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:useBean id="manager" class="manager.Manager" scope="session" />
<jsp:useBean id="validation" 
	class="validation.Validation" 
	scope="request" />

<%

boolean codeErreur = false;

if (request.getParameter("anim") != null) { // test clic sur bouton
	
	// validation champ nom
	validation.nonVide(Animation.class, 
			"nom",
			request.getParameter("nom"));
	System.out.println("validation nom "+validation.isValide());

	// validation champ description
	validation.nonVide(Animation.class, 
			"description",
			request.getParameter("description"));
	System.out.println("validation description "+validation.isValide());

	// validation champ annee
	if (validation.nonVide(Animation.class, 
			"duree",
			request.getParameter("duree"))) {
		validation.estEntier(Animation.class, 
				"duree",
				request.getParameter("duree"));
	}
	System.out.println("validation date "+validation.isValide());

	
	if (validation.isValide()) {
		
		Animation a = new Animation();
		a.setNom(validation.getValeurs().get("nom"));
		a.setDescription(validation.getValeurs().get("description"));
		a.setDuree(Integer.parseInt(validation.getValeurs().get("duree")));
		a.setNbPlacesTotal(Integer.parseInt(validation.getValeurs().get("duree")));
		
		codeErreur = !manager.creer_animation(a);
	}
}

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modification d'une animation</title>
</head>
<body>
	<jsp:include page="../commun_page_menu/menu.jsp" />


	
<div style="color:red;">
<p>
<% 
if (codeErreur) out.println("Erreur enregistrement");
%> 
</p>
</div>

<h1>Enregistrement d'une animation</h1>

<form>
	<table>
		<tr>
			<td>Nom : </td>
			<td><input type="text" 
						value= "${validation.valeurs['nom']}"
						name="nom"/></td>
			<td>${validation.erreurs['nom']}</td>
		</tr>
		<tr>
			<td>Description : </td>
			<td><textarea name="description" rows="5" cols="16">${validation.valeurs['description']}</textarea></td>
			<td>${validation.erreurs['description']}</td>
		</tr>
		<tr>
			<td>Date : </td>
			<td><input type="date" 
						value= "${validation.valeurs['date']}"
						name="date"/></td>
			<td>${validation.erreurs['date']}</td>
		</tr>
		<tr>
			<td>Durée (minutes): </td>
			<td><input type="number" 
						value= "${validation.valeurs['duree']}"
						name="duree" min="0"/></td>
			<td>${validation.erreurs['duree']}</td>
		</tr>
		<tr>
			<td>Nombre de places total : </td>
			<td><input type="number" 
						value= "${validation.valeurs['nbplace']}"
						name="nbplace" min="0"/></td>
			<td>${validation.erreurs['nbplace']}</td>
		</tr>
		<tr>
			<td>Groupe : </td>
			<td><select name="groupe">
				<%
				ArrayList<Groupe> lgrp = manager.listeGroupe();
				for (Groupe grp : lgrp) {
					out.println("<option value=\""+grp.getIdGroupe()+"\" >"+grp.getNom()+"</option>");
				}
				%>
			</select></td>
			<td>${validation.erreurs['groupe']}</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit"
				value="Enregistrer"
				name="submit" /></td>
		</tr>
	</table>
</form>
	
</body>
</html>