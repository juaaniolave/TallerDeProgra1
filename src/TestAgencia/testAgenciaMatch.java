package TestAgencia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Contratacion;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloDatos.Ticket;
import modeloNegocio.Agencia;
import util.Constantes;

public class testAgenciaMatch {
	Agencia agencia = Agencia.getInstance();
	Empleador empleador;
	EmpleadoPretenso empleadoPretenso;
	Contratacion c;
	Ticket t;
	GregorianCalendar calendar;
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
		agencia.match(empleador,empleadoPretenso);
		calendar = new GregorianCalendar();
		c=new Contratacion(empleador,empleadoPretenso);
		t=new Ticket(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS);
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
		empleador.setPuntaje(0);
		empleadoPretenso.setPuntaje(0);
	}
	@Test
	public void testMatchTicketEliminadoEmpleador() {
		Assert.assertEquals("el ticket deberia ser null",empleador.getTicket(),null);
	}
	@Test
	public void testMatchTicketEliminadoEmpleado() {
		Assert.assertEquals("el ticket deberia ser null",empleadoPretenso.getTicket(),null);
	}
	@Test
	public void testMatchPuntajeEmpleador() {
		Assert.assertEquals("el puntaje debe ser 50",empleador.getPuntaje(),50);
	}
	@Test
	public void testMatchPuntajeEmpleado() {
		Assert.assertEquals("el puntaje debe ser 10",empleadoPretenso.getPuntaje(),10);
	}
	@Test
	public void testMatchVinculoContratacionEmpleador() {
		Assert.assertEquals("deberia devolver el usuario asociado",agencia.getContratacionEmpleador(empleador),empleadoPretenso);
	}
	@Test
	public void testMatchVinculoContratacionEmpleado() {
		Assert.assertEquals("deberia devolver el usuario asociado",agencia.getContratacionEmpleadoPretenso(empleadoPretenso),empleador);
	}
	@Test
	public void testMatchContratacionFecha() {
		Contratacion c2=agencia.getContrataciones().get(0);
		Assert.assertEquals("deberian ser los mismos datos de contratacion (FECHA)",calendar,c2.getFecha());
	}
	@Test
	public void testMatchContratacionEmpleador() {
		Contratacion c2=agencia.getContrataciones().get(0);
		Assert.assertEquals("deberian ser los mismos datos de contratacion (EMPELADOR)",c.getEmpleador(),c2.getEmpleador());
	}
	@Test
	public void testMatchContratacionEmpleado() {
		Contratacion c2=agencia.getContrataciones().get(0);
		Assert.assertEquals("deberian ser los mismos datos de contratacion (EMPELADO)",c.getEmpleado(),c2.getEmpleado());
	}
	@Test
	public void testMatchComisionEmpleador() {
		Assert.assertEquals("deberian ser la misma remuneracion del empleador",empleador.calculaComision(t),agencia.getComisionUsuario(empleador));
	}
	@Test
	public void testMatchComisionEmpleado() {
		Assert.assertEquals("deberian ser la misma remuneracion del empleado",empleadoPretenso.calculaComision(t),agencia.getComisionUsuario(empleadoPretenso));
	}

}
