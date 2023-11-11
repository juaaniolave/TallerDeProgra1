package testGui;

import static org.junit.Assert.*;

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
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;
import util.Mensajes;
import vista.IOptionPane;

public class testMensajes {
	Robot robot;
	Controlador controlador;
	FalsoOptionPane op=new FalsoOptionPane();
	Agencia agencia;
	String user,password;
	public testMensajes()
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
		controlador.setMyOptionPane(op);
		agencia=agencia.getInstance();
		this.user="Pepe342";
		this.password="1234";
		agencia.registroEmpleador(this.user,this.password , "nombR1", "2235912241", Constantes.JURIDICA, Constantes.SALUD);
	}

	@After
	public void tearDown() throws Exception {
		agencia.getEmpleadores().clear();
	}

	@Test
	public void testContraIncorrecta() {
		 	robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarReg = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto(this.user, robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("444", robot);
	        //verifico los resultados
	        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.PASS_ERRONEO.getValor(),Mensajes.PASS_ERRONEO.getValor(),op.getMensaje());
	}

}
