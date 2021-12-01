package com.myfirm.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Exercise")
public class Exercise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Exercise_id;
	
	@Column(nullable = false, length = 15)
	private int reps;
	
	@Column(nullable = false, unique = true, length = 45)
	private String name;
	
	@ManyToMany(mappedBy = "exercises")
	private Set<PersonalProgram> personalPrograms=new HashSet<PersonalProgram>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<PersonalProgram> getPersonalPrograms() {
		return personalPrograms;
	}
	public void setPersonalPrograms(Set<PersonalProgram> personalPrograms) {
		this.personalPrograms = personalPrograms;
	}
	public long getExercise_id() {
		return Exercise_id;
	}
	public void setExercise_id(long exercise_id) {
		Exercise_id = exercise_id;
	}
	public int getReps() {
		return reps;
	}
	public void setReps(int reps) {
		this.reps = reps;
	}	
}
