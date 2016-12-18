package es.uc3.tiw.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.uc3.tiw.dao.MensajeRepository;
import es.uc3.tiw.dominio.Mensaje;


@RestController
@RequestMapping("/rest")
public class MensajeRestController {

	@Autowired
	private MensajeRepository mensajeRepository;
	
	@RequestMapping(value = "/mensaje/{id}",  method = RequestMethod.GET)
	public Mensaje mostrarMensaje(@PathVariable Long id){
		return mensajeRepository.findOne(id);	
	}
	
	
	@RequestMapping(value = "/mensajes",  method = RequestMethod.GET)
	public List<Mensaje> mostrarMensajes(){
		return mensajeRepository.findAll();
	}
	
	@RequestMapping(value = "/mensajes/usuario/{id}",  method = RequestMethod.GET)
	public List<Mensaje> mostrarMensajesUsuario(@PathVariable Long id){
		return mensajeRepository.buscarPorFromOrTo(id);
	}
	
	@RequestMapping(value = "/mensaje",  method = RequestMethod.POST)
	public Mensaje crearMensaje(@RequestBody Mensaje mensaje){
		return mensajeRepository.save(mensaje);			
	}
	
	
	@RequestMapping(value = "/mensaje",  method = RequestMethod.PUT)
	public void editarMensajes(@RequestBody Mensaje mensaje){
		mensajeRepository.save(mensaje);
	}
	
	
	@RequestMapping(value = "/mensaje/{id}",  method = RequestMethod.DELETE)
	public void eliminarMensaje(@PathVariable long id){
		mensajeRepository.delete(id);	
	}
	
	@RequestMapping(value = "/mensajes/usuario/{id}",  method = RequestMethod.DELETE)
	public void eliminarProductosUsuario(@PathVariable long id){
		List<Mensaje> mensajesUsuario = mensajeRepository.findByIdUsuarioFrom(id);
		for(Mensaje m : mensajesUsuario){
			mensajeRepository.delete(m);
		}
	}
}
