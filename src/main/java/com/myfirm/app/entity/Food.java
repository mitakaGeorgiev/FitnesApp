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
@Table(name="Food")
public class Food {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long Food_id;
		
		@Column(nullable = false, length = 15)
		private int grams;	
		
		@Column(nullable = false, unique = true, length = 45)
		private String name;
		
		@Column(nullable = true, length = 15)
		private int nutritionalValue;
		
		@ManyToMany(mappedBy = "foods")
		private Set<PersonalRegime> PersonalRegime=new HashSet<PersonalRegime>();

		public long getFood_id() {
			return Food_id;
		}

		public void setFood_id(long food_id) {
			Food_id = food_id;
		}

		public int getGrams() {
			return grams;
		}

		public void setGrams(int grams) {
			this.grams = grams;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Set<PersonalRegime> getPersonalRegime() {
			return PersonalRegime;
		}

		public void setPersonalRegime(Set<PersonalRegime> personalRegime) {
			PersonalRegime = personalRegime;
		}

		public int getNutritionalValue() {
			return nutritionalValue;
		}

		public void setNutritionalValue(int nutritionalValue) {
			this.nutritionalValue = nutritionalValue;
		}
		
}
