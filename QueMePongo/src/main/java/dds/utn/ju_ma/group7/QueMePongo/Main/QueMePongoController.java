package dds.utn.ju_ma.group7.QueMePongo.Main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

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

public class QueMePongoController implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps{
	
	private AuthenticationService authService;
	
	public QueMePongoController(AuthenticationService authService) {
		this.authService = authService;
	}
	
	public String loginGet(Request req, Response res) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(null, "inicioSesion.hbs"));
	}
	
	public void authFilter(Request req, Response res) {
		String accessTokenString = req.cookie("quemepongo-auth-token");
		if(accessTokenString == null) {
			res.redirect("/login");
		}
		Long accessTokenLong = Long.parseLong(accessTokenString);
		AuthenticatedUser user = this.authService.getAuthenticatedUser(accessTokenLong);
		if(user == null) {
			res.redirect("/login");
		}
	}
	
	public String loginPost(Request req, Response res) {
		String username = req.queryParams("username");
		String password = req.queryParams("password");
		AuthenticatedUser authenticatedUser = this.authService.loginUser(username, password);
		res.cookie("quemepongo-auth-token", authenticatedUser.getAccessToken().toString());
		res.redirect("/quemepongo/guardarropas");
		return null;
	}
	
	public String logout(Request req, Response res) {
		AuthenticatedUser user = this.authService.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		this.authService.logoutUser(user);
		res.removeCookie("quemepongo-auth-token");
		res.redirect("/login");
		return null;
	}

	public String listarGuardarropas(Request req, Response res) {
		AuthenticatedUser user = this.authService.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		ModelAndView modelAndView = new ModelAndView(user.getUsuario(), "listadoGuardarropas.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

	public String listarPrendas(Request req, Response res) {
		AuthenticatedUser user = this.authService.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
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
		withTransaction(() -> {
			TipoPrenda tipoPrenda = TipoPrenda.valueOf(req.session().attribute("tipoPrenda"));
			TipoTela tipoTela = TipoTela.valueOf(req.session().attribute("tipoTela"));
			Color colorPrimario = Color.hexToRgb(req.queryParams("colorPrimario"));
			PrendaBuilder prendaBuilder = new PrendaBuilder();
			prendaBuilder.setTipoPrenda(tipoPrenda).setTipoTela(tipoTela).setColorPrimario(colorPrimario);
			if(Boolean.valueOf(req.queryParams("tieneSecundario"))) {
				Color colorSecundario = Color.hexToRgb(req.queryParams("colorSecundario"));
				prendaBuilder.setColorSecundario(colorSecundario);
			}
	    	Prenda prenda = prendaBuilder.crearPrenda();
	    	persist(prenda);
		});
		return "Tu prenda esta construida";
	}
	
	public String listarEventos(Request req, Response res) {
		AuthenticatedUser user = this.authService.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
    	RepositorioEventosPersistente repoEventos = new RepositorioEventosPersistente();
    	List<Evento> eventos = repoEventos.eventosDelUsuario(user.getUsuario());
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("eventos", eventos);
    	ModelAndView modelAndView = new ModelAndView(model, "calificarSugerencia.hbs");
        return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	public String listarSugerencias(Request req, Response res) {
		RepositorioEventosPersistente repoEventos = new RepositorioEventosPersistente();
		Long idEvento = Long.parseLong(req.queryParams("id"));
    	Evento evento = repoEventos.obtenerEventoPorId(idEvento);
    	List<Sugerencia> sugerencias = evento.getSugerencias();
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("sugerencias", sugerencias);
    	model.put("descripcionEvento", evento.getDescripcion());
    	ModelAndView modelAndView = new ModelAndView(model, "listadoSugerencias.hbs");
        return new HandlebarsTemplateEngine().render(modelAndView);
    }
	
	public String listarSugerenciasAceptadas(Request req, Response res) {
		AuthenticatedUser user = this.authService.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		RepositorioEventosPersistente repoEventos = new RepositorioEventosPersistente();
		List<Sugerencia> sugerenciasAceptadas = repoEventos.sugerenciasAceptadasDelUsuario(user.getUsuario());
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("sugerencias", sugerenciasAceptadas);
    	ModelAndView modelAndView = new ModelAndView(model, "listadoSugerenciasAceptadas.hbs");
        return new HandlebarsTemplateEngine().render(modelAndView);   
	}
}
