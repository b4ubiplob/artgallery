package com.bideeparts.website.gallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bideeparts.website.gallery.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

}
