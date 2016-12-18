package es.uc3.tiw.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 50, name = "nombre")
	private String nombre;
	
	@Column(nullable = false, length = 50, name = "apellidos")
	private String apellidos;
	
	@Column(nullable = false, length = 32, name = "clave")
	private String clave;
	
	@Email(message="Error en formato email")
	@Column(nullable = false, length = 100, name = "email")
	private String email;
	
	@Column(nullable = false, length = 50, name = "ciudad")
	private String ciudad;
	
	@Column(nullable = false, name = "admin")
	private Boolean admin;

	
	public Usuario(){ 
		this.admin = false;
	}
	
	public Usuario(long id, String nombre, String apellidos, String clave, String email, String ciudad, boolean admin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.clave = clave;
		this.email = email;
		this.ciudad = ciudad;
		this.admin = admin;
	}
	
	
	public Long getId() {
		return id;
	}
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
	public Boolean isAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", clave=" + clave + ", email="
				+ email + ", ciudad=" + ciudad + "]";
	}
	
}
