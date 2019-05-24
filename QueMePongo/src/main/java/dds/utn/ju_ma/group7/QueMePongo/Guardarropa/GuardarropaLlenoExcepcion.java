package dds.utn.ju_ma.group7.QueMePongo.Guardarropa;

public class GuardarropaLlenoExcepcion extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public GuardarropaLlenoExcepcion(String mensaje) {
		super(mensaje);
	}
}
