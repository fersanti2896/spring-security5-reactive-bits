package sample.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Document
public class User {
	@Id
	private Long id;

	@Indexed
	@NotEmpty(message = "This field is required")
	private String email;

	@NotEmpty(message = "This field is required")
	private String password;

	@NotEmpty(message = "This field is required")
	private String nombres;

	@NotEmpty(message = "This field is required")
	private String apellidos;

	public User() {}

	public User(User user) {
		this(user.getId(), user.getEmail(), user.getPassword(), user.getNombres(), user.getApellidos());
	}

	public User(Long id, String email, String password, String nombres,
			String apellidos) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nombres = nombres;
		this.apellidos = apellidos;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
}
