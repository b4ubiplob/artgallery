package com.bideeparts.gallery.artgallery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bideeparts.gallery.artgallery.model.entities.Painting;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, String> {
	
	@Query("SELECT p FROM Painting p WHERE p.url = ?1")
	public Painting findByUrl(String url);
	
	@Query("SELECT p FROM Painting p JOIN FETCH p.imageFile WHERE p.url = ?1")
	public Painting getImageFileOfPainting(String url);
	
	@Query("SELECT p FROM Painting p where p.name = ?1")
	public Optional<Painting> findByName(String name);

}
