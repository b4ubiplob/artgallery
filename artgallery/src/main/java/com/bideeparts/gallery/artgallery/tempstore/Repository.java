package com.bideeparts.gallery.artgallery.tempstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bideeparts.gallery.artgallery.model.to.GalleryTO;
import com.bideeparts.gallery.artgallery.model.to.PaintingTO;

public class Repository {
	
	private Repository() {}
	
	private static List<GalleryTO> galleries = new ArrayList<>();
	private static List<PaintingTO> paintings = new ArrayList<>();
	private static Map<String, byte[]> filesMap = new HashMap<>();
	private static int id = 1;
	
	public static GalleryTO addGallery(GalleryTO gallery) {
		gallery.setId("" + id++);
		galleries.add(gallery);
		return gallery;
	}
	
	public static List<GalleryTO> getAllGalleries() {
		return galleries;
	}
	
	public static PaintingTO addPainting(PaintingTO painting) {
		painting.setId("" + id++);
		String url = painting.getName().replace(" ", "_") + ".jpeg";
		painting.setUrl(url);
		paintings.add(painting);
		filesMap.put(url, painting.getContent());
		painting.setContent(null);
		return painting;
	}
	
	public static List<PaintingTO> getAllPaintings() {
		return paintings;
	}
	
	public static byte[] getImage(String name) {
		if (filesMap != null && !filesMap.isEmpty()) {
			return filesMap.get(name);
		}
		return new byte[] {};
		
	}

}
