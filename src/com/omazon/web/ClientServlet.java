package com.omazon.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.omazon.beans.Client;
import com.omazon.dao.ClientDaoImpl;
import com.omazon.dao.DaoFactory;

/**
 * Servlet implementation class ClientServlet
 */
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ClientDaoImpl clientImpl = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.clientImpl = daoFactory.getClientImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/CreationClient.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest r, HttpServletResponse response) throws ServletException, IOException {
		if(r.getParameter("nomClient").equals("") || r.getParameter("prenomClient").equals("") || r.getParameter("adresseClient").equals("") || r.getParameter("telephoneClient").equals("") || r.getParameter("emailClient").equals("")) {
			r.setAttribute("error", "Le formulaire n'est pas bien complété");
			this.getServletContext().getRequestDispatcher("/WEB-INF/CreationClient.jsp").forward(r, response);
		}
		else {
			Client client = new Client(r.getParameter("nomClient"), r.getParameter("prenomClient"), r.getParameter("adresseClient"), r.getParameter("telephoneClient"), r.getParameter("emailClient"));

			clientImpl.addClient(client);
			
			r.setAttribute("client", client);
			this.getServletContext().getRequestDispatcher("/WEB-INF/AfficheClient.jsp").forward(r, response);
		}
	}

}
