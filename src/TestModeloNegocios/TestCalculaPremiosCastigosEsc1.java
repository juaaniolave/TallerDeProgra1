package TestModeloNegocios;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import modeloDatos.ClientePuntaje;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

public class TestCalculaPremiosCastigosEsc1 {
	Agencia a=Agencia.getInstance();
	Empleador e1=new Empleador("user1","pass1","nombR1","123",Constantes.SALUD,Constantes.JURIDICA);
	Empleador e2=new Empleador("user2","pass2","nombR2","123",Constantes.COMERCIO_LOCAL,Constantes.FISICA);
	Empleador e3=new Empleador("user3","pass3","nombR3","123",Constantes.COMERCIO_INTERNACIONAL,Constantes.JURIDICA);;
	EmpleadoPretenso ep1=new EmpleadoPretenso("user4","pass4","nombR4","123","ap1",20);
	EmpleadoPretenso ep2=new EmpleadoPretenso("user5","pass5","nombR5","123","ap2",30);
	EmpleadoPretenso ep3=new EmpleadoPretenso("user6","pass6","nombR6","123","ap3",40);
	ArrayList<ClientePuntaje> al1=new ArrayList<ClientePuntaje>();
	ArrayList<ClientePuntaje> al2=new ArrayList<ClientePuntaje>();
	HashMap<String,Empleador>h1=new HashMap<String,Empleador>();
	HashMap<String,EmpleadoPretenso>h2=new HashMap<String,EmpleadoPretenso>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ClientePuntaje c1=new ClientePuntaje(30,e1);
		ClientePuntaje c2=new ClientePuntaje(20,e2);
		ClientePuntaje c3=new ClientePuntaje(10,e3);
		al1.add(c1);al1.add(c2);al1.add(c3);
		ep1.setListaDePostulantes(al1);
		
		ClientePuntaje c4=new ClientePuntaje(30,ep1);
		ClientePuntaje c5=new ClientePuntaje(20,ep2);
		ClientePuntaje c6=new ClientePuntaje(10,ep3);
		al2.add(c4);al2.add(c5);al2.add(c6);
		e1.setListaDePostulantes(al2);
		
		h1.put("user1", e1);
		h2.put("user4", ep1);
		
		a.setEmpleadores(h1);
		a.setEmpleados(h2);
		
		a.calculaPremiosCastigosAsignaciones();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMayorListaEmpleador() {
		Assert.assertEquals("el puntaje debe ser el mismo", a.getEmpleadores().get("user1").getListaDePostulantes().get(0).getPuntaje(),35);
	}

}
