package dds.utn.ju_ma.group7.QueMePongo.Excepciones;

public class NotificationError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotificationError(String mensaje) {
		super(mensaje);
	}
	
}
