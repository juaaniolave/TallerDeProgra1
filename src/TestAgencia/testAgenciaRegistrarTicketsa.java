package TestAgencia;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.ContraException;
import excepciones.ImposibleModificarTicketsException;
import excepciones.NombreUsuarioException;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

public class testAgenciaRegistrarTicketsa {
	Agencia agencia=Agencia.getInstance();
	Empleador empleador;
	EmpleadoPretenso empleadoPretenso;
	@Before
	public void setUp() throws Exception {
		this.empleador = (Empleador) agencia.registroEmpleador("user1", "pass1", "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
		this.empleadoPretenso = (EmpleadoPretenso) agencia.registroEmpleado("user2", "pass2", "nombR2", "ap1", "456", 30);
		this.agencia.crearTicketEmpleado(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleadoPretenso);
		this.agencia.crearTicketEmpleador(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleador);
	}

	@After
	public void tearDown() throws Exception {
		agencia.getEmpleados().clear();
		agencia.getEmpleadores().clear();
		this.agencia.setEstadoContratacion(false);
	}

	@Test
	public void testTicketEmpleadorLocacion() {
		Assert.assertEquals("las locaciones deben ser iguales",this.empleador.getTicket().getLocacion(),Constantes.PRESENCIAL);
	}
	@Test
	public void testTicketEmpleadorRemuneracion() {
		Assert.assertEquals("las remuneracion deben ser iguales",this.empleador.getTicket().getRemuneracion(),1500);
	}
	@Test
	public void testTicketEmpleadorJornada() {
		Assert.assertEquals("las jornadas deben ser iguales",this.empleador.getTicket().getJornada(),Constantes.JORNADA_COMPLETA);
	}
	@Test
	public void testTicketEmpleadorPuesto() {
		Assert.assertEquals("los puestos deben ser iguales",this.empleador.getTicket().getPuesto(),Constantes.JUNIOR);
	}
	@Test
	public void testTicketEmpleadorEstudios() {
		Assert.assertEquals("los estudios deben ser iguales",this.empleador.getTicket().getEstudios(),Constantes.TERCIARIOS);
	}
	@Test
	public void testTicketEmpleadorExperiencia() {
		Assert.assertEquals("las experiencias deben ser iguales",this.empleador.getTicket().getExperiencia(),Constantes.EXP_MEDIA);
	}
	
	@Test
	public void testTicketEmpleadoLocacion() {
		Assert.assertEquals("las locaciones deben ser iguales",this.empleadoPretenso.getTicket().getLocacion(),Constantes.PRESENCIAL);
	}
	@Test
	public void testTicketEmpleadoRemuneracion() {
		Assert.assertEquals("las remuneracion deben ser iguales",this.empleadoPretenso.getTicket().getRemuneracion(),1500);
	}
	@Test
	public void testTicketEmpleadoJornada() {
		Assert.assertEquals("las jornadas deben ser iguales",this.empleadoPretenso.getTicket().getJornada(),Constantes.JORNADA_COMPLETA);
	}
	@Test
	public void testTicketEmpleadoPuesto() {
		Assert.assertEquals("los puestos deben ser iguales",this.empleadoPretenso.getTicket().getPuesto(),Constantes.JUNIOR);
	}
	@Test
	public void testTicketEmpleadoEstudios() {
		Assert.assertEquals("los estudios deben ser iguales",this.empleadoPretenso.getTicket().getEstudios(),Constantes.TERCIARIOS);
	}
	@Test
	public void testTicketEmpleadoExperiencia() {
		Assert.assertEquals("las experiencias deben ser iguales",this.empleadoPretenso.getTicket().getExperiencia(),Constantes.EXP_MEDIA);
	}
	
	@Test
	public void testTicketCambioEmpleado() {
		try {
			this.agencia.login("user2","pass2");
		} catch (ContraException | NombreUsuarioException e1) {
		}
		try {
			this.agencia.crearTicketEmpleado(Constantes.HOME_OFFICE, 2500, Constantes.JORNADA_MEDIA, Constantes.SENIOR, Constantes.EXP_MUCHA, Constantes.SECUNDARIOS,agencia.getEmpleados().get("user2"));
			Assert.assertEquals("deben ser la misma locacion",Constantes.HOME_OFFICE,agencia.getEmpleados().get("user2").getTicket().getLocacion());
			Assert.assertEquals("debe ser la misma remuneracion",this.empleadoPretenso.getTicket().getRemuneracion(),2500);
			Assert.assertEquals("debe ser la misma jornada",this.empleadoPretenso.getTicket().getJornada(),Constantes.JORNADA_MEDIA);
			Assert.assertEquals("debe ser el mismo puesto",this.empleadoPretenso.getTicket().getPuesto(),Constantes.SENIOR);
			Assert.assertEquals("deben ser la misma experiencia",this.empleadoPretenso.getTicket().getExperiencia(),Constantes.EXP_MUCHA);
			Assert.assertEquals("deben ser los mismo estudios",this.empleadoPretenso.getTicket().getEstudios(),Constantes.SECUNDARIOS);
		} catch (ImposibleModificarTicketsException e) {

		}
	} 
	@Test
	public void testTicketCambioEmpleador() {
		try {
			this.agencia.login("user1","pass1");
		} catch (ContraException | NombreUsuarioException e1) {
		}
		try {
			this.agencia.crearTicketEmpleado(Constantes.HOME_OFFICE, 2500, Constantes.JORNADA_MEDIA, Constantes.SENIOR, Constantes.EXP_MUCHA, Constantes.SECUNDARIOS,agencia.getEmpleadores().get("user1"));
			Assert.assertEquals("deben ser la misma locacion",Constantes.HOME_OFFICE,this.empleador.getTicket().getLocacion());
			Assert.assertEquals("debe ser la misma remuneracion",this.empleador.getTicket().getRemuneracion(),2500);
			Assert.assertEquals("debe ser la misma jornada",this.empleador.getTicket().getJornada(),Constantes.JORNADA_MEDIA);
			Assert.assertEquals("debe ser el mismo puesto",this.empleador.getTicket().getPuesto(),Constantes.SENIOR);
			Assert.assertEquals("deben ser la misma experiencia",this.empleador.getTicket().getExperiencia(),Constantes.EXP_MUCHA);
			Assert.assertEquals("deben ser los mismo estudios",this.empleador.getTicket().getEstudios(),Constantes.SECUNDARIOS);
		} catch (ImposibleModificarTicketsException e) {

		}

	} 
	@Test
	public void testTicketEmpleadorExcepcion() {
		this.agencia.setEstadoContratacion(true);
		try {
			this.agencia.crearTicketEmpleado(Constantes.HOME_OFFICE, 2500, Constantes.JORNADA_MEDIA, Constantes.SENIOR, Constantes.EXP_MUCHA, Constantes.SECUNDARIOS,agencia.getEmpleadores().get("user1"));
			Assert.fail("debe lanzar excepcion");
		} catch (ImposibleModificarTicketsException e) {
		}
	}
	@Test
	public void testTicketEmpleadoExcepcion() {
		this.agencia.setEstadoContratacion(true);
		try {
			this.agencia.crearTicketEmpleado(Constantes.HOME_OFFICE, 2500, Constantes.JORNADA_MEDIA, Constantes.SENIOR, Constantes.EXP_MUCHA, Constantes.SECUNDARIOS,agencia.getEmpleados().get("user2"));
			Assert.fail("debe lanzar excepcion");
		} catch (ImposibleModificarTicketsException e) {
		}
	}
	

}
