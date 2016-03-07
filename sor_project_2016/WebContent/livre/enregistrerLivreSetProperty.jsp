<%@page import="bean.Livre"%>
<%@page import="base.Base"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="livre" class="bean.Livre" 
	scope="request"/>


<%
String res = "";
int codeErreur = -1;
%>

<c:catch var="catchException">
<jsp:setProperty name="livre" property="*" />
</c:catch>

<c:if test="${catchException == null}">
Erreur setProperty ${catchException}
</c:if>

${catchException}

<%
if (request.getParameter("submit") != null) { // test clic sur bouton
	try {
		Base base = new Base();
		base.ouvrir();
		codeErreur = base.enregistrerLivre(livre);
		base.fermer();
	}
	catch (Exception e) {
		codeErreur = 0;
		System.out.println("erreur enregistrerLivre "+e.getMessage());
	}
	if (codeErreur > 0) res = "Livre enregistré : "+livre.getTitre();
	else if (codeErreur == 0) res = "Erreur enregistrement";

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
<p><%= res %></p>
</div>


<h1>Enregistrement d'un livre (version setProperty)</h1>

<form>
	<table>
		<tr>
			<td>Titre : </td>
			<td><input type="text" 
						value= "${livre.titre}"
						name="titre"/></td>
		</tr>
		<tr>
			<td>Auteur : </td>
			<td><input type="text" 
						value= "${livre.auteur}"
						name="auteur"/></td>
		</tr>
		<tr>
			<td>Année : </td>
			<td><input type="text" 
						value= "${livre.annee}"
						name="annee"/></td>
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