package testsModeloDatos;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
import util.Constantes;

public class TestListaPostulantes {
	ClientePuntaje c;
	double puntaje;
	Cliente cliente,cliente2;
	ArrayList<ClientePuntaje>listaClientePuntaje=new ArrayList<ClientePuntaje>();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		puntaje=20.0;
		cliente=new Empleador("Pepe32","Boca123","Pedro","223",Constantes.JURIDICA, Constantes.SALUD);
		c=new ClientePuntaje(this.puntaje,cliente);
		listaClientePuntaje.add(c);
		cliente2=new EmpleadoPretenso();
		cliente2.setListaDePostulantes(listaClientePuntaje);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testListaPostulantes() {
		ArrayList<ClientePuntaje>listaClientes=cliente2.getListaDePostulantes();
		Assert.assertEquals("Error deberia ser el mismo Postulante","Pepe32",listaClientes.get(0).getCliente().getUsserName());
	}

}
