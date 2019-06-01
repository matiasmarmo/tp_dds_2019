package dds.utn.ju_ma.group7.QueMePongo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Evento;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventos;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Sugerencia;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Main.QueMePongoConfiguration;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Color;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.PrendaBuilder;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoPrenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoTela;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.UsuarioPremium;

public class Fixture {

	protected PrendaBuilder remeraNegraBuilder = new PrendaBuilder();
	protected Prenda remeraNegra;
	protected PrendaBuilder remeraBlancaBuilder = new PrendaBuilder();
	protected Prenda remeraBlanca;
	protected PrendaBuilder jeanBuilder = new PrendaBuilder();
	protected Prenda jeanNegro;
	protected PrendaBuilder zapatillasBuilder = new PrendaBuilder();
	protected Prenda zapatillasNegras;
	protected PrendaBuilder collarDivinoBuilder = new PrendaBuilder();
	protected Prenda collarDivino;
	protected PrendaBuilder remeraNegraYBlancaBuilder = new PrendaBuilder();
	protected Prenda remeraNegraYBlanca;
	protected PrendaBuilder remeraDeCueroBuilder = new PrendaBuilder();
	protected PrendaBuilder remeraColoresInvalidosBuilder = new PrendaBuilder();
	protected PrendaBuilder remeraNulaBuilder = new PrendaBuilder();
	protected PrendaBuilder buzoBuilder = new PrendaBuilder();
	protected Prenda buzo;
	protected PrendaBuilder pantalonNegroBuilder = new PrendaBuilder();
	protected Prenda pantalonNegro;
	protected PrendaBuilder zapatosNegrosBuilder = new PrendaBuilder();
	protected Prenda zapatosNegros;
	protected PrendaBuilder collarBuilder = new PrendaBuilder();
	protected Prenda collar;
	
	protected Color negro = new Color(0, 0, 0);
	protected Color blanco = new Color(255, 255, 255);
	
	protected Guardarropa guardarropaCompleto = new Guardarropa();
	protected Guardarropa guardarropaIncompleto = new Guardarropa();
	
	protected Atuendo atuendo;
	protected Sugerencia sugerencia;
	protected Sugerencia sugerencia2;
	protected Sugerencia sugerencia3;
	protected List<Sugerencia> sugerencias = new ArrayList<Sugerencia>();
	
	protected Usuario usuario;
	protected Usuario otroUsuario;
	
	protected List<Prenda> prendasSupPobre = new ArrayList<Prenda>();
	protected List<Atuendo> atuendosSoloVerano = new ArrayList<Atuendo>();
	protected Guardarropa guardarropasVerano;
	
	protected List<Prenda> prendasSupFuerte = new ArrayList<Prenda>();
	protected List<Atuendo> atuendosVeranoEinvierno = new ArrayList<Atuendo>();
	protected Guardarropa guardarropasVeranoEInvierno;
	
	protected Atuendo atuendoNegro;
	protected Atuendo atuendoNegroConBuzo;
	
	protected Calendar fechaProxima = Calendar.getInstance();
	protected Calendar fechaLejana = Calendar.getInstance();
	protected Calendar fechaActual = Calendar.getInstance();
	
	protected Evento eventoVerano;
	protected Evento eventoInvierno;
	protected Evento quince;
	
	protected RepositorioEventos repositorioEventos = new RepositorioEventos();

