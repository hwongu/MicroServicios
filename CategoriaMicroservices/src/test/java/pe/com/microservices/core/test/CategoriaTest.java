package pe.com.microservices.core.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.com.microservices.core.domain.Categoria;
import pe.com.microservices.core.service.CategoriaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/java//pe/com/microservices/core/configuration/SpringContext.xml" })
public class CategoriaTest {

	@Autowired
	private CategoriaService categoriaService;
	
	@Test
	public void test(){
		try {
			Categoria c = categoriaService.getRepository().findOne(1);
			System.out.println(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
