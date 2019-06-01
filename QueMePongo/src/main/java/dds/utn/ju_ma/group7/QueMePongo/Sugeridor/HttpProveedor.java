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
import com.sun.jersey.api.client.WebResource;

import dds.utn.ju_ma.group7.QueMePongo.Excepciones.FechaInexistenteException;

public abstract class HttpProveedor implements ProveedorClima {

	protected Client client;
	
	protected abstract String api();
	
	protected abstract String key_id();
	
	protected abstract WebResource parametrosRequest(WebResource resource);
	
	protected abstract Calendar strignJsonToCalendar(String fecha);
	
	protected abstract String campoFecha();
	
	protected abstract String campoListaPronosticos();
	
	public abstract double getTemperatura(Calendar fecha);

	public HttpProveedor() {
		this.client = Client.create();
	}
	
	private void cerrarConexion(ClientResponse response) {
		client.destroy();
		response.close();
	}
	
	private JsonObject StringToJsonObject(String jsonString) {
		JsonReader pronosticoJsonReader = Json.createReader(new StringReader(jsonString));
		JsonObject pronosticoJsonObject = pronosticoJsonReader.readObject();
		pronosticoJsonReader.close();
		return pronosticoJsonObject;
	}
	
	private JsonObject obtenerRespuestaJson() {
		WebResource resource = this.client.resource(api());
		ClientResponse response = parametrosRequest(resource)
				.accept(MediaType.APPLICATION_JSON)
				.get(ClientResponse.class);
		
		String jsonString = response.getEntity(String.class);
		JsonObject respuesta = StringToJsonObject(jsonString);
		
		cerrarConexion(response);
		return respuesta;
	}

	protected JsonArray getPronosticoCincoDias() {
		JsonObject respuestaProveedor = obtenerRespuestaJson();
		JsonArray pronosticoCincoDias = respuestaProveedor.getJsonArray(campoListaPronosticos());
		return pronosticoCincoDias;
	}
	
	protected boolean fechaCoincide(String fechaProvistaString, Calendar fechaBuscada) {
		Calendar fechaProvista = strignJsonToCalendar(fechaProvistaString);
		return fechaProvista.get(Calendar.DAY_OF_MONTH) == fechaBuscada.get(Calendar.DAY_OF_MONTH);
	}
	
	private Stream<JsonObject> jsonArraytoStream(JsonArray jsonArray){
		return jsonArray.stream().map(JsonValue -> JsonValue.asJsonObject());
	}
	
	private JsonObject filtrarPorFecha(Stream<JsonObject> pronosticos, Calendar fecha) {
		List<JsonObject> pronosticoBuscadoLista = pronosticos
				.filter(pronostico -> fechaCoincide(pronostico.getString(campoFecha()), fecha))
				.collect(Collectors.toList());
		
		if(pronosticoBuscadoLista.size() == 0) {
			throw new FechaInexistenteException("No existe la fecha buscada dentro del pronostico de 5 dias");			
		} else {
			return pronosticoBuscadoLista.iterator().next();
		}
	}
	
	public JsonObject pronosticoEspecifico(Calendar fechaBuscada) {
		JsonArray pronosticoCincoDias = getPronosticoCincoDias();
		Stream<JsonObject> pronosticosStream = jsonArraytoStream(pronosticoCincoDias);	
		
		// El filtrar por fecha para openweather anda mal
		return filtrarPorFecha(pronosticosStream, fechaBuscada);
	}
	
	protected double truncarADosDecimales(double numero) {
		return Math.floor(numero * 100) / 100;
	}
}

























