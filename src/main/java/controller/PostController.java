package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelException;
import model.Post;
import model.User;
import model.dao.DAOFactory;
import model.dao.PostDAO;
import model.dao.UserDAO;
import model.dao.PostDAO;


@WebServlet(urlPatterns = {"/posts", "/posts/setupRegistration", "/posts/save", "/posts/update", "/posts/delete"})
public class PostController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String action = req.getRequestURI();
        
        switch(action) {
            case "/facebook/posts": {
                loadPosts(req);
                RequestDispatcher dispatcher = req.getRequestDispatcher("posts.jsp");
                dispatcher.forward(req, resp);
                break;
            }
            
            case "/facebook/posts/setupRegistration": {
                loadUsers(req);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/posts_form.jsp");
                dispatcher.forward(req, resp);
                break;
            }
            
            case "/facebook/posts/update": {
                loadUsers(req);
                loadPost(req);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/posts_form.jsp");
                dispatcher.forward(req, resp);
                break;
            }
            
            case "/facebook/posts/delete": {
                deletePost(req);
                resp.sendRedirect("/facebook/posts");
                break;  
            }
            
            case "/facebook/posts/save": {
                saveOrUpdatePost(req);
                resp.sendRedirect("/facebook/posts");
                break;
            }
            
            default: {
                String errorMsg = "Endpoint não suportado: " + action + ".";
                throw new IllegalArgumentException(errorMsg);
            }
        }
    }
    
    private void deletePost(HttpServletRequest req) {
        String idParam = req.getParameter("postId");
        int id = Integer.parseInt(idParam);
        
        
        Post postTarget = new Post();
        postTarget.setId(id);
        
        PostDAO postDao = DAOFactory.createDAO(PostDAO.class);
        
        try {
            postDao.delete(postTarget);
        } catch (ModelException e) {
            e.printStackTrace();
        }
    }
    
    private void saveOrUpdatePost(HttpServletRequest req) {
        String textContent = req.getParameter("post_content");
        String userIdParam = req.getParameter("post_user");
        String postIdParam = req.getParameter("post_id");
        
        int userId = Integer.parseInt(userIdParam);
        User author = loadUser(userId);
        
        boolean isEditing = false;
        
        Post postObj = new Post();
        postObj.setContent(textContent);
        postObj.setUser(author);
        
        if (postIdParam != null && !postIdParam.isBlank()) {
            postObj.setId(Integer.parseInt(postIdParam));
            isEditing = true;
        }
        
        PostDAO postDao = DAOFactory.createDAO(PostDAO.class);
        
        try {
            if (isEditing) {
                postDao.update(postObj);
            } else {
                postDao.save(postObj);
            }
        } catch (ModelException e) {
            e.printStackTrace();
        }
    }
    
    private User loadUser(int id) {
        UserDAO userDao = DAOFactory.createDAO(UserDAO.class);
        User foundUser = null;
        
        try {
            foundUser = userDao.findById(id);
        } catch (ModelException e) {
            e.printStackTrace();
        }
        
        return foundUser;
    }
    
    private void loadUsers(HttpServletRequest req) {
        UserDAO userDao = DAOFactory.createDAO(UserDAO.class);
        List<model.User> userList = List.of();
        
        try {
            userList = userDao.listAll();
        } catch(ModelException e) {
            e.printStackTrace();
        }
        
        req.setAttribute("users_list", userList); 
    }
    
    private void loadPosts(HttpServletRequest req) {
        PostDAO postDao = DAOFactory.createDAO(PostDAO.class);
        List<model.Post> postList = List.of();
        
        try {
            postList = postDao.listAll();
        } catch(ModelException e) {
            e.printStackTrace();
        }
        
        req.setAttribute("posts_list", postList); 
    }
    
    private void loadPost(HttpServletRequest req) {
        String idParam = req.getParameter("postId");
        int id = Integer.parseInt(idParam);
        
        PostDAO postDao = DAOFactory.createDAO(PostDAO.class);
        Post targetPost = null;
        
        try {
            targetPost = postDao.findById(id);
        } catch(ModelException e) {
            e.printStackTrace();
        }
        
        req.setAttribute("post", targetPost);
    }
}