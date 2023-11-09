package testsModeloDatos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import modeloDatos.Empleador;
import modeloDatos.Ticket;
import util.Constantes;

public class testEmpleador {
	Empleador empleador;
	String username;
	String password;
	String nombreReal;
	String telefono;
	String rubro;
	String tipoDePersona;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.username = "AlanPerez223";
		this.password = "password123";
		this.nombreReal = "Alan Perez";
		this.telefono = "22342323";
		this.rubro = Constantes.SALUD;
		this.tipoDePersona = Constantes.JURIDICA;
		
		this.empleador = new Empleador(username,password,nombreReal,telefono,rubro,tipoDePersona);
		 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructorUsername() {
		Assert.assertEquals("username no es igual", this.username, this.empleador.getUsserName());
	
	}
	
	@Test
	public void testConstructorPassword() {
		Assert.assertEquals("password no es igual",this.password , this.empleador.getPassword());
	}
	
	@Test
	public void testConstructorRealName() {
		Assert.assertEquals("nombre real no es igual", this.nombreReal , this.empleador.getRealName());

	}
	
	@Test
	public void testConstructorTelefono() {
		Assert.assertEquals("telefono no es igual",this.telefono , this.empleador.getTelefono());
	}
	
	@Test
	public void testConstructorRubro() {
		Assert.assertEquals("rubro no es igual", this.rubro, this.empleador.getRubro());
	}
	
	@Test
	public void testConstructorTipoDePersona() {
		Assert.assertEquals("tipo de persona no es igual", this.tipoDePersona, this.empleador.getTipoPersona());	
	}
	
	@Test
	public void testNombreReal() {
		this.nombreReal = "Alan";
		this.empleador.setRealName(this.nombreReal);
		Assert.assertEquals("set y get no son igual",this.nombreReal,this.empleador.getRealName());
	}
	@Test
	public void testTelefono() {
		this.telefono = "11111";
		this.empleador.setTelefono(this.telefono);
		Assert.assertEquals("set y get no son igual",this.telefono,this.empleador.getTelefono());
	}
	
	@Test
	public void testCalculaComision() {		
		//Para salud 60%, comercio local, 70%, comercion internacional 80%
		Ticket ticket = new Ticket(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS);
		double comisionEsperada = 1500*0.6;
		Assert.assertEquals("Las comisiones no son iguales", comisionEsperada, this.empleador.calculaComision(ticket), 0.0);
	}
	

}
