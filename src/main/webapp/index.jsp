<%@ page import="java.util.List, br.com.sefaz.entities.Usuario" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Home</title>
</head>
<body>
	<h1>Lista de Usuarios:</h1>
	<ul>
		<c:forEach items="${usuarios}" var="usuario">
			<li>${usuario.nome}</li>
		</c:forEach>
	</ul>
</body>
</html>