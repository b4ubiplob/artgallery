package com.bideeparts.gallery.artgallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bideeparts.gallery.artgallery.model.entities.ImageFile;

public interface ImageFileRepository extends JpaRepository<ImageFile, String>{

	
	
}
