package es.uc3.tiw.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import es.uc3.tiw.dominio.Mensaje;
import es.uc3.tiw.dominio.Producto;
import es.uc3.tiw.dominio.Producto.Categoria;
import es.uc3.tiw.dominio.Usuario;

@Controller
@RequestMapping("/user")
public class GatewayUserController{
	
	@Autowired
	protected RestTemplate restTemplate;
	
	private Usuario getUsuarioLogueado(){
		return GatewayHomeController.getUsuarioLogueado();
	}
	
	@RequestMapping(value = (""), method = RequestMethod.GET)
	public String rootUser(){
		return "redirect:user/";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String menuPrincipal(Model model){
		Usuario usuario = getUsuarioLogueado();
		List<Producto> productos = restTemplate.getForObject(URL.TODOS_PRODUCTOS, List.class);
		model.addAttribute("usuario", usuario);
		model.addAttribute("productos", productos);
		
		return "menu_principal";
	}
	
	/////// PERFIL ///////
	
	@RequestMapping(value = "/perfil", method = RequestMethod.GET)
	public String verPerfil(Model model){
		Usuario usuarioLogueado = getUsuarioLogueado();
		model.addAttribute("usuario", usuarioLogueado);
		
		return "perfil";
	}
	
	@RequestMapping(value = "/perfil", method = RequestMethod.POST)
	public String modificarPerfil(Model model, Usuario newUsuario){
		
		Usuario currentUsuario = restTemplate.getForObject(URL.USUARIO_ID, Usuario.class, newUsuario.getId());
		
		if(currentUsuario == null){
			model.addAttribute("Mensaje", new InfoMessage("No existe el usuario con [Id]: " + newUsuario.getId(), TipoInfoMessage.ERROR, "alert-danger"));
		}
		else{
			
			currentUsuario.setNombre(newUsuario.getNombre());
			currentUsuario.setApellidos(newUsuario.getApellidos());
			currentUsuario.setEmail(newUsuario.getEmail());
			currentUsuario.setClave(newUsuario.getClave());
			currentUsuario.setCiudad(newUsuario.getCiudad());
			
			restTemplate.postForObject(URL.USUARIO, currentUsuario, Usuario.class);
			model.addAttribute("Mensaje", new InfoMessage("Usuario modificado correctamente", TipoInfoMessage.EXITO, "alert-success"));
		}
	
		
		return "perfil";
	}
	
	@RequestMapping(value="/darseDeBaja", method = RequestMethod.GET)
	public String darseDeBaja(Model model){
		
		long idUsuario = getUsuarioLogueado().getId();
		if(idUsuario > 0){		
			restTemplate.delete(URL.ELIMINAR_PRODUCTOS_USUARIO, idUsuario);
			restTemplate.delete(URL.ELIMINAR_MENSAJES_USUARIO, idUsuario);
			restTemplate.delete(URL.USUARIO_ID, idUsuario);
			return "redirect:/logout";
		}
		
		model.addAttribute("Mensaje", new InfoMessage("No se ha podido dar de baja", TipoInfoMessage.ERROR, "alert-danger"));
		
		return "perfil";
	}
	
	/////// PRODUCTOS ///////
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/productos", method = RequestMethod.GET)
	public String listarProductos(Model model){
		long idUsuario = getUsuarioLogueado().getId();
		List<Producto> productos = restTemplate.getForObject(URL.USUARIO_PRODUCTOS, List.class, idUsuario);
		model.addAttribute("productos", productos);
		
		return "listado_productos";
	}
	
	@RequestMapping(value="/producto/{id}", method = RequestMethod.GET)
	public String mostrarProducto(Model model, @PathVariable Long id){
		Producto producto = restTemplate.getForObject(URL.PRODUCTO_ID, Producto.class, id);
		model.addAttribute("producto", producto);
		
		return "modificar_producto";
	}
	
	
	@RequestMapping(value="/producto/{id}", method = RequestMethod.POST)
	public String editarProducto(Model model, @PathVariable Long id, Producto newProducto, @RequestParam CommonsMultipartFile foto){
		
		//if(validarProducto (newProducto)){
			Producto producto = restTemplate.getForObject(URL.PRODUCTO_ID, Producto.class, id);
			
			if(producto == null){
				model.addAttribute("Mensaje", new InfoMessage("No existe el producto con [Id]: " + id, TipoInfoMessage.ERROR, "alert-danger"));
			}
			else{
				producto.setTitulo(newProducto.getTitulo());
				producto.setPrecio(newProducto.getPrecio());
				producto.setCategoria(newProducto.getCategoria());
				producto.setEstado(newProducto.getEstado());
				producto.setDescripcion(newProducto.getDescripcion());
				if(foto.getSize() > 0){
					producto.setImagen(foto.getBytes());
				}			
				producto = restTemplate.postForObject(URL.PRODUCTO, producto, Producto.class);
				model.addAttribute("Mensaje", new InfoMessage("Producto modificado correctamente", TipoInfoMessage.EXITO, "alert-success"));
				}				
		
		//else{
			//model.addAttribute("Mensaje", new InfoMessage("Producto no modificado", TipoInfoMessage.ERROR, "alert-danger"));
		//}
		return "redirect:/user/productos";		
}
	
	@RequestMapping(value="/producto", method = RequestMethod.GET)
	public String altaProducto(Model model){
		model.addAttribute("usuario", getUsuarioLogueado());
		
		return "alta_producto";
	}
	
	@RequestMapping(value="/producto", method = RequestMethod.POST)
	public String doAltaProducto(Model model, Producto producto, 
								@RequestParam CommonsMultipartFile foto){
		
		if(validarProducto(producto)){
			long idUsuario = getUsuarioLogueado().getId();
			
			Producto newProducto = new Producto();
			newProducto.setTitulo(producto.getTitulo());
			newProducto.setPrecio(producto.getPrecio());
			newProducto.setCategoria(producto.getCategoria());
			newProducto.setEstado(producto.getEstado());
			newProducto.setDescripcion(producto.getDescripcion());
			newProducto.setImagen(foto.getBytes());
			newProducto.setIdUsuario(idUsuario);
			
			newProducto = restTemplate.postForObject(URL.PRODUCTO, newProducto, Producto.class);
			
			return "redirect:productos";
		}
		else{
			model.addAttribute("Mensaje", new InfoMessage("Hay campos vacios que son necesarios", TipoInfoMessage.ERROR, "alert-danger"));
			return "alta_producto";
		}
		
	}
	
	private boolean validarProducto(Producto producto){

	    return StringUtils.isNotBlank(producto.getTitulo().trim()) &&
	            StringUtils.isNotBlank(producto.getDescripcion().trim()) &&
	            (producto.getPrecio()!=null && producto.getPrecio().doubleValue()>0);
	} 
	
	
	@RequestMapping(value="/eliminarProducto", method = RequestMethod.POST)
	public String eliminarProducto(Model model, @RequestParam long id){
		restTemplate.delete(URL.PRODUCTO_ID, id);
		
		return "redirect:productos";
	}
	
	/////// BUSQUEDA ///////
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/buscarProducto", method = RequestMethod.POST)
	public String buscarProducto(Model model, @RequestParam String searchTerm){
		Usuario usuario = getUsuarioLogueado();
		model.addAttribute("usuario", usuario);
		if(StringUtils.isNotBlank(searchTerm.trim())){
		
			
		List<Producto> productos = restTemplate.getForObject(URL.BUSCAR_TODOS_PRODUCTOS, List.class, searchTerm);

		model.addAttribute("productos", productos);
		}
		
		else{
			model.addAttribute("Mensaje", new InfoMessage("Introduce un título de producto", TipoInfoMessage.ERROR, "alert-danger"));
		}
		
		return "menu_principal";
	}
	
	
	/////// BUSQUEDA AVANZADA ///////
	
	@RequestMapping(value="/busqueda-avanzada", method = RequestMethod.GET)
	public String busquedaAvanzada(){
		return "busqueda_avanzada";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/busquedaAvanzada", method = RequestMethod.POST)
	public String busquedaAvanzada(Model model, @RequestParam String titulo,
									@RequestParam BigDecimal precio, @RequestParam Categoria categoria){
		
		List<Producto> productos = new ArrayList<>();
		
		if(StringUtils.isNotBlank(titulo.trim())){
			productos = restTemplate.getForObject(URL.BUSCAR_EN_TITULO_O_DESCRIPCION, List.class, titulo);
		}
		else if(precio.longValueExact() > 0.0){
			productos = restTemplate.getForObject(URL.BUSCAR_POR_PRECIO, List.class, precio);
		}
		else if(categoria != Categoria.Cualquiera){
			productos = restTemplate.getForObject(URL.BUSCAR_POR_CATEGORIA, List.class, categoria);
		}
		else{
			productos = restTemplate.getForObject(URL.TODOS_PRODUCTOS, List.class);
		}
		
		model.addAttribute("productos", productos);
		
		return "busqueda_avanzada";
	}
	
	
	/////// CHAT ///////
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/mensajes", method = RequestMethod.GET)
	public String listarMensajes(Model model){
		Usuario usuario = getUsuarioLogueado();
		model.addAttribute("usuarioFrom", usuario);
		List<Mensaje> mensajes = restTemplate.getForObject(URL.MENSAJES_USUARIO, List.class, usuario.getId());
		model.addAttribute("mensajes", mensajes);
		
		return "listado_mensajes";
	}
	
	@RequestMapping(value="/mensaje", method = RequestMethod.POST)
	public String escribirMensaje(Model model, @RequestParam long idUsuarioTo){
		model.addAttribute("usuarioFrom", getUsuarioLogueado());
		model.addAttribute("usuarioTo", restTemplate.getForObject(URL.USUARIO_ID, Usuario.class, idUsuarioTo));
		
		
		return "escribir_mensaje";
	}
	
	@RequestMapping(value="/enviarMensaje", method = RequestMethod.POST)
	public String enviarMensaje(Model model, Mensaje mensaje){
		
		if(mensaje.getIdUsuarioFrom() == mensaje.getIdUsuarioTo()){
			 model.addAttribute("Mensaje", new InfoMessage("No se puede enviar mensaje a sí mismo", TipoInfoMessage.ERROR, "alert-danger"));
		}
		else{
			restTemplate.postForObject(URL.MENSAJE, mensaje, Mensaje.class);
		    model.addAttribute("Mensaje", new InfoMessage("Mensaje enviado correctamente", TipoInfoMessage.EXITO, "alert-success"));
		}
		
		 model.addAttribute("usuarioFrom", getUsuarioLogueado());
	    
		return "escribir_mensaje";
	}
	
	
	
}
