package com.bideeparts.gallery.artgallery.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the comments database table.
 * 
 */
@Entity
@Table(name="comments")
@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_on")
	private Date createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_on")
	private Date updatedOn;

	//bi-directional many-to-one association to Comment
	@ManyToOne
	@JoinColumn(name="comments_id")
	private Comment comment;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="comment")
	private List<Comment> comments;

	//bi-directional many-to-one association to Painting
	@ManyToOne
	private Painting painting;

	//bi-directional many-to-one association to RegisteredUser
	@ManyToOne
	@JoinColumn(name="user_id")
	private RegisteredUser registeredUser;

	public Comment() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setComment(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setComment(null);

		return comment;
	}

	public Painting getPainting() {
		return this.painting;
	}

	public void setPainting(Painting painting) {
		this.painting = painting;
	}

	public RegisteredUser getRegisteredUser() {
		return this.registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}

}