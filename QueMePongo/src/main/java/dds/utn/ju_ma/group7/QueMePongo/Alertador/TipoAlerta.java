package dds.utn.ju_ma.group7.QueMePongo.Alertador;

public enum TipoAlerta {
	TORMENTA, NIEVE;
	
	private String descripcion;
	
	static {
		TORMENTA.descripcion = "Hay Tormenta!";
		NIEVE.descripcion = "Hay Nieve!";
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}

}
