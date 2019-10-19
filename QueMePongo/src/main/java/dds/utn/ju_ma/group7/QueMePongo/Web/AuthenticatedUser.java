package dds.utn.ju_ma.group7.QueMePongo.Web;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

@Entity
public class AuthenticatedUser {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	@Cascade(CascadeType.PERSIST)
	private AuthToken token;
	
	@OneToOne
	private Usuario usuario;
	
	public AuthenticatedUser() {}
	
	public AuthenticatedUser(AuthToken token, Usuario usuario) {
		this.token = token;
		this.usuario = usuario;
	}

	public Long getAccessToken() {
		return this.token.getToken();
	}


	public Usuario getUsuario() {
		return usuario;
	}
	
	
}
