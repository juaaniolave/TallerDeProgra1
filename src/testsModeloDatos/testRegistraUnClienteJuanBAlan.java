package testsModeloDatos;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

class testRegistraUnClienteJuanBAlan {

	
	String nombre,password,user,tel,apellido;
	int edad;
	EmpleadoPretenso ep1;
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
	}

	@Test
	void testRegistraEmpleado() {
	HashMap<String, EmpleadoPretenso> empleados = a1.getInstance().getEmpleados();
	String user ="Pedrito";
	String password ="1234";
	String nombre="Pedro";
	String tel="22359";
	String apellido ="Basualdo";
	int edad=20;
	Assert.assertEquals("No devuelve usuario esperado", user,empleados.get(user).getUsserName());
	Assert.assertEquals("No devuelve password esperado", password,empleados.get(user).getPassword());
	Assert.assertEquals("No devuelve nombreReal esperado", nombre,empleados.get(user).getRealName());
	Assert.assertEquals("No devuelve telefono esperado", tel,empleados.get(user).getTelefono());
	Assert.assertEquals("No devuelve apellido esperado", apellido,empleados.get(user).getApellido());
	Assert.assertEquals("No devuelve edad esperado", edad,empleados.get(user).getEdad());
	}
	
	@Test
	void testRegistraEmpleadores() throws Exception{
		HashMap<String, Empleador> empleadores = a1.getInstance().getEmpleadores();
		String user="Gero";
		String password="3333";
		String nombre="Geronimo";
		String tel="7777";
		String rubro=Constantes.COMERCIO_LOCAL;
		String tipoPersona=Constantes.FISICA;
		Assert.assertEquals("No devuelve usuario esperado", user,empleadores.get(user).getUsserName());
		Assert.assertEquals("No devuelve password esperado", password,empleadores.get(user).getPassword());
		Assert.assertEquals("No devuelve nombreReal esperado", nombre,empleadores.get(user).getRealName());
		Assert.assertEquals("No devuelve telefono esperado", tel,empleadores.get(user).getTelefono());
		Assert.assertEquals("No devuelve rubro esperado", rubro,empleadores.get(user).getRubro());
		Assert.assertEquals("No devuelve tipoPersona esperado", tipoPersona,empleadores.get(user).getTipoPersona());
	}

}
