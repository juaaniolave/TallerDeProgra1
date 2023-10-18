package testsModeloDatos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modeloDatos.Cliente;
import modeloDatos.ClientePuntaje;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;

class testClientePutajeBruno {
	double puntaje;
	Cliente cliente;
	ClientePuntaje c;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		this.puntaje=1.0;
		cliente=new Empleador();
		c=new ClientePuntaje(this.puntaje,cliente);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConstructorCliente() {
		Assert.assertEquals("no son el mismo cliente",this.cliente,c.getCliente());
	}
	@Test
	void testSetCliente() {
		cliente=new EmpleadoPretenso();
		this.c.setCliente(cliente);
		Assert.assertEquals("no cambio el cliente",cliente,c.getCliente());
	}

}
