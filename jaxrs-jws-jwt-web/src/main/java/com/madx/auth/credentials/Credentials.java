package com.madx.auth.credentials;

import java.io.Serializable;

public class Credentials implements Serializable {
	private static final long serialVersionUID = 441411484257159552L;
	private String username;
    private String password;
    
    public Credentials() {
		super();
	}
    
	public Credentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
