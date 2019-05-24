package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;

public class UsuarioGratis extends Usuario {
	
	protected Guardarropa instanciarGuardarropas(List<Prenda> prendas) {
		GuardarropaLimitado nuevoGuardarropa = new GuardarropaLimitado();
		nuevoGuardarropa.agregarPrendas(prendas);
		return nuevoGuardarropa;
	}
}
