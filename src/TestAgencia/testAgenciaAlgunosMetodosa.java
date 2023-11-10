package TestAgencia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
import util.Constantes;

public class testAgenciaAlgunosMetodosa {
	Agencia agencia = Agencia.getInstance();
	Empleador empleador;
	EmpleadoPretenso empleadoPretenso;
	Empleador empleador2;
	EmpleadoPretenso empleadoPretenso2;
	Contratacion contratacion;
	HashMap<Cliente, Double> comisionesCliente;
	int limiteInferior;
	int limiteSuperior;
	@Before
	public void setUp() throws Exception {
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
		Assert.assertEquals("deberian ser la misma remuneracion del empleado",empleadoPretenso.calculaComision(t),agencia.getComisionUsuario(empleadoPretenso));

		
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
	
	@Test
	public void testCrearTicketEmpleadoPretensoLocacion() {
		//estan todos en un mismo metodo porque ninguno tiro error
		Assert.assertEquals("El ticket no tiene la locacion esperada", this.empleadoPretenso.getTicket().getLocacion(),Constantes.PRESENCIAL);
		Assert.assertEquals("El ticket no tiene la experiencia esperada", this.empleadoPretenso.getTicket().getExperiencia(),Constantes.EXP_MEDIA);
		Assert.assertEquals("El ticket no tiene la jornada esperada", this.empleadoPretenso.getTicket().getJornada(),Constantes.JORNADA_COMPLETA );
		Assert.assertEquals("El ticket no tiene le puesto esperado", this.empleadoPretenso.getTicket().getPuesto(),Constantes.JUNIOR );
		Assert.assertEquals("El ticket no tiene la remuneracion esperada", this.empleadoPretenso.getTicket().getRemuneracion(),1500);
		Assert.assertEquals("El ticket no tiene los estudios esperados", this.empleadoPretenso.getTicket().getEstudios(),Constantes.TERCIARIOS);
		try {
			this.agencia.crearTicketEmpleado(Constantes.HOME_OFFICE, 2000, Constantes.JORNADA_MEDIA, Constantes.MANAGMENT, Constantes.EXP_NADA, Constantes.SECUNDARIOS,this.empleadoPretenso);
		} catch (ImposibleModificarTicketsException e) {
			Assert.fail("No debio tirar la excepcion");
		}
		Assert.assertEquals("El ticket no tiene la locacion esperada", this.empleadoPretenso.getTicket().getLocacion(),Constantes.PRESENCIAL);
		Assert.assertEquals("El ticket no tiene la experiencia esperada", this.empleadoPretenso.getTicket().getExperiencia(),Constantes.EXP_MEDIA);
		Assert.assertEquals("El ticket no tiene la jornada esperada", this.empleadoPretenso.getTicket().getJornada(),Constantes.JORNADA_COMPLETA );
		Assert.assertEquals("El ticket no tiene le puesto esperado", this.empleadoPretenso.getTicket().getPuesto(),Constantes.JUNIOR );
		Assert.assertEquals("El ticket no tiene la remuneracion esperada", this.empleadoPretenso.getTicket().getRemuneracion(),1500);
		Assert.assertEquals("El ticket no tiene los estudios esperados", this.empleadoPretenso.getTicket().getEstudios(),Constantes.TERCIARIOS);
		
	}
	@Test
	public void testCrearTicketEmpleadorLocacion() {	
		//estan todos en un mismo metodo porque ninguno tiro error
		Assert.assertEquals("El ticket no tiene la locacion esperada", this.empleador.getTicket().getLocacion(),Constantes.PRESENCIAL);
		Assert.assertEquals("El ticket no tiene la experiencia esperada", this.empleador.getTicket().getExperiencia(),Constantes.EXP_MEDIA);
		Assert.assertEquals("El ticket no tiene la jornada esperada", this.empleador.getTicket().getJornada(),Constantes.JORNADA_COMPLETA );
		Assert.assertEquals("El ticket no tiene le puesto esperado", this.empleador.getTicket().getPuesto(),Constantes.JUNIOR );
		Assert.assertEquals("El ticket no tiene la remuneracion esperada", this.empleador.getTicket().getRemuneracion(),1500);
		Assert.assertEquals("El ticket no tiene los estudios esperados", this.empleador.getTicket().getEstudios(),Constantes.TERCIARIOS);
		try {
			this.agencia.crearTicketEmpleador(Constantes.INDISTINTO, 4000, Constantes.JORNADA_EXTENDIDA, Constantes.SENIOR, Constantes.EXP_MUCHA, Constantes.PRIMARIOS,this.empleador);
		} catch (ImposibleModificarTicketsException e) {
			Assert.fail("No debio tirar la excepcion");
		}
	}


}
