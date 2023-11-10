package testsModeloDatos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.ClientePuntaje;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;

public class TestClientePuntaje {
	double puntaje;
	Cliente cliente;
	ClientePuntaje c;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.puntaje=1.0;
		cliente=new Empleador();
		c=new ClientePuntaje(this.puntaje,cliente);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructorCliente() {
		Assert.assertEquals("no son el mismo cliente",this.cliente,c.getCliente());
	}
	@Test
	public void testSetCliente() {
		cliente=new EmpleadoPretenso();
		this.c.setCliente(cliente);
		Assert.assertEquals("no cambio el cliente",cliente,c.getCliente());
	}


}
