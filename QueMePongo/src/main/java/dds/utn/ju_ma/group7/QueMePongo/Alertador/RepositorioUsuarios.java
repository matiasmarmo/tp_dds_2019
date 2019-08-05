package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.ArrayList;
import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Usuario.InteresEnNotificaciones;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioGratis;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioPremium;

public class RepositorioUsuarios {
	private static List<Usuario> usuarios = new ArrayList<>();
	
	public static Usuario instanciarUsuarioGratis(List<InteresEnNotificaciones> notificadores) {
		UsuarioGratis usuarioGratis = new UsuarioGratis(notificadores);
		RepositorioUsuarios.usuarios.add(usuarioGratis);
		return usuarioGratis;
	}
	
	public static Usuario instanciarUsuarioPremium(List<InteresEnNotificaciones> notificadores) {
		UsuarioPremium usuarioPremium = new UsuarioPremium(notificadores);
		RepositorioUsuarios.usuarios.add(usuarioPremium);
		return usuarioPremium;
	}

}
