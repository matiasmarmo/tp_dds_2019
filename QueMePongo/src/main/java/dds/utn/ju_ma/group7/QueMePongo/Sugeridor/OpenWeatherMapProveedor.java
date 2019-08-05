package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.Calendar;
import javax.json.JsonObject;
import com.sun.jersey.api.client.WebResource;

public class OpenWeatherMapProveedor extends HttpProveedor {

	private String lon_CABA = "-58.450001";
	private String lat_CABA = "-34.599998";

	protected String api() {
		return "http://api.openweathermap.org/data/2.5/forecast/";
	}

	protected String key_id() {
		return "a40660bcb12fb0790f9455769b86f8ed";
	}

	protected WebResource parametrosRequest(WebResource resource) {
		return resource.queryParam("lon", lon_CABA).queryParam("lat", lat_CABA).queryParam("appid", key_id());
	}

	protected Calendar strignJsonToCalendar(String fecha) {
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

	protected String campoFecha() {
		return "dt_txt";
	}

	protected String campoListaPronosticos() {
		return "list";
	}

	protected boolean fechaCoincide(String fechaString, Calendar fechaBuscada) {
		Calendar fechaCalendar = strignJsonToCalendar(fechaString);
		return super.fechaCoincide(fechaString, fechaBuscada)
				&& fechaCalendar.get(Calendar.HOUR_OF_DAY) == fechaBuscada.get(Calendar.HOUR_OF_DAY);
	}

	private JsonObject getPronosticoEspecifico(Calendar fecha) {
		Calendar fechaRedondeada = ModuloAlgebraico.redondearFecha(fecha);
		return pronosticoEspecifico(fechaRedondeada);
	}

	public double getTemperatura(Calendar fecha) {
		JsonObject pronostico = getPronosticoEspecifico(fecha);
		double temperaturaK = pronostico.getJsonObject("main").getJsonNumber("temp").doubleValue();
		double temperaturaC = ModuloAlgebraico.kelvinToCelsius(temperaturaK);
		return ModuloAlgebraico.truncarADosDecimales(temperaturaC);
	}
	
	private String clima(Calendar fecha) {
		JsonObject pronostico = getPronosticoEspecifico(fecha);
		return pronostico.getJsonArray("weather").getJsonObject(0).getJsonString("main").getString();
	}

	public boolean hayTormentas(Calendar fecha) {
		return clima(fecha).contentEquals("Thunderstorm");
	}

	public boolean hayNieve(Calendar fecha) {
		return clima(fecha).contentEquals("Snow");
	}

	public boolean hayLluvia(Calendar fecha) {
		return clima(fecha).contentEquals("Rain");
	}

	public boolean hayClimaSoleado(Calendar fecha) {
		return clima(fecha).contentEquals("Clear");
	}

	public boolean hayClimaVentoso(Calendar fecha) {
		JsonObject pronostico = getPronosticoEspecifico(fecha);
		return pronostico.getJsonObject("wind").getJsonNumber("speed").doubleValue() > 30;
	}
}
