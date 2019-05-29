package dds.utn.ju_ma.group7.QueMePongo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.openmbean.OpenDataException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Evento.Evento;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventos;
import dds.utn.ju_ma.group7.QueMePongo.Evento.Sugerencia;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.OpenWeatherMapProveedor;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.ProveedorClima;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.ProveedorMockInvierno;
import dds.utn.ju_ma.group7.QueMePongo.Sugeridor.Sugeridor;

public class RepositorioEventosTest extends Fixture {
	@Before
	public void init() {
		RepositorioEventos.instanciarEvento(usuario, 
											guardarropasVerano, 
											fechaProxima,
											"Cumple de 15");
		RepositorioEventos.instanciarEvento(otroUsuario, 
											guardarropasVeranoEInvierno, 
											fechaProxima, 
											"Bar Mitzva");
	}
	
	@Test
	public void elRepositorioEventosTiene2SugerenciasAceptadas() {
		Assert.assertEquals(2, RepositorioEventos.filtrarSugerenciasAceptadas(sugerencias).size());
	}
	
	@Test
	public void elRepositorioEventosTiene1EventoDeUnUsuario() {
		Assert.assertEquals(1, RepositorioEventos.eventosDelUsuario(usuario).size());
	}
	
	@Test
	public void elBarMitzvaTiene2SugerenciasAceptadas() {
		List<Evento> eventos = new ArrayList<Evento>(); 
		eventos.addAll(RepositorioEventos.eventosDelUsuario(otroUsuario));
		eventos.get(0).serSugerido(sugerencias);
		Assert.assertEquals(2,RepositorioEventos.sugerenciasAceptadasDelUsuario(otroUsuario).size());
	}
	
	@Test
	public void elBarMitzvaTiene1SugerenciaRechazada() {
		List<Evento> eventos = new ArrayList<Evento>(); 
		eventos.addAll(RepositorioEventos.eventosDelUsuario(otroUsuario));
		eventos.get(0).serSugerido(sugerencias);
		Assert.assertEquals(1,RepositorioEventos.sugerenciasRechazadasDelUsuario(otroUsuario).size());
	}
}
