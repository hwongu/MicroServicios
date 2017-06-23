package pe.com.microservices.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.com.microservices.core.repository.CategoriaRepository;
import pe.com.microservices.core.service.CategoriaService;

/**
 * 
 * @author Henry Joe Wong Urquiza
 * @mail hwongu@hwongu.net
 */
@Component("categoriaService")
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public CategoriaRepository getRepository() {
		return categoriaRepository;
	}

}
