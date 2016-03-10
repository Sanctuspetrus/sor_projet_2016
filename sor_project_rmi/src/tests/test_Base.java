package tests;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import org.junit.Test;

import base.Base;
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
	
	//Tests sur le cycle de vie d'une animation
	public void test_animation() throws SQLException{
		base.ouvrir();
		co = base.getCo();
		//Création d'une animation de test et tests de la récupération par le nom
		base.ajouter_animation("La vie en Rouge", "Conférence sur le communisme", "test.png", "1883-03-14 00:20:55", 1337, 42);
		assertTrue(base.getAnimation_by_nom("La vie en Rouge").size() > 0);
		assertEquals(base.getAnimation_by_nom("La vie en Rouge").get(0).getNom(), "La vie en Rouge");
		//Suppression de toutes les animations de tests enregistrées
		base.supprimer_animation_by_nom("La vie en Rouge");
		assertEquals(base.getAnimation_by_nom("La vie en Rouge").size(), 0);
		//Création d'une nouvelle animation de test
		base.ajouter_animation("La vie en Rouge", "Conférence sur le communisme", "test.png", "1883-03-14 00:20:55", 1337, 42);
		//Récupération de l'id de test grace au nom et tests de la récupération par id
		int id = base.getAnimation_by_nom("La vie en Rouge").get(0).getIdAnimation();
		assertTrue(base.getAnimation(id).size() > 0);
		assertEquals(base.getAnimation(id).get(0).getNom(), "La vie en Rouge");
		//Modification de l'animation d'id de test
		
		//Suppression de l'animation d'id de test
		base.supprimer_animation(id);
		assertEquals(base.getAnimation(id).size(), 0);
	}
	
}