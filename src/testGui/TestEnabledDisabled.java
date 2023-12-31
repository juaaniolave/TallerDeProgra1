package testGui;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controlador.Controlador;
import junit.framework.Assert;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;
import vista.Ventana;

public class TestEnabledDisabled {
	 Robot robot;
	 Controlador controlador;
	 Agencia agencia;
	 String user,password,user2,password2;
	 public TestEnabledDisabled()
	 {
		 try
	     {
			 robot = new Robot();
	     } catch (AWTException e)
	     {
	     }
	 }
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		controlador = new Controlador();
		agencia=agencia.getInstance();
		user="user1";
		password="pass1";
		user2="user2";
		password2="pass2";
		Empleador empleador=(Empleador)agencia.registroEmpleador(user,password , "nombR1", "123", Constantes.JURIDICA, Constantes.SALUD);
		this.agencia.crearTicketEmpleador(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleador);
		agencia.registroEmpleador(user2,password2, "nombR2", "1234", Constantes.FISICA, Constantes.SALUD);

	}

	@After
	public void tearDown() throws Exception {
		Ventana ventana=(Ventana) controlador.getVista();
		ventana.setVisible(false);	
		agencia.getEmpleadores().clear();
		agencia.getEmpleados().clear();
		agencia.setEstadoContratacion(false);
		agencia.cerrarSesion();
	}

	 @Test
	    public void testLogVacio()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //verifico los resultados
	        Assert.assertTrue("El boton de registro deberia estar hablitado", aceptarReg.isEnabled());
	        Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
	        
	    }
	    @Test
	    public void testLogSoloNombre()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(nombre, robot);
	        TestUtils.tipeaTexto("hola13123", robot);
	        //verifico los resultados
	        Assert.assertTrue("El boton de registro deberia estar hablitado", aceptarReg.isEnabled());
	        Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
	    }
	    @Test
	    public void testLogSoloContrasenia()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("h2321312321", robot);
	        //verifico los resultados
	        Assert.assertTrue("El boton de registro deberia estar hablitado", aceptarReg.isEnabled());
	        Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
	    }
	    @Test
	    public void testLogUseryContrasenia()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("h21", robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("h2321312321", robot);
	        //verifico los resultados
	        Assert.assertTrue("El boton de registro deberia estar hablitado", aceptarReg.isEnabled());
	        Assert.assertTrue("El boton de login deberia estar hablitado", aceptarLog.isEnabled());
	    }
	    @Test
	    public void testLogCompletoYquitoUser()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("h21", robot);
	        TestUtils.borraJTextField(user, robot);
	        //verifico los resultados
	        Assert.assertTrue("El boton de registro deberia estar hablitado", aceptarReg.isEnabled());
	        Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
	    }
	    @Test
	    public void testLogCompletoYquitoContra()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("h21", robot);
	        TestUtils.borraJTextField(password, robot);
	        //verifico los resultados
	        Assert.assertTrue("El boton de registro deberia estar hablitado", aceptarReg.isEnabled());
	        Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
	    }
	    @Test
	    public void testLogCompletoYquitoUserConContra()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios   
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("55555", robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("h21", robot);
	        TestUtils.borraJTextField(user, robot);
	        //verifico los resultados
	        Assert.assertTrue("El boton de registro deberia estar habilitado", aceptarReg.isEnabled());
	        Assert.assertFalse("El boton de login deberia estar deshabilitado", aceptarLog.isEnabled());
	    }
	    @Test
	    public void testLogCompletoYquitoContraConUser()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios   
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("h21", robot);
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("55555", robot);
	        TestUtils.borraJTextField(password, robot);
	        //verifico los resultados
	        Assert.assertTrue("El boton de registro deberia estar hablitado", aceptarReg.isEnabled());
	        Assert.assertFalse("El boton de login deberia estar deshabilitado", aceptarLog.isEnabled());
	    }
	    @Test
	    public void testRegVacio()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        TestUtils.clickComponent(registrar,robot);	        
	        JRadioButton salud= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_RADIO_SALUD);
	        JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
	        JButton cancelarRegistro = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_CANCELAR);
	        //verifico los resultados
	        Assert.assertFalse("El boton de registro deberia estar deshablitado",aceptarRegistrar.isEnabled());
	        Assert.assertTrue("El boton de cancelar deberia estar habilitado", cancelarRegistro.isEnabled());
	        Assert.assertFalse("El boton de salud deberia estar deshabilitado", salud.isEnabled());
	    }
	    @Test
	    public void testRegUser()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        TestUtils.clickComponent(registrar,robot);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_USSER_NAME);
	        JRadioButton salud= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_RADIO_SALUD);
	        JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
	        JButton cancelarRegistro = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_CANCELAR);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("Pepe", robot);
	        //verifico los resultados
	        Assert.assertFalse("El boton de registro deberia estar deshablitado",aceptarRegistrar.isEnabled());
	        Assert.assertTrue("El boton de cancelar deberia estar habilitado", cancelarRegistro.isEnabled());
	        Assert.assertFalse("El boton de salud deberia estar deshabilitado", salud.isEnabled());
	    }
	    @Test
	    public void testRegPassword()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        TestUtils.clickComponent(registrar,robot);
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
	        JRadioButton salud= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_RADIO_SALUD);
	        JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
	        JButton cancelarRegistro = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_CANCELAR);
	        //lleno los JTextField
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("Pepe123", robot);
	        //verifico los resultados
	        Assert.assertFalse("El boton de registro deberia estar deshablitado",aceptarRegistrar.isEnabled());
	        Assert.assertTrue("El boton de cancelar deberia estar habilitado", cancelarRegistro.isEnabled());
	        Assert.assertFalse("El boton de salud deberia estar deshabilitado", salud.isEnabled());
	    }
	    @Test
	    public void testRegConfirmPassword()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        TestUtils.clickComponent(registrar,robot);
	        JTextField confirmPassword= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
	        JRadioButton salud= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_RADIO_SALUD);
	        JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
	        JButton cancelarRegistro = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_CANCELAR);
	        //lleno los JTextField
	        TestUtils.clickComponent(confirmPassword, robot);
	        TestUtils.tipeaTexto("Pepe123", robot);
	        //verifico los resultados
	        Assert.assertFalse("El boton de registro deberia estar deshablitado",aceptarRegistrar.isEnabled());
	        Assert.assertTrue("El boton de cancelar deberia estar habilitado", cancelarRegistro.isEnabled());
	        Assert.assertFalse("El boton de salud deberia estar deshabilitado", salud.isEnabled());
	    }
	    @Test
	    public void testRegNombreReal()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        TestUtils.clickComponent(registrar,robot);
	        JTextField nombreReal= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_REAL_NAME);
	        JRadioButton salud= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_RADIO_SALUD);
	        JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
	        JButton cancelarRegistro = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_CANCELAR);
	        //lleno los JTextField
	        TestUtils.clickComponent(nombreReal, robot);
	        TestUtils.tipeaTexto("Pepe", robot);
	        //verifico los resultados
	        Assert.assertFalse("El boton de registro deberia estar deshablitado",aceptarRegistrar.isEnabled());
	        Assert.assertTrue("El boton de cancelar deberia estar habilitado", cancelarRegistro.isEnabled());
	        Assert.assertFalse("El boton de salud deberia estar deshabilitado", salud.isEnabled());
	    }
	    @Test
	    public void testRegTelefono()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        TestUtils.clickComponent(registrar,robot);
	        JTextField telefono= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_TELEFONO);
	        JRadioButton salud= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_RADIO_SALUD);
	        JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
	        JButton cancelarRegistro = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_CANCELAR);
	        //lleno los JTextField
	        TestUtils.clickComponent(telefono, robot);
	        TestUtils.tipeaTexto("223411523", robot);
	        //verifico los resultados
	        Assert.assertFalse("El boton de registro deberia estar deshablitado",aceptarRegistrar.isEnabled());
	        Assert.assertTrue("El boton de cancelar deberia estar habilitado", cancelarRegistro.isEnabled());
	        Assert.assertFalse("El boton de salud deberia estar deshabilitado", salud.isEnabled());
	    }
	    @Test
	    public void testRegApellido()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        TestUtils.clickComponent(registrar,robot);
	        JTextField apellido= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_APELLIDO);
	        JRadioButton salud= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_RADIO_SALUD);
	        JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
	        JButton cancelarRegistro = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_CANCELAR);
	        //lleno los JTextField
	        TestUtils.clickComponent(apellido, robot);
	        TestUtils.tipeaTexto("Pepe", robot);
	        //verifico los resultados
	        Assert.assertFalse("El boton de registro deberia estar deshablitado",aceptarRegistrar.isEnabled());
	        Assert.assertTrue("El boton de cancelar deberia estar habilitado", cancelarRegistro.isEnabled());
	        Assert.assertFalse("El boton de salud deberia estar deshabilitado", salud.isEnabled());
	    }
	    public void testRegEdad()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        TestUtils.clickComponent(registrar,robot);
	        JTextField edad= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_EDAD);
	        JRadioButton salud= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_RADIO_SALUD);
	        JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
	        JButton cancelarRegistro = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_CANCELAR);
	        //lleno los JTextField
	        TestUtils.clickComponent(edad, robot);
	        TestUtils.tipeaTexto("Pepe", robot);
	        //verifico los resultados
	        Assert.assertFalse("El boton de registro deberia estar deshablitado",aceptarRegistrar.isEnabled());
	        Assert.assertTrue("El boton de cancelar deberia estar habilitado", cancelarRegistro.isEnabled());
	        Assert.assertFalse("El boton de salud deberia estar deshabilitado", salud.isEnabled());
	    }
	    public void testRegEnabledEmpleador()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        TestUtils.clickComponent(registrar,robot);
	        JButton empleador= (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.EMPLEADOR);
	        TestUtils.clickComponent(empleador,robot);
	        JRadioButton salud= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_RADIO_SALUD);
	        JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
	        JButton cancelarRegistro = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_CANCELAR);
	        //verifico los resultados
	        Assert.assertFalse("El boton de registro deberia estar deshablitado",aceptarRegistrar.isEnabled());
	        Assert.assertTrue("El boton de cancelar deberia estar habilitado", cancelarRegistro.isEnabled());
	        Assert.assertTrue("El boton de salud deberia estar habilitado", salud.isEnabled());
	        
	    }
	    @Test
	    public void testRegEnabledBotonRegistrarEmpleado()
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
	        JRadioButton salud= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_RADIO_SALUD);
	        JButton cancelarRegistro = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_CANCELAR);

	 	    //lleno los JTextField
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
	        //verifico los resultados
	        Assert.assertTrue("El boton de registro deberia estar hablitado",aceptarRegistrar.isEnabled());
	        Assert.assertTrue("El boton de cancelar deberia estar habilitado",cancelarRegistro.isEnabled());
	        Assert.assertFalse("El boton de salud deberia estar deshabilitado",salud.isEnabled());
	    }
	    @Test
	    public void testRegEnabledBotonRegistrarEmpleador()
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
	        JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
	        JRadioButton empleador= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.EMPLEADOR);
	        JRadioButton salud= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_RADIO_SALUD);
	        JButton cancelarRegistro = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_CANCELAR);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("Alan", robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("12345", robot);
	        TestUtils.clickComponent(repetirPassword, robot);
	        TestUtils.tipeaTexto("41451323223323", robot);
	        TestUtils.clickComponent(nombreReal, robot);
	        TestUtils.tipeaTexto("Pepe", robot);
	        TestUtils.clickComponent(tel, robot);
	        TestUtils.tipeaTexto("22341414", robot);
	        TestUtils.clickComponent(empleador, robot);
	        TestUtils.clickComponent(aceptarRegistrar, robot);
	        //verifico los resultados
	        Assert.assertTrue("El boton de registro deberia estar hablitado",aceptarRegistrar.isEnabled());
	        Assert.assertTrue("El boton de cancelar deberia estar habilitado",cancelarRegistro.isEnabled());
	        Assert.assertTrue("El boton de salud deberia estar habilitado",salud.isEnabled());
	    }
	    @Test
	    public void testRegEdadNeg()
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
	        JRadioButton salud= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_RADIO_SALUD);
	        JButton cancelarRegistro = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_CANCELAR);

	 	    //lleno los JTextField
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
	 	    TestUtils.tipeaTexto("-24", robot);
	 	    //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("Pepe", robot);
	        //verifico los resultados
	        Assert.assertFalse("El boton de registro deberia estar deshablitado",aceptarRegistrar.isEnabled());
	        Assert.assertTrue("El boton de cancelar deberia estar habilitado",cancelarRegistro.isEnabled());
	        Assert.assertFalse("El boton de salud deberia estar deshabilitado",salud.isEnabled());
	    }
	    @Test
	    public void testAdminVacio()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("admin", robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("admin", robot);
	        TestUtils.clickComponent(aceptarLog, robot);
	        JButton cambiar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.MODIFICAR_VALORES);
	        //verifico los resultados
	        Assert.assertFalse("El boton cambiar deberia estar deshablitado", cambiar.isEnabled());
	    }
	    @Test
	    public void testAdminSoloInferior()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("admin", robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("admin", robot);
	        TestUtils.clickComponent(aceptarLog, robot);
	        JTextField inferior= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.TEXTO_INFERIOR);
	        JButton cambiar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.MODIFICAR_VALORES);
	        TestUtils.clickComponent(inferior, robot);
	        TestUtils.tipeaTexto("10", robot);
	        
	        //verifico los resultados
	        Assert.assertFalse("El boton cambiar deberia estar deshablitado", cambiar.isEnabled());
	    }
	    @Test
	    public void testAdminSoloSuperior()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("admin", robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("admin", robot);
	        TestUtils.clickComponent(aceptarLog, robot);
	        JTextField superior= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.TEXTO_SUPERIOR);
	        JButton cambiar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.MODIFICAR_VALORES);
	        TestUtils.clickComponent(superior, robot);
	        TestUtils.tipeaTexto("10", robot);
	        
	        //verifico los resultados
	        Assert.assertFalse("El boton cambiar deberia estar deshablitado", cambiar.isEnabled());
	    }
	    @Test
	    public void testAdminSupMenorInf()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("admin", robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("admin", robot);
	        TestUtils.clickComponent(aceptarLog, robot);
	        JTextField inferior= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.TEXTO_INFERIOR);
	        JTextField superior= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.TEXTO_SUPERIOR);
	        JButton cambiar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.MODIFICAR_VALORES);
	        TestUtils.clickComponent(inferior, robot);
	        TestUtils.tipeaTexto("10", robot);
	        TestUtils.clickComponent(superior, robot);
	        TestUtils.tipeaTexto("5", robot);
	        //verifico los resultados
	        Assert.assertFalse("El boton cambiar deberia estar deshablitado", cambiar.isEnabled());
	    }
	    @Test
	    public void testAdminInfNoPos()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("admin", robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("admin", robot);
	        TestUtils.clickComponent(aceptarLog, robot);
	        JTextField inferior= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.TEXTO_INFERIOR);
	        JTextField superior= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.TEXTO_SUPERIOR);
	        JButton cambiar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.MODIFICAR_VALORES);
	        TestUtils.clickComponent(inferior, robot);
	        TestUtils.tipeaTexto("0", robot);
	        TestUtils.clickComponent(superior, robot);
	        TestUtils.tipeaTexto("20", robot);
	        //verifico los resultados
	        Assert.assertFalse("El boton cambiar deberia estar deshablitado", cambiar.isEnabled());
	    }
	    @Test
	    public void testAdminEnabledAplicaPromoVacia()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("admin", robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("admin", robot);
	        TestUtils.clickComponent(aceptarLog, robot);
	        JButton AplicaPromo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.APLICAR_PROMO);	        
	        //verifico los resultados
	        Assert.assertTrue("El boton cambiar deberia estar hablitado",AplicaPromo.isEnabled());
	    }
	    @Test
	    public void testAdminEnabledAplicaPromoConLista()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("admin", robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("admin", robot);
	        TestUtils.clickComponent(aceptarLog, robot);
	        JButton AplicaPromo = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.APLICAR_PROMO);	        
	        JCheckBox ListaPostulantes = (JCheckBox) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CHECK_BOX_LISTA_POSTULANTES);	        
	        TestUtils.clickComponent(ListaPostulantes, robot);

	        Assert.assertTrue("El boton cambiar deberia estar hablitado",AplicaPromo.isEnabled());
	    }
	    @Test
	    public void testClienteVacia()
	    {
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
	        JRadioButton jornadaMedia= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.JORNADA_MEDIA);
	        JButton confirmarNuevoTicket = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CONFIRMARNUEVOTICKET);
	        Assert.assertFalse("El boton de jornada media deberia estar deshablitado", jornadaMedia.isEnabled());
	        Assert.assertFalse("El boton de registro deberia estar deshablitado", confirmarNuevoTicket.isEnabled());
	    }
	    @Test
	    public void testClienteAreaNuevoTicketVisibleYNuevoTicketInvisible()
	    {
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
	        JRadioButton jornadaMedia= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.JORNADA_MEDIA);
	        JButton confirmarNuevoTicket = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CONFIRMARNUEVOTICKET);
	        JButton nuevoTicket = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NUEVOTICKET);
	        TestUtils.clickComponent(nuevoTicket, robot);
	        Assert.assertTrue("El boton de jornada media deberia estar hablitado", jornadaMedia.isEnabled());
	        Assert.assertFalse("El boton de aceptar deberia estar deshablitado", confirmarNuevoTicket.isEnabled());
	        Assert.assertFalse("El boton de nuevoTicket deberia estar deshablitado", nuevoTicket.isEnabled());  
	    }
	    @Test
	    public void testClienteNumNegNuevoTicketInVisibleYAceptarInvisible()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto(this.user, robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto(this.password, robot);
	        //verifico los resultados
	        TestUtils.clickComponent(aceptarLog, robot);
	        JRadioButton jornadaMedia= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.JORNADA_MEDIA);
	        JButton confirmarNuevoTicket = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CONFIRMARNUEVOTICKET);
	        JButton nuevoTicket = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NUEVOTICKET);
	        TestUtils.clickComponent(nuevoTicket, robot);
	        JTextField remuneracion= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.TEXTFIELD_REMUNERACION);
	        TestUtils.clickComponent(remuneracion, robot);
	        TestUtils.tipeaTexto("-2", robot);
	        Assert.assertTrue("El boton de jornada media deberia estar hablitado", jornadaMedia.isEnabled());
	        Assert.assertFalse("El boton de aceptar deberia estar deshablitado", confirmarNuevoTicket.isEnabled());
	        Assert.assertFalse("El boton de nuevoTicket deberia estar deshabilitado", nuevoTicket.isEnabled());    
	    }
	    @Test
	    public void testClienteAreaNuevoTicketVisibleYAceptarVisible()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto(this.user, robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto(this.password, robot);
	        //verifico los resultados
	        TestUtils.clickComponent(aceptarLog, robot);
	        JRadioButton jornadaMedia= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.JORNADA_MEDIA);
	        JButton confirmarNuevoTicket = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CONFIRMARNUEVOTICKET);
	        JButton nuevoTicket = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NUEVOTICKET);
	        TestUtils.clickComponent(nuevoTicket, robot);
	        JTextField remuneracion= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.TEXTFIELD_REMUNERACION);
	        TestUtils.clickComponent(remuneracion, robot);
	        TestUtils.tipeaTexto("200", robot);
	        Assert.assertTrue("El boton de jornada media deberia estar hablitado", jornadaMedia.isEnabled());
	        Assert.assertTrue("El boton de aceptar deberia estar hablitado", confirmarNuevoTicket.isEnabled());
	        Assert.assertFalse("El boton de nuevoTicket deberia estar deshabilitado", nuevoTicket.isEnabled());    
	    }
	    @Test
	    public void testClienteConfirmaNuevoTicket()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto(this.user, robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto(this.password, robot);
	        //verifico los resultados
	        TestUtils.clickComponent(aceptarLog, robot);
	        JRadioButton jornadaMedia= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.JORNADA_MEDIA);
	        JButton confirmarNuevoTicket = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.CONFIRMARNUEVOTICKET);
	        JButton nuevoTicket = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NUEVOTICKET);
	        TestUtils.clickComponent(nuevoTicket, robot);
	        JTextField remuneracion= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.TEXTFIELD_REMUNERACION);
	        TestUtils.clickComponent(remuneracion, robot);
	        TestUtils.tipeaTexto("200", robot);
	        TestUtils.clickComponent(confirmarNuevoTicket, robot);

	        Assert.assertFalse("El boton de jornada media deberia estar deshablitado", jornadaMedia.isEnabled());
	        Assert.assertFalse("El boton de aceptar deberia estar deshablitado", confirmarNuevoTicket.isEnabled());
	        Assert.assertTrue("El boton de nuevoTicket deberia estar habilitado", nuevoTicket.isEnabled());    
	    }
	    @Test
	    public void testClienteEnabledElimTicket()
	    {
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
	        JButton eliminarTicket = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.ELIMINAR_TICKET);
	        Assert.assertTrue("El boton de registro deberia estar hablitado",eliminarTicket.isEnabled());
	    }
	    @Test
	    public void testClienteDesabledElimTicket()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto(this.user2, robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto(this.password2, robot);
	        TestUtils.clickComponent(aceptarLog, robot);
	        JButton eliminarTicket = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.ELIMINAR_TICKET);
	        Assert.assertFalse("El boton de registro deberia estar deshablitado",eliminarTicket.isEnabled());
	    }
}
