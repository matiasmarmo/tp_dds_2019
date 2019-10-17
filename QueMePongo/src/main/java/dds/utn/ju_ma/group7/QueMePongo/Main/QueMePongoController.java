package dds.utn.ju_ma.group7.QueMePongo.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.RepositorioUsuariosPersistente;
import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Evento;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventosPersistente;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Sugerencia;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.GuardarropaLimitado;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Color;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.PrendaBuilder;
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

	public String listarPrendas(Request req, Response res) {
		Prenda prenda = new Prenda(TipoPrenda.BUZO, TipoTela.ALGODON, new Color(35,128,200), null);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("prendas", Arrays.asList(prenda));
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
    	Evento e  = repoEventos.instanciarEventoUnico(null, null, null, "evento 1");
    	Evento e2 = repoEventos.instanciarEventoUnico(null, null, null, "evento 2");
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