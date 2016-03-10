<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="manager" 
	class="manager.Manager"
	scope="session" />

<link type='text/css' href='../commun_page_menu/menu.css'	rel='stylesheet' />

<%
	if(manager.isAdmin())
		response.sendRedirect("../commun_page_menu/menuAdmin.jsp");
%>
<div id="menus">
	<div id="main-menu">
		<ul>
			<li class=" first"><a href="../accueil/accueil.jsp" title="Accueil">Accueil</a><span class="spacer"></span>
				<ul>
					<li class="first">
						<a href="../accueil/accueil.jsp" title="Page d'accueil">Page d'accueil</a>
					</li>
				</ul>
			</li>
			<li class=" first"><a href="../Reservation/reservation.jsp" title="Livres">Réservations</a><span class="spacer"></span>
				<ul>
					<li class="first">
						<a href="../livre/enregistrerLivre.jsp" title="Enregistrer un livre">Réserver une animation</a>
					</li>
				</ul>
			</li>
			<c:if test="${!manager.isAdmin()}">
			<li class=" first"><a href="../Animation/listeAnimation.jsp" title="Item 2">Animations</a><span class="spacer"></span>
			</li>
			</c:if>
			<c:if test="${manager.isAdmin()}">
				<li class=" first"><a href="../Animation/animation.jsp" title="Item 2">Animations</a><span class="spacer"></span>
				</li>
			</c:if>
		</ul>
	</div>
</div>