package com.myfirm.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
@Entity
@Table(name="personalProgram")
public class PersonalProgram {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PersonalProgram_id;
	
	@Column(nullable = false, length = 15)
	private int days;
	
	@Column(nullable = false, length = 15)
	private int sets;
	
	@Column(nullable = false, length = 15)
	private int breakForSets;
	
	@ManyToMany
	@JoinTable(name= "PersonalProgram_Exercise"
	,joinColumns= {
			@JoinColumn(name="PersonalProgram_id")
			}
	,inverseJoinColumns= {
			@JoinColumn(name="Exercise_id")
			}
	)
	
	@OrderBy
	private Set<Exercise> exercises= new HashSet<Exercise>(0);
	
	@OneToOne(mappedBy = "personalProgram")
    private accountInfo accountinfo;
	
	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}		

	public Set<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(Set<Exercise> exercises) {
		this.exercises = exercises;
	}

	public accountInfo getAccountinfo() {
		return accountinfo;
	}

	public void setAccountinfo(accountInfo accountinfo) {
		this.accountinfo = accountinfo;
	}

	public Long getPersonalProgram_id() {
		return PersonalProgram_id;
	}

	public void setPersonalProgram_id(Long personalProgram_id) {
		PersonalProgram_id = personalProgram_id;
	}
	
	public void addExercise(Exercise exercise,PersonalProgram personalProgram) {
		exercises.add(exercise);
		exercise.getPersonalPrograms().add(personalProgram);

   }
	
	public void RemoveExercise(Exercise exercise,PersonalProgram personalProgram) {
		exercises.remove(exercise);
		exercise.getPersonalPrograms().add(personalProgram);
   }

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public int getBreakForSets() {
		return breakForSets;
	}

	public void setBreakForSets(int breakForSets) {
		this.breakForSets = breakForSets;
	}
	
}
