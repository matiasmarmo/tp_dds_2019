package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.Calendar;

public enum TipoRecurrencia {
	
	DIARIA, SEMANAL, MENSUAL, ANUAL;
	
	private int campoSumaCalendar;
	
	static {
		DIARIA.campoSumaCalendar = Calendar.DATE;
		SEMANAL.campoSumaCalendar = Calendar.WEEK_OF_YEAR;
		MENSUAL.campoSumaCalendar = Calendar.MONTH;
		ANUAL.campoSumaCalendar = Calendar.YEAR;
	}
	
	public Calendar obtenerFechaSiguienteIntancia(Calendar fechaInicioEventoRecurrente, Calendar fechaMinimaInstancia) {
		Calendar nuevaInstancia = (Calendar) fechaInicioEventoRecurrente.clone();
		while(nuevaInstancia.before(fechaMinimaInstancia) || nuevaInstancia.equals(fechaMinimaInstancia)) {
			nuevaInstancia.add(this.campoSumaCalendar, 1);
		}
		return nuevaInstancia;
	}

}
