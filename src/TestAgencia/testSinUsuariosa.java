package TestAgencia;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import junit.framework.Assert;
import modeloNegocio.Agencia;

public class testSinUsuariosa {
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSinUsers() {
		Agencia a1;
		Assert.assertEquals("El tipo deberia ser -1 ya que la agencia no presenta usuarios",-1,Agencia.getInstance().getTipoUsuario());
	}

}
