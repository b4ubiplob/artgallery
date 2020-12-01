package com.bideeparts.website.gallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bideeparts.website.gallery.model.Dimension;

@Repository
public interface DimensionRepository extends JpaRepository<Dimension, String> {

}
