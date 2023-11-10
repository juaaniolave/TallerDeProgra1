package TestModeloNegocios;

import static org.junit.Assert.*;

import java.io.IOException;
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
import persistencia.PersistenciaXML;
import util.Constantes;

public class TestCargar {
	Agencia agencia=Agencia.getInstance();
	Empleador empleador;
	EmpleadoPretenso empleadoPretenso;
	PersistenciaXML p=new PersistenciaXML();
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
		this.agencia.setLimitesRemuneracion(1000, 3000);
		agencia.setPersistencia(p);
		
		agencia.guardarAgencia("archivoagencia.xml");
		
		this.agencia.setLimitesRemuneracion(300, 2500);
		
		agencia.registroEmpleador("EJ1", "EJ1", "EJ1", "111", Constantes.JURIDICA, Constantes.SALUD);
		agencia.registroEmpleador("EJ2", "EJ2", "EJ2", "222", Constantes.JURIDICA, Constantes.SALUD);
		agencia.registroEmpleado("EJ3", "EJ3", "EJ3", "EJ3", "333", 30);
		agencia.registroEmpleado("EJ4", "EJ4", "EJ4", "EJ4", "444", 30);
		agencia.login("user1", "pass1");
		
		agencia.cargarAgencia("archivoagencia.xml");
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
		agencia.cerrarSesion();
	}

	@Test
	public void testMismoComisionesUsuarios() {
		HashMap<Cliente,Double> h1=agencia.getComisionesUsuarios();
		Assert.assertTrue("deberia esta vacia",h1.isEmpty());
	}
	@Test
	public void testMismoContrataciones() {
		ArrayList<Contratacion> al1=agencia.getContrataciones();
		Assert.assertTrue("deberia esta vacia",al1.isEmpty());
	}
	@Test
	public void testLimiteInferior() {
		Assert.assertEquals("deberian ser iguales",1000,agencia.getLimiteInferior());
	}
	@Test
	public void testLimiteSuperior() {
		Assert.assertEquals("deberian ser iguales",3000,agencia.getLimiteSuperior());
	}
	@Test
	public void testTipoUser() {
		Assert.assertEquals("deberian ser iguales",-1,agencia.getTipoUsuario());
	}
	@Test
	public void testPersistencia() {
		Assert.assertEquals("deberian ser iguales",this.p,agencia.getPersistencia());
	}
	@Test
	public void testEmpleados() {
		HashMap<String,EmpleadoPretenso> h1=agencia.getEmpleados();
		Assert.assertEquals("deberian tener 1 solo empleado", 1,h1.size());
		EmpleadoPretenso e1=h1.get("user2");
		Assert.assertEquals("deberian ser el username", e1.getUsserName(),empleadoPretenso.getUsserName());
		Assert.assertEquals("deberian ser el nombre real", e1.getRealName(),empleadoPretenso.getRealName());
		Assert.assertEquals("deberian ser el mismo puntaje", e1.getPuntaje(),empleadoPretenso.getPuntaje());
		Assert.assertEquals("deberian ser el mismo puntaje", e1.getTelefono(),empleadoPretenso.getTelefono());
		Assert.assertEquals("deberian ser la misma edad", e1.getEdad(),empleadoPretenso.getEdad());
		Assert.assertEquals("deberian ser el mismo apellido", e1.getApellido(),empleadoPretenso.getApellido());
		
	}
	@Test
	public void testEmpleadores() {
		HashMap<String,Empleador> h1=agencia.getEmpleadores();
		Assert.assertEquals("deberian tener 1 solo empleador", 1,h1.size());
		Empleador e1=h1.get("user1");
		Assert.assertEquals("deberian ser el username", e1.getUsserName(),empleador.getUsserName());
		Assert.assertEquals("deberian ser el nombre real", e1.getRealName(),empleador.getRealName());
		Assert.assertEquals("deberian ser el mismo puntaje", e1.getPuntaje(),empleador.getPuntaje());
		Assert.assertEquals("deberian ser el mismo puntaje", e1.getTelefono(),empleador.getTelefono());
	}
	@Test
	public void testIOException() {
		try {
			agencia.cargarAgencia("archivox");
			Assert.fail();
		} catch (ClassNotFoundException e) {
			Assert.fail(e.getMessage());
		} catch (IOException e) {
		}
		
	}

}
