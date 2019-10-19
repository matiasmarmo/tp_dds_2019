package dds.utn.ju_ma.group7.QueMePongo.Main;

import java.util.Arrays;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.RepositorioUsuariosPersistente;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.GuardarropaLimitado;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Color;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.PrendaBuilder;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoPrenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoTela;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;
import spark.Spark;
import spark.debug.DebugScreen;

public class SparkServer implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps{

    public static void main(String[] args) {
    	new SparkServer().run();
    }

	public void run() {
		withTransaction(() -> {
        	QueMePongoConfiguration.inicializar(100, "", "");
        	Usuario usuario = new RepositorioUsuariosPersistente().instanciarUsuarioGratis(Arrays.asList(), "mallocos@dds.com", "dds123");
        	GuardarropaLimitado guardarropaTestP;
        	guardarropaTestP = new GuardarropaLimitado(usuario, null);
        	
        	Color blanco = new Color(255, 255, 255);
        	Color negro = new Color(0, 0, 0);
        	
    		PrendaBuilder remeraNegraBuilder = new PrendaBuilder();
    		remeraNegraBuilder.setTipoPrenda(TipoPrenda.REMERA).setTipoTela(TipoTela.ALGODON).setColorPrimario(negro);
    		Prenda remeraNegra = remeraNegraBuilder.crearPrenda();

    		PrendaBuilder remeraBlancaBuilder = new PrendaBuilder();
    		remeraBlancaBuilder.setTipoPrenda(TipoPrenda.REMERA).setTipoTela(TipoTela.ALGODON).setColorPrimario(blanco);
    		Prenda remeraBlanca = remeraBlancaBuilder.crearPrenda();
    		
    		
    		guardarropaTestP.agregarPrenda(remeraNegra);
    		guardarropaTestP.agregarPrenda(remeraBlanca);
    		
    		usuario.agregarGuardarropa(guardarropaTestP);
    		persist(usuario);
    	});
		entityManager().clear();

        Spark.port(9000);
        DebugScreen.enableDebugScreen();
        Router.instance().configurar();
	}

}