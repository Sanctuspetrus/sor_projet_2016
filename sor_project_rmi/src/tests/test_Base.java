package tests;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import base.*;
import bean.*;
import junit.framework.TestCase;

public class test_Base extends TestCase{

	Base base;
	Connection co;	

	public void setUp() {
		base = new Base();
	}

	@Test
	public void test_null() {
		co = base.getCo();
		assertEquals(co, null);
	}

	public void test_ouvrir_1() {
		base.ouvrir();
		co = base.getCo();
		assertTrue(co!=null);
	}

	public void test_ouvrir_2() throws SQLException {
		base.ouvrir();
		co = base.getCo();
		assertTrue(!co.isClosed());		
	}

	public void test_fermer() throws SQLException{
		base.ouvrir();
		co = base.getCo();
		base.fermer();
		assertTrue(co.isClosed());		
	}

	//Tests sur le cycle de vie d'un groupe
	public void test_groupe() throws SQLException{
		base.ouvrir();
		//Création d'un groupe de test et tests de la récupération par le nom
		Groupe grp = new Groupe("Parti Communiste", "Le groupe des gens pas contents");
		base.ajouter_groupe(grp);
		assertTrue(base.getGroupe_by_nom("Parti Communiste").size() > 0);
		assertEquals(base.getGroupe_by_nom("Parti Communiste").get(0).getNom(), "Parti Communiste");
		//Suppression de toutes les groupes de tests enregistrés
		base.supprimer_groupe_by_nom("Parti Communiste");
		assertEquals(base.getAnimation_by_nom("Parti Communiste").size(), 0);
		//Création d'un nouveau groupe de test
		grp = new Groupe("Parti Communiste", "Le groupe des gens pas contents");
		base.ajouter_groupe(grp);
		//Récupération de l'id de test grace au nom et tests de la récupération par id
		int id = base.getGroupe_by_nom("Parti Communiste").get(0).getIdGroupe();
		assertTrue(base.getGroupe(id) != null);
		assertEquals(base.getGroupe(id).getNom(), "Parti Communiste");
		//Modification de l'animation d'id de test
		grp = base.getGroupe(id);
		grp.setDescription("Les ennemis du Capital");
		base.modifier_groupe(grp);
		assertEquals(base.getGroupe(id).getDescription(), "Les ennemis du Capital");
		//Suppression de l'animation d'id de test
		base.supprimer_groupe(grp);
		assertEquals(base.getGroupe(id), null);
		base.fermer();
	}

	public void test_getListGroupe() throws SQLException{
		ArrayList<Groupe> grpList = null;		
		base.ouvrir();
		base.supprimer_groupe_by_nom("Parti Communiste");
		base.supprimer_groupe_by_nom("Les fans de BHL");
		Groupe grp = new Groupe("Parti Communiste", "Le groupe des gens pas contents");
		base.ajouter_groupe(grp);
		grpList = base.getListGroupe();
		int size = grpList.size();
		grp = new Groupe("Les fans de BHL", "Sérieusement ?");
		base.ajouter_groupe(grp);
		grpList = base.getListGroupe();
		assertTrue(grpList != null);
		assertEquals(grpList.size(), size+1);
		base.supprimer_groupe_by_nom("Parti Communiste");
		base.supprimer_groupe_by_nom("Les fans de BHL");
		base.fermer();
	}

