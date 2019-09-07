package Kabina.DTO;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -5892343753492809513L;

	private final String jwttoken;
	private String role;
	private long id;

	public JwtResponse(String jwttoken, String role, long id) {
		this.jwttoken = jwttoken;
		this.role = role;
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
