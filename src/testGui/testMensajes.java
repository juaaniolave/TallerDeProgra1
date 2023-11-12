package testGui;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import junit.framework.Assert;
import modeloDatos.EmpleadoPretenso;
import modeloDatos.Empleador;
import modeloNegocio.Agencia;
import util.Constantes;
import util.Mensajes;
import vista.IOptionPane;
import vista.Ventana;

public class testMensajes {
	Robot robot;
	Controlador controlador;
	FalsoOptionPane op=new FalsoOptionPane();
	String userEmpleadoPretenso,userEmpleador,passEmpleadoPretenso,passEmpleador;
	EmpleadoPretenso empleado;
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
		this.userEmpleador="Alan";
		this.passEmpleador="Boca123";
		Agencia.getInstance().registroEmpleador(this.userEmpleador,this.passEmpleador, "Leonel", "2235912241", Constantes.JURIDICA, Constantes.SALUD);
		this.userEmpleadoPretenso="Pepe342";
		this.passEmpleadoPretenso="1234";
		empleado=(EmpleadoPretenso) Agencia.getInstance().registroEmpleado(this.userEmpleadoPretenso,this.passEmpleadoPretenso, "pepe", "Joselito","2235912241" , 21);
		Agencia.getInstance().crearTicketEmpleado(Constantes.PRESENCIAL, 1500, Constantes.JORNADA_COMPLETA, Constantes.JUNIOR, Constantes.EXP_MEDIA, Constantes.TERCIARIOS,empleado);
		Agencia.getInstance().setEstadoContratacion(false);
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
	public void testContraIncorrecta() {
		 	robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto(this.userEmpleadoPretenso, robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("111", robot);
	        TestUtils.clickComponent(aceptarLog, robot);

	        //verifico los resultados
	        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.PASS_ERRONEO.getValor(),Mensajes.PASS_ERRONEO.getValor(),op.getMensaje());
	}
	@Test
	public void testUserIncorrecta() {
		 	robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("Rogel", robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto(this.passEmpleadoPretenso, robot);
	        TestUtils.clickComponent(aceptarLog, robot);

	        //verifico los resultados
	        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.USUARIO_DESCONOCIDO.getValor(),Mensajes.USUARIO_DESCONOCIDO.getValor(),op.getMensaje());
	}
	 @Test
	    public void testRegEmpleadoRepetido()
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
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto(this.userEmpleadoPretenso, robot);
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
	        //verifico los resultados
	        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.USUARIO_REPETIDO.getValor(),Mensajes.USUARIO_REPETIDO.getValor(),op.getMensaje());
	    }
	 @Test
	    public void testRegEmpleadoConfirmacionContraInvalida()
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
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("Barcelona", robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("12345", robot);
	        TestUtils.clickComponent(repetirPassword, robot);
	        TestUtils.tipeaTexto("2525251", robot);
	        TestUtils.clickComponent(nombreReal, robot);
	        TestUtils.tipeaTexto("Pepe", robot);
	        TestUtils.clickComponent(tel, robot);
	        TestUtils.tipeaTexto("22341414", robot);
	        TestUtils.clickComponent(apellido, robot);
	        TestUtils.tipeaTexto("Guardiola", robot);
	        TestUtils.clickComponent(edad, robot);
	        TestUtils.tipeaTexto("21", robot);
	        TestUtils.clickComponent(aceptarRegistrar, robot);
	        //verifico los resultados
	        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.PASS_NO_COINCIDE.getValor(),Mensajes.PASS_NO_COINCIDE.getValor(),op.getMensaje());
	    }
	 /*
	 @Test
	    public void testRegEmpleadoCasillaVacia()
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
	        JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("Messi", robot);
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
	        TestUtils.clickComponent(aceptarRegistrar, robot);
	        //verifico los resultados
	        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.PARAMETROS_NULOS.getValor(),Mensajes.PARAMETROS_NULOS.getValor(),op.getMensaje());
	    }
	    */
	 @Test
	    public void testRegEmpleadorRepetido()
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
	        JRadioButton salud= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_RADIO_SALUD);
	        JRadioButton juridica= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_RADIO_JURIDICA);
	        JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto(this.userEmpleador, robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("12345", robot);
	        TestUtils.clickComponent(repetirPassword, robot);
	        TestUtils.tipeaTexto("12345", robot);
	        TestUtils.clickComponent(nombreReal, robot);
	        TestUtils.tipeaTexto("Pepe", robot);
	        TestUtils.clickComponent(tel, robot);
	        TestUtils.tipeaTexto("22341414", robot);
	        TestUtils.clickComponent(salud, robot);
	        TestUtils.clickComponent(juridica, robot);
	        TestUtils.clickComponent(aceptarRegistrar, robot);
	        //verifico los resultados
	        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.USUARIO_REPETIDO.getValor(),Mensajes.USUARIO_REPETIDO.getValor(),op.getMensaje());
	    }
	 @Test
	    public void testRegEmpleadorConfirmacionContraInvalida()
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
	   
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("roquee2e2", robot);
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
	        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.PASS_NO_COINCIDE.getValor(),Mensajes.PASS_NO_COINCIDE.getValor(),op.getMensaje());
	    }
	 	@Test
	 	public void testMuestraMensajeNoTicket() {
	 		robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto(this.userEmpleadoPretenso, robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto(this.passEmpleadoPretenso, robot);
	        TestUtils.clickComponent(aceptarLog, robot);
	        JButton eliminarTicket= (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.ELIMINAR_TICKET);
	        TestUtils.clickComponent(eliminarTicket, robot); 
	        JTextArea areaTicket=(JTextArea)TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.TEXT_AREA_TICKET);
	        TestUtils.clickComponent(areaTicket, robot);   
	        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.SIN_TICKET.getValor(),Mensajes.SIN_TICKET.getValor(),op.getMensaje());
	 	}
	 	@Test
	 	public void testMuestraTicket() {
	 		robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto(this.userEmpleadoPretenso, robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto(this.passEmpleadoPretenso, robot);
	        TestUtils.clickComponent(aceptarLog, robot);
	        JTextArea areaTicket=(JTextArea)TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.TEXT_AREA_TICKET);
	        TestUtils.clickComponent(areaTicket, robot);   
	        Assert.assertEquals("Mensaje incorrecto, deberia decir "+empleado.getTicket().toString(),empleado.getTicket().toString(),op.getMensaje());
	 	}
	 	
	 /*
	 @Test
	    public void testRegEmpleadorCasillaVacia()
	    {
		 robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JButton registrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REGISTRAR);
	        TestUtils.clickComponent(registrar,robot);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_USSER_NAME);
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_PASSWORD);
	        JTextField repetirPassword= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_CONFIRM_PASSWORD);
	        JTextField nombreReal= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_REAL_NAME);
	        JRadioButton salud= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_RADIO_SALUD);
	        JRadioButton juridica= (JRadioButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_RADIO_JURIDICA);
	        JButton aceptarRegistrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.REG_BUTTON_REGISTRAR);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("Alan", robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("12345", robot);
	        TestUtils.clickComponent(repetirPassword, robot);
	        TestUtils.tipeaTexto("12345", robot);
	        TestUtils.clickComponent(nombreReal, robot);
	        TestUtils.tipeaTexto("Pepe", robot);
	        TestUtils.clickComponent(salud, robot);
	        TestUtils.clickComponent(juridica, robot);
	        TestUtils.clickComponent(aceptarRegistrar, robot);
	        //verifico los resultados
	        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.PARAMETROS_NULOS.getValor(),Mensajes.PARAMETROS_NULOS.getValor(),op.getMensaje());
	    }
	 @Test
	    public void testAdminSoloInferiorNeg()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios                     
	        JTextField password= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.PASSWORD);
	        JTextField user= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.NOMBRE_USUARIO);
	        JButton aceptarLog = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.LOGIN);
	        //lleno los JTextField
	        TestUtils.clickComponent(user, robot);
	        TestUtils.tipeaTexto("admin", robot);
	        TestUtils.clickComponent(password, robot);
	        TestUtils.tipeaTexto("admin", robot);
	        TestUtils.clickComponent(aceptarLog, robot);
	        JTextField inferior= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.TEXTO_INFERIOR);
	        TestUtils.clickComponent(inferior, robot);
	        TestUtils.tipeaTexto("-100", robot);
	        
	        //verifico los resultados
	        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.LIMITE_REMUNERACION_NEGATIVO.getValor(),Mensajes.LIMITE_REMUNERACION_NEGATIVO,op.getMensaje());
	    }
	 @Test
	    public void testAdminSolosuperiorNeg()
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
	        TestUtils.tipeaTexto("-100", robot);
	        
	        //verifico los resultados
	        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.LIMITE_REMUNERACION_NEGATIVO.getValor(),Mensajes.LIMITE_REMUNERACION_NEGATIVO,op.getMensaje());
	    }
	 
	 @Test
	    public void testAdminInfMayorASup()
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
	        JTextField inferior= (JTextField) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.TEXTO_INFERIOR);
	        JButton cambiar = (JButton) TestUtils.getComponentForName((Component) controlador.getVista(),Constantes.MODIFICAR_VALORES);
	        TestUtils.clickComponent(superior, robot);
	        TestUtils.tipeaTexto("200", robot);
	        TestUtils.clickComponent(inferior, robot);
	        TestUtils.tipeaTexto("400", robot);
	        //verifico los resultados
	        Assert.assertEquals("Mensaje incorrecto, deberia decir "+Mensajes.LIMITE_REMUNERACION_INVALIDO,Mensajes.LIMITE_REMUNERACION_INVALIDO,op.getMensaje());
	    }
	    */
}
