package com.myfirm.app.entity;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="UserRole")
public class UserRole {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
	
	private String name;
 @OneToMany(targetEntity=User.class, mappedBy="role", fetch = FetchType.LAZY)
 private Set<User> users;
 
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Set<User> getUsers() {
	return users;
}
public void setUsers(Set<User> users) {
	this.users = users;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}


}
