package com.everis.d4i.tutorial.controllers.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.services.ActorService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.everis.d4i.tutorial.controllers.ActorController;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_ACTOR)
public class ActorControllerImpl implements ActorController {

	@Autowired
	private ActorService actorService;
	
	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<List<ActorRest>> getActors() throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				actorService.getActors());
	}
	
	@Override
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<ActorRest> createActor(
			@ApiParam(value = RestConstants.PARAMETER_ACTOR, required = true) @RequestBody @Valid final ActorRest actorRest)
			throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				actorService.createActors(actorRest));
	}

	@Override
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public NetflixResponse<ActorRest> getActorById(@PathVariable Long id) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				actorService.getActorById(id));
	}
	
	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value = RestConstants.RESOURCE_ID)
	public void deleteActorById(@PathVariable Long id) throws NetflixException {
		actorService.deleteActorById(id); 
	}
	
	
	@Override
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	
	public NetflixResponse<ActorRest> modifyActor(@PathVariable Long id, @RequestBody ActorRest actor) throws NetflixException {
		return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
				actorService.modifyActor(id, actor));		
	  }
}
