package com.healthportal.dto;

public class UserDTO {
	
	private Integer userId;
	private String name;
	private String username;
	private String role;
	private String password;
	private String address;
	private int age;
	private String gender;

	
    public UserDTO() {
		
	}

	public UserDTO(Integer userId, String name, String username, String role, String password, String address, int age, String gender) {
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.role = role;
		this.password = password;
		this.address = address;
		this.age = age;
		this.gender = gender;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

    public static class Builder {
		
	private Integer nesteduserid;
	private String nestedname;
	private String nestedusername;
	private String nestedrole;
	private String nestedpassword;
	private int nestedage;
	private String nestedaddress;
	private String nestedgender;
		
		public Builder userid(int userID) {
			this.nesteduserid = userID;
			return this;
		}
		
		public Builder name(String name) {
			this.nestedname = name;
			return this;
		}
		
		public Builder username(String username) {
		   this.nestedusername = username;
		   return this;
		}
		
		
		public Builder role(String role) {
			this.nestedrole = role;
			return this;
		}
		
		public Builder password(String password)
		{
			this.nestedpassword= password;
			return this;
		}

		public Builder address(String address)
		{
			this.nestedaddress = address;
			return this;
		}

		public Builder gender(String gender)
		{
			this.nestedgender = gender;
			return this;
		}

		public Builder age(int age)
		{
			this.nestedage = age;
			return this;
		}

		
		public UserDTO create(){
			return new UserDTO(nesteduserid,nestedname,nestedusername,nestedrole,nestedpassword,nestedaddress,nestedage, nestedgender);
		}
	}

}
