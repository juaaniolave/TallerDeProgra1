package testsModeloDatos;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import modeloDatos.Cliente;
import modeloDatos.ClientePuntaje;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

class testAplicaPromoJuanOlave {
	EmpleadoPretenso ep1,ep2;
	Empleador emp1,emp2;
	HashMap<String,EmpleadoPretenso> empleados=new HashMap<>();
	HashMap<String,Empleador> empleadores=new HashMap<>();
	Cliente cliente;
	Agencia agencia=Agencia.getInstance();
	boolean promoPorListaDePostulantes;
	String nombre,password,user,tel,apellido;
	String rubro,tipoPersona;
	ArrayList <ClientePuntaje> listaPostulantes = new ArrayList<>();
	ArrayList <ClientePuntaje> listaPostulantes2 = new ArrayList<>();
	ClientePuntaje clientePuntaje;
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
		clientePuntaje= new ClientePuntaje(100.0,emp1);
		this.listaPostulantes.add(clientePuntaje);
		clientePuntaje= new ClientePuntaje(200.0,emp2);
		this.listaPostulantes.add(clientePuntaje);
		this.ep1.setListaDePostulantes(listaPostulantes);
		
		clientePuntaje= new ClientePuntaje(150.0,ep1);
		this.listaPostulantes2.add(clientePuntaje);
		this.emp2.setListaDePostulantes(listaPostulantes2);
		this.empleadores.put(emp1.getPassword(), emp1);
		this.empleadores.put(emp2.getPassword(), emp2);
		agencia.setEmpleadores(empleadores);
		this.empleados.put(ep1.getPassword(), ep1);
		this.empleados.put(ep2.getPassword(), ep2);
		agencia.setEmpleados(empleados);
		
		//crear lista empleados y empleadores y postulantes
		
	}
	
	
	
	@Test
	public void testAplicaPromo() {
		cliente= agencia.aplicaPromo(promoPorListaDePostulantes);
		Assert.assertTrue("El cliente no es el esperado",cliente.equals(ep1));
	} 
}
