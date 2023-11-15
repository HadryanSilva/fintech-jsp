<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<title>Cadastro Fintech</title>
		<link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<div class="main">
			<section class="signup">
				<div class="container">
					<div class="signup-content">
						<div class="signup-form">
							<h2 class="form-title">Cadastro</h2>
							<form method="POST" action="CadastroServlet" class="register-form"
								id="register-form">
								<div class="form-group">
									<label for="name"><i
										class="zmdi zmdi-account material-icons-name"></i></label> <input
										type="text" name="name" id="name" placeholder="Nome Completo" />
								</div>
								<div class="form-group">
									<label for="email"><i class="zmdi zmdi-email"></i></label> <input
										type="email" name="email" id="email" placeholder="E-mail" />
								</div>
								<div class="form-group">
									<label for="password"><i class="zmdi zmdi-lock"></i></label> <input
										type="password" name="password" id="password" placeholder="Senha" />
								</div>
								<div class="form-group">
									<label for="nome"><i class="zmdi zmdi-lock-outline"></i></label>
									<input type="text" name="nome" id="nome"
										placeholder="Nome da Conta" />
								</div>
								<div class="form-group">
									<label for="descricao"><i class="zmdi zmdi-lock-outline"></i></label>
									<input type="text" name="descricao" id="descricao"
										   placeholder="Descricao da Conta" />
								</div>
								<div class="form-group">
									<input type="checkbox" name="agree-term" id="agree-term"
										class="agree-term" /> <label for="agree-term"
										class="label-agree-term"><span><span></span></span>Eu concordo com todos
									os <a href="#" class="term-service">Termos de Serviço</a></label>
								</div>
								<div class="form-group form-button">
									<input type="submit" name="signup" id="signup"
										class="form-submit" value="Cadastrar" />
								</div>
							</form>
						</div>
						<div class="signup-image">
							<figure>
								<img src="images/signup-image.jpg" alt="sing up image">
							</figure>
							<a href="index.jsp" class="signup-image-link">Já tenho uma conta</a>
						</div>
					</div>
				</div>
			</section>
		</div>
		<script src="vendor/jquery/jquery.min.js"></script>
		<script src="js/main.js"></script>
	</body>
</html>