	//Tests sur le cycle de vie d'une animation
	public void test_animation() throws SQLException{
		base.ouvrir();
		//Création d'une animation de test et tests de la récupération par le nom
		Animation anim = new Animation("La vie en Rouge", "Conférence sur le communisme", "test2.png", 1337, 42, 42,-1);
		base.ajouter_animation(anim);
		assertTrue(base.getAnimation_by_nom("La vie en Rouge").size() > 0);
		assertEquals(base.getAnimation_by_nom("La vie en Rouge").get(0).getNom(), "La vie en Rouge");
		//Suppression de toutes les animations de tests enregistrées
		base.supprimer_animation_by_nom("La vie en Rouge");
		assertEquals(base.getAnimation_by_nom("La vie en Rouge").size(), 0);
		//Création d'une nouvelle animation de test
		base.ajouter_animation(anim);
		//Récupération de l'id de test grace au nom et tests de la récupération par id
		int id = base.getAnimation_by_nom("La vie en Rouge").get(0).getIdAnimation();
		assertTrue(base.getAnimation(id) != null);
		assertEquals(base.getAnimation(id).getNom(), "La vie en Rouge");
		//Modification de l'animation d'id de test
		anim = base.getAnimation(id);
		anim.setDuree(555);
		base.modifier_animation(anim);
		assertEquals(base.getAnimation(id).getDuree(), 555);
		//Suppression de l'animation d'id de test
		base.supprimer_animation(anim);
		assertEquals(base.getAnimation(id), null);
		base.fermer();
	}

	public void test_getListAnimation() throws SQLException{
		ArrayList<Animation> animList = null;		
		base.ouvrir();
		base.supprimer_animation_by_nom("La vie en Rouge");
		base.supprimer_animation_by_nom("L'ennui");
		Animation anim = new Animation("La vie en Rouge", "Conférence sur le communisme", "test2.png", 1337, 42, 42,-1);
		base.ajouter_animation(anim);
		animList = base.getListAnimation();
		int size = animList.size();
		anim = new Animation("L'ennui", "Conférence sur la culture de la patate en Pologne", "patate.png", 2, 2, 2,-1);
		base.ajouter_animation(anim);
		animList = base.getListAnimation();
		assertTrue(animList != null);
		assertEquals(animList.size(), size+1);
		base.supprimer_animation_by_nom("La vie en Rouge");
		base.supprimer_animation_by_nom("L'ennui");
		base.fermer();
	}

	//Tests sur le cycle de vie d'un billet
	public void test_billet() throws SQLException{
		base.ouvrir();
		Billet billet = new Billet("1883-03-14");
		base.ajouter_billet(billet);
		ArrayList<Billet> listBillet = base.getListBillet();
		int size = listBillet.size();
		base.ajouter_billet(billet);
		listBillet = base.getListBillet();
		assertEquals(listBillet.size(), size+1);
		assertTrue(base.getBillet(1000)!=null);
		base.fermer();
	}		

	//Tests sur le cycle de vie d'une DateAnimation
	public void test_dateAnimation() throws SQLException{
		base.ouvrir();
		base.supprimer_animation_by_nom("La vie en Rouge");
		Animation anim = new Animation(42,"La vie en Rouge", "Conférence sur le communisme", "test2.png", 1337, 42, 42,-1);
		base.supprimer_dateAnimation_by_animation(anim);
		DateAnimation datanim = new DateAnimation(42, "2016-03-28");
		base.ajouter_dateAnimation(datanim);		
		assertTrue(base.getDateAnimation_by_animation(anim).size() > 0);
		assertEquals(base.getDateAnimation_by_animation(anim).get(0).getDate(), "2016-03-28 00:00:00.0");		
		base.supprimer_dateAnimation_by_animation(anim);
		assertEquals(base.getAnimation_by_nom("La vie en Rouge").size(), 0);
		base.ajouter_dateAnimation(datanim);
		int id = base.getDateAnimation_by_animation(anim).get(0).getIdDateAnimation();
		assertTrue(base.getDateAnimation(id) != null);
		assertEquals(base.getDateAnimation(id).getDate(), "2016-03-28 00:00:00.0");
		datanim = base.getDateAnimation(id);
		datanim.setDate("2005-05-05");
		base.modifier_dateAnimation(datanim);
		assertEquals(base.getDateAnimation(id).getDate(), "2005-05-05 00:00:00.0");
		base.supprimer_dateAnimation(datanim);
		assertEquals(base.getDateAnimation(id), null);
		base.supprimer_animation_by_nom("La vie en Rouge");
		base.fermer();
	}

