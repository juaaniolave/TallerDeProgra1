package testsModeloDatos;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;
import modeloDatos.Cliente;
import modeloDatos.ClientePuntaje;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;

class testAplicaPromoJuanOlave {
	EmpleadoPretenso empleadoPretenso;
	Empleador empleadores;
	Cliente cliente;
	Agencia agencia;
	boolean promoPorListaDePostulantes;
	@Before
	public void setUp() {
		promoPorListaDePostulantes=true;
		//crear lista empleados y empleadores y postulantes
		
	}
	
	
	
	@Test
	public void testAplicaPromo() {
		Cliente c1;
		c1 = agencia.aplicaPromo(promoPorListaDePostulantes);
		Assert.assertEquals("El cliente no esta en la lista de postulantes",cliente.getListaDePostulantes().contains(c1),true);
		
	} 
}
