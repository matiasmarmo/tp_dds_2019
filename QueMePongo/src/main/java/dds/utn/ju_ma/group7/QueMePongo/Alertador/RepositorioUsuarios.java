package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Usuario.InteresEnNotificaciones;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioGratis;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioPremium;

public class RepositorioUsuarios {
	private List<Usuario> usuarios = new ArrayList<>();
	
	private static RepositorioUsuarios instance;
	
	private RepositorioUsuarios() {}
	
	public static RepositorioUsuarios getInstance() {
		if(instance == null) {
			instance = new RepositorioUsuarios();
		}
		return instance;
	}
	
	public Usuario instanciarUsuarioGratis(List<InteresEnNotificaciones> notificadores) {
		UsuarioGratis usuarioGratis = new UsuarioGratis(notificadores);
		this.usuarios.add(usuarioGratis);
		return usuarioGratis;
	}
	
	public Usuario instanciarUsuarioPremium(List<InteresEnNotificaciones> notificadores) {
		UsuarioPremium usuarioPremium = new UsuarioPremium(notificadores);
		this.usuarios.add(usuarioPremium);
		return usuarioPremium;
	}
	
	public void informarUsuariosDe(Calendar fecha, TipoAlerta alerta) {
		this.usuarios.forEach(usuario -> usuario.notificarAlerta(fecha, alerta));
	}

}
