package com.omazon.beans;

import java.time.LocalDate;

public class Commande {
	private Client client;
	private LocalDate date;
	private String prix;
	private String modePaiement;
	private String StatutPaiement;
	private String modeLivraison;
	private String statutLivraison;
	
	
	public Commande(Client client, LocalDate date, String prix, String modePaiement, String statutPaiement,
			String modeLivraison, String statutLivraison) {
		this.client = client;
		this.date = date;
		this.prix = prix;
		this.modePaiement = modePaiement;
		this.StatutPaiement = statutPaiement;
		this.modeLivraison = modeLivraison;
		this.statutLivraison = statutLivraison;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public String getPrix() {
		return prix;
	}


	public void setPrix(String prix) {
		this.prix = prix;
	}


	public String getModePaiement() {
		return modePaiement;
	}


	public void setModePaiement(String modePaiement) {
		this.modePaiement = modePaiement;
	}


	public String getStatutPaiement() {
		return StatutPaiement;
	}


	public void setStatutPaiement(String statutPaiement) {
		StatutPaiement = statutPaiement;
	}


	public String getModeLivraison() {
		return modeLivraison;
	}


	public void setModeLivraison(String modeLivraison) {
		this.modeLivraison = modeLivraison;
	}


	public String getStatutLivraison() {
		return statutLivraison;
	}


	public void setStatutLivraison(String statutLivraison) {
		this.statutLivraison = statutLivraison;
	}
}
