package com.bideeparts.gallery.artgallery.model.to;

public class GalleryTO {
	
	private String id;
	private String name;
	private String description;
	private String coverImageUrl;
	
	public GalleryTO() {
	}
	
	public GalleryTO(String id, String name, String description, String coverImageUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.coverImageUrl = coverImageUrl;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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

	public String getCoverImageUrl() {
		return coverImageUrl;
	}

	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}
	
	

}
