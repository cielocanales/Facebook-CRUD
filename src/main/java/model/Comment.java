package model;

import java.util.Calendar;
import java.util.Date;

public class Comment {
	private int id;
	private String content;
	private Date commentDate;
	private User user;
	private Post post;
	
	public Comment() {
	}
	
	public Comment(int id) {
		this.id = id;
		setContent("");
		setCommentDate(Calendar.getInstance().getTime());
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date postDate) {
		this.commentDate = postDate;
	}
	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
