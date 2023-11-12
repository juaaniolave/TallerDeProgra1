package testGui;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import junit.framework.Assert;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;

public class TestLlamadoAmetodos {
	Robot robot;
	Controlador controlador;
	Agencia agencia;
	String user,password;
	 public TestLlamadoAmetodos()
	 {
		 try
	     {
			 robot = new Robot();
	     } catch (AWTException e)
	     {
	     }
	 }
	@Before
	public void setUp() throws Exception {
		controlador = new Controlador();
		agencia=agencia.getInstance();
		user="user1";
		password="pass1";
		agencia.registroEmpleador(user,password , "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testLoginExitoso(){
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto(this.user, robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto(this.password, robot);
	        TestUtils.clickComponent(aceptarLog, robot);
	        
	        Assert.assertTrue("El cliente no se logueo de manera exitosa",agencia.getEmpleadores().containsKey(this.user));
	        Assert.assertEquals("El cliente no se logueo de manera exitosa",agencia.getTipoUsuario(),1);

	    }
	@Test
    public void testRegistroExitoso()
    {
 		robot.delay(TestUtils.getDelay());
 	    //obtengo las referencias a los componentes necesarios                     
 	    JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
 	    TestUtils.clickComponent(registrar,robot);
 	    JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_USSER_NAME);
 	    JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
 	    JTextField repetirPassword= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
 	    JTextField nombreReal= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_REAL_NAME);
 	    JTextField tel= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_TELEFONO);
 	    JTextField apellido= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_APELLIDO);
 	    JTextField edad= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_EDAD);
 	    JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
 	  
 	    TestUtils.clickComponent(user, robot);
 	    TestUtils.tipeaTexto("Barcelona", robot);
 	    TestUtils.clickComponent(password, robot);
 	    TestUtils.tipeaTexto("12345", robot);
 	    TestUtils.clickComponent(repetirPassword, robot);
 	    TestUtils.tipeaTexto("12345", robot);
 	    TestUtils.clickComponent(nombreReal, robot);
 	    TestUtils.tipeaTexto("Pepe", robot);
 	    TestUtils.clickComponent(tel, robot);
 	    TestUtils.tipeaTexto("22341414", robot);
 	    TestUtils.clickComponent(apellido, robot);
 	    TestUtils.tipeaTexto("Guardiola", robot);
 	    TestUtils.clickComponent(edad, robot);
 	    TestUtils.tipeaTexto("21", robot);
 	    TestUtils.clickComponent(aceptarRegistrar, robot);
 	    
        Assert.assertTrue("El cliente no se logueo de manera exitosa deberia existir en el sistema",agencia.getEmpleados().containsKey("Barcelona"));
    }
	@Test
    public void testcerrarSesionExitoso()
    {
 		robot.delay(TestUtils.getDelay());
 	    //obtengo las referencias a los componentes necesarios                     
 	    JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
 	    TestUtils.clickComponent(registrar,robot);
 	    JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_USSER_NAME);
 	    JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
 	    JTextField repetirPassword= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
 	    JTextField nombreReal= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_REAL_NAME);
 	    JTextField tel= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_TELEFONO);
 	    JTextField apellido= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_APELLIDO);
 	    JTextField edad= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_EDAD);
 	    JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
 	  
 	    TestUtils.clickComponent(user, robot);
 	    TestUtils.tipeaTexto("Barcelona", robot);
 	    TestUtils.clickComponent(password, robot);
 	    TestUtils.tipeaTexto("12345", robot);
 	    TestUtils.clickComponent(repetirPassword, robot);
 	    TestUtils.tipeaTexto("12345", robot);
 	    TestUtils.clickComponent(nombreReal, robot);
 	    TestUtils.tipeaTexto("Pepe", robot);
 	    TestUtils.clickComponent(tel, robot);
 	    TestUtils.tipeaTexto("22341414", robot);
 	    TestUtils.clickComponent(apellido, robot);
 	    TestUtils.tipeaTexto("Guardiola", robot);
 	    TestUtils.clickComponent(edad, robot);
 	    TestUtils.tipeaTexto("21", robot);
 	    TestUtils.clickComponent(aceptarRegistrar, robot);
 	    JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CERRARSESION);
 	    TestUtils.clickComponent(cerrarSesion, robot);

        Assert.assertEquals("El cliente deberia haberse deslogueado con exito",agencia.getTipoUsuario(),-1);
    }
}
