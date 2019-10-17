package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import dds.utn.ju_ma.group7.QueMePongo.Usuario.InteresEnNotificaciones;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioGratis;

public abstract class RepositorioUsuarios {

	protected abstract List<Usuario> todosLosUsuarios();

	protected abstract void almacenar(Usuario usuario);

	public Usuario instanciarUsuarioGratis(List<InteresEnNotificaciones> notificadores, String username,
			String password) {
		UsuarioGratis usuarioGratis = new UsuarioGratis(notificadores, username, password);
		this.almacenar(usuarioGratis);
		return usuarioGratis;
	}

	public Usuario instanciarUsuarioPremium(List<InteresEnNotificaciones> notificadores, String username,
			String password) {
		Usuario usuarioPremium = new Usuario(notificadores, username, password);
		this.almacenar(usuarioPremium);
		return usuarioPremium;
	}

	public void informarUsuariosDe(Calendar fecha, TipoAlerta alerta) {
		this.todosLosUsuarios().forEach(usuario -> usuario.notificarAlerta(fecha, alerta));
	}

	public Usuario obtenerUsuarioPorId(Long id) {
		return this.todosLosUsuarios().stream().filter(usuario -> usuario.getId() == id).collect(Collectors.toList())
				.get(0);
	}

	public Usuario obtenerUsuarioParaLogear(String username, String password) {
		return this.todosLosUsuarios().parallelStream()
				.filter(usuario -> usuario.getUsername().equals(username) && usuario.getPassword().equals(password))
				.findFirst().get();
	}

}
