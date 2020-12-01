package com.bideeparts.website.gallery.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;


/**
 * The persistent class for the dimension database table.
 * 
 */
@Entity
@Table(name = "t_dimension")
@NamedQuery(name="Dimension.findAll", query="SELECT d FROM Dimension d")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Dimension implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(precision = 5, scale = 2)
	private double breadth;

	@Column(precision = 5, scale = 2)
	private double length;

	private String unit;

	//bi-directional many-to-one association to Painting
	@ManyToOne
	private Painting painting;

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

	public Painting getPainting() {
		return this.painting;
	}

	public void setPainting(Painting painting) {
		this.painting = painting;
	}

}