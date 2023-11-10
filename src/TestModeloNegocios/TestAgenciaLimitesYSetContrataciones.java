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

import excepciones.LimiteInferiorRemuneracionInvalidaException;
import excepciones.LimiteSuperiorRemuneracionInvalidaException;
import modeloDatos.Cliente;
import modeloDatos.Contratacion;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

public class TestAgenciaLimitesYSetContrataciones {
	Agencia agencia = Agencia.getInstance();
	Empleador empleador;
	EmpleadoPretenso empleadoPretenso;
	Contratacion c;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.empleador = (Empleador) agencia.registroEmpleador("user1", "pass1", "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
		this.empleadoPretenso = (EmpleadoPretenso) agencia.registroEmpleado("user2", "pass2", "nombR2", "ap1", "456", 30);
		this.agencia.crearTicketEmpleado(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleadoPretenso);
		this.agencia.crearTicketEmpleador(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleador);
		this.agencia.setLimitesRemuneracion(1000, 3000);
		c=new Contratacion(empleador,empleadoPretenso);
		ArrayList<Contratacion> alc=new ArrayList<Contratacion>();
		alc.add(c);
		agencia.setContrataciones(alc);
	}

	@After
	public void tearDown() throws Exception {
		HashMap<String,Empleador> vacio1=new HashMap<String,Empleador>();
		HashMap<String,EmpleadoPretenso> vacio2=new HashMap<String,EmpleadoPretenso>();
		HashMap<Cliente,Double> vacio3=new HashMap<Cliente,Double>();
		ArrayList<Contratacion> vacio4=new ArrayList<Contratacion>();
		agencia.setEmpleados(vacio2);
		agencia.setEmpleadores(vacio1);
		agencia.setContrataciones(vacio4);
		agencia.setComisionesUsuarios(vacio3);
		agencia.setLimitesRemuneracion(1000, 3000);
	}

	@Test
	public void testLimiteSuperiorCorrecto() {
		Assert.assertEquals("el limite debe cambiar",agencia.getLimiteSuperior(),3000);
	}
	@Test
	public void testLimiteInferiorCorrecto() {
		Assert.assertEquals("el limite debe cambiar",agencia.getLimiteInferior(),1000);
	}
	
	@Test
	public void testSetLimitesRemuneracionExcepcion1() {
		try {
			agencia.setLimitesRemuneracion(1000, 500);
			Assert.fail("debe haber tirado una excepcion");
		} catch (LimiteSuperiorRemuneracionInvalidaException e) {
		} catch (LimiteInferiorRemuneracionInvalidaException e) {
			Assert.fail(e.getMessage());
		}
	}
	@Test
	public void testSetLimitesRemuneracionExcepion2() {
		try {
			agencia.setLimitesRemuneracion(-1000, 500);
			Assert.fail("debe haber tirado una excepcion");
		} catch (LimiteSuperiorRemuneracionInvalidaException e) {
			Assert.fail(e.getMessage());
		} catch (LimiteInferiorRemuneracionInvalidaException e) {
		}
	}
	
	@Test
	public void testGetContratacionEmpleado() {
		Empleador e=(Empleador) agencia.getContratacionEmpleadoPretenso(empleadoPretenso);
		Assert.assertEquals("deben ser el mismo empleador",e,empleador);
	}
	@Test
	public void testGetContratacionEmpleador() {
		EmpleadoPretenso e=(EmpleadoPretenso)agencia.getContratacionEmpleador(empleador);
		Assert.assertEquals("deben ser el mismo empleador",e,empleadoPretenso);
	}
	

}
