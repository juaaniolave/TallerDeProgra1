package TestModeloNegocios;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Contratacion;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import persistencia.PersistenciaXML;
import util.Constantes;

public class TestGuardar {
	Agencia agencia=Agencia.getInstance();
	Empleador empleador;
	EmpleadoPretenso empleadoPretenso;
	PersistenciaXML p=new PersistenciaXML();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.empleador = (Empleador) agencia.registroEmpleador("user1", "pass1", "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
		this.empleadoPretenso = (EmpleadoPretenso) agencia.registroEmpleado("user2", "pass2", "nombR2", "ap1", "456", 30);
		this.agencia.crearTicketEmpleado(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleadoPretenso);
		this.agencia.crearTicketEmpleador(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleador);
		this.agencia.setLimitesRemuneracion(1000, 3000);
		agencia.setPersistencia(p);
	}

	@After
	public void tearDown() throws Exception {
		HashMap<String,Empleador> vacio1=new HashMap<String,Empleador>();
		HashMap<String,EmpleadoPretenso> vacio2=new HashMap<String,EmpleadoPretenso>();
		HashMap<Cliente,Double> vacio3=new HashMap<Cliente,Double>();
		ArrayList<Contratacion> vacio4=new ArrayList<Contratacion>();
		agencia.setEmpleados(vacio2);
		agencia.setEmpleadores(vacio1);
		agencia.setContrataciones(vacio4);
		agencia.setComisionesUsuarios(vacio3);
		empleador.setListaDePostulantes(null);
		empleadoPretenso.setListaDePostulantes(null);
	}

	@Test
	public void testGuardarAgencia(){
		
		try {
			agencia.guardarAgencia("archivoagencia.xml");
			File arch=new File("archivoagencia.xml");
			Assert.assertTrue("el archivo deberia existir",arch.exists());
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}

}
