package com.omazon.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.omazon.beans.Commande;
import com.omazon.dao.CommandeDaoImpl;
import com.omazon.dao.DaoFactory;

/**
 * Servlet implementation class AfficheCommandeServlet
 */
public class AfficheCommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommandeDaoImpl commandeImpl = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficheCommandeServlet() {
        super();
        DaoFactory daoFactory = DaoFactory.getInstance();
        commandeImpl = daoFactory.getCommandeImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Commande> commandes = new ArrayList<Commande>();
		commandes = commandeImpl.listeCommandeAll();
		
		request.setAttribute("commandes", commandes);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/AfficheCommande.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
