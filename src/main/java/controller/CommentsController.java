package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Comment;
import model.ModelException;
import model.Post;
import model.User;
import model.dao.CommentDAO;
import model.dao.DAOFactory;
import model.dao.PostDAO;
import model.dao.UserDAO;

@WebServlet(urlPatterns = { "/comments", "/comments/save", "/comments/setupRegistration", "/comments/update", "/comments/delete"})
public class CommentsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getRequestURI();

        switch (action) {

            case "/facebook/comments": {
                loadComments(req);
                RequestDispatcher dispatcher = req.getRequestDispatcher("comments.jsp");
                dispatcher.forward(req, resp);
                break;
            }

            case "/facebook/comments/setupRegistration": {
                loadUsers(req);
                loadPosts(req);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/comments_form.jsp");
                dispatcher.forward(req, resp);
                break;
            }

            case "/facebook/comments/update": {
                loadUsers(req);
                loadPosts(req);
                loadComment(req);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/comments_form.jsp");
                dispatcher.forward(req, resp);
                break;
            }
            
            case "/facebook/comments/delete": {
                deleteComment(req);
                resp.sendRedirect("/facebook/comments");
                break;
            }

            case "/facebook/comments/save": {
                saveOrUpdateComment(req);
                resp.sendRedirect("/facebook/comments");
                break;
            }

            default: {
                String errorMsg = "URL não suportada: " + action + ".";
                throw new IllegalArgumentException(errorMsg);
            }

        }
    }

    private void saveOrUpdateComment(HttpServletRequest req) {

        String textContent = req.getParameter("comment_content");
        String updateIdParam = req.getParameter("comment_id");
        String userIdParam = req.getParameter("comment_user");
        String postIdParam = req.getParameter("comment_post");

        int userId = Integer.parseInt(userIdParam);
        int postId = Integer.parseInt(postIdParam);
        
        User author = loadUser(userId);
        Post targetPost = loadPost(postId);

        boolean isEditing = false;

        Comment commentObj = new Comment();
        commentObj.setContent(textContent);
        commentObj.setUser(author);
        commentObj.setPost(targetPost);

        if (updateIdParam != null && !updateIdParam.isBlank()) {
            commentObj.setId(Integer.parseInt(updateIdParam));
            isEditing = true;
        }

        CommentDAO commentDao = DAOFactory.createDAO(CommentDAO.class);

        try {

            if (isEditing) {
                commentDao.update(commentObj);
            } else {
                commentDao.save(commentObj);
            }

        } catch (ModelException e) {
            e.printStackTrace();
        }
    }
    
    private void deleteComment(HttpServletRequest req) {
        String deleteIdParam = req.getParameter("commentId");
        int deleteId = Integer.parseInt(deleteIdParam);
        
        Comment commentTarget = new Comment();
        commentTarget.setId(deleteId);
        
        CommentDAO commentDao = DAOFactory.createDAO(CommentDAO.class);
        
        try {
            commentDao.delete(commentTarget);
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
        } catch (ModelException e) {
            e.printStackTrace();
        }

        req.setAttribute("users_list", userList);
    }

    private Post loadPost(int id) {

        PostDAO postDao = DAOFactory.createDAO(PostDAO.class);
        Post foundPost = null;
        
        try {
            foundPost = postDao.findById(id);
        } catch (ModelException e) {
            e.printStackTrace();
        }

        return foundPost;

    }

    private void loadPosts(HttpServletRequest req) {
        PostDAO postDao = DAOFactory.createDAO(PostDAO.class);
        List<model.Post> postList = List.of();

        try {
            postList = postDao.listAll();
        } catch (ModelException e) {
            e.printStackTrace();
        }

        req.setAttribute("posts_list", postList);
    }

    private void loadComment(HttpServletRequest req) {

        String commentIdParam = req.getParameter("commentId");
        int commentId = Integer.parseInt(commentIdParam);

        CommentDAO commentDao = DAOFactory.createDAO(CommentDAO.class);
        Comment targetComment = null;

        try {
            targetComment = commentDao.findById(commentId);
        } catch (ModelException e) {
            e.printStackTrace();
        }

        req.setAttribute("comment", targetComment);

    }

    private void loadComments(HttpServletRequest req) {
        CommentDAO commentDao = DAOFactory.createDAO(CommentDAO.class);
        List<Comment> commentList = List.of();

        try {
            commentList = commentDao.listAll();
        } catch (ModelException e) {
            System.err.println("Erro ao carregar comentários do banco:");
            e.printStackTrace();
        }

        req.setAttribute("comments_list", commentList);
    }
}