package testsModeloDatos;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Contratacion;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

public class testAgenciaPersistencia {
	
	Agencia agencia = Agencia.getInstance();
	Empleador empleador;
	EmpleadoPretenso empleadoPretenso;
	Contratacion contratacion;
	HashMap<Cliente, Double> comisionesCliente;
	int limiteInferior;
	int limiteSuperior;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		empleador = agencia.registroEmpleador(null, null, null, null, null, limiteInferior);
		//String claveMapEmpleador="emp1";
		empleadoPretenso = agencia.registroEmpleado(null, null, null, null, null, limiteInferior);
		/*String claveMapEmpleado="ep1";
		HashMap<String, EmpleadoPretenso> mapEmpleados=new HashMap<>();
		mapEmpleados.put(claveMapEmpleado, empleadoPretenso);
		HashMap<String, Empleador> mapEmpleadores=new HashMap<>();
		mapEmpleadores.put(claveMapEmpleador, empleador);
		agencia.setEmpleadores(mapEmpleadores);
		agencia.setEmpleados(mapEmpleados);
		*/
		
		contratacion = new Contratacion();
		comisionesCliente = new HashMap<Cliente, Double>();
		ArrayList<Contratacion> listacontrataciones = new ArrayList<Contratacion>();
		listacontrataciones.add(contratacion);
		limiteInferior = 2000;
		limiteSuperior = 3000;
		agencia.setComisionesUsuarios(comisionesCliente);
		agencia.setLimitesRemuneracion(limiteInferior, limiteSuperior);
		agencia.setContrataciones(listacontrataciones);
		agencia.crearTicketEmpleado(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleadoPretenso);
		agencia.crearTicketEmpleador(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleador);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
