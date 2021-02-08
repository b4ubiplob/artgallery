package com.bideeparts.gallery.artgallery.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the registered_user database table.
 * 
 */
@Entity
@Table(name="registered_user")
@NamedQuery(name="RegisteredUser.findAll", query="SELECT r FROM RegisteredUser r")
public class RegisteredUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_on")
	private Date createdOn;

	private String email;

	private String password;

	@Lob
	@Column(name="profile_pic")
	private byte[] profilePic;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_on")
	private Date updatedOn;

	private String username;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="registeredUser")
	private List<Comment> comments;

	public RegisteredUser() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getProfilePic() {
		return this.profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}

	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setRegisteredUser(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setRegisteredUser(null);

		return comment;
	}

}