package dds.utn.ju_ma.group7.QueMePongo.Main;

import java.util.HashMap;

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
            HashMap<String, Object> viewModel = new HashMap<String, Object>();
            ModelAndView modelAndView = new ModelAndView(viewModel, "test.hbs");
            return modelAndView;
        }, new HandlebarsTemplateEngine());
        

        Spark.before("/quemepongo/*", controller::authFilter);
        Spark.get("/login", controller::loginGet);
        Spark.post("/login", controller::loginPost);
        Spark.post("/logout", controller::logout);
        Spark.get("/quemepongo/guardarropas", controller::listarGuardarropas);
        Spark.get("/quemepongo/aceptar-sugerencias", controller::listarEventos);
        Spark.get("/quemepongo/guardarropas/prendas/:id", controller::listarPrendas);
        Spark.get("/quemepongo/altaPrendas", controller::altaPrendas);
        Spark.post("/quemepongo/altaPrendas/tipoPrenda", controller::postTipoPrenda);
        Spark.post("/quemepongo/altaPrendas/tipoTela", controller::postTipoTela);
        Spark.post("/quemepongo/altaPrendas/color", controller::postColor);
        Spark.get("/quemepongo/eventos/sugerencias", controller::listarSugerencias);
    }

}
