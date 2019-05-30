package dds.utn.ju_ma.group7.QueMePongo.Excepciones;

public class ImagenInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ImagenInvalidaException(String mensaje) {
		super(mensaje);
	}
}
