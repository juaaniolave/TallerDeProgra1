package testsModeloDatos;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import modeloDatos.Contratacion;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;

class testContratacionJuanOlave {
	
	Contratacion contratacion;
	Empleador empleador;
	EmpleadoPretenso empleadoPretenso;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		empleador = new Empleador ();
		empleadoPretenso = new EmpleadoPretenso();
		contratacion=new Contratacion(empleador,empleadoPretenso);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConstructorEmpleador() {
		Assert.assertEquals("empleador no son iguales",this.empleador, this.contratacion.getEmpleador());
	}

	@Test
	void testConstructorEmpleadoPretenso() {
		Assert.assertEquals("empleadorPretenso no son iguales",this.empleadoPretenso, this.contratacion.getEmpleado());
	}
	@Test
	void testConstructorFecha() {
		 Calendar calendar = new GregorianCalendar();
		Assert.assertEquals("empleadorPretenso no son iguales",calendar, this.contratacion.getFecha());
	}
}
