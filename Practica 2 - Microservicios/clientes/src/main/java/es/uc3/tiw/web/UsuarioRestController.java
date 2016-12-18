package es.uc3.tiw.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.uc3.tiw.dao.UsuarioRepository;
import es.uc3.tiw.dominio.Usuario;


@RestController
@RequestMapping("/rest")
public class UsuarioRestController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@RequestMapping(value = "/usuario/validar",  method = RequestMethod.POST)
	public Usuario validarUsuario(@RequestBody Usuario usuario){
		return usuarioRepository.findByEmailAndClave(usuario.getEmail(), usuario.getClave());	
	}
	
	@RequestMapping(value = "/usuario/email/{email}",  method = RequestMethod.GET)
	public Usuario mostrarUsuarioPorEmail(@PathVariable String email){
		return usuarioRepository.findByEmail(email);	
	}
	
	@RequestMapping(value = "/usuario/{id}",  method = RequestMethod.GET)
	public Usuario mostrarUsuarioPorId(@PathVariable Long id){
		return usuarioRepository.findOne(id);	
	}
	
	
	@RequestMapping(value = "/usuarios",  method = RequestMethod.GET)
	public List<Usuario> mostrarUsuarios(){
		return usuarioRepository.findAll();	
	}
	
	
	@RequestMapping(value = "/usuario",  method = RequestMethod.POST)
	public Usuario crearUsuario(@RequestBody Usuario usuario){
		return usuarioRepository.save(usuario);	
	}
	
	
	@RequestMapping(value = "/usuario",  method = RequestMethod.PUT)
	public void editarUsuarios(@RequestBody Usuario usuario){
		usuarioRepository.save(usuario);
	}
	
	
	@RequestMapping(value = "/usuario/{id}",  method = RequestMethod.DELETE)
	public void eliminarUsuario(@PathVariable Long id){
		usuarioRepository.delete(id);	
	}
	
	
}
