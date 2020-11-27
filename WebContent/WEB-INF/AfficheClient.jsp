<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bienvenue ${ client.prenom }</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>

<c:out value="${ client.email }"></c:out>

<body>
	<div>

		<c:forEach items="${ clients }" var="client">
			<p>
				Nom :
				<c:out value="${ client.nom }"></c:out>
			</p>
			<p>
				Prenom :
				<c:out value="${ client.prenom }"></c:out>
			</p>
			<p>
				Adresse :
				<c:out value="${ client.adresse }"></c:out>
			</p>
			<p>
				Telephone :
				<c:out value="${ client.telephone }"></c:out>
			</p>
			<p>
				Email :
				<c:out value="${ client.email }"></c:out>
			</p>
			<hr />
		</c:forEach>
	</div>
</body>
</html>