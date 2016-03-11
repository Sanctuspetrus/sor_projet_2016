<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="manager" class="manager.Manager" scope="session" />

<link type='text/css' href='../commun_page_menu/menu.css'
	rel='stylesheet' />

<div id="menus">
	<div id="main-menu">
		<ul>
			<li class=" first"><a href="../accueil/accueil.jsp"
				title="Accueil">Accueil</a><span class="spacer"></span>
				<ul>
					<li class="first"><a href="../accueil/accueil.jsp"
						title="Page d'accueil">Page d'accueil</a></li>
				</ul></li>
			<c:if test="${manager.isIdentifie()}">
				<li class=" first"><a href="../Reservation/reservation.jsp"
					title="Livres">Réservations</a><span class="spacer"></span>
					<ul>
						<li><a href="../Reservation/reservation.jsp"
							title="Réserver une animation">Réserver une animation</a></li>
					</ul></li>
			</c:if>
			<c:if test="${!manager.isAdmin()}">
				<li class=" first"><a href="../Animation/listeAnimation.jsp"
					title="Animations">Animations</a><span class="spacer"></span></li>
			</c:if>
			<c:if test="${manager.isAdmin()}">
				<li class=" first"><a href="../Animation/listeAnimation.jsp"
					title="Animation">Animations</a><span class="spacer"></span>
					<ul>
				<li class=" first"><a href="../Animation/listeAnimation.jsp"
					title="Liste des animations">Liste des animations</a><span class="spacer"></span></li>
				<li class=" first"><a href="../Animation/creerAnimation.jsp"
					title="Nouvelle animation">Nouvelle animation</a><span class="spacer"></span></li>
					</ul></li>
			</c:if>
		</ul>
	</div>
</div>