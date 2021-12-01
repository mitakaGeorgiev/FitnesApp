package com.myfirm.app.entity;

import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name="logs")
public class Log {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private int age;
	
	@Column(nullable = false)
	private int height;
	
	@Column(nullable = false)
	private double kilograms;
	
	@Column(nullable = false)
	private Date date=new Date();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="account_id",referencedColumnName = "id", nullable= false)
	private accountInfo accountinfo;
	
	public Log() {}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public double getKilograms() {
		return kilograms;
	}
	
	public void setKilograms(double kilograms) {
		this.kilograms = kilograms;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public accountInfo getAccountinfo() {
		return accountinfo;
	}

	public void setAccountinfo(accountInfo accountinfo) {
		this.accountinfo = accountinfo;
	}

}
