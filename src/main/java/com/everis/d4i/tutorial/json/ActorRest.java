package com.everis.d4i.tutorial.json;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorRest implements Serializable {

	private static final long serialVersionUID = 2562292635410148858L;
	
	private Long id;
	private String firstName;
	private String secondName;
	private Date dateOfBirth;
	private TvShowRest tvshow;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
		
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getSecondName() {
		return secondName;
	}
	
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public TvShowRest getTvshow() {
		return tvshow;
	}

	public void setTvshow(TvShowRest tvshow) {
		this.tvshow = tvshow;
	}	
}
