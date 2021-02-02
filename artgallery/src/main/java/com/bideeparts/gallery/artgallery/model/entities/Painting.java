package com.bideeparts.gallery.artgallery.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the painting database table.
 * 
 */
@Entity
@NamedQuery(name="Painting.findAll", query="SELECT p FROM Painting p")
public class Painting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_on")
	private Date createdOn;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updated_on")
	private Date lastUpdatedOn;

	@Column(name="name", unique = true)
	private String name;
	
	@Column(name="url", unique = true)
	private String url;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="painting")
	private List<Comment> comments;

	//bi-directional many-to-one association to Dimension
	@OneToMany(mappedBy="painting")
	private List<Dimension> dimensions;

	//bi-directional many-to-many association to Gallery
	@ManyToMany
	@JoinTable(
		name="painting_has_category"
		, joinColumns={
			@JoinColumn(name="painting_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="gallery_id")
			}
		)
	private List<Gallery> galleries;
	
	//bi-directional many-to-one association to ImageFile
	@ManyToOne
	@JoinColumn(name="image_files_id")
	private ImageFile imageFile;


	public Painting() {
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
	
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
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

	public List<Gallery> getGalleries() {
		return this.galleries;
	}

	public void setGalleries(List<Gallery> galleries) {
		this.galleries = galleries;
	}
	
	public ImageFile getImageFile() {
		return this.imageFile;
	}

	public void setImageFile(ImageFile imageFile) {
		this.imageFile = imageFile;
	}

}