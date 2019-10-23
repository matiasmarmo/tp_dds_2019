package dds.utn.ju_ma.group7.QueMePongo.Main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Color;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.PrendaBuilder;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoPrenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoTela;
import dds.utn.ju_ma.group7.QueMePongo.Web.AuthenticatedUser;
import dds.utn.ju_ma.group7.QueMePongo.Web.AuthenticationService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class PrendasController implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps {
	
	private AuthenticationService authService;
	
	public PrendasController(AuthenticationService authService) {
		this.authService = authService;
	}
	
	public String seleccionarGuardarropas(Request req, Response res) {
		AuthenticatedUser user = this.authService
				.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		ModelAndView modelAndView = new ModelAndView(user.getUsuario(), "altaPrenda/seleccionGuardarropas.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	public String guardarropasSeleccionado(Request req, Response res) {
		String idGuardarropa = req.queryParams("idGuardarropa");
		req.session().attribute("idGuardarropa", idGuardarropa);
		Map<String, List<String>> model = new HashMap<String, List<String>>();
		model.put("tiposPrenda", Arrays.asList(TipoPrenda.values()).stream().map(value -> value.toString())
				.collect(Collectors.toList()));
		ModelAndView modelAndView = new ModelAndView(model, "altaPrenda/tipoPrenda.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}


	public String postTipoPrenda(Request req, Response res) {
		req.session(true).attribute("tipoPrenda", req.queryParams("tipoPrenda"));
		Map<String, List<String>> model = new HashMap<String, List<String>>();
		model.put("tiposTela",
				Arrays.asList(TipoTela.values()).stream().map(value -> value.toString()).collect(Collectors.toList()));
		ModelAndView modelAndView = new ModelAndView(model, "altaPrenda/tipoTela.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

	public String postTipoTela(Request req, Response res) {
		req.session().attribute("tipoTela", req.queryParams("tipoTela"));
		ModelAndView modelAndView = new ModelAndView(null, "altaPrenda/color.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

	public String postColor(Request req, Response res) {
		req.session().attribute("colorPrimario", req.queryParams("colorPrimario"));
		req.session().attribute("colorSecundario", req.queryParams("colorSecundario"));
		req.session().attribute("tieneSecundario", req.queryParams("tieneSecundario"));
		Map<String, String> model = new HashMap<String, String>();
		model.put("tipoPrenda", req.session().attribute("tipoPrenda").toString());
		model.put("tipoTela", req.session().attribute("tipoTela").toString());
		model.put("colorPrimario", req.queryParams("colorPrimario"));
		model.put("colorSecundario", req.queryParams("colorSecundario"));
		model.put("tieneSecundario", req.queryParams("tieneSecundario"));
		ModelAndView modelAndView = new ModelAndView(model, "altaPrenda/mostrarPrenda.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

	public String postPrendaLista(Request req, Response res) {
		AuthenticatedUser user = this.authService
				.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		Guardarropa guardarropa = user.getUsuario().obtenerGuardarropa(Long.parseLong(req.session().attribute("idGuardarropa")));
		withTransaction(() -> {
			TipoPrenda tipoPrenda = TipoPrenda.valueOf(req.session().attribute("tipoPrenda"));
			TipoTela tipoTela = TipoTela.valueOf(req.session().attribute("tipoTela"));
			Color colorPrimario = Color.hexToRgb(req.session().attribute("colorPrimario"));
			PrendaBuilder prendaBuilder = new PrendaBuilder();
			prendaBuilder.setTipoPrenda(tipoPrenda).setTipoTela(tipoTela).setColorPrimario(colorPrimario);
			if (req.session().attribute("tieneSecundario") != null) {
				Color colorSecundario = Color.hexToRgb(req.session().attribute("colorSecundario"));
				prendaBuilder.setColorSecundario(colorSecundario);
			}
			Prenda prenda = prendaBuilder.crearPrenda();
			guardarropa.agregarPrenda(prenda);
			persist(guardarropa);
		});

		ModelAndView modelAndView = new ModelAndView(null, "altaPrenda/prendaCreada.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

}
