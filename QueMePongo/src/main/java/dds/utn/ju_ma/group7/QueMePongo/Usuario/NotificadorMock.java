package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import dds.utn.ju_ma.group7.QueMePongo.Evento.EventoUnico;

public class NotificadorMock implements InteresEnNotificaciones {
	
	private boolean fueNotificado = false;
	
	public boolean getFueNotificado() {
		return this.fueNotificado;
	}

	@Override
	public void notificar(EventoUnico evento, String notificacion) {
		this.fueNotificado = true;
	}

}
