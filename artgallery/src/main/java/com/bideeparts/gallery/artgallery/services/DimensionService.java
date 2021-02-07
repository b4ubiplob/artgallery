package com.bideeparts.gallery.artgallery.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bideeparts.gallery.artgallery.model.entities.Dimension;
import com.bideeparts.gallery.artgallery.model.to.DimensionTO;
import com.bideeparts.gallery.artgallery.repository.DimensionRepository;
import com.bideeparts.gallery.artgallery.util.RandomIDGenerator;

@Service
public class DimensionService {
	
	@Autowired
	private DimensionRepository dimensionRepository;
	
	public DimensionTO createDimension(DimensionTO dimensionTO) {
		Dimension dimension = getDimension(dimensionTO);
		dimension.setId(RandomIDGenerator.getRandomId());
		return getDimensionTO(dimensionRepository.saveAndFlush(dimension));
	}
	
	public List<DimensionTO> getAllDimensions() {
		List<DimensionTO> dimensionTOs = new ArrayList<>();
		dimensionRepository.findAll().forEach(dimension -> dimensionTOs.add(getDimensionTO(dimension)));
		return dimensionTOs;
	}
	
	public DimensionTO getDimension(String id) {
		return getDimensionTO(dimensionRepository.getOne(id));
	}
	
	public void updateDimension(String id, DimensionTO dimension) {
		
	}
	
	public void deleteDimension(String id) {
		dimensionRepository.deleteById(id);
	}
	
	private Dimension getDimension(DimensionTO dimensionTO) {
		Dimension dimension = new Dimension();
		dimension.setBreadth(dimensionTO.getBreadth());
		dimension.setLength(dimensionTO.getLength());
		dimension.setUnit(dimensionTO.getUnit());
		return dimension;
	}
	
	private DimensionTO getDimensionTO(Dimension dimension) {
		DimensionTO dimensionTO = new DimensionTO();
		dimensionTO.setBreadth(dimension.getBreadth());
		dimensionTO.setId(dimension.getId());
		dimensionTO.setLength(dimension.getLength());
		dimensionTO.setUnit(dimension.getUnit());
		return dimensionTO;
	}
	

}
