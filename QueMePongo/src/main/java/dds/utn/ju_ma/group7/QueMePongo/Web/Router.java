package dds.utn.ju_ma.group7.QueMePongo.Web;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.RepositorioUsuariosPersistente;
import spark.Filter;
import spark.Route;
import spark.Spark;

public class Router {
    static Router _instance;

    private Router() {}
    
    public static Router instance() {
        if(_instance == null) {
            _instance = new Router();
        }
        return _instance;
    }
    
    private void get(String path, Route route) {
    	Spark.get("/quemepongo" + path, route);
    }
    
    private void post(String path, Route route) {
    	Spark.post("/quemepongo" + path, route);
    }
    
    private void before(String path, Filter filter) {
    	Spark.before("/quemepongo" + path, filter);
    }
    
    
    public void configurar() {
    	AuthenticationService authService = new AuthenticationService(new RepositorioUsuariosPersistente());
    	
    	AuthController authController = new AuthController(authService);
        GuardarropasController guardarropasController = new GuardarropasController(authService);
        PrendasController prendasController = new PrendasController(authService);
        EventosController controller = new EventosController(authService);

        this.before("/*", authController::authFilter);
        Spark.get("/login", authController::loginGet);
        Spark.post("/login", authController::loginPost);
        Spark.post("/logout", authController::logout);
        
        this.get("/guardarropas", guardarropasController::listarGuardarropas);
        this.get("/guardarropas/:id", guardarropasController::listarPrendas);
        
        this.post("/prenda/guardarropas", prendasController::guardarropasSeleccionado);
        this.post("/prenda/tipoPrenda", prendasController::postTipoPrenda);
        this.post("/prenda/tipoTela", prendasController::postTipoTela);
        this.post("/prenda/color", prendasController::postColor);
        this.post("/prenda/listo", prendasController::postPrendaLista);
        
        this.get("/eventos/:id/sugerencias", controller::listarSugerenciasDeUnEvento);
        this.post("/eventos/:idEvento/sugerencias/:idSugerencia", controller::ejecutarAccionSugerencia);
        
        this.get("/eventos/sugerencias/calificacion", controller::listarSugerenciasAceptadas);
        this.post("/eventos/sugerencias/calificacion", controller::ejecutarCalificacion);
        this.get("/eventos", controller::listarEventos);
        
        this.get("/eventos/new", controller::altaNuevoEvento);
        this.post("/eventos/new/nombre", controller::postNombreEvento);
        this.post("/eventos/new/guardarropas", controller::postGuardarropasEvento);
        this.get("/eventos/new/fecha", controller::getFormFechaEvento);
        this.post("/eventos/new/fecha", controller::postFechaEvento);
        this.post("/eventos/new", controller::confirmarEvento);
    }

}