package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.ProveedorClima;

public class JobAlertas extends TimerTask {
	
	public static Timer instanciarJobAlertas(ProveedorClima proveedorClima, long periodo) {
		JobAlertas job = new JobAlertas(proveedorClima);
		Timer timer = new Timer(false);
		timer.scheduleAtFixedRate(job, 0, periodo);
		return timer;
	}
	
	private ProveedorClima proveedorClima;
	
	private JobAlertas(ProveedorClima proveedorClima) {
		this.proveedorClima = proveedorClima;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 3; i++) {
			Calendar fecha = Calendar.getInstance();
			fecha.add(Calendar.DATE, i);
			Alertador alertador = new Alertador(this.proveedorClima, new RepositorioUsuariosPersistente());
			alertador.informarAlertas(fecha);
		}
	}

}
