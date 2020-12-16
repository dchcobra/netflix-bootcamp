package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.entities.CategoryTvShow;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CategoryTvShowRest;
import com.everis.d4i.tutorial.repositories.CategoryRepository;
import com.everis.d4i.tutorial.repositories.CategoryTvShowRepository;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.CategoryTvShowService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class CategoryTvShowServiceImpl implements CategoryTvShowService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryTvShowServiceImpl.class);

	@Autowired
	private CategoryTvShowRepository CategoryTvShowRepository;

	@Autowired
	private TvShowRepository tvShowRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<CategoryTvShowRest> getCategoryTvShowByTvShow(Long tvShowId) throws NetflixException {
		return CategoryTvShowRepository.findByTvShowId(tvShowId).stream()
				.map(categoryTvShow -> modelMapper.map(categoryTvShow, CategoryTvShowRest.class)).collect(Collectors.toList());
	}

	
	@Override
	public CategoryTvShowRest addCategoryToTvShow(CategoryTvShowRest categoryTvShowRest) throws NetflixException {
		CategoryTvShow categoryTvShow = new CategoryTvShow();
		TvShow tvShow = tvShowRepository.findById(categoryTvShowRest.getTvshow().getId()).get();
		Category category = categoryRepository.findById(categoryTvShowRest.getCategory().getId()).get();
		categoryTvShow.setTvShow(tvShow);
		categoryTvShow.setCategory(category);
		try {
			categoryTvShow = CategoryTvShowRepository.save(categoryTvShow);
		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(categoryTvShow, CategoryTvShowRest.class);
	}
}
