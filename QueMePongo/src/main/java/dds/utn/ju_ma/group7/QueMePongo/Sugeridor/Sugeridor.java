package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Evento;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Sugerencia;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class Sugeridor {
	
	private ProveedorClima proveedorClima;

	public Sugeridor(ProveedorClima proveedorClima) {
		this.proveedorClima = proveedorClima;
	}
	
	private List<Atuendo> filtrarAtuendosPorTemperatura(List<Atuendo> atuendos, Calendar fecha, Usuario usuario){
		double temperatura = this.proveedorClima.getTemperatura(fecha);
		return atuendos.stream().filter(unAtuendo -> unAtuendo.esAdecuadoATemperatura(temperatura, usuario.getSensibilidad())).collect(Collectors.toList());
	}

	public void sugerir(Evento evento) {
		List<Sugerencia> sugerencias = new ArrayList<>();
		List<Atuendo> atuendosAdecuados = this.filtrarAtuendosPorTemperatura(
				evento.getGuardarropa().generarAtuendos(),
				evento.getProximaFecha(Calendar.getInstance()),
				evento.getUsuario());
		atuendosAdecuados.forEach(unAtuendo -> sugerencias.add(new Sugerencia(unAtuendo)));
		evento.serSugerido(sugerencias);
	}

}
