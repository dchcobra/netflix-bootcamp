package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CategoryTvShowRest;
import com.everis.d4i.tutorial.repositories.CategoryTvShowRepository;
import com.everis.d4i.tutorial.services.CategoryTvShowService;

@Service
public class CategoryTvShowServiceImpl implements CategoryTvShowService {
	@Autowired
	private CategoryTvShowRepository CategoryTvShowRepository;

	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<CategoryTvShowRest> getCategoryTvShowByTvShow(Long tvShowId) throws NetflixException {
		return CategoryTvShowRepository.findByTvShow_Id(tvShowId).stream()
				.map(categoryTvShow -> modelMapper.map(categoryTvShow, CategoryTvShowRest.class)).collect(Collectors.toList());
	}
}