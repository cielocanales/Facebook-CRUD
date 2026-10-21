package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ModelException;
import model.User;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.utils.PasswordEncryptor;

@WebServlet(urlPatterns= {"/login", "/logout"} )
public class loginController extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		HttpSession session = req.getSession(false);
		if(session != null) session.invalidate();
		
		resp.sendRedirect("/facebook/login.jsp");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String userEmail = req.getParameter("user_login");
		String userPw = req.getParameter("user_pw");
		
		if (userEmail == null || userEmail.isBlank() || userPw == null || userPw.isBlank()) {
			resp.sendRedirect("/facebook/login.jsp?erro=true");
			return;
		}
		
		// Carregar o usuario do banco pelo email
		UserDAO dao = DAOFactory.createDAO(UserDAO.class);
		User userToLogin = null;
		
		try {
			userToLogin = dao.findByEmail(userEmail);
		} catch (ModelException e) {
			e.printStackTrace();
		}
		
		if (userToLogin == null) {
			resp.sendRedirect("/facebook/login.jsp?erro=true");
			return;
		}
		
		
		
		//Verificar se as senhas são iguais
		if(PasswordEncryptor.checkPassword(userPw, userToLogin.getPasswordHash()) == true) {
			req.getSession().setAttribute("user_logged", userToLogin);
			resp.sendRedirect("/facebook/");
		} else {
			resp.sendRedirect("/facebook/login.jsp?erro=true");
		}
		//Redirecionar para a pagina de index se a senha for correta
		
		
		//Redirecionar pra login se senha falhar
	}
	
}
