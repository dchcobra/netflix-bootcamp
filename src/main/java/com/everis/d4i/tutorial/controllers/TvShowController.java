package com.everis.d4i.tutorial.controllers;

import java.util.List;

import javax.validation.Valid;

import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

public interface TvShowController {

	NetflixResponse<List<TvShowRest>> getTvShowsByCategory(Long categoryId) throws NetflixException;

	NetflixResponse<TvShowRest> getTvShowById(Long id) throws NetflixException;

	void deleteTvShowById(Long id) throws NetflixException;

	NetflixResponse<TvShowRest> updateTvShowName(Long id, @Valid TvShowRest tvShowRest) 
			throws InternalServerErrorException;

	NetflixResponse<TvShowRest> getTvShowByName(String name) throws NetflixException;
	
}
