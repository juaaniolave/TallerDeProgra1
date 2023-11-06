package testsModeloDatos;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modeloDatos.Cliente;
import modeloDatos.ClientePuntaje;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import util.Constantes;

class setListaPostulantes {
	ClientePuntaje c;
	double puntaje;
	Cliente cliente,cliente2;
	ArrayList<ClientePuntaje>listaClientePuntaje=new ArrayList<ClientePuntaje>();
	@BeforeEach
	void setUp() throws Exception {
		puntaje=20.0;
		cliente=new Empleador("Pepe32","Boca123","Pedro","223",Constantes.JURIDICA, Constantes.SALUD);
		c=new ClientePuntaje(this.puntaje,cliente);
		listaClientePuntaje.add(c);
		cliente2=new EmpleadoPretenso();
		cliente2.setListaDePostulantes(listaClientePuntaje);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testListaPostulantes() {
		ArrayList<ClientePuntaje>listaClientes=cliente2.getListaDePostulantes();
		Assert.assertEquals("Error deberia ser el mismo Postulante","Pepe32",listaClientes.get(0).getCliente().getUsserName());
	}

}
