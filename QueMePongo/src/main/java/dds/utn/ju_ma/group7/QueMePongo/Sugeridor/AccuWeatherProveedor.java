package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import dds.utn.ju_ma.group7.QueMePongo.Excepciones.FechaInexistenteException;

public class AccuWeatherProveedor implements ProveedorClima {
	
	private Client client;
	private static final String API = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/7894";
	private String key_id = "0yMLbaqAcUXajQDilhGxZNGZnhWDl1SP";
	
	public AccuWeatherProveedor() {
		this.client = Client.create();
	}
	
	private JSONArray getPronosticoCincoDias() {
		ClientResponse response = 
				this.client.resource(API)
				.queryParam("apikey", key_id)
				.queryParam("language", "es-ar")
				.queryParam("details", "true")
				.queryParam("metrics", "true")
				.accept(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);

		String jsonString = response.getEntity(String.class);
		response.close();
		client.destroy();
		JSONObject pronostico = new JSONObject(jsonString);
		JSONArray pronosticoCincoDias = pronostico.getJSONArray("DailyForecasts");
		return pronosticoCincoDias;
	}
	
	private Calendar strignJsonToCalendar(String fecha) {
//		formato de fecha en json: "2019-05-27T07:00:00+07:00"
// 		No nos interesan las horas ya que AccuWeather no tiene precision con respecto a las horas
		String[] camposFecha = fecha.split("T");
		String[] camposDiaCompleto = camposFecha[0].split("-");
		String campoAnio = camposDiaCompleto[0];
		String campoMes = camposDiaCompleto[1];
		String campoDia = camposDiaCompleto[2];
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.set(Integer.parseInt(campoAnio), Integer.parseInt(campoMes), Integer.parseInt(campoDia));
		return fechaCalendar;
	}
	
	private boolean fechaCoincide(String fechaString, Calendar fechaBuscada) {
		Calendar fechaCalendar = strignJsonToCalendar(fechaString);
		return fechaCalendar.get(Calendar.DAY_OF_MONTH) == fechaBuscada.get(Calendar.DAY_OF_MONTH);
	}
	
	private Stream<JSONObject> jsonArraytoStream(JSONArray jsonArray){
		return IntStream
				.range(0, jsonArray.length())
				.mapToObj(elemento -> jsonArray.getJSONObject(elemento));
	}
	
	private JSONObject filtrarPorFecha(Stream<JSONObject> pronosticos, Calendar fecha) {
		List<JSONObject> pronosticoBuscadoLista = pronosticos
			.filter(pronostico -> fechaCoincide(pronostico.getString("Date"), fecha))
			.collect(Collectors.toList());
		if(pronosticoBuscadoLista.size() == 0) {
			throw new FechaInexistenteException("No existe la fecha buscada dentro del pronostico de 5 dias");			
		} else {
			return pronosticoBuscadoLista.iterator().next();
		}
	}
	
	private JSONObject pronosticoEspecifico(Calendar fechaBuscada) {
		JSONArray pronosticoCincoDias = getPronosticoCincoDias();
		Stream<JSONObject> pronosticosStream = jsonArraytoStream(pronosticoCincoDias);
		return filtrarPorFecha(pronosticosStream, fechaBuscada);
	}
	
	private double fahrenheitToCelsius(double gradosFahrenheit) {
		return (gradosFahrenheit - 32)* 5/9;
	}
	
	private double truncarADosDecimales(double numero) {
	    return Math.floor(numero * 100) / 100;
	}
	
	public double getTemperatura(Calendar fecha) {
		JSONObject pronostico = pronosticoEspecifico(fecha);
		JSONObject temperaturaJsonObject = pronostico.getJSONObject("Temperature");
		double minimaF = temperaturaJsonObject.getJSONObject("Minimum").getDouble("Value");
		double maximaF = temperaturaJsonObject.getJSONObject("Maximum").getDouble("Value");
		double minimaC = fahrenheitToCelsius(minimaF);
		double maximaC = fahrenheitToCelsius(maximaF);
		double resC = (minimaC + maximaC) / 2;
		return truncarADosDecimales(resC);
	}

}
