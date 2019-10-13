package dds.utn.ju_ma.group7.QueMePongo.Main;

import java.util.HashMap;
import spark.Spark;
import spark.ModelAndView;
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
        // Configuración de rutas
        Spark.get("/test", (req, res) -> {
            HashMap<String, Object> viewModel = new HashMap();
            ModelAndView modelAndView = new ModelAndView(viewModel, "test.hbs");
            return new HandlebarsTemplateEngine().render(modelAndView);
        });
    }

}
