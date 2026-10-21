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
			<div class="row mt-3">
				<div class="col-8">
					<a class="btn btn-secondary" href="${pageContext.request.contextPath}/posts">Voltar</a>
				</div>
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<h1>Cadastro de Posts</h1>


					<form action="/facebook/posts/save" method="GET" class="">

						<div class="mb-3">
							<input type="hidden" id="post_id_id" name="post_id" value="${post.getId()}">
							<label for="post_content_id" class="form-label">Conteúdo</label>
							<textarea id="post_content_id" name="post_content" rows="5"
								placeholder="Digite seu post aqui..."
								class="form-control">${post.getContent()}</textarea>
						</div>



						<div class="mb-3">

							<label for="post_user_id" class="form-label">Usuário</label>
							<select name="post_user" id="post_user_id" class="form-control">

								<option value="" disabled selected>Selecione um usuário...</option>

								<c:forEach var="user" items="${users_list}">
									<option value="${user.getId()}" ${post.getUser().getId()==user.getId() ? 'selected'
										: '' }> ${user.getName()}</option>
								</c:forEach>

							</select>
						</div>

						<div class="mb-3">

						</div>





						<button type="submit" class="btn btn-primary">Cadastrar
						</button>

					</form>


				</div>

			</div>
		</div>

	</body>

	</html>