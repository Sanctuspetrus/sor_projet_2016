<%@page import="bean.Billet"%>
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
<%
if (request.getParameter("submit") != null) {
	validation.nonVide(
			Billet.class, 
			"code", 
			request.getParameter("code"));

	if (validation.isValide()) {
		String code = validation.getValeurs().get("code");
		manager.setCode(code);
		

		if (code.equals("ubo")) {
			manager.setIdentifie(true);
			manager.setAdmin(true);
			response.sendRedirect("../accueil/accueil.jsp");
		}else if(manager.getBillet() != null){
			manager.setIdentifie(true);
			response.sendRedirect("../accueil/accueil.jsp");
		}

		
				
	}
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
</head>
<body>
<jsp:include page="../commun_page_menu/menu.jsp" />
<h1>Accueil</h1>

<form>
	<table>
		<tr>
			<td>N° du billet : </td>
			<td><input type="text" 
						value= "${validation.valeurs['code']}"
						name="code"/></td>
			<td>${validation.erreurs['ident']}</td>
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