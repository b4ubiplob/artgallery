package com.bideeparts.gallery.artgallery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bideeparts.gallery.artgallery.model.entities.Gallery;
import com.bideeparts.gallery.artgallery.model.entities.Painting;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, String> {
	
	@Query("SELECT g.paintings FROM Gallery g where g.id = ?1")
	public List<Painting> getPaintingsOfGallery(String id);

}
