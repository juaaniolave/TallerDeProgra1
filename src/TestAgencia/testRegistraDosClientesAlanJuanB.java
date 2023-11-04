package TestAgencia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

class testRegistraDosClientesAlanJuanB {
	
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
	    
	    this.user ="G1";
		this.password ="12345";
		this.nombre="Guille";
		this.tel="223";
		this.apellido ="Gomez";
		this.edad=40;
		
		a1.getInstance().registroEmpleado(user, password, nombre, apellido, tel, edad);
		   
		
		this.user="Gero";
		this.password="3333";
		this.nombre="Geronimo";
		this.tel="7777";
		this.rubro=Constantes.COMERCIO_LOCAL;
		this.tipoPersona=Constantes.FISICA;
		a1.getInstance().registroEmpleador(user, password, nombre, tel, tipoPersona, rubro);
		
		this.user="B1";
		this.password="444";
		this.nombre="Bruno";
		this.tel="999";
		this.rubro=Constantes.COMERCIO_LOCAL;
		this.tipoPersona=Constantes.JURIDICA;
		a1.getInstance().registroEmpleador(user, password, nombre, tel, tipoPersona, rubro);
	
	}

	
	@Test
	void testChequeaPrimerEmpleado() {
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
	void testChequeaSegundoEmpleado() {
	HashMap<String, EmpleadoPretenso> empleados = a1.getInstance().getEmpleados();
	
	String user ="G1";
	String password ="12345";
	String nombre="Guille";
	String tel="223";
	String apellido ="Gomez";
	int edad=40;
	Assert.assertEquals("No devuelve usuario esperado", user,empleados.get(user).getUsserName());
	Assert.assertEquals("No devuelve password esperado", password,empleados.get(user).getPassword());
	Assert.assertEquals("No devuelve nombreReal esperado", nombre,empleados.get(user).getRealName());
	Assert.assertEquals("No devuelve telefono esperado", tel,empleados.get(user).getTelefono());
	Assert.assertEquals("No devuelve apellido esperado", apellido,empleados.get(user).getApellido());
	Assert.assertEquals("No devuelve edad esperado", edad,empleados.get(user).getEdad());
	}
	
	@Test
	void testChequeaPrimerEmpleador() throws Exception{
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

	@Test
	void testChequeaSegundoEmpleador() throws Exception{
		HashMap<String, Empleador> empleadores = a1.getInstance().getEmpleadores();
		
		String user="B1";
		String password="444";
		String nombre="Bruno";
		String tel="999";
		String rubro=Constantes.COMERCIO_LOCAL;
		String tipoPersona=Constantes.JURIDICA;
		Assert.assertEquals("No devuelve usuario esperado", user,empleadores.get(user).getUsserName());
		Assert.assertEquals("No devuelve password esperado", password,empleadores.get(user).getPassword());
		Assert.assertEquals("No devuelve nombreReal esperado", nombre,empleadores.get(user).getRealName());
		Assert.assertEquals("No devuelve telefono esperado", tel,empleadores.get(user).getTelefono());
		Assert.assertEquals("No devuelve rubro esperado", rubro,empleadores.get(user).getRubro());
		Assert.assertEquals("No devuelve tipoPersona esperado", tipoPersona,empleadores.get(user).getTipoPersona());
	}

	
	@AfterEach
	void tearDown() {
		
		HashMap<String, Empleador> empleadores = a1.getInstance().getEmpleadores();
	    empleadores.clear();
		a1.getInstance().setEmpleadores(empleadores);
		
		HashMap<String, EmpleadoPretenso> empleados = a1.getInstance().getEmpleados();
	    empleados.clear();
	    a1.getInstance().setEmpleados(empleados);
	}
	
}
