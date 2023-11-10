package testGui;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import junit.framework.Assert;
import util.Constantes;

public class TestEnabledDisabled {

	 Robot robot;
	    Controlador controlador;

	    public TestEnabledDisabled()
	    {
	        try
	        {
	            robot = new Robot();
	        } catch (AWTException e)
	        {
	        }
	    }

	    @Before
	    public void setUp() throws Exception
	    {
	        controlador = new Controlador();
	    }

	    @After
	    public void tearDown() throws Exception
	    {
	    
	    //    controlador.getVentana().setVisible(false);
	    }

	    @Test
	    public void testVacio()
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
	    public void testRegSoloNombre()
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
	    public void testRegSoloContrasenia()
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
	    public void testRegUseryContrasenia()
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
	        Assert.assertTrue("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
	    }
	    @Test
	    public void testRegContraseniayUser()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("h2321312321", robot);
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("h21", robot);
	        //verifico los resultados
	        Assert.assertTrue("El boton de registro deberia estar hablitado", aceptarReg.isEnabled());
	        Assert.assertTrue("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
	    }
	    
}
