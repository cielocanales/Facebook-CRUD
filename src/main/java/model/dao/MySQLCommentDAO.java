package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.ModelException;
import model.Post;
import model.Comment;
import model.User;

public class MySQLCommentDAO implements CommentDAO {
	@Override
	public boolean save(Comment comment) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlInsert = "INSERT INTO comments VALUES "
				+ " (DEFAULT, ?, CURDATE(), ?, ?);";
		
		db.prepareStatement(sqlInsert);
		db.setString(1, comment.getContent());
		db.setInt(2, comment.getUser().getId());
		db.setInt(3, comment.getPost().getId());
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean update(Comment comment) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlUpdate = "UPDATE comments "
						 + " SET content = ? , "
						 + " user_id = ? ," 
						 + "post_id = ?"
						 + " WHERE id = ?;"
						 ;
		
		db.prepareStatement(sqlUpdate);
		
		db.setString(1, comment.getContent());
		db.setInt(2, comment.getUser().getId());
		db.setInt(3, comment.getPost().getId());
		db.setInt(4, comment.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(Comment comment) throws ModelException {
		
		DBHandler db = new DBHandler();
		
		String sqlDelete = " DELETE FROM comments "
		         + " WHERE id = ?;";

		db.prepareStatement(sqlDelete);		
		db.setInt(1, comment.getId());
		
		return db.executeUpdate() > 0;
	}

	@Override
	public List<Comment> listAll() throws ModelException {
	
		DBHandler db = new DBHandler();
		
		List<Comment> posts = new ArrayList<Comment>();
			
		// Declara um instrução SQL
		String sqlQuery = " SELECT c.*, "
                + "u.id AS user_id, u.nome AS user_name, "
                + "p.id AS post_id "
                + "FROM comments c "
                + "INNER JOIN users u ON c.user_id = u.id "
                + "INNER JOIN posts p ON c.post_id = p.id ";
		
		db.createStatement();
	
		db.executeQuery(sqlQuery);

		while (db.next()) {
			Comment p = createComment(db);
			
			posts.add(p);
		}
		
		return posts;
	}

	@Override
	public Comment findById(int id) throws ModelException {
		
		DBHandler db = new DBHandler();
				
		String sql = "SELECT * FROM comments WHERE id = ?;";
		
		db.prepareStatement(sql);
		db.setInt(1, id);
		db.executeQuery();
		
		Comment p = null;
		while (db.next()) {
			p = createComment(db);
			break;
		}
		
		return p;
	}
	
	private Comment createComment(DBHandler db) throws ModelException {
	    Comment comment = new Comment(db.getInt("id"));
	    comment.setContent(db.getString("content"));
	    comment.setCommentDate(db.getDate("comment_date"));
	    
	    
	    UserDAO userDAO = DAOFactory.createDAO(UserDAO.class); 
	    User user = userDAO.findById(db.getInt("user_id"));
	    comment.setUser(user);
	    
	    
	    PostDAO postDAO = DAOFactory.createDAO(PostDAO.class);
	    Post post = postDAO.findById(db.getInt("post_id"));
	    comment.setPost(post);
	    
	    return comment;
	}
}
