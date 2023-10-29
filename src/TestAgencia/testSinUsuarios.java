package TestAgencia;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import modeloNegocio.Agencia;

class testSinUsuarios {
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSinUsers() {
		Agencia a1;
		Assert.assertEquals("El tipo deberia ser -1 ya que la agencia no presenta usuarios",-1,Agencia.getInstance().getTipoUsuario());
	}

}
