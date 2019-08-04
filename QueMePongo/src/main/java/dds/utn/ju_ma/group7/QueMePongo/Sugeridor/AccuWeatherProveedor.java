package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Calendar;
import javax.json.JsonObject;
import com.sun.jersey.api.client.WebResource;

public class AccuWeatherProveedor extends HttpProveedor {

	protected String api() {
		return "http://dataservice.accuweather.com/forecasts/v1/daily/5day/7894";
	}

	protected String key_id() {
		return "0yMLbaqAcUXajQDilhGxZNGZnhWDl1SP";
	}

	protected WebResource parametrosRequest(WebResource resource) {
		return resource.queryParam("apikey", key_id()).queryParam("language", "es-ar").queryParam("details", "true")
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

	public double getTemperatura(Calendar fecha) {
		JsonObject pronostico = pronosticoEspecifico(fecha);
		JsonObject temperaturaJsonObject = pronostico.getJsonObject("Temperature");
		double minimaF = temperaturaJsonObject.getJsonObject("Minimum").getJsonNumber("Value").doubleValue();
		double maximaF = temperaturaJsonObject.getJsonObject("Maximum").getJsonNumber("Value").doubleValue();
		double minimaC = ModuloAlgebraico.fahrenheitToCelsius(minimaF);
		double maximaC = ModuloAlgebraico.fahrenheitToCelsius(maximaF);
		double resC = (minimaC + maximaC) / 2;
		return ModuloAlgebraico.truncarADosDecimales(resC);
	}

	public boolean hayTormentas(Calendar fecha) {
		JsonObject pronostico = pronosticoEspecifico(fecha);
		String periodo = fecha.get(Calendar.AM_PM) == Calendar.AM ? "Day" : "Night";
		return pronostico.getJsonObject(periodo).getJsonNumber("ThunderstormProbability").intValue() >= 75;
	}
}
