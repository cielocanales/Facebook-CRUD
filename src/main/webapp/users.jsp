<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Usuários - Facebook CRUD</title>
	    <!-- Bootstrap CSS -->
       <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
</head>

<body>
	<div class="container">
		<div class="row">
			<div class = "col-md-2"></div>
			<div class = "col-md-8">

			<h2>Usuarios</h1>
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Nome</th>
						<th scope="col">Sexo</th>
						<th scope="col">Email</th>
						<th  scope="col">Acoes</th>
					</tr>
					
				</thead>
				<tbody>
				
					<c:forEach var="user" items="${users_list}">
                        <tr>
                            <th scope="row">${user.id}</th>
                            <td>${user.name}</td>
                            <td>${user.gender}</td>
                            <td>${user.email}</td>
                            <td>
                            <a href="${pageContext.request.contextPath}/users/update?userId=${user.getId()}" class="bi bi-pencil-square"></a>
                            <a href="${pageContext.request.contextPath}/users/delete?userId=${user.getId()}" class="bi bi-trash"></a>
                            
                            </td>
                        </tr>
                    </c:forEach>
					
				</tbody>
			</table>
			<a href="users_form.jsp" class="btn btn-primary">Cadastrar Usuário</a>
</div>
<div class="col-md-2"></div>
		</div>
	</div>

	  <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>