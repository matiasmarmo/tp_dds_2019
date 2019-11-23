package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.Calendar;
import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.AccuWeatherProveedor;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.ProveedorClima;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.Sugeridor;

public class JobEventos {

	private Sugeridor sugeridor;
	private RepositorioEventos repositorioEventos;

	private JobEventos(ProveedorClima proveedorClima) {
		this.sugeridor = new Sugeridor(proveedorClima);
		this.repositorioEventos = new RepositorioEventosPersistente();
	}

	public void run() {
		List<Evento> eventosProximos = this.repositorioEventos.eventosProximos(Calendar.getInstance());
		eventosProximos.forEach(unEvento -> {
			this.sugeridor.sugerir(unEvento);
		});
	}

	public static void main(String[] args) {
		new JobEventos(new AccuWeatherProveedor()).run();
		System.exit(0);
	}

}
