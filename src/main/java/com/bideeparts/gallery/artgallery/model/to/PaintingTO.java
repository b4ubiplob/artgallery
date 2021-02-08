package com.bideeparts.gallery.artgallery.model.to;

import java.util.List;

public class PaintingTO {

	private String id;
	private String name;
	private String description;
	private byte[] content;
	private String url;
	private List<String> galleryIds;
	private String dimensionId;
	private double length;
	private double breadth;
	private String unit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public List<String> getGalleryIds() {
		return galleryIds;
	}

	public void setGalleryIds(List<String> galleryIds) {
		this.galleryIds = galleryIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDimensionId() {
		return dimensionId;
	}

	public void setDimensionId(String dimensionId) {
		this.dimensionId = dimensionId;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getBreadth() {
		return breadth;
	}

	public void setBreadth(double breadth) {
		this.breadth = breadth;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}


}
