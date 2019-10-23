package dds.utn.ju_ma.group7.QueMePongo.Main;

import java.util.HashMap;
import java.util.Map;

import dds.utn.ju_ma.group7.QueMePongo.Web.AuthenticatedUser;
import dds.utn.ju_ma.group7.QueMePongo.Web.AuthenticationService;
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
		Map<String, Object> model = new HashMap<String, Object>();
		Long id = Long.parseLong(req.params("id"));
		model.put("prendas", user.getUsuario().obtenerGuardarropa(id).getPrendas());
		ModelAndView modelAndView = new ModelAndView(model, "listadoPrendas.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

}
