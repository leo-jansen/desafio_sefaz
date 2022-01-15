<%@ page import="java.util.List, br.com.sefaz.entities.Usuario" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
		integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<title>Login</title>
</head>

<body>
	<header class="navbar navbar-expand navbar-dark bg-primary d-flex justify-content-between mb-3">
		<a class="navbar-brand" href="/sefaz/home">HOME</a>
		<a class="btn btn-outline-light" href="/sefaz/logout" id="logout">Logout</a>
	</header>
	<div class="container">
		<nav class="navbar navbar-expand navbar-light bg-light d-flex justify-content-between mb-3">
      <div class="navbar-nav">
        <a class="nav-item nav-link" href="/sefaz/home">Listar usuarios</a>
      </div>
      <a href="/sefaz/incluirUsuario"><button class="btn btn-outline-secondary my-2 my-sm-0">Novo Usuario</button></a>
    </nav>
		<c:forEach items="${usuarios}" var="usuario">
			<div class="card mb-2">
				<div class="card-header">${usuario.nome}</div>
				<div class="card-body">
					<p class="card-text">Email: ${usuario.email}</p>
					<p class="card-text">${usuario.telefone.toString()}</p>
					<a href="/sefaz/editar?id=${usuario.id}"><button class="btn btn-secondary">Editar</button></a>
					<a href="/sefaz/remover?id=${usuario.id}"><button class="btn btn-danger">Excluir</button></a>
				</div>
			</div>
		</c:forEach> 
	</div>
		<!-- JQuery para o bootstrap -->
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
			integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
			crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
			integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
			crossorigin="anonymous"></script>
</body>

</html>