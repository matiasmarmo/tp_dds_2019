package dds.utn.ju_ma.group7.QueMePongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;

import dds.utn.ju_ma.group7.QueMePongo.Alertador.RepositorioUsuarios;
import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.EventoRepetitivo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.EventoUnico;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventos;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Sugerencia;
import dds.utn.ju_ma.group7.QueMePongo.Evento.TipoRecurrencia;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.GuardarropaLimitado;
import dds.utn.ju_ma.group7.QueMePongo.Main.QueMePongoConfiguration;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Color;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.PrendaBuilder;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoPrenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoTela;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.InteresEnNotificaciones;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.NotificadorMock;
import dds.utn.ju_ma.group7.QueMePongo.Usuario.Usuario;

public class Fixture {

	protected Calendar hace3DiasCalendar;
	protected Calendar manianaCalendar;

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
	protected PrendaBuilder shortBuilder = new PrendaBuilder();
	protected Prenda unShort;
	protected PrendaBuilder collarBuilder = new PrendaBuilder();
	protected Prenda collar;

	protected Color negro = new Color(0, 0, 0);
	protected Color blanco = new Color(255, 255, 255);

	protected InteresEnNotificaciones notificadorUsuario = new NotificadorMock();
	protected InteresEnNotificaciones notificadorOtroUsuario = new NotificadorMock();

	protected Usuario usuario = RepositorioUsuarios.getInstance().instanciarUsuarioPremium(Arrays.asList(notificadorUsuario));
	protected Usuario otroUsuario = RepositorioUsuarios.getInstance().instanciarUsuarioGratis(Arrays.asList(notificadorOtroUsuario));

	protected Guardarropa guardarropaCompleto = new Guardarropa(usuario);
	protected Guardarropa guardarropaIncompleto = new Guardarropa(usuario);
	protected GuardarropaLimitado guardarropaCompartido;

	protected Atuendo atuendo;
	protected Sugerencia sugerencia;
	protected Sugerencia sugerencia2;
	protected Sugerencia sugerencia3;
	protected List<Sugerencia> sugerencias = new ArrayList<Sugerencia>();

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

	protected EventoUnico eventoVerano;
	protected EventoUnico eventoInvierno;
	protected EventoUnico quince;

	protected EventoRepetitivo irATrabajar;
	protected EventoRepetitivo eventoRepetitivoNoProximo;
	protected EventoRepetitivo eventoMensualProximo;

	
	@Before
	public void initFixture() {

		QueMePongoConfiguration.inicializar(1, "", "");
		RepositorioEventos.vaciar();

		hace3DiasCalendar = Calendar.getInstance();
		hace3DiasCalendar.add(Calendar.DATE, -3);
		manianaCalendar = Calendar.getInstance();
		manianaCalendar.add(Calendar.DATE, 1);

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

		remeraNegraYBlancaBuilder.setTipoPrenda(TipoPrenda.REMERA).setTipoTela(TipoTela.ALGODON)
				.setColorPrimario(negro);
		remeraNegraYBlancaBuilder.setColorSecundario(blanco);

		remeraDeCueroBuilder.setTipoPrenda(TipoPrenda.REMERA).setTipoTela(TipoTela.CUERO).setColorPrimario(negro);

		remeraColoresInvalidosBuilder.setTipoPrenda(TipoPrenda.REMERA).setTipoTela(TipoTela.ALGODON)
				.setColorPrimario(negro).setColorSecundario(negro);

		buzoBuilder.setTipoPrenda(TipoPrenda.BUZO).setTipoTela(TipoTela.ALGODON).setColorPrimario(negro);
		buzo = buzoBuilder.crearPrenda();

		pantalonNegroBuilder.setTipoPrenda(TipoPrenda.JOGGIN).setTipoTela(TipoTela.NYLON).setColorPrimario(negro);
		pantalonNegro = pantalonNegroBuilder.crearPrenda();
		
		shortBuilder.setTipoPrenda(TipoPrenda.SHORT).setTipoTela(TipoTela.DRY_FIT).setColorPrimario(negro);
		unShort = shortBuilder.crearPrenda();

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

		atuendo = guardarropaCompleto.generarAtuendos(Calendar.getInstance()).get(0);
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

		atuendoNegro = new Atuendo(prendasSupPobre, unShort, zapatosNegros, collar);
		atuendoNegroConBuzo = new Atuendo(prendasSupFuerte, pantalonNegro, zapatosNegros, collar);

		atuendosSoloVerano.add(atuendoNegro);
		atuendosVeranoEinvierno.add(atuendoNegro);
		atuendosVeranoEinvierno.add(atuendoNegroConBuzo);

		guardarropaCompartido = new GuardarropaLimitado(usuario);
		guardarropaCompartido.agregarUsuario(otroUsuario);

		guardarropasVerano = new Guardarropa(usuario);
		guardarropasVerano.agregarPrenda(remeraNegra);
		guardarropasVerano.agregarPrenda(pantalonNegro);
		guardarropasVerano.agregarPrenda(zapatosNegros);
		guardarropasVerano.agregarPrenda(collar);

		guardarropasVeranoEInvierno = new Guardarropa(usuario);
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

		eventoInvierno = RepositorioEventos.instanciarEventoUnico(usuario, guardarropasVeranoEInvierno, fechaProxima, "Un evento de invierno");
		eventoVerano = RepositorioEventos.instanciarEventoUnico(usuario, guardarropasVerano, fechaLejana, "Un evento de verano");
		irATrabajar = RepositorioEventos.instanciarEventoRepetitivo(usuario, guardarropasVeranoEInvierno, hace3DiasCalendar, "Hay que laburar",
				TipoRecurrencia.DIARIA);
		eventoRepetitivoNoProximo = RepositorioEventos.instanciarEventoRepetitivo(usuario, guardarropaCompleto, hace3DiasCalendar,
				"Falta para este", TipoRecurrencia.ANUAL);
		eventoMensualProximo = RepositorioEventos.instanciarEventoRepetitivo(usuario, guardarropaCompleto, manianaCalendar, "Hay que sugerirlo",
				TipoRecurrencia.MENSUAL);
		RepositorioEventos.instanciarEventoUnico(usuario, guardarropasVerano, fechaProxima, "Cumple de 15");
		RepositorioEventos.instanciarEventoUnico(otroUsuario, guardarropasVeranoEInvierno, fechaProxima, "Bar Mitzva");
	}

}
