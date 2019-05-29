package dds.utn.ju_ma.group7.QueMePongo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Evento;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Sugerencia;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Color;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.PrendaBuilder;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoPrenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoTela;

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
	
	protected List<Prenda> prendasSupPobre = new ArrayList<Prenda>();
	protected List<Atuendo> atuendosSoloVerano = new ArrayList<Atuendo>();
	
	protected List<Prenda> prendasSupFuerte = new ArrayList<Prenda>();
	protected List<Atuendo> atuendosVeranoEinvierno = new ArrayList<Atuendo>();
	
	protected Atuendo atuendoNegro;
	protected Atuendo atuendoNegroConBuzo;
	
	protected Calendar fechaProxima = Calendar.getInstance();
	protected Calendar fechaLejana = Calendar.getInstance();
	
	protected Evento eventoProximo;
	protected Evento eventoLejano;

	@Before
	public void initFixture() {

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

		prendasSupPobre.add(remeraNegra);
		prendasSupFuerte.add(remeraNegra);
		prendasSupFuerte.add(buzo);
		
		atuendosSoloVerano.add(atuendoNegro);
		atuendosVeranoEinvierno.add(atuendoNegro);
		atuendosVeranoEinvierno.add(atuendoNegroConBuzo);
		
		atuendoNegro = new Atuendo(prendasSupPobre,pantalonNegro,zapatosNegros,collar);
		atuendoNegroConBuzo = new Atuendo(prendasSupFuerte,pantalonNegro,zapatosNegros,collar);
		
		fechaProxima.setTime(new Date());
		fechaProxima.add(Calendar.DATE, 2);
		
		fechaLejana.setTime(new Date());
		fechaLejana.add(Calendar.DATE, 10);
	}
	
}
