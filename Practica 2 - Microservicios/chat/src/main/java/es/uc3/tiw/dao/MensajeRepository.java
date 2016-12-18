package es.uc3.tiw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.uc3.tiw.dominio.Mensaje;


public interface MensajeRepository extends JpaRepository<Mensaje, Long>{
	
	@Query("FROM Mensaje m WHERE m.idUsuarioFrom = ?1 OR  m.idUsuarioTo = ?1")
	List<Mensaje> buscarPorFromOrTo(long idUsuario);
	
	List<Mensaje> findByIdUsuarioFrom(long idUsuario);
	
}
