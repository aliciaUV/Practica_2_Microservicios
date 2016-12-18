package es.uc3.tiw.web;

public class URL {

	public static final String VALIDAR_USUARIO = "http://localhost:8010/rest/usuario/validar";
	public static final String TODOS_USUARIOS = "http://localhost:8010/rest/usuarios";
	public static final String USUARIO_EMAIL = "http://localhost:8010/rest/usuario/email/{email}";
	public static final String USUARIO_ID = "http://localhost:8010/rest/usuario/{id}";
	public static final String USUARIO = "http://localhost:8010/rest/usuario";
	public static final String USUARIO_PRODUCTOS = "http://localhost:8020/rest/usuario/{id}/productos";
	
	public static final String TODOS_PRODUCTOS = "http://localhost:8020/rest/productos";
	public static final String PRODUCTO_ID = "http://localhost:8020/rest/producto/{id}";
	public static final String PRODUCTO = "http://localhost:8020/rest/producto";
	public static final String BUSCAR_PRODUCTOS_USUARIO = "http://localhost:8020/rest/usuario/{id}/productos/buscar/{searchTerm}";
	public static final String BUSCAR_TODOS_PRODUCTOS = "http://localhost:8020/rest/productos/buscar/{searchTerm}";
	public static final String BUSCAR_EN_TITULO_O_DESCRIPCION = "http://localhost:8020/rest/productos/buscar-en-titulo-o-descripcion/{titulo}";
	public static final String BUSCAR_POR_PRECIO = "http://localhost:8020/rest/productos/buscar-por-precio/{precio}";
	public static final String BUSCAR_POR_CATEGORIA = "http://localhost:8020/rest/productos/buscar-por-categoria/{categoria}";
	public static final String ELIMINAR_PRODUCTOS_USUARIO = "http://localhost:8020/rest/productos/usuario/{id}";
	
	public static final String MENSAJE = "http://localhost:8030/rest/mensaje";
	public static final String TODOS_MENSAJES = "http://localhost:8030/rest/mensajes";
	public static final String MENSAJE_ID = "http://localhost:8030/rest/mensaje/{id}";
	public static final String MENSAJES_USUARIO = "http://localhost:8030/rest/mensajes/usuario/{id}";
	public static final String ELIMINAR_MENSAJES_USUARIO = "http://localhost:8030/rest/mensajes/usuario/{id}";
	
	
	
	
}
