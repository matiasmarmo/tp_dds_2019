package dds.utn.ju_ma.group7.QueMePongo.Web;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dds.utn.ju_ma.group7.QueMePongo.Evento.Evento;
import dds.utn.ju_ma.group7.QueMePongo.Evento.EventoUnico;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventosPersistente;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Sugerencia;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class EventosController implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps {

	private AuthenticationService authService;

	public EventosController(AuthenticationService authService) {
		this.authService = authService;
	}

	public String listarEventos(Request req, Response res) {
		AuthenticatedUser user = this.authService
				.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		RepositorioEventosPersistente repoEventos = new RepositorioEventosPersistente();
		List<Evento> eventos = repoEventos.eventosDelUsuario(user.getUsuario());
		eventos.forEach(evento -> evento.transformarFechaAString());
		return new HandlebarsViewBuilder().attribute("eventos", eventos).view("listadoEventos.hbs").render();
	}

	public String listarSugerenciasDeUnEvento(Request req, Response res) {
		Long idEvento = Long.parseLong(req.params("id"));
		RepositorioEventosPersistente repoEventos = new RepositorioEventosPersistente();
		Evento evento = repoEventos.getEventoPorId(idEvento);
		List<Sugerencia> sugerencias = evento.getSugerencias();
		return new HandlebarsViewBuilder().attribute("sugerencias", sugerencias).attribute("evento", evento)
				.view("sugerencias/listadoSugerencias.hbs").render();
	}

	public String ejecutarAccionSugerencia(Request req, Response res) {
		String idEventoString = req.params("idEvento");
		Long idEvento = Long.parseLong(idEventoString);
		Long idSugerencia = Long.parseLong(req.params("idSugerencia"));
		RepositorioEventosPersistente repoEventos = new RepositorioEventosPersistente();
		Evento evento = repoEventos.getEventoPorId(idEvento);
		Sugerencia sugerenciaAceptada = evento.getSugerenciaPorId(idSugerencia);
		withTransaction(() -> {
			sugerenciaAceptada.aceptar();
			evento.getSugerencias().stream().filter(sugerencia -> sugerencia != sugerenciaAceptada)
					.forEach(sugerencia -> sugerencia.rechazar());
		});
		return new HandlebarsViewBuilder().view("sugerencias/sugerenciaAceptada.hbs").render();
	}

	public String listarSugerenciasAceptadas(Request req, Response res) {
		AuthenticatedUser user = this.authService
				.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		RepositorioEventosPersistente repoEventos = new RepositorioEventosPersistente();
		List<Sugerencia> sugerenciasCalificables = repoEventos.sugerenciasCalificablesDelUsuario(user.getUsuario());
		return new HandlebarsViewBuilder().attribute("sugerencias", sugerenciasCalificables)
				.view("sugerencias/listadoSugerenciasCalificables.hbs").render();
	}

	public String ejecutarCalificacion(Request req, Response res) {
		AuthenticatedUser user = this.authService
				.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		Long idSugerencia = Long.parseLong(req.queryParams("idSugerencia"));
		Long calificacion = Long.parseLong(req.queryParams("calificacion"));
		RepositorioEventosPersistente repoEventos = new RepositorioEventosPersistente();
		Sugerencia sugerencia = repoEventos.obtenerSugerenciaDelUsuario(user.getUsuario(), idSugerencia);
		withTransaction(() -> {
			sugerencia.calificar(calificacion);
		});
		res.redirect("/quemepongo/eventos/sugerencias/calificacion");
		return "redirigiendo a sugerencias...";
	}

	public String altaEvento(Request req, Response res) {
		AuthenticatedUser user = this.authService
				.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		String nombreEvento = req.queryParams("nombreEvento");
		String guardarropas = req.queryParams("guardarropas");
		String fechaEvento = req.queryParams("fechaEvento");
		String confirmacion = req.queryParams("confirmacion");
		HashMap<String, Object> viewModel = new HashMap<String, Object>();
		AltaEventoController controlador = new AltaEventoController();
		viewModel = controlador.controladorPantallas(viewModel);
		if (req.queryParams().isEmpty()) {
			viewModel.put("nombreEvento", nombreEvento);
			viewModel.put("visibilidadNombreEvento", "visible");
		} else {
			Boolean hayNombre = nombreEvento != null;
			Boolean hayGuardarropas = guardarropas != null;
			Boolean hayFechaEvento = fechaEvento != null;
			Boolean hayConfirmacion = confirmacion != null;
			if (hayNombre && !hayGuardarropas) {
				viewModel.put("nombreEvento", nombreEvento);
				viewModel.put("todosLosGuardarropas", user.getUsuario().getGuardarropas());
				viewModel.put("visibilidadGuardarropas", "visible");
			} else if (hayNombre && hayGuardarropas && !hayFechaEvento) {
				viewModel.put("nombreEvento", nombreEvento);
				viewModel.put("guardarropas", guardarropas);
				viewModel.put("todosLosGuardarropas", user.getUsuario().getGuardarropas());
				viewModel.put("visibilidadCalendario", "visible");
			} else if (hayNombre && hayGuardarropas && hayFechaEvento && !hayConfirmacion) {
				viewModel.put("nombreEvento", nombreEvento);
				viewModel.put("guardarropas", guardarropas);
				viewModel.put("nombreGuardarropas",
						user.getUsuario().obtenerGuardarropa(Long.parseLong(guardarropas)).getNombreGuardarropas());
				Boolean fechaCorrecta = controlador.esFechaValida(fechaEvento);
				viewModel.put("fechaEvento", fechaEvento);
				viewModel.put("todoOk", fechaCorrecta);
				viewModel.put("visibilidadConfirmacion", "visible");
			} else if (hayNombre && hayGuardarropas && hayFechaEvento && hayConfirmacion) {
				Usuario usuario = user.getUsuario();
				Guardarropa guardarropaSeleccionado = user.getUsuario()
						.obtenerGuardarropa(Long.parseLong(guardarropas));
				RepositorioEventosPersistente repositorioEventosPersistente = new RepositorioEventosPersistente();
				Calendar fecha = controlador.generarFecha(fechaEvento);
				EventoUnico eventoVerano = repositorioEventosPersistente.instanciarEventoUnico(usuario,
						guardarropaSeleccionado, fecha, nombreEvento);
				withTransaction(() -> {
					this.entityManager().persist(eventoVerano);
				});
				viewModel.put("visibilidadEventoOk", "visible");
			}
		}
		ModelAndView modelAndView = new ModelAndView(viewModel, "altaEvento.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
}
