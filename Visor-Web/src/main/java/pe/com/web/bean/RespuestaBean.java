package pe.com.web.bean;

import java.io.Serializable;
import java.util.Map;

import com.google.gson.annotations.Expose;

/**
 * 
 * clase RespuestaBean
 *
 */
public class RespuestaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Expose
	private String estadoRespuesta;	
	
	@Expose
	private String mensajeRespuesta;
	
	@Expose
	private Map<String, Object> parametros;

	public String getEstadoRespuesta() {
		return estadoRespuesta;
	}

	public void setEstadoRespuesta(String estadoRespuesta) {
		this.estadoRespuesta = estadoRespuesta;
	}

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

	public Map<String, Object> getParametros() {
		return parametros;
	}

	public void setParametros(Map<String, Object> parametros) {
		this.parametros = parametros;
	}

}
