package es.uc3.tiw;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import es.uc3.tiw.dao.UsuarioRepository;
import es.uc3.tiw.dominio.Usuario;

@SpringBootApplication
public class ClientesApplication {

	@Autowired
	private UsuarioRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(ClientesApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		Set<Usuario> usuarios = new HashSet<>();
		usuarios.add(new Usuario(0, "Matias", "Vazquez", "user", "user@user.com", "Madrid", false));
		usuarios.add(new Usuario(0, "Alicia", "Uceda", "admin", "admin@admin.com", "Madrid", true));
		
		repository.save(usuarios);
		repository.flush();
	}
}
