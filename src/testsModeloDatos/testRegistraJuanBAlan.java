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

class testRegistraJuanBAlan {

	
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
	
		this.user ="Josesito";
		this.password ="5252";
		this.nombre ="Jose";
		this.tel ="2231515";
		this.apellido = "Borges";
		this.edad = 25;
		a1.getInstance().registroEmpleado(user, password, nombre, apellido, tel, edad);
		
		this.user="Gero";
		this.password="3333";
		this.nombre="Geronimo";
		this.tel="7777";
		this.rubro=Constantes.COMERCIO_LOCAL;
		this.tipoPersona=Constantes.FISICA;
		a1.getInstance().registroEmpleador(user, password, nombre, tel, tipoPersona, rubro);
		
		this.user="Turco";
		this.password="6663";
		this.nombre="David";
		this.tel="9091";
		this.rubro=Constantes.COMERCIO_INTERNACIONAL;
		this.tipoPersona=Constantes.JURIDICA;
		a1.getInstance().registroEmpleador(user, password, nombre, tel, tipoPersona, rubro);
	}

	
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testRegistra() {
	HashMap<String, Empleador> Empleadores = a1.getInstance().getEmpleadores();
	HashMap<String, EmpleadoPretenso> Empleados = a1.getInstance().getEmpleados();
	EmpleadoPretenso ep1,ep2;
	Empleador emp1,emp2;
	String user ="Pedrito";
	String password ="1234";
	String nombre="Pedro";
	String tel="22359";
	String apellido ="Basualdo";
	int edad=20;
	ep1=new EmpleadoPretenso(user,password,nombre,tel,apellido,edad);
	user ="Josesito";
	password ="5252";
	nombre ="Jose";
	tel ="2231515";
	apellido = "Borges";
	edad = 25;
	ep2=new EmpleadoPretenso(user,password,nombre,tel,apellido,edad);
	
	user="Gero";
	password="3333";
	nombre="Geronimo";
	tel="7777";
	String rubro=Constantes.COMERCIO_LOCAL;
	String tipoPersona=Constantes.FISICA;
	emp1=new Empleador(user,password,nombre,tel,rubro,tipoPersona);
	
	user="Turco";
	password="6663";
	nombre="David";
	tel="9091";
	rubro=Constantes.COMERCIO_INTERNACIONAL;
	tipoPersona=Constantes.JURIDICA;
	emp2=new Empleador(user,password,nombre,tel,rubro,tipoPersona);
	EmpleadoPretenso empleado = Empleados.get("Pedrito");
	Assert.assertFalse("El empleado no es el esperado",empleado.equals(ep1));
	empleado = Empleados.get("Josesito");
	Assert.assertFalse("El empleado no es el esperado",empleado.equals(ep2));
	Empleador empleador = Empleadores.get("Gero");
	Assert.assertFalse("El empleado no es el esperado",empleador.equals(emp1));
	empleador = Empleadores.get("Turco");
	Assert.assertFalse("El empleado no es el esperado",empleador.equals(emp2));
	}

}
