package TestModeloNegocios;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

public class TestCalculaPremiosCastigosEsc2 {
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
		e1=(Empleador) a.registroEmpleador("user1","pass1","nombR1","123",Constantes.JURIDICA,Constantes.SALUD);
		ep1=(EmpleadoPretenso) a.registroEmpleado("user4","pass4","nombR4","123","ap1",20);
		e1.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.SECUNDARIOS));
		ep1.setTicket(new MockTicket(Constantes.HOME_OFFICE, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.SECUNDARIOS));
		
		a.generaPostulantes();
		a.calculaPremiosCastigosAsignaciones();
	}

	@After
	public void tearDown() throws Exception {
		a.setEmpleadores(new HashMap<String,Empleador>());
		a.setEmpleados(new HashMap<String,EmpleadoPretenso>());
		e1.setListaDePostulantes(null);
		ep1.setListaDePostulantes(null);
	}

	@Test
	public void testNocambioPuntajeEmpleado() {
		Assert.assertEquals("no debio haber camiado el valor",0.0,ep1.getPuntaje(),0.0);
	}
	@Test
	public void testCambioPuntajeEmpleador() {
		Assert.assertEquals("no debio haber camiado el valor",10.0,e1.getPuntaje(),0.0);

	}

}
