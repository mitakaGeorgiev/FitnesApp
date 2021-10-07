package com.myfirm.app.entity;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="blogs")
public class Blog {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
	
	@Column(nullable = false, length = 300)
private String title;
	
	@Lob @Column (nullable = false)
private String body;
	@Column(nullable=false)
private Date date =new Date();
	
	@ManyToOne(optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",referencedColumnName = "id", nullable= false)
	private User user;
	public Blog() {}
	
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}

public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

}
