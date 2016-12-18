package es.uc3.tiw.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import es.uc3.tiw.dominio.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByEmailAndClave(String email, String clave);
	
	Usuario findByEmail(String email);
	
}
