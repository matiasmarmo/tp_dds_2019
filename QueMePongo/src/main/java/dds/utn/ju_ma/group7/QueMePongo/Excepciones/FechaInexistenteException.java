package dds.utn.ju_ma.group7.QueMePongo.Excepciones;

public class FechaInexistenteException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public FechaInexistenteException(String mensaje) {
		super(mensaje);
	}
}
