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

import annotation.Table;
import bean.Livre;

public class Base {

	String config = "prop"; // fichier config
	
	String url = null;
	String user = null;
	String passwd = null;
	
	Connection co = null;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver").
				newInstance();
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
	
	public void test() {
		try {
			String sql = "select * from t_livre";
			Statement st = co.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				System.out.println("titre = "+
						rs.getString("titre"));
			}
			try {rs.close();}catch(Exception e){}
			try {st.close();}catch(Exception e){}
		}
		catch (Exception e) {
			System.out.println("erreur base.test "+e.getMessage());
		}
		
	}
	
	public ArrayList<Livre> listerLivres(
			String auteur) {
		ArrayList<Livre> lst = new ArrayList<Livre>();
		
		try {
			String sql = 
			 "select * from t_livre where auteur like ?";
			PreparedStatement ps = co.prepareStatement(sql);
			ps.setString(1, auteur); // num param
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Livre l = new Livre();
				l.setIdLivre(rs.getInt("idLivre"));
				l.setTitre(rs.getString("titre"));
				l.setAuteur(rs.getString("auteur"));
				l.setAnnee(rs.getInt("annee"));
				lst.add(l);
			}
			try {rs.close();}catch(Exception e){}
			try {ps.close();}catch(Exception e){}
		}
		catch (Exception e) {
			System.out.println("Erreur Base.listerLivres "+e.getMessage());
		}
		
		return lst;
	}
	
	public int enregistrerLivre(Livre l) {
		int res = 0; // 0 echec 1 succes
		try {
			String sql = 
			 "insert into t_livre "
			 + "(titre, auteur, annee) "
			 + "values (? , ? , ?)";
			PreparedStatement ps = 
					co.prepareStatement(sql);
			ps.setString(1, l.getTitre());
			ps.setString(2, l.getAuteur());
			ps.setInt(3, l.getAnnee());
			res = ps.executeUpdate();
			try {ps.close();}catch(Exception e){}
		}
		catch (Exception e) {
			System.out.println("Erreur Base.listerLivres "+e.getMessage());
		}

		return res;
	}
	
	public int enregistrer(Object o) {
		int res = 0;
		String sql = "insert into ";
		
		Class c = o.getClass();
		Table ann = (Table)
				c.getAnnotation(Table.class);
		System.out.println("table = "+ann.name());
		
		sql += ann.name();
		
		Field [] fields = c.getDeclaredFields();
		sql += "(";
		for (int i=0 ; i<fields.length ; i++) {
			System.out.println("champ = "+
						fields[i].getName());
			if (i > 0) {
				if (i > 1) sql += ",";
				sql += fields[i].getName();
			}
		}
		
		sql += ") values (";
		
		for (int i=0 ; i<fields.length ; i++) {
			if (i > 0) {
				if (i > 1) sql+= ",";
				Field field = fields[i];
				try {
					System.out.println("champ = "+field.getName());
					String nomGetter = "get" + field.getName().substring(0,1).toUpperCase() +	field.getName().substring(1);
					System.out.println("getter = "+ nomGetter);
					Method m = null;
					m = c.getMethod(nomGetter);
					if (m.getReturnType() == 
							String.class) {
						String s = 
								(String)m.invoke(o);
						System.out.println("valeur = "+s);
						sql += "'" + s + "'";
					}
					else if (m.getReturnType() == 
							Integer.class) {
						Integer v = 
								(Integer)m.invoke(o);
						System.out.println("valeur = "+v);
						sql += v;
					}
					else {
						System.out.println("Erreur base.enregistrer : type inconnu "+m.getReturnType());
					}
					System.out.println();
				} catch (Exception e) {
					System.out.println("Erreur base.enregistrer : exception "+e.getMessage());
				}
			}
		}
			
		sql += ")";

		
		System.out.println("sql = "+sql);
		
		try {
			Statement st = co.createStatement();
			res = st.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("Erreur Base.enregistrer : erreur executeUpdate "+e.getMessage());
		}

		return res;
	}
	public static void main(String [] args) {
		Base base = new Base();
		base.ouvrir();
		//base.test();
		
		Livre livre = new Livre();
		Date d = new Date();
		livre.setTitre("titre "+d);
		livre.setAuteur("auteur "+d);
		livre.setAnnee(2016);
		
		base.enregistrer(livre);
		
		//base.enregistrerLivre(livre);
		ArrayList <Livre> lst = base.listerLivres("%");
		base.fermer();
		
		for (Livre l : lst) {
			System.out.println("titre = "+l.getTitre());
		}
	}

}
