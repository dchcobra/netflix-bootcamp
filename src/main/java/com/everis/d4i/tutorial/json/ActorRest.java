package com.everis.d4i.tutorial.json;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorRest implements Serializable {

	private static final long serialVersionUID = 2562292635410148858L;
	
	private Long id;
	private String firstName;
	private String secondName;
	private Date dateOfBirth;
	private TvShowRest tvShow;
	private List<ChapterRest> chapters;
	
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
	
	public TvShowRest getTvShow() {
		return tvShow;
	}

	public void setTvShow(TvShowRest tvShow) {
		this.tvShow = tvShow;
	}

	public List<ChapterRest> getChapters() {
		return chapters;
	}

	public void setChapters(List<ChapterRest> chapters) {
		this.chapters = chapters;
	}
	
	

}