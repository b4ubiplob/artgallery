package com.bideeparts.website.gallery.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the painting database table.
 * 
 */
@Entity
@Table(name = "t_paintings")
@NamedQuery(name="Painting.findAll", query="SELECT p FROM Painting p")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Painting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private byte[] content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_on")
	private Date createdOn;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updated_on")
	private Date lastUpdatedOn;

	private String name;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="painting")
	private List<Comment> comments;

	//bi-directional many-to-one association to Dimension
	@OneToMany(mappedBy="painting")
	private List<Dimension> dimensions;

	//bi-directional many-to-many association to Category
	@ManyToMany
	@JoinTable(
		name="painting_has_category"
		, joinColumns={
			@JoinColumn(name="painting_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="category_id")
			}
		)
	private List<Category> categories;

	public Painting() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getContent() {
		return this.content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastUpdatedOn() {
		return this.lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setPainting(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setPainting(null);

		return comment;
	}

	public List<Dimension> getDimensions() {
		return this.dimensions;
	}

	public void setDimensions(List<Dimension> dimensions) {
		this.dimensions = dimensions;
	}

	public Dimension addDimension(Dimension dimension) {
		getDimensions().add(dimension);
		dimension.setPainting(this);

		return dimension;
	}

	public Dimension removeDimension(Dimension dimension) {
		getDimensions().remove(dimension);
		dimension.setPainting(null);

		return dimension;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}