package com.bideeparts.gallery.artgallery.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bideeparts.gallery.artgallery.model.entities.Gallery;
import com.bideeparts.gallery.artgallery.model.to.GalleryTO;
import com.bideeparts.gallery.artgallery.repository.GalleryRepository;
import com.bideeparts.gallery.artgallery.util.RandomIDGenerator;

@Service
public class GalleryService {
	
	private static final Logger logger = LoggerFactory.getLogger(GalleryService.class);
	
	@Autowired
	private GalleryRepository galleryRepository;
	
	public List<GalleryTO> getAllGalleries() {
		logger.info("getting all galleries from service");
		List<GalleryTO> galleryTOs = new ArrayList<>();
		galleryRepository.findAll().stream().forEach((gallery) -> galleryTOs.add(getGalleryTO(gallery)));
		return galleryTOs;
	}
	
	public GalleryTO getGasllery(String id) {
		return getGalleryTO(galleryRepository.getOne(id));
	}
	
	public GalleryTO createGallery(GalleryTO galleryTO) {
		logger.info("creating gallery in service");
		Gallery gallery = getGallery(galleryTO);
		gallery.setId(RandomIDGenerator.getRandomId());
		Date date = new Date();
		gallery.setCreatedOn(date);
		gallery.setUpdatedOn(date);
		Gallery savedGallery = galleryRepository.saveAndFlush(gallery);
		return getGalleryTO(savedGallery);
	}
	
	public void deleteGallery(String id) {
		Optional<Gallery> optional = galleryRepository.findById(id);
		if (optional.isPresent()) {
			Gallery gallery = optional.get();
			if (gallery.getPaintings() != null && !gallery.getPaintings().isEmpty()) {
				throw new IllegalArgumentException("Gallery is not empty");
			}
			galleryRepository.deleteById(id);
		}
		
	}

	public GalleryTO updateGallery(String id, GalleryTO galleryTO) {
		Optional<Gallery> optional = galleryRepository.findById(id);
		if (optional.isPresent()) {
			Gallery gallery = optional.get();
			getGallery(galleryTO, gallery);
			return getGalleryTO(galleryRepository.saveAndFlush(gallery));
			
		}
		throw new IllegalArgumentException("Gallery not found with id " + id);
	}
	
	private Gallery getGallery(GalleryTO galleryTO) {
		Gallery gallery = new Gallery();
		return getGallery(galleryTO, gallery);
	}
	
	private Gallery getGallery(GalleryTO galleryTO, Gallery gallery) {
		gallery.setName(galleryTO.getName());
		gallery.setDescription(galleryTO.getDescription());
		return gallery;
	}
	
	private GalleryTO getGalleryTO(Gallery gallery) {
		GalleryTO galleryTO = new GalleryTO();
		galleryTO.setDescription(gallery.getDescription());
		galleryTO.setId(gallery.getId());
		galleryTO.setName(gallery.getName());
		return galleryTO;
	}

}
