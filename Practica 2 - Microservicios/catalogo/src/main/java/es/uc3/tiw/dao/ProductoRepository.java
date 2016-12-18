package es.uc3.tiw.dao;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.uc3.tiw.dominio.Producto;
import es.uc3.tiw.dominio.Producto.Categoria;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

	List<Producto> findByIdUsuario(long idUsuario);
	
	@Query("FROM Producto p WHERE ( (p.titulo like %?1%) or (p.descripcion like %?1%) )")
	List<Producto> buscarEnTituloODescripcion(String searchTerm);
	

	
	List<Producto> findByPrecio(double precio);
	
	List<Producto> findByCategoria(Categoria categoria);
	
	List<Producto> findByTituloContaining(String searchTerm);
	
}
