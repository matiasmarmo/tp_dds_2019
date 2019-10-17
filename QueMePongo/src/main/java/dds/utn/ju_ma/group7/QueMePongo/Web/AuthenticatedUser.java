package dds.utn.ju_ma.group7.QueMePongo.Web;

import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class AuthenticatedUser {
	
	private String accessToken;
	private Usuario usuario;
	
	public AuthenticatedUser(String accessToken, Usuario usuario) {
		super();
		this.accessToken = accessToken;
		this.usuario = usuario;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	
	public void clear() {
		this.accessToken = null;
	}
}
