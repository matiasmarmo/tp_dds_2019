package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.Calendar;
import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.EstadoDelClima;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.ProveedorClima;

public class Alertador {
	
	private ProveedorClima proveedorClima;
	private RepositorioUsuarios repositorioUsuarios;
	
	public Alertador(ProveedorClima proveedorClima, RepositorioUsuarios repositorioUsuarios) {
		this.proveedorClima = proveedorClima;
		this.repositorioUsuarios = repositorioUsuarios;
	}
	
	public void informarAlertas(Calendar fecha) {
		this.obtenerAlertasParaLaFecha(fecha)
			.forEach(alerta -> this.repositorioUsuarios.informarUsuariosDe(fecha, alerta));
	}
	
	public void informarDe(Calendar fecha, TipoAlerta alerta) {
		this.repositorioUsuarios.informarUsuariosDe(fecha, alerta);
	}
	
	public List<TipoAlerta> obtenerAlertasParaLaFecha(Calendar fecha) {
		return new EstadoDelClima(this.proveedorClima, fecha).getAlertas();
	}

}
