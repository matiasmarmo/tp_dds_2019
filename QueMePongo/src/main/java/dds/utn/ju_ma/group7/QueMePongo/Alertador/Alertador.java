package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.Calendar;

import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.ProveedorClima;

public class Alertador {
	
	public void comprobarAlertas(Calendar fecha, ProveedorClima proveedor) {
		// List<enum> = proveedor.getEstadoClima(fecha).obtenerAlertas()
	}
	
	public void informarDe(Calendar fecha, TipoAlerta alerta) {
		RepositorioUsuarios.informarUsuariosDe(fecha, alerta);
	}

}
