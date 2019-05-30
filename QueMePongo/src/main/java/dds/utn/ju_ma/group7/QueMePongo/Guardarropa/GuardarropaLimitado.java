package dds.utn.ju_ma.group7.QueMePongo.Guardarropa;

import dds.utn.ju_ma.group7.QueMePongo.Excepciones.GuardarropaLlenoException;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;

public class GuardarropaLimitado extends Guardarropa{
	
	private static final int limite = 50;
	
	@Override
	public void agregarPrenda(Prenda prenda) {
		if(this.prendas.size() >= GuardarropaLimitado.limite) {
			throw new GuardarropaLlenoException("El Guardarropa esta lleno.");
		}
		this.prendas.add(prenda);
	}

}