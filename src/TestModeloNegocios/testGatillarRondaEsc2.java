package TestModeloNegocios;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import modeloDatos.Cliente;
import modeloDatos.Contratacion;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

public class testGatillarRondaEsc2 {
	Agencia a = Agencia.getInstance();
	Empleador e1;
	Empleador e2;

	EmpleadoPretenso ep1;
	EmpleadoPretenso ep2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//en este caso el e1 y ep1 matchean y los otros no
		a.setEstadoContratacion(true);
		e1=(Empleador) a.registroEmpleador("user1","pass1","nombR1","123",Constantes.JURIDICA,Constantes.SALUD);
		e2=(Empleador) a.registroEmpleador("user2","pass2","nombR2","123",Constantes.FISICA,Constantes.COMERCIO_LOCAL);
		
		ep1=(EmpleadoPretenso) a.registroEmpleado("user4","pass4","nombR4","123","ap1",20);
		ep2=(EmpleadoPretenso) a.registroEmpleado("user5","pass5","nombR5","123","ap2",30);
		
		e1.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.SECUNDARIOS));
		e2.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS));
		ep1.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.SECUNDARIOS));
		ep2.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS));
		
		e1.setCandidato(ep1);
		ep1.setCandidato(e1);
		e2.setCandidato(ep1);
		ep2.setCandidato(e1);
		a.gatillarRonda();
	}

	@After
	public void tearDown() throws Exception {
		a.setEmpleadores(new HashMap<String,Empleador>());
		a.setEmpleados(new HashMap<String,EmpleadoPretenso>());
		a.setComisionesUsuarios(new HashMap<Cliente,Double>());
		a.setContrataciones(new ArrayList<Contratacion>());
		e1.setListaDePostulantes(null);
		e2.setListaDePostulantes(null);
		e1.setPuntaje(0);
		e2.setPuntaje(0);
		ep1.setListaDePostulantes(null);
		ep2.setListaDePostulantes(null);
		ep1.setPuntaje(0);
		ep2.setPuntaje(0);
		a.setEstadoContratacion(true);
	}

	@Test
	public void testCantidadContratacion() {
		Assert.assertEquals("se debe habre creado una contratacion",1,a.getContrataciones().size());
	}
	@Test
	public void testContratacionEmpleador() {
		Assert.assertEquals("se debe habre creado una contratacion",e1,a.getContrataciones().get(0).getEmpleador());
	}
	@Test
	public void testContratacionEmpleado() {
		Assert.assertEquals("se debe habre creado una contratacion",ep1,a.getContrataciones().get(0).getEmpleado());
	}
	@Test
	public void testeliminacionTicketEmpleado() {
		Assert.assertEquals("se debe haber eliminado el ticket",ep1.getTicket(),null);

	}
	@Test
	public void testeliminacionTicketEmpleador() {
		Assert.assertEquals("se debe haber eliminado el ticket",e1.getTicket(),null);
	}
	@Test
	public void testNoEliminacionTicketEmpleado() {
		Assert.assertNotNull("no se debe haber borrado su ticket",ep2.getTicket());

	}
	@Test
	public void testNoEliminacionTicketEmpleador() {
		Assert.assertNotNull("no se debe haber borrado su ticket",e2.getTicket());
	}
	@Test
	public void testListaVaciaEmpleador1() {
		Assert.assertNull("deberian ser null",e1.getListaDePostulantes());
	}
	@Test
	public void testListaVaciaEmpleador2() {
		Assert.assertNull("deberian ser null",e2.getListaDePostulantes());
	}
	@Test
	public void testListaVaciaEmpleado1() {
		Assert.assertNull("deberian ser null",ep1.getListaDePostulantes());
	}
	@Test
	public void testListaVaciaEmpleado2() {
		Assert.assertNull("deberian ser null",ep2.getListaDePostulantes());
	}
	@Test
	public void testNoFueElegido() {
		Assert.assertEquals("deberian ser -20",-20.0,e2.getPuntaje(),0.0);
	}

}
