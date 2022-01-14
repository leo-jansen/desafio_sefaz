<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
		integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<title>Cadastro de Usuario</title>
</head>

<body>
	<header class="navbar navbar-expand navbar-dark bg-primary d-flex justify-content-between mb-3">
		<a class="navbar-brand" href="/sefaz/home">HOME</a>
		<c:if test="${conected == false}">
			<a class="btn btn-outline-light" href="/sefaz/login">Login</a>
		</c:if>
		<c:if test="${conected == true}">
			<a class="btn btn-outline-light" href="/sefaz/logout">Logout</a>
		</c:if>
	</header>
	<div class="container">
		<form action="/sefaz/cadastrarUsuario" method="post" class="card-body">
			<div class="form-group">
				<label for="nome" class="form-label">Nome</label>
				<input type="text" class="form-control" id="nome" name="nome" required>
			</div>
			<div class="form-group">
				<label for="senha" class="form-label">Senha</label>
				<input type="password" class="form-control" id="senha" name="senha" required>
			</div>
			<div class="form-group">
				<label for="email" class="form-label">Email</label>
				<input type="text" class="form-control" id="email" name="email" required>
			</div>
			<div class="form-group">
				<label for="ddd" class="form-label">DDD</label>
				<input type="number" class="form-control" id="ddd" name="ddd" required>
			</div>
			<div class="form-group">
				<label for="numero" class="form-label">Numero</label>
				<input type="text" class="form-control" id="numero" name="numero" required>
			</div>
			<div class="form-group">
				<label for="tipo">Tipo</label>
				<select class="form-control" id="tipo" name="tipo">
					<option>Celular</option>
					<option>Telefone Fixo</option>
				</select>
			</div>
			<button type="submit" class="btn btn-primary">Cadastrar</button>
		</form>
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