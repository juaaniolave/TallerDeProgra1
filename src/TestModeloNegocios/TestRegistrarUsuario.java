package TestModeloNegocios;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import excepciones.ImposibleCrearEmpleadoException;
import excepciones.ImposibleCrearEmpleadorException;
import excepciones.NewRegisterException;
import junit.framework.Assert;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

public class TestRegistrarUsuario {
	Agencia agencia = Agencia.getInstance();
	Empleador empleador;
	EmpleadoPretenso empleadoPretenso;
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
	}

	@After
	public void tearDown() throws Exception {
		agencia.setEmpleadores(new HashMap<String,Empleador>());
		agencia.setEmpleados(new HashMap<String,EmpleadoPretenso>());
	}

	@Test
	public void testRegistroEmpleadorExiste() {
		//analizamos el caso de que ya exista un usuario
		try {
			agencia.registroEmpleador("user1", "pass1", "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
			Assert.fail("debe haber lanzado exepcion");
		} catch (NewRegisterException e) {
		} catch (ImposibleCrearEmpleadorException e) {
			Assert.fail(e.getMessage());
		}

	}
	@Test
	public void testRegistroEmpleadoExiste() {
		//analizamos el caso de que ya exista un usuario
			try {
				agencia.registroEmpleado("user2", "pass2", "nombR2", "ap1", "456", 30);
				Assert.fail("debio haber lanzado excepcion");
			} catch (NewRegisterException e) {
				
			} catch (ImposibleCrearEmpleadoException e) {
				Assert.fail("no es la exepcion que se espera");
			}
	}
	@Test
	public void testRegistroEmpleadorInvalidUser() {
		//analizamos el caso de que el usuario que se quiera crear tenga mal las credenciales
		
			try {
				agencia.registroEmpleador(null, "pass1", "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
				Assert.fail();
			} catch (NewRegisterException e) {
				Assert.fail();
			} catch (ImposibleCrearEmpleadorException e) {
				
			}
	}
	@Test
	public void testRegistroEmpleadorInvalidPass() {
		try {
			agencia.registroEmpleador("usej", null, "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
			Assert.fail("debe haber lanzado exepcion porque password es null");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadorException e) {
		}
	}
	@Test
	public void testRegistroEmpleadorInvalidRealName() {
		try {
			agencia.registroEmpleador("usej", "pass1", null, "123", Constantes.JURIDICA, Constantes.SALUD);
			Assert.fail("debe haber lanzado exepcion porque nombre real es null");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadorException e) {
		}
	}
	@Test
	public void testRegistroEmpleadorInvalidTel() {
		try {
			agencia.registroEmpleador("usej", "pass1", "nombR1", null, Constantes.JURIDICA, Constantes.SALUD);
			Assert.fail("debe haber lanzado exepcion porque telefono es null");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadorException e) {
		}
	}
	@Test
	public void testRegistroEmpleadorInvalidTipo() {
		try {
			agencia.registroEmpleador("usej", "pass1", "nombR1", "123", "MATERIAL", Constantes.SALUD);
			Assert.fail("debe haber lanzado exepcion porque tipo no concuerda");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadorException e) {
		}
	}
	@Test
	public void testRegistroEmpleadorInvalidRubro() {
		try {
			agencia.registroEmpleador("usej", "pass1", "nombR1", "123", Constantes.JURIDICA, "OTRO");
			Assert.fail("debe haber lanzado exepcion porque rubro no concuerda");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadorException e) {
		}
	}
	@Test
	public void testRegistroEmpleadoInvalidUser() {
		try {
			agencia.registroEmpleado(null, "pass", "nombreR", "ap", "12343", 30);
			Assert.fail("deberia dar exepcion usuario nulo");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadoException e) {
		}
	}
	@Test
	public void testRegistroEmpleadoInvalidPass() {
		try {
			agencia.registroEmpleado("usej", null, "nombreR", "ap", "12343", 30);
			Assert.fail("deberia dar exepcion password nulo");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadoException e) {
		}

	}
	@Test
	public void testRegistroEmpleadoInvalidRealName() {
		try {
			agencia.registroEmpleado("usej", "pass", null, "ap", "12343", 30);
			Assert.fail("deberia dar exepcion nombre real nulo");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadoException e) {
		}

		
	}
	@Test
	public void testRegistroEmpleadoInvalidApellido() {
		try {
			agencia.registroEmpleado("usej", "pass", "nombreR", null, "12343", 30);
			Assert.fail("deberia dar exepcion apellido nulo");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadoException e) {
		}
	}
	@Test
	public void testRegistroEmpleadoInvalidTel() {
		try {
			agencia.registroEmpleado("usej", "pass", "nombreR", "ap", null, 30);
			Assert.fail("deberia dar exepcion telefono nulo");
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadoException e) {
		}
	}
	@Test
	public void testRegistroEmpleadorCorrecto() {
		//este es el caso que se reistra correctamente
		Empleador emp;
		try {
			emp = (Empleador) agencia.registroEmpleador("USEJ", "pass1", "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
			Empleador emp2=agencia.getEmpleadores().get("USEJ");
			Assert.assertEquals("deben ser el mismo usuario",emp,emp2);
		} catch (ImposibleCrearEmpleadorException e) {
			Assert.fail(e.getMessage());
		}
		catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} 
	}
	@Test
	public void testRegistroEmpleadoCorrecto() {
		//este es el caso que se reistra correctamente
		EmpleadoPretenso emp;
		try {
			emp = (EmpleadoPretenso) agencia.registroEmpleado("USEJ", "pass2", "nombR2", "ap1", "456", 30);
			EmpleadoPretenso emp2=agencia.getEmpleados().get("USEJ");
			Assert.assertEquals("deben ser el mismo usuario",emp,emp2);
		} catch (NewRegisterException e) {
			Assert.fail(e.getMessage());
		} catch (ImposibleCrearEmpleadoException e) {
			Assert.fail(e.getMessage());
		}
	}

}
