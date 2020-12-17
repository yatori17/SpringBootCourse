package com.example.carros;

import com.example.carros.api.exception.ObjectNotFoundException;
import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static junit.framework.TestCase.*;

import java.util.List;
import java.util.Optional;


@SpringBootTest
class CarroServiceTest {

	@Autowired
	private CarroService carroService;
	@Test
	void test1() {
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivos");
		CarroDTO c =  carroService.save(carro);
		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		//Buscar objeto
		CarroDTO op = carroService.getCarroById(id);
		assertNotNull(op);


		c = op;

		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivos", c.getTipo());


		//Deletar o objeto

		carroService.delete(id);

		// Verificar se deletou
		try{
			assertNull(carroService.getCarroById(id));
			fail("O carro não foi excluído");
		} catch (ObjectNotFoundException e){
			//ok
		}


	}
	@Test
	public void testLista(){
		List<CarroDTO> carros = carroService.getCarros();

		assertEquals(30, carros.size());
	}
	@Test
	public void testGet(){
		CarroDTO op = carroService.getCarroById(11L);
		assertNotNull(op);

		CarroDTO c =op;

		assertEquals("Ferrari FF", c.getNome());
	}

	@Test
	public void testListaPorTipo(){

		assertEquals(10, carroService.getCarroByTipo("esportivos").size());
		assertEquals(10, carroService.getCarroByTipo("luxo").size());
		assertEquals(10, carroService.getCarroByTipo("classicos").size());

	}

}
