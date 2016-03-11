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
		//System.out.println("url = "+url);
		//System.out.println("user = "+user);

		try {
			co = DriverManager.getConnection(
					url, user, passwd);
			//System.out.println("Base.ouvrir "+url);
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
					+ "set nom = \""+grp.getNom()+"\", "
							+ "description = \""+grp.getDescription()+"\""
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
	
	public boolean supprimer_groupe(Groupe grp){
		try {
			String sql = "delete from t_groupe where id_groupe = "+grp.getIdGroupe();
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
				anim = new Animation(rs.getInt("id_animation"), rs.getString("nom"), rs.getString("description"), rs.getString("photo"), rs.getInt("duree"), rs.getInt("nb_places_dispo"), rs.getInt("nb_places_total"), rs.getInt("id_groupe"));
			}
			try {rs.close();}catch(Exception e){}
			return anim;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.getAnimation() "+e.getMessage());
			return null;
		}
	}
	
	public boolean supprimer_animation(Animation animation){
		try {
			String sql = "delete from t_animation where id_animation = "+animation.getIdAnimation();
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
					+ "set nom = \""+animation.getNom()+"\", "
							+ "description = \""+animation.getDescription()+"\", "
							+ "photo = \""+animation.getPhoto()+"\", "
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
			String sql = "insert into t_animation (nom, description, photo, duree, nb_places_dispo, nb_places_total, id_groupe) "
					+ " values (\""+animation.getNom()+"\", \""+animation.getDescription()+"\", \""+animation.getPhoto()+"\", "
					+animation.getDuree()+", "+animation.getNbPlacesDispo()+", "
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
						rs.getInt("duree"), rs.getInt("nb_places_dispo"), rs.getInt("nb_places_total"), rs.getInt("id_groupe"));
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
						rs.getInt("duree"), rs.getInt("nb_places_dispo"), rs.getInt("nb_places_total"), rs.getInt("id_groupe"));
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
	
	public ArrayList<Billet> getListBillet() {
		try {
			ArrayList<Billet> resList = new ArrayList<Billet>();
			Billet billet;
			String sql = "select * from t_billet";
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				billet = new Billet(rs.getInt("id_billet"), rs.getString("date_valid"));
				resList.add(billet);
			}
			try {rs.close();}catch(Exception e){}
			return resList;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.getListBillet() "+e.getMessage());
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

	/////////////DateAnimation//////////	
	public DateAnimation getDateAnimation(int id){
		try {
			DateAnimation datanim = null;
			String sql = "select * from t_date_animation where id_date_animation = "+id;
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				datanim = new DateAnimation(rs.getInt("id_date_animation"), rs.getString("heure_debut"), rs.getInt("id_animation"));
			}
			try {rs.close();}catch(Exception e){}
			return datanim;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.getDateAnimation() "+e.getMessage());
			return null;
		}
	}
	
	public boolean supprimer_dateAnimation(DateAnimation datanim){
		try {
			String sql = "delete from t_date_animation where id_date_animation = "+datanim.getIdDateAnimation();
			Statement st= co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.supprimer_dateAnimation() "+e.getMessage());
			return false;
		}	
	}
	
	public boolean supprimer_dateAnimation_by_animation(Animation animation){
		try {
			String sql = "delete from t_date_animation where id_animation = "+animation.getIdAnimation()+"";
			Statement st= co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.supprimer_dateAnimation_by_animation() "+e.getMessage());
			return false;
		}	
	}	
		
	public boolean modifier_dateAnimation(DateAnimation dateAnimation){

		try {
			String sql = "update t_date_animation "
					+ "set id_animation = "+dateAnimation.getIdAnimation()+", "
							+ "heure_debut = \""+dateAnimation.getDate()+"\""
							+ " where id_date_animation = "+dateAnimation.getIdDateAnimation();
			Statement st = co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur :  Base.modifier_dateAnimation_nom() "+e.getMessage());
			return false;
		}	
	}

	public boolean ajouter_dateAnimation(DateAnimation dateAnimation) {
		try {
			String sql = "insert into t_date_animation (id_animation, heure_debut) "
					+ " values ("+dateAnimation.getIdAnimation()+", \""+dateAnimation.getDate()+"\")";
			Statement st = co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.ajouter_dateAnimation() "+e.getMessage());
			return false;
		}		
	}

	public ArrayList<DateAnimation> getDateAnimation_by_animation(Animation anim) {
		try {
			ArrayList<DateAnimation> resList = new ArrayList<DateAnimation>();
			DateAnimation datanim;
			String sql = "select * from t_date_animation where id_animation = "+anim.getIdAnimation();
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				datanim = new DateAnimation(rs.getInt("id_date_animation"), rs.getString("heure_debut"), rs.getInt("id_animation"));
				resList.add(datanim);
			}
			try {rs.close();}catch(Exception e){}
			return resList;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.getDateAnimation_by_animation() "+e.getMessage());
			return null;
		}
	}
	
	public ArrayList<DateAnimation> getListDateAnimation(){
		try {
			ArrayList<DateAnimation> resList = new ArrayList<DateAnimation>();
			DateAnimation datanim;
			String sql = "select * from t_date_animation";
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				datanim = new DateAnimation(rs.getInt("id_date_animation"), rs.getString("heure_debut"), rs.getInt("id_animation"));
				resList.add(datanim);
			}
			try {rs.close();}catch(Exception e){}
			return resList;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.getListDateAnimation() "+e.getMessage());
			return null;
		}
	}
	
	/////////////Réservation//////////
	public Reservation getReservation(int id){
		try {
			Reservation reserv = null;
			String sql = "select * from t_reservation where id_reservation = "+id;
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				reserv = new Reservation(rs.getInt("id_reservation"), rs.getInt("id_date_animation"), rs.getInt("id_billet"));
			}
			try {rs.close();}catch(Exception e){}
			return reserv;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.getReservation() "+e.getMessage());
			return null;
		}
	}
	
	public boolean supprimer_reservation(Reservation reservation){
		try {
			String sql = "delete from t_reservation where id_reservation = "+reservation.getIdReservation();
			Statement st= co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.supprimer_reservation() "+e.getMessage());
			return false;
		}	
	}
	
	public boolean supprimer_reservation_by_billet(Billet billet){
		try {
			String sql = "delete from t_reservation where id_billet = "+billet.getIdBillet()+"";
			Statement st= co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.supprimer_reservation_by_billet() "+e.getMessage());
			return false;
		}	
	}
	
	public boolean supprimer_reservation_by_dateAnimation(DateAnimation dateAnimation){
		try {
			String sql = "delete from t_reservation where id_date_animation = "+dateAnimation.getIdDateAnimation()+"";
			Statement st= co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.supprimer_reservation_by_dateAnimation() "+e.getMessage());
			return false;
		}	
	}	
		
	public boolean modifier_reservation(Reservation reservation){

		try {
			String sql = "update t_reservation "
					+ "set id_reservation = "+reservation.getIdReservation()+", "
							+ "id_billet = "+reservation.getIdBillet()+", "
							+ "id_date_animation = "+reservation.getIdDateAnimation()
							+ " where id_reservation = "+reservation.getIdReservation();
			Statement st = co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur :  Base.modifier_reservation() "+e.getMessage());
			return false;
		}	
	}

	public boolean ajouter_reservation(Reservation reservation) {
		try {
			String sql = "insert into t_reservation (id_date_animation, id_billet) "
					+ " values ("+reservation.getIdDateAnimation()+", "+reservation.getIdBillet()+")";
			Statement st = co.createStatement();
			st.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.ajouter_reservation() "+e.getMessage());
			return false;
		}		
	}

	public ArrayList<Reservation> getReservation_by_dateAnimation(DateAnimation dateAnimation) {
		try {
			ArrayList<Reservation> resList = new ArrayList<Reservation>();
			Reservation reserv;
			String sql = "select * from t_reservation where id_date_animation = "+dateAnimation.getIdDateAnimation()+"";
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				reserv = new Reservation(rs.getInt("id_reservation"), rs.getInt("id_date_animation"), rs.getInt("id_billet"));
				resList.add(reserv);
			}
			try {rs.close();}catch(Exception e){}
			return resList;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.getReservation_by_dateAnimation() "+e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Reservation> getReservation_by_billet(Billet billet) {
		try {
			ArrayList<Reservation> resList = new ArrayList<Reservation>();
			Reservation reserv;
			String sql = "select * from t_reservation where id_billet = "+billet.getIdBillet()+"";
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				reserv = new Reservation(rs.getInt("id_reservation"), rs.getInt("id_date_animation"), rs.getInt("id_billet"));
				resList.add(reserv);
			}
			try {rs.close();}catch(Exception e){}
			return resList;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.getReservation_by_billet() "+e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Reservation> getListReservation(){
		try {
			ArrayList<Reservation> resList = new ArrayList<Reservation>();
			Reservation reserv;
			String sql = "select * from t_reservation";
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				reserv = new Reservation(rs.getInt("id_reservation"), rs.getInt("id_date_animation"), rs.getInt("id_billet"));
				resList.add(reserv);
			}
			try {rs.close();}catch(Exception e){}
			return resList;
		}
		catch (Exception e) {
			System.out.println("Erreur : Base.getListReservation() "+e.getMessage());
			return null;
		}
	}
	
}


