package testGui;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import junit.framework.Assert;
import modeloDatos.EmpleadoPretenso;
import modeloNegocio.Agencia;
import util.Constantes;
import util.Mensajes;
import vista.Ventana;

public class TestLlamadoAmetodosAgenciaSinContratacion {
	Robot robot;
	Controlador controlador;
	Agencia agencia;
	FalsoOptionPane op=new FalsoOptionPane();
	String user,password;
	 public TestLlamadoAmetodosAgenciaSinContratacion()
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
		user="user1";
		password="pass1";
		EmpleadoPretenso empleado=(EmpleadoPretenso) Agencia.getInstance().registroEmpleado(this.user,this.password, "pepe", "Joselito","2235912241" , 21);
		Agencia.getInstance().crearTicketEmpleado(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleado);
		Agencia.getInstance().setEstadoContratacion(true);
	}

	@After
	public void tearDown() throws Exception {
		Ventana ventana=(Ventana) controlador.getVista();
		ventana.setVisible(false);	
		Agencia.getInstance().getEmpleadores().clear();
		Agencia.getInstance().getEmpleados().clear();
		Agencia.getInstance().setEstadoContratacion(false);
		Agencia.getInstance().cerrarSesion();
	}

 	@Test
 	public void testMuestraMensajeErrorEliminarTicketEstadoDeContratacion() {
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
        TestUtils.clickComponent(aceptarLog, robot);
        JButton eliminarTicket= (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.ELIMINAR_TICKET);
        TestUtils.clickComponent(eliminarTicket, robot); 
        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.ERROR_AGENCIA_EN_CONTRATACION.getValor(),Mensajes.ERROR_AGENCIA_EN_CONTRATACION.getValor(),op.getMensaje());
 	}
 	@Test
 	public void testMuestraMensajeErrorCrearTicketEstadoDeContratacion() {
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
        TestUtils.clickComponent(aceptarLog, robot);
        JButton nuevoTicket= (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NUEVOTICKET);
        TestUtils.clickComponent(nuevoTicket, robot); 
        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.ERROR_AGENCIA_EN_CONTRATACION.getValor(),Mensajes.ERROR_AGENCIA_EN_CONTRATACION.getValor(),op.getMensaje());
 	}
}
