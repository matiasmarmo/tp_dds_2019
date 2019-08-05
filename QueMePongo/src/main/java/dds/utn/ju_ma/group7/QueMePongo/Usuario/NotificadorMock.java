package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.util.Calendar;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.TipoAlerta;
import dds.utn.ju_ma.group7.QueMePongo.Evento.EventoUnico;

public class NotificadorMock extends InteresEnNotificaciones {
	
	private boolean fueNotificado = false;
	private boolean fueNotificadoAlerta = false;
	
	public boolean getFueNotificado() {
		return this.fueNotificado;
	}
	
	public boolean getFueNotificadoAlerta() {
		return this.fueNotificadoAlerta;
	}

	@Override
	public void notificar(EventoUnico evento, String notificacion) {
		this.fueNotificado = true;
	}
	
	@Override
	public void notificarAlerta(Calendar fecha, TipoAlerta alerta) {
		this.fueNotificadoAlerta = true;
	}

}
