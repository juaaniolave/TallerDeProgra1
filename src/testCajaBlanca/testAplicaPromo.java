package testCajaBlanca;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import modeloDatos.Cliente;
import modeloDatos.ClientePuntaje;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

public class testAplicaPromo {
	
	Agencia agencia=Agencia.getInstance();
	UtilPromo utilPromo;
	EmpleadoPretenso empleadoPretenso1;
	EmpleadoPretenso empleadoPretenso2;
	Empleador empleador1;
	Empleador empleador2;
	HashMap<String, EmpleadoPretenso> empleados;
	HashMap<String, Empleador> empleadores;
	ArrayList<ClientePuntaje> listaPuntaje;
	ClientePuntaje clientePuntaje;
	boolean promoPorListaDePostulantes;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.empleador1 = (Empleador) agencia.registroEmpleador("user1", "pass1", "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
		this.empleadoPretenso1 = (EmpleadoPretenso) agencia.registroEmpleado("user2", "pass2", "nombR2", "ap1", "456", 30);		
		this.empleador2 = (Empleador) agencia.registroEmpleador("user12", "pas2s1", "nomb2R1", "2123", Constantes.JURIDICA, Constantes.SALUD);
		this.empleadoPretenso2 = (EmpleadoPretenso) agencia.registroEmpleado("us44er2", "pass25", "nomb3R2", "ap41", "4526", 30);		
		this.empleados = new HashMap<String, EmpleadoPretenso>();
		this.empleadores = new HashMap<String, Empleador>();
		this.listaPuntaje = new ArrayList<ClientePuntaje>();
		this.clientePuntaje = new ClientePuntaje();
		this.listaPuntaje.add(this.clientePuntaje);

		
		
		this.utilPromo = new UtilPromo();
	}

	@After
	public void tearDown() throws Exception {
		agencia.getEmpleados().clear();
		agencia.getEmpleadores().clear();
		agencia.cerrarSesion();
	}

	@Test
	public void testCamino1() {
		

		
		this.empleadoPretenso1.setPuntaje(200); 
		this.empleador1.setPuntaje(0); 
		this.empleador2.setPuntaje(20); 
		

		this.empleadoPretenso1.setListaDePostulantes(listaPuntaje);		

		this.empleador1.setListaDePostulantes(listaPuntaje);
		
		this.empleador2.setListaDePostulantes(listaPuntaje);

		this.empleados.put("EmpleadoPretenso1", empleadoPretenso1);

		this.empleadores.put("Empleador1", empleador1);
		this.empleadores.put("Empleador2", empleador2);
		
		this.promoPorListaDePostulantes = true;	
		Assert.assertEquals("Debió devolver el empleador 2", this.empleador2,utilPromo.aplicaPromo(this.promoPorListaDePostulantes, this.empleados, this.empleadores));
		
	}
	
	@Test
	public void testCamino2() {
		this.empleadoPretenso1.setPuntaje(200); 
		this.empleador1.setPuntaje(0); 
		this.empleador2.setPuntaje(20); 
	
		this.empleados.put("EmpleadoPretenso1", empleadoPretenso1);
		this.empleadores.put("Empleador1", empleador1);
		this.empleadores.put("Empleador2", empleador2);
		
		this.promoPorListaDePostulantes = false;
		Assert.assertEquals("Debió devolver el empleador 2", this.empleador2,utilPromo.aplicaPromo(this.promoPorListaDePostulantes, this.empleados, this.empleadores));
		
	}
	
	@Test
	public void testCamino3() {
		this.empleadoPretenso1.setPuntaje(200); 
		this.empleadoPretenso1.setListaDePostulantes(listaPuntaje);

		
		this.empleados.put("EmpleadoPretenso1", empleadoPretenso1);
		
		this.promoPorListaDePostulantes=true;
		Assert.assertEquals("Debió devolver el empleadoPretenso1", this.empleadoPretenso1,utilPromo.aplicaPromo(this.promoPorListaDePostulantes, this.empleados, this.empleadores));
		
	}
	
	
	@Test
	public void testCamino4() {
		this.empleador1.setPuntaje(2000); 

		this.empleadoPretenso1.setPuntaje(200); 
		this.empleadoPretenso1.setListaDePostulantes(listaPuntaje);
		
		this.empleados.put("EmpleadoPretenso1", empleadoPretenso1);

		this.empleadores.put("Empleador1", empleador1);
		this.empleadores.put("Empleador2", empleador2);
		
		this.promoPorListaDePostulantes=true;	
		Assert.assertEquals("Debió devolver el empleadoPretenso1", this.empleadoPretenso1,utilPromo.aplicaPromo(this.promoPorListaDePostulantes, this.empleados, this.empleadores));
		
	}
	
