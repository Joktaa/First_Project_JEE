<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bienvenue ${ client.prenom }</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
	<div>
		<c:forEach items="${ commandes }" var="commande">
			<p>
				Nom :
				<c:out value="${ commande.client.nom }"></c:out>
			</p>
			<p>
				Prenom :
				<c:out value="${ commande.client.prenom }"></c:out>
			</p>
			<p>
				Adresse :
				<c:out value="${ commande.client.adresse }"></c:out>
			</p>
			<p>
				Telephone :
				<c:out value="${ commande.client.telephone }"></c:out>
			</p>
			<p>
				Email :
				<c:out value="${ commande.client.email }"></c:out>
			</p>
			<p>
				Date :
				<c:out value="${ commande.date }"></c:out>
			</p>
			<p>
				Prix :
				<c:out value="${ commande.prix }"></c:out>
			</p>
			<p>
				Mode de paiement :
				<c:out value="${ commande.modePaiement }"></c:out>
			</p>
			<p>
				Statut du paiement :
				<c:out value="${ commande.statutPaiement }"></c:out>
			</p>
			<p>
				Mode de livraison :
				<c:out value="${ commande.modeLivraison }"></c:out>
			</p>
			<p>
				Statut de la livraison :
				<c:out value="${ commande.statutLivraison }"></c:out>
			</p>
			
			<hr />
		</c:forEach>
	</div>
</body>
</html>