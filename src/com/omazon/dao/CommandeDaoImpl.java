package com.omazon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import com.omazon.beans.Client;
import com.omazon.beans.Commande;

public class CommandeDaoImpl implements CommandeDao {

	private DaoFactory daoFactory;
	CommandeDaoImpl(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	@Override
	public ArrayList<Commande> listeCommandeAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<Commande> commandes = new ArrayList<Commande>();
		Commande commande = null;
		Client client = null;
		
		try {
			connection = daoFactory.getConnexion();
			statement = connection.prepareStatement("SELECT * FROM Commande");
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				client = getClientById(resultSet.getInt(2));
				commande = new Commande(client, convertToLocalDateViaInstant(resultSet.getDate("date")), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
				commandes.add(commande);
			}
			connection.close();
		} catch(Exception e){e.printStackTrace();}
		
		return commandes;
	}

	@Override
	public ArrayList<Commande> listCommandeByCLient(String email) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<Commande> commandes = new ArrayList<Commande>();
		Commande commande = null;
		Client client = null;
		
		try {
			connection = daoFactory.getConnexion();
			statement = connection.prepareStatement("SELECT * FROM Commande co JOIN Client cl ON (cl.id = co.client) WHERE cl.email = ?;");
			statement.setString(1, email);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				client = getClientById(resultSet.getInt(2));
				commande = new Commande(client, convertToLocalDateViaInstant(resultSet.getDate("date")), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
				commandes.add(commande);
			}
			connection.close();
		} catch(Exception e){e.printStackTrace();}
		
		return commandes;
	}

	@Override
	public void addCommandeByEmail(Commande commande, String email) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = daoFactory.getConnexion();
			statement = connection.prepareStatement("SELECT id FROM Client WHERE email = ?");
			statement.setString(1, email);
			resultSet = statement.executeQuery();
			resultSet.next();
			int idClient = resultSet.getInt(1);
			
			
			statement = connection.prepareStatement("INSERT INTO Commande(client, date, prix, modePaiement, statutPaiement, modeLivraison, statutLivraison) VALUES (? , NOW(), ?, ?, ?, ?, ?);");
			statement.setInt(1, idClient);
			statement.setString(2, commande.getPrix());
			statement.setString(3, commande.getModePaiement());
			statement.setString(4, commande.getStatutPaiement());
			statement.setString(5, commande.getModeLivraison());
			statement.setString(6, commande.getStatutLivraison());

			statement.execute();
			
			connection.close();
		} catch(Exception e){e.printStackTrace();}
	}

	
	@Override
	public void addCommandeByClient(Commande commande, Client client) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = daoFactory.getConnexion();
			ClientDaoImpl clientImpl = daoFactory.getClientImpl();
			
			clientImpl.addClient(client);
			String email = client.getEmail();
			
			
			statement = connection.prepareStatement("SELECT id FROM Client WHERE email = ?");
			statement.setString(1, email);
			resultSet = statement.executeQuery();
			resultSet.next();
			int idClient = resultSet.getInt(1);
			
			
			statement = connection.prepareStatement("INSERT INTO Commande(client, date, prix, modePaiement, statutPaiement, modeLivraison, statutLivraison) VALUES (? , NOW(), ?, ?, ?, ?, ?);");
			statement.setInt(1, idClient);
			statement.setString(2, commande.getPrix());
			statement.setString(3, commande.getModePaiement());
			statement.setString(4, commande.getStatutPaiement());
			statement.setString(5, commande.getModeLivraison());
			statement.setString(6, commande.getStatutLivraison());

			statement.execute();
			
			connection.close();
		} catch(Exception e){e.printStackTrace();}
	}

	private Client getClientById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Client client = null;
		
		try {
			connection = daoFactory.getConnexion();
			statement = connection.prepareStatement("SELECT * FROM Client WHERE id = ?");
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			resultSet.next();
			
			client = new Client(resultSet.getString(2), resultSet.getString(1), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
			
			connection.close();
		} catch(Exception e){e.printStackTrace();}
		
		return client;
	}
	
	private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return Instant.ofEpochMilli(dateToConvert.getTime())
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}
}
