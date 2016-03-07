package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletImgWebInf
 */
@WebServlet("/ServletImgWebInf")
public class ServletImgWebInf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletImgWebInf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String img = "img2.jpg";
		String rep =  request.
				getServletContext().
					getRealPath("WEB-INF/img");
		String fic = rep + "/" + img;
		System.out.println("fic = "+fic);
		if (new File(fic).exists()) {
			response.setContentType("image/jpeg");
			BufferedInputStream bis = 
					new BufferedInputStream(
							new FileInputStream(fic));
			BufferedOutputStream bos =
					new BufferedOutputStream(
							response.
								getOutputStream());
			int c;
			while ((c = bis.read()) != -1) {
				bos.write(c);
			}
			// bos.flush();
			bis.close();
			bos.close();
		}
		else {
			System.out.println("erreur "+fic);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
/*
	String img = request.getParameter("im");
	String rep = request.
					getServletContext().
						getRealPath("/WEB-INF/img");
	String fic = rep + "/" + img;
	if ((img == null) || !(new File(fic)).exists()) {
		System.out.println("rep = "+rep);
		PrintWriter out = response.getWriter();
		out.println("erreur : paramètre ou fichier manquant");
		return;
	}
	
	BufferedInputStream bis =
			new BufferedInputStream(
					new FileInputStream(fic));
	BufferedOutputStream bos =
			new BufferedOutputStream(
					response.getOutputStream());
	int c;
	while ((c = bis.read()) != -1) {
		bos.write(c);
	}
	//bos.flush();
	bis.close();
	bos.close();
*/
}
