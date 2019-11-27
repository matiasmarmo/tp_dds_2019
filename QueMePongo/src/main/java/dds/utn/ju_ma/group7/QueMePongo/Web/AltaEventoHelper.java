package dds.utn.ju_ma.group7.QueMePongo.Web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class AltaEventoHelper {
	public Map<String, Object> controladorPantallas(Map<String, Object> viewModel) {
		viewModel.put("visibilidadNombreEvento", "hidden");
		viewModel.put("visibilidadGuardarropas", "hidden");
		viewModel.put("visibilidadCalendario", "hidden");
		viewModel.put("visibilidadConfirmacion", "hidden");
		viewModel.put("visibilidadEventoOk", "hidden");
		return viewModel;
	}
	
	public Boolean esFechaValida(String fecha) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = formatter.parse(fecha);
			Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			Calendar hoy = Calendar.getInstance();
			if(calender.before(hoy)) {
				return false;
			}
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public Calendar generarFecha(String fecha) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date;
		Calendar calender = Calendar.getInstance();
		try {
			date = formatter.parse(fecha);
			calender.setTime(date);
			return calender;
		} catch (ParseException e) {
			calender.setTime(new Date());
			return calender;
		}
	}
}
