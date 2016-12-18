package es.uc3.tiw.dominio;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.tomcat.util.codec.binary.Base64;



@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 50, name = "titulo")
	private String titulo;
	
	@Column(nullable = false, length = 500, name = "descripcion")
	private String descripcion;
	
	@Lob
	@Column(nullable = false, name = "imagen")
	private byte[] imagen;
	
	@Column(nullable = false, name = "precio")
	private double precio;
	
	public enum Categoria {Vehiculos, Moda, Electrodomesticos, Libros};
	@Column(name = "categoria")
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	public enum Estado {Disponible, Reservado, Vendido};
	@Column(name = "estado")
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	
	@Column(name="idUsuario")
	private long idUsuario;
	

	public Producto(){
		
	}
	
	
	public Long getId() {
		return id;
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
	
	
	public void setImagen(byte[] img) {
		this.imagen = img;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
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

	
	@Override
	public String toString() {
		return "Producto [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", urlFoto=" + imagen
				+ ", precio=" + precio + ", categoria=" + categoria + "]";
	}
}
