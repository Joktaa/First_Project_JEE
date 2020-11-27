package com.omazon.web;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.omazon.beans.Client;
import com.omazon.beans.Commande;
import com.omazon.dao.ClientDaoImpl;
import com.omazon.dao.CommandeDaoImpl;
import com.omazon.dao.DaoFactory;

/**
 * Servlet implementation class CommandeServlet
 */
public class CommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommandeDaoImpl commandeImpl = null;
	private ClientDaoImpl clientImpl = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandeServlet() {
        super();
        DaoFactory daoFactory = DaoFactory.getInstance();
        commandeImpl = daoFactory.getCommandeImpl();
        clientImpl = daoFactory.getClientImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/CreationCommande.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest r, HttpServletResponse response) throws ServletException, IOException {
		Client client = null;
		LocalDate date = LocalDate.now();
		
		// CLIENT
		if(!r.getParameter("nomClient").equals("") && !r.getParameter("prenomClient").equals("") && !r.getParameter("adresseClient").equals("") && !r.getParameter("telephoneClient").equals("") && !r.getParameter("emailClient").equals("")) {
			client = new Client(r.getParameter("nomClient"), r.getParameter("prenomClient"), r.getParameter("adresseClient"), r.getParameter("telephoneClient"), r.getParameter("emailClient"));
			clientImpl.addClient(client);
			System.out.println("1");
		}
		else if (!r.getParameter("emailNotSet").equals("")) {
			System.out.println("2");
		} else {
			r.setAttribute("error", "Le formulaire n'est pas bien complété");
			this.getServletContext().getRequestDispatcher("/WEB-INF/CreationCommande.jsp").forward(r, response);
			System.out.println("2");
		}


		// COMMANDE
		if(date.equals(null) || r.getParameter("montantCommande").equals("") || r.getParameter("modePaiementCommande").equals("") || r.getParameter("statutPaiementCommande").equals("") || r.getParameter("modeLivraisonCommande").equals("") || r.getParameter("statutLivraisonCommande").equals("")) {
			r.setAttribute("error", "Le formulaire n'est pas bien complété");
			this.getServletContext().getRequestDispatcher("/WEB-INF/CreationCommande.jsp").forward(r, response);
			System.out.println("4");
		}
		else {
			if (client == null) {
				Commande commande = new Commande(null, date, r.getParameter("montantCommande"), r.getParameter("modePaiementCommande"), r.getParameter("statutPaiementCommande"), r.getParameter("modeLivraisonCommande"), r.getParameter("statutLivraisonCommande"));
				commandeImpl.addCommandeByEmail(commande, r.getParameter("emailNotSet"));
				System.out.println("5");
				this.getServletContext().getRequestDispatcher("/WEB-INF/AfficheCommande.jsp").forward(r, response);
			}
			else {
				Commande commande = new Commande(client, date, r.getParameter("montantCommande"), r.getParameter("modePaiementCommande"), r.getParameter("statutPaiementCommande"), r.getParameter("modeLivraisonCommande"), r.getParameter("statutLivraisonCommande"));
				commandeImpl.addCommandeByEmail(commande, client.getEmail());
				System.out.println("6");
				this.getServletContext().getRequestDispatcher("/WEB-INF/AfficheCommande.jsp").forward(r, response);
			}
		}
	}
}
