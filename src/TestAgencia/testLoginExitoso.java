package TestAgencia;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.ContraException;
import excepciones.NombreUsuarioException;
import junit.framework.Assert;
import modeloDatos.Admin;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloDatos.Usuario;
import modeloNegocio.Agencia;
import util.Constantes;

class testLoginExitoso {
	String nombre,password,user,tel,apellido;
	int edad;
	Agencia a1;
	private String rubro;
	private String tipoPersona;

	@BeforeEach
	void setUp() throws Exception {
		this.user ="Pedrito";
		this.password ="1234";
		this.nombre="Pedro";
		this.tel="22359";
		this.apellido ="Basualdo";
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

	@AfterEach
	void tearDown() throws Exception {
		HashMap<String, Empleador> empleadores = a1.getInstance().getEmpleadores();
	    empleadores.clear();
		a1.getInstance().setEmpleadores(empleadores);
		
		HashMap<String, EmpleadoPretenso> empleados = a1.getInstance().getEmpleados();
	    empleados.clear();
	    a1.getInstance().setEmpleados(empleados);
	}

	@Test
	void testLoginEmpleado() throws ContraException, NombreUsuarioException {
		String user="Pedrito";
		String password="1234";
		Usuario usuario=a1.getInstance().login(user, password);
		Assert.assertEquals("El tipo de usuario logeado no es el esperado",0,a1.getInstance().getTipoUsuario());
	}
	@Test
	void testLoginEmpleador() throws ContraException, NombreUsuarioException {
		String user="Gero";
		String password="3333";
		Usuario usuario=a1.getInstance().login(user, password);
		Assert.assertEquals("El tipo de usuario logeado no es el esperado",1,a1.getInstance().getTipoUsuario());
	}
	@Test
	void testLoginAdmin() throws ContraException, NombreUsuarioException {
		String user="admin";
		String password="admin";
		Usuario usuario=a1.getInstance().login(user, password);
		Assert.assertEquals("El tipo de usuario logeado no es el esperado",2,a1.getInstance().getTipoUsuario());
	}
}
