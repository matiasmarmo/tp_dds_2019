package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Sugerencia;

public class Sugeridor {
	private static ProveedorClima proveedorClima;

	public ProveedorClima getProveedorClima() {
		return proveedorClima;
	}

	public static void setProveedorClima(ProveedorClima proveedorClima) {
		Sugeridor.proveedorClima = proveedorClima;
	}
	
	private static List<Atuendo> filtrarAtuendosPorTemperatura(List<Atuendo> atuendos, Calendar fecha){
		double temperatura = Sugeridor.proveedorClima.getTemperatura(fecha);
		return atuendos.stream().filter(unAtuendo -> unAtuendo.esAdecuadoATemperatura(temperatura)).collect(Collectors.toList());
	}

	public static List<Sugerencia> sugerir(List<Atuendo> atuendos, Calendar fecha) {
		List<Sugerencia> sugerencias = new ArrayList<Sugerencia>();
		List<Atuendo> atuendosAdecuados = Sugeridor.filtrarAtuendosPorTemperatura(atuendos, fecha);
		atuendosAdecuados.stream().forEach(unAtuendo -> sugerencias.add(new Sugerencia(unAtuendo)));
		return sugerencias;
	}

}
