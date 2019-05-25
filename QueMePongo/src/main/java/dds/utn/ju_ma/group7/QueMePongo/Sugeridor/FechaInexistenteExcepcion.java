package dds.utn.ju_ma.group7.QueMePongo.Sugeridor;

public class FechaInexistenteExcepcion extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public FechaInexistenteExcepcion(String mensaje) {
		super(mensaje);
	}
}
