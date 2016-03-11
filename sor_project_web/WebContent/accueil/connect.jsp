<%@page import="java.util.ArrayList"%>
<%@page import="bean.Billet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="manager" class="manager.Manager" scope="session" />

<%
	if (request.getParameter("submit") != null) {
		manager.setIdentifie(false);
		manager.setAdmin(false);
		response.sendRedirect("../accueil/accueil.jsp");
	}


	if (request.getParameter("admin") != null) {
		manager.setIdentifie(false);
		manager.setAdmin(false);
		response.sendRedirect("../accueil/accueil.jsp");
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

	<c:if test="${manager.isAdmin()}">
		<table>
			<tr>
				<td><p>Connecté en tant qu'administrateur</p></td>
				<td><form>
						<input type="submit" value="Déconnecter" name="admin" />
					</form></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${!manager.isAdmin()}">
		<table>
			<tr>
				<td><p>Réservations pour le billet n°${manager.code}</p></td>
				<td><form>
						<input type="submit" value="Changer" name="submit" />
					</form></td>
			</tr>
			<tr>
				<td><c:if test="${manager.isIdentifie()}"><p>Billet valide le ${manager.getBillet().getJourValid()}</p></c:if></td>
			</tr>
		</table>
	</c:if>


</body>
</html>