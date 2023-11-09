package TestAgencia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;
import modeloDatos.Cliente;
import modeloDatos.ClientePuntaje;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

public class testAplicaPromoEmpleadorTestTruea {
	Agencia agencia=Agencia.getInstance();
	Empleador empleador;
	EmpleadoPretenso empleadoPretenso,empleadoPretenso2;
	ClientePuntaje clientePuntaje;
	boolean promoPorLista;
	ArrayList<ClientePuntaje> listaPuntaje=new ArrayList<ClientePuntaje>();
	ArrayList<ClientePuntaje> listaPuntaje2=new ArrayList<ClientePuntaje>();
	@Before
	public void setUp() throws Exception {
		this.empleador = (Empleador) agencia.registroEmpleador("user1", "pass1", "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
		this.empleadoPretenso = (EmpleadoPretenso) agencia.registroEmpleado("user2", "pass2", "nombR2", "ap1", "456", 30);
		this.empleadoPretenso2 = (EmpleadoPretenso) agencia.registroEmpleado("user4", "pass4", "nombR4", "ap2", "000", 35);
		clientePuntaje=new ClientePuntaje(100,empleadoPretenso);
		listaPuntaje.add(clientePuntaje);
		clientePuntaje=new ClientePuntaje(100,empleadoPretenso2);
		listaPuntaje.add(clientePuntaje);
		this.empleador.setListaDePostulantes(listaPuntaje);	
		clientePuntaje=new ClientePuntaje(100,empleador);
		this.listaPuntaje2.add(clientePuntaje);
		this.empleadoPretenso.setListaDePostulantes(listaPuntaje2);
		this.promoPorLista=true;
	}

	@After
	public void tearDown() throws Exception {
		HashMap<String,Empleador> vacio1=new HashMap<String,Empleador>();
		HashMap<String,EmpleadoPretenso> vacio2=new HashMap<String,EmpleadoPretenso>();
		agencia.setEmpleados(vacio2);
		agencia.setEmpleadores(vacio1);
	}
	@Test
	public void testAplicaPromoEmpleador() {
		Cliente cliente=agencia.aplicaPromo(promoPorLista);
		Assert.assertEquals("Deberia ser el cliente user1",empleador.getUsserName(),cliente.getUsserName());
	}
}
