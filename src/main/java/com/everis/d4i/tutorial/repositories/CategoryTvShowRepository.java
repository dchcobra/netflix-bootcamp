package com.everis.d4i.tutorial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.d4i.tutorial.entities.CategoryTvShow;

@Repository
public interface CategoryTvShowRepository extends JpaRepository<CategoryTvShow, Long> {
	
	List<CategoryTvShow> findByTvShow_Id(Long tvShowId);

}
