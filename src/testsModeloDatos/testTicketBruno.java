package testsModeloDatos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modeloDatos.Ticket;
import modeloNegocio.Agencia;
import util.Constantes;

class testTicketBruno {
	String locacion1;
	int remuneracion1;
	String jornada1;
	String puesto1;
	String experiencia1;
	String estudios1;
	Ticket t1;
	String locacion2;
	int remuneracion2;
	String jornada2;
	String puesto2;
	String experiencia2;
	String estudios2;
	Ticket t2;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		Agencia.getInstance().setLimitesRemuneracion(1000, 5000);
		this.locacion1=Constantes.PRESENCIAL;
		this.remuneracion1=1500;
		this.jornada1=Constantes.JORNADA_COMPLETA;
		this.puesto1=Constantes.JUNIOR;
		this.experiencia1=Constantes.EXP_MEDIA;
		this.estudios1=Constantes.TERCIARIOS;
		t1=new Ticket(this.locacion1,this.remuneracion1,this.jornada1,this.puesto1,this.experiencia1,this.estudios1);
		this.locacion2=Constantes.HOME_OFFICE;
		this.remuneracion2=2500;
		this.jornada2=Constantes.JORNADA_EXTENDIDA;
		this.puesto2=Constantes.SENIOR;
		this.experiencia2=Constantes.EXP_MUCHA;
		this.estudios2=Constantes.SECUNDARIOS;
		t2=new Ticket(this.locacion2,this.remuneracion2,this.jornada2,this.puesto2,this.experiencia2,this.estudios2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetComparacionEstudios() {
		Assert.assertEquals(-1.5,t1.getComparacionEstudios(t2),0.0);
	}
	@Test
	void testGetComparacionExperiencia() {
		Assert.assertEquals(1.5,t1.getComparacionExperiencia(t2),0.0);
	}
	@Test
	void testGetComparacionJornada() {
		Assert.assertEquals(-0.5,t1.getComparacionJornada(t2),0.0);
	}
	@Test
	void testGetComparacionLocacion() {
		Assert.assertEquals(-1,t1.getComparacionLocacion(t2),0.0);
	}
	@Test
	void testGetComparacionPuesto() {
		Assert.assertEquals(-0.5,t1.getComparacionPuesto(t2),0.0);
	}
	@Test
	void testGetComparacionRemuneracion() {
		Assert.assertEquals(1.0,t1.getComparacionRemuneracion(t2),0.0);
	}
	@Test
	void tesSetRemuneracion() {
		t1.setRemuneracion(1500);
		Assert.assertEquals("no cambio bien la remuneracion",t1.getRemuneracion(),1500);
	}

}
