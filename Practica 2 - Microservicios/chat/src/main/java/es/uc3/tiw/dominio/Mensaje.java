package es.uc3.tiw.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mensaje")
public class Mensaje {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="texto")
	private String texto;
	
	private long idUsuarioTo;
	
	private long idUsuarioFrom;
	
	
	public Mensaje(){
		
	}

	public Long getId() {
		return id;
	}


	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public long getIdUsuarioTo() {
		return idUsuarioTo;
	}

	public void setIdUsuarioTo(long idUsuarioTo) {
		this.idUsuarioTo = idUsuarioTo;
	}

	public long getIdUsuarioFrom() {
		return idUsuarioFrom;
	}

	public void setIdUsuarioFrom(long idUsuarioFrom) {
		this.idUsuarioFrom = idUsuarioFrom;
	}
	
	
	
}
