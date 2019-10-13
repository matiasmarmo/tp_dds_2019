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
        
        Spark.get("/guardarropas", controller::listarGuardarropas);
        Spark.get("/aceptar-sugerencias", controller::listarEventos);
        Spark.get("/guardarropas/prendas/:id", controller::listarPrendas);
    }

}
