package testsModeloDatos;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;
import modeloDatos.Contratacion;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;

public class testContratacion {
	Contratacion contratacion;
	Empleador empleador;
	EmpleadoPretenso empleadoPretenso;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		empleador = new Empleador ();
		empleadoPretenso = new EmpleadoPretenso();
		contratacion=new Contratacion(empleador,empleadoPretenso);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructorEmpleador() {
		Assert.assertEquals("empleador no son iguales",this.empleador, this.contratacion.getEmpleador());
	}

	@Test
	public void testConstructorEmpleadoPretenso() {
		Assert.assertEquals("empleadorPretenso no son iguales",this.empleadoPretenso, this.contratacion.getEmpleado());
	}
	@Test
	public void testConstructorFecha() {
		 Calendar calendar = new GregorianCalendar();
		Assert.assertEquals("empleadorPretenso no son iguales",calendar, this.contratacion.getFecha());
	}

}
