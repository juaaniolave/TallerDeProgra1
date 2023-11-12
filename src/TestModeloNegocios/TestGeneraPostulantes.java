package TestModeloNegocios;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.ClientePuntaje;
import modeloDatos.Contratacion;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

public class TestGeneraPostulantes {
	Agencia a = Agencia.getInstance();
	Empleador e1;
	Empleador e2;
	Empleador e3;
	EmpleadoPretenso ep1;
	EmpleadoPretenso ep2;
	EmpleadoPretenso ep3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		e1=(Empleador) a.registroEmpleador("user1","pass1","nombR1","123",Constantes.JURIDICA,Constantes.SALUD);
		e2=(Empleador) a.registroEmpleador("user2","pass2","nombR2","123",Constantes.FISICA,Constantes.COMERCIO_LOCAL);
		e3=(Empleador) a.registroEmpleador("user3","pass3","nombR3","123",Constantes.JURIDICA,Constantes.COMERCIO_INTERNACIONAL);
		
		ep1=(EmpleadoPretenso) a.registroEmpleado("user4","pass4","nombR4","123","ap1",20);
		ep2=(EmpleadoPretenso) a.registroEmpleado("user5","pass5","nombR5","123","ap2",30);
		ep3=(EmpleadoPretenso) a.registroEmpleado("user6","pass6","nombR6","123","ap3",40);
		
		e1.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.SECUNDARIOS));
		e2.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS));
		e3.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MUCHA, Constantes.TERCIARIOS));
		
		ep1.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.SECUNDARIOS));
		ep2.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS));
		ep3.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MUCHA, Constantes.TERCIARIOS));

		a.generaPostulantes();
	}

	@After
	public void tearDown() throws Exception {
		a.setEmpleadores(new HashMap<String,Empleador>());
		a.setEmpleados(new HashMap<String,EmpleadoPretenso>());
		e1.setListaDePostulantes(null);
		ep1.setListaDePostulantes(null);
	}
	
	@Test
	public void testMayorEmpleadorPuntaje() {
		Assert.assertEquals("deberian ser el mismo puntaje",7.0,a.getEmpleadores().get("user1").getListaDePostulantes().get(0).getPuntaje(),0.0);
	}
	@Test
	public void testMenorEmpleadorPuntaje() {
		Assert.assertEquals("deberian ser el mismo puntaje",6.0,a.getEmpleadores().get("user1").getListaDePostulantes().get(2).getPuntaje(),0.0);
	}
	@Test
	public void testMayorEmpleadoPuntaje() {
		Assert.assertEquals("deberian ser el mismo puntaje",6.0,a.getEmpleados().get("user4").getListaDePostulantes().get(0).getPuntaje(),0.0);
	}
	@Test
	public void testMenorEmpleadoPuntaje() {
		Assert.assertEquals("deberian ser el mismo puntaje",1.0,a.getEmpleados().get("user4").getListaDePostulantes().get(2).getPuntaje(),0.0);
	}
	
	@Test
	public void testMayorEmpleadorUser() {
		Assert.assertEquals("deberian ser el mismo usuario","user6",a.getEmpleadores().get("user1").getListaDePostulantes().get(0).getCliente().getUsserName());
	}
	@Test
	public void testMenorEmpleadorUser() {
		Assert.assertEquals("deberian ser el mismo usuario","user4",a.getEmpleadores().get("user1").getListaDePostulantes().get(2).getCliente().getUsserName());
	}
	@Test
	public void testMayorEmpleadoUser() {
		Assert.assertEquals("deberian ser el mismo usuario","user1",a.getEmpleados().get("user4").getListaDePostulantes().get(0).getCliente().getUsserName());
	}
	@Test
	public void testMenorEmpleadoUser() {
		Assert.assertEquals("deberian ser el mismo usuario","user3",a.getEmpleados().get("user4").getListaDePostulantes().get(2).getCliente().getUsserName());
	}

	
}
