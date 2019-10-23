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
    	AuthenticationService authService = new AuthenticationService(new RepositorioUsuariosPersistente());
    	
    	AuthController authController = new AuthController(authService);
        GuardarropasController guardarropasController = new GuardarropasController(authService);
        PrendasController prendasController = new PrendasController(authService);
        EventosController controller = new EventosController(authService);

        Spark.before("/quemepongo/*", authController::authFilter);
        Spark.get("/login", authController::loginGet);
        Spark.post("/login", authController::loginPost);
        Spark.post("/logout", authController::logout);
        
        Spark.get("/quemepongo/guardarropas", guardarropasController::listarGuardarropas);
        Spark.get("/quemepongo/guardarropas/:id", guardarropasController::listarPrendas);
        
        Spark.get("/quemepongo/prenda/guardarropas", prendasController::seleccionarGuardarropas);
        Spark.post("/quemepongo/prenda/guardarropas", prendasController::guardarropasSeleccionado);
        Spark.post("/quemepongo/prenda/tipoPrenda", prendasController::postTipoPrenda);
        Spark.post("/quemepongo/prenda/tipoTela", prendasController::postTipoTela);
        Spark.post("/quemepongo/prenda/color", prendasController::postColor);
        Spark.post("/quemepongo/prenda/listo", prendasController::postPrendaLista);
        
        Spark.get("/quemepongo/calificacion-sugerencias", controller::listarSugerenciasParaCalificar);
        Spark.get("/quemepongo/eventos/sugerencias", controller::listarSugerenciasDeUnEvento);
        Spark.get("/quemepongo/eventos/sugerencias/eleccion", controller::ejecutarAccionSugerencia);
        Spark.get("/quemepongo/eventos/sugerencias/rechazarPendientes", controller::rechazarSugerenciasPendientes);
        Spark.get("/quemepongo/eventos/sugerencias/calificar", controller::listarSugerenciasAceptadas);
        Spark.get("/quemepongo/eventos/sugerencias/calificar/ejecutarCalificacion", controller::ejecutarCalificacion);
        Spark.get("/quemepongo/altaEvento", controller::altaEvento);
    }

}