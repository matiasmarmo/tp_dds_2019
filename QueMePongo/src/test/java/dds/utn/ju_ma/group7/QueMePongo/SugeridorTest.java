package dds.utn.ju_ma.group7.QueMePongo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import dds.utn.ju_ma.group7.QueMePongo.Atuendo.Atuendo;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Evento;
import dds.utn.ju_ma.group7.QueMePongo.Prenda.*;

public class SugeridorTest extends Fixture {
	
	

	private Color negro;
	
	private Calendar fecha1;
	
	List<Prenda> prendasSupPobre;
	List<Atuendo> atuendosSoloVerano;
	
	List<Prenda> prendasSupFuerte;
	List<Atuendo> atuendosVeranoEinvierno;
	
	Atuendo atuendoNegro;
	Atuendo atuendoNegroConBuzo;
	
	Evento eventoProximo;
	Evento eventoLejano;
	
	@Before
	public void init() {
		
		negro = new Color(0, 0, 0);
		
		fecha1 = Calendar.getInstance();
		fecha1.set(2019, 05, 27, 18, 0);
		
		buzoBuilder = new PrendaBuilder();
		buzoBuilder.setTipoPrenda(TipoPrenda.BUZO).setTipoTela(TipoTela.ALGODON).setColorPrimario(negro);
		buzo = buzoBuilder.crearPrenda();

		pantalonNegroBuilder = new PrendaBuilder();
		pantalonNegroBuilder.setTipoPrenda(TipoPrenda.JOGGIN).setTipoTela(TipoTela.NYLON).setColorPrimario(negro);
		pantalonNegro = pantalonNegroBuilder.crearPrenda();

		zapatosNegrosBuilder = new PrendaBuilder();
		zapatosNegrosBuilder.setTipoPrenda(TipoPrenda.ZAPATILLAS).setTipoTela(TipoTela.NYLON).setColorPrimario(negro);
		zapatosNegros = zapatosNegrosBuilder.crearPrenda();

		collarBuilder = new PrendaBuilder();
		collarBuilder.setTipoPrenda(TipoPrenda.COLLAR).setTipoTela(TipoTela.SEDA).setColorPrimario(negro);
		collar = collarBuilder.crearPrenda();
		
		prendasSupPobre = new ArrayList<Prenda>();
		prendasSupFuerte = new ArrayList<Prenda>();
		prendasSupPobre.add(remeraNegra);
		prendasSupFuerte.add(remeraNegra);
		prendasSupFuerte.add(buzo);
		
		atuendosSoloVerano = new ArrayList<Atuendo>();
		atuendosVeranoEinvierno = new ArrayList<Atuendo>();
		atuendosSoloVerano.add(atuendoNegro);
		atuendosVeranoEinvierno.add(atuendoNegro);
		atuendosVeranoEinvierno.add(atuendoNegroConBuzo);
		
		atuendoNegro = new Atuendo(prendasSupPobre,pantalonNegro,zapatosNegros,collar);
		atuendoNegroConBuzo = new Atuendo(prendasSupFuerte,pantalonNegro,zapatosNegros,collar);
		
	}
	
	@Test
	public void asd() {
		Assert.assertEquals(0, 0);
	}
	
	@Test
	public void elAtuendoSinBuzoTieneNivelDeAbrigo35() { 
		Assert.assertEquals(atuendoNegro.getNivelAbrigo(), 35);
	}

	@Test
	public void elAtuendoConBuzoTieneNivelDeAbrigo65() {
		Assert.assertEquals(atuendoNegroConBuzo.getNivelAbrigo(), 65);
	}

//	@Test
//	public void sugiereRopaDeVeranoConMockVerano() { // al sugeridor le ofrezco solo el atuendo de verano y me lo devuelve
//		Sugeridor sugeridor = new Sugeridor(new ProveedorMockVerano());
//		Assert.assertEquals(sugeridor.sugerir().size(), 1);
//	}
//	
//	@Test
//	public void sugiereRopaDeInviernoConMockInvierno() { // al sugeridor le ofrezco un atuendo de verano y otro de invierno y solo devuelve el de invierno
//		Sugeridor.setProveedorClima(proveedor2);
//		Assert.assertEquals(Sugeridor.sugerir(atuendosVeranoEinvierno, fecha1).size(), 1);
//	}
}