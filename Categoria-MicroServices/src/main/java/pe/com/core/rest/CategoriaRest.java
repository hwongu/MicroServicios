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

import pe.com.core.bean.CategoriaRespuestaBean;
import pe.com.core.business.CategoriaBusiness;
import pe.com.core.entity.Categoria;
import pe.com.core.util.JsonUtil;

@RestController
@RequestMapping("/categoria")
public class CategoriaRest {

	private CategoriaBusiness categoriaBusiness = new CategoriaBusiness();
	private final static String OK = "OK";
	private final static String ERROR = "ERROR";
	
	
	@RequestMapping(value="/insertar", method=RequestMethod.POST)
    public String insertar(@RequestBody Categoria categoria) {
		CategoriaRespuestaBean categoriaBean = new CategoriaRespuestaBean();
		Map<String, Object> resultado = new HashMap<String, Object>();
		List<Categoria> lista = new ArrayList<>();
		try {
			categoriaBusiness.insertar(categoria);
			resultado.put("resulado", lista.add(categoria));
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
    public String actualizar(@RequestBody Categoria categoria) {
		CategoriaRespuestaBean categoriaBean = new CategoriaRespuestaBean();
		Map<String, Object> resultado = new HashMap<String, Object>();
		List<Categoria> lista = new ArrayList<>();
		try {
			categoriaBusiness.actualizar(categoria);
			resultado.put("resulado", lista.add(categoria));
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
    public String eliminar(@RequestBody Categoria categoria) {
		CategoriaRespuestaBean categoriaBean = new CategoriaRespuestaBean();
		Map<String, Object> resultado = new HashMap<String, Object>();
		List<Categoria> lista = new ArrayList<>();
		try {
			categoriaBusiness.eliminar(categoria);
			resultado.put("resulado", lista.add(categoria));
			categoriaBean.setEstadoRespuesta(OK);   
			categoriaBean.setParametros(resultado);
		} catch (Exception e) {
			categoriaBean.setEstadoRespuesta(ERROR);
			categoriaBean.setMensajeRespuesta(e.getMessage());
            return JsonUtil.convertirObjetoACadenaJson(categoriaBean);
		}
    	return JsonUtil.convertirObjetoACadenaJson(categoriaBean);
    }
	
	@RequestMapping(value="/obtenerCategoria/{filtro}", method=RequestMethod.GET)
	public String obtenerCategoria(@PathVariable("filtro") String filtro){
		CategoriaRespuestaBean categoriaBean = new CategoriaRespuestaBean();
		Map<String, Object> resultado = new HashMap<String, Object>();
		try {
			resultado.put("resulado", new Gson().toJson(categoriaBusiness.obtener(Integer.parseInt(filtro))));
			categoriaBean.setEstadoRespuesta(OK);   
			categoriaBean.setParametros(resultado);
		} catch (Exception e) {
			categoriaBean.setEstadoRespuesta(ERROR);
			categoriaBean.setMensajeRespuesta(e.getMessage());
            return JsonUtil.convertirObjetoACadenaJson(categoriaBean);
		}
		return JsonUtil.convertirObjetoACadenaJson(categoriaBean);
	}
	
	@RequestMapping(value="/listarCategorias", method=RequestMethod.GET)
	public String listaCategoria(){
		CategoriaRespuestaBean categoriaBean = new CategoriaRespuestaBean();
		Map<String, Object> resultado = new HashMap<String, Object>();
		List<Categoria> lista = new ArrayList<>();
		try {
			lista = categoriaBusiness.listar();
			resultado.put("resulado",  new Gson().toJson(lista));
			categoriaBean.setEstadoRespuesta(OK);   
			categoriaBean.setParametros(resultado);
		} catch (Exception e) {
			categoriaBean.setEstadoRespuesta(ERROR);
			categoriaBean.setMensajeRespuesta(e.getMessage());
            return JsonUtil.convertirObjetoACadenaJson(categoriaBean);
		}
		return JsonUtil.convertirObjetoACadenaJson(categoriaBean);
	}
	
	
	@RequestMapping(value="/filtrarCategorias/{filtro}", method=RequestMethod.GET)
	public String filtrarCategorias(@PathVariable("filtro") String filtro){
		CategoriaRespuestaBean categoriaBean = new CategoriaRespuestaBean();
		Map<String, Object> resultado = new HashMap<String, Object>();
		try {
			resultado.put("resulado",  new Gson().toJson(categoriaBusiness.listar(filtro)));
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
