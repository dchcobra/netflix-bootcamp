package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.TvShowService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class TvShowServiceImpl implements TvShowService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TvShowServiceImpl.class);


	@Autowired
	private TvShowRepository tvShowRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<TvShowRest> getTvShowsByCategory(Long categoryId) throws NetflixException {
		try {
			tvShowRepository.findById(categoryId).get();
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}
		return tvShowRepository.findByCategoryId(categoryId).stream()
				.map(tvShow -> modelMapper.map(tvShow, TvShowRest.class)).collect(Collectors.toList());

	}

	@Override
	public TvShowRest getTvShowById(Long id) throws NetflixException {
		try {
			tvShowRepository.findById(id).get();
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}
		return modelMapper.map(tvShowRepository.findById(id).get(), TvShowRest.class);

	}
	
	@Override
	public TvShowRest getTvShowByName(String name) throws NetflixException {
		try {
			tvShowRepository.findByName(name);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}
		return modelMapper.map(tvShowRepository.findByName(name), TvShowRest.class);
	}
	
	



	@Override
	public void deleteTvShowById(Long id) throws NetflixException {
		try {
			tvShowRepository.deleteById(id);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}
	}
	
	@Override
	public TvShowRest updateTvShowName(Long id, String name) throws InternalServerErrorException {
		TvShow myTvShow = tvShowRepository.findById(id).get();
		myTvShow.setName(name);
		try {
			myTvShow = tvShowRepository.save(myTvShow);
		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(myTvShow, TvShowRest.class);
	}
	
}
