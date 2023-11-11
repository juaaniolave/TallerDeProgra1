package TestModeloNegocios;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

public class TestGatillarRondaEsc1 {
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
		a.setEstadoContratacion(false);
		e1=(Empleador) a.registroEmpleador("user1","pass1","nombR1","123",Constantes.JURIDICA,Constantes.SALUD);
		e2=(Empleador) a.registroEmpleador("user2","pass2","nombR2","123",Constantes.FISICA,Constantes.COMERCIO_LOCAL);
		
		ep1=(EmpleadoPretenso) a.registroEmpleado("user4","pass4","nombR4","123","ap1",20);
		ep2=(EmpleadoPretenso) a.registroEmpleado("user5","pass5","nombR5","123","ap2",30);
		
		e1.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.SECUNDARIOS));
		e2.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS));
		
		ep1.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.SECUNDARIOS));
		ep2.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS));
		
		a.gatillarRonda();
	}

	@After
	public void tearDown() throws Exception {
		a.setEmpleadores(new HashMap<String,Empleador>());
		a.setEmpleados(new HashMap<String,EmpleadoPretenso>());
		e1.setListaDePostulantes(null);
		e2.setListaDePostulantes(null);
		e1.setPuntaje(0);
		e2.setPuntaje(0);
		ep1.setListaDePostulantes(null);
		ep2.setListaDePostulantes(null);
		ep1.setPuntaje(0);
		ep2.setPuntaje(0);
		a.setEstadoContratacion(false);
	}
	@Test
	public void testListaBienCreadaEmpleadorE11() {
		Assert.assertEquals("deben ser el mismo usuario","user5",e1.getListaDePostulantes().get(0).getCliente().getUsserName());
	}
	@Test
	public void testListaBienCreadaEmpleadorE12() {
		Assert.assertEquals("deben ser el mismo usuario","user4",e1.getListaDePostulantes().get(1).getCliente().getUsserName());
	}
	@Test
	public void testListaBienCreadaEmpleadoEP11() {
		Assert.assertEquals("deben ser el mismo usuario","user1",ep1.getListaDePostulantes().get(0).getCliente().getUsserName());
	}
	@Test
	public void testListaBienCreadaEmpleadoEP12() {
		Assert.assertEquals("deben ser el mismo usuario","user2",ep1.getListaDePostulantes().get(1).getCliente().getUsserName());
	}
	@Test
	public void testListaBienCreadaEmpleadorE21() {
		Assert.assertEquals("deben ser el mismo usuario","user5",e2.getListaDePostulantes().get(0).getCliente().getUsserName());
	}
	@Test
	public void testListaBienCreadaEmpleadorE22() {
		Assert.assertEquals("deben ser el mismo usuario","user4",e2.getListaDePostulantes().get(1).getCliente().getUsserName());
	}
	@Test
	public void testListaBienCreadaEmpleadoEP21() {
		Assert.assertEquals("deben ser el mismo usuario","user1",ep2.getListaDePostulantes().get(0).getCliente().getUsserName());
	}
	@Test
	public void testListaBienCreadaEmpleadoEP22() {
		Assert.assertEquals("deben ser el mismo usuario","user2",ep2.getListaDePostulantes().get(1).getCliente().getUsserName());
	}
	@Test
	public void testPuntajeEmpleador1() {
		Assert.assertEquals("deben ser el mismo puntaje",20.0,e1.getPuntaje(),0.0);

	}
	@Test
	public void testPuntajeEmpleador2() {
		Assert.assertEquals("deben ser el mismo puntaje",0.0,e2.getPuntaje(),0.0);
	}
	@Test
	public void testPuntajeEmpleado1() {
		Assert.assertEquals("deben ser el mismo puntaje",-10.0,ep1.getPuntaje(),0.0);

	}
	@Test
	public void testPuntajeEmpleado2() {
		Assert.assertEquals("deben ser el mismo puntaje",10.0,ep2.getPuntaje(),0.0);
	}
	
}
