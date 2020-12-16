package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.ActorService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class ActorServiceImpl implements ActorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActorServiceImpl.class);

	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private TvShowRepository tvShowRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	//Give all users on JSON
	@Override
	public List<ActorRest> getActors() throws NetflixException {
		try {
			actorRepository.findAll();
		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return actorRepository.findAll().stream().map(actor -> modelMapper.map(actor, ActorRest.class))
				.collect(Collectors.toList());
	}

	//Create user with JSON require firstName, secondName, dateOfBirdth and id of tvShow to select a tvshow
	@Override
	public ActorRest createActors(ActorRest actorRest) throws NetflixException {
		Actor actor = new Actor();
		TvShow tvShow = tvShowRepository.findById(actorRest.getTvShow().getId()).get();
		actor.setFirstName(actorRest.getFirstName());
		actor.setSecondName(actorRest.getSecondName());
		actor.setDateOfBirth(actorRest.getDateOfBirth());
		actor.setTvShow(tvShow);
		try {
			actor = actorRepository.save(actor);
		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(actor, ActorRest.class);
	}
	
	//Give a actor that you introduce id
	@Override
	public ActorRest getActorById(Long id) throws NetflixException {
		try {
			actorRepository.findById(id).get();
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}
		return modelMapper.map(actorRepository.findById(id).get(), ActorRest.class);
	}
	
	
	//Delete actor that you introduce id
	@Override
	public void deleteActorById(Long id) throws NetflixException {
		try {
			actorRepository.deleteById(id);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}

	}
	
	//Modify actor that you introduce id
	@Override
	public ActorRest modifyActor(Long id, ActorRest actor) throws InternalServerErrorException {
		Actor updateActor = actorRepository.findById(id).get();
		TvShow tvShow = tvShowRepository.findById(actor.getTvShow().getId()).get();
		updateActor.setFirstName(actor.getFirstName());
		updateActor.setSecondName(actor.getSecondName());
		updateActor.setDateOfBirth(actor.getDateOfBirth());
		updateActor.setTvShow(tvShow);
		try {
			updateActor = actorRepository.save(updateActor);
		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(updateActor, ActorRest.class);
	}	
}
