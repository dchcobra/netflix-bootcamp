package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.services.ActorService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class ActorServiceImpl implements ActorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActorServiceImpl.class);

	@Autowired
	private ActorRepository ActorRepository;

	private ModelMapper modelMapper = new ModelMapper();

	//give all users on JSON
	public List<ActorRest> getActors() throws NetflixException {

		return ActorRepository.findAll().stream().map(Actor -> modelMapper.map(Actor, ActorRest.class))
				.collect(Collectors.toList());

	}

	//create user
	public ActorRest createActors(final ActorRest ActorRest) throws NetflixException {
		Actor Actor = new Actor();
		Actor.setFirstName(ActorRest.getFirstName());
		Actor.setSecondName(ActorRest.getSecondName());
		Actor.setDateOfBirth(ActorRest.getDateOfBirth());
		try {
			Actor = ActorRepository.save(Actor);
		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(Actor, ActorRest.class);
	}
	//give a actor that you introduce id
	public ActorRest getActorById(Long id) throws NetflixException {

		try {
			return modelMapper.map(ActorRepository.getOne(id), ActorRest.class);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}

	}
	
	//delete actor that you introduce id
	public void deleteActorById(Long id) throws NetflixException {

		try {
			ActorRepository.deleteById(id);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}

	}

	public void updateActor(Long id, String firstName, String secondName, Date dateOfBirth) {
		Actor myActor = ActorRepository.findByid(id);
		myActor.setFirstName(firstName);
		myActor.setSecondName(secondName);
		myActor.setDateOfBirth(dateOfBirth);
		ActorRepository.save(myActor);
	}
	
	
}
