package com.bideeparts.gallery.artgallery.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bideeparts.gallery.artgallery.model.entities.Dimension;
import com.bideeparts.gallery.artgallery.model.entities.Gallery;
import com.bideeparts.gallery.artgallery.model.entities.ImageFile;
import com.bideeparts.gallery.artgallery.model.entities.Painting;
import com.bideeparts.gallery.artgallery.model.to.PaintingTO;
import com.bideeparts.gallery.artgallery.repository.DimensionRepository;
import com.bideeparts.gallery.artgallery.repository.GalleryRepository;
import com.bideeparts.gallery.artgallery.repository.ImageFileRepository;
import com.bideeparts.gallery.artgallery.repository.PaintingRepository;
import com.bideeparts.gallery.artgallery.util.RandomIDGenerator;

@Service
public class PaintingService {

	@Autowired
	private PaintingRepository paintingRepository;

	@Autowired
	private GalleryRepository galleryRepository;

	@Autowired
	private ImageFileRepository imageFileRepository;

//	@Autowired
//	private DimensionRepository dimensionRepository;

	public PaintingTO createPainting(PaintingTO paintingTO) {

		if (isNamePresent(paintingTO.getName())) {
			throw new IllegalArgumentException("Painting with name already exists");
		}

		Painting painting = getPainting(paintingTO);
		Date date = new Date();
		painting.setCreatedOn(date);
		painting.setLastUpdatedOn(date);
		painting.setId(RandomIDGenerator.getRandomId());
		List<Gallery> galleries = new ArrayList<>();
		paintingTO.getGalleryIds().forEach(id -> galleries.add(galleryRepository.findById(id).get()));
		painting.setGalleries(galleries);

		ImageFile imageFile = new ImageFile();
		imageFile.setId(RandomIDGenerator.getRandomId());
		imageFile.setFileContent(paintingTO.getContent());
		imageFile.setType("jpeg");

		ImageFile savedFile = imageFileRepository.save(imageFile);
		painting.setImageFile(savedFile);

//		Dimension dimension = dimensionRepository.getOne(paintingTO.getDimensionId());
//		List<Dimension> dimensions = new ArrayList<>();
//		dimensions.add(dimension);
//		painting.setDimensions(dimensions);

		Painting savedPainting = paintingRepository.saveAndFlush(painting);
		return getPaintingTO(savedPainting);
	}

	private boolean isNamePresent(String name) {
		Optional<Painting> optional = paintingRepository.findByName(name);
		return optional.isPresent();
	}

	public List<PaintingTO> getAllPaintings() {
		List<PaintingTO> paintingTOs = new ArrayList<>();
		paintingRepository.findAll().forEach(painting -> paintingTOs.add(getPaintingTO(painting)));
		return paintingTOs;
	}

	private PaintingTO getPaintingTO(Painting painting) {
		PaintingTO paintingTO = new PaintingTO();
		paintingTO.setContent(null);
		paintingTO.setDescription(painting.getDescription());
		paintingTO.setId(painting.getId());
		paintingTO.setName(painting.getName());
		paintingTO.setUrl(painting.getUrl());
		return paintingTO;
	}

	private Painting getPainting(PaintingTO paintingTO) {
		Painting painting = new Painting();
		painting.setDescription(paintingTO.getDescription());
		painting.setName(paintingTO.getName());
		painting.setUrl(painting.getName().replace(" ", "_") + ".jpeg");
		return painting;
	}

	public byte[] getImage(String url) {
		Painting painting = paintingRepository.getImageFileOfPainting(url);
		if (painting != null) {
			return painting.getImageFile().getFileContent();
		}
		throw new IllegalArgumentException("Image with url not found " + url);
	}

}
