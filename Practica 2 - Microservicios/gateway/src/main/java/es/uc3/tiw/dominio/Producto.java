package es.uc3.tiw.dominio;

import java.math.BigDecimal;

import org.apache.tomcat.util.codec.binary.Base64;

public class Producto {

	private Long id;
	
	private String titulo;
	
	private String descripcion;
	
	private byte[] imagen;
	
	private BigDecimal precio;
	
	public enum Categoria {Cualquiera, Vehiculos, Moda, Electrodomesticos, Libros};
	
	private Categoria categoria;
	
	public enum Estado {Disponible, Reservado, Vendido};
	
	private Estado estado;
	
	private long idUsuario;
	
	
	public Producto(){
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public byte[] getImagen() {
		return imagen;
	}

	public String getImagenCodificada() {
		return Base64.encodeBase64String(imagen);
	}
	
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}


	public BigDecimal getPrecio() {
		return precio;
	}


	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	
}
