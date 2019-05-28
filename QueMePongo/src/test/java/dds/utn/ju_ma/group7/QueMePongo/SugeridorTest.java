package dds.utn.ju_ma.group7.QueMePongo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.*;
import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.*;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.*;

public class SugeridorTest extends Fixture {
	
	private PrendaBuilder buzoBuilder = new PrendaBuilder();
	private Prenda buzo;
	
	private PrendaBuilder remeraNegraBuilder = new PrendaBuilder();
	private Prenda remeraNegra;

	private PrendaBuilder pantalonNegroBuilder = new PrendaBuilder();
	private Prenda pantalonNegro;

	private PrendaBuilder zapatosNegrosBuilder = new PrendaBuilder();
	private Prenda zapatosNegros;

	private PrendaBuilder collarBuilder = new PrendaBuilder();
	private Prenda collar;

	private Color negro = new Color(0, 0, 0);
	
	private ProveedorMockVerano proveedor1 = new ProveedorMockVerano();
	private ProveedorMockInvierno proveedor2 = new ProveedorMockInvierno();
	private Sugeridor sug1 = new Sugeridor();
	private Sugeridor sug2 = new Sugeridor();
	
	private Calendar fecha1 = Calendar.getInstance();
	
	List<Prenda> prendasSupPobre = new ArrayList<Prenda>();
	List<Atuendo> atuendosSoloVerano = new ArrayList<Atuendo>();
	Atuendo atuendoNegro = new Atuendo(prendasSupPobre,pantalonNegro,zapatosNegros,collar);
	
	List<Prenda> prendasSupFuerte = new ArrayList<Prenda>();
	List<Atuendo> atuendosVeranoEinvierno = new ArrayList<Atuendo>();
	Atuendo atuendoNegroConBuzo = new Atuendo(prendasSupFuerte,pantalonNegro,zapatosNegros,collar);
	
	public void init() {
		
		buzoBuilder.setTipoPrenda(TipoPrenda.BUZO).setTipoTela(TipoTela.ALGODON).setColorPrimario(negro);
		buzo = buzoBuilder.crearPrenda();
		
		remeraNegraBuilder.setTipoPrenda(TipoPrenda.REMERA).setTipoTela(TipoTela.ALGODON).setColorPrimario(negro);
		remeraNegra = remeraNegraBuilder.crearPrenda();

		pantalonNegroBuilder.setTipoPrenda(TipoPrenda.JOGGIN).setTipoTela(TipoTela.NYLON).setColorPrimario(negro);
		pantalonNegro = pantalonNegroBuilder.crearPrenda();

		zapatosNegrosBuilder.setTipoPrenda(TipoPrenda.ZAPATILLAS).setTipoTela(TipoTela.NYLON).setColorPrimario(negro);
		zapatosNegros = zapatosNegrosBuilder.crearPrenda();

		collarBuilder.setTipoPrenda(TipoPrenda.COLLAR).setTipoTela(TipoTela.SEDA).setColorPrimario(negro);
		collar = collarBuilder.crearPrenda();
		
		prendasSupPobre.add(remeraNegra);
		prendasSupFuerte.add(remeraNegra);
		prendasSupFuerte.add(buzo);
		
		
		atuendosSoloVerano.add(atuendoNegro);
		atuendosVeranoEinvierno.add(atuendoNegro);
		atuendosVeranoEinvierno.add(atuendoNegroConBuzo);
		
		sug1.setProveedorClima(proveedor1);
		sug2.setProveedorClima(proveedor2);
		fecha1.set(2019, 05, 27, 18, 0);
	}


	@Test
	public void sugiereRopaDeVeranoConMockVerano() { // al sugeridor le ofrezco solo el atuendo de verano y me lo devuelve
		Assert.assertEquals(sug1.sugerir(atuendosSoloVerano, fecha1).size(), 1);
	}
	
	@Test
	public void sugiereRopaDeInviernoConMockInvierno() { // al sugeridor le ofrezco un atuendo de verano y otro de invierno y solo devuelve el de invierno
		Assert.assertEquals(sug1.sugerir(atuendosVeranoEinvierno, fecha1).size(), 1);
	}

	@Test(expected = NullPointerException.class)
	public void pruebaDeNull(){
		sug1.sugerir(atuendosVeranoEinvierno, fecha1);
	}

}