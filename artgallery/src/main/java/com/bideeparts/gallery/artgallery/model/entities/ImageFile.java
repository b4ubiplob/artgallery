package com.bideeparts.gallery.artgallery.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the image_files database table.
 * 
 */
@Entity
@Table(name="image_files")
@NamedQuery(name="ImageFile.findAll", query="SELECT i FROM ImageFile i")
public class ImageFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="file_content")
	private byte[] fileContent;

	private String type;

	//bi-directional many-to-one association to Painting
	@OneToMany(mappedBy="imageFile")
	private List<Painting> paintings;

	public ImageFile() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getFileContent() {
		return this.fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Painting> getPaintings() {
		return this.paintings;
	}

	public void setPaintings(List<Painting> paintings) {
		this.paintings = paintings;
	}

	public Painting addPainting(Painting painting) {
		getPaintings().add(painting);
		painting.setImageFile(this);

		return painting;
	}

	public Painting removePainting(Painting painting) {
		getPaintings().remove(painting);
		painting.setImageFile(null);

		return painting;
	}

}