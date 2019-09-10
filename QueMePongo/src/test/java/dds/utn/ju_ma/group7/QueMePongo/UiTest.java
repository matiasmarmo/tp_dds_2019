package dds.utn.ju_ma.group7.QueMePongo;

import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Ui.EventosViewModel;

public class UiTest extends Fixture {

	@Test
	public void entreHaceTresDiasYManianaHaySeisEventos() {
		SimpleDateFormat fecha1 = new SimpleDateFormat("yyyy-MM-dd");
		String minus = fecha1.format(hace3DiasCalendar.getTime()); 
		SimpleDateFormat fecha2 = new SimpleDateFormat("yyyy-MM-dd");
		String plus = fecha2.format(manianaCalendar.getTime()); 
		
		EventosViewModel viewModel = new EventosViewModel();
		viewModel.setFechaInicio(minus);
		viewModel.setFechaFin(plus);
		viewModel.cargarEventos();
		
		Assert.assertEquals(6, viewModel.getEventos().size());
	}
}
