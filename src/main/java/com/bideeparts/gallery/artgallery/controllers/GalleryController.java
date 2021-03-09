package com.bideeparts.gallery.artgallery.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bideeparts.gallery.artgallery.model.to.GalleryTO;
import com.bideeparts.gallery.artgallery.model.to.PaintingTO;
import com.bideeparts.gallery.artgallery.services.GalleryService;

@RestController
@RequestMapping("/api/v1/galleries")
public class GalleryController {
	
	private static final Logger logger = LoggerFactory.getLogger(GalleryController.class);
	@Autowired
	private GalleryService galleryService;
	
	@GetMapping
	public List<GalleryTO> getAllGalleries() {
		logger.info("getting all galleries");
		return galleryService.getAllGalleries();
	}
	
	@GetMapping(value = "/{id}")
	public GalleryTO getGallery(@PathVariable String id) {
		logger.info("getting a single gallery");
		return galleryService.getGasllery(id);
	}
	
	@GetMapping(value = "/{id}/paintings")
	public List<PaintingTO> getPaintingsOfGallery(@PathVariable String id) {
		return galleryService.getPaintingsOfGallery(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GalleryTO createGallery(@RequestBody GalleryTO gallery) {
		logger.info("Creating gallery");
		return galleryService.createGallery(gallery);
	}
	
	@DeleteMapping
	@RequestMapping("/{id}")
	public void deleteGallery(@PathVariable String id) {
		logger.info("Deleting gallery with id : " + id);
		galleryService.deleteGallery(id);
	}
	
	@PutMapping(value = "/{id}")
	public GalleryTO updateGallery(@PathVariable String id, @RequestBody GalleryTO galleryTO) {
		logger.info("updating gallery with id : " + id);
		return galleryService.updateGallery(id, galleryTO);
	}
}
