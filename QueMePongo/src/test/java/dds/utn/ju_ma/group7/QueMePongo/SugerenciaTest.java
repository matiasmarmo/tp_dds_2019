package dds.utn.ju_ma.group7.QueMePongo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.EstadoSugerencia;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Sugerencia;
import dds.utn.ju_ma.group7.QueMePongo.Guardarropa.Guardarropa;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.Prenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.PrendaBuilder;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoPrenda;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.TipoTela;

public class SugerenciaTest extends Fixture {
	
	private Guardarropa guardarropaCompleto = new Guardarropa();
	private PrendaBuilder remeraBlancaBuilder = new PrendaBuilder();
	private Prenda remeraBlanca;
	private PrendaBuilder jeanBuilder = new PrendaBuilder();
	private Prenda jeanNegro;
	private PrendaBuilder zapatillasBuilder = new PrendaBuilder();
	private Prenda zapatillasNegras;
	private PrendaBuilder collarDivinoBuilder = new PrendaBuilder();
	private Prenda collarDivino;
	
	private Atuendo atuendo;
	private Sugerencia sugerencia;
	
	@Before
	public void init() {

		remeraBlancaBuilder.setTipoPrenda(TipoPrenda.REMERA);
		remeraBlancaBuilder.setTipoTela(TipoTela.ALGODON);
		remeraBlancaBuilder.setColorPrimario(blanco);
		remeraBlanca = remeraBlancaBuilder.crearPrenda();

		jeanBuilder.setTipoPrenda(TipoPrenda.JEAN);
		jeanBuilder.setTipoTela(TipoTela.CUERO);
		jeanBuilder.setColorPrimario(negro);
		jeanNegro = jeanBuilder.crearPrenda();

		zapatillasBuilder.setTipoPrenda(TipoPrenda.ZAPATILLAS);
		zapatillasBuilder.setTipoTela(TipoTela.NYLON);
		zapatillasBuilder.setColorPrimario(negro);
		zapatillasNegras = zapatillasBuilder.crearPrenda();

		collarDivinoBuilder.setTipoPrenda(TipoPrenda.COLLAR);
		collarDivinoBuilder.setTipoTela(TipoTela.SEDA);
		collarDivinoBuilder.setColorPrimario(blanco);
		collarDivino = collarDivinoBuilder.crearPrenda();

		guardarropaCompleto.agregarPrenda(remeraNegra);
		guardarropaCompleto.agregarPrenda(remeraBlanca);
		guardarropaCompleto.agregarPrenda(jeanNegro);
		guardarropaCompleto.agregarPrenda(zapatillasNegras);
		guardarropaCompleto.agregarPrenda(collarDivino);

		
		atuendo = guardarropaCompleto.generarAtuendos().get(0);
		sugerencia = new Sugerencia(atuendo);

	}
	
	@Test
	public void aceptarSugerencia() {
		sugerencia.aceptar();
		Assert.assertEquals(EstadoSugerencia.ACEPTADA, sugerencia.getEstado());
	}
	
	@Test
	public void rechazarSugerencia() {
		sugerencia.rechazar();
		Assert.assertEquals(EstadoSugerencia.RECHAZADA, sugerencia.getEstado());
	}
	
	@Test
	public void deshacerOperacion() {
		sugerencia.rechazar();
		sugerencia.deshacerOperacion();
		Assert.assertEquals(EstadoSugerencia.PENDIENTE, sugerencia.getEstado());
	}

}
