package com.omazon.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.omazon.beans.Client;
import com.omazon.dao.ClientDaoImpl;
import com.omazon.dao.DaoFactory;

/**
 * Servlet implementation class AfficheCLientServlet
 */
public class AfficheClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientDaoImpl clientImpl = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficheClientServlet() {
        super();
        DaoFactory daoFactory = DaoFactory.getInstance();
        clientImpl = daoFactory.getClientImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Client> clients = new ArrayList<Client>();
		clients = clientImpl.listClientAll();
		
		request.setAttribute("clients", clients);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/AfficheClient.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
