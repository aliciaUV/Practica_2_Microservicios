package es.uc3.tiw.dominio;

public class Mensaje {

	private long id;
	
	private String texto;
	
	private long idUsuarioTo;
	
	private long idUsuarioFrom;
	
	public Mensaje(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
