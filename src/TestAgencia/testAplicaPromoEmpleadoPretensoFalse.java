package TestAgencia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import modeloDatos.Cliente;
import modeloDatos.ClientePuntaje;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

class testAplicaPromoEmpleadoPretensoFalse {
	Agencia agencia=Agencia.getInstance();
	Empleador empleador;
	EmpleadoPretenso empleadoPretenso,empleadoPretenso2;
	ClientePuntaje clientePuntaje;
	boolean promoPorLista;
	ArrayList<ClientePuntaje> listaPuntaje=new ArrayList<ClientePuntaje>();
	ArrayList<ClientePuntaje> listaPuntaje2=new ArrayList<ClientePuntaje>();
	@BeforeEach
	void setUp() throws Exception {
		this.empleador = (Empleador) agencia.registroEmpleador("user1", "pass1", "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
		this.empleadoPretenso = (EmpleadoPretenso) agencia.registroEmpleado("user2", "pass2", "nombR2", "ap1", "456", 30);
		this.empleadoPretenso2 = (EmpleadoPretenso) agencia.registroEmpleado("user4", "pass4", "nombR4", "ap2", "000", 35);
		clientePuntaje=new ClientePuntaje(100,empleadoPretenso);
		listaPuntaje.add(clientePuntaje);
		clientePuntaje=new ClientePuntaje(200,empleadoPretenso2);
		listaPuntaje.add(clientePuntaje);
		this.empleador.setListaDePostulantes(listaPuntaje);	
		clientePuntaje=new ClientePuntaje(50,empleador);
		this.listaPuntaje2.add(clientePuntaje);
		this.empleadoPretenso.setListaDePostulantes(listaPuntaje2);
		this.promoPorLista=false;
	}

	@AfterEach
	void tearDown() throws Exception {
		HashMap<String,Empleador> vacio1=new HashMap<String,Empleador>();
		HashMap<String,EmpleadoPretenso> vacio2=new HashMap<String,EmpleadoPretenso>();
		agencia.setEmpleados(vacio2);
		agencia.setEmpleadores(vacio1);
	}

	@Test
	void testAplicaPromoEmpleadoPretenso() {
		Cliente cliente=agencia.aplicaPromo(promoPorLista);
		Assert.assertEquals("Deberia ser el cliente user4",empleadoPretenso2.getUsserName(),cliente.getUsserName());
	}

}
