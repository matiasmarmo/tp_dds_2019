package dds.utn.ju_ma.group7.QueMePongo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import dds.utn.ju_ma.group7.QueMePongo.Evento.Evento;
import dds.utn.ju_ma.group7.QueMePongo.Evento.RepositorioEventos;

public class RepositorioEventosTest extends Fixture {

	@Test
	public void elRepositorioEventosTiene1EventoDeUnUsuario() {
		Assert.assertEquals(6, RepositorioEventos.eventosDelUsuario(usuario).size());
	}

	@Test
	public void elBarMitzvaTiene2SugerenciasAceptadas() {
		List<Evento> eventos = new ArrayList<Evento>();
		eventos.addAll(RepositorioEventos.eventosDelUsuario(otroUsuario));
		eventos.get(0).serSugerido(sugerencias);
		Assert.assertEquals(2, RepositorioEventos.sugerenciasAceptadasDelUsuario(otroUsuario).size());
	}

	@Test
	public void elBarMitzvaTiene1SugerenciaRechazada() {
		List<Evento> eventos = new ArrayList<Evento>();
		eventos.addAll(RepositorioEventos.eventosDelUsuario(otroUsuario));
		eventos.get(0).serSugerido(sugerencias);
		Assert.assertEquals(1, RepositorioEventos.sugerenciasRechazadasDelUsuario(otroUsuario).size());
	}
}
