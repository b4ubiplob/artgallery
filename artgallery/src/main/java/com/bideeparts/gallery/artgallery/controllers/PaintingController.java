package com.bideeparts.gallery.artgallery.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bideeparts.gallery.artgallery.model.to.PaintingTO;
import com.bideeparts.gallery.artgallery.services.PaintingService;

@RestController
@RequestMapping("/api/v1/paintings")
public class PaintingController {
	
	@Autowired
	private PaintingService paintingService;

	@PostMapping(produces = { "application/json" }, consumes = { "multipart/form-data" })
	public PaintingTO createPainting( @RequestParam("name") String name, 
			@RequestParam("description") String description,
			@RequestParam("content") MultipartFile file, 
			@RequestParam("galleryIds") List<String> galleryIds
//			@RequestParam("dimensionId") String dimensionId
			) {
		PaintingTO painting = new PaintingTO();
		painting.setName(name);
		painting.setDescription(description);
		painting.setGalleryIds(galleryIds);
		//painting.setDimensionId(dimensionId);
		try {
			painting.setContent(file.getBytes());
		} catch (IOException e) {
			throw new IllegalArgumentException("Could not upload file");
		}
		return paintingService.createPainting(painting);

	}

	@GetMapping
	public List<PaintingTO> getAllPaintings() {
		return paintingService.getAllPaintings();
	}

	@GetMapping(value="/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImage(@PathVariable String name) {
		return paintingService.getImage(name);
	}
	
}
