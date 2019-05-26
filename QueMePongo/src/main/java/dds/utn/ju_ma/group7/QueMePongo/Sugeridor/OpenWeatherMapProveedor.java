package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Calendar;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class OpenWeatherMapProveedor implements ProveedorClima {
	private Client client;
	private static final String API = "http://api.openweathermap.org/data/2.5/forecast/";
	private String lon_CABA = "-58.450001";
	private String lat_CABA = "-34.599998";
	private String key_id = "a40660bcb12fb0790f9455769b86f8ed";

	public OpenWeatherMapProveedor() {
		this.client = Client.create();
	}

	private JSONArray getPronosticoCincoDias() {
		ClientResponse response = this.client.resource(API).queryParam("lat", lat_CABA).queryParam("lon", lon_CABA)
				.queryParam("appid", key_id).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		String jsonString = response.getEntity(String.class);
		JSONObject pronostico = new JSONObject(jsonString);
		JSONArray pronosticoCincoDias = pronostico.getJSONArray("list");
		return pronosticoCincoDias;
	}

	private Calendar strignJsonToCalendar(String fecha) {
//		 formato de fecha en json: "2019-05-25 18:00:00"
		String[] camposFecha = fecha.split(" ");
		String[] camposDiaCompleto = camposFecha[0].split("-");
		String campoAnio = camposDiaCompleto[0];
		String campoMes = camposDiaCompleto[1];
		String campoDia = camposDiaCompleto[2];
		String[] camposHoraStrings = camposFecha[1].split(":");
		String campoHora = camposHoraStrings[0];
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.set(Integer.parseInt(campoAnio), Integer.parseInt(campoMes), Integer.parseInt(campoDia),
				Integer.parseInt(campoHora), 00);
		return fechaCalendar;
	}

	private boolean fechaCoincide(String fechaString, Calendar fechaBuscada) {
		Calendar fechaCalendar = strignJsonToCalendar(fechaString);
		return fechaCalendar.get(Calendar.YEAR) == fechaBuscada.get(Calendar.YEAR)
				&& fechaCalendar.get(Calendar.MONTH) == fechaBuscada.get(Calendar.MONTH)
				&& fechaCalendar.get(Calendar.DAY_OF_MONTH) == fechaBuscada.get(Calendar.DAY_OF_MONTH)
				&& fechaCalendar.get(Calendar.HOUR_OF_DAY) == fechaBuscada.get(Calendar.HOUR_OF_DAY);
	}

	private JSONObject pronosticoEspecifico(Calendar fechaBuscada) {
		JSONArray pronostricoCincoDias = getPronosticoCincoDias();
		String fecha;
		JSONObject pronosticoBuscado;
		for (int i = 0; i < pronostricoCincoDias.length(); i++) {
			pronosticoBuscado = pronostricoCincoDias.getJSONObject(i);
			fecha = pronosticoBuscado.getString("dt_txt");
			if (fechaCoincide(fecha, fechaBuscada)) {
				return pronosticoBuscado;
			}
		}
		throw new FechaInexistenteExcepcion("No existe la fecha buscada dentro del pronostico de 5 dias");
	}

	private double kelvinToCelsius(double gradosKelvin) {
		return gradosKelvin - 273.15;
	}

	public double getTemperatura(Calendar fecha) {
		JSONObject pronostico = pronosticoEspecifico(fecha);
		double temperaturaKelvin = pronostico.getJSONObject("main").getInt("temp");
		return kelvinToCelsius(temperaturaKelvin);
	}

}
