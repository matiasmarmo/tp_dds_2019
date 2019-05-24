package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;

public class UsuarioPremium extends Usuario {

	protected Guardarropa instanciarGuardarropas(List<Prenda> prendas) {		
		Guardarropa nuevoGuardarropa = new Guardarropa();
		nuevoGuardarropa.agregarPrendas(prendas);
		return nuevoGuardarropa;
	}
}
