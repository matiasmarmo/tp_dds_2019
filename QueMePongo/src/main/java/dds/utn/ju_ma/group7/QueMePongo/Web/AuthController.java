package dds.utn.ju_ma.group7.QueMePongo.Web;

import java.util.HashMap;
import java.util.Map;

import dds.utn.ju_ma.group7.QueMePongo.Excepciones.InvalidLoginException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class AuthController {
	
	private AuthenticationService authService;

	public AuthController(AuthenticationService authService) {
		this.authService = authService;
	}
	
	public String loginGet(Request req, Response res) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(null, "inicioSesion.hbs"));
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
			Map<String, String> model = new HashMap<String, String>();
			model.put("loginError", e.getMessage());
			return new HandlebarsTemplateEngine().render(new ModelAndView(model, "inicioSesion.hbs"));
		}
		res.cookie("quemepongo-auth-token", authenticatedUser.getAccessToken().toString());
		res.redirect("/quemepongo/guardarropas");
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
