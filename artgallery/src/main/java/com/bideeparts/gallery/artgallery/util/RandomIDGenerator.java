package com.bideeparts.gallery.artgallery.util;

import java.util.UUID;

public class RandomIDGenerator {
	
	public static String getRandomId() {
		return UUID.randomUUID().toString();
	}

}
