package es.uc3.tiw.web;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import es.uc3.tiw.dao.ProductoRepository;
import es.uc3.tiw.dominio.Producto;
import es.uc3.tiw.dominio.Producto.Categoria;

@RestController
@RequestMapping("/rest")
public class ProductoRestController {

	@Autowired
	private ProductoRepository productoRepository;
	
	@RequestMapping(value = "/producto/{id}",  method = RequestMethod.GET)
	public Producto mostrarProducto(@PathVariable Long id){
		return productoRepository.findOne(id);	
	}
	
	
	@RequestMapping(value = "/productos",  method = RequestMethod.GET)
	public List<Producto> mostrarProductos(){
		return productoRepository.findAll();
		
	}
	
	
	@RequestMapping(value = "/usuario/{id}/productos",  method = RequestMethod.GET)
	public List<Producto> mostrarProductosUsuario(@PathVariable long id){
		return productoRepository.findByIdUsuario(id);
		
	}

	
	@RequestMapping(value = "/productos/buscar-en-titulo-o-descripcion/{titulo}",  method = RequestMethod.GET)
	public List<Producto> buscarProductosEnTituloODescripcion(@PathVariable String titulo){
		return productoRepository.buscarEnTituloODescripcion(titulo);
	}
	
	@RequestMapping(value = "/productos/buscar-por-precio/{precio}",  method = RequestMethod.GET)
	public List<Producto> buscarProductosPorTitulo(@PathVariable double precio){
		return productoRepository.findByPrecio(precio);
	}
	
	@RequestMapping(value = "/productos/buscar-por-categoria/{categoria}",  method = RequestMethod.GET)
	public List<Producto> buscarProductosPorTitulo(@PathVariable Categoria categoria){
		return productoRepository.findByCategoria(categoria);
	}
	
	@RequestMapping(value = "/productos/buscar/{searchTerm}",  method = RequestMethod.GET)
	public List<Producto> buscarProductosPorTitulo(@PathVariable String searchTerm){
		return productoRepository.findByTituloContaining(searchTerm);
	}
	
	
	@RequestMapping(value = "/producto",  method = RequestMethod.POST)
	public Producto crearProducto(@RequestBody Producto producto){
		return productoRepository.save(producto);			
	}
	
	
	@RequestMapping(value = "/producto",  method = RequestMethod.PUT)
	public void editarProductos(@RequestBody Producto producto){
		productoRepository.save(producto);
	}
	
	
	@RequestMapping(value = "/producto/{id}",  method = RequestMethod.DELETE)
	public void eliminarProducto(@PathVariable long id){
		productoRepository.delete(id);	
	}
	
	@RequestMapping(value = "/productos/usuario/{id}",  method = RequestMethod.DELETE)
	public void eliminarProductosUsuario(@PathVariable long id){
		List<Producto> productosUsuario = productoRepository.findByIdUsuario(id);
		for(Producto p : productosUsuario){
			productoRepository.delete(p);
		}
			
	}
	
}
