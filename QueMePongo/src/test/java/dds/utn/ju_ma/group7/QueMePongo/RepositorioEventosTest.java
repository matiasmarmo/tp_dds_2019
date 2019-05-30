package dds.utn.ju_ma.group7.QueMePongo;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Evento.Evento;

public class RepositorioEventosTest extends Fixture {
	@Before
	public void init() {
		repositorioEventos.instanciarEvento(usuario, guardarropasVerano, fechaProxima, "Cumple de 15");
		repositorioEventos.instanciarEvento(otroUsuario, guardarropasVeranoEInvierno, fechaProxima, "Bar Mitzva");
	}

	@Test
	public void elRepositorioEventosTiene1EventoDeUnUsuario() {
		Assert.assertEquals(1, repositorioEventos.eventosDelUsuario(usuario).size());
	}

	@Test
	public void elBarMitzvaTiene2SugerenciasAceptadas() {
		List<Evento> eventos = new ArrayList<Evento>();
		eventos.addAll(repositorioEventos.eventosDelUsuario(otroUsuario));
		eventos.get(0).serSugerido(sugerencias);
		Assert.assertEquals(2, repositorioEventos.sugerenciasAceptadasDelUsuario(otroUsuario).size());
	}

	@Test
	public void elBarMitzvaTiene1SugerenciaRechazada() {
		List<Evento> eventos = new ArrayList<Evento>();
		eventos.addAll(repositorioEventos.eventosDelUsuario(otroUsuario));
		eventos.get(0).serSugerido(sugerencias);
		Assert.assertEquals(1, repositorioEventos.sugerenciasRechazadasDelUsuario(otroUsuario).size());
	}
}
