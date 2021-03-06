<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="resources/img/icone-uberlandia-mototaxi.ico">
<meta http-equiv="X-UA-Compatible" content="IE-edge">
<meta name="description" content="">
<meta name="author" content="Anna Carolina Novaes">
<meta charset="UTF-8">

<title>Uberl�ndia Motot�xi</title>

<!--  +++++++++++ REFERENCIAS BASICAS DO BOOTSTRAP +++++++++++ -->
<%@ include file="/resources/includes/refs-bootstrap-inicio.jsp"%>
</head>

<body>
	<!--  +++++++++++ BARRA DE NAVEGACAO +++++++++++ -->
	<%@ include file="/resources/includes/barra-superior.jsp"%>

	<div id="main" class="container-fluid">
		<div class="section">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<h1>Cadastro da Empresa</h1>
						<p>Por gentileza, preencha os dados solicitados para que o
							cadastro seja efetivado.</p>
					</div>
				</div>
			</div>
		</div>

		<div class="section">
			<div class="container">

				<form method="post"
					action="<%=request.getContextPath()%>/cliente/EmpresaCRUD"
					name="formEmpr">

					<div class="row">
						<div class="form group col-md-6" style="visibility: hidden;">
							<label for="codEmpresa"> C�digo: </label> <input type="text"
								name="codEmpresa" value="${empr.codEmpresa}"
								class="form-control" id="codEmpresa" readonly="readonly" /> <br />
						</div>
					</div>

					<div class="row">
						<div class="form group col-md-6">
							<label for="nomefantasia"> Nome fantasia: </label> <input
								type="text" name="nomeFantasia" value="${empr.nomeFantasia}"
								class="form-control" id="nomeFantasia"
								placeholder="Digite o nome fantasia da Empresa" /> <br />
						</div>
					</div>

					<div class="row">
						<div class="form group col-md-6">
							<label for="razaoSocial"> Raz�o social: </label> <input
								type="text" name="razaoSocial" value="${empr.razaoSocial}"
								class="form-control" id="razaoSocial"
								placeholder="Digite a raz�o social" /> <br />
						</div>
					</div>

					<div class="row">
						<div class="form group col-md-6">
							<label for="telefone"> Telefone </label> <input type="text"
								name="telefone" value="${empr.telefone}" class="form-control"
								id="telefone" placeholder="Digite o telefone"/> <br />
						</div>
					</div>

					<div class="row">
						<div class="form group col-md-6">
							<label for="email"> E-mail </label> <input type="text"
								name="email" value="${empr.email}" class="form-control"
								id="email" placeholder="Digite o e-mail" /> <br />
						</div>
					</div>

					<div class="row">
						<div class="form group col-md-6">
							<label for="cnpj"> CNPJ </label> <input type="text" name="cnpj"
								value="${empr.cnpj}" class="form-control" id="cnpj" placeholder="Digite o CNPJ"/>  <br />
						</div>
					</div>

					<div class="row">
						<div class="form group col-md-6">
							<label for="logradouro"> Logradouro </label> <input type="text"
								name="logradouro" value="${empr.logradouro}"
								class="form-control" id="logradouro" placeholder="Digite o logradouro"/> <br />
						</div>
					</div>

					<div class="row">
						<div class="form group col-md-6">
							<label for="numero"> N�mero </label> <input type="text"
								name="numero" value="${empr.numero}" class="form-control"
								id="numero" placeholder="Digite o n�mero"/> <br />
						</div>
					</div>

					<div class="row">
						<div class="form group col-md-6">
							<label for="bairro"> Bairro </label> <input type="text"
								name="bairro" value="${empr.bairro}" class="form-control"
								id="bairro" placeholder="Digite o nome do bairro"/> <br />
						</div>
					</div>

					<div class="row">
						<div class="form group col-md-6">
							<label for="cep"> CEP </label> <input type="text" name="cep"
								value="${empr.cep}" class="form-control" id="cep" placeholder="Digite o CEP"/> <br />
						</div>
					</div>


					<div id="actions" class="row">
						<div class="col-md-12">
							<button type="submit" class="btn btn-success btn-xs">Enviar</button>
							<a href="<%=request.getContextPath()%>/index.jsp"
								class="btn btn-danger btn-xs">Cancelar</a>
						</div>
					</div>


					<!-- Exemplo de data com JSTL: 
		 Data do pedido: <input
		 type="text" name="dataPedido"
		 value="<fmt:formatDate pattern="dd/MM/yyyy" value="${pedido.dataPedido}" />" /> <br />
		 -->
				</form>
			</div>
		</div>
		'
	</div>
</body>
</html>