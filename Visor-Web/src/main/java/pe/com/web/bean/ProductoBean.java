package pe.com.web.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase mapeada de la tabla Producto
 * @author Henry Joe Wong Urquiza
 * @email hwongu@gmail.com
 */
public class ProductoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idProducto;
	private String nombre;
	private Double precio;
	private Integer idCategoria;


	public ProductoBean() {
		super();
	}

	public ProductoBean(Integer idProducto) {
		super();
		this.idProducto = idProducto;
	}

	public ProductoBean(Integer idProducto, String nombre, Double precio,
			Integer idCategoria) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.idCategoria = idCategoria;
	
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
	
		result = prime * result
				+ ((idCategoria == null) ? 0 : idCategoria.hashCode());
		result = prime * result
				+ ((idProducto == null) ? 0 : idProducto.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ProductoBean other = (ProductoBean) obj;
		return Objects.equals(this.idProducto, other.idProducto);
	}

}
