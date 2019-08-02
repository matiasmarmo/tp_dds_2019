package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Excepciones.GuardarropaInvalidoException;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.GuardarropaLimitado;

public class UsuarioGratis extends Usuario {

	public UsuarioGratis(List<InteresEnNotificaciones> notificadores) {
		super(notificadores);
	}

	@Override
	protected void verificarGuardarropas(Guardarropa guardarropa) {
		if (!(guardarropa instanceof GuardarropaLimitado)) {
			throw new GuardarropaInvalidoException("Los usuarios gratuitos solo pueden usar guardarropas limitados");
		}
	}

}
