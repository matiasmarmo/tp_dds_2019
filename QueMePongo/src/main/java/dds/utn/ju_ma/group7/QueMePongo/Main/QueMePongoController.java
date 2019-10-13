package dds.utn.ju_ma.group7.QueMePongo.Main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.RepositorioUsuariosPersistente;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Evento;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventosMock;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.GuardarropaLimitado;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Color;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoPrenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoTela;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class QueMePongoController {

	public String listarGuardarropas(Request req, Response res) {
		RepositorioUsuariosPersistente repositorioUsuariosPersistente = new RepositorioUsuariosPersistente();
		Usuario usuario = repositorioUsuariosPersistente.instanciarUsuarioGratis(Arrays.asList());
		Guardarropa guardarropa = new GuardarropaLimitado();
		usuario.agregarGuardarropa(guardarropa);
		usuario.agregarGuardarropa(new GuardarropaLimitado());
		repositorioUsuariosPersistente.almacenar(usuario);
		ModelAndView modelAndView = new ModelAndView(usuario, "listadoGuardarropas.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

	public String listarEventos(Request req, Response res) {
		RepositorioEventosMock repoEventos = new RepositorioEventosMock();
		repoEventos.instanciarEventoUnico(null, null, null, "evento 1");
		repoEventos.instanciarEventoUnico(null, null, null, "evento 2");
		List<Evento> eventos = repoEventos.eventos;
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("eventos", eventos);
		ModelAndView modelAndView = new ModelAndView(model, "calificarSugerencia.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

	public String listarPrendas(Request req, Response res) {
		Prenda prenda = new Prenda(TipoPrenda.BUZO, TipoTela.ALGODON, new Color(35,128,200), null);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("prendas", Arrays.asList(prenda));
    	ModelAndView modelAndView = new ModelAndView(model, "listadoPrendas.hbs");
    	return new HandlebarsTemplateEngine().render(modelAndView);
	}

}
