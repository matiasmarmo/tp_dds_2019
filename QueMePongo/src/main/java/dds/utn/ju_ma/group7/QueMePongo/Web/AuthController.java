package dds.utn.ju_ma.group7.QueMePongo.Web;

import dds.utn.ju_ma.group7.QueMePongo.Excepciones.InvalidLoginException;
import spark.Request;
import spark.Response;

public class AuthController {
	
	private AuthenticationService authService;

	public AuthController(AuthenticationService authService) {
		this.authService = authService;
	}
	
	public String loginGet(Request req, Response res) {
		return new HandlebarsViewBuilder().view("inicioSesion.hbs").render();
	}

	public void authFilter(Request req, Response res) {
		String accessTokenString = req.cookie("quemepongo-auth-token");
		if (accessTokenString == null) {
			res.redirect("/login");
		}
		Long accessTokenLong = Long.parseLong(accessTokenString);
		AuthenticatedUser user = this.authService.getAuthenticatedUser(accessTokenLong);
		if (user == null) {
			res.redirect("/login");
		}
	}

	public String loginPost(Request req, Response res) {
		String username = req.queryParams("username");
		String password = req.queryParams("password");
		AuthenticatedUser authenticatedUser;
		try {
			authenticatedUser = this.authService.loginUser(username, password);
		} catch (InvalidLoginException e) {
			return new HandlebarsViewBuilder()
					.attribute("loginError", e.getMessage())
					.view("inicioSesion.hbs")
					.render();
		}
		res.cookie("quemepongo-auth-token", authenticatedUser.getAccessToken().toString());
		res.redirect("/quemepongo/eventos");
		return null;
	}

	public String logout(Request req, Response res) {
		AuthenticatedUser user = this.authService
				.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		this.authService.logoutUser(user);
		res.removeCookie("quemepongo-auth-token");
		res.redirect("/login");
		return null;
	}

}
