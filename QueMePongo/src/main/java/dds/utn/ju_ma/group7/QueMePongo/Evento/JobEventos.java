package dds.utn.ju_ma.group7.QueMePongo.Evento;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.ProveedorClima;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.Sugeridor;

public class JobEventos extends TimerTask {
	
	public static Timer instanciarJobEventos(ProveedorClima proveedorClima, long periodo) {
		JobEventos job = new JobEventos(proveedorClima);
		Timer timer = new Timer(false);
		timer.scheduleAtFixedRate(job, 0, periodo);
		return timer;
	}
	
	private Sugeridor sugeridor;
	
	private JobEventos(ProveedorClima proveedorClima) {
		this.sugeridor = new Sugeridor(proveedorClima);
	}
	
	@Override
	public void run() {
		RepositorioEventos repositorioEventos = new RepositorioEventos();
		List<EventoUnico> eventosProximos = repositorioEventos.eventosProximos(Calendar.getInstance());
		eventosProximos.forEach(unEvento -> this.sugeridor.sugerir(unEvento));
	}

}
