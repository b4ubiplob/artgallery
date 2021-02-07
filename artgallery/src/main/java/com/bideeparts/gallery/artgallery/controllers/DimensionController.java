package com.bideeparts.gallery.artgallery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bideeparts.gallery.artgallery.model.to.DimensionTO;
import com.bideeparts.gallery.artgallery.services.DimensionService;

@RestController
@RequestMapping("/api/v1/dimensions")
public class DimensionController {
	
	@Autowired
	private DimensionService dimensionService;
	
	@GetMapping
	public List<DimensionTO> getAllDimensions() {
		return dimensionService.getAllDimensions();
	}
	
	@PostMapping
	public DimensionTO createDimension(@RequestBody DimensionTO dimensionTO) {
		return dimensionService.createDimension(dimensionTO);
	}
	
	@GetMapping(value = "/{id}")
	public DimensionTO getDimension(@PathVariable String id) {
		return dimensionService.getDimension(id);
	}

}
