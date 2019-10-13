package dds.utn.ju_ma.group7.QueMePongo.Main;

import java.util.Arrays;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.RepositorioUsuariosPersistente;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.GuardarropaLimitado;
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

}
