package com.myfirm.app.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;
 
@Entity
@Table(name = "users")
public class User {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @Column(nullable = false, unique = true, length = 45)
    private String email;
     
    @Column(nullable = false, unique = true, length = 45)
    private String username;
     
    @Column(nullable = false, length = 64)
    private String password;
     
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
     
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    
    @Column(name = "age", nullable = false, length = 20)
    private int age;
    
    @Column(name = "sex")
    private String sex;
    
    @Column(name = "mentor_id", nullable = true)
    private Long mentorid;
    
    
     @Lob @Column(name="mentor_Info" , nullable =true)
    private String mentorInfo;
    
    @Column(nullable=true,length= 64)
    private String photo;
    
    @Transient
    public String getPhotosImagePath() {
        if (photo == null || id == null) return null;
         
        return "/user-photos/" + id + "/" + photo;
    }

    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private accountInfo accountinfo;
    public User() {}
    
    @OneToMany(mappedBy="user")
    private List<Blog> blogs= new ArrayList<Blog>();
    
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name="role_id")    
	private UserRole role;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public accountInfo getAccountinfo() {
		return accountinfo;
	}
	public void setAccountinfo(accountInfo accountinfo) {
		this.accountinfo = accountinfo;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Optional<Long> getMentorid() {
		return Optional.ofNullable(mentorid);
	}

	public void setMentorid(Long mentorid) {
		this.mentorid = mentorid;
	}

	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getMentorInfo() {
		return mentorInfo;
	}

	public void setMentorInfo(String mentorInfo) {
		this.mentorInfo = mentorInfo;
	}

	
	
}