	@Before
	public void initFixture() {
		
		QueMePongoConfiguration.inicializar(1);

		remeraNegraBuilder.setTipoPrenda(TipoPrenda.REMERA).setTipoTela(TipoTela.ALGODON).setColorPrimario(negro);
		remeraNegra = remeraNegraBuilder.crearPrenda();
		
		remeraBlancaBuilder.setTipoPrenda(TipoPrenda.REMERA).setTipoTela(TipoTela.ALGODON).setColorPrimario(blanco);
		remeraBlanca = remeraBlancaBuilder.crearPrenda();

		jeanBuilder.setTipoPrenda(TipoPrenda.JEAN).setTipoTela(TipoTela.CUERO).setColorPrimario(negro);
		jeanNegro = jeanBuilder.crearPrenda();

		zapatillasBuilder.setTipoPrenda(TipoPrenda.ZAPATILLAS).setTipoTela(TipoTela.NYLON).setColorPrimario(negro);
		zapatillasNegras = zapatillasBuilder.crearPrenda();

		collarDivinoBuilder.setTipoPrenda(TipoPrenda.COLLAR).setTipoTela(TipoTela.SEDA).setColorPrimario(blanco);
		collarDivino = collarDivinoBuilder.crearPrenda();
		
		remeraNegraYBlancaBuilder.setTipoPrenda(TipoPrenda.REMERA).setTipoTela(TipoTela.ALGODON).setColorPrimario(negro);
		remeraNegraYBlancaBuilder.setColorSecundario(blanco);

		remeraDeCueroBuilder.setTipoPrenda(TipoPrenda.REMERA).setTipoTela(TipoTela.CUERO).setColorPrimario(negro);

		remeraColoresInvalidosBuilder.setTipoPrenda(TipoPrenda.REMERA).setTipoTela(TipoTela.ALGODON)
			.setColorPrimario(negro).setColorSecundario(negro);
		
		buzoBuilder.setTipoPrenda(TipoPrenda.BUZO).setTipoTela(TipoTela.ALGODON).setColorPrimario(negro);
		buzo = buzoBuilder.crearPrenda();

		pantalonNegroBuilder.setTipoPrenda(TipoPrenda.JOGGIN).setTipoTela(TipoTela.NYLON).setColorPrimario(negro);
		pantalonNegro = pantalonNegroBuilder.crearPrenda();

		zapatosNegrosBuilder.setTipoPrenda(TipoPrenda.ZAPATILLAS).setTipoTela(TipoTela.NYLON).setColorPrimario(negro);
		zapatosNegros = zapatosNegrosBuilder.crearPrenda();

		collarBuilder.setTipoPrenda(TipoPrenda.COLLAR).setTipoTela(TipoTela.SEDA).setColorPrimario(negro);
		collar = collarBuilder.crearPrenda();

		guardarropaCompleto.agregarPrenda(remeraNegra);
		guardarropaCompleto.agregarPrenda(remeraBlanca);
		guardarropaCompleto.agregarPrenda(jeanNegro);
		guardarropaCompleto.agregarPrenda(zapatillasNegras);
		guardarropaCompleto.agregarPrenda(collarDivino);

		guardarropaIncompleto.agregarPrenda(remeraNegra);
		guardarropaIncompleto.agregarPrenda(jeanNegro);
		guardarropaIncompleto.agregarPrenda(collarDivino);
		
		atuendo = guardarropaCompleto.generarAtuendos().get(0);
		sugerencia = new Sugerencia(atuendo);
		sugerencia2 = new Sugerencia(atuendoNegro);
		sugerencia3 = new Sugerencia(atuendoNegroConBuzo);
		
		sugerencia.aceptar();
		sugerencia2.aceptar();
		sugerencia3.rechazar();

		sugerencias.add(sugerencia);
		sugerencias.add(sugerencia2);
		sugerencias.add(sugerencia3);
		
		prendasSupPobre.add(remeraNegra);
		prendasSupFuerte.add(remeraNegra);
		prendasSupFuerte.add(buzo);
		
		atuendoNegro = new Atuendo(prendasSupPobre,pantalonNegro,zapatosNegros,collar);
		atuendoNegroConBuzo = new Atuendo(prendasSupFuerte,pantalonNegro,zapatosNegros,collar);
		
		atuendosSoloVerano.add(atuendoNegro);
		atuendosVeranoEinvierno.add(atuendoNegro);
		atuendosVeranoEinvierno.add(atuendoNegroConBuzo);
		
		usuario = new UsuarioPremium();
		otroUsuario = new UsuarioPremium();
		
		guardarropasVerano = new Guardarropa();
		guardarropasVerano.agregarPrenda(remeraNegra);
		guardarropasVerano.agregarPrenda(pantalonNegro);
		guardarropasVerano.agregarPrenda(zapatosNegros);
		guardarropasVerano.agregarPrenda(collar);
		
		guardarropasVeranoEInvierno = new Guardarropa();
		guardarropasVeranoEInvierno.agregarPrenda(remeraNegra);
		guardarropasVeranoEInvierno.agregarPrenda(buzo);
		guardarropasVeranoEInvierno.agregarPrenda(pantalonNegro);
		guardarropasVeranoEInvierno.agregarPrenda(zapatosNegros);
		guardarropasVeranoEInvierno.agregarPrenda(collar);
		
		fechaActual.setTime(new Date());
		
		fechaProxima.setTime(new Date());
		fechaProxima.add(Calendar.DATE, 2);
		
		fechaLejana.setTime(new Date());
		fechaLejana.add(Calendar.DATE, 100);
		
		eventoInvierno = new Evento(usuario, guardarropasVeranoEInvierno, fechaProxima, "Un evento de invierno");
		eventoVerano = new Evento(usuario, guardarropasVerano, fechaLejana, "Un evento de verano");
	}
	
}
