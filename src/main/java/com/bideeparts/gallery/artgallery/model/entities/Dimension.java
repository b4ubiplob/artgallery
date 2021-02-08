package com.bideeparts.gallery.artgallery.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the dimension database table.
 * 
 */
@Entity
@NamedQuery(name="Dimension.findAll", query="SELECT d FROM Dimension d")
public class Dimension implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private double breadth;

	private double length;

	private String unit;

	//bi-directional many-to-one association to Painting
	@OneToMany(mappedBy="dimension")
	private List<Painting> paintings;

	public Dimension() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getBreadth() {
		return this.breadth;
	}

	public void setBreadth(double breadth) {
		this.breadth = breadth;
	}

	public double getLength() {
		return this.length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<Painting> getPaintings() {
		return this.paintings;
	}

	public void setPaintings(List<Painting> paintings) {
		this.paintings = paintings;
	}

	public Painting addPainting(Painting painting) {
		getPaintings().add(painting);
		painting.setDimension(this);

		return painting;
	}

	public Painting removePainting(Painting painting) {
		getPaintings().remove(painting);
		painting.setDimension(null);

		return painting;
	}

}