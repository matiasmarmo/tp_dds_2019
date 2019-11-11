package dds.utn.ju_ma.group7.QueMePongo.Web;

import java.util.List;
import java.util.NoSuchElementException;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.RepositorioUsuarios;
import dds.utn.ju_ma.group7.QueMePongo.Excepciones.InvalidLoginException;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class AuthenticationService implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps {
	
	private RepositorioUsuarios repositorioUsuarios;
	
	public AuthenticationService(RepositorioUsuarios repositorio) {
		this.repositorioUsuarios = repositorio;
	}

	private void addUserToAuthenticated(AuthenticatedUser usuario) {
		withTransaction(() -> {
			this.persist(usuario);
		});
	}

	public void logoutUser(AuthenticatedUser usuario) {
		withTransaction(() -> {
			this.remove(usuario);
		});
	}

	public AuthenticatedUser getAuthenticatedUser(Long accessToken) {
		List<AuthenticatedUser> userList = this.entityManager()
				.createQuery("select u from AuthenticatedUser u join u.token t where t.token = :token", AuthenticatedUser.class)
				.setParameter("token", accessToken)
				.getResultList();
		return userList.size() == 0 ? null : userList.get(0);
	}

	public AuthenticatedUser loginUser(String username, String password) throws InvalidLoginException {
		Usuario userToLogin;
		try {
			userToLogin = this.repositorioUsuarios.obtenerUsuarioParaLogear(username,
					password);
		} catch (NoSuchElementException e) {
			throw new InvalidLoginException(username, "El usuario " + username + " no existe o la contraseña es incorrecta"); 
		}
		return this.authenticateUser(userToLogin);
	}

	private AuthenticatedUser authenticateUser(Usuario usuario) {
		AuthToken token = new AuthToken();
		AuthenticatedUser newUser = new AuthenticatedUser(token, usuario);
		this.addUserToAuthenticated(newUser);
		return newUser;
	}

}
