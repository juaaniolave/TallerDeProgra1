package TestAgencia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.ContraException;
import excepciones.ImposibleCrearEmpleadoException;
import excepciones.ImposibleCrearEmpleadorException;
import excepciones.ImposibleModificarTicketsException;
import excepciones.LimiteInferiorRemuneracionInvalidaException;
import excepciones.LimiteSuperiorRemuneracionInvalidaException;
import excepciones.NewRegisterException;
import excepciones.NombreUsuarioException;
import modeloDatos.Cliente;
import modeloDatos.ClientePuntaje;
import modeloDatos.Contratacion;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloDatos.Ticket;
import modeloNegocio.Agencia;
import persistencia.IPersistencia;
import util.Constantes;

class testAgenciaAlgunosMetodos {
	
	Agencia agencia = Agencia.getInstance();
	Empleador empleador;
	EmpleadoPretenso empleadoPretenso;
	Empleador empleador2;
	EmpleadoPretenso empleadoPretenso2;
	Contratacion contratacion;
	HashMap<Cliente, Double> comisionesCliente;
	int limiteInferior;
	int limiteSuperior;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		//este es un escenario posible algunos metodos requieren de otros escenarios tambien
		this.empleador = (Empleador) agencia.registroEmpleador("user1", "pass1", "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
		this.empleadoPretenso = (EmpleadoPretenso) agencia.registroEmpleado("user2", "pass2", "nombR2", "ap1", "456", 30);
		this.empleador2 = (Empleador) agencia.registroEmpleador("user3", "pass3", "nombR3", "789", Constantes.FISICA, Constantes.COMERCIO_LOCAL);
		this.empleadoPretenso2 = (EmpleadoPretenso) agencia.registroEmpleado("user4", "pass4", "nombR4", "ap2", "000", 35);
		this.agencia.crearTicketEmpleado(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleadoPretenso);
		this.agencia.crearTicketEmpleador(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleador);
		this.agencia.crearTicketEmpleado(Constantes.HOME_OFFICE, 2000, Constantes.JORNADA_MEDIA, Constantes.MANAGMENT, Constantes.EXP_NADA, Constantes.SECUNDARIOS,empleadoPretenso2);
		this.agencia.crearTicketEmpleador(Constantes.INDISTINTO, 4000, Constantes.JORNADA_EXTENDIDA, Constantes.SENIOR, Constantes.EXP_MUCHA, Constantes.PRIMARIOS,empleador2);
		this.limiteInferior = 1000;
		this.limiteSuperior = 3000;
		this.agencia.setLimitesRemuneracion(limiteInferior, limiteSuperior);
	}

	@AfterEach
	void tearDown() throws Exception {
		HashMap<String,Empleador> vacio1=new HashMap<String,Empleador>();
		HashMap<String,EmpleadoPretenso> vacio2=new HashMap<String,EmpleadoPretenso>();
		HashMap<Cliente,Double> vacio3=new HashMap<Cliente,Double>();
		ArrayList<Contratacion> vacio4=new ArrayList<Contratacion>();
		agencia.setEmpleados(vacio2);
		agencia.setEmpleadores(vacio1);
		agencia.setContrataciones(vacio4);
		agencia.setComisionesUsuarios(vacio3);
		empleador.setListaDePostulantes(null);
		empleadoPretenso.setListaDePostulantes(null);
		empleador2.setListaDePostulantes(null);
		empleadoPretenso2.setListaDePostulantes(null);
		empleador.setPuntaje(0);
		empleadoPretenso.setPuntaje(0);
		empleador2.setPuntaje(0);
		empleadoPretenso2.setPuntaje(0);
		agencia.setLimitesRemuneracion(1000, 3000);
		agencia.setEstadoContratacion(false);
	}

