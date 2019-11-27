package dds.utn.ju_ma.group7.QueMePongo.Web;

import java.util.Calendar;
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
import spark.Request;
import spark.Response;

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
	
	public String altaNuevoEvento(Request req, Response res) {
		return new HandlebarsViewBuilder()
				.attribute("nombreEvento", "")
				.attribute("visibilidadNombreEvento", "visible")
				.attribute("visibilidadGuardarropas", "hidden")
				.attribute("visibilidadCalendario", "hidden")
				.attribute("visibilidadConfirmacion", "hidden")
				.attribute("visibilidadEventoOk", "hidden")
				.view("altaEvento.hbs")
				.render();
	}
	
	public String postNombreEvento(Request req, Response res) {
		String nombreEvento = req.queryParams("nombreEvento");
		if(nombreEvento == null || nombreEvento.isEmpty()) {
			res.redirect("/quemepongo/eventos/new");
			return "";
		}
		req.session().attribute("nombreEvento", nombreEvento);
		
		AuthenticatedUser user = this.authService
				.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		
		return new HandlebarsViewBuilder()
				.attribute("nombreEvento", nombreEvento)
				.attribute("todosLosGuardarropas", user.getUsuario().getGuardarropas())
				.attribute("visibilidadNombreEvento", "hidden")
				.attribute("visibilidadGuardarropas", "visible")
				.attribute("visibilidadCalendario", "hidden")
				.attribute("visibilidadConfirmacion", "hidden")
				.attribute("visibilidadEventoOk", "hidden")
				.view("altaEvento.hbs")
				.render();
	}
	
	public String postGuardarropasEvento(Request req, Response res) {
		String guardarropas = req.queryParams("guardarropas");
		req.session().attribute("guardarropas", guardarropas);
		
		res.redirect("/quemepongo/eventos/new/fecha");
		return "";
	}
	
	public String getFormFechaEvento(Request req, Response res) {
		AuthenticatedUser user = this.authService
				.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		return new HandlebarsViewBuilder()
				.attribute("nombreEvento", req.session().attribute("nombreEvento"))
				.attribute("guardarropas", req.session().attribute("guardarropas"))
				.attribute("todosLosGuardarropas", user.getUsuario().getGuardarropas())
				.attribute("visibilidadNombreEvento", "hidden")
				.attribute("visibilidadGuardarropas", "hidden")
				.attribute("visibilidadCalendario", "visible")
				.attribute("visibilidadConfirmacion", "hidden")
				.attribute("visibilidadEventoOk", "hidden")
				.view("altaEvento.hbs")
				.render();
	}
	
	public String postFechaEvento(Request req, Response res) {
		String fechaEvento = req.queryParams("fechaEvento");
		req.session().attribute("fechaEvento", fechaEvento);

		AuthenticatedUser user = this.authService
				.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		
		String guardarropas = req.session().attribute("guardarropas");
		
		Boolean fechaCorrecta = new AltaEventoHelper().esFechaValida(fechaEvento);
		
		if(!fechaCorrecta) {
			res.redirect("/quemepongo/eventos/new/fecha");
			return "";
		}
		
		return new HandlebarsViewBuilder()
				.attribute("nombreEvento", req.session().attribute("nombreEvento"))
				.attribute("guardarropas", guardarropas)
				.attribute("nombreGuardarropas", user.getUsuario().obtenerGuardarropa(Long.parseLong(guardarropas)).getNombreGuardarropas())
				.attribute("fechaEvento", req.session().attribute("fechaEvento"))
				.attribute("todoOk", fechaCorrecta)
				.attribute("visibilidadNombreEvento", "hidden")
				.attribute("visibilidadGuardarropas", "hidden")
				.attribute("visibilidadCalendario", "hidden")
				.attribute("visibilidadConfirmacion", "visible")
				.attribute("visibilidadEventoOk", "hidden")
				.view("altaEvento.hbs")
				.render();
	}
	
	public String confirmarEvento(Request req, Response res) {
		
		AuthenticatedUser user = this.authService
				.getAuthenticatedUser(Long.parseLong(req.cookie("quemepongo-auth-token")));
		Usuario usuario = user.getUsuario();
		
		Guardarropa guardarropaSeleccionado = user.getUsuario()
				.obtenerGuardarropa(Long.parseLong(req.session().attribute("guardarropas")));
		RepositorioEventosPersistente repositorioEventosPersistente = new RepositorioEventosPersistente();
		
		Calendar fecha = new AltaEventoHelper().generarFecha(req.session().attribute("fechaEvento"));
		
		EventoUnico eventoVerano = repositorioEventosPersistente.instanciarEventoUnico(usuario,
				guardarropaSeleccionado, fecha, req.session().attribute("nombreEvento"));
		
		withTransaction(() -> {
			this.entityManager().persist(eventoVerano);
		});
		
		return new HandlebarsViewBuilder()
				.attribute("visibilidadNombreEvento", "hidden")
				.attribute("visibilidadGuardarropas", "hidden")
				.attribute("visibilidadCalendario", "hidden")
				.attribute("visibilidadConfirmacion", "hidden")
				.attribute("visibilidadEventoOk", "visible")
				.view("altaEvento.hbs")
				.render();
	}
}
