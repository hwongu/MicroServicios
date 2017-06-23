package pe.com.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pe.com.web.bean.CategoriaBean;
import pe.com.web.bean.RespuestaBean;
import pe.com.web.util.WebUtil;

/**
 * @author Henry Joe Wong Urquiza
 * @email hwongu@gmail.com
 */
@ManagedBean
@SessionScoped
public class CategoriaController implements Serializable {

    
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(CategoriaController.class);
    public static final String REST_SERVICE_CATEGORIA_URI = "http://localhost:9995";
    
    private String filtro = "";
    private List<CategoriaBean> listaCategoria = new ArrayList<CategoriaBean>();
    private CategoriaBean categoriaGuardar = new CategoriaBean();
    private CategoriaBean categoriaSeleccionada = new CategoriaBean();
    private String inicioMantenimiento = "";
    private RestTemplate restTemplate = new RestTemplate();

    public CategoriaController() {

    }

    private void limpiar() {
        this.listaCategoria = new ArrayList<CategoriaBean>();
        this.categoriaGuardar = new CategoriaBean();
        this.categoriaSeleccionada = new CategoriaBean();
    }

    public void insertar() {
        try {
        	restTemplate.postForEntity(REST_SERVICE_CATEGORIA_URI + "/categoria/insertar", categoriaGuardar, RespuestaBean.class);
        	WebUtil.mensajeInformacion(WebUtil.obtenerPropiedad("categoriaController.guardarExito"), WebUtil.obtenerPropiedad("categoriaController.guardarExito"));
            this.limpiar();
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    public void actualizar() {
        try {
        	restTemplate.postForEntity(REST_SERVICE_CATEGORIA_URI + "/categoria/actualizar", categoriaGuardar, RespuestaBean.class);
            WebUtil.mensajeInformacion(WebUtil.obtenerPropiedad("categoriaController.actualizarExito"), WebUtil.obtenerPropiedad("categoriaController.actualizarExito"));
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    public void eliminar() {
        try {
            if (this.categoriaSeleccionada != null && this.categoriaSeleccionada.getNombre().length() >= 0) {
            	restTemplate.postForEntity(REST_SERVICE_CATEGORIA_URI + "/categoria/eliminar", categoriaSeleccionada, RespuestaBean.class);
                WebUtil.mensajeInformacion(WebUtil.obtenerPropiedad("categoriaController.eliminarExito"), WebUtil.obtenerPropiedad("categoriaController.eliminarExito"));
                this.limpiar();
            } else {
                WebUtil.mensajeAdvertencia(WebUtil.obtenerPropiedad("categoriaController.noSeleccionado"), WebUtil.obtenerPropiedad("categoriaController.noSeleccionado"));
            }
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    
	public void buscar() {
        try {
        	if(this.filtro.trim().length()==0){
        		this.filtro = "%";
        	}
        	RespuestaBean respuestaBeanRest = restTemplate.getForObject(REST_SERVICE_CATEGORIA_URI + "/categoria/filtrarCategorias/"+ this.filtro.trim(), RespuestaBean.class);
        	this.listaCategoria = new ArrayList<>();
           	List<CategoriaBean> categorias = new Gson().fromJson(String.valueOf(respuestaBeanRest.getParametros().get("resulado")), new TypeToken<List<CategoriaBean>>(){}.getType());
    		for (CategoriaBean categoriaTempBean : categorias) {
    			listaCategoria.add(categoriaTempBean);
    		}
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    public String irMantenimiento() {
        this.limpiar();
        return "mntAdmCategoria";
    }

    public String irNuevo() {
        this.limpiar();
        return "registrarAdmCategoria";
    }

    public String irActualizar() {
        String rpta = "";
        if (this.categoriaSeleccionada != null && this.categoriaSeleccionada.getNombre().length() >= 0) {
            this.categoriaGuardar = this.categoriaSeleccionada;
            this.listaCategoria.clear();
            this.filtro = "";
            rpta = "actualizarAdmCategoria";
        } else {
            WebUtil.mensajeAdvertencia(WebUtil.obtenerPropiedad("categoriaController.noSeleccionado"), WebUtil.obtenerPropiedad("categoriaController.noSeleccionado"));
        }
        return rpta;
    }

    public String getInicioMantenimiento() {
        this.limpiar();
        return inicioMantenimiento;
    }

    public void setInicioMantenimiento(String inicioMantenimiento) {
        this.inicioMantenimiento = inicioMantenimiento;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public List<CategoriaBean> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<CategoriaBean> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public CategoriaBean getCategoriaSeleccionada() {
        return categoriaSeleccionada;
    }

    public void setCategoriaSeleccionada(CategoriaBean categoriaSeleccionada) {
        this.categoriaSeleccionada = categoriaSeleccionada;
    }

    public CategoriaBean getCategoriaGuardar() {
        return categoriaGuardar;
    }

    

}
