package dds.utn.ju_ma.group7.QueMePongo;

import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Ui.*;

public class UiTest extends Fixture {
	@Before
	public void init() {
	}

	@Test
	public void entreHaceTresDiasYManianaHayOchoEventos() {
		SimpleDateFormat fecha1 = new SimpleDateFormat("yyyy-MM-dd");
		String minus = fecha1.format(hace3DiasCalendar.getTime()); 
		SimpleDateFormat fecha2 = new SimpleDateFormat("yyyy-MM-dd");
		String plus = fecha2.format(manianaCalendar.getTime()); 
		
		EventosViewModel viewModel = new EventosViewModel();
		viewModel.setFechaInicio(minus);
		viewModel.setFechaFin(plus);
		viewModel.cargarEventos();
		
		Assert.assertEquals(8, viewModel.getA().size());
	}
}
