package com.omazon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.omazon.beans.Client;

public class ClientDaoImpl implements ClientDao {

	private DaoFactory daoFactory;
	ClientDaoImpl(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	@Override
	public ArrayList<Client> listClientAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<Client> clients = new ArrayList<Client>();
		Client client = null;
		
		try {
			connection = daoFactory.getConnexion();
			statement = connection.prepareStatement("SELECT * FROM Client");
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				client = new Client(resultSet.getString(3), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
				clients.add(client);
			}
			connection.close();
		} catch(Exception e){e.printStackTrace();}
		
		return clients;
	}

	@Override
	public Client listClientByMail(String email) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Client client = null;
		
		try {
			connection = daoFactory.getConnexion();
			statement = connection.prepareStatement("SELECT * FROM Client WHERE email = ?");
			statement.setString(1, email);
			resultSet = statement.executeQuery();
			resultSet.next();
			
			client = new Client(resultSet.getString(3), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
			
			connection.close();
		} catch(Exception e){e.printStackTrace();}
		
		return client;
	}

	@Override
	public void addClient(Client client) {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = daoFactory.getConnexion();
			statement = connection.prepareStatement("INSERT INTO Client(prenom, nom, adresse, telephone, email) VALUES (?,?,?,?,?)");
			statement.setString(1, client.getPrenom());
			statement.setString(2, client.getNom());
			statement.setString(3, client.getAdresse());
			statement.setString(4, client.getTelephone());
			statement.setString(5, client.getEmail());

			statement.execute();
			
			connection.close();
		} catch(Exception e){e.printStackTrace();}
	}
	
}
