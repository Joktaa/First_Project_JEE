package com.omazon.dao;

import java.util.ArrayList;

import com.omazon.beans.Client;
import com.omazon.beans.Commande;


public interface CommandeDao {
	public ArrayList<Commande> listeCommandeAll();
	public ArrayList<Commande> listCommandeByCLient(String email);
	public void addCommandeByEmail(Commande commande, String email);
	public void addCommandeByClient(Commande commande, Client client);
}