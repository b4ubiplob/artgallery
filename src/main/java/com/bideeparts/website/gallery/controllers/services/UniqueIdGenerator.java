package com.bideeparts.website.gallery.controllers.services;

import java.util.UUID;

public class UniqueIdGenerator {
	
	public static String getRandomId() {
		return UUID.randomUUID().toString();
	}

}
