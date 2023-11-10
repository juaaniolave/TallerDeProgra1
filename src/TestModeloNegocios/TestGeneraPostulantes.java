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
	Agencia agencia = Agencia.getInstance();
	Empleador empleador;
	EmpleadoPretenso empleadoPretenso;
	Empleador empleador2;
	EmpleadoPretenso empleadoPretenso2;
	Contratacion contratacion;
	HashMap<Cliente, Double> comisionesCliente;
	ClientePuntaje c;
	ClientePuntaje c2;
	ClientePuntaje c3;
	ClientePuntaje c4;
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
		this.empleador2 = (Empleador) agencia.registroEmpleador("user3", "pass3", "nombR3", "789", Constantes.FISICA, Constantes.COMERCIO_LOCAL);
		this.empleadoPretenso2 = (EmpleadoPretenso) agencia.registroEmpleado("user4", "pass4", "nombR4", "ap2", "000", 35);
		this.agencia.crearTicketEmpleado(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleadoPretenso);
		this.agencia.crearTicketEmpleador(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleador);
		this.agencia.crearTicketEmpleado(Constantes.HOME_OFFICE, 2000, Constantes.JORNADA_MEDIA, Constantes.MANAGMENT, Constantes.EXP_NADA, Constantes.SECUNDARIOS,empleadoPretenso2);
		this.agencia.crearTicketEmpleador(Constantes.INDISTINTO, 4000, Constantes.JORNADA_EXTENDIDA, Constantes.SENIOR, Constantes.EXP_MUCHA, Constantes.PRIMARIOS,empleador2);
		this.agencia.setLimitesRemuneracion(1000, 3000);
		c=new ClientePuntaje(6.0,empleadoPretenso);
		c2=new ClientePuntaje(-3.5,empleadoPretenso2);
		c3=new ClientePuntaje(6.0,empleador);
		c4=new ClientePuntaje(1.0,empleador2);
		
		agencia.generaPostulantes();
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
	public void testLista0EmpleadorPuntaje() {
		Assert.assertEquals("el puntaje debe ser el mismo 1",empleador.getListaDePostulantes().get(0).getPuntaje(),c.getPuntaje(),0.0);
	}
	@Test
	public void testLista0EmpleadorCliente() {
		Assert.assertEquals("el cliente debe ser el mismo 1",empleador.getListaDePostulantes().get(0).getCliente(),c.getCliente());
	}
	@Test
	public void testLista1EmpleadorPuntaje() {
		Assert.assertEquals("el puntaje debe ser el mismo 2",empleador.getListaDePostulantes().get(1).getPuntaje(),c2.getPuntaje(),0.0);
	}
	@Test
	public void testLista1EmpleadorCliente() {
		Assert.assertEquals("el cliente debe ser el mismo 2",empleador.getListaDePostulantes().get(1).getCliente(),c2.getCliente());
	}
	
	@Test
	public void testLista0EmpleadoPuntaje() {
		Assert.assertEquals("el puntaje debe ser el mismo 3",empleadoPretenso.getListaDePostulantes().get(0).getPuntaje(),c3.getPuntaje(),0.0);
	}
	@Test
	public void testLista0EmpleadoCliente() {
		Assert.assertEquals("el cliente debe ser el mismo 3",empleadoPretenso.getListaDePostulantes().get(0).getCliente(),c3.getCliente());
	}
	@Test
	public void testLista1EmpleadoPuntaje() {
		Assert.assertEquals("el puntaje debe ser el mismo 4",empleadoPretenso.getListaDePostulantes().get(1).getPuntaje(),c4.getPuntaje(),0.0);
	}
	@Test
	public void testLista1EmpleadoCliente() {
		Assert.assertEquals("el cliente debe ser el mismo 4",empleadoPretenso.getListaDePostulantes().get(1).getCliente(),c4.getCliente());
	}
	
}
