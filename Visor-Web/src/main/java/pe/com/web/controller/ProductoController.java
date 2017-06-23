package pe.com.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pe.com.web.bean.CategoriaBean;
import pe.com.web.bean.ProductoBean;
import pe.com.web.bean.RespuestaBean;
import pe.com.web.util.WebUtil;

/**
 * @author Henry Joe Wong Urquiza
 * @email hwongu@gmail.com
 */
/**
 * @author hwong
 *
 */
@ManagedBean
@SessionScoped
public class ProductoController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(CategoriaController.class);
    private static final long serialVersionUID = 1L;
    public static final String REST_SERVICE_PRODUCTO_URI = "http://localhost:9994";
    public static final String REST_SERVICE_CATEGORIA_URI = "http://localhost:9995";
    private String filtro = "";
    private List<ProductoBean> listaProducto = new ArrayList<ProductoBean>();
    private ProductoBean productoGuardar = new ProductoBean();
    private ProductoBean productoSeleccionado = new ProductoBean();
    private String inicioMantenimiento = "";
    private List<SelectItem> listaItemGrupos = null;
    private RestTemplate restTemplate = new RestTemplate();

    
    private void limpiar() {
        this.listaProducto = new ArrayList<ProductoBean>();
        this.productoGuardar = new ProductoBean();
        this.productoSeleccionado = new ProductoBean();
        this.filtro = "";
    }


	private void inicializarCategorias() {
        try {
            this.listaItemGrupos = new ArrayList<SelectItem>();
            RespuestaBean respuestaBeanRest = restTemplate.getForObject(REST_SERVICE_CATEGORIA_URI + "/categoria/filtrarCategorias/%", RespuestaBean.class);
            List<CategoriaBean> listaCategorias = new ArrayList<>();
        	List<CategoriaBean> categoriaJson = new Gson().fromJson(String.valueOf(respuestaBeanRest.getParametros().get("resulado")), new TypeToken<List<CategoriaBean>>(){}.getType());
    		for (CategoriaBean json : categoriaJson) {
    			listaCategorias.add(json);
    		}
    		this.productoGuardar.setIdCategoria(listaCategorias.get(0).getIdCategoria());
            for (CategoriaBean categoria : listaCategorias) {
                this.listaItemGrupos.add(new SelectItem(categoria.getIdCategoria(), categoria.getNombre()));
            }
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    public void insertar() {
        try {
            restTemplate.postForEntity(REST_SERVICE_PRODUCTO_URI + "/producto/insertar", productoGuardar, RespuestaBean.class);
            WebUtil.mensajeInformacion(WebUtil.obtenerPropiedad("productoController.guardarExito", this.productoGuardar.getIdProducto()), WebUtil.obtenerPropiedad("productoController.guardarExito", this.productoGuardar.getIdProducto()));
            this.limpiar();
            this.inicializarCategorias();
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    public void actualizar() {
        try {
            //this.productoDao.actualizar(this.productoGuardar);
        	restTemplate.postForEntity(REST_SERVICE_PRODUCTO_URI + "/producto/actualizar", productoGuardar, RespuestaBean.class);
            WebUtil.mensajeInformacion(WebUtil.obtenerPropiedad("productoController.actualizarExito", this.productoGuardar.getIdProducto()), WebUtil.obtenerPropiedad("productoController.actualizarExito", this.productoGuardar.getIdProducto()));
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    public void eliminar() {
        try {
            if (this.productoSeleccionado != null && this.productoSeleccionado.getNombre().length() >= 0) {
                //this.productoDao.eliminar(productoSeleccionado);
            	restTemplate.postForEntity(REST_SERVICE_PRODUCTO_URI + "/producto/eliminar", productoSeleccionado, RespuestaBean.class);
                WebUtil.mensajeInformacion(WebUtil.obtenerPropiedad("productoController.eliminarExito", this.productoSeleccionado.getIdProducto()), WebUtil.obtenerPropiedad("productoController.eliminarExito", this.productoSeleccionado.getIdProducto()));
                this.limpiar();
            } else {
                WebUtil.mensajeAdvertencia(WebUtil.obtenerPropiedad("productoController.noSeleccionado"), WebUtil.obtenerPropiedad("productoController.noSeleccionado"));
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
        	RespuestaBean respuestaBeanRest = restTemplate.getForObject(REST_SERVICE_PRODUCTO_URI + "/producto/filtrarProductos/" + this.filtro.trim(), RespuestaBean.class);
        	this.listaProducto = new ArrayList<>();
        	List<ProductoBean> productosJson = new Gson().fromJson(String.valueOf(respuestaBeanRest.getParametros().get("resulado")), new TypeToken<List<ProductoBean>>(){}.getType());
    		for (ProductoBean json : productosJson) {
    			listaProducto.add(json);
    		}
        } catch (Exception exception) {
            String mensaje = WebUtil.controlarError(exception, LOGGER);
            WebUtil.mensajeError(mensaje, mensaje);
        }
    }

    public String irMantenimiento() {
        this.limpiar();
        return "mntAdmProducto";
    }

    public String irNuevo() {
        this.limpiar();
        this.inicializarCategorias();
        return "registrarAdmProducto";
    }

    public String irActualizar() {
        String rpta = "";
        this.inicializarCategorias();
        if (this.productoSeleccionado != null && this.productoSeleccionado.getNombre().length() >= 0) {
            this.productoGuardar = this.productoSeleccionado;
            this.listaProducto.clear();
            this.filtro = "";
            rpta = "actualizarAdmProducto";
        } else {
            WebUtil.mensajeAdvertencia(WebUtil.obtenerPropiedad("productoController.noSeleccionado"), WebUtil.obtenerPropiedad("productoController.noSeleccionado"));
        }
        return rpta;
    }

    public String irActualizarStock() {
        String rpta = "";
        this.inicializarCategorias();
        if (this.productoSeleccionado != null && this.productoSeleccionado.getNombre().length() >= 0) {
            this.productoGuardar = this.productoSeleccionado;
            this.listaProducto.clear();
            this.filtro = "";
            rpta = "actualizarAlmacenProducto";
        } else {
            WebUtil.mensajeAdvertencia(WebUtil.obtenerPropiedad("productoController.noSeleccionado"), WebUtil.obtenerPropiedad("productoController.noSeleccionado"));
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

    public List<ProductoBean> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<ProductoBean> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public ProductoBean getProductoGuardar() {
        return productoGuardar;
    }

    public void setProductoGuardar(ProductoBean productoGuardar) {
        this.productoGuardar = productoGuardar;
    }

    public ProductoBean getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(ProductoBean productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public List<SelectItem> getListaItemGrupos() {
        return listaItemGrupos;
    }

    public void setListaItemGrupos(List<SelectItem> listaItemGrupos) {
        this.listaItemGrupos = listaItemGrupos;
    }

}
