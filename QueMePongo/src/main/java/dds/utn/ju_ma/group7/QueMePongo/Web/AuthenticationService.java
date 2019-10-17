package dds.utn.ju_ma.group7.QueMePongo.Web;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.RepositorioUsuariosPersistente;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class AuthenticationService {

	private static AtomicInteger nextToken = new AtomicInteger(0);
	private static List<AuthenticatedUser> authenticatedUsers = new ArrayList<AuthenticatedUser>();
	private static RepositorioUsuariosPersistente repositorioUsuariosPersistente = new RepositorioUsuariosPersistente();

	private static String getNextAuthToken() {
		return Integer.toString(AuthenticationService.nextToken.getAndIncrement());
	}

	private static synchronized void addUserToAuthenticated(AuthenticatedUser usuario) {
		AuthenticationService.authenticatedUsers.add(usuario);
	}

	private static synchronized void removeUserFromAuthenticated(AuthenticatedUser usuario) {
		AuthenticationService.authenticatedUsers.remove(usuario);
	}

	private static synchronized AuthenticatedUser findAuthenticatedUser(String accessToken) {
		return AuthenticationService.authenticatedUsers.stream()
				.filter(user -> user.getAccessToken().equals(accessToken)).findFirst().get();
	}

	public static AuthenticatedUser loginUser(String username, String password) {
		Usuario userToLogin = AuthenticationService.repositorioUsuariosPersistente.obtenerUsuarioParaLogear(username,
				password);
		return AuthenticationService.authenticateUser(userToLogin);
	}

	private static AuthenticatedUser authenticateUser(Usuario usuario) {
		AuthenticatedUser newUser = new AuthenticatedUser(AuthenticationService.getNextAuthToken(), usuario);
		AuthenticationService.addUserToAuthenticated(newUser);
		return newUser;
	}

	public static void logoutUser(AuthenticatedUser usuario) {
		AuthenticationService.removeUserFromAuthenticated(usuario);
	}

	public static AuthenticatedUser getAuthenticatedUser(String accessToken) {
		return AuthenticationService.findAuthenticatedUser(accessToken);
	}

}
