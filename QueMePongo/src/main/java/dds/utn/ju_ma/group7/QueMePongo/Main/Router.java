package dds.utn.ju_ma.group7.QueMePongo.Main;

import java.util.HashMap;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.RepositorioUsuariosPersistente;
import dds.utn.ju_ma.group7.QueMePongo.Web.AuthenticationService;
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
        QueMePongoController controller = new QueMePongoController(new AuthenticationService(new RepositorioUsuariosPersistente()));
        Spark.get("/test", (req, res) -> {
            HashMap<String, Object> viewModel = new HashMap<String, Object>();
            ModelAndView modelAndView = new ModelAndView(viewModel, "test.hbs");
            return modelAndView;
        }, new HandlebarsTemplateEngine());
        

        Spark.before("/quemepongo/*", controller::authFilter);
        Spark.get("/login", controller::loginGet);
        Spark.post("/login", controller::loginPost);
        Spark.post("/logout", controller::logout);
        Spark.get("/quemepongo/guardarropas", controller::listarGuardarropas);
        Spark.get("/quemepongo/guardarropas/:id", controller::listarPrendas);
        Spark.get("/quemepongo/prenda", controller::altaPrendas);
        Spark.post("/quemepongo/prenda/tipoPrenda", controller::postTipoPrenda);
        Spark.post("/quemepongo/prenda/tipoTela", controller::postTipoTela);
        Spark.post("/quemepongo/prenda/color", controller::postColor);
        Spark.get("/quemepongo/sugerencias", controller::listarEventos);
        Spark.get("/quemepongo/eventos/sugerencias", controller::listarSugerencias);
        Spark.get("/quemepongo/eventos/sugerencias/eleccion", controller::mostrarEleccion);
        Spark.get("/quemepongo/eventos/sugerencias/calificar", controller::listarSugerenciasAceptadas);
    }

}
