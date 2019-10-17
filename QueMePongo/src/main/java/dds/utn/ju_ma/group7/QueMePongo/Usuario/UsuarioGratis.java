package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.util.List;

import javax.persistence.Entity;

import dds.utn.ju_ma.group7.QueMePongo.Excepciones.GuardarropaInvalidoException;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.GuardarropaLimitado;

@Entity
public class UsuarioGratis extends Usuario {
	
	public UsuarioGratis() {}

	public UsuarioGratis(List<InteresEnNotificaciones> notificadores, String username, String password) {
		super(notificadores, username, password);
	}

	@Override
	protected void verificarGuardarropas(Guardarropa guardarropa) {
		if (!(guardarropa instanceof GuardarropaLimitado)) {
			throw new GuardarropaInvalidoException("Los usuarios gratuitos solo pueden usar guardarropas limitados");
		}
	}

}
