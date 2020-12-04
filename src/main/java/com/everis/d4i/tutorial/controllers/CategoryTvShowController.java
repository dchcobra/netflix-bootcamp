package com.everis.d4i.tutorial.controllers;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CategoryTvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;


public interface CategoryTvShowController {

	NetflixResponse<List<CategoryTvShowRest>> getCategoryTvShowByTvShow(int tvShowId) throws NetflixException;

}
