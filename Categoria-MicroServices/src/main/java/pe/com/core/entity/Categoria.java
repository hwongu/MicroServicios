package pe.com.core.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase mapeada de la tabla Categoria
 * @author Henry Joe Wong Urquiza
 * @email hwongu@gmail.com
 */
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idCategoria;
	private String nombre;

	public Categoria() {
		super();
	}

	public Categoria(Integer idCategoria) {
		super();
		this.idCategoria = idCategoria;
	}

	public Categoria(Integer idCategoria, String nombre
			) {
		super();
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idCategoria == null) ? 0 : idCategoria.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		final Categoria other = (Categoria) obj;
		return Objects.equals(this.idCategoria, other.idCategoria);
	}

}
