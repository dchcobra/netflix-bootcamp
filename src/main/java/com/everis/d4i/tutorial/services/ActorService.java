package com.everis.d4i.tutorial.services;

import java.util.Date;
import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;

public interface ActorService {
	
	List<ActorRest> getActors() throws NetflixException;

	ActorRest createActors(ActorRest ActorRest) throws NetflixException;
	
	ActorRest getActorById(Long id) throws NetflixException;

	void deleteActorById(Long id) throws NetflixException;

	void updateActor(Long id, String firstName, String secondName, Date dateOfBirth);

}
