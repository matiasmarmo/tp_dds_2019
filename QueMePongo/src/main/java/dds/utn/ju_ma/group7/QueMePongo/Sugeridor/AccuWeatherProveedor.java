package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.io.StringReader;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.core.MediaType;

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
	
	private JsonArray getPronosticoCincoDias() {
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
		
		JsonReader pronosticoJsonReader = Json.createReader(new StringReader(jsonString));
		JsonObject pronostico = pronosticoJsonReader.readObject();
		pronosticoJsonReader.close();
		JsonArray pronosticoCincoDias = pronostico.getJsonArray("DailyForecasts");
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
		int diaProvisto = fechaCalendar.get(Calendar.DAY_OF_MONTH);
		int diaBuscado = fechaBuscada.get(Calendar.DAY_OF_MONTH);
		boolean coinciden = diaBuscado == diaProvisto;
		return coinciden;
	}
	
	private Stream<JsonObject> jsonArrayToStream(JsonArray jsonArray){
		return jsonArray.stream().map(JsonValue -> JsonValue.asJsonObject());
	}
	
	private JsonObject filtrarPorFecha(Stream<JsonObject> pronosticos, Calendar fecha) {
		List<JsonObject> pronosticoBuscadoLista = pronosticos
				.filter(pronostico -> fechaCoincide(pronostico.getString("Date"), fecha))
				.collect(Collectors.toList());
		
		if(pronosticoBuscadoLista.size() == 0) {
			throw new FechaInexistenteException("No existe la fecha buscada dentro del pronostico de 5 dias");			
		} else {
			System.out.println(pronosticoBuscadoLista.size());
			return pronosticoBuscadoLista.iterator().next();
		}
	}
	
	private JsonObject pronosticoEspecifico(Calendar fechaBuscada) {
		JsonArray pronosticoCincoDias = getPronosticoCincoDias();
		Stream<JsonObject> pronosticosStream = jsonArrayToStream(pronosticoCincoDias);
		return filtrarPorFecha(pronosticosStream, fechaBuscada);
	}
	
	private double fahrenheitToCelsius(double gradosFahrenheit) {
		return (gradosFahrenheit - 32)* 5/9;
	}
	
	private double truncarADosDecimales(double numero) {
	    return Math.floor(numero * 100) / 100;
	}
	
	public double getTemperatura(Calendar fecha) {
		JsonObject pronostico = pronosticoEspecifico(fecha);
		JsonObject temperaturaJsonObject = pronostico.getJsonObject("Temperature");
		double minimaF = temperaturaJsonObject.getJsonObject("Minimum").getJsonNumber("Value").doubleValue();
		double maximaF = temperaturaJsonObject.getJsonObject("Maximum").getJsonNumber("Value").doubleValue();
		double minimaC = fahrenheitToCelsius(minimaF);
		double maximaC = fahrenheitToCelsius(maximaF);
		double resC = (minimaC + maximaC) / 2;
		return truncarADosDecimales(resC);
	}

}
