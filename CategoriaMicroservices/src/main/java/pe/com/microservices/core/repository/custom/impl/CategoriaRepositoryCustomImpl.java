package pe.com.microservices.core.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import pe.com.microservices.core.repository.custom.CategoriaRepositoryCustom;

/**
 * 
 * @author Henry Joe Wong Urquiza
 * @mail hwongu@hwongu.net
 */
public class CategoriaRepositoryCustomImpl implements CategoriaRepositoryCustom{

	@SuppressWarnings("unused")
	@Autowired
	private JdbcTemplate jdbcTemplate;
}
