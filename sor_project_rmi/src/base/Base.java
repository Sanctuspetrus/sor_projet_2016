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

import bean.Billet;

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
}

