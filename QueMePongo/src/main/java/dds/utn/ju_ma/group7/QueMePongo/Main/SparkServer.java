package dds.utn.ju_ma.group7.QueMePongo.Main;

import java.util.Arrays;
import java.util.Calendar;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.RepositorioUsuariosPersistente;
import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.EventoUnico;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventosPersistente;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Sugerencia;
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
    		
    		PrendaBuilder pantalonBuilder = new PrendaBuilder();
        	pantalonBuilder.setTipoPrenda(TipoPrenda.JOGGIN).setTipoTela(TipoTela.NYLON).setColorPrimario(negro);
        	Prenda unShort = pantalonBuilder.crearPrenda();
        	
        	PrendaBuilder zapatillasBuilder = new PrendaBuilder();
        	zapatillasBuilder.setTipoPrenda(TipoPrenda.ZAPATILLAS).setTipoTela(TipoTela.NYLON).setColorPrimario(negro);
        	Prenda zapatillas = zapatillasBuilder.crearPrenda();
        	
        	PrendaBuilder collarBuilder = new PrendaBuilder();
        	collarBuilder.setTipoPrenda(TipoPrenda.COLLAR).setTipoTela(TipoTela.SEDA).setColorPrimario(negro);
        	Prenda collar = collarBuilder.crearPrenda();
    		
    		guardarropaTestP.agregarPrenda(remeraNegra);
    		guardarropaTestP.agregarPrenda(remeraBlanca);
    		guardarropaTestP.agregarPrenda(unShort);
    		guardarropaTestP.agregarPrenda(zapatillas);
    		guardarropaTestP.agregarPrenda(collar);
    		
    		usuario.agregarGuardarropa(guardarropaTestP);
    		
    		RepositorioEventosPersistente repoEventos = new RepositorioEventosPersistente();
    		repoEventos.instanciarEventoUnico(usuario, guardarropaTestP, Calendar.getInstance(), "Un Evento");
    		repoEventos.instanciarEventoUnico(usuario, guardarropaTestP, Calendar.getInstance(), "Otro Evento");
    		
    		Atuendo atuendo = new Atuendo(Arrays.asList(remeraBlanca, remeraNegra), unShort, zapatillas, collar);
    		Atuendo atuendoNegro = new Atuendo(Arrays.asList(remeraNegra), unShort, zapatillas, collar);
    		Sugerencia sugerencia = new Sugerencia(atuendo);
    		Sugerencia sugerenciaAceptada = new Sugerencia(atuendo);
    		Sugerencia sugerenciaAceptadaDos = new Sugerencia(atuendoNegro);
    		sugerenciaAceptada.aceptar();
    		sugerenciaAceptadaDos.aceptar();
    		
    		EventoUnico evento = (EventoUnico) repoEventos.todosLosEventos().get(0);
    		EventoUnico eventoDos = (EventoUnico) repoEventos.todosLosEventos().get(1);
    		evento.sugerencias = Arrays.asList(sugerencia);
    		eventoDos.sugerencias = Arrays.asList(sugerenciaAceptada,sugerenciaAceptadaDos);
    		
    		persist(usuario);
    	});
		entityManager().clear();
		
        Spark.port(9000);
        DebugScreen.enableDebugScreen();
        Router.instance().configurar();
	}

}