package TestModeloNegocios;

import modeloDatos.Ticket;

public class MockTicket extends Ticket {

	public MockTicket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MockTicket(String locacion, int remuneracion, String jornada, String puesto, String experiencia,
			String estudios) {
		super(locacion, remuneracion, jornada, puesto, experiencia, estudios);
		// TODO Auto-generated constructor stub
	}
	@Override
	public double getComparacionTotal(Ticket t) {
		return super.getComparacionEstudios(t)+super.getComparacionExperiencia(t)+super.getComparacionJornada(t)+super.getComparacionLocacion(t)+super.getComparacionPuesto(t)+super.getComparacionRemuneracion(t);
	}
}
