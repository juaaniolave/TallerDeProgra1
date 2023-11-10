package testsModeloDatos;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import modeloDatos.Contratacion;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;

public class TestContratacion {
	Contratacion contratacion;
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
