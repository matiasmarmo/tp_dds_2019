package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.Calendar;
import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Usuario.InteresEnNotificaciones;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioGratis;

public abstract class RepositorioUsuarios {

	protected abstract List<Usuario> todosLosUsuarios();
	
	protected abstract void almacenar(Usuario usuario);

	public Usuario instanciarUsuarioGratis(List<InteresEnNotificaciones> notificadores) {
		UsuarioGratis usuarioGratis = new UsuarioGratis(notificadores);
		this.almacenar(usuarioGratis);
		return usuarioGratis;
	}

	public Usuario instanciarUsuarioPremium(List<InteresEnNotificaciones> notificadores) {
		Usuario usuarioPremium = new Usuario(notificadores);
		this.almacenar(usuarioPremium);
		return usuarioPremium;
	}

	public void informarUsuariosDe(Calendar fecha, TipoAlerta alerta) {
		this.todosLosUsuarios().forEach(usuario -> usuario.notificarAlerta(fecha, alerta));
	}

}
