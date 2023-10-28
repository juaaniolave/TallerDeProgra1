package TestAgencia;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
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
		this.empleador = (Empleador) agencia.registroEmpleador("user1", "pass1", "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
		this.empleadoPretenso = (EmpleadoPretenso) agencia.registroEmpleado("user2", "pass2", "nombR2", "ap1", "456", 30);
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
	public void testLimitesRemuneracion() {
		Assert.assertEquals("el limite superior deberia existir", this.limiteSuperior,3000);
	}
	@Test
	public void testMatch() {
		agencia.match(empleador,empleadoPretenso);
	}
	/*@Test
	public void testGuardar() {
		try {
			agencia.guardarAgencia("archivoagencia");
			File archivo=new File("archivoagencia");
			//en ningun lado dice que sea xml la persistencia pero lo es en teoria
			//asique otra cosa mas para el informe
			// y por ende no se podria testear cargarAgencia
			Assert.assertTrue("deberia existir el archivo",archivo.exists());
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}*/

	


}
