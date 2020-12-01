package com.bideeparts.website.gallery.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the likes database table.
 * 
 */
@Entity
@Table(name="t_likes")
@NamedQuery(name="Like.findAll", query="SELECT l FROM Like l")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Like implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private String userId;

	@Column(name="comment_id")
	private String commentId;

	@Column(name="painting_id")
	private String paintingId;

	public Like() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

}