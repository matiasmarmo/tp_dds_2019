package dds.utn.ju_ma.group7.QueMePongo.Main;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dds.utn.ju_ma.group7.QueMePongo.Evento.Evento;
import dds.utn.ju_ma.group7.QueMePongo.Evento.EventoUnico;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventosPersistente;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Sugerencia;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Color;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.PrendaBuilder;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoPrenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoTela;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;
import dds.utn.ju_ma.group7.QueMePongo.Web.AuthenticatedUser;
import dds.utn.ju_ma.group7.QueMePongo.Web.AuthenticationService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class QueMePongoController implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps {

	private AuthenticationService authService;

	public QueMePongoController(AuthenticationService authService) {
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
		AuthenticatedUser authenticatedUser = this.authService.loginUser(username, password);
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

	public String postTipoPrenda(Request req, Response res) {
		Map<String, List<String>> model = new HashMap<String, List<String>>();
		model.put("tiposPrenda", Arrays.asList(TipoPrenda.values()).stream().map(value -> value.toString())
				.collect(Collectors.toList()));
		ModelAndView modelAndView = new ModelAndView(model, "altaPrenda/tipoPrenda.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

	public String postTipoTela(Request req, Response res) {
		req.session(true).attribute("tipoPrenda", req.queryParams("tipoPrenda"));
		Map<String, List<String>> model = new HashMap<String, List<String>>();
		model.put("tiposTela",
				Arrays.asList(TipoTela.values()).stream().map(value -> value.toString()).collect(Collectors.toList()));
		ModelAndView modelAndView = new ModelAndView(model, "altaPrenda/tipoTela.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

	public String postColor(Request req, Response res) {
		req.session().attribute("tipoTela", req.queryParams("tipoTela"));
		ModelAndView modelAndView = new ModelAndView(null, "altaPrenda/color.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

	public String confirmacion(Request req, Response res) {
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
			persist(prenda);
		});

		ModelAndView modelAndView = new ModelAndView(null, "altaPrenda/prendaCreada.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

	public String listarEventos(Request req, Response res) {
		AuthenticatedUser user = this.authService
				.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		RepositorioEventosPersistente repoEventos = new RepositorioEventosPersistente();
		List<Evento> eventos = repoEventos.eventosDelUsuario(user.getUsuario());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("eventos", eventos);
		ModelAndView modelAndView = new ModelAndView(model, "sugerencias/calificarSugerencia.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

	public String listarSugerencias(Request req, Response res) {
		Long idEvento = Long.parseLong(req.queryParams("id"));
		boolean huboSugerenciaAceptada = req.queryParams("huboSugerenciaAceptada") != null;
		boolean huboSugerenciaRechazada = req.queryParams("huboSugerenciaRechazada") != null;
		RepositorioEventosPersistente repoEventos = new RepositorioEventosPersistente();
		Evento evento = repoEventos.getEventoPorId(idEvento);
		List<Sugerencia> sugerencias = evento.getSugerenciasPendientes(Calendar.getInstance());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sugerencias", sugerencias);
		model.put("evento", evento);
		model.put("huboSugerenciaAceptada", huboSugerenciaAceptada);
		model.put("huboSugerenciaRechazada", huboSugerenciaRechazada);
		ModelAndView modelAndView = new ModelAndView(model, "sugerencias/listadoSugerencias.hbs");
		System.out.println(sugerencias.size());
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	public String ejecutarAccionSugerencia(Request req, Response res) {
		String idEventoString = req.queryParams("idEvento");
		Long idEvento = Long.parseLong(idEventoString);
		Long idSugerencia = Long.parseLong(req.queryParams("idSugerencia"));
		RepositorioEventosPersistente repoEventos = new RepositorioEventosPersistente();
		Evento evento = repoEventos.getEventoPorId(idEvento);
		Sugerencia sugerencia = evento.getSugerenciaPorId(idSugerencia);
		String accion = req.queryParams("accion");
		if (accion.compareTo("aceptar") == 0) {
			sugerencia.aceptar();
			res.redirect("/quemepongo/eventos/sugerencias?id=" + idEventoString + "&huboSugerenciaAceptada=true");
		} else if (accion.compareTo("rechazar") == 0) {
			sugerencia.rechazar();
			res.redirect("/quemepongo/eventos/sugerencias?id=" + idEventoString + "&huboSugerenciaRechazada=true");
		}
		return "redirigiendo a sugerencias...";
	}
	
	public String rechazarSugerenciasPendientes(Request req, Response res) {
		Long idEvento = Long.parseLong(req.queryParams("idEvento"));
		RepositorioEventosPersistente repoEventos = new RepositorioEventosPersistente();
    	Evento evento = repoEventos.getEventoPorId(idEvento);
    	evento.rechazarSugerenciasPendientes();
		ModelAndView modelAndView = new ModelAndView(null, "sugerencias/finalizacionSugerencias.hbs");
        return new HandlebarsTemplateEngine().render(modelAndView);
	}

	public String listarSugerenciasAceptadas(Request req, Response res) {
		AuthenticatedUser user = this.authService
				.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		RepositorioEventosPersistente repoEventos = new RepositorioEventosPersistente();
		List<Sugerencia> sugerenciasAceptadas = repoEventos.sugerenciasAceptadasDelUsuario(user.getUsuario());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("sugerencias", sugerenciasAceptadas);
		ModelAndView modelAndView = new ModelAndView(model, "sugerencias/listadoSugerenciasAceptadas.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	public String altaEvento(Request req, Response res) {
		AuthenticatedUser user = this.authService
				.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		String nombreEvento = req.queryParams("nombreEvento");
		String guardarropas = req.queryParams("guardarropas");
		String fechaEvento = req.queryParams("fechaEvento");
		String confirmacion = req.queryParams("confirmacion");
		HashMap<String, Object> viewModel = new HashMap<String, Object>();
		viewModel = altaEventoController(viewModel);
		if(req.queryParams().isEmpty()) {
			viewModel.put("nombreEvento", nombreEvento);
			viewModel.put("hayNombre", 0);
			viewModel.put("visibilidadNombreEvento", "visible");
			viewModel.put("guardarropas", guardarropas);
			viewModel.put("hayGuardarropas", 0);
			viewModel.put("todosLosGuardarropas",user.getUsuario().getGuardarropas());
		}
		else {
			Boolean hayNombre = nombreEvento != null;
			Boolean hayGuardarropas = guardarropas != null;
			Boolean hayFechaEvento = fechaEvento != null;
			Boolean hayConfirmacion = confirmacion != null;
			if(hayNombre&&!hayGuardarropas) {
				viewModel.put("nombreEvento", nombreEvento);
				viewModel.put("todosLosGuardarropas",user.getUsuario().getGuardarropas());
				viewModel.put("visibilidadGuardarropas", "visible");
			}
			else if(hayNombre&&hayGuardarropas&&!hayFechaEvento){
				viewModel.put("nombreEvento", nombreEvento);
				viewModel.put("guardarropas", guardarropas);
				viewModel.put("todosLosGuardarropas",user.getUsuario().getGuardarropas());
				viewModel.put("visibilidadCalendario", "visible");
			}
			else if(hayNombre&&hayGuardarropas&&hayFechaEvento&&!hayConfirmacion){
				viewModel.put("nombreEvento", nombreEvento);
				viewModel.put("guardarropas", guardarropas);
				viewModel.put("nombreGuardarropas", user.getUsuario().obtenerGuardarropa(Long.parseLong(guardarropas)).getNombreGuardarropas());
				viewModel.put("fechaEvento", "2019-5-6");
				viewModel.put("todoOk",true);
				viewModel.put("visibilidadConfirmacion", "visible");
			}
			else if(hayNombre&&hayGuardarropas&&hayFechaEvento&&hayConfirmacion){
				Calendar fecha = Calendar.getInstance();
				fecha.setTime(new Date());
				Usuario usuario = user.getUsuario();
				Guardarropa guardarropaSeleccionado = user.getUsuario().obtenerGuardarropa(Long.parseLong(guardarropas));
				RepositorioEventosPersistente repositorioEventosPersistente = new RepositorioEventosPersistente();
				EventoUnico eventoVerano = repositorioEventosPersistente.instanciarEventoUnico(usuario, guardarropaSeleccionado,
						fecha,nombreEvento);
				withTransaction(() -> {
					this.entityManager().persist(eventoVerano);
				});
				viewModel.put("visibilidadEventoOk", "visible");
			}
		}
		ModelAndView modelAndView = new ModelAndView(viewModel, "altaEvento.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	public HashMap<String, Object> altaEventoController(HashMap<String, Object> viewModel) {
		viewModel.put("visibilidadNombreEvento", "hidden");
		viewModel.put("visibilidadGuardarropas", "hidden");
		viewModel.put("visibilidadCalendario", "hidden");
		viewModel.put("visibilidadConfirmacion", "hidden");
		viewModel.put("visibilidadEventoOk", "hidden");
		return viewModel;
	}
}
