<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'une commande</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
    </head>
    <body>
    	<c:if test="${ !empty error }"><c:out value="${ error }"/></c:if>
        <div>
        	<button OnClick="formPLS()">Cliquez pour afficher le client</button>
        	<button OnClick="emailPLS()">Cliquez pour afficher le mail</button>
            <form method="post" action="CreationCommande">
                <fieldset id="formClient">
                    <%@ include file="formClient.jsp" %>
                </fieldset>
                <fieldset>
                    <legend>Informations commande</legend>
                    
                    <span id="email" style="visibility: hidden">
                    	<label for="emailNotSet">Email <span class="requis">*</span></label>
                    	<input type="email" id="emailNotSet" name="emailNotSet" value="" size="20" maxlength="50"/>
                    	<br />
                    </span>
                    
                    <label for="montantCommande">Montant <span class="requis">*</span></label>
                    <input type="text" id="montantCommande" name="montantCommande" value="" size="20" maxlength="20" />
                    <br />
                    
                    <label for="modePaiementCommande">Mode de paiement <span class="requis">*</span></label>
                    <input type="text" id="modePaiementCommande" name="modePaiementCommande" value="" size="20" maxlength="20" />
                    <br />
                    
                    <label for="statutPaiementCommande">Statut du paiement</label>
                    <input type="text" id="statutPaiementCommande" name="statutPaiementCommande" value="" size="20" maxlength="20" />
                    <br />
                    
                    <label for="modeLivraisonCommande">Mode de livraison <span class="requis">*</span></label>
                    <input type="text" id="modeLivraisonCommande" name="modeLivraisonCommande" value="" size="20" maxlength="20" />
                    <br />
                    
                    <label for="statutLivraisonCommande">Statut de la livraison</label>
                    <input type="text" id="statutLivraisonCommande" name="statutLivraisonCommande" value="" size="20" maxlength="20" />
                    <br />
                </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
        
        <script>
        	const formPLS = () => {
        		console.log("oui");
        		const form = document.getElementById("formClient");
        		const email = document.getElementById("email");
        		form.style.visibility = "visible";
        		email.style.visibility = "hidden";
        	}
        	
        	const emailPLS = () => {
        		const form = document.getElementById("formClient");
        		const email = document.getElementById("email");
        		form.style.visibility = "hidden";
        		email.style.visibility = "visible";
        	}
        </script>
    </body>
</html>