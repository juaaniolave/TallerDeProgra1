package TestModeloNegocios;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.ContraException;
import excepciones.NombreUsuarioException;
import junit.framework.Assert;
import modeloDatos.Usuario;
import modeloNegocio.Agencia;
import util.Constantes;

public class TestLoginLogout {
	String nombre,password,user,tel,apellido;
	int edad;
	Agencia a1;
	private String rubro;
	private String tipoPersona;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.user ="Pedrito";
		this.password ="1234";
		this.nombre="Pedro";
		this.tel="22359";
		this.apellido ="Basualdo";
		this.edad=20;
		a1.getInstance().registroEmpleado(user, password, nombre, apellido, tel, edad);
	
		
		this.user ="";
		this.password ="";
		this.nombre="";
		this.tel="22359";
		this.apellido ="";
		this.edad=20;
		a1.getInstance().registroEmpleado(user, password, nombre, apellido, tel, edad);
		this.user="Gero";
		this.password="3333";
		this.nombre="Geronimo";
		this.tel="7777";
		this.rubro=Constantes.COMERCIO_LOCAL;
		this.tipoPersona=Constantes.FISICA;
		a1.getInstance().registroEmpleador(user, password, nombre, tel, tipoPersona, rubro);
	}

	@After
	public void tearDown() throws Exception {
		a1.getInstance().getEmpleadores().clear();
		a1.getInstance().getEmpleados().clear();
	}

	@Test
	public void testLoginEmpleado() {
		String user="Pedrito";
		String password="1234";
		try {
			Usuario usuario=a1.getInstance().login(user, password);
			Assert.assertEquals("El tipo de usuario logeado no es el esperado",0,a1.getInstance().getTipoUsuario());
		} catch (ContraException e) {
			Assert.fail(e.getMessage()+"no debio haber tirado esta excepcion");
		} catch (NombreUsuarioException e) {
			Assert.fail(e.getMessage()+"no debio haber tirado esta excepcion");
		}
		
	}
	
	@Test
	public void testLoginEmpleadoCadenaVacia() {
		String user="";
		String password="";
		try {
			Usuario usuario=a1.getInstance().login(user, password);
			Assert.assertEquals("El tipo de usuario logeado no es el esperado",0,a1.getInstance().getTipoUsuario());
		} catch (ContraException e) {
			Assert.fail(e.getMessage()+"no debio haber tirado esta excepcion");
		} catch (NombreUsuarioException e) {
			Assert.fail(e.getMessage()+"no debio haber tirado esta excepcion");
		}
		
	}
	@Test
	public void testLoginEmpleador()  {
		String user="Gero";
		String password="3333";
		try {
			Usuario usuario=a1.getInstance().login(user, password);
			Assert.assertEquals("El tipo de usuario logeado no es el esperado",1,a1.getInstance().getTipoUsuario());
		} catch (ContraException e) {
			Assert.fail(e.getMessage()+"no debio haber tirado esta excepcion");
		} catch (NombreUsuarioException e) {
			Assert.fail(e.getMessage()+"no debio haber tirado esta excepcion");
		}
		
	}
	@Test
	public void testLoginAdmin()  {
		String user="admin";
		String password="admin";
		try {
			Usuario usuario=a1.getInstance().login(user, password);
			Assert.assertEquals("El tipo de usuario logeado no es el esperado",2,a1.getInstance().getTipoUsuario());
		} catch (ContraException e) {
			Assert.fail(e.getMessage()+"no debio haber tirado esta excepcion");
		} catch (NombreUsuarioException e) {
			Assert.fail(e.getMessage()+"no debio haber tirado esta excepcion");
		}
	}
	@Test
	public void testExcepciones() {
		try {
			a1.getInstance().login("Gero", "pass");
		} catch (ContraException e) {
		} catch (NombreUsuarioException e) {
			Assert.fail("no debio haber tirado esta excepcion");
		}
		try {
			a1.getInstance().login("Juan", "12321");
		} catch (ContraException e) {
			Assert.fail("no debio haber tirado esta excepcion");
		} catch (NombreUsuarioException e) {

		}
	}
	@Test
	public void testCierraSesionEmpleado() {
		String user="Pedrito";
		String password="1234";
		try {
			Usuario usuario=a1.getInstance().login(user, password);
		} catch (ContraException e) {
			Assert.fail("no debio haber tirado esta excepcion");
		} catch (NombreUsuarioException e) {
			Assert.fail("no debio haber tirado esta excepcion");
		}
		a1.getInstance().cerrarSesion();
		Assert.assertEquals("El tipo deberia ser -1 ya que se cerro sesion",-1,a1.getInstance().getTipoUsuario());
	}
	@Test
	public void testCierraSesionEmpleador(){
		String user="Gero";
		String password="3333";
		try {
			Usuario usuario=a1.getInstance().login(user, password);
		} catch (ContraException e) {
			Assert.fail("no debio haber tirado esta excepcion");
		} catch (NombreUsuarioException e) {
			Assert.fail("no debio haber tirado esta excepcion");
		}
		a1.getInstance().cerrarSesion();
		Assert.assertEquals("El tipo deberia ser -1 ya que se cerro sesion",-1,a1.getInstance().getTipoUsuario());
	}

}
