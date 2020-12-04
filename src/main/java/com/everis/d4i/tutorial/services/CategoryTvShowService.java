package com.everis.d4i.tutorial.services;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CategoryTvShowRest;

public interface CategoryTvShowService {

	List<CategoryTvShowRest> getCategoryTvShowByTvShow(Long tvShowId) throws NetflixException;

}
