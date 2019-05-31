package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.io.StringReader;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import dds.utn.ju_ma.group7.QueMePongo.Excepciones.FechaInexistenteException;

public class OpenWeatherMapProveedor implements ProveedorClima {
	
	private Client client;
	private static final String API = "http://api.openweathermap.org/data/2.5/forecast/";
	public String lon_CABA = "-58.450001";
	public String lat_CABA = "-34.599998";
	private String key_id = "a40660bcb12fb0790f9455769b86f8ed";

	public OpenWeatherMapProveedor() {
		this.client = Client.create();
	}

	private JsonArray getPronosticoCincoDias() {
		ClientResponse response = 
				this.client.resource(API)
				.queryParam("lat", lat_CABA)
				.queryParam("lon", lon_CABA)
				.queryParam("appid", key_id)
				.accept(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);

		String jsonString = response.getEntity(String.class);
		response.close();
		client.destroy();
		
		JsonReader pronosticoJsonReader = Json.createReader(new StringReader(jsonString));
		JsonObject pronostico = pronosticoJsonReader.readObject();
		pronosticoJsonReader.close();
		JsonArray pronosticoCincoDias = pronostico.getJsonArray("list");
		return pronosticoCincoDias;
	}

	private Calendar strignJsonToCalendar(String fecha) {
//		formato de fecha en json: "2019-05-25 18:00:00"
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
		return fechaCalendar.get(Calendar.DAY_OF_MONTH) == fechaBuscada.get(Calendar.DAY_OF_MONTH)
				&& fechaCalendar.get(Calendar.HOUR_OF_DAY) == fechaBuscada.get(Calendar.HOUR_OF_DAY);
	}
	
	private Stream<JsonObject> jsonArraytoStream(JsonArray jsonArray){
		return jsonArray.stream().map(JsonValue -> JsonValue.asJsonObject());
	}
	
	private JsonObject filtrarPorFecha(Stream<JsonObject> pronosticos, Calendar fecha) {
		List<JsonObject> pronosticoBuscadoLista = pronosticos
				.filter(pronostico -> fechaCoincide(pronostico.getString("dt_txt"), fecha))
				.collect(Collectors.toList());
		
		if(pronosticoBuscadoLista.size() == 0) {
			throw new FechaInexistenteException("No existe la fecha buscada dentro del pronostico de 5 dias");			
		} else {
			return pronosticoBuscadoLista.iterator().next();
		}
	}

	private JsonObject pronosticoEspecifico(Calendar fechaBuscada) {
		JsonArray pronosticoCincoDias = getPronosticoCincoDias();
		Stream<JsonObject> pronosticosStream = jsonArraytoStream(pronosticoCincoDias);
		return filtrarPorFecha(pronosticosStream, fechaBuscada);
	}

	private double kelvinToCelsius(double gradosKelvin) {
		return gradosKelvin - 273.15;
	}
	
	private double truncarADosDecimales(double numero) {
	    return Math.floor(numero * 100) / 100;
	}

	public double getTemperatura(Calendar fecha) {
		fecha = redondearFecha(fecha);
		JsonObject pronostico = pronosticoEspecifico(fecha);
		double temperaturaK = pronostico.getJsonObject("main").getJsonNumber("temp").doubleValue();
		double temperaturaC = kelvinToCelsius(temperaturaK);
		return truncarADosDecimales(temperaturaC);
	}
	
	private Calendar redondearFecha(Calendar fecha) {
		int hour = fecha.get(Calendar.HOUR_OF_DAY);
		int day = fecha.get(Calendar.DAY_OF_MONTH);
		if(hour == 24) { fecha.set(Calendar.HOUR_OF_DAY, 0); fecha.set(Calendar.DAY_OF_MONTH, day + 1); }
		else if(hour == 23 || hour == 22) { fecha.set(Calendar.HOUR_OF_DAY, 21); }
		else if(hour % 3 == 1) { fecha.set(Calendar.HOUR_OF_DAY, hour+2); }
		else if(hour % 3 == 2) { fecha.set(Calendar.HOUR_OF_DAY, hour+1); }
		return fecha;
	}
}
