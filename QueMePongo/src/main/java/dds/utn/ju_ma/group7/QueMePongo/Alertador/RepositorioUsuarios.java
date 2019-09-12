package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.Calendar;
import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Usuario.InteresEnNotificaciones;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioPremium;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioGratis;

public abstract class RepositorioUsuarios {

	protected abstract List<UsuarioPremium> todosLosUsuarios();
	
	protected abstract void almacenar(UsuarioPremium usuario);

	public UsuarioPremium instanciarUsuarioGratis(List<InteresEnNotificaciones> notificadores) {
		UsuarioGratis usuarioGratis = new UsuarioGratis(notificadores);
		this.almacenar(usuarioGratis);
		return usuarioGratis;
	}

	public UsuarioPremium instanciarUsuarioPremium(List<InteresEnNotificaciones> notificadores) {
		UsuarioPremium usuarioPremium = new UsuarioPremium(notificadores);
		this.almacenar(usuarioPremium);
		return usuarioPremium;
	}

	public void informarUsuariosDe(Calendar fecha, TipoAlerta alerta) {
		this.todosLosUsuarios().forEach(usuario -> usuario.notificarAlerta(fecha, alerta));
	}

}
