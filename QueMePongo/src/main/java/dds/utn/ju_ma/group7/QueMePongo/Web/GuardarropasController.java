package dds.utn.ju_ma.group7.QueMePongo.Web;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class GuardarropasController {
	
	private AuthenticationService authService;

	public GuardarropasController(AuthenticationService authService) {
		this.authService = authService;
	}
	
	public String listarGuardarropas(Request req, Response res) {
		AuthenticatedUser user = this.authService
				.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		ModelAndView modelAndView = new ModelAndView(user.getUsuario(), "listadoGuardarropas.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

	public String listarPrendas(Request req, Response res) {
		AuthenticatedUser user = this.authService
				.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		Long id = Long.parseLong(req.params("id"));
		return new HandlebarsViewBuilder()
				.attribute("prendas", user.getUsuario().obtenerGuardarropa(id).getPrendas())
				.view("listadoPrendas.hbs")
				.render();
	}

}
