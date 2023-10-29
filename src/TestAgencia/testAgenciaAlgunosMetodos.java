package TestAgencia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.LimiteInferiorRemuneracionInvalidaException;
import excepciones.LimiteSuperiorRemuneracionInvalidaException;
import excepciones.NewRegisterException;
import modeloDatos.Cliente;
import modeloDatos.Contratacion;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

class testAgenciaAlgunosMetodos {
	Agencia agencia = Agencia.getInstance();
	Empleador empleador;
	EmpleadoPretenso empleadoPretenso;
	Contratacion contratacion;
	HashMap<Cliente, Double> comisionesCliente;
	int limiteInferior;
	int limiteSuperior;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		//este es un escenario posible algunos metodos requieren de otros escenarios tambien
		this.empleador = (Empleador) agencia.registroEmpleador("user1", "pass1", "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
		this.empleadoPretenso = (EmpleadoPretenso) agencia.registroEmpleado("user2", "pass2", "nombR2", "ap1", "456", 30);
		this.limiteInferior = 2000;
		this.limiteSuperior = 3000;
		this.agencia.setLimitesRemuneracion(limiteInferior, limiteSuperior);
		this.agencia.crearTicketEmpleado(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleadoPretenso);
		this.agencia.crearTicketEmpleador(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleador);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		HashMap<String,Empleador> vacio1=new HashMap<String,Empleador>();
		HashMap<String,EmpleadoPretenso> vacio2=new HashMap<String,EmpleadoPretenso>();
		HashMap<Cliente,Double> vacio3=new HashMap<Cliente,Double>();
		ArrayList<Contratacion> vacio4=new ArrayList<Contratacion>();
		agencia.setEmpleados(vacio2);
		agencia.setEmpleadores(vacio1);
		agencia.setContrataciones(vacio4);
		agencia.setComisionesUsuarios(vacio3);
	}

	@Test
	public void testLimitesRemuneracionSup() {
		Assert.assertEquals("el limite superior deberia existir", agencia.getLimiteSuperior(),3000);
	}
	@Test
	public void testLimitesRemuneracionInf() {
		Assert.assertEquals("el limite inferior deberia existir", agencia.getLimiteInferior(),2000);
	}
	@Test
	public void testMatch() {
		
		agencia.match(empleador,empleadoPretenso);
		Assert.assertEquals("el ticket deberia ser null",empleador.getTicket(),null);
		Assert.assertEquals("el ticket deberia ser null",empleadoPretenso.getTicket(),null);
		Assert.assertEquals("el puntaje debe ser 50",empleador.getPuntaje(),50);
		Assert.assertEquals("el puntaje debe ser 10",empleadoPretenso.getPuntaje(),10);
		//aca tamb testeamos los metodos de getcontratacionempleado/r (esto es integracion)
		Assert.assertEquals("deberia devolver el usuario asociado",agencia.getContratacionEmpleadoPretenso(empleadoPretenso),empleador);
		Assert.assertEquals("deberia devolver el usuario asociado",agencia.getContratacionEmpleador(empleador),empleadoPretenso);
		Contratacion c=new Contratacion(empleador,empleadoPretenso);
		ArrayList<Contratacion> ac=agencia.getContrataciones();
		Contratacion c2=ac.get(0);
		Assert.assertEquals("deberian ser la misma contratacion",c,c2);
		//Son la misma contratacion pero no tiene implementado el equls por ende dan diferente
		
	}
	@Test
	public void testSetLimitesRemuneracionesc1() {
		//en este escenario no se chequean las exepciones solo que cargue bien el numero
		try {
			agencia.setLimitesRemuneracion(1000, 2000);
			Assert.assertEquals("el limite debe cambiar",agencia.getLimiteInferior(),1000);
			Assert.assertEquals("el limite debe cambiar",agencia.getLimiteSuperior(),2000);
		} catch (LimiteSuperiorRemuneracionInvalidaException e) {
			Assert.fail(e.getMessage());
		} catch (LimiteInferiorRemuneracionInvalidaException e) {
			Assert.fail(e.getMessage());
		}
		
	}


}