	@Test
	public void testCamino5() {
		this.empleador1.setPuntaje(0); 
		this.empleador1.setListaDePostulantes(listaPuntaje);
		this.empleador2.setPuntaje(20); 
		this.empleador2.setListaDePostulantes(listaPuntaje);

		
		this.empleadores.put("Empleador1", empleador1);
		this.empleadores.put("Empleador2", empleador2);
		
		this.promoPorListaDePostulantes=true;	
		Assert.assertEquals("Debió devolver el empleador2", this.empleador2,utilPromo.aplicaPromo(this.promoPorListaDePostulantes, this.empleados, this.empleadores));
		
	}
	
	@Test
	public void testCamino6() {
		this.empleador1.setPuntaje(0); 
		this.empleador1.setListaDePostulantes(listaPuntaje);
		this.empleador2.setPuntaje(20); 
		this.empleador2.setListaDePostulantes(listaPuntaje);
		
		this.empleadores.put("Empleador1", empleador1);
		this.empleadores.put("Empleador2", empleador2);

		this.empleadoPretenso1.setPuntaje(200); 
		
		this.empleados.put("EmpleadoPretenso1", empleadoPretenso1);
		

		
		this.promoPorListaDePostulantes=true;	
		Assert.assertEquals("Debió devolver el empleador2", this.empleador2,utilPromo.aplicaPromo(this.promoPorListaDePostulantes, this.empleados, this.empleadores));
		
	}
	@Test
	public void testCamino7() {
		this.empleador1.setPuntaje(0); 
		this.empleador1.setListaDePostulantes(listaPuntaje);
		this.empleadores.put("Empleador1", empleador1);

		this.empleadoPretenso1.setPuntaje(200); 
		this.empleadoPretenso1.setListaDePostulantes(listaPuntaje);
		this.empleadoPretenso2.setPuntaje(20); 
		this.empleadoPretenso2.setListaDePostulantes(listaPuntaje);
		
		this.empleados.put("EmpleadoPretenso1", empleadoPretenso1);
		this.empleados.put("EmpleadoPretenso2", empleadoPretenso2);
	
		this.promoPorListaDePostulantes=true;	
		Assert.assertEquals("Debió devolver el empleadoPretenso1", this.empleadoPretenso1,utilPromo.aplicaPromo(this.promoPorListaDePostulantes, this.empleados, this.empleadores));
		
	}

	@Test
	public void testCamino8() {

		this.promoPorListaDePostulantes=true;	
		Assert.assertEquals("Debió devolver el null", null ,utilPromo.aplicaPromo(this.promoPorListaDePostulantes, this.empleados, this.empleadores));
		
	}
	
	@Test
	public void testCamino9() {
		this.empleador1.setPuntaje(22); 
		this.empleador1.setListaDePostulantes(listaPuntaje);
		this.empleador2.setPuntaje(0); 
		this.empleador2.setListaDePostulantes(listaPuntaje);
		
			
		this.empleadores.put("Empleador1", empleador1);
		this.empleadores.put("Empleador1", empleador2);

		this.empleadoPretenso1.setPuntaje(200); 
		this.empleadoPretenso1.setListaDePostulantes(listaPuntaje);
		
		this.empleados.put("EmpleadoPretenso1", empleadoPretenso1);
		this.empleados.put("EmpleadoPretenso2", empleadoPretenso2);
	
		this.promoPorListaDePostulantes=true;	
		Assert.assertEquals("Debió devolver el empleadoPretenso1", this.empleadoPretenso1,utilPromo.aplicaPromo(this.promoPorListaDePostulantes, this.empleados, this.empleadores));
		
	}

}















