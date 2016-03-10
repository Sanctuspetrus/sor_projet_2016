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
import java.util.ResourceBundle;

import bean.*;

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

	/////////////Groupe//////////
	public Groupe getGroupe(int id){
		try {
			Groupe grp = null;
			String sql = "select * from t_groupe where id_groupe = "+id;
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				grp = new Groupe(rs.getInt("id_groupe"), rs.getString("nom"), rs.getString("description"));
			}
			try {rs.close();}catch(Exception e){}
			return grp;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.getGroupe() "+e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Groupe> getGroupe_by_nom(String nom){
		try {
			ArrayList<Groupe> resList = new ArrayList<Groupe>();
			Groupe grp = null;
			String sql = "select * from t_groupe where nom = \""+nom+"\"";
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				grp = new Groupe(rs.getInt("id_groupe"), rs.getString("nom"), rs.getString("description"));
				resList.add(grp);
			}
			try {rs.close();}catch(Exception e){}
			return resList;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.getGroupe_by_nom() "+e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Groupe> getListGroupe(){
		try {
			ArrayList<Groupe> resList = new ArrayList<Groupe>();
			Groupe grp = null;
			String sql = "select * from t_groupe";
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				grp = new Groupe(rs.getInt("id_groupe"), rs.getString("nom"), rs.getString("description"));
				resList.add(grp);
			}
			try {rs.close();}catch(Exception e){}
			return resList;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.getListGroupe() "+e.getMessage());
			return null;
		}
	}
	
	public boolean modifier_groupe (Groupe grp){
		try {
			String sql = "update t_groupe "
					+ "set nom = '"+grp.getNom()+"', "
							+ "description = '"+grp.getDescription()+"'"
							+ " where id_groupe = "+grp.getIdGroupe();
			Statement st = co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur :  Base.modifier_groupe_nom() "+e.getMessage());
			return false;
		}	
	}
	
	public boolean supprimer_groupe(int idGroupe){
		try {
			String sql = "delete from t_groupe where id_groupe = "+idGroupe;
			Statement st= co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.supprimer_groupe() "+e.getMessage());
			return false;
		}	
	}
	
	public boolean supprimer_groupe_by_nom(String nom){
		try {
			String sql = "delete from t_groupe where nom = \""+nom+"\"";
			Statement st= co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.supprimer_groupe_by_nom() "+e.getMessage());
			return false;
		}	
	}
	
	public boolean ajouter_groupe(Groupe grp) {
		try {
			String sql = "insert into t_groupe (nom, description) "
					+ " values ('"+grp.getNom()+"', '"+grp.getDescription()+"')";
			Statement st = co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.ajouter_groupe() "+e.getMessage());
			return false;
		}		
	}
	
	/////////////Animation//////////	
	public Animation getAnimation(int id){
		try {
			Animation anim = null;
			String sql = "select * from t_animation where id_animation = "+id;
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				anim = new Animation(rs.getInt("id_animation"), rs.getString("nom"), rs.getString("description"), rs.getString("photo"), 
						rs.getString("heure_debut"), rs.getInt("duree"), rs.getInt("nb_places_dispo"), rs.getInt("nb_places_total"), rs.getInt("id_groupe"));
			}
			try {rs.close();}catch(Exception e){}
			return anim;
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
			String sql = "delete from t_animation where nom = \""+nom+"\"";
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
					+ "set nom = '"+animation.getNom()+"', "
							+ "description = '"+animation.getDescription()+"', "
							+ "photo = '"+animation.getPhoto()+"', "
							+ "heure_debut = '"+animation.getDate()+"', "
							+ "duree = "+animation.getDuree()+", "
							+ "nb_places_dispo = "+animation.getNbPlacesDispo()+", "
							+ "nb_places_total = "+animation.getNbPlacesTotal()+", "
							+ "id_groupe = "+animation.getIdGroupe()
							+ " where id_animation = "+animation.getIdAnimation();
			Statement st = co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur :  Base.modifier_animation_nom() "+e.getMessage());
			return false;
		}	
	}

	public boolean ajouter_animation(Animation animation) {
		try {
			String sql = "insert into t_animation (nom, description, photo, heure_debut, duree, nb_places_dispo, nb_places_total, id_groupe) "
					+ " values (\""+animation.getNom()+"\", \""+animation.getDescription()+"\", \""+animation.getPhoto()+"\", \""
					+animation.getDate()+"\", "+animation.getDuree()+", "+animation.getNbPlacesDispo()+", "
					+animation.getNbPlacesTotal()+", "+animation.getIdGroupe()+")";
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
			String sql = "select * from t_animation where nom = \""+nom+"\"";
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				anim = new Animation(rs.getInt("id_animation"), rs.getString("nom"), rs.getString("description"), rs.getString("photo"), 
						rs.getString("heure_debut"), rs.getInt("duree"), rs.getInt("nb_places_dispo"), rs.getInt("nb_places_total"), rs.getInt("id_groupe"));
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
	
	public ArrayList<Animation> getListAnimation() {
		try {
			ArrayList<Animation> resList = new ArrayList<Animation>();
			Animation anim;
			String sql = "select * from t_animation";
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				anim = new Animation(rs.getInt("id_animation"), rs.getString("nom"), rs.getString("description"), rs.getString("photo"), 
						rs.getString("heure_debut"), rs.getInt("duree"), rs.getInt("nb_places_dispo"), rs.getInt("nb_places_total"), rs.getInt("id_groupe"));
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
	
	/////////////Billet//////////
	public Billet getBillet(int id){
		try {
			Billet anim = null;
			String sql = "select * from t_billet where id_billet = "+id;
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				anim = new Billet(rs.getInt("id_billet"), rs.getString("date_valid"));
			}
			try {rs.close();}catch(Exception e){}
			return anim;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.getBillet() "+e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Animation> getListBillet() {
		try {
			ArrayList<Animation> resList = new ArrayList<Animation>();
			Animation anim;
			String sql = "select * from t_animation";
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				anim = new Animation(rs.getInt("id_animation"), rs.getString("nom"), rs.getString("description"), rs.getString("photo"), 
						rs.getString("heure_debut"), rs.getInt("duree"), rs.getInt("nb_places_dispo"), rs.getInt("nb_places_total"), rs.getInt("id_groupe"));
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
	

	public boolean ajouter_billet(Billet billet) {
		try {
			String sql = "insert into t_billet (date_valid) "
					+ " values (\""+billet.getJourValid()+"\")";
			Statement st = co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.ajouter_billet() "+e.getMessage());
			return false;
		}		
	}
}


