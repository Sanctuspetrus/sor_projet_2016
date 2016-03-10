package base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import bean.Animation;
import bean.Billet;

public class Base {

	private String config = "prop"; // fichier config

	private String url = null;
	private String user = null;
	private String passwd = null;

	private Connection co = null;
	public Connection getCo(){
		return this.co;
	}
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver").
				newInstance();
		}		
		catch (ClassNotFoundException e)
	    {
			System.out.println("erreur Class.forName La classe n'existe pas");
	    }
	    catch (InstantiationException e)
	    {
	    	System.out.println("erreur Class.forName La classe est abstract ou est une interface ou n'a pas de constructeur accessible sans paramètre"); 
	    }
	    catch (IllegalAccessException e)
	    {
	    	System.out.println("erreur Class.forName La classe n'est pas accessible"); 
	    }
		catch (Exception e) {
			System.out.println("erreur Class.forName");
		}
	}

	public void ouvrir() {
		ResourceBundle rb = 
				ResourceBundle.getBundle(config);
		url = rb.getString("url");
		user = rb.getString("user");
		passwd = rb.getString("passwd");
		System.out.println("url = "+url);
		System.out.println("user = "+user);

		try {
			co = DriverManager.getConnection(
					url, user, passwd);
			System.out.println("Base.ouvrir "+url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(
					"erreur Base.ouvrir "+
							e.getMessage());
			e.printStackTrace();
		}
	}

	public void fermer() {
		if (co != null) 
			try {co.close();}catch(Exception e){}

	}

	public ArrayList<String> test() {
		ArrayList<String> resList = new ArrayList<String>();
		try {
			String sql = "select * from t_billet";
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				resList.add(rs.getString("id_billet"));
			}
			try {rs.close();}catch(Exception e){}			
		}
		catch (Exception e) {
			System.out.println("erreur base.test "+e.getMessage());
		}
		return resList; 

	}
	
	public ArrayList<Animation> getAnimation(int id){
		try {
			ArrayList<Animation> resList = new ArrayList<Animation>();
			Animation anim;
			String sql = "select * from t_animation where id_animation = "+id;
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				anim = new Animation(rs.getString("id_animation"), rs.getString("nom"), rs.getString("description"), rs.getString("photo"), 
						rs.getString("heure_debut"), rs.getString("duree"), rs.getString("nb_places_dispo"), rs.getString("nb_places_total"), rs.getString("id_groupe"));
				resList.add(anim);
			}
			try {rs.close();}catch(Exception e){}
			return resList;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.getAnimation() "+e.getMessage());
			return null;
		}
	}
	
	public boolean supprimer_animation(int idAnimation){
		try {
			String sql = "delete from t_animation where id_animation = "+idAnimation;
			Statement st= co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.supprimer_animation() "+e.getMessage());
			return false;
		}	
	}
	
	public boolean supprimer_animation_by_nom(String nom){
		try {
			String sql = "delete from t_animation where nom = '"+nom+"'";
			Statement st= co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.supprimer_animation_by_nom() "+e.getMessage());
			return false;
		}	
	}
		
	public boolean modifier_animation(Animation animation){
		try {
			String sql = "update t_animation "
					+ "set nom = '"+animation.getNom()+"',"
							+ "description = "+animation.getNom()+"',"
							+ "photo = "+animation.getNom()+"',"
							+ "heure_debut = "
							+ "duree = "
							+ "nb_places_dispo = "
							+ "nb_places_total = "
							+ "id_groupe = "
							+ " where id_animation = "+animation.getIdAnimation();
			System.out.println(sql);
			Statement st = co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur :  Base.modifier_animation_nom() "+e.getMessage());
			return false;
		}	
	}

	public boolean ajouter_animation(String nom, String desc, String photo, String date, int duree, int nbPlaces) {
		try {
			String sql = "insert into t_animation (nom, description, photo, heure_debut, duree, nb_places_dispo, nb_places_total) "
					+ " values ('"+nom+"', '"+desc+"', '"+photo+"', '"+date+"', "+duree+", "+nbPlaces+", "+nbPlaces+")";
			Statement st = co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.ajouter_animation() "+e.getMessage());
			return false;
		}		
	}

	public ArrayList<Animation> getAnimation_by_nom(String nom) {
		try {
			ArrayList<Animation> resList = new ArrayList<Animation>();
			Animation anim;
			String sql = "select * from t_animation where nom = '"+nom+"'";
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				anim = new Animation(rs.getString("id_animation"), rs.getString("nom"), rs.getString("description"), rs.getString("photo"), 
						rs.getString("heure_debut"), rs.getString("duree"), rs.getString("nb_places_dispo"), rs.getString("nb_places_total"), rs.getString("id_groupe"));
				resList.add(anim);
			}
			try {rs.close();}catch(Exception e){}
			return resList;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.getAnimation_by_nom() "+e.getMessage());
			return null;
		}
	}
}

