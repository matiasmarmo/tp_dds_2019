package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Calendar;
import javax.json.JsonObject;
import com.sun.jersey.api.client.WebResource;

public class AccuWeatherProveedor extends HttpProveedor {

	protected String api = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/7894";

	protected String key_id = "0yMLbaqAcUXajQDilhGxZNGZnhWDl1SP";

	protected WebResource parametrosRequest(WebResource resource) {
		return resource.queryParam("apikey", key_id).queryParam("language", "es-ar").queryParam("details", "true")
				.queryParam("metrics", "true");
	}

	protected Calendar strignJsonToCalendar(String fecha) {
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

	protected String campoFecha() {
		return "Date";
	}

	protected String campoListaPronosticos() {
		return "DailyForecasts";
	}
	
	private JsonObject getClimaPeriodo(JsonObject clima, Calendar fecha) {
		String periodo = fecha.get(Calendar.AM_PM) == Calendar.AM ? "Day" : "Night";
		return clima.getJsonObject(periodo);
	}
	
	public JsonObject getClima(Calendar fecha) {
		return pronosticoEspecifico(fecha);
	}
	
	public double getTemperatura(JsonObject clima) {
		JsonObject temperaturaJsonObject = clima.getJsonObject("Temperature");
		double minimaF = temperaturaJsonObject.getJsonObject("Minimum").getJsonNumber("Value").doubleValue();
		double maximaF = temperaturaJsonObject.getJsonObject("Maximum").getJsonNumber("Value").doubleValue();
		double minimaC = ModuloAlgebraico.fahrenheitToCelsius(minimaF);
		double maximaC = ModuloAlgebraico.fahrenheitToCelsius(maximaF);
		double resC = (minimaC + maximaC) / 2;
		return ModuloAlgebraico.truncarADosDecimales(resC);
	}

	public boolean hayTormentas(JsonObject clima, Calendar fecha) {
		return clima.getJsonNumber("ThunderstormProbability").intValue() >= 75;
	}

	public boolean hayNieve(JsonObject clima, Calendar fecha) {
		return getClimaPeriodo(clima, fecha).getJsonObject("Snow").getJsonNumber("Value").doubleValue() > 0;
	}

	public boolean hayLluvia(JsonObject clima, Calendar fecha) {
		return getClimaPeriodo(clima, fecha).getJsonObject("Rain").getJsonNumber("Value").doubleValue() > 0;
	}

	public boolean hayClimaSoleado(JsonObject clima, Calendar fecha) {
		return getClimaPeriodo(clima, fecha).getJsonNumber("CloudCover").intValue() < 20;
	}

	public boolean hayClimaVentoso(JsonObject clima, Calendar fecha) {
		return getClimaPeriodo(clima, fecha).getJsonObject("Wind").getJsonObject("Speed").getJsonNumber("Value").doubleValue() > 30;
	}
}
