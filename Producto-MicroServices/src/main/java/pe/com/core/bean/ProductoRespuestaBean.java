package pe.com.core.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * Clase mapeada de la tabla Categoria
 * @author Henry Joe Wong Urquiza
 * @email hwongu@gmail.com
 */
public class ProductoRespuestaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String estadoRespuesta;
	private String mensajeRespuesta;
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
