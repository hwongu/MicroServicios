package pe.com.core.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import pe.com.core.bean.ProductoRespuestaBean;
import pe.com.core.business.ProductoBusiness;
import pe.com.core.entity.Producto;
import pe.com.core.util.JsonUtil;

@RestController
@RequestMapping("/producto")
public class ProductoRest {

	private ProductoBusiness productoBusiness= new ProductoBusiness();
	private final static String OK = "OK";
	private final static String ERROR = "ERROR";
	
	
	@RequestMapping(value="/insertar", method=RequestMethod.POST)
    public String insertar(@RequestBody Producto producto) {
		ProductoRespuestaBean categoriaBean = new ProductoRespuestaBean();
		Map<String, Object> resultado = new HashMap<String, Object>();
		List<Producto> lista = new ArrayList<>();
		try {
			productoBusiness.insertar(producto);
			resultado.put("resulado", lista.add(producto));
			categoriaBean.setEstadoRespuesta(OK);   
			categoriaBean.setParametros(resultado);
		} catch (Exception e) {
			categoriaBean.setEstadoRespuesta(ERROR);
			categoriaBean.setMensajeRespuesta(e.getMessage());
            return JsonUtil.convertirObjetoACadenaJson(categoriaBean);
		}
    	return JsonUtil.convertirObjetoACadenaJson(categoriaBean);
    }
	
	@RequestMapping(value="/actualizar", method=RequestMethod.POST)
    public String actualizar(@RequestBody Producto producto) {
		ProductoRespuestaBean categoriaBean = new ProductoRespuestaBean();
		Map<String, Object> resultado = new HashMap<String, Object>();
		List<Producto> lista = new ArrayList<>();
		try {
			productoBusiness.actualizar(producto);
			resultado.put("resulado", lista.add(producto));
			categoriaBean.setEstadoRespuesta(OK);   
			categoriaBean.setParametros(resultado);
		} catch (Exception e) {
			categoriaBean.setEstadoRespuesta(ERROR);
			categoriaBean.setMensajeRespuesta(e.getMessage());
            return JsonUtil.convertirObjetoACadenaJson(categoriaBean);
		}
    	return JsonUtil.convertirObjetoACadenaJson(categoriaBean);
    }
	
	@RequestMapping(value="/eliminar", method=RequestMethod.POST)
    public String eliminar(@RequestBody Producto producto) {
		ProductoRespuestaBean categoriaBean = new ProductoRespuestaBean();
		Map<String, Object> resultado = new HashMap<String, Object>();
		List<Producto> lista = new ArrayList<>();
		try {
			productoBusiness.eliminar(producto);
			resultado.put("resulado", lista.add(producto));
			categoriaBean.setEstadoRespuesta(OK);   
			categoriaBean.setParametros(resultado);
		} catch (Exception e) {
			categoriaBean.setEstadoRespuesta(ERROR);
			categoriaBean.setMensajeRespuesta(e.getMessage());
            return JsonUtil.convertirObjetoACadenaJson(categoriaBean);
		}
    	return JsonUtil.convertirObjetoACadenaJson(categoriaBean);
    }
	
	@RequestMapping(value="/listarProductos", method=RequestMethod.GET)
	public String listaCategoria(){
		ProductoRespuestaBean categoriaBean = new ProductoRespuestaBean();
		Map<String, Object> resultado = new HashMap<String, Object>();
		try {
			resultado.put("resulado", new Gson().toJson(productoBusiness.listar()));
			categoriaBean.setEstadoRespuesta(OK);   
			categoriaBean.setParametros(resultado);
		} catch (Exception e) {
			categoriaBean.setEstadoRespuesta(ERROR);
			categoriaBean.setMensajeRespuesta(e.getMessage());
            return JsonUtil.convertirObjetoACadenaJson(categoriaBean);
		}
		return JsonUtil.convertirObjetoACadenaJson(categoriaBean);
	}
	
	
	@RequestMapping(value="/filtrarProductos/{filtro}", method=RequestMethod.GET)
	public String filtrarCategorias(@PathVariable("filtro") String filtro){
		ProductoRespuestaBean categoriaBean = new ProductoRespuestaBean();
		Map<String, Object> resultado = new HashMap<String, Object>();
		try {
			resultado.put("resulado",  new Gson().toJson(productoBusiness.listar(filtro)));
			categoriaBean.setEstadoRespuesta(OK);   
			categoriaBean.setParametros(resultado);
		} catch (Exception e) {
			categoriaBean.setEstadoRespuesta(ERROR);
			categoriaBean.setMensajeRespuesta(e.getMessage());
            return JsonUtil.convertirObjetoACadenaJson(categoriaBean);
		}
		return JsonUtil.convertirObjetoACadenaJson(categoriaBean);
	}
	
}
