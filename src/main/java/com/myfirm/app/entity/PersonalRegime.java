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
@Table(name="personalRegime")
public class PersonalRegime {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PersonalRegime_id;
	
	@Column(nullable = false, length = 15)
	private int days;
	
	@ManyToMany
	@JoinTable(name= "PersonalRegime_Food"
	,joinColumns= {
			@JoinColumn(name="PersonalRegime_id")
			}
	,inverseJoinColumns= {
			@JoinColumn(name="Food_id")
			}	
	)
	@OrderBy
	private Set<Food> foods= new HashSet<Food>(0);
	
	@OneToOne(mappedBy = "personalRegime")
    private accountInfo accountinfo;

	public Long getPersonalRegime_id() {
		return PersonalRegime_id;
	}

	public void setPersonalRegime_id(Long personalRegime_id) {
		PersonalRegime_id = personalRegime_id;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Set<Food> getFoods() {
		return foods;
	}

	public void setFoods(Set<Food> foods) {
		this.foods = foods;
	}

	public accountInfo getAccountinfo() {
		return accountinfo;
	}

	public void setAccountinfo(accountInfo accountinfo) {
		this.accountinfo = accountinfo;
	}
	
	public void addFood(Food food,PersonalRegime personalRegime) {
		foods.add(food);
		food.getPersonalRegime().add(personalRegime);
   }
	
	public void RemoveFood(Food food,PersonalRegime personalRegime) {
		foods.remove(food);
		food.getPersonalRegime().add(personalRegime);
   }
	
}
