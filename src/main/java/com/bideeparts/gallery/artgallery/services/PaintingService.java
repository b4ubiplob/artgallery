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

	@Autowired
	private DimensionRepository dimensionRepository;

	public PaintingTO createPainting(PaintingTO paintingTO) {

		if (isNamePresent(paintingTO.getName())) {
			throw new IllegalArgumentException("Painting with name already exists");
		}

		Painting painting = getPainting(paintingTO);
		Date date = new Date();
		painting.setCreatedOn(date);
		painting.setLastUpdatedOn(date);
		painting.setId(RandomIDGenerator.getRandomId());
		
		painting.setGalleries(getGalleries(paintingTO));

		ImageFile imageFile = new ImageFile();
		imageFile.setId(RandomIDGenerator.getRandomId());
		imageFile.setFileContent(paintingTO.getContent());
		imageFile.setType("jpeg");

		ImageFile savedFile = imageFileRepository.save(imageFile);
		painting.setImageFile(savedFile);

		Dimension dimension = dimensionRepository.getOne(paintingTO.getDimensionId());
		painting.setDimension(dimension);

		Painting savedPainting = paintingRepository.saveAndFlush(painting);
		return getPaintingTO(savedPainting);
	}

	private boolean isNamePresent(String name) {
		Optional<Painting> optional = paintingRepository.findByName(name);
		return optional.isPresent();
	}
	
	public PaintingTO getPainting(String id) {
		Optional<Painting> optional = paintingRepository.findById(id);
		if (optional.isPresent()) {
			Painting painting = optional.get();
			PaintingTO paintingTO = getPaintingTO(painting);
			List<String> galleryIds = new ArrayList<>();
			for (Gallery gallery : painting.getGalleries()) {
				galleryIds.add(gallery.getId());
			}
			paintingTO.setGalleryIds(galleryIds);
			paintingTO.setDimensionId(painting.getDimension().getId());
			return paintingTO;
		}
		return null;
	}

	public List<PaintingTO> getAllPaintings() {
		List<PaintingTO> paintingTOs = new ArrayList<>();
		paintingRepository.findAll().forEach(painting -> paintingTOs.add(getPaintingTO(painting)));
		return paintingTOs;
	}
	
	public void deletePainting(String id) {
		paintingRepository.deleteById(id);
	}
	
	public PaintingTO updatePainting(String id, PaintingTO paintingTO) {
		Optional<Painting> optional = paintingRepository.findById(id);
		if (optional.isPresent()) {
			Painting painting = optional.get();
			getPainting(paintingTO, painting);
			painting.setGalleries(getGalleries(paintingTO));
			painting.setDimension(dimensionRepository.getOne(paintingTO.getDimensionId()));
			Painting savedPainting = paintingRepository.save(painting);
			return getPaintingTO(savedPainting);
		}
		return null;
	}
	
	public List<Gallery> getGalleries(PaintingTO paintingTO) {
		List<Gallery> galleries = new ArrayList<>();
		paintingTO.getGalleryIds().forEach(id -> galleries.add(galleryRepository.findById(id).get()));
		return galleries;

	}

	private PaintingTO getPaintingTO(Painting painting) {
		PaintingTO paintingTO = new PaintingTO();
		paintingTO.setContent(null);
		paintingTO.setDescription(painting.getDescription());
		paintingTO.setId(painting.getId());
		paintingTO.setName(painting.getName());
		paintingTO.setUrl(painting.getUrl());
		paintingTO.setBreadth(painting.getDimension().getBreadth());
		paintingTO.setLength(painting.getDimension().getLength());
		paintingTO.setUnit(painting.getDimension().getUnit());
		return paintingTO;
	}
	
	private void getPainting(PaintingTO paintingTO, Painting painting) {
		painting.setDescription(paintingTO.getDescription());
		painting.setName(paintingTO.getName());
		painting.setUrl(painting.getName().replace(" ", "_") + ".jpeg");
	}
	
	private Painting getPainting(PaintingTO paintingTO) {
		Painting painting = new Painting();
		getPainting(paintingTO, painting);
		return painting;
	}

	public byte[] getImage(String url) {
		Painting painting = paintingRepository.getImageFileOfPainting(url);
		if (painting != null) {
			return painting.getImageFile().getFileContent();
		}
		throw new IllegalArgumentException("Image with url not found " + url);
	}

	public List<PaintingTO> getPaintingsOfGallery(String galleryId) {
		List<PaintingTO> paintingTOs = new ArrayList<>();
		galleryRepository.getPaintingsOfGallery(galleryId).forEach(painting -> paintingTOs.add(getPaintingTO(painting)));
		return paintingTOs;
	}

}
