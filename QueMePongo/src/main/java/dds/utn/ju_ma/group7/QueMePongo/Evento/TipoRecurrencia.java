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
	
	public Calendar obtenerFechaSiguienteIntancia(Calendar fechaUltimaInstancia) {
		Calendar nuevaInstancia = (Calendar) fechaUltimaInstancia.clone();
		nuevaInstancia.add(this.campoSumaCalendar, 1);
		return nuevaInstancia;
	}
	
	public List<Calendar> todasLasFechasEnIntervalo(Calendar fechaUltimaInstancia, Calendar fechaFin) {
		List<Calendar> resultado = new ArrayList<Calendar>();
		Calendar temporal = fechaUltimaInstancia;
		while(ChronoUnit.DAYS.between(fechaFin.toInstant(), temporal.toInstant()) <= 0) {
			resultado.add(temporal);
			temporal = this.obtenerFechaSiguienteIntancia(temporal);
		}
		return resultado;
	}

}
