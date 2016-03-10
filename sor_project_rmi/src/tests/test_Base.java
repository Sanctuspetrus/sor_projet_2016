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
		co = base.getCo();
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
	}
	
	//Tests sur le cycle de vie d'une animation
	public void test_animation() throws SQLException{
		base.ouvrir();
		co = base.getCo();
		//Création d'une animation de test et tests de la récupération par le nom
		Animation anim = new Animation("La vie en Rouge", "Conférence sur le communisme", "test2.png", "1883-03-14 00:20:55", 1337, 42, 42,-1);
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
	}
	
	public void test_getListAnimation() throws SQLException{
		ArrayList<Animation> animList = null;		
		base.ouvrir();
		base.supprimer_animation_by_nom("La vie en Rouge");
		base.supprimer_animation_by_nom("L'ennui");
		Animation anim = new Animation("La vie en Rouge", "Conférence sur le communisme", "test2.png", "1883-03-14 00:20:55", 1337, 42, 42,-1);
		base.ajouter_animation(anim);
		animList = base.getListAnimation();
		int size = animList.size();
		anim = new Animation("L'ennui", "Conférence sur la culture de la patate en Pologne", "patate.png", "2002-02-02 02:02:02", 2, 2, 2,-1);
		base.ajouter_animation(anim);
		animList = base.getListAnimation();
		assertTrue(animList != null);
		assertEquals(animList.size(), size+1);
		base.supprimer_animation_by_nom("La vie en Rouge");
		base.supprimer_animation_by_nom("L'ennui");
	}
	
	//Tests sur le cycle de vie d'un billet
		public void test_billet() throws SQLException{
			base.ouvrir();
			co = base.getCo();
			Billet billet = new Billet("1883-03-14");
			base.ajouter_billet(billet);
			ArrayList<Billet> listBillet = base.getListBillet();
			int size = listBillet.size();
			base.ajouter_billet(billet);
			listBillet = base.getListBillet();
			assertEquals(listBillet.size(), size+1);
			assertTrue(base.getBillet(1)!=null);
		}		
	
}