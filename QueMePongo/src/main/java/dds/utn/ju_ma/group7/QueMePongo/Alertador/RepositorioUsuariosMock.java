package dds.utn.ju_ma.group7.QueMePongo.Alertador;

import java.util.ArrayList;
import java.util.List;

import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioPremium;

public class RepositorioUsuariosMock extends RepositorioUsuarios {
	
	private List<UsuarioPremium> usuarios;
	
	public RepositorioUsuariosMock() {
		this.usuarios = new ArrayList<UsuarioPremium>();
	}

	@Override
	protected List<UsuarioPremium> todosLosUsuarios() {
		return this.usuarios;
	}

	@Override
	protected void almacenar(UsuarioPremium usuario) {
		this.usuarios.add(usuario);
	}

	
	
}
