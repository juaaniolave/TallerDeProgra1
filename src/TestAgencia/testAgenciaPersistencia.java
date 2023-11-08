package TestAgencia;

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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import controlador.Controlador;
import excepciones.ContraException;
import excepciones.LimiteInferiorRemuneracionInvalidaException;
import excepciones.LimiteSuperiorRemuneracionInvalidaException;
import excepciones.NombreUsuarioException;
import modeloDatos.Cliente;
import modeloDatos.Contratacion;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import persistencia.AgenciaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaXML;
import persistencia.UtilPersistencia;
import util.Constantes;

public class testAgenciaPersistencia {
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

	@BeforeEach
	void setUp() throws Exception {
		//este es un escenario posible algunos metodos requieren de otros escenarios tambien
		Agencia.getInstance().setPersistencia(p);
		this.empleador = (Empleador) agencia.registroEmpleador("user1", "pass1", "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
		this.empleadoPretenso = (EmpleadoPretenso) agencia.registroEmpleado("user2", "pass2", "nombR2", "ap1", "456", 30);
		this.agencia.crearTicketEmpleado(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleadoPretenso);
		this.agencia.crearTicketEmpleador(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleador);
		this.agencia.setLimitesRemuneracion(1000, 3000);
	}

	@AfterEach
	void tearDown() throws Exception {
		HashMap<String,Empleador> vacio1=new HashMap<String,Empleador>();
		HashMap<String,EmpleadoPretenso> vacio2=new HashMap<String,EmpleadoPretenso>();
		agencia.setEmpleados(vacio2);
		agencia.setEmpleadores(vacio1);
		agencia.setLimitesRemuneracion(1000, 3000);

	}

	@Test
	public void testGuardar() {
		
		
	}
	@Test
	public void testCargar() {
		
	}

}
