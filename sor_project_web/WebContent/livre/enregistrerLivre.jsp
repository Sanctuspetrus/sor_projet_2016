<%@page import="bean.Livre"%>
<%@page import="base.Base"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="validation" 
	class="validation.Validation" 
	scope="request" />

<%

int codeErreur = -1;

if (request.getParameter("submit") != null) { // test clic sur bouton
	
	// validation champ titre
	validation.nonVide(Livre.class, 
			"titre",
			request.getParameter("titre"));
	System.out.println("validation titre "+validation.isValide());

	// validation champ auteur
	validation.nonVide(Livre.class, 
			"auteur",
			request.getParameter("auteur"));
	System.out.println("validation auteur "+validation.isValide());

	// validation champ annee
	if (validation.nonVide(Livre.class, 
			"annee",
			request.getParameter("annee"))) {
		validation.estEntier(Livre.class, 
				"annee",
				request.getParameter("annee"));
	}
	System.out.println("validation annee "+validation.isValide());

	
	if (validation.isValide()) {
 		Base base = new Base();
		base.ouvrir();
		
		Livre l = new Livre();
		l.setTitre(
			validation.getValeurs().get("titre"));
		l.setAuteur(
			validation.getValeurs().get("auteur"));
		l.setAnnee(Integer.parseInt(
				validation.getValeurs().get("annee")));
		
		codeErreur = base.enregistrerLivre(l);
		base.fermer();
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
<% 
if (codeErreur > 0) out.println("Le livre a été enregistré");
else if (codeErreur == 0) out.println("Erreur enregistrement");
%> 
</p>
</div>

<h1>Enregistrement d'un livre</h1>

<form>
	<table>
		<tr>
			<td>Titre : </td>
			<td><input type="text" 
						value= "${validation.valeurs['titre']}"
						name="titre"/></td>
			<td>${validation.erreurs['titre']}</td>
		</tr>
		<tr>
			<td>Auteur : </td>
			<td><input type="text" 
						value= "${validation.valeurs['auteur']}"
						name="auteur"/></td>
			<td>${validation.erreurs['auteur']}</td>
		</tr>
		<tr>
			<td>Année : </td>
			<td><input type="text" 
						value= "${validation.valeurs['annee']}"
						name="annee"/></td>
			<td>${validation.erreurs['annee']}</td>
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