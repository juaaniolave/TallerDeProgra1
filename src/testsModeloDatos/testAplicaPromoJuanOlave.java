package testsModeloDatos;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import modeloDatos.Cliente;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

class testAplicaPromoJuanOlave {
	EmpleadoPretenso ep1,ep2,ep3;
	Empleador emp1,emp2,emp3;
	HashMap<String,EmpleadoPretenso> empleados=new HashMap<>();
	HashMap<String,Empleador> empleadores=new HashMap<>();
	Cliente cliente;
	Agencia agencia=Agencia.getInstance();
	boolean promoPorListaDePostulantes;
	String nombre,password,user,tel,apellido;
	String rubro,tipoPersona;
	int edad;
	@BeforeEach
	 void setUp() throws Exception {
		promoPorListaDePostulantes=true;
		this.user="Pedrito";
		this.password="1234";
		this.nombre="Pedro";
		this.tel="22359";
		this.apellido="Basualdo";
		this.edad=20;
		this.ep1=new EmpleadoPretenso(user,password,nombre,tel,apellido,edad);
		this.user="Josesito";
		this.password="5252";
		this.nombre="Jose";
		this.tel="2231515";
		this.apellido="Borges";
		this.edad=25;
		this.ep2=new EmpleadoPretenso(user,password,nombre,tel,apellido,edad);
		this.user="Ramirito";
		this.password="1010";
		this.nombre="Ramiro";
		this.tel="4412";
		this.apellido="Mattioli";
		this.edad=30;
		this.ep3=new EmpleadoPretenso(user,password,nombre,tel,apellido,edad);
		this.user="Gero";
		this.password="3333";
		this.nombre="Geronimo";
		this.tel="7777";
		this.rubro=Constantes.COMERCIO_LOCAL;
		this.tipoPersona=Constantes.FISICA;
		this.emp1=new Empleador(user,password,nombre,tel,rubro,tipoPersona);
		this.user="Turco";
		this.password="6663";
		this.nombre="David";
		this.tel="9091";
		this.rubro=Constantes.COMERCIO_INTERNACIONAL;
		this.tipoPersona=Constantes.JURIDICA;
		this.emp2=new Empleador(user,password,nombre,tel,rubro,tipoPersona);
		this.user="Mati";
		this.password="2091";
		this.nombre="Matias";
		this.tel="9732";
		this.rubro=Constantes.SALUD;
		this.tipoPersona=Constantes.FISICA;
		this.emp3=new Empleador(user,password,nombre,tel,rubro,tipoPersona);
		ep1.setCandidato(emp1);
		ep1.setCandidato(emp2);
		ep1.setCandidato(emp3);
		emp1.setCandidato(ep1);
		emp2.setCandidato(ep1);
		ep2.setCandidato(emp1);
		ep2.setCandidato(emp3);
		emp1.setCandidato(ep2);
		emp3.setCandidato(ep2);
		emp3.setCandidato(ep1);
		this.empleadores.put(emp1.getPassword(), emp1);
		this.empleadores.put(emp2.getPassword(), emp2);
		this.empleadores.put(emp3.getPassword(), emp3);
		agencia.setEmpleadores(empleadores);
		this.empleados.put(ep1.getPassword(), ep1);
		this.empleados.put(ep2.getPassword(), ep2);
		this.empleados.put(ep3.getPassword(), ep3);
		agencia.setEmpleados(empleados);
		
		//crear lista empleados y empleadores y postulantes
		
	}
	
	
	
	@Test
	public void testAplicaPromo() {
		cliente= agencia.aplicaPromo(promoPorListaDePostulantes);
		Assert.assertEquals("El cliente no es el esperado",cliente.equals(ep1),true);
	} 
}
