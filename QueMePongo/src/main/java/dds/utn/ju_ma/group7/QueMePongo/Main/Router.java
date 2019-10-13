package dds.utn.ju_ma.group7.QueMePongo.Main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.jknack.handlebars.Handlebars;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.RepositorioUsuariosPersistente;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Evento;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventosMock;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.GuardarropaLimitado;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioGratis;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
    static Router _instance;

    private Router() {}
    
    public static Router instance() {
        if(_instance == null) {
            _instance = new Router();
        }
        return _instance;
    }
    
    
    public void configurar() {
        QueMePongoController controller = new QueMePongoController();
        Spark.get("/test", (req, res) -> {
            HashMap<String, Object> viewModel = new HashMap();
            ModelAndView modelAndView = new ModelAndView(viewModel, "test.hbs");
            return modelAndView;
        }, new HandlebarsTemplateEngine());
        
        Spark.get("/guardarropas", controller::listarGuardarropas);
        
        Spark.get("/aceptar-sugerencias", controller::listarEventos);
    }

}
