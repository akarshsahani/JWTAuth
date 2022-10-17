package com.example.demo.model;

public class JwtRequest {
	private String username;
	private String password;
	private String token;
	
	public JwtRequest() {
		
	}

	public JwtRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtRequest [username=" + username + ", password=" + password + ", token=" + token + "]";
	}
	
	

}
	
