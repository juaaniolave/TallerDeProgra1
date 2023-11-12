package testGui;

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
import modeloNegocio.Agencia;
import util.Constantes;
import vista.IVista;

public class testPaneles {
	Robot robot;
	 Controlador controlador;
	 IVista vistalogin;
	 Agencia agencia;
	 String user,password;
	 public testPaneles()
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
		vistalogin=controlador.getVista();
		user="user1";
		password="pass1";
		agencia.registroEmpleador(user,password , "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPanelLoginACliente(){
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
	        
	        Assert.assertEquals("El panel deberia ser el de Cliente", this.vistalogin,controlador.getVista());
	    }
	 @Test
	 public void testPanelLoginARegistro(){
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        TestUtils.clickComponent(registrar,robot);	        
	        
	        Assert.assertEquals("El panel deberia ser el de Registro",this.vistalogin,controlador.getVista());
	 }
	 @Test
	 public void testPanelLoginAadmin(){
		        robot.delay(TestUtils.getDelay());
		        //obtengo las referencias a los componentes necesarios                     
		        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
		        
		        TestUtils.clickComponent(user, robot);
		        TestUtils.tipeaTexto("admin", robot);
		        TestUtils.clickComponent(password, robot);
		        TestUtils.tipeaTexto("admin", robot);
		        TestUtils.clickComponent(aceptarLog, robot);
		        
		        Assert.assertEquals("El panel deberia ser el de Admin", this.vistalogin,controlador.getVista());
	 }
	 @Test
	 public void testRegistroALogin(){
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        TestUtils.clickComponent(registrar,robot);	        
	        JButton cancelar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_CANCELAR);
	        TestUtils.clickComponent(cancelar,robot);	        

	        Assert.assertTrue("El panel deberia ser el de Login",this.vistalogin==controlador.getVista());
	 }
	 @Test
	 public void testPanelAdminALogin(){
		        robot.delay(TestUtils.getDelay());
		        //obtengo las referencias a los componentes necesarios                     
		        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
		        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
		        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
		        
		        TestUtils.clickComponent(user, robot);
		        TestUtils.tipeaTexto("admin", robot);
		        TestUtils.clickComponent(password, robot);
		        TestUtils.tipeaTexto("admin", robot);
		        TestUtils.clickComponent(aceptarLog, robot);
		        JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CERRARSESION);
		        TestUtils.clickComponent(cerrarSesion, robot);
		        Assert.assertTrue("El panel deberia ser el de Login",this.vistalogin==controlador.getVista());

	 }
	 @Test
		public void testPanelClienteaLogin(){
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
		        JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CERRARSESION);
		        TestUtils.clickComponent(cerrarSesion, robot);
		        Assert.assertTrue("El panel deberia ser el de Login",this.vistalogin==controlador.getVista());

		    }
}