	@Test
	public void testLimitesRemuneracionSup() {
		Assert.assertEquals("el limite superior deberia existir", agencia.getLimiteSuperior(),3000);
	}
	@Test
	public void testLimitesRemuneracionInf() {
		Assert.assertEquals("el limite inferior deberia existir", agencia.getLimiteInferior(),1000);
	}
	@Test
	public void testMatch() {
		
		agencia.match(empleador,empleadoPretenso);
		Contratacion c=new Contratacion(empleador,empleadoPretenso);
		ArrayList<Contratacion> ac=agencia.getContrataciones();
		GregorianCalendar calendar = new GregorianCalendar();
		Contratacion c2=ac.get(0);
		Assert.assertEquals("el ticket deberia ser null",empleador.getTicket(),null);
		Assert.assertEquals("el ticket deberia ser null",empleadoPretenso.getTicket(),null);
		Assert.assertEquals("el puntaje debe ser 50",empleador.getPuntaje(),50);
		Assert.assertEquals("el puntaje debe ser 10",empleadoPretenso.getPuntaje(),10);
		Assert.assertEquals("deberia devolver el usuario asociado",agencia.getContratacionEmpleadoPretenso(empleadoPretenso),empleador);
		Assert.assertEquals("deberia devolver el usuario asociado",agencia.getContratacionEmpleador(empleador),empleadoPretenso);
		Assert.assertEquals("deberian ser los mismos datos de contratacion (FECHA)",calendar,c2.getFecha());
		Assert.assertEquals("deberian ser los mismos datos de contratacion (EMPELADO)",c.getEmpleado(),c2.getEmpleado());
		Assert.assertEquals("deberian ser los mismos datos de contratacion (EMPELADOR)",c.getEmpleador(),c2.getEmpleador());
		Ticket t=new Ticket(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS);
		Assert.assertEquals("deberian ser la misma remuneracion del empleador",empleador.calculaComision(t),agencia.getComisionUsuario(empleador));
		Assert.assertEquals("deberian ser la misma remuneracion del empleador",empleadoPretenso.calculaComision(t),agencia.getComisionUsuario(empleadoPretenso));

		
	}
	@Test
	public void testSetLimitesRemuneracion() {
		//en este escenario no se chequean las exepciones solo que cargue bien el numero
		try {
			agencia.setLimitesRemuneracion(500, 1500);
			Assert.assertEquals("el limite debe cambiar",agencia.getLimiteInferior(),500);
			Assert.assertEquals("el limite debe cambiar",agencia.getLimiteSuperior(),1500);
		} catch (LimiteSuperiorRemuneracionInvalidaException e) {
			Assert.fail(e.getMessage());
		} catch (LimiteInferiorRemuneracionInvalidaException e) {
			Assert.fail(e.getMessage());
		}
	}
	@Test
	public void testSetLimitesRemuneracion2() {
		try {
			agencia.setLimitesRemuneracion(1000, 500);
			Assert.fail("debe haber tirado una excepcion");
		} catch (LimiteSuperiorRemuneracionInvalidaException e) {
		} catch (LimiteInferiorRemuneracionInvalidaException e) {
			Assert.fail(e.getMessage());
		}
	}
	@Test
	public void testSetLimitesRemuneracion3() {
		try {
			agencia.setLimitesRemuneracion(-1000, 500);
			Assert.fail("debe haber tirado una excepcion");
		} catch (LimiteSuperiorRemuneracionInvalidaException e) {
			Assert.fail(e.getMessage());
		} catch (LimiteInferiorRemuneracionInvalidaException e) {
		}
	}
	@Test
	public void testGeneraPostulantes() {
		agencia.generaPostulantes();
		ClientePuntaje c=new ClientePuntaje(6.0,empleadoPretenso);
		ClientePuntaje c2=new ClientePuntaje(-3.5,empleadoPretenso2);
		//probamos la lista de 1 solo empleador porque es suficiente para el ejemplo
		Assert.assertEquals("el puntaje debe ser el mismo 1",empleador.getListaDePostulantes().get(0).getPuntaje(),c.getPuntaje(),0.0);
		Assert.assertEquals("el cliente debe ser el mismo 1",empleador.getListaDePostulantes().get(0).getCliente(),c.getCliente());
		Assert.assertEquals("el puntaje debe ser el mismo 2",empleador.getListaDePostulantes().get(1).getPuntaje(),c2.getPuntaje(),0.0);
		Assert.assertEquals("el cliente debe ser el mismo 2",empleador.getListaDePostulantes().get(1).getCliente(),c2.getCliente());
		
		ClientePuntaje c3=new ClientePuntaje(6.0,empleador);
		ClientePuntaje c4=new ClientePuntaje(1.0,empleador2);
		//probamos la lista de 1 solo empleado porque es suficiente para el ejemplo
		Assert.assertEquals("el puntaje debe ser el mismo 3",empleadoPretenso.getListaDePostulantes().get(0).getPuntaje(),c3.getPuntaje(),0.0);
		Assert.assertEquals("el cliente debe ser el mismo 3",empleadoPretenso.getListaDePostulantes().get(0).getCliente(),c3.getCliente());
		Assert.assertEquals("el puntaje debe ser el mismo 4",empleadoPretenso.getListaDePostulantes().get(1).getPuntaje(),c4.getPuntaje(),0.0);
		Assert.assertEquals("el cliente debe ser el mismo 4",empleadoPretenso.getListaDePostulantes().get(1).getCliente(),c4.getCliente());
		
	}
	@Test
	public void testEliminarTicket() throws ContraException, NombreUsuarioException  {
			agencia.login("user1", "pass1");
			agencia.setContrataciones(null);
			try {
				agencia.eliminarTicket();
				Assert.assertEquals("el ticket deberia ser eliminado",null,empleador.getTicket());
			} catch (ImposibleModificarTicketsException e) {
				Assert.fail(e.getMessage());
			}
	}
	@Test
	public void testEliminarTicketExcepcion() throws ContraException, NombreUsuarioException  {
			agencia.login("user1", "pass1");
			agencia.setEstadoContratacion(true);
			try {
				agencia.eliminarTicket();
				Assert.fail("debe lanzar excepcion");
			} catch (ImposibleModificarTicketsException e) {
				
			}
	}
	@Test
	public void testRegistroEmpleador() {
		//analizamos el caso de que ya exista un usuario
		try {
			agencia.registroEmpleador("user1", "pass1", "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
			Assert.fail("debe haber lanzado exepcion");
		} catch (NewRegisterException e) {
		} catch (ImposibleCrearEmpleadorException e) {
			Assert.fail(e.getMessage());
		}

	}
	@Test
	public void testRegistroEmpleado() {
		//analizamos el caso de que ya exista un usuario
			try {
				EmpleadoPretenso empleado = (EmpleadoPretenso) agencia.registroEmpleado("user2", "pass2", "nombR2", "ap1", "456", 30);
				Assert.fail("debe haber lanzado exepcion");
			} catch (NewRegisterException e) {
			} catch (ImposibleCrearEmpleadoException e) {
				Assert.fail(e.getMessage());
			}
	}
	@Test
	public void testRegistroEmpleador2() {
		//analizamos el caso de que el usuario que se quiera crear tenga mal las credenciales
		try {
			agencia.registroEmpleador(null, "pass1", "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
			Assert.fail("debe haber lanzado exepcion porque user es null");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadorException e) {
		}
		try {
			agencia.registroEmpleador("usej", null, "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
			Assert.fail("debe haber lanzado exepcion porque password es null");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadorException e) {
		}
		try {
			agencia.registroEmpleador("usej", "pass1", null, "123", Constantes.JURIDICA, Constantes.SALUD);
			Assert.fail("debe haber lanzado exepcion porque nombre real es null");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadorException e) {
		}
		try {
			agencia.registroEmpleador("usej", "pass1", "nombR1", null, Constantes.JURIDICA, Constantes.SALUD);
			Assert.fail("debe haber lanzado exepcion porque telefono es null");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadorException e) {
		}
		try {
			agencia.registroEmpleador("usej", "pass1", "nombR1", "123", "MATERIAL", Constantes.SALUD);
			Assert.fail("debe haber lanzado exepcion porque tipo no concuerda");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadorException e) {
		}
		try {
			agencia.registroEmpleador("usej", "pass1", "nombR1", "123", Constantes.JURIDICA, "OTRO");
			Assert.fail("debe haber lanzado exepcion porque rubro no concuerda");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadorException e) {
		}
	}
	@Test
	public void testRegistroEmpleado2() {
		try {
			agencia.registroEmpleado(null, "pass", "nombreR", "ap", "12343", 30);
			Assert.fail("deberia dar exepcion usuario nulo");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadoException e) {
		}
		try {
			agencia.registroEmpleado("usej", null, "nombreR", "ap", "12343", 30);
			Assert.fail("deberia dar exepcion password nulo");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadoException e) {
		}
		try {
			agencia.registroEmpleado("usej", "pass", null, "ap", "12343", 30);
			Assert.fail("deberia dar exepcion nombre real nulo");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadoException e) {
		}
		try {
			agencia.registroEmpleado("usej", "pass", "nombreR", null, "12343", 30);
			Assert.fail("deberia dar exepcion apellido nulo");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadoException e) {
		}
		try {
			agencia.registroEmpleado("usej", "pass", "nombreR", "ap", null, 30);
			Assert.fail("deberia dar exepcion telefono nulo");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadoException e) {
		}
	}
	@Test
	public void testRegistroEmpleador3() {
		//este es el caso que se reistra correctamente
		Empleador emp;
		try {
			emp = (Empleador) agencia.registroEmpleador("USEJ", "pass1", "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
			Empleador emp2=agencia.getEmpleadores().get("USEJ");
			Assert.assertEquals("deben ser el mismo usuario",emp,emp2);
		} catch (ImposibleCrearEmpleadorException e) {
		}
		catch (NewRegisterException e) {
		} 
	}
	@Test
	public void testRegistroEmpleado3() {
		//este es el caso que se reistra correctamente
		EmpleadoPretenso emp;
		try {
			emp = (EmpleadoPretenso) agencia.registroEmpleado("USEJ", "pass2", "nombR2", "ap1", "456", 30);
			EmpleadoPretenso emp2=agencia.getEmpleados().get("USEJ");
			Assert.assertEquals("deben ser el mismo usuario",emp,emp2);
		} catch (NewRegisterException e) {
		} catch (ImposibleCrearEmpleadoException e) {
		}
	}
	@Test
	public void testGetContratacionEmpleado() {
		Contratacion c=new Contratacion(empleador,empleadoPretenso);
		ArrayList<Contratacion> alc=new ArrayList<Contratacion>();
		alc.add(c);
		agencia.setContrataciones(alc);
		Empleador e=(Empleador) agencia.getContratacionEmpleadoPretenso(empleadoPretenso);
		Assert.assertEquals("deben ser el mismo empleador",e,empleador);
	}
	@Test
	public void testGetContratacionEmpleador() {
		Contratacion c=new Contratacion(empleador,empleadoPretenso);
		ArrayList<Contratacion> alc=new ArrayList<Contratacion>();
		alc.add(c);
		agencia.setContrataciones(alc);
		EmpleadoPretenso e=(EmpleadoPretenso)agencia.getContratacionEmpleador(empleador);
		Assert.assertEquals("deben ser el mismo empleador",e,empleadoPretenso);
	}


}
