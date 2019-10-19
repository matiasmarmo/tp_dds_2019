package dds.utn.ju_ma.group7.QueMePongo.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Evento;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventosPersistente;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Sugerencia;
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

public class QueMePongoController {
	
	private AuthenticationService authService;
	
	public QueMePongoController(AuthenticationService authService) {
		this.authService = authService;
	}
	
	public String loginGet(Request req, Response res) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(null, "inicioSesion.hbs"));
	}
	
	public void authFilter(Request req, Response res) {
		Long accessToken = req.session().attribute("auth-token");
		if(accessToken == null) {
			res.redirect("/login");
		}
		AuthenticatedUser user = this.authService.getAuthenticatedUser(accessToken);
		if(user == null) {
			res.redirect("/login");
		}
	}
	
	public String loginPost(Request req, Response res) {
		String username = req.queryParams("username");
		String password = req.queryParams("password");
		AuthenticatedUser authenticatedUser = this.authService.loginUser(username, password);
		req.session().attribute("auth-token", authenticatedUser.getAccessToken());
		res.redirect("/quemepongo/guardarropas");
		return null;
	}
	
	public String logout(Request req, Response res) {
		AuthenticatedUser user = this.authService.getAuthenticatedUser(req.session().attribute("auth-token"));
		this.authService.logoutUser(user);
		req.session().removeAttribute("auth-token");
		res.redirect("/login");
		return null;
	}

	public String listarGuardarropas(Request req, Response res) {
		AuthenticatedUser user = this.authService.getAuthenticatedUser(req.session().attribute("auth-token"));
		ModelAndView modelAndView = new ModelAndView(user.getUsuario(), "listadoGuardarropas.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

	public String listarPrendas(Request req, Response res) {
		AuthenticatedUser user = this.authService.getAuthenticatedUser(req.session().attribute("auth-token"));
		Map<String, Object> model = new HashMap<String, Object>();
		Long id = Long.parseLong(req.params("id"));
		model.put("prendas", user.getUsuario().obtenerGuardarropa(id).getPrendas());
    	ModelAndView modelAndView = new ModelAndView(model, "listadoPrendas.hbs");
    	return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	public String altaPrendas(Request req, Response res) {
		Map<String, List<String>> model = new HashMap<String, List<String>>();
		model.put("tiposPrenda", 
				Arrays.asList(TipoPrenda.values()).stream().map(value -> value.toString()).collect(Collectors.toList())
				);
		ModelAndView modelAndView = new ModelAndView(model, "altaPrenda/tipoPrenda.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	public String postTipoPrenda(Request req, Response res) {
		req.session(true).attribute("tipoPrenda", req.queryParams("tipoPrenda"));
		Map<String, List<String>> model = new HashMap<String, List<String>>();
		model.put("tiposTela", 
				Arrays.asList(TipoTela.values()).stream().map(value -> value.toString()).collect(Collectors.toList())
				);
		ModelAndView modelAndView = new ModelAndView(model, "altaPrenda/tipoTela.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	public String postTipoTela(Request req, Response res) {
		req.session().attribute("tipoTela", req.queryParams("tipoTela"));
		ModelAndView modelAndView = new ModelAndView(null, "altaPrenda/color.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	public String postColor(Request req, Response res) {
		// Construir prenda
		System.out.println(req.queryParams("colorPrimario"));
		System.out.println(req.queryParams("colorSecundario"));
		System.out.println(req.queryParams("tieneSecundario"));
		return "Tu prenda esta construida";
	}
	
	public String listarEventos(Request req, Response res) {
    	RepositorioEventosPersistente repoEventos = new RepositorioEventosPersistente();
    	repoEventos.instanciarEventoUnico(null, null, null, "evento 1");
    	repoEventos.instanciarEventoUnico(null, null, null, "evento 2");
    	List<Evento> eventos = repoEventos.todosLosEventos();
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("eventos", eventos);
    	ModelAndView modelAndView = new ModelAndView(model, "calificarSugerencia.hbs");
        return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	public String listarSugerencias(Request req, Response res) {
    	String idEvento = req.queryParams("id");
    	PrendaBuilder remeraNegraBuilder = new PrendaBuilder();
    	remeraNegraBuilder.setTipoPrenda(TipoPrenda.REMERA).setTipoTela(TipoTela.ALGODON).setColorPrimario(new Color(0, 0, 255));
    	Prenda remeraNegra = remeraNegraBuilder.crearPrenda();
    	PrendaBuilder shortBuilder = new PrendaBuilder();
    	shortBuilder.setTipoPrenda(TipoPrenda.SHORT).setTipoTela(TipoTela.DRY_FIT).setColorPrimario(new Color(0, 0, 0));
    	Prenda unShort = shortBuilder.crearPrenda();
    	List<Prenda> prendasSupPobre = new ArrayList<Prenda>();
    	prendasSupPobre.add(remeraNegra);
    	prendasSupPobre.add(unShort);
    	Atuendo atuendo = new Atuendo(prendasSupPobre, remeraNegra, remeraNegra, remeraNegra);
    	Sugerencia sugerencia = new Sugerencia(atuendo);
    	List<Sugerencia> sugerencias = Arrays.asList(sugerencia, sugerencia, sugerencia);
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("sugerencias", sugerencias);
    	model.put("idEvento", idEvento);
    	ModelAndView modelAndView = new ModelAndView(model, "listadoSugerencias.hbs");
        return new HandlebarsTemplateEngine().render(modelAndView);
    }

}
