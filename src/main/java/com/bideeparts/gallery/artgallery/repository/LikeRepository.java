package com.bideeparts.gallery.artgallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bideeparts.gallery.artgallery.model.entities.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, String> {

}
