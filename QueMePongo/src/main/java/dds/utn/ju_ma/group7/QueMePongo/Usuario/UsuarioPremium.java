package dds.utn.ju_ma.group7.QueMePongo.Usuario;

import java.util.List;

import javax.persistence.Entity;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;

@Entity
public class UsuarioPremium extends Usuario {

	public UsuarioPremium(List<InteresEnNotificaciones> notificadores) {
		super(notificadores);
	}

	@Override
	protected void verificarGuardarropas(Guardarropa guardarropa) {}
}
