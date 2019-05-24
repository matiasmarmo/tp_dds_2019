package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;

public class GuardarropaLimitado extends Guardarropa{

	private int limite;

	public GuardarropaLimitado(int limite) {
		this.limite = limite;
	}
	
	public void agregarPrenda(Prenda prenda) {
		if(this.prendas.size() >= this.limite) {
			new GuardarropaLlenoExcepcion("El Guardarropa esta lleno.");
		}
		this.prendas.add(prenda);
	}

}