	@Test
	public void test_getListDateAnimation() throws SQLException{
		ArrayList<DateAnimation> datanimList = null;		
		base.ouvrir();
		base.supprimer_animation_by_nom("La vie en Rouge");
		Animation anim = new Animation(42,"La vie en Rouge", "Conférence sur le communisme", "test2.png", 1337, 42, 42,-1);
		base.supprimer_dateAnimation_by_animation(anim);
		anim = new Animation(5, "L'ennui", "Conférence sur la culture de la patate en Pologne", "patate.png", 2, 2, 2,-1);
		base.supprimer_dateAnimation_by_animation(anim);
		anim = new Animation(42, "La vie en Rouge", "Conférence sur le communisme", "test2.png", 1337, 42, 42,-1);
		DateAnimation datanim = new DateAnimation(42, "2016-03-28");
		base.ajouter_dateAnimation(datanim);	
		datanimList = base.getListDateAnimation();
		int size = datanimList.size();
		anim = new Animation(5, "L'ennui", "Conférence sur la culture de la patate en Pologne", "patate.png", 2, 2, 2,-1);
		datanim = new DateAnimation(5, "2016-03-28");
		base.ajouter_dateAnimation(datanim);	
		datanimList = base.getListDateAnimation();
		assertTrue(datanimList != null);
		assertEquals(datanimList.size(), size+1);
		anim = new Animation(42, "La vie en Rouge", "Conférence sur le communisme", "test2.png", 1337, 42, 42,-1);
		base.supprimer_dateAnimation_by_animation(anim);
		anim = new Animation(5, "L'ennui", "Conférence sur la culture de la patate en Pologne", "patate.png", 2, 2, 2,-1);
		base.supprimer_dateAnimation_by_animation(anim);
		base.supprimer_animation_by_nom("La vie en Rouge");
		base.supprimer_animation_by_nom("L'ennui");
		base.fermer();
	}

	//Tests sur le cycle de vie d'une Reservation
	public void test_reservation() throws SQLException{		
		base.ouvrir();
		
		base.supprimer_animation_by_nom("Chez les Riches");
		Animation anim = new Animation(21,"Chez les Riches", "Conférence sur l'argent", "test3.png", 9999, 2, 2,-1);
		Animation anim2 = anim;
		base.supprimer_dateAnimation_by_animation(anim);
		DateAnimation datanim = new DateAnimation(21, "2061-05-25");
		base.ajouter_dateAnimation(datanim);
		
		datanim = base.getDateAnimation_by_animation(anim).get(0);
		Billet billet = new Billet("1883-03-14");
		base.ajouter_billet(billet);
		billet = base.getListBillet().get(0);
		
		Reservation reserv = new Reservation(datanim, billet);
		base.ajouter_reservation(reserv);
		assertTrue(base.getReservation_by_dateAnimation(datanim).size() > 0);
		assertTrue(base.getReservation_by_billet(billet).size() > 0);
		int idDatanim = base.getReservation_by_dateAnimation(datanim).get(0).getIdDateAnimation();
		assertEquals(base.getDateAnimation(idDatanim).getDate(), "2061-05-25 00:00:00.0");
		
		base.supprimer_animation_by_nom("Rien");
		anim = new Animation(10,"Rien", "Conférence sur le rien", "test2.png", 1337, 42, 42,-1);
		base.supprimer_dateAnimation_by_animation(anim);
		datanim = new DateAnimation(10, "2016-03-28");
		base.ajouter_dateAnimation(datanim);
		idDatanim = base.getDateAnimation_by_animation(anim).get(0).getIdDateAnimation();
		
		reserv.setIdDateAnimation(idDatanim);
		base.modifier_reservation(reserv);
		assertEquals(base.getDateAnimation(idDatanim).getDate(), "2016-03-28 00:00:00.0");
		
		base.supprimer_dateAnimation_by_animation(anim);
		base.supprimer_dateAnimation_by_animation(anim2);
		base.supprimer_animation_by_nom("La vie en Rouge");
		base.supprimer_animation_by_nom("L'ennui");
		base.supprimer_reservation(reserv);		
	}

}