package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.Calendar;

import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.AccuWeatherProveedor;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.ProveedorClima;

public class JobAlertas {
	
	private ProveedorClima proveedorClima;
	
	private JobAlertas(ProveedorClima proveedorClima) {
		this.proveedorClima = proveedorClima;
	}
	
	public void run() {
		for(int i = 0; i < 3; i++) {
			Calendar fecha = Calendar.getInstance();
			fecha.add(Calendar.DATE, i);
			Alertador alertador = new Alertador(this.proveedorClima, new RepositorioUsuariosPersistente());
			alertador.informarAlertas(fecha);
		}
	}
	
	public static void main(String[] args) {
		new JobAlertas(new AccuWeatherProveedor()).run();
		System.exit(0);
	}

}
