package com.bideeparts.gallery.artgallery.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the likes database table.
 * 
 */
@Entity
@Table(name="likes")
@NamedQuery(name="Like.findAll", query="SELECT l FROM Like l")
public class Like implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="comment_id")
	private String commentId;

	@Column(name="painting_id")
	private String paintingId;

	@Column(name="user_id")
	private String userId;

	public Like() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCommentId() {
		return this.commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getPaintingId() {
		return this.paintingId;
	}

	public void setPaintingId(String paintingId) {
		this.paintingId = paintingId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}