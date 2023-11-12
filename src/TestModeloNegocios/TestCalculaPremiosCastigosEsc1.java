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
	Empleador e1;
	Empleador e3;
	EmpleadoPretenso ep1;
	EmpleadoPretenso ep3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//en este escenario se testea que, el EMPLEADO 3 este en la primera posicion de todos los empreadores
		//y el EMPLEADO 1 esta en la ultima posicion de todos los empleadores
		//el EMPLEADOR 1 esta primero en todas las listas de empleadores
		e1=(Empleador) a.registroEmpleador("user1","pass1","nombR1","123",Constantes.JURIDICA,Constantes.SALUD);
		e3=(Empleador) a.registroEmpleador("user3","pass3","nombR3","123",Constantes.JURIDICA,Constantes.COMERCIO_INTERNACIONAL);
		
		ep1=(EmpleadoPretenso) a.registroEmpleado("user4","pass4","nombR4","123","ap1",20);
		ep3=(EmpleadoPretenso) a.registroEmpleado("user6","pass6","nombR6","123","ap3",40);
		
		e1.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.SECUNDARIOS));
		e3.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MUCHA, Constantes.TERCIARIOS));
		ep1.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.SECUNDARIOS));
		ep3.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MUCHA, Constantes.TERCIARIOS));
		
		a.generaPostulantes();
		
		a.calculaPremiosCastigosAsignaciones();
		
		
	}

	@After
	public void tearDown() throws Exception {
		a.setEmpleadores(new HashMap<String,Empleador>());
		a.setEmpleados(new HashMap<String,EmpleadoPretenso>());
		e1.setListaDePostulantes(null);
		e3.setListaDePostulantes(null);
		ep1.setListaDePostulantes(null);
		ep3.setListaDePostulantes(null);
	}

	@Test
	public void testMayorListaEmpleador() {
		Assert.assertEquals("deberian tener el mismo puntaje",10,ep3.getPuntaje(),0.0);
	}
	@Test
	public void testMenorListaEmpleador() {
		Assert.assertEquals("deberian tener el mismo puntaje",-15,ep1.getPuntaje(),0.0);
	}
	@Test
	public void testMayorListaEmpleado() {
		Assert.assertEquals("deberian tener el mismo puntaje",20.0,e1.getPuntaje(),0.0);
	}

}
