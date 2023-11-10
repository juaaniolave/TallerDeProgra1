package testsModeloDatos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Ticket;
import util.Constantes;

public class TestEmpleadoPretenso {
	EmpleadoPretenso empleado;
	String username;
	String password;
	String nombreReal;
	String telefono;
	String apellido;
	int edad;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.username = "bruno";//no se puede testear por que no hay get
		this.password = "asdasd";
		this.nombreReal = "bruno t";
		this.telefono = "123456789";
		this.apellido = "t";
		this.edad = 25;
		
		this.empleado = new EmpleadoPretenso(username,password,nombreReal,telefono,apellido,edad);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructorPassword() {
		Assert.assertEquals("password no es igual",this.password , this.empleado.getPassword());
	}
	
	@Test
	public void testConstructorRealName() {
		Assert.assertEquals("nombre real no es igual", this.nombreReal , this.empleado.getRealName());

	}
	
	@Test
	public void testConstructorTelefono() {
		Assert.assertEquals("telefono no es igual",this.telefono , this.empleado.getTelefono());
	}
	@Test
	public void testConstructorApellido() {
		Assert.assertEquals("apellido no es igual",this.apellido , this.empleado.getApellido());
	}
	@Test
	public void testConstructorEdad() {
		Assert.assertEquals("edad no es igual",this.edad , this.empleado.getEdad());
	}
	@Test
	public void testSetEdad() {
		empleado.setEdad(20);
		Assert.assertEquals("edad no es igual",20 , this.empleado.getEdad());
	}
	@Test
	public void testCalculaComision1() {
		Ticket t=new Ticket(Constantes.HOME_OFFICE,1000,Constantes.JORNADA_COMPLETA,Constantes.JUNIOR,Constantes.EXP_NADA,Constantes.SECUNDARIOS);
		Assert.assertEquals("no calcula bien la comision",800.0,this.empleado.calculaComision(t));
	}
	@Test
	public void testCalculaComision2() {
		Ticket t=new Ticket(Constantes.HOME_OFFICE,1000,Constantes.JORNADA_COMPLETA,Constantes.SENIOR,Constantes.EXP_NADA,Constantes.SECUNDARIOS);
		Assert.assertEquals("no calcula bien la comision",900.0,this.empleado.calculaComision(t));
	}

}
