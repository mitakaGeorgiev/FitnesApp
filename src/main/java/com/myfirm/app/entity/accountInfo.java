package com.myfirm.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="accountInfo")
public class accountInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="personalProgram_id")
    private PersonalProgram personalProgram;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="personalRegime_id")
    private PersonalRegime personalRegime;
	
	@OneToMany(mappedBy="accountinfo")
    private List<Log> logs= new ArrayList<Log>();

	public PersonalRegime getPersonalRegime() {
		return personalRegime;
	}

	public void setPersonalRegime(PersonalRegime personalRegime) {
		this.personalRegime = personalRegime;
	}

	public PersonalProgram getPersonalProgram() {
		return personalProgram;
	}

	public void setPersonalProgram(PersonalProgram personalProgram) {
		this.personalProgram = personalProgram;
	}

	public accountInfo() {}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}
}
