<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Gestão de Usuários</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
	</head>

	<body>
		<div class="container">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
				
					<div class="mb-3"></div>
					<h1>Cadastro de Usuário</h1>
					<form action="/facebook/users/save" method="GET" class="">
					
					<div class="mb-3">
					<input type="hidden" id="user_id_id" name="user_id" value="${user.getId()}">
					<label for="user_name_id"class="form-label">Nome</label>
					<input type="text" id="user_name_id" name="user_name" value="${user.getName()}" class="form-control">
					</div>
					
					<div>
					<label for="user_email_id"class="form-label">Email</label>
					<input type="text" id="user_email_id" name="user_email" value="${user.getEmail()}" class="form-control">
					</div>
							
				    <div class="mb-3"></div>		
				    <label>Genero</label>
				    <div class="mb-3"></div>
				    
					<div class="form-check">
					<input id="user_gender_m_id"type="radio"value="M" class="form-check-input" name="user_gender"
					${user.getGender().equals("M") ? "checked" : ""} >
					
					<label class="form-check-label" for="user_gender_m_id">Masculino</label>
					</div>
												
							
					<div class="form-check">
					<input id="user_gender_f_id"type="radio"value="F" class="form-check-input" name="user_gender"
					${user.getGender().equals("F") ? "checked" : ""} >
					
					<label class="form-check-label" for="user_gender_f_id">Femenino</label>
					</div>
					
				    <div class="mb-3"></div>
								
					<div>
					<label for="user_pw_id"class="form-label">Senha</label>
					<input type="password" id="user_pw_id" name="user_pw" class="form-control">
					</div>
					
					<div class="mb-3"></div>
					<button type="submit" class="btn btn-primary">Cadastrar</button>
					</form>
				</div>

			</div>
		</div>

	</body>

	</html>