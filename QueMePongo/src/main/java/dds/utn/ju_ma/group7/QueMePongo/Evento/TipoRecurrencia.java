package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
	
	public List<Calendar> todasLasFechasEnIntervalo(Calendar fechaInicioEventoRecurrente, Calendar fechaInicio, Calendar fechaFin) {
		List<Calendar> resultado = new ArrayList<Calendar>();
		Calendar temporal = (Calendar) fechaInicio.clone();
		while(ChronoUnit.DAYS.between(fechaFin.toInstant(), temporal.toInstant()) < 0) {
			temporal = this.obtenerFechaSiguienteIntancia(fechaInicioEventoRecurrente, temporal);
			resultado.add(temporal);
		}
		return resultado;
	}

}
