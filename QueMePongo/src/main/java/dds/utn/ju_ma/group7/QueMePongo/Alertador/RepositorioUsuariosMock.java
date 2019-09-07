package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.ArrayList;
import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class RepositorioUsuariosMock extends RepositorioUsuarios {
	
	private List<Usuario> usuarios;
	
	public RepositorioUsuariosMock() {
		this.usuarios = new ArrayList<Usuario>();
	}

	@Override
	protected List<Usuario> todosLosUsuarios() {
		return this.usuarios;
	}

	@Override
	protected void almacenar(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	
	
}
