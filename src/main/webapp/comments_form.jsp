<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Gestão de Comentários</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
</head>

<body>
	<div class="container">
		<div class="row mt-3">
			<div class="col-8">
				<a class="btn btn-secondary"
					href="${pageContext.request.contextPath}/comments">Voltar</a>
			</div>
			
			<div class="col-md-2"></div>
			<div class="col-md-8">


				<h1>Cadastro de Comentários</h1>


				<form action="/facebook/comments/save" method="GET" class="">

					<div class="mb-3">
						<input type="hidden" id="comment_id_id" name="comment_id"
							value="${comment.getId()}"> <label
							for="comment_content_id" class="form-label">Comentário</label>
						<textarea id="comment_content_id" name="comment_content" rows="5"
							placeholder="Digite seu comentário aqui..." class="form-control">${comment.getContent()}</textarea>
					</div>



					<div class="mb-3">

						<label for="comment_user_id" class="form-label">Usuário</label> <select
							name="comment_user" id="comment_user_id" class="form-control">

							<option value="" disabled selected>Selecione um
								usuário...</option>

							<c:forEach var="user" items="${users_list}">
								<option value="${user.getId()}"
									${comment.getUser().getId() == user.getId() ? 'selected' : '' }>
									${user.getName()}</option>
							</c:forEach>

						</select>
					</div>


					<div class="mb-3">

						<label for="comment_post_id" class="form-label">Post</label> <select
							name="comment_post" id="comment_post_id" class="form-control">

							<option value="" disabled selected>Selecione um post
								para comentar...</option>

							<c:forEach var="post" items="${posts_list}">
								<option value="${post.getId()}"
									${comment.getPost().getId() == post.getId() ? 'selected' : '' }>
									${post}</option>
							</c:forEach>

						</select>
					</div>


					<div class="mb-3"></div>


					<button type="submit" class="btn btn-primary">Cadastrar</button>

				</form>


			</div>

		</div>
	</div>

</body>

</html>