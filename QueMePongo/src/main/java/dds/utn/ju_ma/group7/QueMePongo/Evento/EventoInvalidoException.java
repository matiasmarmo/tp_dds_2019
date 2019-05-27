package dds.utn.ju_ma.group7.QueMePongo.Evento;

public class EventoInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EventoInvalidoException(String mensaje) {
		super(mensaje);
	}
}