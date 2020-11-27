package com.omazon.dao;

import java.util.ArrayList;

import com.omazon.beans.Client;

public interface ClientDao {
	public ArrayList<Client> listClientAll();
	public Client listClientByMail(String mail);
	public void addClient(Client client);
}