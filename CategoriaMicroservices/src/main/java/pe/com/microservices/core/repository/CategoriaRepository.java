package pe.com.microservices.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.microservices.core.domain.Categoria;

/**
 * 
 * @author Henry Joe Wong Urquiza
 * @mail hwongu@hwongu.net
 */
@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer>{

}
