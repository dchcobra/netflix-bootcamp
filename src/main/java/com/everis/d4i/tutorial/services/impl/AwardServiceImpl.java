package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.repositories.AwardRepository;
import com.everis.d4i.tutorial.services.AwardService;

@Service
public class AwardServiceImpl implements AwardService {
	
	@Autowired
	private AwardRepository AwardRepository;

	private ModelMapper modelMapper = new ModelMapper();
	/*
	public List<AwardRest> getAwards() throws NetflixException {

		return AwardRepository.findAll().stream().map(Award -> modelMapper.map(Award, AwardRest.class))
				.collect(Collectors.toList());

	}
	*/
	@Override
	public List<AwardRest> getAwardsByTvShow(Long tvShowId) throws NetflixException {
		return AwardRepository.findByTvShow_Id(tvShowId).stream()
				.map(award -> modelMapper.map(award, AwardRest.class)).collect(Collectors.toList());
	}
}

